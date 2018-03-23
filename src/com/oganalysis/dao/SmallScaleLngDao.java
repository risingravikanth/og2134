package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.SmallScaleLng;

public interface SmallScaleLngDao {
	public List<SmallScaleLng> getSelectedSmallScaleLng(Map<String, List<String>> selectedOptions);
	public List<String> getRegions();
	public List<String> getCountries();
	public List<String> getLocations();
	public List<String> getCompanies();
	public List<String> getTechnologyProviders();
	public List<String> getTechnologies();
	public List<String> getStatuses();
	public List<String> getTypes();
	
}
