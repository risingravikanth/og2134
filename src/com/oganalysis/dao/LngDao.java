package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.Lng;

public interface LngDao {
	List<Lng> getRegasificationCriteriaData(Map<String,List> selectedOptions,int startDate,int endDate);
	List<Lng> getLiquefactionCriteriaData(Map<String,List> selectedOptions,int startDate,int endDate);
	
	List<Lng> getLiquefactionCriteriaData(Map<String,List> selectedOptions);
	List<Lng> getRegasificationCriteriaData(Map<String,List> selectedOptions);
	
	
	List<String> getLocations();
	List<String> getOperator();
	List<String> getOwners();
//	List<Lng> getLngData();
	
	List<Lng> getLiquefactionData();
	List<Lng> getRegasificationData();
	
	List<Lng> getTerminalData(String terminalName,String type);
	
}
