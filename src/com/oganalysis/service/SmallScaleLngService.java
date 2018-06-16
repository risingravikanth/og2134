package com.oganalysis.service;

import java.util.List;
import java.util.Map;

public interface SmallScaleLngService {
	public String getSmallScaleLngData(Map<String,List<String>> selectedOptions);
	public String getRegions();
	public String getCountries(Map<String,List<String>> selectedOptions);
	public String getLocations(Map<String,List<String>> selectedOptions);
	public String getCompanies(Map<String,List<String>> selectedOptions);
	public String getTechnologyProviders(Map<String,List<String>> selectedOptions);
	public String getTechnologies(Map<String,List<String>> selectedOptions);
	public String getStatuses(Map<String,List<String>> selectedOptions);
	public String getTypes(Map<String,List<String>> selectedOptions);
}
