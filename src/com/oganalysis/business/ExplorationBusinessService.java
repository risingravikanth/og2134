package com.oganalysis.business;

import java.util.List;
import java.util.Map;

public interface ExplorationBusinessService {
	public List<Map> getExplorationByBlockName(Map<String,List<String>> selectedOptions);
	
	public List<String> getRegions();	
	public List<String> getCountries(Map<String,List<String>> selectedOptions);	
	public List<String> getBasins(Map<String,List<String>> selectedOptions);
	public List<String> getOwners(Map<String,List<String>> selectedOptions);
	public List<String> getOperators(Map<String,List<String>> selectedOptions);
	public List<String> getStatus(Map<String,List<String>> selectedOptions);
	public List<String> getType(Map<String,List<String>> selectedOptions);
	
}
