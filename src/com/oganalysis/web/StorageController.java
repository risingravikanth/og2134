package com.oganalysis.web;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.io.File;
import java.io.FileInputStream;
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
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
	@Autowired
	private ServletContext servletContext;
	
	@ResponseBody
	@RequestMapping(value="/capacity",method={RequestMethod.POST})
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
	@RequestMapping(value="/modalcapacity",method={RequestMethod.POST})
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
	@RequestMapping(value="/infrastructure",method={RequestMethod.POST})
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
	@ResponseBody
	@RequestMapping(value="/regions",method={RequestMethod.POST})
	public String getRegions()
	{					
		return storageServiceImpl.getRegions();				
	}
	@ResponseBody
	@RequestMapping(value="/countries",method={RequestMethod.POST})
	public String getCountries(HttpServletRequest req)
	{					
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
		return storageServiceImpl.getCountries(selectedOptions);				
	}	
	@ResponseBody
	@RequestMapping(value="/status",method={RequestMethod.POST})
	public String getStatus(HttpServletRequest req)
	{					
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
		return storageServiceImpl.getStatus(selectedOptions);				
	}
	@ResponseBody
	@RequestMapping(value="/locations",method={RequestMethod.POST})
	public String getLocations(HttpServletRequest req)
	{					
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
		return storageServiceImpl.getLocations(selectedOptions);				
	}
	@ResponseBody
	@RequestMapping(value="/operators",method={RequestMethod.POST})
	public String getOperators(HttpServletRequest req)
	{					
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
		return storageServiceImpl.getOperators(selectedOptions);				
	}
	@ResponseBody
	@RequestMapping(value="/owners",method={RequestMethod.POST})
	public String getOwners(HttpServletRequest req)
	{					
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
		return storageServiceImpl.getOwners(selectedOptions);				
	}
	@RequestMapping("/download/terminaldetails")
	public String downloadTerminalDetails(HttpServletRequest request, HttpServletResponse response) {
		 		
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
	            os=response.getOutputStream();
	            is=servletContext.getResourceAsStream(EXCEL_LOGO);
	            workbook = storageServiceImpl.getExcelTerminalData(recordName,is);	            
	            workbook.write(os);	            
	            os.flush();
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
	    	return EXCEL_REDIRECT;
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
