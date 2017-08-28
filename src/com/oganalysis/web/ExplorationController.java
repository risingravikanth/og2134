package com.oganalysis.web;

import static com.oganalysis.constants.ApplicationConstants.DISPLAYTYPE;
import static com.oganalysis.constants.ApplicationConstants.EMAIL;
import static com.oganalysis.constants.ApplicationConstants.LOGIN;
import static com.oganalysis.constants.ApplicationConstants.OPTION_BASIN;
import static com.oganalysis.constants.ApplicationConstants.OPTION_COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.OPTION_OPERATOR;
import static com.oganalysis.constants.ApplicationConstants.OPTION_OWNER;
import static com.oganalysis.constants.ApplicationConstants.OPTION_REGION;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_BASINS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_COUNTRIES;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_OPERATORS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_OWNERS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_REGIONS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_STATUSES;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_TYPES;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_UNITS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_STATUS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_TYPE;
import static com.oganalysis.constants.ApplicationConstants.OPTION_UNIT;

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

import com.oganalysis.service.ExplorationService;

@Controller
@RequestMapping("/exploration")
public class ExplorationController {
	@Autowired
	private ExplorationService explorationServiceImpl;
//		
	@ResponseBody
	@RequestMapping(method={RequestMethod.GET})
	public String getExplorationData(HttpServletRequest req)
	{				
		String response=LOGIN;		
		if(null!=req.getSession().getAttribute(EMAIL))
		{
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);			
			String displayType=req.getParameter(DISPLAYTYPE);					
			response=explorationServiceImpl.getExplorationData(selectedOptions,displayType);
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
		List<String> selectedTypes=new ArrayList<String>();
		List<String> selectedUnits=new ArrayList<String>();
				
		while(selectedOptions.hasMoreElements())
		{
			String option=selectedOptions.nextElement();
						
			if(option.contains(OPTION_REGION))			
				selectedRegions.add(request.getParameter(option));	
			else if(option.contains(OPTION_COUNTRY))			
				selectedCountries.add(request.getParameter(option));
			else if(option.contains(OPTION_BASIN))
				selectedLocations.add(request.getParameter(option));
			else if(option.contains(OPTION_OPERATOR))
				selectedOperators.add(request.getParameter(option));
			else if(option.contains(OPTION_OWNER))
				selectedOwners.add(request.getParameter(option));			
			else if(option.contains(OPTION_STATUS))
				selectedStatuses.add(request.getParameter(option));
			else if(option.contains(OPTION_UNIT))
				selectedUnits.add(request.getParameter(option));
			else if(option.contains(OPTION_TYPE))
				selectedTypes.add(request.getParameter(option));
			
		}
		
			
			optionsMap.put(OPTION_SELECTED_REGIONS,selectedRegions);
			optionsMap.put(OPTION_SELECTED_COUNTRIES, selectedCountries);			
			optionsMap.put(OPTION_SELECTED_BASINS,selectedLocations);
			optionsMap.put(OPTION_SELECTED_OPERATORS,selectedOperators);
			optionsMap.put(OPTION_SELECTED_OWNERS,selectedOwners);
			optionsMap.put(OPTION_SELECTED_STATUSES,selectedStatuses);
			optionsMap.put(OPTION_SELECTED_UNITS,selectedUnits);			
			optionsMap.put(OPTION_SELECTED_TYPES,selectedTypes);
			
									
		return optionsMap;
	}
}
