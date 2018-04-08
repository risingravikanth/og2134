package com.oganalysis.business.impl;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.business.RefineriesCapacityBusinessService;
import com.oganalysis.cache.RefineriesCache;
import com.oganalysis.dao.RefineriesDao;
import com.oganalysis.entities.RefineriesFilter;
import com.oganalysis.entities.Refinery;

public class RefineriesCapacityBusinessServiceImpl implements RefineriesCapacityBusinessService {

	private RefineriesDao refineriesDao;
	private static final int STARTYEAR=2005;
	private static final int ENDYEAR=2022;
	private RefineriesCache refineriesCache;
	private SimpleDateFormat sd=new SimpleDateFormat("yyyy");

	@Override
	public Map<String, Map<Integer, Double>> getCapacityByCompany(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> companiesCapacity=new HashMap<String, Map<Integer,Double>>();		
		List<String> selectedCompanies=refineriesDao.getSelectedCompanies(selectedOptions, startDate, endDate);
		if(selectedCompanies.size()>0)
			companiesCapacity=calculateCapacitiesByCompany(selectedCompanies,selectedOptions,startDate,endDate);			
		return companiesCapacity;
	}

	@Override
	public Map<String, Map<Integer, Double>> getCapacityByCountry(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> countriesCapacity=new HashMap<String, Map<Integer,Double>>();		
		List<String> selectedCountries=refineriesDao.getSelectedCountries(selectedOptions, startDate, endDate);
		if(selectedCountries.size()>0)
			countriesCapacity=calculateCapacitiesByCountry(selectedCountries,selectedOptions,startDate,endDate);			
		return countriesCapacity;
	}

