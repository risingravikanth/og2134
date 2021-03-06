package com.oganalysis.service;

import java.util.List;
import java.util.Map;

public interface ProductionService {
	public String getAssetData(Map<String,List<String>> selectedOptions,String startYear,String endYear,String displayType);
	public String getCompanyOilGasData(Map<String,List<String>> selectedOptions,String startYear,String endYear);
	public String getAssetRegions();
	public String getAssetCountries(Map<String,List<String>> selectedOptions);
	public String getCompanyCountries();
}
