package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.PipeLine;

public interface PipeLineDao {
	public List<String> getRegions();	
	public List<String> getCountries();	
	public List<String> getCommodities();
	public List<String> getStartPoints();
	public List<String> getEndPoints();
	public List<String> getStatus();
	
	public List<PipeLine> getSelectedPipeLines(Map<String, List<String>> selectedOptions,String relation,String type);	
	public List<PipeLine> getNonBlankChildSelectedPipeLines(Map<String, List<String>> selectedOptions,String relation,String type);
}
