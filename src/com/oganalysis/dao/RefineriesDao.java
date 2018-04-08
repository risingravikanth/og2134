package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.RefineriesFilter;
import com.oganalysis.entities.Refinery;

public interface RefineriesDao {
		
	List<String> getSelectedCompanies(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	List<String> getSelectedCountries(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	List<String> getSelectedTerminals(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	
	public List<String> getTerminals(int startYear,int endYear);
		
	// Below method is for to get the companyStakes
	public List<RefineriesFilter> getRefineriesFilter();
	
	//Below is for Terminal Modal Data
	public List<Refinery> getTerminalData(String terminalName);
	public List<RefineriesFilter> getTerminalCompanies(String terminalName);
	public List<RefineriesFilter> getTerminalHistoricCompanies(String terminalName);
	
	// Below is for cache
	public List<Refinery> getRefineries(int year);
	public List<String> getCompanies();
	public List<String> getCountries();	
	public List<String> getCountryTerminals(String country);
	public List<String> getCompanyTerminals(String company);
	public List<String> getTerminals();
	
	//Infra
	List<String> getSelectedTerminals(Map<String,List<String>> selectedOptions);
	//filters
	public List<String> getRegions();	
	public List<String> getStatus();
	public List<String> getLocations();
	public List<String> getOperators();
	public List<String> getOwners();
	
	
			
}
