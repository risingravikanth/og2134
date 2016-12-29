package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.Lng;
import com.oganalysis.entities.LngFilter;

public interface LngDao {
	List<Lng> getRegasificationCriteriaData(int startDate,int endDate);
	List<Lng> getLiquefactionCriteriaData(int startDate,int endDate);
			
	
	List<String> getLocations();
	List<String> getOperator();
	List<String> getOwners();
	
	
	List<Lng> getTerminalData(String terminalName,String type);
	
		
	List<String> getLiqueTerminals(int startDate,int endDate);
	List<String> getRegasTerminals(int startDate,int endDate);
	
	List<String> getSelectedCompanies(Map<String,List> selectedOptions,int startDate,int endDate,String type);
	List<String> getSelectedCountries(Map<String,List> selectedOptions,int startDate,int endDate,String type);
	List<String> getSelectedTerminals(Map<String,List> selectedOptions,int startDate,int endDate,String type);
	
	List<String> getSelectedTerminals(Map<String,List> selectedOptions,String type);
	
	
	List<String> getCompanyTerminals(String company,int startDate,int endDate,String type);
	List<String> getCountryTerminals(String country,int startDate,int endDate,String type);
	
	List<String> getCompanyTerminals(String company,String type);
	
	List<LngFilter> getTerminalCompanies(String terminal);
	
	
	List<LngFilter> getLngFilter();
	
//	List<String> getTerminalsInDateRange(int startDate,int endDate,List<String> terminals);
	
}
