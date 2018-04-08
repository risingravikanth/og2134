package com.oganalysis.service;

import java.util.List;
import java.util.Map;

public interface ExplorationService {
	public String getExplorationData(Map<String,List<String>> selectedOptions,String displayType);
	public String getRegions();
	public String getCountries();	
	public String getBasins();	
	public String getOwners();	
	public String getOperators();	
	public String getStatus();	
	public String getType();
}
