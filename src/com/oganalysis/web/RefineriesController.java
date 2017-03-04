package com.oganalysis.web;

import static com.oganalysis.constants.ApplicationConstants.*;


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

import com.oganalysis.service.RefineriesService;

@Controller
@RequestMapping("/refineries")
public class RefineriesController {
	
	@Autowired
	private RefineriesService refineriesServiceImpl;
	
	
	@ResponseBody
	@RequestMapping(value="/capacity",method={RequestMethod.GET})
	public String getRefineriesCapacityData(HttpServletRequest req)
	{		
		String response=null;
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
		String startDate=req.getParameter(STARTDATE);
		String endDate=req.getParameter(ENDDATE);		
		String displayType=req.getParameter(DISPLAYTYPE);		
		
		response=refineriesServiceImpl.getRefineriesData(selectedOptions,startDate,endDate,displayType);
		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/modalcapacity",method={RequestMethod.GET})
	public String getRefineriesCapacityModalData(HttpServletRequest req)
	{
		
		String response=null;
		
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);				
		String startDate=req.getParameter(STARTDATE);
		String endDate=req.getParameter(ENDDATE);
		String displayType=req.getParameter(DISPLAYTYPE);
		String recordName=req.getParameter(RECORDNAME);				
		response=refineriesServiceImpl.getModalCapacityData(selectedOptions,startDate,endDate,displayType,recordName);
		
		return response;
	}
	private Map<String,List<String>> getSelectedOptionsData(HttpServletRequest request)
	{
		Enumeration<String> selectedOptions=request.getParameterNames();
		Map<String,List<String>> optionsMap=new HashMap<String,List<String>>();
		List<String> selectedCountries=new ArrayList<String>();
		List<String> selectedRegions=new ArrayList<String>();
		List<String> selectedLocations=new ArrayList<String>();
		List<String> selectedOperators=new ArrayList<String>();
		List<String> selectedOwners=new ArrayList<String>();
		List<String> selectedStatuses=new ArrayList<String>();
					
		while(selectedOptions.hasMoreElements())
		{
			String option=selectedOptions.nextElement();
						
			if(option.contains(OPTION_REGION))			
				selectedRegions.add(request.getParameter(option));	
			else if(option.contains(OPTION_COUNTRY))			
				selectedCountries.add(request.getParameter(option));
			else if(option.contains(OPTION_LOCATION))
				selectedLocations.add(request.getParameter(option));
			else if(option.contains(OPTION_OPERATOR))
				selectedOperators.add(request.getParameter(option));
			else if(option.contains(OPTION_OWNER))
				selectedOwners.add(request.getParameter(option));			
			else if(option.contains(OPTION_STATUS))
				selectedStatuses.add(request.getParameter(option));			
			
		}				
			optionsMap.put(OPTION_SELECTED_REGIONS,selectedRegions);
			optionsMap.put(OPTION_SELECTED_COUNTRIES, selectedCountries);			
			optionsMap.put(OPTION_SELECTED_LOCATIONS,selectedLocations);
			optionsMap.put(OPTION_SELECTED_OPERATORS,selectedOperators);
			optionsMap.put(OPTION_SELECTED_OWNERS,selectedOwners);
			optionsMap.put(OPTION_SELECTED_STATUSES,selectedStatuses);
				
									
		return optionsMap;
	}

}
