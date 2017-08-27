package com.oganalysis.service;

import java.util.List;
import java.util.Map;

public interface ExplorationService {
	public String getExplorationData(Map<String,List<String>> selectedOptions,String displayType);
}
