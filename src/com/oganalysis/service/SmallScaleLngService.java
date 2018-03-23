package com.oganalysis.service;

import java.util.List;
import java.util.Map;

public interface SmallScaleLngService {
	public String getSmallScaleLngData(Map<String,List<String>> selectedOptions);
	public String getRegions();
	public String getCountries();
	public String getLocations();
	public String getCompanies();
	public String getTechnologyProviders();
	public String getTechnologies();
	public String getStatuses();
	public String getTypes();
}
