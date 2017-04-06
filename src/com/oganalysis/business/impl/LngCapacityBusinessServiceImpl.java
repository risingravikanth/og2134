package com.oganalysis.business.impl;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oganalysis.business.LngCapacityBusinessService;
import com.oganalysis.cache.LngCache;
import com.oganalysis.dao.LngDao;
import com.oganalysis.entities.Lng;
import com.oganalysis.entities.LngFilter;

public class LngCapacityBusinessServiceImpl implements LngCapacityBusinessService {

	private LngDao lngDao;
	private LngCache lngCache;
	private static final int STARTYEAR=2005;
	private static final int ENDYEAR=2020;
	private static final double BCF_UNIT=48.7;
	private static final String BCF="bcf";
	private static final String UNDERSCORE="_";
	
	@Override
	public Map<String,Map<Integer,Double>> getRegasificationCapacityByCountry(Map<String,List<String>>selectedOptions,String startDate,String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
				
		Map<String,Map<Integer,Double>> regasification=new HashMap<String, Map<Integer,Double>>();
		List<String> countries=lngDao.getSelectedCountries(selectedOptions,startDateVal,endDateVal,LNG_REGASIFICATION);
		if(countries.size()>0)								
			regasification=calculateCapacitiesByCountry(countries,selectedOptions,startDateVal,endDateVal,LNG_REGASIFICATION);
				
		return regasification;
	}
	@Override
	public Map<String,Map<Integer,Double>> getLiquefactionCapacityByCountry(Map<String,List<String>>selectedOptions,String startDate,String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
		
		Map<String,Map<Integer,Double>> liquefaction=new HashMap<String, Map<Integer,Double>>();		
		List<String> countries=lngDao.getSelectedCountries(selectedOptions,startDateVal,endDateVal,LNG_LIQUEFACTION);
		if(countries.size()>0)						
			liquefaction=calculateCapacitiesByCountry(countries,selectedOptions,startDateVal,endDateVal,LNG_LIQUEFACTION);		
		return liquefaction;
	}
	@Override
	public Map<String, Map<Integer, Double>> getRegasificationCapacityByTerminal(
			Map<String, List<String>> selectedOptions, String startDate, String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		
		Map<String,Map<Integer,Double>> regasification=new HashMap<String, Map<Integer,Double>>();
		List<String> terminals=lngDao.getSelectedTerminals(selectedOptions,startDateVal,endDateVal,LNG_REGASIFICATION);
		if(terminals.size()>0)							
			regasification=calculateCapacitiesByTerminal(terminals,selectedOptions,startDateVal,endDateVal,LNG_REGASIFICATION);								
		return regasification;
	}
	@Override
	public Map<String, Map<Integer, Double>> getLiquefactionCapacityByTerminal(
			Map<String, List<String>> selectedOptions, String startDate, String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
		
		Map<String,Map<Integer,Double>> liquefaction=new HashMap<String, Map<Integer,Double>>();
		List<String> terminals=lngDao.getSelectedTerminals(selectedOptions,startDateVal,endDateVal,LNG_LIQUEFACTION);
		if(terminals.size()>0)					
			liquefaction=calculateCapacitiesByTerminal(terminals,selectedOptions,startDateVal,endDateVal,LNG_LIQUEFACTION);				
		return liquefaction;
	}
	@Override
	public Map<String, Map<Integer, Double>> getRegasificationCapacityByCompany(
			Map<String, List<String>> selectedOptions, String startDate, String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		
		Map<String,Map<Integer,Double>> regasification=new HashMap<String, Map<Integer,Double>>();
		List<String> companies=lngDao.getSelectedCompanies(selectedOptions,startDateVal,endDateVal,LNG_REGASIFICATION);			
		if(companies.size()>0)				
			regasification=calculateCapacitiesByCompany(companies,selectedOptions,startDateVal,endDateVal,LNG_REGASIFICATION);
		
				
		return regasification;
	}
	@Override
	public Map<String, Map<Integer, Double>> getLiquefactionCapacityByCompany(
			Map<String, List<String>> selectedOptions, String startDate, String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
		
		Map<String,Map<Integer,Double>> liquefaction=new HashMap<String, Map<Integer,Double>>();
		List<String> companies=lngDao.getSelectedCompanies(selectedOptions,startDateVal,endDateVal,LNG_LIQUEFACTION);
		if(companies.size()>0)
				liquefaction=calculateCapacitiesByCompany(companies,selectedOptions,startDateVal,endDateVal,LNG_LIQUEFACTION);		
		return liquefaction;		
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.oganalysis.business.LngCapacityBusinessService#getLiqueModalCapacityForRecord(java.util.Map, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * Modal data for a record of type Liquefaction
	 */
	@Override
	public Map<String, Map<Integer, Double>> getLiqueModalCapacityForRecord(Map<String,List<String>> selectedOptions,
			String startDate,
			String endDate, String displayType, String recordName) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
			Map<String,Map<Integer,Double>> terminalCapacities=new HashMap<String, Map<Integer,Double>>();
		if(null!=displayType && displayType.equalsIgnoreCase(COUNTRY))
			terminalCapacities=getTerminalsCapacityForCountry(recordName,selectedOptions,startDateVal,endDateVal,LNG_LIQUEFACTION);
		else if(null!=displayType && displayType.equalsIgnoreCase(COMPANY))					
			terminalCapacities=getTerminalsCapacityForCompany(recordName,selectedOptions,startDateVal,endDateVal,LNG_LIQUEFACTION);
						
		return terminalCapacities;
	}
	/*
	 * (non-Javadoc)
	 * @see com.oganalysis.business.LngCapacityBusinessService#getRegasModalCapacityForRecord(java.util.Map, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * Modal data for a record of type Regasifiation
	 */
	@Override
	public Map<String, Map<Integer, Double>> getRegasModalCapacityForRecord(Map<String,List<String>> selectedOptions,
			String startDate,
			String endDate, String displayType, String recordName) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
		
