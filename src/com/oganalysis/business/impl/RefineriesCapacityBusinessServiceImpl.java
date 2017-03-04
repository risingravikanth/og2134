package com.oganalysis.business.impl;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oganalysis.business.RefineriesCapacityBusinessService;
import com.oganalysis.dao.RefineriesDao;
import com.oganalysis.entities.RefineriesFilter;
import com.oganalysis.entities.Refinery;

public class RefineriesCapacityBusinessServiceImpl implements RefineriesCapacityBusinessService {

	private RefineriesDao refineriesDao;
		

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
			terminalsCapacity=calculateCapacitiesByTerminal(selectedTerminals,selectedOptions,startDate,endDate);
			
		return terminalsCapacity;
	}
	private Map<String,Map<Integer,Double>> calculateCapacitiesByCompany(List<String> selectedCompanies,Map<String,List<String>> selectedOptions,int startDate,int endDate)	
	{
		List<String> selectedTerminals=refineriesDao.getSelectedTerminals(selectedOptions,startDate, endDate);
		Map<String,List<String>> selectedOptionsWithoutOwners=getSelectedOptionsWithoutOwners(selectedOptions);
		List<Refinery> refineriesList=refineriesDao.getRefineriesData(selectedOptionsWithoutOwners,selectedTerminals, startDate, endDate);
		List<Integer> years=getSelectedYears(startDate, endDate);
		Map<String,Double> companyStakes=getCompanyStakeForTerminal();
		Map<Integer,Double> yearMap=null;
		Map<String,Map<Integer,Double>> companiesCapacity=new HashMap<String, Map<Integer,Double>>();
		for(String company:selectedCompanies)
		{
			List<String> companyTerminals=refineriesDao.getCompanyTerminals(company,selectedTerminals);
			if(companyTerminals.size()>0)
			{
				yearMap=new HashMap<Integer, Double>();
				for(Integer year:years)
				{
					double soc=0;					
					for(String terminal:companyTerminals)
					{
						double stake=companyStakes.get(company.toLowerCase()+UNDERSCORE+terminal.toLowerCase());														
						for(Refinery refinery:refineriesList)
						{
							if(terminal.equals(refinery.getName()) && year==refinery.getCapacityYear())
								soc=soc+(refinery.getRefiningCapacity()*(stake/100));
						}
						soc=round(soc,2);
						yearMap.put(year, soc);
					}
				}
				companiesCapacity.put(company, yearMap);
			}						
		}
		return companiesCapacity;
	}
	private Map<String,Map<Integer,Double>> calculateCapacitiesByCountry(List<String> selectedCountries,Map<String,List<String>> selectedOptions,int startDate,int endDate)	
	{
		List<String> selectedTerminals=refineriesDao.getSelectedTerminals(selectedOptions,startDate, endDate);
		Map<String,List<String>> selectedOptionsWithoutOwners=getSelectedOptionsWithoutOwners(selectedOptions);
		List<Refinery> refineriesList=refineriesDao.getRefineriesData(selectedOptionsWithoutOwners,selectedTerminals,startDate,endDate);
		List<Integer> years=getSelectedYears(startDate, endDate);
		
		Map<Integer,Double> yearMap=null;
		Map<String,Map<Integer,Double>> countriesCapacity=new HashMap<String, Map<Integer,Double>>();
		for(String country:selectedCountries)
		{
			List<String> countryTerminals=refineriesDao.getCountryTerminals(country,selectedTerminals);
			if(countryTerminals.size()>0)
			{
				yearMap=new HashMap<Integer, Double>();
				for(Integer year:years)
				{
					double soc=0;					
					for(String terminal:countryTerminals)
					{																			
						for(Refinery refinery:refineriesList)
						{
							if(terminal.equals(refinery.getName()) && year==refinery.getCapacityYear() && country.equals(refinery.getCountry()))
								soc=soc+refinery.getRefiningCapacity();
						}
						soc=round(soc,2);
						yearMap.put(year, soc);
					}
				}
				countriesCapacity.put(country, yearMap);
			}						
		}
		return countriesCapacity;
	}
	private Map<String,Map<Integer,Double>> calculateCapacitiesByTerminal(List<String> selectedTerminals,Map<String,List<String>> selectedOptions,int startDate,int endDate)	
	{
		Map<String,List<String>> selectedOptionsWithoutOwners=getSelectedOptionsWithoutOwners(selectedOptions);
		List<Refinery> refineriesList=refineriesDao.getRefineriesData(selectedOptionsWithoutOwners,selectedTerminals,startDate,endDate);
		List<Integer> years=getSelectedYears(startDate, endDate);
		
		Map<Integer,Double> yearMap=null;
		Map<String,Map<Integer,Double>> terminalsCapacity=new HashMap<String, Map<Integer,Double>>();
		for(String terminal:selectedTerminals)
		{
				yearMap=new HashMap<Integer, Double>();
				for(Integer year:years)
				{
						double soc=0;																									
						for(Refinery refinery:refineriesList)
						{
							if(terminal.equals(refinery.getName()) && year==refinery.getCapacityYear())
								soc=soc+refinery.getRefiningCapacity();
						}
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
		List<String> countryTerminals=refineriesDao.getCountryTerminals(countryName,selectedTerminals);		
		Map<String,Map<Integer,Double>> countryterminalsCapacity=calculateCapacitiesByTerminal(countryTerminals,selectedOptions,startDate,endDate);	
					
		return countryterminalsCapacity;
		
	}
	private Map<String,Map<Integer,Double>> getTerminalsCapacityForCompany(String companyName,Map<String,List<String>> selectedOptions,int startDate,int endDate)
	{
		List<String> selectedTerminals=refineriesDao.getSelectedTerminals(selectedOptions, startDate, endDate);			
		List<String> companyTerminals=refineriesDao.getCompanyTerminals(companyName,selectedTerminals);		
		Map<String,Map<Integer,Double>> companyterminalsCapacity=calculateCapacitiesByTerminal(companyTerminals,selectedOptions,startDate,endDate);	
					
		return companyterminalsCapacity;
	}
	@Override
	public Map getTerminalData(String recordName) {
		// TODO Auto-generated method stub
		return null;
	}
	private double round(double value, int places) {	    

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	private Map<String,Double> getCompanyStakeForTerminal()
	{
		List<RefineriesFilter> refineriesFilterList=refineriesDao.getRefineriesFilter();
		Map<String,Double> companyStakeForTerminal=new HashMap<String, Double>();
		for(RefineriesFilter refineriesFilter:refineriesFilterList)
		{
			String companyName=refineriesFilter.getCurrentEquityPartners();
			String terminalName=refineriesFilter.getName();
			String key=companyName.toLowerCase()+UNDERSCORE+terminalName.toLowerCase();
			companyStakeForTerminal.put(key, refineriesFilter.getCurrentEquityStakes());
		}
		return companyStakeForTerminal;
	}
	private List<Integer> getSelectedYears(int startDate,int endDate)
	{
		List<Integer> years=new ArrayList<Integer>();
		for(int i=startDate;i<=endDate;i++)
			years.add(i);
		return years;
	}
	private Map<String,List<String>> getSelectedOptionsWithoutOwners(Map<String,List<String>> selectedOptions)
	{
		Map<String,List<String>> selectedOptionsWithoutOwners=new HashMap<String, List<String>>();
		Set<String> keys=selectedOptions.keySet();
		for(String key:keys)
		{
			if(null!=key && !OPTION_SELECTED_OWNERS.equals(key) && !OPTION_SELECTED_OPERATORS.equals(key))
				selectedOptionsWithoutOwners.put(key,selectedOptions.get(key));
		}
		
		return selectedOptionsWithoutOwners;
	}
	public RefineriesDao getRefineriesDao() {
		return refineriesDao;
	}

	public void setRefineriesDao(RefineriesDao refineriesDao) {
		this.refineriesDao = refineriesDao;
	}

	

	
	
}