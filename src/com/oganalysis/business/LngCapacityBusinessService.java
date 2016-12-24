package com.oganalysis.business;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oganalysis.entities.Lng;

public interface LngCapacityBusinessService {
	public Map<String,Map<Integer,Double>> getRegasificationCapacityByCountry(Map<String,List> selectedOptions,String startDate,String endDate);
	public Map<String,Map<Integer,Double>> getLiquefactionCapacityByCountry(Map<String,List> selectedOptions,String startDate,String endDate);
	
	public Map<String,Map<Integer,Double>> getRegasificationCapacityByTerminal(Map<String,List> selectedOptions,String startDate,String endDate);
	public Map<String,Map<Integer,Double>> getLiquefactionCapacityByTerminal(Map<String,List> selectedOptions,String startDate,String endDate);
	
	public Map<String,Map<Integer,Double>> getRegasificationCapacityByCompany(Map<String,List> selectedOptions,String startDate,String endDate);
	public Map<String,Map<Integer,Double>> getLiquefactionCapacityByCompany(Map<String,List> selectedOptions,String startDate,String endDate);
	
	public Map<String,Map<Integer,Double>> getLiqueModalCapacityForRecord(Map<String, List> selectedOptions,String startDate, String endDate, String displayType,String recordName);
	public Map<String,Map<Integer,Double>> getRegasModalCapacityForRecord(Map<String, List> selectedOptions,String startDate, String endDate, String displayType,String recordName);
	
	public Map<String,String> getLiqueModalTerminalData(String recordName);
	public Map<String,String> getRegasModalTerminalData(String recordName);
	
	public Map<String,Set<String>> getCompanyTerminals();
	public Map<String,Set<String>> getOperatorTerminals();
}
