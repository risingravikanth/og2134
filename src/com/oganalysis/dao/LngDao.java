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
	List<Lng> getLngData();
}
