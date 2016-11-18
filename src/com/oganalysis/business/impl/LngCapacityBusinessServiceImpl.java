package com.oganalysis.business.impl;

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
	
	private Map<String,Map<Integer,Double>> calculateRegasificationCapacitiesByCompany(List<Lng> lngList)
	{
		/*
		 * Each record doesn't have company details. So we calculated capacities for Regasification separately
		 */
		List<Lng> regasificationList=lngDao.getRegasificationData();
		Set<String> companies=getCompanies(regasificationList);
		Map<String,Double> companyStakeForTerminal=getCompanyStakeForTerminal(regasificationList);
		
		Map<String,Set<String>> companyTerminals=getCompanyTerminals(regasificationList);
		Map<Integer,Double> yearMap=null;
		
		Set<Integer> years=getYears(lngList);
		
		Map<String,Map<Integer,Double>> companyMap=new HashMap<String, Map<Integer,Double>>();
		
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
	private Map<String,Map<Integer,Double>> calculateLiquefactionCapacitiesByCompany(List<Lng> lngList)
	{
		/*
		 * Each record doesn't have company details. So we calculated capacities for Liquefaction separately
		 */
		List<Lng> liquefactionList=lngDao.getLiquefactionData();
		Set<String> companies=getCompanies(liquefactionList);
		Map<String,Double> companyStakeForTerminal=getCompanyStakeForTerminal(liquefactionList);
		
		Map<String,Set<String>> companyTerminals=getCompanyTerminals(liquefactionList);
		Map<Integer,Double> yearMap=null;
		
		Set<Integer> years=getYears(lngList);
		
		Map<String,Map<Integer,Double>> companyMap=new HashMap<String, Map<Integer,Double>>();
		
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
		List<Lng> dataList=null;
		if(lngList==null)
			dataList=lngDao.getLngData();
		else
			dataList=lngList;
		
		for(Lng lng:dataList)
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
	private Map<String,Set<String>> getCompanyTerminals(List<Lng> lngList)
	{
		Set<String> companies=getCompanies(lngList);
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
