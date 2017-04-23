package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.Lng;
import com.oganalysis.entities.LngFilter;

public interface LngDao {
	
	List<String> getLiqueTerminals(int startYear,int endYear);
	List<String> getRegasTerminals(int startYear,int endYear);
	
	List<Lng> getTerminalData(String terminalName,String type);
	
	//Below is for filters
	List<String> getLocations();
	List<String> getOperators();
	List<String> getOwners();
				
	List<String> getSelectedCompanies(Map<String,List<String>> selectedOptions,int startDate,int endDate,String type);
	List<String> getSelectedCountries(Map<String,List<String>> selectedOptions,int startDate,int endDate,String type);
	List<String> getSelectedTerminals(Map<String,List<String>> selectedOptions,int startDate,int endDate,String type);
	
	List<String> getSelectedTerminals(Map<String,List<String>> selectedOptions,String type);		
	List<LngFilter> getTerminalCompanies(String terminal,String type);
	List<LngFilter> getLngFilter(String type);
		
	
	//Below is for cache
	List<Lng> getLng(int year,String type);
	List<String> getCountries(String type);
	List<String> getCompanies(String type);
	List<String> getCountryTerminals(String country);
	List<String> getCompanyTerminals(String company);
	//cache for Infrastructure
	List<String> getTerminals(String type);
}
