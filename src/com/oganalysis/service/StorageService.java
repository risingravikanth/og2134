package com.oganalysis.service;

import java.util.List;
import java.util.Map;

public interface StorageService {
	public String getStorageData(Map<String,List<String>> selectedOptions,String startYear,String endYear,String displayType);
	public String getModalCapacityData(Map<String,List<String>> selectedOptions,String startYear,String endYear,String displayType,String recordName);
	public String getInfrastructureData(Map<String,List<String>> selectedOptions);
}
