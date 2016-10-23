package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

public interface OGDao {
	public List<Object> getOGAnalysisCriteriaData(Map<String,List> selectedOptions);
	public List<Object> getOGAnalysisData();
}
