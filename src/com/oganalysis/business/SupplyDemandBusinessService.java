package com.oganalysis.business;

import java.util.List;
import java.util.Map;

public interface SupplyDemandBusinessService {
	public List<Map<String,String>> getSupplyDemand(Map<String,List<String>> selectedOptions,String displayType);
	
	public List<String> getRegions();
	public List<String> getCountries(); 
}
