package com.oganalysis.business;

import java.util.List;
import java.util.Map;

public interface StorageInfraBusinessService {
	public List<Map<String,String>> getInfrastructure(Map<String,List<String>> selectedOptions);	
	public Map<String,Map<String,String>> createInfrastructure(List<String> terminals);
}
