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

import com.oganalysis.service.StorageService;
@Controller
@RequestMapping("/storage")
public class StorageController {

	
	@Autowired
	private StorageService storageServiceImpl;
	
	
	@ResponseBody
	@RequestMapping(value="/capacity",method={RequestMethod.GET})
	public String getStorageCapacityData(HttpServletRequest req)
	{		
		String response=LOGIN;
		if(null!=req.getSession().getAttribute(EMAIL))
		{
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
			String startYear=req.getParameter(STARTDATE);
			String endYear=req.getParameter(ENDDATE);		
			String displayType=req.getParameter(DISPLAYTYPE);		
			
			response=storageServiceImpl.getStorageData(selectedOptions,startYear,endYear,displayType);
		}			
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/modalcapacity",method={RequestMethod.GET})
	public String getStorageCapacityModalData(HttpServletRequest req)
	{
		
		String response=LOGIN;
		if(null!=req.getSession().getAttribute(EMAIL))
		{
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);				
			String startYear=req.getParameter(STARTDATE);
			String endYear=req.getParameter(ENDDATE);
			String displayType=req.getParameter(DISPLAYTYPE);
			String recordName=req.getParameter(RECORDNAME);				
			response=storageServiceImpl.getModalCapacityData(selectedOptions,startYear,endYear,displayType,recordName);
		}			
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/infrastructure",method={RequestMethod.GET})
	public String getStorageInfrastructureData(HttpServletRequest req)
	{
		
		String response=LOGIN;
		if(null!=req.getSession().getAttribute(EMAIL))
		{
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);			
			response=storageServiceImpl.getInfrastructureData(selectedOptions);
		}					
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
