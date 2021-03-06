package com.oganalysis.business;

import java.util.List;
import java.util.Map;

public interface RefineriesCapacityBusinessService {
	public Map<String,Map<Integer,Double>> getCapacityByCompany(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	public Map<String,Map<Integer,Double>> getCapacityByCountry(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	public Map<String,Map<Integer,Double>> getCapacityByTerminal(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	
	public Map<String,Map<Integer,Double>> getModalCapacityForRecord(Map<String,List<String>>selectedOptions,int startDate,int endDate,String displayType,String recordName);
	public Map getTerminalData(String recordName);
	public List<String> getRegions();
	public List<String> getCountries(Map<String,List<String>> selectedOptions);
	public List<String> getStatus(Map<String,List<String>> selectedOptions);
	public List<String> getLocations(Map<String,List<String>> selectedOptions);
	public List<String> getOperators(Map<String,List<String>> selectedOptions);
	public List<String> getOwners(Map<String,List<String>> selectedOptions);
}
