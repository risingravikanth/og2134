package com.oganalysis.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public interface SupplyDemandService {
	public String getSupplyDemandData(Map<String,List<String>> selectedOptions,String StartDate,String endDate,String displayType);
	public String getRegions();	
	public String getCountries();
	
}
