package com.oganalysis.service;

import java.util.List;
import java.util.Map;

public interface PipeLineService {
	
	public String getDomesticData(Map<String,List<String>> selectedOptions,String displayType);
	public String getTransNationalData(Map<String,List<String>> selectedOptions,String displayType);
}
