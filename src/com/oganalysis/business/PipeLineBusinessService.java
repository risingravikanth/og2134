package com.oganalysis.business;

import java.util.List;
import java.util.Map;

public interface PipeLineBusinessService {
	public Map<String,Double> getDomesticLengthByCountry(Map<String,List<String>> selectedOptions);
	public Map<String,Double> getDomesticLengthByCompany(Map<String,List<String>> selectedOptions);
	public Map<String,List<Map>> getDomesticPipeLines(Map<String,List<String>> selectedOptions);
	
	public Map<String,Double> getTransNationalLengthByCountry(Map<String,List<String>> selectedOptions);
	public Map<String,Double> getTransNationalLengthByCompany(Map<String,List<String>> selectedOptions);
	public Map<String,List<Map>> getTransNationalPipeLines(Map<String,List<String>> selectedOptions);
	
	public List<String> getRegions();	
	public List<String> getCountries();	
	public List<String> getCommodities();
	public List<String> getStartPoints();
	public List<String> getEndPoints();
	public List<String> getStatus();
}
