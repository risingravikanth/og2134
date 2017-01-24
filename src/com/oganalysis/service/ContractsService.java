package com.oganalysis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.oganalysis.helper.LngJsonResponse;

public interface ContractsService {
	public String getContractsData(Map<String,List<String>> selectedOptions,String startDate,String endDate,String displayType);
	
	
	public String getModalQuantityData(Map<String, List<String>> selectedOptions,String startDate, String endDate, String displayType,String recordName); 
	
	public String getImportCountries(List<String> exportCountries);
	
	public String getImportCompanies(List<String> exportCompanies);
	
	public String getExportCompanies();
}
