package com.oganalysis.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public interface PipeLineService {
	
	public String getDomesticData(Map<String,List<String>> selectedOptions,String displayType);
	public String getTransNationalData(Map<String,List<String>> selectedOptions,String displayType);
	
	
	public String getRegions();	
	public String getCountries(Map<String,List<String>> selectedOptions);	
	public String getCommodities(Map<String,List<String>> selectedOptions);
	public String getStartPoints(Map<String,List<String>> selectedOptions);
	public String getEndPoints(Map<String,List<String>> selectedOptions);
	public String getStatus(Map<String,List<String>> selectedOptions);	
}
