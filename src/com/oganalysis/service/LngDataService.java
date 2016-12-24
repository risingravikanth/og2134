package com.oganalysis.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LngDataService {
	   public String getCapacityData(Map<String,List> selectedOptions,String startDate,String endDate,String displayType);
	   public String getModalCapacityData(Map<String,List> selectedOptions,String startDate,String endDate,String displayType,String type,String recordName);
	   public String getInfrastructureData(Map<String,List> selectedOptions);
	   public Map<String,Set<String>> getCompanyTerminals();
	   public Map<String,Set<String>> getOperatorTerminals();
}	
