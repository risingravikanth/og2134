package com.oganalysis.web;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oganalysis.service.LngDataService;
import com.oganalysis.service.impl.LngDataServiceImpl;
@Controller
//@RequestMapping("/lng")
public class LngDataDisplayController {
	
	@Autowired
	private LngDataService lngDataServiceImpl;
	
	@Autowired
	private LngDataServiceImpl lngDataServiceImpl1;
	@Autowired
	private ServletContext servletContext;
	
	@ResponseBody
	@RequestMapping(value="/capacity",method={RequestMethod.GET})
	public String getLngCapacityData(HttpServletRequest req)
	{				
		String response=LOGIN;		
		if(null!=req.getSession().getAttribute(EMAIL))
		{
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
			String startDate=req.getParameter(STARTDATE);
			String endDate=req.getParameter(ENDDATE);		
			String displayType=req.getParameter(DISPLAYTYPE);					
			response=lngDataServiceImpl.getCapacityData(selectedOptions,startDate,endDate,displayType);
		}						
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/infrastructure",method={RequestMethod.GET})
	public String getLngInfrastructureData(HttpServletRequest req)
	{
		
		String response=LOGIN;
		if(null!=req.getSession().getAttribute(EMAIL))
		{
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);								
			response=lngDataServiceImpl.getInfrastructureData(selectedOptions);
		}				
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/modalcapacity",method={RequestMethod.GET})
	public String getLngCapacityModalData(HttpServletRequest req)
	{
		
		String response=LOGIN;
		if(null!=req.getSession().getAttribute(EMAIL))
		{
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);				
			String startDate=req.getParameter(STARTDATE);
			String endDate=req.getParameter(ENDDATE);
			String displayType=req.getParameter(DISPLAYTYPE);
			String type=req.getParameter(TYPE);
			String recordName=req.getParameter(RECORDNAME);				
			response=lngDataServiceImpl.getModalCapacityData(selectedOptions,startDate,endDate,displayType,type,recordName);
		}			
		return response;
	}
	@RequestMapping("/download/terminaldetails")
	public String downloadTerminalDetails(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 		
	    String res=null;
	    if(null!=request.getSession().getAttribute(EMAIL))
	    {
	    	 try
	 	    {
	 	    	String type=request.getParameter(TYPE);
	 			String recordName=request.getParameter(RECORDNAME);	
	            response.setContentType(EXCEL_CONTENT_TYPE);
	            response.setHeader(EXCEL_CONTENT_DISPOSITION, EXCEL_ATTACHMENT+recordName+EXCEL_FILE_EXTENSION);
	            InputStream is=servletContext.getResourceAsStream("/WEB-INF/oglogo.jpg");
	            Workbook workbook = lngDataServiceImpl.getExcelTerminalData(recordName,type,is);
	            workbook.write(response.getOutputStream());
	 	    }
	 	    catch(Exception e)
	 	    {
//	 	    	throw new ServletException("Exception in DownLoad Excel Servlet", e);
	 	    	return EXCEL_ERROR;
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
		List<String> selectedOffOnshores=new ArrayList<String>();
		List<String> selectedTypes=new ArrayList<String>();
		List<String> selectedUnits=new ArrayList<String>();
				
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
			else if(option.contains(OPTION_UNIT))
				selectedUnits.add(request.getParameter(option));
			else if(option.contains(OPTION_OFFONSHORE))
				selectedOffOnshores.add(request.getParameter(option));
			else if(option.contains(OPTION_TYPE))
				selectedTypes.add(request.getParameter(option));
			
		}
		
//			selectedUnits.add("BCF");
			optionsMap.put(OPTION_SELECTED_REGIONS,selectedRegions);
			optionsMap.put(OPTION_SELECTED_COUNTRIES, selectedCountries);			
			optionsMap.put(OPTION_SELECTED_LOCATIONS,selectedLocations);
			optionsMap.put(OPTION_SELECTED_OPERATORS,selectedOperators);
			optionsMap.put(OPTION_SELECTED_OWNERS,selectedOwners);
			optionsMap.put(OPTION_SELECTED_STATUSES,selectedStatuses);
			optionsMap.put(OPTION_SELECTED_UNITS,selectedUnits);
			optionsMap.put(OPTION_SELECTED_OFFONSHORES,selectedOffOnshores);
			optionsMap.put(OPTION_SELECTED_TYPES,selectedTypes);
			
									
		return optionsMap;
	}
	
}