	@Override
	public Map<String, Map<Integer, Double>> getCapacityByTerminal(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> terminalsCapacity=new HashMap<String, Map<Integer,Double>>();
		List<String> selectedTerminals=refineriesDao.getSelectedTerminals(selectedOptions, startDate, endDate);
		if(selectedTerminals.size()>0)
			terminalsCapacity=calculateCapacitiesByTerminal(selectedTerminals,startDate,endDate);			
		return terminalsCapacity;
	}
	private Map<String,Map<Integer,Double>> calculateCapacitiesByCompany(List<String> selectedCompanies,Map<String,List<String>> selectedOptions,int startDate,int endDate)
	{
		List<String> selectedTerminals=refineriesDao.getSelectedTerminals(selectedOptions, startDate, endDate);		
		List<Integer> years=getSelectedYears(startDate, endDate);
		Map<String,Double> terminalsYearCapacity=null;
		Map<String,Double> companyStakes=null;
		if(null==refineriesCache.getTerminalsYearCapacity())
		{
			terminalsYearCapacity=refineriesCache.createTerminalsYearCapacity();
			refineriesCache.setTerminalsYearCapacity(terminalsYearCapacity);
		}
		else
			terminalsYearCapacity=refineriesCache.getTerminalsYearCapacity();
		
		if(null==refineriesCache.getCompanyStakeForTerminal())
		{
			companyStakes=refineriesCache.createCompanyStakeForTerminal();
			refineriesCache.setCompanyStakeForTerminal(companyStakes);
		}
		else
			companyStakes=refineriesCache.getCompanyStakeForTerminal();
		Map<Integer,Double> yearMap=null;
		Map<String,Map<Integer,Double>> companiesCapacity=new HashMap<String, Map<Integer,Double>>();
		for(String company:selectedCompanies)
		{
			List<String> companyTerminals=getCompanyTerminals(company,selectedTerminals);
			if(companyTerminals.size()>0)
			{
				yearMap=new HashMap<Integer, Double>();
				for(Integer year:years)
				{
					double soc=0;					
					for(String terminal:companyTerminals)
					{
						double stake=companyStakes.get(company.toLowerCase()+UNDERSCORE+terminal.toLowerCase())==null?0:companyStakes.get(company.toLowerCase()+UNDERSCORE+terminal.toLowerCase());
						double capacity=terminalsYearCapacity.get(terminal.toLowerCase()+year)==null?0:terminalsYearCapacity.get(terminal.toLowerCase()+year);
						soc=soc+(capacity*(stake/100));											
					}
					soc=round(soc,2);
					yearMap.put(year, soc);
				}
				companiesCapacity.put(company, yearMap);
			}						
		}
		return companiesCapacity;
	}
	private Map<String,Map<Integer,Double>> calculateCapacitiesByCountry(List<String> selectedCountries,Map<String,List<String>> selectedOptions,int startDate,int endDate)	
	{
		List<String> selectedTerminals=refineriesDao.getSelectedTerminals(selectedOptions, startDate, endDate);		
		List<Integer> years=getSelectedYears(startDate, endDate);
		Map<String,Double> terminalsYearCapacity=null;
		Map<Integer,Double> yearMap=null;
		Map<String,Map<Integer,Double>> countriesCapacity=new HashMap<String, Map<Integer,Double>>();
		if(null==refineriesCache.getTerminalsYearCapacity())
		{
			terminalsYearCapacity=refineriesCache.createTerminalsYearCapacity();
			refineriesCache.setTerminalsYearCapacity(terminalsYearCapacity);
		}
		else
			terminalsYearCapacity=refineriesCache.getTerminalsYearCapacity();
		for(String country:selectedCountries)
		{
			List<String> countryTerminals=getCountryTerminals(country,selectedTerminals);
			if(countryTerminals.size()>0)
			{
				yearMap=new HashMap<Integer, Double>();
				for(Integer year:years)
				{
					double soc=0;					
					for(String terminal:countryTerminals)
					{												
						double capacity=terminalsYearCapacity.get(terminal.toLowerCase()+year)==null?0:terminalsYearCapacity.get(terminal.toLowerCase()+year);
						soc=soc+capacity;												
					}
					soc=round(soc,2);
					yearMap.put(year, soc);
				}
				countriesCapacity.put(country, yearMap);
			}						
		}
		return countriesCapacity;
	}
	private Map<String,Map<Integer,Double>> calculateCapacitiesByTerminal(List<String> selectedTerminals,int startDate,int endDate)	
	{
		
		List<Integer> years=getSelectedYears(startDate, endDate);
		Map<String,Double> terminalsYearCapacity=null;
		Map<Integer,Double> yearMap=null;
		Map<String,Map<Integer,Double>> terminalsCapacity=new HashMap<String, Map<Integer,Double>>();
		if(null==refineriesCache.getTerminalsYearCapacity())
		{
			terminalsYearCapacity=refineriesCache.createTerminalsYearCapacity();
			refineriesCache.setTerminalsYearCapacity(terminalsYearCapacity);
		}
		else
			terminalsYearCapacity=refineriesCache.getTerminalsYearCapacity();
		for(String terminal:selectedTerminals)
		{
				yearMap=new HashMap<Integer, Double>();
				
				for(Integer year:years)
				{				
					double soc=0;
					double capacity=terminalsYearCapacity.get(terminal.toLowerCase()+year)==null?0:terminalsYearCapacity.get(terminal.toLowerCase()+year);
					soc=soc+capacity;				
					soc=round(soc,2);
					yearMap.put(year, soc);					
				}
				terminalsCapacity.put(terminal, yearMap);							
		}
		return terminalsCapacity;
	}
	@Override
	public Map<String, Map<Integer, Double>> getModalCapacityForRecord(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate, String displayType, String recordName) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> terminalCapacities=new HashMap<String, Map<Integer,Double>>();
		
