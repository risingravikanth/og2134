package com.oganalysis.business;

import java.util.List;
import java.util.Map;

public interface ExplorationBusinessService {
	public List<Map> getExplorationByBlockName(Map<String,List<String>> selectedOptions);
}
