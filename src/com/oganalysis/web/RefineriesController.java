package com.oganalysis.web;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
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
	@Autowired
	private ServletContext servletContext;
	
	@ResponseBody
	@RequestMapping(value="/capacity",method={RequestMethod.GET})
	public String getRefineriesCapacityData(HttpServletRequest req)
	{		
		String response=LOGIN;
		if(null!=req.getSession().getAttribute(EMAIL))
		{
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
			String startDate=req.getParameter(STARTDATE);
			String endDate=req.getParameter(ENDDATE);		
			String displayType=req.getParameter(DISPLAYTYPE);		
			
			response=refineriesServiceImpl.getRefineriesData(selectedOptions,startDate,endDate,displayType);
		}			
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/modalcapacity",method={RequestMethod.GET})
	public String getRefineriesCapacityModalData(HttpServletRequest req)
	{
		
		String response=LOGIN;
		if(null!=req.getSession().getAttribute(EMAIL))
		{
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);				
			String startDate=req.getParameter(STARTDATE);
			String endDate=req.getParameter(ENDDATE);
			String displayType=req.getParameter(DISPLAYTYPE);
			String recordName=req.getParameter(RECORDNAME);				
			response=refineriesServiceImpl.getModalCapacityData(selectedOptions,startDate,endDate,displayType,recordName);
		}			
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/infrastructure",method={RequestMethod.GET})
	public String getRefineriesInfrastructureData(HttpServletRequest req)
	{
		
		String response=LOGIN;
		if(null!=req.getSession().getAttribute(EMAIL))
		{
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);			
			response=refineriesServiceImpl.getInfrastructureData(selectedOptions);
		}					
		return response;
	}
	@RequestMapping("/download/terminaldetails")
	public String downloadTerminalDetails(HttpServletRequest request, HttpServletResponse response)  {
		 		
		 String res=null;
		 Workbook workbook=null;
		 InputStream is=null;
		 OutputStream os=null;
	    if(null!=request.getSession().getAttribute(EMAIL))
	    {
	    	try {
	    		String recordName=request.getParameter(RECORDNAME);	
	            response.setContentType(EXCEL_CONTENT_TYPE);
	            response.setHeader(EXCEL_CONTENT_DISPOSITION, EXCEL_ATTACHMENT+recordName+EXCEL_FILE_EXTENSION);
	            is=servletContext.getResourceAsStream(EXCEL_LOGO);
	            os=response.getOutputStream();
	            workbook = refineriesServiceImpl.getExcelTerminalData(recordName,is);
	            workbook.write(os);
	        } catch (Exception e) {
	            return EXCEL_ERROR;
	        }
	    	finally
	    	{
	    		try
	    		{
	    			is.close();
	    			os.close();
	    			workbook.close();	
	    		}
	    		catch(Exception e)
	    		{
	    			return res;
	    		}
	    	}
	    }	
	    else
	    {
	    	return "redirect:/";
	    }
	   return res;
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
