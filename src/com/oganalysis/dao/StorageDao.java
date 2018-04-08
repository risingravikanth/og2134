package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.Storage;
import com.oganalysis.entities.StorageFilter;

public interface StorageDao {

	
	List<String> getSelectedCompanies(Map<String,List<String>> selectedOptions,int startYear,int endYear);
	List<String> getSelectedCountries(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	List<String> getSelectedTerminals(Map<String,List<String>> selectedOptions,int startYear,int endYear);
//	
	public List<String> getTerminals(int startYear,int endYear);
	
	// Below method is for to get the companyStakes
	public List<StorageFilter> getStorageFilter();
	
//	//Below is for Terminal Modal Data
	public List<Storage> getTerminalData(String terminalName);
	public List<StorageFilter> getTerminalCompanies(String terminalName);
	
	
//	 Below is for cache
	public List<Storage> getStorage(int year);
	public List<String> getCompanies();
	public List<String> getCountries();	
	public List<String> getCountryTerminals(String country);
	public List<String> getCompanyTerminals(String company);
	public List<String> getTerminals();
//	
//	//Infra
	List<String> getSelectedTerminals(Map<String,List<String>> selectedOptions);
	//filters
	public List<String> getRegions();		
	public List<String> getStatus();	
	public List<String> getLocations();	
	public List<String> getOperators();	
	public List<String> getOwners();		

}
