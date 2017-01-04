package com.oganalysis.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oganalysis.service.SupplyDemandService;

@Controller
public class SupplyDemandController {
	
	@Autowired
	private SupplyDemandService supplyDemandServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/supplyDemand",method={RequestMethod.GET})
	public String getSupplyDemand(HttpServletRequest req)
	{
		String startDate=req.getParameter("startDate");//"2014";
		String endDate=req.getParameter("endDate");//"2020";
		String displayType=req.getParameter("displayType");//export";
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
		String response=supplyDemandServiceImpl.getSupplyDemandData(selectedOptions, startDate, endDate, displayType);
		return response;
	}
	private Map<String,List<String>> getSelectedOptionsData(HttpServletRequest request)
	{
		Enumeration<String> selectedOptions=request.getParameterNames();
		Map<String,List<String>> optionsMap=new HashMap<String,List<String>>();
		List<String> selectedCountries=new ArrayList<String>();
		List<String> selectedRegions=new ArrayList<String>();
					
		while(selectedOptions.hasMoreElements())
		{
			String option=selectedOptions.nextElement();
			if(option.contains("country"))			
				selectedCountries.add(request.getParameter(option));			
			else if(option.contains("region"))			
				selectedRegions.add(request.getParameter(option));			
					
		}
		
			optionsMap.put("countries", selectedCountries);
			optionsMap.put("regions",selectedRegions);
						
									
		return optionsMap;
	}
}
