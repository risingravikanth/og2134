package com.oganalysis.web;

import static com.oganalysis.constants.ApplicationConstants.DISPLAYTYPE;
import static com.oganalysis.constants.ApplicationConstants.EMAIL;
import static com.oganalysis.constants.ApplicationConstants.ENDDATE;
import static com.oganalysis.constants.ApplicationConstants.LOGIN;
import static com.oganalysis.constants.ApplicationConstants.OPTION_COMPANY;
import static com.oganalysis.constants.ApplicationConstants.OPTION_COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.OPTION_LOCATION;
import static com.oganalysis.constants.ApplicationConstants.OPTION_REGION;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_COMPANIES;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_COUNTRIES;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_LOCATIONS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_REGIONS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_TECHNOLOGIES;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_TECHNOLOGYPROVIDERS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_STATUS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_TECHNOLOGY;
import static com.oganalysis.constants.ApplicationConstants.OPTION_TECHNOLOGYPROVIDER;
import static com.oganalysis.constants.ApplicationConstants.OPTION_TYPE;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_TYPES;
import static com.oganalysis.constants.ApplicationConstants.STARTDATE;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_STATUSES;

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

import com.oganalysis.service.SmallScaleLngService;

@Controller
@RequestMapping("/smallscalelng")
public class SmallScaleLngController {
	
	@Autowired
	private SmallScaleLngService smallScaleLngServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/asset",method={RequestMethod.GET})
	public String getSmallScaleLngData(HttpServletRequest req)
	{		
		String response=LOGIN;		
//		if(null!=req.getSession().getAttribute(EMAIL))
//		{
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);									
			response=smallScaleLngServiceImpl.getSmallScaleLngData(selectedOptions);
//		}						
		return response;
	}
	private Map<String,List<String>> getSelectedOptionsData(HttpServletRequest request)
	{
		Enumeration<String> selectedOptions=request.getParameterNames();
		Map<String,List<String>> optionsMap=new HashMap<String,List<String>>();
		List<String> selectedRegions=new ArrayList<String>();
		List<String> selectedCountries=new ArrayList<String>();
		List<String> selectedLocations=new ArrayList<String>();
		List<String> selectedCompanies=new ArrayList<String>();
		List<String> selectedTechnologyproviders=new ArrayList<String>();
		List<String> selectedTechnologies=new ArrayList<String>();
		List<String> selectedStatuses=new ArrayList<String>();
		List<String> selectedTypes=new ArrayList<String>();
					
		while(selectedOptions.hasMoreElements())
		{
			String option=selectedOptions.nextElement();
						
			if(option.contains(OPTION_REGION))			
				selectedRegions.add(request.getParameter(option));	
			else if(option.contains(OPTION_COUNTRY))			
				selectedCountries.add(request.getParameter(option));
			else if(option.contains(OPTION_LOCATION))
				selectedLocations.add(request.getParameter(option));
			else if(option.contains(OPTION_COMPANY))
				selectedCompanies.add(request.getParameter(option));
			else if(option.contains(OPTION_TECHNOLOGYPROVIDER))
				selectedTechnologyproviders.add(request.getParameter(option));
			else if(option.contains(OPTION_TECHNOLOGY))
				selectedTechnologies.add(request.getParameter(option));
			else if(option.contains(OPTION_STATUS))
				selectedStatuses.add(request.getParameter(option));
			else if(option.contains(OPTION_TYPE))
				selectedTypes.add(request.getParameter(option));
			
		}				
			optionsMap.put(OPTION_SELECTED_REGIONS,selectedRegions);
			optionsMap.put(OPTION_SELECTED_COUNTRIES, selectedCountries);			
			optionsMap.put(OPTION_SELECTED_LOCATIONS,selectedLocations);
			optionsMap.put(OPTION_SELECTED_COMPANIES,selectedCompanies);
			optionsMap.put(OPTION_SELECTED_TECHNOLOGYPROVIDERS,selectedTechnologyproviders);
			optionsMap.put(OPTION_SELECTED_TECHNOLOGIES,selectedTechnologies);
			optionsMap.put(OPTION_SELECTED_STATUSES,selectedStatuses);
			optionsMap.put(OPTION_SELECTED_TYPES,selectedTypes);
				
									
		return optionsMap;
	}
	@ResponseBody
	@RequestMapping(value="/regions",method={RequestMethod.GET})
	public String getRegions()
	{										
			return smallScaleLngServiceImpl.getRegions();
	}
	@ResponseBody
	@RequestMapping(value="/countries",method={RequestMethod.GET})
	public String getCountries()
	{										
			return smallScaleLngServiceImpl.getCountries();
	}
	@ResponseBody
	@RequestMapping(value="/locations",method={RequestMethod.GET})
	public String getLocations()
	{										
			return smallScaleLngServiceImpl.getLocations();
	}
	@ResponseBody
	@RequestMapping(value="/companies",method={RequestMethod.GET})
	public String getCompanies()
	{										
			return smallScaleLngServiceImpl.getCompanies();
	}
	@ResponseBody
	@RequestMapping(value="/technologyproviders",method={RequestMethod.GET})
	public String getTechnologyProviders()
	{										
			return smallScaleLngServiceImpl.getTechnologyProviders();
	}
	@ResponseBody
	@RequestMapping(value="/technologies",method={RequestMethod.GET})
	public String getTechnologies()
	{										
			return smallScaleLngServiceImpl.getTechnologies();
	}
	@ResponseBody
	@RequestMapping(value="/statuses",method={RequestMethod.GET})
	public String getStatuses()
	{										
			return smallScaleLngServiceImpl.getStatuses();
	}
	@ResponseBody
	@RequestMapping(value="/types",method={RequestMethod.GET})
	public String getTypes()
	{										
			return smallScaleLngServiceImpl.getTypes();
	}
}