		if(null!=displayType && displayType.equalsIgnoreCase(COUNTRY))
			terminalCapacities=getTerminalsCapacityForCountry(recordName,selectedOptions,startDate,endDate);
		else if(null!=displayType && displayType.equalsIgnoreCase(COMPANY))
			terminalCapacities=getTerminalsCapacityForCompany(recordName,selectedOptions,startDate,endDate);
		return terminalCapacities;
	}
	private Map<String,Map<Integer,Double>> getTerminalsCapacityForCountry(String countryName,Map<String,List<String>> selectedOptions,int startDate,int endDate)
	{
		List<String> selectedTerminals=refineriesDao.getSelectedTerminals(selectedOptions, startDate, endDate);
		List<String> countryTerminals=getCountryTerminals(countryName, selectedTerminals);		
		Map<String,Map<Integer,Double>> countryterminalsCapacity=calculateCapacitiesByTerminal(countryTerminals,startDate,endDate);					
		return countryterminalsCapacity;
		
	}
	private Map<String,Map<Integer,Double>> getTerminalsCapacityForCompany(String companyName,Map<String,List<String>> selectedOptions,int startDate,int endDate)
	{				
		List<String> selectedTerminals=refineriesDao.getSelectedTerminals(selectedOptions, startDate, endDate);
		List<String> companyTerminals=getCompanyTerminals(companyName, selectedTerminals);		
		Map<String,Map<Integer,Double>> companyterminalsCapacity=calculateTerminalsCapacityForCompany(companyName,companyTerminals,startDate,endDate);				
		return companyterminalsCapacity;
	}
	private Map<String,Map<Integer,Double>> calculateTerminalsCapacityForCompany(String companyName,List<String> terminals,int startDate,int endDate)
	{
		List<Integer> years=getSelectedYears(startDate, endDate);
		Map<String,Double> terminalsYearCapacity=null;
		Map<Integer,Double> yearMap=null;
		Map<String,Map<Integer,Double>> terminalsCapacity=new HashMap<String, Map<Integer,Double>>();
		Map<String,Double> companyStakes=null;
		if(null==refineriesCache.getTerminalsYearCapacity())
		{
			terminalsYearCapacity=refineriesCache.createTerminalsYearCapacity();
			refineriesCache.setTerminalsYearCapacity(terminalsYearCapacity);
		}
		else
			terminalsYearCapacity=refineriesCache.getTerminalsYearCapacity();
		
		if(null==refineriesCache.getCompanyStakeForTerminal())
		{
			companyStakes=refineriesCache.createCompanyStakeForTerminal();
			refineriesCache.setCompanyStakeForTerminal(companyStakes);
		}
		else
			companyStakes=refineriesCache.getCompanyStakeForTerminal();
		for(String terminal:terminals)
		{
				yearMap=new HashMap<Integer, Double>();
				double stake=companyStakes.get(companyName.toLowerCase()+UNDERSCORE+terminal.toLowerCase())==null?0:companyStakes.get(companyName.toLowerCase()+UNDERSCORE+terminal.toLowerCase());
				for(Integer year:years)
				{				
					double soc=0;
					double capacity=terminalsYearCapacity.get(terminal.toLowerCase()+year)==null?0:terminalsYearCapacity.get(terminal.toLowerCase()+year);
					soc=soc+(capacity*(stake/100));				
					soc=round(soc,2);
					yearMap.put(year, soc);					
				}
				terminalsCapacity.put(terminal, yearMap);							
		}
		return terminalsCapacity;
	}
	@Override
	public Map getTerminalData(String terminalName) {
		// TODO Auto-generated method stub
		Map terminalData=new HashMap();
		List<Refinery> refineryList=refineriesDao.getTerminalData(terminalName);
		Refinery refinery=refineryList.get(0);		
		terminalData.put(TERMINALNAME, refinery.getName());
		//General Info
		terminalData.put(REGION, (null!=refinery.getRegion() && !refinery.getRegion().equals(BLANK))?refinery.getRegion():NA);
		terminalData.put(COUNTRY,(null!=refinery.getCountry() && !refinery.getCountry().equals(BLANK))?refinery.getCountry():NA);
		terminalData.put(LOCATION,(null!=refinery.getLocation() && !refinery.getLocation().equals(BLANK))?refinery.getLocation():NA);
		String type=getType(refineryList);
		terminalData.put(TYPE,(null!=type && !type.equals(BLANK))?type:NA);
		terminalData.put(STATUS,(null!=refinery.getStatus() && !refinery.getStatus().equals(BLANK))?refinery.getStatus():NA);
		terminalData.put(CAPACITY, refinery.getRefiningCapacity());
		String statusDetails=getStatusDetails(refineryList);
		terminalData.put(RECENTDEVELOPMENTS,(null!=statusDetails && !statusDetails.equals(BLANK)?statusDetails:NA));
		String startYear=getStartYear(refineryList);
		terminalData.put(STARTUP,(null!=startYear && !startYear.equals(BLANK))?startYear:NA);
		
		//Company Info
		String operator=getOperator(refineryList);
		terminalData.put(OPERATOR,(null!=operator && !operator.equals(BLANK)?operator:NA));
		terminalData.put(OWNERSHIP, getOwnership(terminalName));
		//Investment Info
		terminalData.put(CAPEX,round(getCapex(refineryList),2));
		terminalData.put(CONSTRUCTIONDETAILS, getConstructionDetails(refineryList));
		
		//Capaciity Forecasts
		terminalData.put(CDUCAPACITY,getCduCapacity(refineryList));
		terminalData.put(VDUCAPACITY,getVduCapacity(refineryList));
		terminalData.put(COKINGCAPACITY,getCokingCapacity(refineryList));
		terminalData.put(FCCCAPACITY, getFccCapacity(refineryList));
		terminalData.put(HYDROCRACKINGCAPACITY, getHydroCrackingCapacity(refineryList));
		
//		terminalData.put(DECOMISSIONEDYEAR, getDecomissionedYear(refineryList));
//		
//		
//		
//		terminalData.put(HISTORICOPERATOR, getHistoricOperator(refineryList));
//		terminalData.put(HISTORICOWNERSHIP, getHistoricOwnership(terminalName));
//		
//		
//		
//		terminalData.put(CRUDESTORAGEORTANK,getCrudeStorageOrTankCapacity(refineryList));
//		terminalData.put(CRUDESTORAGECAPACITY,getCrudeStorageCapacity(refineryList));
//		terminalData.put(VISBREAKINGCAPACITY, getVisbreakingCapacity(refineryList));
//		terminalData.put(REFORMERCAPACITY, getReformerCapacity(refineryList));
//		terminalData.put(HYDROTREATINGCAPACITY,getHydroTreatingCapacity(refineryList));
//		terminalData.put(ALKYLATIONCAPACITY,getAlkylationCapacity(refineryList));
//		terminalData.put(AROMACTICSCAPACITY,getAromaticsCapacity(refineryList));
//		terminalData.put(ISOMERIZATIONCAPACITY,getIsomerizationCapacity(refineryList));
//		terminalData.put(POLYMERIZATIONCAPACITY, getPolymerizationCapacity(refineryList));
//		terminalData.put(LUBESCAPACITY,getLubesCapacity(refineryList));
//		terminalData.put(OXYGENATESCAPACITY, getOxygenatesCapacity(refineryList));
//		terminalData.put(COKECAPACITY,getCokeCapacity(refineryList));
//		terminalData.put(SULPHURCAPACITY,getSulphurCapacity(refineryList));
//		terminalData.put(HYDROGENCAPACITY,getHydrogenCapacity(refineryList));
//		terminalData.put(ASPHALTCAPACITY,getAsphaltCapacity(refineryList));
//		terminalData.put(GASOLINE, getGasoline(refineryList));
//		terminalData.put(LPG,getLpg(refineryList));
//		terminalData.put(KEROSINE,getKerosine(refineryList));
//		terminalData.put(JETFUEL, getJetFuel(refineryList));
//		terminalData.put(DIESEL, getDiesel(refineryList));
//		terminalData.put(PROPYLENE, getPropylene(refineryList));
//		terminalData.put(LIGHTNAPHTHA, getLightNaphtha(refineryList));
//		terminalData.put(HEAVYNAPHTHA,getHeavyNaphtha(refineryList));
//		terminalData.put(KEROJET,getKerojet(refineryList));
//		
//		terminalData.put(MAINTENANCEDETAILS, getMaintenanceDetails(refineryList));
//		terminalData.put(MAINTENANCENOTE, getMaintenanceNote(refineryList));
//		terminalData.put(NELSONCOMPLEXINDEX, getNelsonComplexIndex(refineryList));
//		terminalData.put(REFINERYUTILIZATIONRATE, getRefineryUtilizationRate(refineryList));
//		terminalData.put(OTHERSNAMESOFREFINERY, getOtherNamesOfRefinery(refineryList));
		return terminalData;		
	}
	private String getStatusDetails(List<Refinery> refineryList)
	{
		StringBuffer statusDetails=new StringBuffer();
		
		for(Refinery refinery:refineryList)
		{
			if(null!=refinery && !(BLANK).equalsIgnoreCase(refinery.getStatusDetails()))
				statusDetails.append(refinery.getStatusDetails()).append(COMMA);								
		}
		removeCommaAtEnd(statusDetails);
		return statusDetails.toString();
	}
	private String getOtherNamesOfRefinery(List<Refinery> refineryList)
	{
		StringBuffer otherNames=new StringBuffer();
		
		for(Refinery refinery:refineryList)
		{
			if(null!=refinery && !(BLANK).equalsIgnoreCase(refinery.getRefineryOtherNames()))
				otherNames.append(refinery.getRefineryOtherNames()).append(COMMA);								
		}
		return otherNames.toString();
	}
	private String getRefineryUtilizationRate(List<Refinery> refineryList)
	{
		StringBuffer refineryUtilizationRate=new StringBuffer();
		
		for(Refinery refinery:refineryList)
		{
			if(null!=refinery && 0!=refinery.getRefineryUtilization())
				refineryUtilizationRate.append(refinery.getRefineryUtilization()).append(COMMA);								
		}
		return refineryUtilizationRate.toString();
	}
	private String getNelsonComplexIndex(List<Refinery> refineryList)
	{
		StringBuffer nelsonComplexIndex=new StringBuffer();
		
		for(Refinery refinery:refineryList)
		{
			if(null!=refinery && 0!=refinery.getNelsonComplexityIndex())
				nelsonComplexIndex.append(refinery.getNelsonComplexityIndex()).append(COMMA);								
		}
		return nelsonComplexIndex.toString();
	}
	private String getMaintenanceNote(List<Refinery> refineryList)
	{
		StringBuffer maintenanceNote=new StringBuffer();
		
		for(Refinery refinery:refineryList)
		{
			if(null!=refinery && !(BLANK).equalsIgnoreCase(refinery.getMaintananceNote()))
				maintenanceNote.append(refinery.getMaintananceNote()).append(COMMA);								
		}
	
	return maintenanceNote.toString();
	}
	private List<Map<String,Integer>> getMaintenanceDetails(List<Refinery> refineryList)
	{
		List<Map<String,Integer>> maintenanceDetails=new ArrayList<Map<String,Integer>>();
		Map<String,Integer> maintenanceDetailsMap=null;
		for(Refinery refinery:refineryList)
		{
			maintenanceDetailsMap=new HashMap<String, Integer>();
			maintenanceDetailsMap.put(MAINTENANCESTARTDATE,refinery.getMaintananceStartDate());
			maintenanceDetailsMap.put(MAINTENANCEENDDATE,refinery.getMaintananceEndDate());			
			maintenanceDetails.add(maintenanceDetailsMap);
		}
		return maintenanceDetails;
	}
	private List<Map<String,String>> getConstructionDetails(List<Refinery> refineryList)
	{
		List<Map<String,String>> constructionDetails=new ArrayList<Map<String,String>>();
		Map<String,String> constructionDetailsMap=null;
		for(Refinery refinery:refineryList)
		{
			constructionDetailsMap=new HashMap<String, String>();
			if(null!=refinery.getConstructionCompanyName() && !BLANK.equalsIgnoreCase(refinery.getConstructionCompanyName()))			
				constructionDetailsMap.put(CONSTRUCTIONCOMPANYNAME,refinery.getConstructionCompanyName());			
			else
				constructionDetailsMap.put(CONSTRUCTIONCOMPANYNAME, BLANK);
			
			if(null!=refinery.getConstructionContractDetails() && !BLANK.equalsIgnoreCase(refinery.getConstructionContractDetails()))			
				constructionDetailsMap.put(CONSTRUCTIONCONTRACTDETAILS,refinery.getConstructionContractDetails());			
			else
				constructionDetailsMap.put(CONSTRUCTIONCONTRACTDETAILS, BLANK);
			constructionDetails.add(constructionDetailsMap);
		}
		return constructionDetails;
			
	}
	private String getKerojet(List<Refinery> refineryList)
	{
		StringBuffer kerojet=new StringBuffer();
		
			for(Refinery refinery:refineryList)
			{
				if(null!=refinery && 0!=refinery.getKerojet())
					kerojet.append(refinery.getKerojet()).append(COMMA);								
			}
		
		return kerojet.toString();
	}
	private String getHeavyNaphtha(List<Refinery> refineryList)
	{
		StringBuffer heavyNaphtha=new StringBuffer();
		
			for(Refinery refinery:refineryList)
			{
				if(null!=refinery && 0!=refinery.getHeavyNaphtha())
					heavyNaphtha.append(refinery.getHeavyNaphtha()).append(COMMA);								
			}
		
		return heavyNaphtha.toString();
	}
	private String getLightNaphtha(List<Refinery> refineryList)
	{
		StringBuffer lightNaphtha=new StringBuffer();
		
			for(Refinery refinery:refineryList)
			{
				if(null!=refinery && 0!=refinery.getLightNaphtha())
					lightNaphtha.append(refinery.getLightNaphtha()).append(COMMA);								
			}
		
		return lightNaphtha.toString();
	}
	private String getPropylene(List<Refinery> refineryList)
	{
		StringBuffer propylene=new StringBuffer();
		
			for(Refinery refinery:refineryList)
			{
				if(null!=refinery && 0!=refinery.getPropylene())
						propylene.append(refinery.getPropylene()).append(COMMA);								
			}
		
		return propylene.toString();
	}
	private String getDiesel(List<Refinery> refineryList)
	{
		StringBuffer diesel=new StringBuffer();
		
			for(Refinery refinery:refineryList)
			{
				if(null!=refinery && 0!=refinery.getDiesel())
						diesel.append(refinery.getDiesel()).append(COMMA);								
			}
		
		return diesel.toString();
	}
	private String getJetFuel(List<Refinery> refineryList)
	{
		StringBuffer jetFuel=new StringBuffer();
		
			for(Refinery refinery:refineryList)
			{
				if(null!=refinery && 0!=refinery.getJetFuel())
						jetFuel.append(refinery.getJetFuel()).append(COMMA);								
			}
		
		return jetFuel.toString();
	}
	private String getKerosine(List<Refinery> refineryList)
	{
		StringBuffer kerosine=new StringBuffer();
		
			for(Refinery refinery:refineryList)
			{
				if(null!=refinery && 0!=refinery.getKerosine())
						kerosine.append(refinery.getKerosine()).append(COMMA);								
			}
		
		return kerosine.toString();
	}
	private String getLpg(List<Refinery> refineryList)
	{
		StringBuffer lpg=new StringBuffer();
		
			for(Refinery refinery:refineryList)
			{
				if(null!=refinery && 0!=refinery.getLpg())
						lpg.append(refinery.getLpg()).append(COMMA);								
			}
		
		return lpg.toString();
	}
	private String getGasoline(List<Refinery> refineryList)
	{
		StringBuffer gasoline=new StringBuffer();
		
			for(Refinery refinery:refineryList)
			{
				if(null!=refinery && 0!=refinery.getGasolinePetrol())
						gasoline.append(refinery.getGasolinePetrol()).append(COMMA);								
			}
		
		return gasoline.toString();
	}
	private Map<Integer,Double> getAsphaltCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> asphaltCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					asphaltCapacity.put(i, refinery.getAsphaltCapacity());
					
				}
			}
		}
		return asphaltCapacity;
	}
	private Map<Integer,Double> getHydrogenCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> hydrogenCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					hydrogenCapacity.put(i, refinery.getHydrogenCapacity());
					
				}
			}
		}
		return hydrogenCapacity;
	}
	private Map<Integer,Double> getSulphurCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> sulphurCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					sulphurCapacity.put(i, refinery.getSulphurCapacity());
					
				}
			}
		}
		return sulphurCapacity;
	}
	private Map<Integer,Double> getCokeCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> cokeCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					cokeCapacity.put(i, refinery.getCokeCapacity());
					
				}
			}
		}
		return cokeCapacity;
	}
	private Map<Integer,Double> getOxygenatesCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> oxygenatesCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					oxygenatesCapacity.put(i, refinery.getOxygenatesCapacity());
					
				}
			}
		}
		return oxygenatesCapacity;
	}
	private Map<Integer,Double> getLubesCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> lubesCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					lubesCapacity.put(i, refinery.getLubesCapacity());
					
				}
			}
		}
		return lubesCapacity;
	}
	private Map<Integer,Double> getPolymerizationCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> polymerizationCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					polymerizationCapacity.put(i, refinery.getPolymerizationCapacity());
					
				}
			}
		}
		return polymerizationCapacity;
	}
	private Map<Integer,Double> getIsomerizationCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> isomerizationCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					isomerizationCapacity.put(i, refinery.getIsomerizationCapacity());
					
				}
			}
		}
		return isomerizationCapacity;
	}
	private Map<Integer,Double> getAromaticsCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> aromaticsCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					aromaticsCapacity.put(i, refinery.getAromaticsCapacity());
					
				}
			}
		}
		return aromaticsCapacity;
	}
	private Map<Integer,Double> getAlkylationCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> alkylationCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					alkylationCapacity.put(i, refinery.getAlkylationCapacity());
					
				}
			}
		}
		return alkylationCapacity;
	}
	private Map<Integer,Double> getHydroTreatingCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> hydroTreatingCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					hydroTreatingCapacity.put(i, refinery.getHydrotreatingCapacity());
					
				}
			}
		}
		return hydroTreatingCapacity;
	}
	private Map<Integer,Double> getReformerCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> reformerCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					reformerCapacity.put(i, refinery.getReformerCapacity());
					
				}
			}
		}
		return reformerCapacity;
	}
	private Map<Integer,Double> getVisbreakingCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> visbreakingCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					visbreakingCapacity.put(i, refinery.getVisbreakingCapacity());
					
				}
			}
		}
		return visbreakingCapacity;
	}
	private Map<Integer,Double> getCrudeStorageCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> crudeStorageCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					crudeStorageCapacity.put(i, refinery.getCrudeStorageCapacity());
					
				}
			}
		}
		return crudeStorageCapacity;
	}
	private Map<Integer,Double> getCrudeStorageOrTankCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> crudeStorageOrTankCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					crudeStorageOrTankCapacity.put(i, refinery.getCrudeStorageUnitOrTanksNo());
					
				}
			}
		}
		return crudeStorageOrTankCapacity;
	}
	private Map<Integer,Double> getHydroCrackingCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> hydroCrackingCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					hydroCrackingCapacity.put(i, refinery.getHydroCrackingCapacity());
					
				}
			}
		}
		return hydroCrackingCapacity;
	}
	private Map<Integer,Double> getFccCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> fccCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					fccCapacity.put(i, refinery.getFcc());
					
				}
			}
		}
		return fccCapacity;
	}
	private Map<Integer,Double> getCokingCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> cokingCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					cokingCapacity.put(i, refinery.getCokingCapacity());
					
				}
			}
		}
		return cokingCapacity;
	}
	private Map<Integer,Double> getVduCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> vduCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					vduCapacity.put(i, refinery.getVduCapacity());
					
				}
			}
		}
		return vduCapacity;
	}
	private Map<Integer,Double> getCduCapacity(List<Refinery> refineryList)
	{
		Map<Integer,Double> cduCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Refinery refinery:refineryList)
			{
				if(i!=0 && refinery.getCapacityYear()==i)
				{
					cduCapacity.put(i, refinery.getRefiningCapacity());
					
				}
			}
		}
		return cduCapacity;
	}
	private List<Map<String,String>> getOwnership(String terminalName)
	{
			
		List<Map<String,String>> ownershipList=new ArrayList<Map<String,String>>();
		List<RefineriesFilter> refineriesFilterList=refineriesDao.getTerminalCompanies(terminalName);			
		Map<String,String> ownershipMap=null;new HashMap<String, String>();
		for(RefineriesFilter refineriesFilter:refineriesFilterList)
		{				
				ownershipMap=new HashMap<String, String>();
//				String key=refineriesFilter.getCurrentEquityPartners()+UNDERSCORE+refineriesFilter.getName();	
				if(refineriesFilter.getCurrentEquityStakes()!=0)
				{
					ownershipMap.put(CURRENTEQUITYPARTNER,refineriesFilter.getCurrentEquityPartners());
					ownershipMap.put(CURRENTEQUITYSTAKE,String.valueOf(refineriesFilter.getCurrentEquityStakes()));
					ownershipList.add(ownershipMap);
				}
				
		}
		
		return ownershipList;
	}
	private List<Map<String,String>> getHistoricOwnership(String terminalName)
	{
			
		List<Map<String,String>> historicOwnershipList=new ArrayList<Map<String,String>>();
		List<RefineriesFilter> refineriesFilterList=refineriesDao.getTerminalHistoricCompanies(terminalName);
		Map<String,String> historicOwnershipMap=null;new HashMap<String, String>();
		for(RefineriesFilter refineriesFilter:refineriesFilterList)
		{				
			historicOwnershipMap=new HashMap<String, String>();				
			historicOwnershipMap.put(HISTORICEQUITYPARTNER,refineriesFilter.getHistoricEquityPartners());
			historicOwnershipMap.put(HISTORICEQUITYSTAKE,String.valueOf(refineriesFilter.getHistoricEquityStakes()));
			historicOwnershipList.add(historicOwnershipMap);
		}
		
		return historicOwnershipList;
	}
	private String getHistoricOperator(List<Refinery> refineryList)
	{
		StringBuffer sb=new StringBuffer();
		for(Refinery refinery:refineryList)
		{
			if(null!=refinery && null!=refinery.getHistoricOperator() && !(BLANK).equalsIgnoreCase(refinery.getHistoricOperator()))
				sb.append(refinery.getHistoricOperator()).append(COMMA);
		}
		return sb.toString();
	}
	private String getOperator(List<Refinery> refineryList)
	{
		StringBuffer sb=new StringBuffer();
		for(Refinery refinery:refineryList)
		{
			if(null!=refinery && null!=refinery.getCurrentOperator() && !(BLANK).equalsIgnoreCase(refinery.getCurrentOperator()))
				sb.append(refinery.getCurrentOperator()).append(COMMA);
		}
		return sb.toString();
	}
	private String getType(List<Refinery> refineryList)
	{
		StringBuffer sb=new StringBuffer();
		for(Refinery refinery:refineryList)
		{
			if(null!=refinery && null!=refinery.getType() && !(BLANK).equalsIgnoreCase(refinery.getType()))
				sb.append(refinery.getType()).append(COMMA);
		}
		removeCommaAtEnd(sb);
		return sb.toString();
	}
	private String getStartYear(List<Refinery> refineryList)
	{
		StringBuffer sb=new StringBuffer();
		for(Refinery refinery:refineryList)
		{
			if(null!=refinery && null!=refinery.getStartYear())
				sb.append(sd.format(refinery.getStartYear())).append(COMMA);
		}
		removeCommaAtEnd(sb);
		return sb.toString();
	}
	private String getDecomissionedYear(List<Refinery> refineryList)
	{
		StringBuffer sb=new StringBuffer();
		for(Refinery refinery:refineryList)
		{
			if(null!=refinery && null!=refinery.getDecomissionedYear())
				sb.append(refinery.getDecomissionedYear()).append(COMMA);
		}
		return sb.toString();
	}
	private void removeCommaAtEnd(StringBuffer inputString)
	{		
		if(inputString.length()>0)
		inputString.deleteCharAt(inputString.length()-1);
	}
	private double getCapex(List<Refinery> refineryList)
	{
		double capex=0;
		for(Refinery refinery:refineryList)
		{
			if(null!=refinery)
				capex=capex+refinery.getCapex();
		}
		return capex;
	}
	private double round(double value, int places) {	    

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	private List<Integer> getSelectedYears(int startDate,int endDate)
	{
		List<Integer> years=new ArrayList<Integer>();
		for(int i=startDate;i<=endDate;i++)
			years.add(i);
		return years;
	}	
	private List<String> getCompanyTerminals(String company,List<String> selectedTerminals)
	{
		List<String> companyTerminals=new ArrayList<String>();
		List<String> terminalsList=null;
		if(null==refineriesCache.getCompanyTerminals())
		{
			Map<String,List<String>> companyTerminalsMap=refineriesCache.createCompanyTerminals();
			refineriesCache.setCompanyTerminals(companyTerminalsMap);
			terminalsList=companyTerminalsMap.get(company.toLowerCase());
		}
		else
			terminalsList=refineriesCache.getCompanyTerminals().get(company.toLowerCase());
		for(String terminal:selectedTerminals)
		{
			if(terminalsList.contains(terminal))
				companyTerminals.add(terminal);
		}
		return companyTerminals;
	}
	private List<String> getCountryTerminals(String country,List<String> selectedTerminals)
	{
		List<String> countryTerminals=new ArrayList<String>();
		List<String> terminalsList=null;		 
		if(null==refineriesCache.getCountryTerminals()){
			Map<String,List<String>> countryTerminalsMap=refineriesCache.createCountryTerminals();
			refineriesCache.setCountryTerminals(countryTerminalsMap);
			terminalsList=countryTerminalsMap.get(country.toLowerCase());
		}
		else
			terminalsList=refineriesCache.getCountryTerminals().get(country.toLowerCase());
		for(String terminal:selectedTerminals)
		{
			if(terminalsList.contains(terminal))
				countryTerminals.add(terminal);
		}
		return countryTerminals;
	}
	@Override
	public List<String> getRegions() {
		// TODO Auto-generated method stub
		return refineriesDao.getRegions();
	}

	@Override
	public List<String> getCountries() {
		// TODO Auto-generated method stub
		return refineriesDao.getCountries();
	}

	@Override
	public List<String> getStatus() {
		// TODO Auto-generated method stub
		return refineriesDao.getStatus();
	}

	@Override
	public List<String> getLocations() {
		// TODO Auto-generated method stub
		return refineriesDao.getLocations();
	}

	@Override
	public List<String> getOperators() {
		// TODO Auto-generated method stub
		return refineriesDao.getOperators();
	}

	@Override
	public List<String> getOwners() {
		// TODO Auto-generated method stub
		return refineriesDao.getOwners();
	}
	public RefineriesDao getRefineriesDao() {
		return refineriesDao;
	}

	public void setRefineriesDao(RefineriesDao refineriesDao) {
		this.refineriesDao = refineriesDao;
	}

	public RefineriesCache getRefineriesCache() {
		return refineriesCache;
	}

	public void setRefineriesCache(RefineriesCache refineriesCache) {
		this.refineriesCache = refineriesCache;
	}

	
	
}