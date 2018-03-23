package com.oganalysis.business;

import java.util.List;
import java.util.Map;

public interface SmallScaleLngBusinessService {
	public List<Map> getSmallScaleLng(Map<String,List<String>> selectedOptions);
	public List<String> getRegions();
	public List<String> getCountries();
	public List<String> getLocations();
	public List<String> getCompanies();
	public List<String> getTechnologyProviders();
	public List<String> getTechnologies();
	public List<String> getStatuses();
	public List<String> getTypes();
}