		Map<String,Map<Integer,Double>> terminalCapacities=new HashMap<String, Map<Integer,Double>>();
		
		if(null!=displayType && displayType.equalsIgnoreCase(COUNTRY))
			terminalCapacities=getTerminalsCapacityForCountry(recordName,selectedOptions,startDateVal,endDateVal,LNG_REGASIFICATION);
		else if(null!=displayType && displayType.equalsIgnoreCase(COMPANY))
			terminalCapacities=getTerminalsCapacityForCompany(recordName,selectedOptions,startDateVal,endDateVal,LNG_REGASIFICATION);
		
		return terminalCapacities;
	}
	private Map<String,Map<Integer,Double>> getTerminalsCapacityForCountry(String countryName,Map<String,List<String>> selectedOptions,int startDate,int endDate,String type)
	{
		List<String> selectedTerminals=lngDao.getSelectedTerminals(selectedOptions, startDate, endDate, type);			
		List<String> countryTerminals=getCountryTerminals(countryName,selectedTerminals,type);//lngDao.getCountryTerminals(countryName,selectedTerminals,type);		
		Map<String,Map<Integer,Double>> countryterminalsCapacity=calculateCapacitiesByTerminal(countryTerminals,selectedOptions,startDate,endDate,type);	
					
		return countryterminalsCapacity;
		
	}
	private Map<String,Map<Integer,Double>> getTerminalsCapacityForCompany(String companyName,Map<String,List<String>> selectedOptions,int startDate,int endDate,String type)
	{
		List<String> selectedTerminals=lngDao.getSelectedTerminals(selectedOptions, startDate, endDate, type);			
		List<String> companyTerminals=getCompanyTerminals(companyName,selectedTerminals,type);//lngDao.getCompanyTerminals(companyName,selectedTerminals,type);		
		Map<String,Map<Integer,Double>> companyterminalsCapacity=calculateCapacitiesByTerminal(companyTerminals,selectedOptions,startDate,endDate,type);	
					
		return companyterminalsCapacity;
	}
	private Map<String,Map<Integer,Double>> calculateCapacitiesByCompany(List<String> companies,Map<String,List<String>> selectedOptions,int startDate,int endDate,String type)
	{		
		List<String> selectedTerminals=lngDao.getSelectedTerminals(selectedOptions, startDate, endDate, type);
		String unit=getSelectedUnit(selectedOptions);
		Map<String,Double> terminalsYearCapacity=null;
		Map<String,Double> companyStakeForTerminal=null;
		if(type.equalsIgnoreCase(LNG_LIQUEFACTION))
		{				
			if(null==lngCache.getLiqueTerminalsYearCapacity())
			{
				terminalsYearCapacity=lngCache.createTerminalsYearCapacity(LNG_LIQUEFACTION);
				lngCache.setLiqueTerminalsYearCapacity(terminalsYearCapacity);
			}
			else
				terminalsYearCapacity=lngCache.getLiqueTerminalsYearCapacity();
						
			if(null==lngCache.getLiqueCompanyStakeForTerminal())
			{
				companyStakeForTerminal=lngCache.createCompanyStakeForTerminal(LNG_LIQUEFACTION);
				lngCache.setLiqueCompanyStakeForTerminal(companyStakeForTerminal);
			}
			else
				companyStakeForTerminal=lngCache.getLiqueCompanyStakeForTerminal();
		}	
		else
		{				
			if(null==lngCache.getRegasTerminalsYearCapacity())
			{
				terminalsYearCapacity=lngCache.createTerminalsYearCapacity(LNG_REGASIFICATION);
				lngCache.setRegasTerminalsYearCapacity(terminalsYearCapacity);
			}
			else
				terminalsYearCapacity=lngCache.getRegasTerminalsYearCapacity();	
						
			if(null==lngCache.getRegasCompanyStakeForTerminal())
			{
				companyStakeForTerminal=lngCache.createCompanyStakeForTerminal(LNG_REGASIFICATION);
				lngCache.setRegasCompanyStakeForTerminal(companyStakeForTerminal);
			}
			else
				companyStakeForTerminal=lngCache.getRegasCompanyStakeForTerminal();
		}	
				
		Map<String,Map<Integer,Double>> companyMap=new HashMap<String, Map<Integer,Double>>();
		List<Integer> years=getSelectedYears(startDate,endDate);
										
		Map<Integer,Double> yearMap=null;
		for(String company:companies)
		{
				List<String> companyTerminals=getCompanyTerminals(company,selectedTerminals,type);//lngDao.getCompanyTerminals(company,selectedTerminals,type);
				if(companyTerminals.size()>0)
				{
					yearMap=new HashMap<Integer, Double>();
					for(Integer year:years)
					{				
						double soc=0.0;
						for(String terminal:companyTerminals)
						{			
							String key=company.toLowerCase()+UNDERSCORE+terminal.toLowerCase();
							double stake=companyStakeForTerminal.get(key);			
							double capacity=terminalsYearCapacity.get(terminal.toLowerCase()+year)==null?0:terminalsYearCapacity.get(terminal.toLowerCase()+year);																							
							soc=soc+(capacity*(stake/100));							
						}			
						if(null!=unit && unit.equalsIgnoreCase(BCF))
							soc=soc*BCF_UNIT;
						soc=round(soc,2);						
						yearMap.put(year,soc);//Double.valueOf(formatCapacity.format(soc))
					}					
					companyMap.put(company, yearMap);
				}
				
		}
			return companyMap;
	}		
	//This is to calculate capacities by Terminal i.e with name field . 
	private Map<String,Map<Integer,Double>> calculateCapacitiesByTerminal(List<String> terminals,Map<String,List<String>> selectedOptions,int startDate,int endDate,String type)
	{		
		Map<String,Double> terminalsYearCapacity=null;
		if(type.equalsIgnoreCase(LNG_LIQUEFACTION))
		{				
			if(null==lngCache.getLiqueTerminalsYearCapacity())
			{
				terminalsYearCapacity=lngCache.createTerminalsYearCapacity(LNG_LIQUEFACTION);
				lngCache.setLiqueTerminalsYearCapacity(terminalsYearCapacity);
			}
			else
				terminalsYearCapacity=lngCache.getLiqueTerminalsYearCapacity();							
		}	
		else
		{				
			if(null==lngCache.getRegasTerminalsYearCapacity())
			{
				terminalsYearCapacity=lngCache.createTerminalsYearCapacity(LNG_REGASIFICATION);
				lngCache.setRegasTerminalsYearCapacity(terminalsYearCapacity);
			}
			else
				terminalsYearCapacity=lngCache.getRegasTerminalsYearCapacity();									
		}	
		String unit=getSelectedUnit(selectedOptions);
		List<Integer> years=getSelectedYears(startDate, endDate);
		Map<Integer,Double> yearMap=null;
		Map<String,Map<Integer,Double>> terminalMap=new HashMap<String, Map<Integer,Double>>();
		for(String terminal:terminals)
		{
			yearMap=new HashMap<Integer, Double>();
			for(Integer year:years)
			{
				double soc=0.0;
				double capacity=terminalsYearCapacity.get(terminal.toLowerCase()+year)==null?0:terminalsYearCapacity.get(terminal.toLowerCase()+year);
				soc=soc+capacity;				
				if(null!=unit && unit.equalsIgnoreCase(BCF))
					soc=soc*BCF_UNIT;
				soc=round(soc,2);
				yearMap.put(year,soc);
			}
			terminalMap.put(terminal,yearMap);
		}
		return terminalMap;
	
	}
	// This is to calculate capacities by Country i.e with country field .
	private Map<String,Map<Integer,Double>> calculateCapacitiesByCountry(List<String> countries,Map<String,List<String>> selectedOptions,int startDate,int endDate,String type)
	{		
		List<String> selectedTerminals=lngDao.getSelectedTerminals(selectedOptions, startDate, endDate, type);
		
		Map<String,Double> terminalsYearCapacity=null;
		if(type.equalsIgnoreCase(LNG_LIQUEFACTION))
		{				
			if(null==lngCache.getLiqueTerminalsYearCapacity())
			{
				terminalsYearCapacity=lngCache.createTerminalsYearCapacity(LNG_LIQUEFACTION);
				lngCache.setLiqueTerminalsYearCapacity(terminalsYearCapacity);
			}
			else
				terminalsYearCapacity=lngCache.getLiqueTerminalsYearCapacity();								
		}	
		else
		{				
			if(null==lngCache.getRegasTerminalsYearCapacity())
			{
				terminalsYearCapacity=lngCache.createTerminalsYearCapacity(LNG_REGASIFICATION);
				lngCache.setRegasTerminalsYearCapacity(terminalsYearCapacity);
			}
			else
				terminalsYearCapacity=lngCache.getRegasTerminalsYearCapacity();									
		}					
		String unit=getSelectedUnit(selectedOptions);
		List<Integer> years=getSelectedYears(startDate, endDate);
		Map<Integer,Double> yearMap=null;
		Map<String,Map<Integer,Double>> countryMap=new HashMap<String, Map<Integer,Double>>();		
		for(String country:countries)
		{
			List<String> countryTerminals=getCountryTerminals(country, selectedTerminals, type);//lngDao.getCountryTerminals(country, selectedTerminals, type);
			if(countryTerminals.size()>0)
			{
				yearMap=new HashMap<Integer, Double>();
				for(Integer year:years)
				{
					double soc=0.0;
					for(String terminal:countryTerminals)
					{
						double capacity=terminalsYearCapacity.get(terminal.toLowerCase()+year)==null?0:terminalsYearCapacity.get(terminal.toLowerCase()+year);
						soc=soc+capacity;
						
					}
					if(null!=unit && unit.equalsIgnoreCase(BCF))
						soc=soc*BCF_UNIT;
					soc=round(soc,2);
					yearMap.put(year,soc);
				}
				countryMap.put(country,yearMap);
			}
			
		}
		return countryMap;
	}
	private String getSelectedUnit(Map<String,List<String>> selectedOptions)
	{
		List<String> units=selectedOptions.get(OPTION_SELECTED_UNITS);
		String unit=null;
		if(null!=units && units.size()>0)
			unit=units.get(0);
		return unit;
		
	}
	private List<String> getCompanyTerminals(String company,List<String> selectedTerminals,String type)
	{
		List<String> companyTerminals=new ArrayList<String>();
		List<String> terminalsList=null;		
		if(type.equalsIgnoreCase(LNG_LIQUEFACTION))
		{				
			if(null==lngCache.getLiqueCompanyTerminals())
			{
				Map<String,List<String>> companyTerminalsMap=lngCache.createCompanyTerminals(LNG_LIQUEFACTION);
				lngCache.setLiqueCompanyTerminals(companyTerminalsMap);
				terminalsList=companyTerminalsMap.get(company.toLowerCase());
			}
			else
				terminalsList=lngCache.getLiqueCompanyTerminals().get(company.toLowerCase());				
		}	
		else
		{			
			if(null==lngCache.getRegasCompanyTerminals())
			{
				Map<String,List<String>> companyTerminalsMap=lngCache.createCompanyTerminals(LNG_REGASIFICATION);
				lngCache.setRegasCompanyTerminals(companyTerminalsMap);
				terminalsList=companyTerminalsMap.get(company.toLowerCase());
			}
			else
				terminalsList=lngCache.getRegasCompanyTerminals().get(company.toLowerCase());	
		}
			
		for(String terminal:selectedTerminals)
		{
			if(terminalsList.contains(terminal))
				companyTerminals.add(terminal);
		}
		return companyTerminals;
	}
	private List<String> getCountryTerminals(String country,List<String> selectedTerminals,String type)
	{
		List<String> countryTerminals=new ArrayList<String>();
		List<String> terminalsList=null;
		if(type.equalsIgnoreCase(LNG_LIQUEFACTION))
		{			
			if(null==lngCache.getLiqueCountryTerminals())
			{
				Map<String,List<String>> countryTerminalsMap=lngCache.createCountryTerminals(LNG_LIQUEFACTION);
				lngCache.setLiqueCountryTerminals(countryTerminalsMap);
				terminalsList=countryTerminalsMap.get(country.toLowerCase());
			}
			else
				terminalsList=lngCache.getLiqueCountryTerminals().get(country.toLowerCase());
		}
		else
		{			
			if(null==lngCache.getRegasCountryTerminals())
			{
				Map<String,List<String>> countryTerminalsMap=lngCache.createCountryTerminals(LNG_REGASIFICATION);
				lngCache.setRegasCountryTerminals(countryTerminalsMap);
				terminalsList=countryTerminalsMap.get(country.toLowerCase());
			}
			else
				terminalsList=lngCache.getRegasCountryTerminals().get(country.toLowerCase());
		}
			
		for(String terminal:selectedTerminals)
		{
			if(terminalsList.contains(terminal))
				countryTerminals.add(terminal);
		}
		return countryTerminals;
	}
	private List<Integer> getSelectedYears(int startDate,int endDate)
	{
		List<Integer> years=new ArrayList<Integer>();
		for(int i=startDate;i<=endDate;i++)
			years.add(i);
		return years;
	}	
	@Override
	public Map<String, String> getTerminalData(String recordName,String type) {
		// TODO Auto-generated method stub		
		List<Lng> regasificationTerminalList=lngDao.getTerminalData(recordName, type);
		Map terminalData=new HashMap();
		Lng lng=regasificationTerminalList.get(0);
		terminalData.put(TERMINALNAME,lng.getName());
		terminalData.put(TYPE, lng.getType());
		terminalData.put(STATUS,lng.getStatus());
		terminalData.put(LOCATION,lng.getArea());
		terminalData.put(COUNTRY,lng.getCountry());
		terminalData.put(ONSHORE_OR_OFFSHORE,lng.getOnshoreOrOffshore());
		terminalData.put(EXPECTEDSTARTUP, lng.getExpectedStartYear()!=null?lng.getExpectedStartYear().toString():BLANK);
		if(null!=lng.getScheduledStartYear() && !BLANK.equals(lng.getScheduledStartYear()))
			terminalData.put(SCHEDULEDSTARTUP,lng.getScheduledStartYear().toString());
		else
			terminalData.put(SCHEDULEDSTARTUP,BLANK);
		
		terminalData.put(PROCESSINGCAPACITY, getProcessingCapacity(regasificationTerminalList));
		terminalData.put(TRAINSORVAPORIZERS,getTrainsOrVaporizers(regasificationTerminalList));
		terminalData.put(STORAGECAPACITY,getStorageCapacity(regasificationTerminalList));
		terminalData.put(STORAGETANKS, getStorageTanks(regasificationTerminalList));
		
		terminalData.put(OPERATOR,getOperator(regasificationTerminalList).toString());
		terminalData.put(OWNERSHIP,getOwnership(recordName,type));

		terminalData.put(CAPEX, String.valueOf(getCapexDetails(regasificationTerminalList)));
		terminalData.put(TECHNOLOGY,getTechnologyDetails(regasificationTerminalList).toString());
		terminalData.put(CONSTRUCTIONPERIOD,getConstructionPeriod(regasificationTerminalList));
		terminalData.put(CONSTRUCTIONDETAILS,getConstructionDetails(regasificationTerminalList));
		
		terminalData.put(SOURCEFIELDS,getSourceFields(regasificationTerminalList).toString());
		terminalData.put(DISTRIBUTIONTYPE,getDistributionType(regasificationTerminalList).toString());
		return terminalData;
	}
	private List<Map<String,String>> getOwnership(String recordName,String type)
	{
			
		List<Map<String,String>> ownershipList=new ArrayList<Map<String,String>>();
		List<LngFilter> lngFilterList=lngDao.getTerminalCompanies(recordName,type);
//		Map<String,Double> companyStakes=null;
//		if(type.equalsIgnoreCase(LNG_LIQUEFACTION))
//			companyStakes=lngCache.getLiqueCompanyStakeForTerminal();
//		else
//			companyStakes=lngCache.getRegasCompanyStakeForTerminal();
		Map<String,String> ownershipMap=null;new HashMap<String, String>();
		for(LngFilter lngFilter:lngFilterList)
		{				
				ownershipMap=new HashMap<String, String>();
//				String key=lngFilter.getEquityPartners().toLowerCase()+UNDERSCORE+lngFilter.getName().toLowerCase();			
				ownershipMap.put(EQUITYPARTNER,lngFilter.getEquityPartners());
				ownershipMap.put(EQUITYSTAKE,String.valueOf(lngFilter.getEquityStakes()));//String.valueOf(companyStakes.get(key))
				ownershipList.add(ownershipMap);
		}
		
		return ownershipList;
	}
	private StringBuffer getDistributionType(List<Lng> dataList)
	{
		StringBuffer distributionType=new StringBuffer();
		for(Lng lng:dataList)
		{
			if(null!=lng.getDisttributionOrOutputName() && !BLANK.equalsIgnoreCase(lng.getDisttributionOrOutputName()))
				distributionType.append(lng.getDisttributionOrOutputName()).append(COMMA);
		}
		if(distributionType.length()>0)
		removeCommaAtEnd(distributionType);
		return distributionType;
	}
	private StringBuffer getSourceFields(List<Lng> dataList)
	{
		StringBuffer sourceFields=new StringBuffer();
		for(int i=0;i<dataList.size();i++)
		{
			Lng lng=dataList.get(i);
			if(null!=lng.getFeedOrInputName() && !BLANK.equalsIgnoreCase(lng.getFeedOrInputName()))			
				sourceFields.append(lng.getFeedOrInputName()).append(COMMA);				
							
		}
		if(sourceFields.length()>0)
		removeCommaAtEnd(sourceFields);
		return sourceFields;
	}
	private void removeCommaAtEnd(StringBuffer inputString)
	{
		inputString.deleteCharAt(inputString.length()-1);
	}
	private StringBuffer getOperator(List<Lng> dataList)
	{
		StringBuffer operators=new StringBuffer();
		for(int i=0;i<dataList.size();i++)
		{
			Lng lng=dataList.get(i);
			if(null!=lng.getOperator() && !BLANK.equalsIgnoreCase(lng.getOperator()))				
				operators.append(lng.getOperator()).append(COMMA);											
		}
		if(operators.length()>0)
		removeCommaAtEnd(operators);
		return operators;
	}
	private Map<Integer,Double> getProcessingCapacity(List<Lng> dataList)
	{
		Map<Integer,Double> processingCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Lng lng:dataList)
			{
				if(i!=0 && lng.getCapacityYear()==i)
				{
					processingCapacity.put(i, lng.getCapacity());
					
				}
			}
		}
		return processingCapacity;
	}
	private Map<Integer,Double> getTrainsOrVaporizers(List<Lng> dataList)
	{
		Map<Integer,Double> trainsOrVaporizers=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Lng lng:dataList)
			{
				if(i!=0 && lng.getCapacityYear()==i)
				{
					trainsOrVaporizers.put(i, lng.getNumberOfTrainsOrNumberOfVaporizers());
					
				}
			}
		}
		return trainsOrVaporizers;
	}
	private Map<Integer,Double> getStorageCapacity(List<Lng> dataList)
	{
		Map<Integer,Double> storageCapacity=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Lng lng:dataList)
			{
				if(i!=0 && lng.getCapacityYear()==i)
				{
					storageCapacity.put(i, lng.getTotalLngStorageCapacity());
					
				}
			}
		}
		return storageCapacity;
	}
	private Map<Integer,Double> getStorageTanks(List<Lng> dataList)
	{
		Map<Integer,Double> storageTanks=new HashMap<Integer,Double>();
		List<Integer> years=getSelectedYears(STARTYEAR,ENDYEAR);
		for(Integer i:years)
		{
			for(Lng lng:dataList)
			{
				if(i!=0 && lng.getCapacityYear()==i)
				{
					storageTanks.put(i, lng.getNumberOfStorageTanks());
					
				}
			}
		}
		return storageTanks;
	}
	private List<Map<String,Integer>> getConstructionPeriod(List<Lng> dataList)
	{
		List<Map<String,Integer>> constructionPeriod=new ArrayList<Map<String,Integer>>();
		Map<String,Integer> constructionPeriodMap=null;
		for(Lng lng:dataList)
		{
			constructionPeriodMap=new HashMap<String, Integer>();
			constructionPeriodMap.put(CONSTRUCTIONSTART,lng.getConstructionStart());
			constructionPeriodMap.put(CONSTRUCTIONEND,lng.getConstructionEnd());
			constructionPeriod.add(constructionPeriodMap);
		}
		return constructionPeriod;
	}
	private List<Map<String,String>> getConstructionDetails(List<Lng> dataList)
	{
		List<Map<String,String>> constructionDetails=new ArrayList<Map<String,String>>();
		Map<String,String> constructionDetailsMap=null;
		for(Lng lng:dataList)
		{
			constructionDetailsMap=new HashMap<String, String>();
			if(null!=lng.getConstructionCompanyName() && !BLANK.equalsIgnoreCase(lng.getConstructionCompanyName()))			
				constructionDetailsMap.put(CONSTRUCTIONCOMPANYNAME,lng.getConstructionCompanyName());			
			else
				constructionDetailsMap.put(CONSTRUCTIONCOMPANYNAME, BLANK);
			
			if(null!=lng.getConstructionContractDetails() && !BLANK.equalsIgnoreCase(lng.getConstructionContractDetails()))			
				constructionDetailsMap.put(CONSTRUCTIONCONTRACTDETAILS,lng.getConstructionContractDetails());			
			else
				constructionDetailsMap.put(CONSTRUCTIONCONTRACTDETAILS, BLANK);
			constructionDetails.add(constructionDetailsMap);
		}
		return constructionDetails;
			
	}
	private StringBuffer getTechnologyDetails(List<Lng> dataList)
	{
		StringBuffer technology=new StringBuffer();
		for(Lng lng:dataList)
		{	
			if(null!=lng.getTechnologyDetails() && !BLANK.equalsIgnoreCase(lng.getTechnologyDetails()))
			technology.append(lng.getTechnologyDetails()).append(COMMA);
		}	
		if(technology.length()>0)
		removeCommaAtEnd(technology);
		return technology;
	}
	private double getCapexDetails(List<Lng> dataList)
	{
		double totalCapex=0;
		for(Lng lng:dataList)
			totalCapex=totalCapex+lng.getCapex();
		return totalCapex;
	}
	private double round(double value, int places) {	    

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	public LngDao getLngDao() {
		return lngDao;
	}
	public void setLngDao(LngDao lngDao) {
		this.lngDao = lngDao;
	}
	public LngCache getLngCache() {
		return lngCache;
	}
	public void setLngCache(LngCache lngCache) {
		this.lngCache = lngCache;
	}
	
}
