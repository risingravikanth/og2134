package com.oganalysis.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oganalysis.business.LngBusinessService;
import com.oganalysis.dao.LngDao;
import com.oganalysis.entities.Lng;

public class LngBusinessServiceImpl implements LngBusinessService {

	private LngDao lngDao;
	public static final String REGASIFICATION="Regasification";
	public static final String LIQUEFACTION="Liquefaction";
	
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
		
		Map<String,Map<Integer,Double>> regasification=calculateCapacitiesByCompany(regasificationList);						
		
		return regasification;
	}
	@Override
	public Map<String, Map<Integer, Double>> getLiquefactionCapacityByCompany(
			Map<String, List> selectedOptions, String startDate, String endDate) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate); 
		int endDateVal=Integer.parseInt(endDate);
			
		List<Lng> liquefactionList=lngDao.getLiquefactionCriteriaData(selectedOptions, startDateVal, endDateVal);			
		Map<String,Map<Integer,Double>> liquefaction=calculateCapacitiesByCompany(liquefactionList);
	
		return liquefaction;
	}
	private Map<String,Map<Integer,Double>> calculateCapacitiesByCompany(List<Lng> dataList)
	{
		List<Lng> lngList=lngDao.getLngData();
		Set<String> companies=getCompanies(lngList);		
		Map<String,Set<String>> companyTerminals=getCompanyTerminals(lngList);
		Map<String,Map<Integer,Double>> companyMap=new HashMap<String, Map<Integer,Double>>();
		for(String companyName:companies)
		{
				Set<String> terminals=companyTerminals.get(companyName);
//				List<Map<String,Map<Integer,Double>>> terminalsList=getCapacityByTerminalForCompany(companyName, dataList,terminals,lngList);
				Map<String,Map<Integer,Double>> terminalYearCapacityMap=getCapacityByTerminalForCompany(companyName, dataList,terminals,lngList);
				Map<Integer,Double> companyYearCapacityMap=new HashMap<Integer, Double>();
				double capacity=0.0;
//				for(Map<String,Map<Integer,Double>> terminalYearCapacityMap:terminalsList)
//				{
					Set<String> terminalKeys=terminalYearCapacityMap.keySet();
					for(String terminalName:terminalKeys)
					{
						Map<Integer,Double> yearCapacityMap=terminalYearCapacityMap.get(terminalName);
						Set<Integer> yearKeys=yearCapacityMap.keySet();
						for(Integer year:yearKeys)
						{
							if(companyYearCapacityMap.containsKey(year))
							{
								double capacityVal=companyYearCapacityMap.get(year);
								capacity=capacityVal+yearCapacityMap.get(year);
								companyYearCapacityMap.put(year,capacity);
							}
							else
							{	
								capacity=yearCapacityMap.get(year);
								companyYearCapacityMap.put(year,capacity);
							}	
							
						}
					}
//				}
				companyMap.put(companyName, companyYearCapacityMap);							
		}
		return companyMap;
	}
