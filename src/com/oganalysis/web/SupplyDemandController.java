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

import static com.oganalysis.constants.ApplicationConstants.*;

import com.oganalysis.service.SupplyDemandService;

@Controller
@RequestMapping("/supplyDemand")
public class SupplyDemandController {
	
	@Autowired
	private SupplyDemandService supplyDemandServiceImpl;
	
	@ResponseBody
	@RequestMapping(method={RequestMethod.POST})
	public String getSupplyDemand(HttpServletRequest req)
	{
		String response=LOGIN;
		if(null!=req.getSession().getAttribute(EMAIL))
		{
			String startDate=req.getParameter(STARTDATE);//"2014";
			String endDate=req.getParameter(ENDDATE);//"2020";
			String displayType=req.getParameter(DISPLAYTYPE);//export";
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
			response=supplyDemandServiceImpl.getSupplyDemandData(selectedOptions, startDate, endDate, displayType);
		}		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/regions",method={RequestMethod.POST})
	public String getRegions()
	{					
		return supplyDemandServiceImpl.getRegions();				
	}	
	@ResponseBody
	@RequestMapping(value="/countries",method={RequestMethod.POST})
	public String getCountries(HttpServletRequest req)
	{					
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
		return supplyDemandServiceImpl.getCountries(selectedOptions);				
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
			if(option.contains(OPTION_COUNTRY))			
				selectedCountries.add(request.getParameter(option));			
			else if(option.contains(OPTION_REGION))			
				selectedRegions.add(request.getParameter(option));			
					
		}
		
			optionsMap.put(OPTION_SELECTED_COUNTRIES, selectedCountries);
			optionsMap.put(OPTION_SELECTED_REGIONS,selectedRegions);
						
									
		return optionsMap;
	}
}
