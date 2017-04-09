package com.oganalysis.business;

import java.util.List;
import java.util.Map;

public interface LngInfraBusinessService {
	public List<Map<String,String>> getRegasificationInfrastructure(Map<String,List<String>> selectedOptions);
	public List<Map<String,String>> getLiquefactionInfrastructure(Map<String,List<String>> selectedOptions);
	public Map<String,Map<String,String>> createInfrastructure(List<String> terminals,String type);
}
