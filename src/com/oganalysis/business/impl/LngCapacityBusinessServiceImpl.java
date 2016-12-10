package com.oganalysis.business.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oganalysis.business.LngCapacityBusinessService;
import com.oganalysis.dao.LngDao;
import com.oganalysis.entities.Lng;

public class LngCapacityBusinessServiceImpl implements LngCapacityBusinessService {

	private LngDao lngDao;
	private static final String REGASIFICATION="Regasification";
	private static final String LIQUEFACTION="Liquefaction";
	
	@Override
	public Map<String,Map<Integer,Double>> getRegasificationCapacityByCountry(Map<String,List>selectedOptions,String startDate,String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		List<Lng> regasificationList=lngDao.getRegasificationCriteriaData(selectedOptions,startDateVal,endDateVal);		
				
		Map<String,Map<Integer,Double>> regasification=calculateCapacitiesByCountry(regasificationList);					
		
		return regasification;
	}
	@Override
	public Map<String,Map<Integer,Double>> getLiquefactionCapacityByCountry(Map<String,List>selectedOptions,String startDate,String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
			
		List<Lng> liquefactionList=lngDao.getLiquefactionCriteriaData(selectedOptions, startDateVal, endDateVal);			
		Map<String,Map<Integer,Double>> liquefaction=calculateCapacitiesByCountry(liquefactionList);
		
		return liquefaction;
	}
	@Override
	public Map<String, Map<Integer, Double>> getRegasificationCapacityByTerminal(
			Map<String, List> selectedOptions, String startDate, String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		List<Lng> regasificationList=lngDao.getRegasificationCriteriaData(selectedOptions,startDateVal,endDateVal);		
				
		Map<String,Map<Integer,Double>> regasification=calculateCapacitiesByTerminal(regasificationList);						
		
		return regasification;
	}
	@Override
	public Map<String, Map<Integer, Double>> getLiquefactionCapacityByTerminal(
			Map<String, List> selectedOptions, String startDate, String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
			
		List<Lng> liquefactionList=lngDao.getLiquefactionCriteriaData(selectedOptions, startDateVal, endDateVal);			
		Map<String,Map<Integer,Double>> liquefaction=calculateCapacitiesByTerminal(liquefactionList);
	
		return liquefaction;
	}
	@Override
	public Map<String, Map<Integer, Double>> getRegasificationCapacityByCompany(
			Map<String, List> selectedOptions, String startDate, String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		
		List<Lng> regasificationList=lngDao.getRegasificationCriteriaData(selectedOptions,startDateVal,endDateVal);		
		
		Map<String,Map<Integer,Double>> regasification=calculateRegasificationCapacitiesByCompany(regasificationList);						
		
		return regasification;
	}
	@Override
	public Map<String, Map<Integer, Double>> getLiquefactionCapacityByCompany(
			Map<String, List> selectedOptions, String startDate, String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
			
		List<Lng> liquefactionList=lngDao.getLiquefactionCriteriaData(selectedOptions, startDateVal, endDateVal);			
		Map<String,Map<Integer,Double>> liquefaction=calculateLiquefactionCapacitiesByCompany(liquefactionList);
		
		return liquefaction;
	}
	/*
	 * (non-Javadoc)
	 * @see com.oganalysis.business.LngCapacityBusinessService#getLiqueModalCapacityForRecord(java.util.Map, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * Modal data for a record of type Liquefaction
	 */
	@Override
	public Map<String, Map<Integer, Double>> getLiqueModalCapacityForRecord(
			Map<String, List> selectedOptions, String startDate,
			String endDate, String displayType, String recordName) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
			Map<String,Map<Integer,Double>> terminalCapacities=new HashMap<String, Map<Integer,Double>>();
		List<Lng> liquefactionList=lngDao.getLiquefactionCriteriaData(selectedOptions, startDateVal, endDateVal);
		if(null!=displayType && displayType.equalsIgnoreCase("country"))
			terminalCapacities=getTerminalsCapacityForCountry(liquefactionList,recordName);
		else if(null!=displayType && displayType.equalsIgnoreCase("company"))
			terminalCapacities=getTerminalsCapacityForCompany(liquefactionList,recordName,LIQUEFACTION);
		
