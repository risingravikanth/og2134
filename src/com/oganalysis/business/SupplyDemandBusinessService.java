package com.oganalysis.business;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.SupplyDemand;

public interface SupplyDemandBusinessService {
	public List<Map<String,String>> getSupplyDemand(Map<String,List<String>> selectedOptions,String displayType);
}
