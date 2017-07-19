package com.oganalysis.service;

import java.util.List;
import java.util.Map;

public interface ProductionService {
	public String getAssetData(Map<String,List<String>> selectedOptions,String startYear,String endYear,String displayType,String filterType);
	public String getCompanyOilGasData(String country,String startYear,String endYear,String filterType);
}
