package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.SupplyDemand;

public interface SupplyDemandDao {
	public List<SupplyDemand> getSupplyDemandImport(Map<String,List<String>> selectedOptions);
	public List<SupplyDemand> getSupplyDemandExport(Map<String,List<String>> selectedOptions);
	
	public List<String> getRegions();	
	public List<String> getCountries();
}
