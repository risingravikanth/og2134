package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.Lng;
import com.oganalysis.entities.LngFilter;

public interface LngDao {
	
	
	List<Lng> getRegasificationCriteriaData(Map<String,List<String>> selectedOptions,List<String> terminals,int startDate,int endDate);
	
	List<Lng> getLiquefactionCriteriaData(Map<String,List<String>> selectedOptions,List<String> terminals,int startDate,int endDate);
			
//	List<Lng> getRegasificationCriteriaData(List<String> terminals);
//	List<Lng> getLiquefactionCriteriaData(List<String> terminals);
	
	List<String> getLiqueTerminals(int startDate,int endDate);
	List<String> getRegasTerminals(int startDate,int endDate);
	
	List<String> getLocations();
	List<String> getOperator();
	List<String> getOwners();
	
	
//	List<Integer> getSelectedYears(int startDate,int endDate,String type);
	
	List<Lng> getTerminalData(String terminalName,String type);
			
	
	List<String> getSelectedCompanies(Map<String,List<String>> selectedOptions,int startDate,int endDate,String type);
	List<String> getSelectedCountries(Map<String,List<String>> selectedOptions,int startDate,int endDate,String type);
	List<String> getSelectedTerminals(Map<String,List<String>> selectedOptions,int startDate,int endDate,String type);
	
	List<String> getSelectedTerminals(Map<String,List<String>> selectedOptions,String type);
	
	
//	List<String> getCompanyTerminals(String company,Map<String,List> selectedOptions,int startDate,int endDate,String type);
//	List<String> getCountryTerminals(String country,Map<String,List> selectedOptions,int startDate,int endDate,String type);
	
	List<String> getCompanyTerminals(String company,List<String> terminals,String type);
	List<String> getCountryTerminals(String country,List<String> terminals,String type);
	
//	List<String> getCompanyTerminals(String company,Map<String,List> selectedOptions,String type);
	
	List<LngFilter> getTerminalCompanies(String terminal,String type);
	
	
	List<LngFilter> getLngFilter(String type);
	
//	List<String> getTerminalsInDateRange(int startDate,int endDate,List<String> terminals);
	
}
