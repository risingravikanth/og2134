package com.oganalysis.business;

import java.util.List;
import java.util.Map;

public interface ContractsBusinessService {
	
		
	public Map<String,Map<Integer,Float>> getQuantityByCompany(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	public Map<String,Map<Integer,Float>> getQuantityByCountry(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	public Map<String,Map<Integer,Float>> getQuantityByTerminal(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	
	public List<String> getImportCountries(List<String> exportCountries);
	public List<String> getImportCompanies(List<String> exportCompanies);
	
	public Map<String,Map<Integer,Float>> getQuantitiesForRecord(Map<String,List<String>>selectedOptions,int startDate, int endDate,String displayType,String recordName);
}
