package com.oganalysis.service;

import java.util.List;
import java.util.Map;

public interface LngDataService {
	   public String getCapacityData(Map<String,List<String>> selectedOptions,String startDate,String endDate,String displayType);
	   public String getModalCapacityData(Map<String,List<String>> selectedOptions,String startDate,String endDate,String displayType,String type,String recordName);
	   public String getInfrastructureData(Map<String,List<String>> selectedOptions);
	  
}	
