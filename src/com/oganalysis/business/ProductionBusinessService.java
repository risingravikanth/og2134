package com.oganalysis.business;

import java.util.List;
import java.util.Map;

public interface ProductionBusinessService {
	public List<Map<String,String>> getNaturalGasCapacityByField(Map<String,List<String>> selectedOptions);
	public List<Map<String,String>> getCrudeOilCapacityByField(Map<String,List<String>> selectedOptions);
	public List<Map<String,String>> getNaturalGasCapacityByCountry(Map<String,List<String>> selectedOptions);
	public List<Map<String,String>> getCrudeOilCapacityByCountry(Map<String,List<String>> selectedOptions);
	
	public List<Map<String,String>> getCompanyOilGasCapacity(String country,String filterType);
}
