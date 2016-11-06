package com.oganalysis.business;

import java.util.List;
import java.util.Map;

public interface LngBusinessService {
	public Map<String,Map<Integer,Double>> getRegasificationCapacityByCountry(Map<String,List> selectedOptions,String startDate,String endDate);
	public Map<String,Map<Integer,Double>> getLiquefactionCapacityByCountry(Map<String,List> selectedOptions,String startDate,String endDate);
}
