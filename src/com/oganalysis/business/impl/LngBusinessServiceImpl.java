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
		int startDateVal=Integer.parseInt("2010"); // Time being hard code
		int endDateVal=Integer.parseInt("2020");
			
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
		int startDateVal=Integer.parseInt(startDate); // Time being hard code
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
		int startDateVal=Integer.parseInt(startDate); // Time being hard code
		int endDateVal=Integer.parseInt(endDate);
			
		List<Lng> liquefactionList=lngDao.getLiquefactionCriteriaData(selectedOptions, startDateVal, endDateVal);			
		Map<String,Map<Integer,Double>> liquefaction=calculateCapacitiesByCompany(liquefactionList);
	
		return liquefaction;
	}
	private Map<String,Map<Integer,Double>> calculateCapacitiesByCompany(List<Lng> dataList)
	{

		Set<String> trackerCompany=new HashSet<String>();		
		List<Lng> copyDataList=dataList;
		List<Lng> copyDataList2=dataList;
		Map<String,Map<Integer,Double>> companyMap=new HashMap<String, Map<Integer,Double>>();
		Map<Integer,Double> yearMap=null;
		for(Lng lng:dataList)
		{
			String companyName=lng.getEquityPartners();
			if(!trackerCompany.contains(companyName))
			{
				yearMap=new HashMap<Integer, Double>();
				Set<Integer> trackerYear=new HashSet<Integer>();
				for(Lng lngCopy1:copyDataList)
				{
					if(companyName.equals(lngCopy1.getEquityPartners()))
					{
						int capacityYear=lngCopy1.getCapacityYear();
						if(!trackerYear.contains(capacityYear))
						{							
							double soc=0;
							for(Lng lngCopy2:copyDataList2)
							{
//								System.out.println(lngCopy2.getName()+" Year:"+lngCopy2.getCapacityYear());
								if(capacityYear==lngCopy2.getCapacityYear() && companyName.equalsIgnoreCase(lngCopy2.getEquityPartners()))
									soc=soc+lngCopy2.getCapacity();
							}
							trackerYear.add(capacityYear);
							yearMap.put(capacityYear,soc);
						}
						
					}
				}
				trackerCompany.add(companyName);
				companyMap.put(companyName, yearMap);
			}
			
		}
		return companyMap;
	
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
//			countryMap.put(countryName, yearMap);
		}
		return countryMap;
	}
	public LngDao getLngDao() {
		return lngDao;
	}
	public void setLngDao(LngDao lngDao) {
		this.lngDao = lngDao;
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
	
	
	
}