//	List<Map<String,Map<Integer,Double>>> 
	private Map<String,Map<Integer,Double>> getCapacityByTerminalForCompany(String companyName,List<Lng> dataList,Set<String> terminals,List<Lng> lngList)
	{
			
		Map<String,Double> stakeMap=getCompanyTerminalStake(lngList);
		List<Lng> lngCopyDataList1=dataList;
		List<Lng> lngCopyDataList2=dataList;
//		List<Map<String,Map<Integer,Double>>> totalTerminals=new ArrayList<Map<String,Map<Integer,Double>>>();
		Map<String,Map<Integer,Double>> terminalMap=new HashMap<String, Map<Integer,Double>>();
		for(String terminalName:terminals)
		{
			String key=terminalName+"_"+companyName;
			Set<String> terminalTracker=new HashSet<String>();
			double stake=stakeMap.get(key);
//			Map<String,Map<Integer,Double>> terminalMap=new HashMap<String, Map<Integer,Double>>();
			for(Lng lng:dataList)
			{				
				if(terminalName.equalsIgnoreCase(lng.getName()))
				{					
					if(!terminalTracker.contains(terminalName))
					{
						HashMap<Integer,Double> yearMap=new HashMap<Integer, Double>();
						Set<Integer> trackerYear=new HashSet<Integer>();						
						for(Lng lngCopy1:lngCopyDataList1)
						{
							if(terminalName.equalsIgnoreCase(lngCopy1.getName()))
							{
								int capacityYear=lngCopy1.getCapacityYear();							
								if(!trackerYear.contains(capacityYear)){
									double soc=0.0;
									for(Lng lngCopy2:lngCopyDataList2)
									{
										if(lngCopy2.getCapacityYear()==capacityYear && terminalName.equalsIgnoreCase(lngCopy2.getName()))
										{
											soc=soc+(lngCopy2.getCapacity()*(stake/100));
										}
									}
									
									trackerYear.add(capacityYear);
									yearMap.put(capacityYear,soc);
								}
							}
							
						}
						terminalTracker.add(terminalName);
						terminalMap.put(terminalName,yearMap);
					}
				}
			}
//			totalTerminals.add(terminalMap);
		}
		return terminalMap;
	}
	//This is to calculate capacities by Terminal i.e with name field
	private Map<String,Map<Integer,Double>> calculateCapacitiesByTerminal(List<Lng> dataList)
	{

		Set<String> trackerTerminal=new HashSet<String>();		
		List<Lng> copyDataList=dataList;
		List<Lng> copyDataList2=dataList;
		Map<String,Map<Integer,Double>> terminalMap=new HashMap<String, Map<Integer,Double>>();
		Map<Integer,Double> yearMap=null;
		for(Lng lng:dataList)
		{
			String terminalName=lng.getName();
			if(!trackerTerminal.contains(terminalName))
			{
				yearMap=new HashMap<Integer, Double>();
				Set<Integer> trackerYear=new HashSet<Integer>();
				for(Lng lngCopy1:copyDataList)
				{
					if(terminalName.equals(lngCopy1.getName()))
					{
						int capacityYear=lngCopy1.getCapacityYear();
						if(!trackerYear.contains(capacityYear))
						{							
							double soc=0;
							for(Lng lngCopy2:copyDataList2)
							{
//								System.out.println(lngCopy2.getName()+" Year:"+lngCopy2.getCapacityYear());
								if(capacityYear==lngCopy2.getCapacityYear() && terminalName.equalsIgnoreCase(lngCopy2.getName()))
									soc=soc+lngCopy2.getCapacity();
							}
							trackerYear.add(capacityYear);
							yearMap.put(capacityYear,soc);
						}
						
					}
				}
				trackerTerminal.add(terminalName);
				terminalMap.put(terminalName, yearMap);
			}
			
		}
		return terminalMap;
	
	}
	private Map<String,Map<Integer,Double>> calculateCapacitiesByCountry(List<Lng> dataList)
	{
		Set<String> trackerCountry=new HashSet<String>();		
		List<Lng> copyDataList=dataList;
		List<Lng> copyDataList2=dataList;
		Map<String,Map<Integer,Double>> countryMap=new HashMap<String, Map<Integer,Double>>();
		Map<Integer,Double> yearMap=null;
		for(Lng lng:dataList)
		{
			String countryName=lng.getCountry();
			if(!trackerCountry.contains(countryName))
			{
				yearMap=new HashMap<Integer, Double>();
				Set<Integer> trackerYear=new HashSet<Integer>();
				for(Lng lngCopy1:copyDataList)
				{
					if(countryName.equals(lngCopy1.getCountry()))
					{
						int capacityYear=lngCopy1.getCapacityYear();
						if(!trackerYear.contains(capacityYear))
						{							
							double soc=0;
							for(Lng lngCopy2:copyDataList2)
							{
//								System.out.println(lngCopy2.getName()+" Year:"+lngCopy2.getCapacityYear());
								if(capacityYear==lngCopy2.getCapacityYear() && countryName.equalsIgnoreCase(lngCopy2.getCountry()))
									soc=soc+lngCopy2.getCapacity();
							}
							trackerYear.add(capacityYear);
							yearMap.put(capacityYear,soc);
						}
						
					}
				}
				trackerCountry.add(countryName);
				countryMap.put(countryName, yearMap);
			}
			
		}
		return countryMap;
	}
	@Override
	public List<Map<String,String>> getRegasificationInfrastructure(
			Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
		List<Lng> regasificationList=lngDao.getRegasificationCriteriaData(selectedOptions);
		List<Map<String,String>> mapList=new ArrayList<Map<String,String>>();
		for(Lng lng:regasificationList)
		{
			Map<String,String> regasificationMap=new HashMap<String, String>();
			regasificationMap.put("terminalName", lng.getName());
			regasificationMap.put("status", lng.getStatus());
			regasificationMap.put("startYear", ""); // This one need to check
			regasificationMap.put("location", lng.getArea());
			regasificationMap.put("technology",lng.getTechnologyDetails());// This one also check once;
			regasificationMap.put("train","");//This one also need to check;
			regasificationMap.put("operator",lng.getOperator());//This one also need to check;
			regasificationMap.put("storageCapacity","");//This one need to check;
			regasificationMap.put("tanks","");//This one also need to check;
			mapList.add(regasificationMap);
		}
		return mapList;
	}
	@Override
	public List<Map<String,String>> getLiquefactionInfrastructure(
			Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
		List<Lng> liquefactionList=lngDao.getLiquefactionCriteriaData(selectedOptions);
		List<Map<String,String>> mapList=new ArrayList<Map<String,String>>();
		for(Lng lng:liquefactionList)
		{
			Map<String,String> liquefactionMap=new HashMap<String, String>();
			liquefactionMap.put("terminalName", lng.getName());
			liquefactionMap.put("status", lng.getStatus());
			liquefactionMap.put("startYear", ""); // This one need to check
			liquefactionMap.put("location", lng.getArea());
			liquefactionMap.put("technology",lng.getTechnologyDetails());// This one also check once;
			liquefactionMap.put("train","");//This one also need to check;
			liquefactionMap.put("operator",lng.getOperator());//This one also need to check;
			liquefactionMap.put("storageCapacity","");//This one need to check;
			liquefactionMap.put("tanks","");//This one also need to check;
			mapList.add(liquefactionMap);
		}
		return mapList;
	}
	private Map<String,Double> getCompanyTerminalStake(List<Lng> lngList)
	{
		
		Set<String> terminalTracker=new HashSet<String>();
		Map<String,Double> companyTerminalStake=new HashMap<String, Double>();
		List<Lng> dataList=null;
		if(lngList==null)
			dataList=lngDao.getLngData();
		else
			dataList=lngList;
		List<Lng> lngCopyDataList=dataList;
		for(Lng lng:dataList)
		{
			String terminalName=lng.getName();
			String companyName=lng.getEquityPartners();
			
			if(null!=companyName && !("").equalsIgnoreCase(companyName))
			{
				if(!terminalTracker.contains(terminalName))
				{
					for(Lng lngCopy:lngCopyDataList)
					{
						if(terminalName.equalsIgnoreCase(lngCopy.getName()) && !lngCopy.getEquityPartners().equalsIgnoreCase(""))
						{	
							String key=terminalName+"_"+lngCopy.getEquityPartners();
							companyTerminalStake.put(key, lngCopy.getEquityStakes());
						}	
					}
					terminalTracker.add(terminalName);
				}
			}
			
		}
		return companyTerminalStake;
	}
	private Map<String,Set<String>> getCompanyTerminals(List<Lng> lngList)
	{
		Set<String> companyTracker=new HashSet<String>();
		Map<String,Set<String>> terminalCompany=new HashMap<String, Set<String>>();
		List<Lng> dataList=null;
		if(lngList==null)
			dataList=lngDao.getLngData();
		else
			dataList=lngList;
		List<Lng> lngCopyDataList=dataList;
		for(Lng lng:dataList)
		{
			String companyName=lng.getEquityPartners();		
			Set<String> terminals=new HashSet<String>();
			if(null!=companyName && !("").equalsIgnoreCase(companyName))
			{
				if(!companyTracker.contains(companyName)){
					for(Lng lngCopy:lngCopyDataList)
					{
						if(companyName.equalsIgnoreCase(lngCopy.getEquityPartners()) && !(lngCopy.getEquityPartners().equalsIgnoreCase("")))
							terminals.add(lngCopy.getName());
					}
					companyTracker.add(companyName);
					terminalCompany.put(companyName, terminals);
				}
			}
			
		}
		return terminalCompany;
	}
	private Set<String> getCompanies(List<Lng> lngList)
	{
		List<Lng> dataList=null;
		if(lngList==null)
			dataList=lngDao.getLngData();
		else
			dataList=lngList;
		Set<String> companiesList=new HashSet<String>();
		for(Lng lng:dataList)
		{
			if(null!=lng.getEquityPartners() && !("").equalsIgnoreCase(lng.getEquityPartners()))
				companiesList.add(lng.getEquityPartners());
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
