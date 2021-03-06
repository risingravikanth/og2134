package com.oganalysis.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public interface LngDataService {
	   public String getCapacityData(Map<String,List<String>> selectedOptions,String startDate,String endDate,String displayType);
	   public String getModalCapacityData(Map<String,List<String>> selectedOptions,String startDate,String endDate,String displayType,String type,String recordName);
	   public String getInfrastructureData(Map<String,List<String>> selectedOptions);
	   public Workbook getExcelTerminalData(String recordName,String type,InputStream is);
	   
	   
		public String getRegions();		
		public String getCountries(Map<String,List<String>> selectedOptions);		
		public String getStatus(Map<String,List<String>> selectedOptions);		
		public String getType(Map<String,List<String>> selectedOptions);		
		public String getLocations(Map<String,List<String>> selectedOptions);		
		public String getOperators(Map<String,List<String>> selectedOptions);		
		public String getOwners(Map<String,List<String>> selectedOptions);
		//Default
		public String getCountries();
		public String getStatus();		
		public String getType();		
		public String getLocations();		
		public String getOperators();		
		public String getOwners();
		
		
	  
}	
