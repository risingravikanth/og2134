package com.oganalysis.business;

import java.util.List;
import java.util.Map;

public interface ContractsBusinessService {
	
		
	public Map<String,Map<Integer,Double>> getQuantityByCompany(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	public Map<String,Map<Integer,Double>> getQuantityByCountry(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	public Map<String,Map<Integer,Double>> getQuantityByTerminal(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	
	public List<String> getImportCountries(List<String> exportCountries);
	public List<String> getImportCompanies(List<String> exportCompanies);
	public List<String> getExportCompanies();
	public List<String> getExportCountries();
	
//	public Map<String,Map<Integer,Double>> getQuantitiesForRecord(Map<String,List<String>>selectedOptions,int startDate, int endDate,String displayType,String recordName);
}
