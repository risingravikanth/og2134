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

import com.oganalysis.service.PipeLineService;
@Controller
@RequestMapping("/pipeline")
public class PipeLineController {
	@Autowired
	private PipeLineService pipeLineServiceImpl;
		
	@ResponseBody
	@RequestMapping(value="/domestic/length",method={RequestMethod.POST})
	public String getDomesticData(HttpServletRequest req)
	{				
		String response=LOGIN;		
		if(null!=req.getSession().getAttribute(EMAIL))
		{
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);			
			String displayType=req.getParameter(DISPLAYTYPE);					
			response=pipeLineServiceImpl.getDomesticData(selectedOptions,displayType);
		}						
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/transnational/length",method={RequestMethod.POST})
	public String getTransNationalData(HttpServletRequest req)
	{				
		String response=LOGIN;		
		if(null!=req.getSession().getAttribute(EMAIL))
		{
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);			
			String displayType=req.getParameter(DISPLAYTYPE);					
			response=pipeLineServiceImpl.getTransNationalData(selectedOptions,displayType);
		}						
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/regions",method={RequestMethod.POST})
	public String getRegions()
	{					
		return pipeLineServiceImpl.getRegions();				
	}
	@ResponseBody
	@RequestMapping(value="/countries",method={RequestMethod.POST})
	public String getCountries(HttpServletRequest req)
	{					
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
		return pipeLineServiceImpl.getCountries(selectedOptions);				
	}	
	@ResponseBody
	@RequestMapping(value="/status",method={RequestMethod.POST})
	public String getStatus(HttpServletRequest req)
	{					
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
		return pipeLineServiceImpl.getStatus(selectedOptions);				
	}
	@ResponseBody
	@RequestMapping(value="/commodities",method={RequestMethod.POST})
	public String getPipeLineCommodities(HttpServletRequest req)
	{					
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
		return pipeLineServiceImpl.getCommodities(selectedOptions);				
	}
	@ResponseBody
	@RequestMapping(value="/startpoints",method={RequestMethod.POST})
	public String getPipeLineStartPoints(HttpServletRequest req)
	{					
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
		return pipeLineServiceImpl.getStartPoints(selectedOptions);				
	}
	@ResponseBody
	@RequestMapping(value="/endpoints",method={RequestMethod.POST})
	public String getPipeLineEndPoints(HttpServletRequest req)
	{					
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
		return pipeLineServiceImpl.getEndPoints(selectedOptions);				
	}
	private Map<String,List<String>> getSelectedOptionsData(HttpServletRequest request)
	{
		Enumeration<String> selectedOptions=request.getParameterNames();
		Map<String,List<String>> optionsMap=new HashMap<String,List<String>>();
		List<String> selectedCountries=new ArrayList<String>();
		List<String> selectedRegions=new ArrayList<String>();
		List<String> selectedStatuses=new ArrayList<String>();
		List<String> selectedStartPoints=new ArrayList<String>();
		List<String> selectedEndPoints=new ArrayList<String>();
		List<String> selectedCommodities=new ArrayList<String>();
				
		while(selectedOptions.hasMoreElements())
		{
			String option=selectedOptions.nextElement();
						
			if(option.contains(OPTION_REGION))			
				selectedRegions.add(request.getParameter(option));	
			else if(option.contains(OPTION_COUNTRY))			
				selectedCountries.add(request.getParameter(option));			
			else if(option.contains(OPTION_STATUS))
				selectedStatuses.add(request.getParameter(option));
			else if(option.contains(OPTION_COMMODITY))
				selectedCommodities.add(request.getParameter(option));
			else if(option.contains(OPTION_STARTPOINT))
				selectedStartPoints.add(request.getParameter(option));
			else if(option.contains(OPTION_ENDPOINT))
				selectedEndPoints.add(request.getParameter(option));
			
		}
		
//			selectedUnits.add("BCF");
			optionsMap.put(OPTION_SELECTED_REGIONS,selectedRegions);
			optionsMap.put(OPTION_SELECTED_COUNTRIES, selectedCountries);			
			optionsMap.put(OPTION_SELECTED_STATUSES,selectedStatuses);
			optionsMap.put(OPTION_SELECTED_COMMODITIES,selectedCommodities);
			optionsMap.put(OPTION_SELECTED_STARTPOINTS,selectedStartPoints);
			optionsMap.put(OPTION_SELECTED_ENDPOINTS,selectedEndPoints);
			
									
		return optionsMap;
	}
}
