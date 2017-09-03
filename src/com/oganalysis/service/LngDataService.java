package com.oganalysis.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

public interface LngDataService {
	   public String getCapacityData(Map<String,List<String>> selectedOptions,String startDate,String endDate,String displayType);
	   public String getModalCapacityData(Map<String,List<String>> selectedOptions,String startDate,String endDate,String displayType,String type,String recordName);
	   public String getInfrastructureData(Map<String,List<String>> selectedOptions);
	   public Workbook getExcelTerminalData(String recordName,String type,InputStream is);
	  
}	
