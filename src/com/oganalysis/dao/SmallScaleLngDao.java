package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.SmallScaleLng;

public interface SmallScaleLngDao {
	public List<SmallScaleLng> getSelectedSmallScaleLng(Map<String, List<String>> selectedOptions);
	public List<String> getRegions();
	public List<String> getCountries(Map<String,List<String>> selectedOptions);
	public List<String> getLocations(Map<String,List<String>> selectedOptions);
	public List<String> getCompanies(Map<String,List<String>> selectedOptions);
	public List<String> getTechnologyProviders(Map<String,List<String>> selectedOptions);
	public List<String> getTechnologies(Map<String,List<String>> selectedOptions);
	public List<String> getStatuses(Map<String,List<String>> selectedOptions);
	public List<String> getTypes(Map<String,List<String>> selectedOptions);
	
}
