package com.oganalysis.business;

import java.util.List;
import java.util.Map;

public interface SmallScaleLngBusinessService {
	public List<Map> getSmallScaleLng(Map<String,List<String>> selectedOptions);
	public List<String> getRegions();
	public List<String> getCountries(Map<String,List<String>> selectedOptions);
	public List<String> getLocations(Map<String,List<String>> selectedOptions);
	public List<String> getCompanies(Map<String,List<String>> selectedOptions);
	public List<String> getTechnologyProviders(Map<String,List<String>> selectedOptions);
	public List<String> getTechnologies(Map<String,List<String>> selectedOptions);
	public List<String> getStatuses(Map<String,List<String>> selectedOptions);
	public List<String> getTypes(Map<String,List<String>> selectedOptions);
}