		return terminalCapacities;
	}
	/*
	 * (non-Javadoc)
	 * @see com.oganalysis.business.LngCapacityBusinessService#getRegasModalCapacityForRecord(java.util.Map, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * Modal data for a record of type Regasifiation
	 */
	@Override
	public Map<String, Map<Integer, Double>> getRegasModalCapacityForRecord(
			Map<String, List> selectedOptions, String startDate,
			String endDate, String displayType, String recordName) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
		
		Map<String,Map<Integer,Double>> terminalCapacities=new HashMap<String, Map<Integer,Double>>();
		List<Lng> regasificationList=lngDao.getRegasificationCriteriaData(selectedOptions, startDateVal, endDateVal);
		if(null!=displayType && displayType.equalsIgnoreCase("country"))
			terminalCapacities=getTerminalsCapacityForCountry(regasificationList,recordName);
		else if(null!=displayType && displayType.equalsIgnoreCase("company"))
			terminalCapacities=getTerminalsCapacityForCompany(regasificationList,recordName,REGASIFICATION);
		
		return terminalCapacities;
	}
	private Map<String,Map<Integer,Double>> getTerminalsCapacityForCountry(List<Lng> lngList,String recordName)
	{
		Map<String,Map<Integer,Double>> countryTerminalsCapacity=new HashMap<String, Map<Integer,Double>>();
		Set<Integer> years=getYears(lngList);
		Map<String,Set<String>> countryTerminals=getCountryTerminals(lngList);
		Set<String> terminals=countryTerminals.get(recordName);
		Map<Integer,Double> yearMap=null;
		if(null!=terminals)
		{
			for(String terminal:terminals)
			{
					yearMap=new HashMap<Integer, Double>();
					for(Integer year:years)
					{
						double soc=0.0;
						for(Lng lng:lngList)
						{
							if(terminal.equalsIgnoreCase(lng.getName()) && year==lng.getCapacityYear())
								soc=soc+lng.getCapacity();
						}
						yearMap.put(year,soc);
					}
					countryTerminalsCapacity.put(terminal,yearMap);
			}			
		}			
		return countryTerminalsCapacity;
		
	}
	private Map<String,Map<Integer,Double>> getTerminalsCapacityForCompany(List<Lng> lngList,String recordName,String type)
	{
		Map<String,Map<Integer,Double>> companyTerminalsCapacity=new HashMap<String, Map<Integer,Double>>();
		Map<String,Set<String>> companyTerminals=null;//getCompanyTerminals(lngList);
		Set<String> terminalsSet=getTerminals(lngList);
		if(null!=type && type.equalsIgnoreCase(LIQUEFACTION))
		{
			List<Lng> liquefactionList=lngDao.getLiquefactionData();
			companyTerminals=getCompanyTerminals(liquefactionList,terminalsSet);// here we need to specific type list
		}
		else if(null!=type && type.equalsIgnoreCase(REGASIFICATION))
		{
			List<Lng> regasificationList=lngDao.getRegasificationData();
			companyTerminals=getCompanyTerminals(regasificationList,terminalsSet);// here we need to specific type list
		}
		Set<String> terminals=companyTerminals.get(recordName);
		Set<Integer> years=getYears(lngList);
		Map<Integer,Double> yearMap=null;
		if(null!=terminals)
		{
			for(String terminal:terminals)
			{
				yearMap=new HashMap<Integer, Double>();
				for(Integer year:years)
				{
					double soc=0.0;
					for(Lng lng:lngList)
					{
						if(terminal.equalsIgnoreCase(lng.getName()) && year==lng.getCapacityYear())
							soc=soc+lng.getCapacity();
					}
					yearMap.put(year,soc);
				}
				companyTerminalsCapacity.put(terminal,yearMap);
			}		
		}
		
		return companyTerminalsCapacity;
	}
	private Map<String,Map<Integer,Double>> calculateRegasificationCapacitiesByCompany(List<Lng> lngList)
	{
		/*
		 * Each record doesn't have company details. So we calculated capacities for Regasification separately
		 */
		List<Lng> regasificationList=lngDao.getRegasificationData();
		Set<String> terminalsSet=getTerminals(lngList);
		Set<String> companies=getCompanies(regasificationList,terminalsSet);
		Map<String,Double> companyStakeForTerminal=getCompanyStakeForTerminal(regasificationList);
		
		Map<String,Set<String>> companyTerminals=getCompanyTerminals(regasificationList,terminalsSet);
		Map<Integer,Double> yearMap=null;
		
		Set<Integer> years=getYears(lngList);
		
		Map<String,Map<Integer,Double>> companyMap=new HashMap<String, Map<Integer,Double>>();
		DecimalFormat df=new DecimalFormat(".#");
		for(String company:companies)
		{
			Set<String> terminals=companyTerminals.get(company);
			yearMap=new HashMap<Integer, Double>();
			for(Integer year:years)
			{				
				double soc=0.0;
				for(String terminal:terminals)
				{			
					String key=company+"_"+terminal;
					double stake=companyStakeForTerminal.get(key);			
										
					for(Lng lng:lngList)
					{
//						System.out.println(lng.getName()+" :"+lng.getCapacityYear());
						if(terminal.equalsIgnoreCase(lng.getName()) && year==lng.getCapacityYear())
							soc=soc+(lng.getCapacity()*(stake/100));
					}					
				}
				yearMap.put(year,Double.valueOf(df.format(soc)));
			}
			companyMap.put(company, yearMap);
		}
		return companyMap;
	}
	private Map<String,Map<Integer,Double>> calculateLiquefactionCapacitiesByCompany(List<Lng> lngList)
	{
		/*
		 * Each record doesn't have company details. So we calculated capacities for Liquefaction separately
		 */
		List<Lng> liquefactionList=lngDao.getLiquefactionData();
		Set<String> terminalsSet=getTerminals(lngList);
		Set<String> companies=getCompanies(liquefactionList,terminalsSet);
		Map<String,Double> companyStakeForTerminal=getCompanyStakeForTerminal(liquefactionList);
		
		Map<String,Set<String>> companyTerminals=getCompanyTerminals(liquefactionList,terminalsSet);
		Map<Integer,Double> yearMap=null;
		
		Set<Integer> years=getYears(lngList);
		
		Map<String,Map<Integer,Double>> companyMap=new HashMap<String, Map<Integer,Double>>();
//		DecimalFormat df=new DecimalFormat(".#");
		for(String company:companies)
		{
			Set<String> terminals=companyTerminals.get(company);
			yearMap=new HashMap<Integer, Double>();
			for(Integer year:years)
			{				
				double soc=0.0;
				for(String terminal:terminals)
				{			
					String key=company+"_"+terminal;
					double stake=companyStakeForTerminal.get(key);			
										
					for(Lng lng:lngList)
					{
//						System.out.println(lng.getName()+" :"+lng.getCapacityYear());
						if(terminal.equalsIgnoreCase(lng.getName()) && year==lng.getCapacityYear())
							soc=soc+(lng.getCapacity()*(stake/100));
					}					
				}
				yearMap.put(year,soc);
			}
			companyMap.put(company, yearMap);
		}
		return companyMap;
		
	}

	//This is to calculate capacities by Terminal i.e with name field . Below method can also be used to display the Modal data for a country
	private Map<String,Map<Integer,Double>> calculateCapacitiesByTerminal(List<Lng> lngList)
	{
		Set<String> terminals=getTerminals(lngList);
		Set<Integer> years=getYears(lngList);
		Map<Integer,Double> yearMap=null;
		Map<String,Map<Integer,Double>> terminalMap=new HashMap<String, Map<Integer,Double>>();
		for(String terminal:terminals)
		{
			yearMap=new HashMap<Integer, Double>();
			for(Integer year:years)
			{
				double soc=0.0;
				for(Lng lng:lngList)
				{
					if(terminal.equalsIgnoreCase(lng.getName()) && year==lng.getCapacityYear())
						soc=soc+lng.getCapacity();
				}
				yearMap.put(year,soc);
			}
			terminalMap.put(terminal,yearMap);
		}
		return terminalMap;
	
	}
	// This is to calculate capacities by Country i.e with country field .
	private Map<String,Map<Integer,Double>> calculateCapacitiesByCountry(List<Lng> lngList)
	{
		Set<String> countries=getCountries(lngList);
		Set<Integer> years=getYears(lngList);
		Map<Integer,Double> yearMap=null;
		Map<String,Map<Integer,Double>> countryMap=new HashMap<String, Map<Integer,Double>>();
		for(String country:countries)
		{
			yearMap=new HashMap<Integer, Double>();
			for(Integer year:years)
			{
				double soc=0.0;
				for(Lng lng:lngList)
				{
					if(country.equalsIgnoreCase(lng.getCountry()) && year==lng.getCapacityYear())
						soc=soc+lng.getCapacity();
				}
				yearMap.put(year,soc);
			}
			countryMap.put(country,yearMap);
		}
		return countryMap;
	}
	@Override
	public Map getLiqueModalTerminalData(String recordName) {
		// TODO Auto-generated method stub
				
		List<Lng> liquefactionTerminalList=lngDao.getTerminalData(recordName, LIQUEFACTION);
		Map terminalData=new HashMap();
		Lng lng=liquefactionTerminalList.get(0);
		terminalData.put("terminalName",lng.getName());
		terminalData.put("type", lng.getType());
		terminalData.put("status",lng.getStatus());
		terminalData.put("location",lng.getArea());
		terminalData.put("country",lng.getCountry());
		terminalData.put("onshoreoroffshore",lng.getOnshoreOrOffshore());
		terminalData.put("expectedStartUp", lng.getExpectedStartYear().toString());
		if(null!=lng.getScheduledStartYear() && !"".equals(lng.getScheduledStartYear()))
			terminalData.put("scheduledStartUp",lng.getScheduledStartYear().toString());
		else
			terminalData.put("scheduledStartUp","");
		
		terminalData.put("processingCapacity", getProcessingCapacity(liquefactionTerminalList));
		terminalData.put("trainsOrVaporizers",getTrainsOrVaporizers(liquefactionTerminalList));
		terminalData.put("storageCapacity",getStorageCapacity(liquefactionTerminalList));
		terminalData.put("storageTanks", getStorageTanks(liquefactionTerminalList));
		
		terminalData.put("operator",getOperator(liquefactionTerminalList).toString());
		terminalData.put("ownership",getOwnership(liquefactionTerminalList));

		terminalData.put("capex", String.valueOf(getCapexDetails(liquefactionTerminalList)));
		terminalData.put("technology",getTechnologyDetails(liquefactionTerminalList).toString());
		terminalData.put("constructionPeriod",getConstructionPeriod(liquefactionTerminalList));
		terminalData.put("constructionDetails",getConstructionDetails(liquefactionTerminalList));
		
		terminalData.put("sourceFields",getSourceFields(liquefactionTerminalList).toString());
		terminalData.put("distributionType",getDistributionType(liquefactionTerminalList).toString());
		return terminalData;
	}
	@Override
	public Map<String, String> getRegasModalTerminalData(String recordName) {
		// TODO Auto-generated method stub
		
		List<Lng> regasificationTerminalList=lngDao.getTerminalData(recordName, REGASIFICATION);
		Map terminalData=new HashMap();
		Lng lng=regasificationTerminalList.get(0);
		terminalData.put("terminalName",lng.getName());
		terminalData.put("type", lng.getType());
		terminalData.put("status",lng.getStatus());
		terminalData.put("location",lng.getArea());
		terminalData.put("country",lng.getCountry());
		terminalData.put("onshoreoroffshore",lng.getOnshoreOrOffshore());
		terminalData.put("expectedStartUp", lng.getExpectedStartYear().toString());
		if(null!=lng.getScheduledStartYear() && !"".equals(lng.getScheduledStartYear()))
			terminalData.put("scheduledStartUp",lng.getScheduledStartYear().toString());
		else
			terminalData.put("scheduledStartUp","");
		
		terminalData.put("processingCapacity", getProcessingCapacity(regasificationTerminalList));
		terminalData.put("trainsOrVaporizers",getTrainsOrVaporizers(regasificationTerminalList));
		terminalData.put("storageCapacity",getStorageCapacity(regasificationTerminalList));
		terminalData.put("storageTanks", getStorageTanks(regasificationTerminalList));
		
		terminalData.put("operator",getOperator(regasificationTerminalList).toString());
		terminalData.put("ownership",getOwnership(regasificationTerminalList));

		terminalData.put("capex", String.valueOf(getCapexDetails(regasificationTerminalList)));
		terminalData.put("technology",getTechnologyDetails(regasificationTerminalList).toString());
		terminalData.put("constructionPeriod",getConstructionPeriod(regasificationTerminalList));
		terminalData.put("constructionDetails",getConstructionDetails(regasificationTerminalList));
		
		terminalData.put("sourceFields",getSourceFields(regasificationTerminalList).toString());
		terminalData.put("distributionType",getDistributionType(regasificationTerminalList).toString());
		return terminalData;
	}
	private List<Map<String,String>> getOwnership(List<Lng> dataList)
	{
		List<Map<String,String>> ownershipList=new ArrayList<Map<String,String>>();
		Map<String,String> ownershipMap=null;
		Set<String> terminals=getTerminals(dataList);
		Set<String> companies=getCompanies(dataList, terminals);
		Map<String,Double> companyStakes=getCompanyStakeForTerminal(dataList);
		for(String terminal:terminals)
		{
			for(String company:companies)
			{
				String key=company+"_"+terminal;
				ownershipMap=new HashMap<String, String>();
				ownershipMap.put("equityPartner",company);
				ownershipMap.put("equityStake",String.valueOf(companyStakes.get(key)));
				ownershipList.add(ownershipMap);
			}
		}
		return ownershipList;
	}
	private StringBuffer getDistributionType(List<Lng> dataList)
	{
		StringBuffer distributionType=new StringBuffer();
		for(Lng lng:dataList)
		{
			if(null!=lng.getDisttributionOrOutputName() && !"".equalsIgnoreCase(lng.getDisttributionOrOutputName()))
				distributionType.append(lng.getDisttributionOrOutputName()).append(",");
		}
		return distributionType;
	}
	private StringBuffer getSourceFields(List<Lng> dataList)
	{
		StringBuffer sourceFields=new StringBuffer();
		for(Lng lng:dataList)
		{
			if(null!=lng.getFeedOrInputName() && !"".equalsIgnoreCase(lng.getFeedOrInputName()))
				sourceFields.append(lng.getFeedOrInputName()).append(",");
		}
		return sourceFields;
	}
	private StringBuffer getOperator(List<Lng> dataList)
	{
		StringBuffer operators=new StringBuffer();
		for(Lng lng:dataList)
		{
			if(null!=lng.getOperator() && !"".equalsIgnoreCase(lng.getOperator()))
				operators.append(lng.getOperator()).append(",");
		}
		return operators;
	}
	private Map<Integer,Double> getProcessingCapacity(List<Lng> dataList)
	{
		Map<Integer,Double> processingCapacity=new HashMap<Integer,Double>();
		Set<Integer> years=getYears(dataList);
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
		Set<Integer> years=getYears(dataList);
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
		Set<Integer> years=getYears(dataList);
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
		Set<Integer> years=getYears(dataList);
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
			constructionPeriodMap.put("constructionStart",lng.getConstructionStart());
			constructionPeriodMap.put("constructionEnd",lng.getConstructionEnd());
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
			if(null!=lng.getConstructionCompanyName() && !"".equalsIgnoreCase(lng.getConstructionCompanyName()))			
				constructionDetailsMap.put("constructionCompanyName",lng.getConstructionCompanyName());			
			else
				constructionDetailsMap.put("constructionCompanyName", "");
			
			if(null!=lng.getConstructionContractDetails() && !"".equalsIgnoreCase(lng.getConstructionContractDetails()))			
				constructionDetailsMap.put("constructionContractDetails",lng.getConstructionContractDetails());			
			else
				constructionDetailsMap.put("constructionContractDetails", "");
			constructionDetails.add(constructionDetailsMap);
		}
		return constructionDetails;
			
	}
	private StringBuffer getTechnologyDetails(List<Lng> dataList)
	{
		StringBuffer technology=new StringBuffer();
		for(Lng lng:dataList)
		{	
			if(null!=lng.getTechnologyDetails() && !"".equalsIgnoreCase(lng.getTechnologyDetails()))
			technology.append(lng.getTechnologyDetails()).append(",");
		}	
		return technology;
	}
	private double getCapexDetails(List<Lng> dataList)
	{
		double totalCapex=0;
		for(Lng lng:dataList)
			totalCapex=totalCapex+lng.getCapex();
		return totalCapex;
	}
	
	private Set<String> getTerminals(List<Lng> lngList)
	{
		Set<String> terminals=new HashSet<String>();
		for(Lng lng:lngList)
		{
			terminals.add(lng.getName());
		}
		return terminals;
	}
	private Set<String> getCountries(List<Lng> lngList)
	{
		Set<String> countries=new HashSet<String>();
		for(Lng lng:lngList)
		{			
			countries.add(lng.getCountry());
		}
		return countries;
	}
	private Set<Integer> getYears(List<Lng> lngList)
	{
		Set<Integer> years=new HashSet<Integer>();
		for(Lng lng:lngList)
		{
			years.add(lng.getCapacityYear());			
		}
		return years;
	}
	
	private Map<String,Double> getCompanyStakeForTerminal(List<Lng> lngList)
	{
		Map<String,Double> companyStakeForTerminal=new HashMap<String, Double>();
//		List<Lng> dataList=null;
//		if(lngList==null)
//			dataList=lngDao.getLngData();
//		else
//			dataList=lngList;
		
		for(Lng lng:lngList)
		{
			if(null!=lng.getEquityPartners() && !("").equalsIgnoreCase(lng.getEquityPartners()))
			{
				String company=lng.getEquityPartners();
				String terminalName=lng.getName();
				String key=company+"_"+terminalName;
				companyStakeForTerminal.put(key,lng.getEquityStakes());
			}
		}
		return companyStakeForTerminal;
	}
	private Map<String,Set<String>> getCompanyTerminals(List<Lng> lngList,Set<String> terminalsSet)
	{
		Set<String> companies=getCompanies(lngList,terminalsSet);
		Map<String,Set<String>> companyTerminals=new HashMap<String, Set<String>>();		
		for(String company:companies)
		{
			Set<String> terminals=new HashSet<String>();
			for(Lng lng:lngList)
			{
				if(company.equalsIgnoreCase(lng.getEquityPartners()))
						terminals.add(lng.getName());
			}
			companyTerminals.put(company, terminals);
		}
		
		return companyTerminals;
	}
	private Map<String,Set<String>> getCountryTerminals(List<Lng> lngList)
	{
		Set<String> countries=getCountries(lngList);
		Map<String,Set<String>> countryTerminals=new HashMap<String, Set<String>>();		
		for(String country:countries)
		{
			Set<String> terminals=new HashSet<String>();
			for(Lng lng:lngList)
			{
				if(country.equalsIgnoreCase(lng.getCountry()))
						terminals.add(lng.getName());
			}
			countryTerminals.put(country, terminals);
		}
		
		return countryTerminals;
	}
	private Set<String> getCompanies(List<Lng> lngList,Set<String> terminals)
	{
//		List<Lng> dataList=null;
//		if(lngList==null)
//			dataList=lngDao.getLngData();
//		else
//			dataList=lngList;
		Set<String> companiesList=new HashSet<String>();
		for(String terminal:terminals)
		{
			for(Lng lng:lngList)
			{
				if(terminal.equalsIgnoreCase(lng.getName()) && null!=lng.getEquityPartners() && !("").equalsIgnoreCase(lng.getEquityPartners()))
					companiesList.add(lng.getEquityPartners());
			}
		}
		
		return companiesList;
	}
	public LngDao getLngDao() {
		return lngDao;
	}
	public void setLngDao(LngDao lngDao) {
		this.lngDao = lngDao;
	}

	
	
}
