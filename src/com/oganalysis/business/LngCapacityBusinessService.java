package com.oganalysis.business;

import java.util.List;
import java.util.Map;

public interface LngCapacityBusinessService {
	public Map<String,Map<Integer,Double>> getRegasificationCapacityByCountry(Map<String,List<String>> selectedOptions,String startDate,String endDate);
	public Map<String,Map<Integer,Double>> getLiquefactionCapacityByCountry(Map<String,List<String>> selectedOptions,String startDate,String endDate);
	
	public Map<String,Map<Integer,Double>> getRegasificationCapacityByTerminal(Map<String,List<String>> selectedOptions,String startDate,String endDate);
	public Map<String,Map<Integer,Double>> getLiquefactionCapacityByTerminal(Map<String,List<String>> selectedOptions,String startDate,String endDate);
	
	public Map<String,Map<Integer,Double>> getRegasificationCapacityByCompany(Map<String,List<String>> selectedOptions,String startDate,String endDate);
	public Map<String,Map<Integer,Double>> getLiquefactionCapacityByCompany(Map<String,List<String>> selectedOptions,String startDate,String endDate);
	
	public Map<String,Map<Integer,Double>> getLiqueModalCapacityForRecord(Map<String,List<String>> selectedOptions,String startDate, String endDate, String displayType,String recordName);
	public Map<String,Map<Integer,Double>> getRegasModalCapacityForRecord(Map<String,List<String>> selectedOptions,String startDate, String endDate, String displayType,String recordName);
	
	public Map getTerminalData(String recordName,String type);
	
	//Below is for filters
	
		List<String> getRegions();
		List<String> getCountries(Map<String,List<String>> selectedOptions); // selectedOptions has list of  selected Regions from filter.
		List<String> getStatus(Map<String,List<String>> selectedOptions);
		List<String> getType(Map<String,List<String>> selectedOptions);	
		List<String> getLocations(Map<String,List<String>> selectedOptions);
		List<String> getOperators(Map<String,List<String>> selectedOptions);
		List<String> getOwners(Map<String,List<String>> selectedOptions);
		//Default
		List<String> getCountries();
		List<String> getStatus();
		List<String> getType();	
		List<String> getLocations();
		List<String> getOperators();
		List<String> getOwners();
	

}
