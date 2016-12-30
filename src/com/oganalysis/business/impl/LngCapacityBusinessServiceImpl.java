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
import com.oganalysis.entities.LngFilter;

public class LngCapacityBusinessServiceImpl implements LngCapacityBusinessService {

	private LngDao lngDao;
	public static final String REGASIFICATION="Regasification";
	public static final String LIQUEFACTION="Liquefaction";
	private final DecimalFormat formatCapacity=new DecimalFormat(".#");
	
	@Override
	public Map<String,Map<Integer,Double>> getRegasificationCapacityByCountry(Map<String,List>selectedOptions,String startDate,String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
				
		Map<String,Map<Integer,Double>> regasification=new HashMap<String, Map<Integer,Double>>();
		List<String> countries=lngDao.getSelectedCountries(selectedOptions,startDateVal,endDateVal,REGASIFICATION);
		List<String> terminals=lngDao.getSelectedTerminals(selectedOptions, startDateVal,endDateVal, REGASIFICATION);
		List<Integer> years=lngDao.getSelectedYears(startDateVal,endDateVal,REGASIFICATION);
		if(countries.size()>0 && terminals.size()>0)
		{	
//			List<Lng> regasificationList=lngDao.getRegasificationCriteriaData(startDateVal,endDateVal);						
			regasification=calculateCapacitiesByCountry(countries,terminals,years,REGASIFICATION);
		}	
		
		return regasification;
	}
	@Override
	public Map<String,Map<Integer,Double>> getLiquefactionCapacityByCountry(Map<String,List>selectedOptions,String startDate,String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
		
		Map<String,Map<Integer,Double>> liquefaction=new HashMap<String, Map<Integer,Double>>();		
		List<String> countries=lngDao.getSelectedCountries(selectedOptions,startDateVal,endDateVal,LIQUEFACTION);
		List<String> terminals=lngDao.getSelectedTerminals(selectedOptions, startDateVal,endDateVal, LIQUEFACTION);
		List<Integer> years=lngDao.getSelectedYears(startDateVal,endDateVal,LIQUEFACTION);
		if(countries.size()>0 && terminals.size()>0)
		{
//			List<Lng> liquefactionList=lngDao.getLiquefactionCriteriaData(startDateVal, endDateVal);			
			liquefaction=calculateCapacitiesByCountry(countries,terminals,years,LIQUEFACTION);
		}			
		return liquefaction;
	}
	@Override
	public Map<String, Map<Integer, Double>> getRegasificationCapacityByTerminal(
			Map<String, List> selectedOptions, String startDate, String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		
		Map<String,Map<Integer,Double>> regasification=new HashMap<String, Map<Integer,Double>>();
		List<String> terminals=lngDao.getSelectedTerminals(selectedOptions,startDateVal,endDateVal,REGASIFICATION);
		if(terminals.size()>0)
		{
			List<Lng> regasificationList=lngDao.getRegasificationCriteriaData(startDateVal,endDateVal);						
			regasification=calculateCapacitiesByTerminal(regasificationList,terminals);
		}									
		return regasification;
	}
	@Override
	public Map<String, Map<Integer, Double>> getLiquefactionCapacityByTerminal(
			Map<String, List> selectedOptions, String startDate, String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
		
		Map<String,Map<Integer,Double>> liquefaction=new HashMap<String, Map<Integer,Double>>();
		List<String> terminals=lngDao.getSelectedTerminals(selectedOptions,startDateVal,endDateVal,LIQUEFACTION);
		if(terminals.size()>0)
		{
			List<Lng> liquefactionList=lngDao.getLiquefactionCriteriaData(startDateVal, endDateVal);			
			liquefaction=calculateCapacitiesByTerminal(liquefactionList,terminals);
		}				
		return liquefaction;
	}
	@Override
	public Map<String, Map<Integer, Double>> getRegasificationCapacityByCompany(
			Map<String, List> selectedOptions, String startDate, String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		
		Map<String,Map<Integer,Double>> regasification=new HashMap<String, Map<Integer,Double>>();
		List<String> companies=lngDao.getSelectedCompanies(selectedOptions,startDateVal,endDateVal,REGASIFICATION);
		List<String> terminals=lngDao.getSelectedTerminals(selectedOptions, startDateVal,endDateVal,REGASIFICATION);
		List<Integer> years=lngDao.getSelectedYears(startDateVal,endDateVal,REGASIFICATION);
		if(companies.size()>0 && terminals.size()>0)
		{
//			List<Lng> regasificationList=lngDao.getRegasificationCriteriaData(terminals);		
			regasification=calculateCapacitiesByCompany(companies,terminals,years,REGASIFICATION);
		}			
				
		return regasification;
	}
	@Override
	public Map<String, Map<Integer, Double>> getLiquefactionCapacityByCompany(
			Map<String, List> selectedOptions, String startDate, String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
		
		Map<String,Map<Integer,Double>> liquefaction=new HashMap<String, Map<Integer,Double>>();
		List<String> companies=lngDao.getSelectedCompanies(selectedOptions,startDateVal,endDateVal,LIQUEFACTION);
		List<String> terminals=lngDao.getSelectedTerminals(selectedOptions, startDateVal, endDateVal, LIQUEFACTION);
		List<Integer> years=lngDao.getSelectedYears(startDateVal,endDateVal,LIQUEFACTION);
		if(companies.size()>0 && terminals.size()>0)
		{					
			liquefaction=calculateCapacitiesByCompany(companies,terminals,years,LIQUEFACTION);
		}				
		return liquefaction;		
		
	}
	/*
	 * (non-Javadoc)
	 * @see com.oganalysis.business.LngCapacityBusinessService#getLiqueModalCapacityForRecord(java.util.Map, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * Modal data for a record of type Liquefaction
	 */
	@Override
	public Map<String, Map<Integer, Double>> getLiqueModalCapacityForRecord(Map<String,List> selectedOptions,
			String startDate,
			String endDate, String displayType, String recordName) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
			Map<String,Map<Integer,Double>> terminalCapacities=new HashMap<String, Map<Integer,Double>>();
		List<String> terminals=lngDao.getSelectedTerminals(selectedOptions, startDateVal,endDateVal,LIQUEFACTION);
		if(null!=displayType && displayType.equalsIgnoreCase("country"))
			terminalCapacities=getTerminalsCapacityForCountry(recordName,terminals,LIQUEFACTION);
		else if(null!=displayType && displayType.equalsIgnoreCase("company"))					
			terminalCapacities=getTerminalsCapacityForCompany(recordName,terminals,LIQUEFACTION);
						
