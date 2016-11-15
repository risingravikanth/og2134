package com.oganalysis.business;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oganalysis.entities.Lng;

public interface LngBusinessService {
	public Map<String,Map<Integer,Double>> getRegasificationCapacityByCountry(Map<String,List> selectedOptions,String startDate,String endDate);
	public Map<String,Map<Integer,Double>> getLiquefactionCapacityByCountry(Map<String,List> selectedOptions,String startDate,String endDate);
	
	public Map<String,Map<Integer,Double>> getRegasificationCapacityByTerminal(Map<String,List> selectedOptions,String startDate,String endDate);
	public Map<String,Map<Integer,Double>> getLiquefactionCapacityByTerminal(Map<String,List> selectedOptions,String startDate,String endDate);
	
	public Map<String,Map<Integer,Double>> getRegasificationCapacityByCompany(Map<String,List> selectedOptions,String startDate,String endDate);
	public Map<String,Map<Integer,Double>> getLiquefactionCapacityByCompany(Map<String,List> selectedOptions,String startDate,String endDate);
	
	public List<Map<String,String>> getRegasificationInfrastructure(Map<String,List> selectedOptions);
	public List<Map<String,String>> getLiquefactionInfrastructure(Map<String,List> selectedOptions);
	
	
}
