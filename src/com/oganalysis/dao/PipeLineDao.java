package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.PipeLine;

public interface PipeLineDao {
	public List<String> getRegions();	
	public List<String> getCountries(Map<String,List<String>> selectedOptions);	
	public List<String> getCommodities(Map<String,List<String>> selectedOptions);
	public List<String> getStartPoints(Map<String,List<String>> selectedOptions);
	public List<String> getEndPoints(Map<String,List<String>> selectedOptions);
	public List<String> getStatus(Map<String,List<String>> selectedOptions);
	
	public List<PipeLine> getSelectedPipeLines(Map<String, List<String>> selectedOptions,String relation,String type);	
	public List<PipeLine> getNonBlankChildSelectedPipeLines(Map<String, List<String>> selectedOptions,String relation,String type);
}
