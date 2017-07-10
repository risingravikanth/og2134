package com.oganalysis.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.business.StorageCapacityBusinessService;
import com.oganalysis.cache.StorageCache;
import com.oganalysis.dao.StorageDao;

import static com.oganalysis.constants.ApplicationConstants.*;
public class StorageCapacityBusinessServiceImpl implements StorageCapacityBusinessService {
	private StorageDao storageDao;
	private StorageCache storageCache;
	@Override
	public Map<String, Map<Integer, Double>> getCapacityByCompany(
			Map<String, List<String>> selectedOptions, int startYear,
			int endYear) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> companiesCapacity=new HashMap<String, Map<Integer,Double>>();		
		List<String> selectedCompanies=storageDao.getSelectedCompanies(selectedOptions, startYear, endYear);
		if(selectedCompanies.size()>0)
			companiesCapacity=calculateCapacitiesByCompany(selectedCompanies,selectedOptions,startYear,endYear);			
		return companiesCapacity;
	}

	public void setStorageCache(StorageCache storageCache) {
		this.storageCache = storageCache;
	}

	@Override
	public Map<String, Map<Integer, Double>> getCapacityByCountry(
			Map<String, List<String>> selectedOptions, int startYear,
			int endYear) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> countriesCapacity=new HashMap<String, Map<Integer,Double>>();
		List<String> selectedCountries=storageDao.getSelectedCountries(selectedOptions, startYear, endYear);
		if(selectedCountries.size()>0)
			countriesCapacity=calculateCapacitiesByCountry(selectedCountries,selectedOptions,startYear,endYear);			
		return countriesCapacity;		
	}

	@Override
	public Map<String, Map<Integer, Double>> getCapacityByTerminal(
			Map<String, List<String>> selectedOptions, int startYear,
			int endYear) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> terminalsCapacity=new HashMap<String, Map<Integer,Double>>();
		List<String> selectedTerminals=storageDao.getSelectedTerminals(selectedOptions, startYear, endYear);
		if(selectedTerminals.size()>0)
			terminalsCapacity=calculateCapacitiesByTerminal(selectedTerminals,startYear,endYear);			
		return terminalsCapacity;
	}
	private Map<String,Map<Integer,Double>> calculateCapacitiesByCompany(List<String> selectedCompanies,Map<String,List<String>> selectedOptions,int startYear,int endYear)
	{
		List<String> selectedTerminals=storageDao.getSelectedTerminals(selectedOptions, startYear,endYear);		
		List<Integer> years=getSelectedYears(startYear, endYear);
		Map<String,Double> terminalsYearCapacity=null;
		Map<String,Double> companyStakes=null;
		if(null==storageCache.getTerminalsYearCapacity())
		{
			terminalsYearCapacity=storageCache.createTerminalsYearCapacity();
			storageCache.setTerminalsYearCapacity(terminalsYearCapacity);
		}
		else
			terminalsYearCapacity=storageCache.getTerminalsYearCapacity();
		
		
		if(null==storageCache.getCompanyStakeForTerminal())
		{
			companyStakes=storageCache.createCompanyStakeForTerminal();
			storageCache.setCompanyStakeForTerminal(companyStakes);
		}
		else
			companyStakes=storageCache.getCompanyStakeForTerminal();
		
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
	private Map<String,Map<Integer,Double>> calculateCapacitiesByCountry(List<String> selectedCountries,Map<String,List<String>> selectedOptions,int startYear,int endYear)	
	{
		List<String> selectedTerminals=storageDao.getSelectedTerminals(selectedOptions, startYear, endYear);		
		List<Integer> years=getSelectedYears(startYear, endYear);
		Map<String,Double> terminalsYearCapacity=null;
		Map<Integer,Double> yearMap=null;
		Map<String,Map<Integer,Double>> countriesCapacity=new HashMap<String, Map<Integer,Double>>();
		if(null==storageCache.getTerminalsYearCapacity())
		{
			terminalsYearCapacity=storageCache.createTerminalsYearCapacity();
			storageCache.setTerminalsYearCapacity(terminalsYearCapacity);
		}
		else
			terminalsYearCapacity=storageCache.getTerminalsYearCapacity();
		
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
		if(null==storageCache.getTerminalsYearCapacity())
		{
			terminalsYearCapacity=storageCache.createTerminalsYearCapacity();
			storageCache.setTerminalsYearCapacity(terminalsYearCapacity);
		}
		else
			terminalsYearCapacity=storageCache.getTerminalsYearCapacity();
		
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
			Map<String, List<String>> selectedOptions, int startYear,
			int endYear, String displayType, String recordName) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> terminalCapacities=new HashMap<String, Map<Integer,Double>>();
		
		if(null!=displayType && displayType.equalsIgnoreCase(COUNTRY))
			terminalCapacities=getTerminalsCapacityForCountry(recordName,selectedOptions,startYear,endYear);
		else if(null!=displayType && displayType.equalsIgnoreCase(COMPANY))
			terminalCapacities=getTerminalsCapacityForCompany(recordName,selectedOptions,startYear,endYear);
		return terminalCapacities;
	}
	private Map<String,Map<Integer,Double>> getTerminalsCapacityForCountry(String countryName,Map<String,List<String>> selectedOptions,int startYear,int endYear)
	{
		List<String> selectedTerminals=storageDao.getSelectedTerminals(selectedOptions, startYear, endYear);
		List<String> countryTerminals=getCountryTerminals(countryName, selectedTerminals);		
		Map<String,Map<Integer,Double>> countryterminalsCapacity=calculateCapacitiesByTerminal(countryTerminals,startYear,endYear);					
		return countryterminalsCapacity;
		
	}
	private Map<String,Map<Integer,Double>> getTerminalsCapacityForCompany(String companyName,Map<String,List<String>> selectedOptions,int startYear,int endYear)
	{				
		List<String> selectedTerminals=storageDao.getSelectedTerminals(selectedOptions, startYear, endYear);
		List<String> companyTerminals=getCompanyTerminals(companyName, selectedTerminals);		
		Map<String,Map<Integer,Double>> companyterminalsCapacity=calculateCapacitiesByTerminal(companyTerminals,startYear,endYear);				
		return companyterminalsCapacity;
	}
	private List<String> getCompanyTerminals(String company,List<String> selectedTerminals)
	{
		List<String> companyTerminals=new ArrayList<String>();
		List<String> terminalsList=null;
		if(null==storageCache.getCompanyTerminals())
		{
			Map<String,List<String>> companyTerminalsMap=storageCache.createCompanyTerminals();
			storageCache.setCompanyTerminals(companyTerminalsMap);
			terminalsList=companyTerminalsMap.get(company.toLowerCase());
		}
		else
			terminalsList=storageCache.getCompanyTerminals().get(company.toLowerCase());
				
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
		if(null==storageCache.getCountryTerminals()){
			Map<String,List<String>> countryTerminalsMap=storageCache.createCountryTerminals();
			storageCache.setCountryTerminals(countryTerminalsMap);
			terminalsList=countryTerminalsMap.get(country.toLowerCase());
		}
		else
			terminalsList=storageCache.getCountryTerminals().get(country.toLowerCase());
	
		for(String terminal:selectedTerminals)
		{
			if(terminalsList.contains(terminal))
				countryTerminals.add(terminal);
		}
		return countryTerminals;
	}
	private List<Integer> getSelectedYears(int startYear,int endYear)
	{
		List<Integer> years=new ArrayList<Integer>();
		for(int i=startYear;i<=endYear;i++)
			years.add(i);
		return years;
	}	
	private double round(double value, int places) {	    

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	public void setStorageDao(StorageDao storageDao) {
		this.storageDao = storageDao;
	}

	

	
}