		return terminalCapacities;
	}
	/*
	 * (non-Javadoc)
	 * @see com.oganalysis.business.LngCapacityBusinessService#getRegasModalCapacityForRecord(java.util.Map, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 * Modal data for a record of type Regasifiation
	 */
	@Override
	public Map<String, Map<Integer, Double>> getRegasModalCapacityForRecord(Map<String,List> selectedOptions,
			String startDate,
			String endDate, String displayType, String recordName) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
		
		Map<String,Map<Integer,Double>> terminalCapacities=new HashMap<String, Map<Integer,Double>>();
		List<String> terminals=lngDao.getSelectedTerminals(selectedOptions,startDateVal,endDateVal,REGASIFICATION);
		if(null!=displayType && displayType.equalsIgnoreCase("country"))
			terminalCapacities=getTerminalsCapacityForCountry(recordName,terminals,REGASIFICATION);
		else if(null!=displayType && displayType.equalsIgnoreCase("company"))
			terminalCapacities=getTerminalsCapacityForCompany(recordName,terminals,REGASIFICATION);
		
		return terminalCapacities;
	}
	private Map<String,Map<Integer,Double>> getTerminalsCapacityForCountry(String recordName,List<String> terminals,String type)
	{
		List<Lng> lngList=null;
		if(type.equalsIgnoreCase(LIQUEFACTION))
			lngList=lngDao.getLiquefactionCriteriaData(terminals);
		else
			lngList=lngDao.getRegasificationCriteriaData(terminals);
		
		Map<String,Map<Integer,Double>> countryTerminalsCapacity=new HashMap<String, Map<Integer,Double>>();
		List<String> countryTerminals=lngDao.getCountryTerminals(recordName,terminals,type);
		Map<String,Map<Integer,Double>> terminalsCapacity=calculateCapacitiesByTerminal(lngList, countryTerminals);
		for(String terminal:terminals)
		{
			if(null!=terminalsCapacity.get(terminal))
				countryTerminalsCapacity.put(terminal, terminalsCapacity.get(terminal));
		}
		return countryTerminalsCapacity;
		
	}
	private Map<String,Map<Integer,Double>> getTerminalsCapacityForCompany(String recordName,List<String> terminals,String type)
	{
		List<Lng> lngList=null;
		if(type.equalsIgnoreCase(LIQUEFACTION))
			lngList=lngDao.getLiquefactionCriteriaData(terminals);
		else
			lngList=lngDao.getRegasificationCriteriaData(terminals);
		
		Map<String,Map<Integer,Double>> companyTerminalsCapacity=new HashMap<String, Map<Integer,Double>>();
		List<String> companyTerminals=lngDao.getCompanyTerminals(recordName,terminals,type);
		Map<String,Map<Integer,Double>> terminalsCapacity=calculateCapacitiesByTerminal(lngList,companyTerminals);	
		
		for(String terminal:terminals)
		{
			if(null!=terminalsCapacity.get(terminal))
				companyTerminalsCapacity.put(terminal, terminalsCapacity.get(terminal));
		}
		
		return companyTerminalsCapacity;
	}
	
	private Map<String,Map<Integer,Double>> calculateCapacitiesByCompany(List<String> companies,List<String> terminals,List<Integer> years,String type)
	{		
		List<Lng> lngList=new ArrayList<Lng>();
		if(type.equalsIgnoreCase(LIQUEFACTION))
			lngList=lngDao.getLiquefactionCriteriaData(terminals);
		else
			lngList=lngDao.getRegasificationCriteriaData(terminals);
		
		Map<String,Map<Integer,Double>> companyMap=new HashMap<String, Map<Integer,Double>>();		
		Map<String,Double> companyStakeForTerminal=getCompanyStakeForTerminal(type);								
		Map<Integer,Double> yearMap;
		for(String company:companies)
		{
				List<String> companyTerminals=lngDao.getCompanyTerminals(company,terminals,type);
				if(companyTerminals.size()>0)
				{
					yearMap=new HashMap<Integer, Double>();
					for(Integer year:years)
					{				
						double soc=0.0;
						for(String terminal:companyTerminals)
						{			
							String key=company+"_"+terminal;
							double stake=companyStakeForTerminal.get(key);			
												
							for(Lng lng:lngList)
							{
//								System.out.println(lng.getName()+" :"+lng.getCapacityYear());
								if(terminal.equalsIgnoreCase(lng.getName()) && year==lng.getCapacityYear())
									soc=soc+(lng.getCapacity()*(stake/100));
							}					
						}
						yearMap.put(year,soc);//Double.valueOf(formatCapacity.format(soc))
					}
					
					companyMap.put(company, yearMap);
				}
				
		}
			return companyMap;
	}
		
	//This is to calculate capacities by Terminal i.e with name field . 
	private Map<String,Map<Integer,Double>> calculateCapacitiesByTerminal(List<Lng> lngList,List<String> terminals)
	{
//		Set<String> terminals=getTerminals(lngList);
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
				yearMap.put(year,Double.valueOf(formatCapacity.format(soc)));
			}
			terminalMap.put(terminal,yearMap);
		}
		return terminalMap;
	
	}
	// This is to calculate capacities by Country i.e with country field .
	private Map<String,Map<Integer,Double>> calculateCapacitiesByCountry(List<String> countries,List<String> terminals,List<Integer> years,String type)
	{		
		List<Lng> lngList=null;
		if(type.equalsIgnoreCase(LIQUEFACTION))
			lngList=lngDao.getLiquefactionCriteriaData(terminals);
		else
			lngList=lngDao.getRegasificationCriteriaData(terminals);
				
		Map<Integer,Double> yearMap=null;
		Map<String,Map<Integer,Double>> countryMap=new HashMap<String, Map<Integer,Double>>();
		for(String country:countries)
		{
			List<String> countryTerminals=lngDao.getCountryTerminals(country, terminals, type);
			if(countryTerminals.size()>0)
			{
				yearMap=new HashMap<Integer, Double>();
				for(Integer year:years)
				{
					double soc=0.0;
					for(String terminal:countryTerminals)
					{
						for(Lng lng:lngList)
						{
							if(terminal.equalsIgnoreCase(lng.getName()) && country.equalsIgnoreCase(lng.getCountry()) && year==lng.getCapacityYear())
								soc=soc+lng.getCapacity();
						}
					}				
					yearMap.put(year,Double.valueOf(formatCapacity.format(soc)));
				}
				countryMap.put(country,yearMap);
			}
			
		}
		return countryMap;
	}
	private Set<Integer> getYears(List<Lng> lngList)
	{
		Set<Integer> years=new HashSet<Integer>();
		for(Lng lng:lngList)
		{
			if(lng.getCapacityYear()!=0)
			years.add(lng.getCapacityYear());			
		}
		return years;
	}	
	private Map<String,Double> getCompanyStakeForTerminal(String type)
	{
		List<LngFilter> lngFilterList=lngDao.getLngFilter(type);
		Map<String,Double> companyStakeForTerminal=new HashMap<String, Double>();
		for(LngFilter lngFilter:lngFilterList)
		{
			String companyName=lngFilter.getEquityPartners();
			String terminalName=lngFilter.getName();
			String key=companyName+"_"+terminalName;
			companyStakeForTerminal.put(key, lngFilter.getEquityStakes());
		}
		return companyStakeForTerminal;
	}

	@Override
	public Map<String, String> getTerminalData(String recordName,String type) {
		// TODO Auto-generated method stub
		
		List<Lng> regasificationTerminalList=lngDao.getTerminalData(recordName, type);
		Map terminalData=new HashMap();
		Lng lng=regasificationTerminalList.get(0);
		terminalData.put("terminalName",lng.getName());
		terminalData.put("type", lng.getType());
		terminalData.put("status",lng.getStatus());
		terminalData.put("location",lng.getArea());
		terminalData.put("country",lng.getCountry());
		terminalData.put("onshoreoroffshore",lng.getOnshoreOrOffshore());
		terminalData.put("expectedStartUp", lng.getExpectedStartYear()!=null?lng.getExpectedStartYear().toString():"");
		if(null!=lng.getScheduledStartYear() && !"".equals(lng.getScheduledStartYear()))
			terminalData.put("scheduledStartUp",lng.getScheduledStartYear().toString());
		else
			terminalData.put("scheduledStartUp","");
		
		terminalData.put("processingCapacity", getProcessingCapacity(regasificationTerminalList));
		terminalData.put("trainsOrVaporizers",getTrainsOrVaporizers(regasificationTerminalList));
		terminalData.put("storageCapacity",getStorageCapacity(regasificationTerminalList));
		terminalData.put("storageTanks", getStorageTanks(regasificationTerminalList));
		
		terminalData.put("operator",getOperator(regasificationTerminalList).toString());
		terminalData.put("ownership",getOwnership(recordName,type));

		terminalData.put("capex", String.valueOf(getCapexDetails(regasificationTerminalList)));
		terminalData.put("technology",getTechnologyDetails(regasificationTerminalList).toString());
		terminalData.put("constructionPeriod",getConstructionPeriod(regasificationTerminalList));
		terminalData.put("constructionDetails",getConstructionDetails(regasificationTerminalList));
		
		terminalData.put("sourceFields",getSourceFields(regasificationTerminalList).toString());
		terminalData.put("distributionType",getDistributionType(regasificationTerminalList).toString());
		return terminalData;
	}
	private List<Map<String,String>> getOwnership(String recordName,String type)
	{
			
		List<Map<String,String>> ownershipList=new ArrayList<Map<String,String>>();
		List<LngFilter> lngFilterList=lngDao.getTerminalCompanies(recordName,type);
		Map<String,Double> companyStakes=getCompanyStakeForTerminal(type);		
		Map<String,String> ownershipMap=null;new HashMap<String, String>();
		for(LngFilter lngFilter:lngFilterList)
		{				
				ownershipMap=new HashMap<String, String>();
				String key=lngFilter.getEquityPartners()+"_"+lngFilter.getName();			
				ownershipMap.put("equityPartner",lngFilter.getEquityPartners());
				ownershipMap.put("equityStake",String.valueOf(companyStakes.get(key)));
				ownershipList.add(ownershipMap);
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
	public LngDao getLngDao() {
		return lngDao;
	}
	public void setLngDao(LngDao lngDao) {
		this.lngDao = lngDao;
	}
	

	
	
}
