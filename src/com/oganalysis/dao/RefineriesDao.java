package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.RefineriesFilter;
import com.oganalysis.entities.Refinery;

public interface RefineriesDao {
		
	List<String> getSelectedCompanies(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	List<String> getSelectedCountries(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	List<String> getSelectedTerminals(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	
	public List<String> getTerminals(int startDate,int endDate);
	public List<Refinery> getRefineriesData(Map<String,List<String>> selectedOptions,List<String> selectedTerminals,int startDate,int endDate);
	
	public List<String> getCompanyTerminals(String company,List<String> selectedTerminals);
	public List<String> getCountryTerminals(String country,List<String> selectedTerminals);
	public List<RefineriesFilter> getRefineriesFilter();
	public List<Refinery> getTerminalData(String terminalName);
	public List<RefineriesFilter> getTerminalCompanies(String terminalName);
	public List<RefineriesFilter> getTerminalHistoricCompanies(String terminalName);
	
	// Below is for cache
	public List<Refinery> getRefineries(int year);
	public List<String> getCompanies();
	public List<String> getCountries();
	public List<String> getTerminals();
	public List<String> getCountryTerminals(String country);
	public List<String> getCompanyTerminals(String company);
	List<RefineriesFilter> getSelectedRefineries(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	public List<RefineriesFilter> getCompanyRefineries(String company,Map<String,List<String>> selectedOptions,int startDate,int endDate);
	public List<RefineriesFilter> getCountryRefineries(String country,Map<String,List<String>> selectedOptions,int startDate,int endDate);
	
}
