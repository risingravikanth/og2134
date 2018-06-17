package com.oganalysis.service;

import java.util.List;
import java.util.Map;

public interface ExplorationService {
	public String getExplorationData(Map<String,List<String>> selectedOptions,String displayType);
	public String getRegions();
	public String getCountries(Map<String,List<String>> selectedOptions);	
	public String getBasins(Map<String,List<String>> selectedOptions);	
	public String getOwners(Map<String,List<String>> selectedOptions);	
	public String getOperators(Map<String,List<String>> selectedOptions);	
	public String getStatus(Map<String,List<String>> selectedOptions);	
	public String getType(Map<String,List<String>> selectedOptions);
}
