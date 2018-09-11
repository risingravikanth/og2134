package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.SupplyDemandExport;
import com.oganalysis.entities.SupplyDemandImport;

public interface SupplyDemandDao {
	public List<SupplyDemandImport> getSupplyDemandImport(Map<String,List<String>> selectedOptions);
	public List<SupplyDemandExport> getSupplyDemandExport(Map<String,List<String>> selectedOptions);
	
	public List<String> getRegions();	
	public List<String> getCountries(Map<String, List<String>> selectedOptions);
	public List<String> getCountries();
}
