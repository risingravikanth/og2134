package com.oganalysis.business;

import java.util.List;
import java.util.Map;

public interface StorageCapacityBusinessService {
	public Map<String,Map<Integer,Double>> getCapacityByCompany(Map<String,List<String>> selectedOptions,int startYear,int endYear);
	public Map<String,Map<Integer,Double>> getCapacityByCountry(Map<String,List<String>> selectedOptions,int startYear,int endYear);
	public Map<String,Map<Integer,Double>> getCapacityByTerminal(Map<String,List<String>> selectedOptions,int startYear,int endYear);
	
	public Map<String,Map<Integer,Double>> getModalCapacityForRecord(Map<String,List<String>>selectedOptions,int startYear,int endYear,String displayType,String recordName);
//	public Map getTerminalData(String recordName);
}
