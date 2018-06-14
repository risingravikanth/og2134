package com.oganalysis.service;

import java.util.List;
import java.util.Map;

public interface SupplyDemandService {
	public String getSupplyDemandData(Map<String,List<String>> selectedOptions,String StartDate,String endDate,String displayType);
	public String getRegions();	
	public String getCountries(Map<String,List<String>> selectedOptions);
	
}
