package com.oganalysis.web;

import static com.oganalysis.constants.ApplicationConstants.DISPLAYTYPE;
import static com.oganalysis.constants.ApplicationConstants.ENDDATE;
import static com.oganalysis.constants.ApplicationConstants.EXPORT_COMPANY;
import static com.oganalysis.constants.ApplicationConstants.EXPORT_COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.IMPORT_COMPANY;
import static com.oganalysis.constants.ApplicationConstants.IMPORT_COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_EXPORT_COMPANIES;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_EXPORT_COUNTRIES;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_IMPORT_COMPANIES;
import static com.oganalysis.constants.ApplicationConstants.OPTION_SELECTED_IMPORT_COUNTRIES;
import static com.oganalysis.constants.ApplicationConstants.STARTDATE;

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

import com.oganalysis.service.ContractsService;

@Controller
@RequestMapping("/contracts")
public class ContractsController {

	@Autowired
	private ContractsService contractsServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/quantity",method={RequestMethod.GET})
	public String getSupplyDemand(HttpServletRequest req)
	{
		String startDate=req.getParameter(STARTDATE);
		String endDate=req.getParameter(ENDDATE);
		String displayType=req.getParameter(DISPLAYTYPE);
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
		String response=contractsServiceImpl.getContractsData(selectedOptions, startDate, endDate, displayType);
		return response;
	}
//	@ResponseBody
//	@RequestMapping(value="/modalquantity",method={RequestMethod.GET})
//	public String getQuantityModalData(HttpServletRequest req)
//	{
//		
//		String response=null;
//		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);				
//		String startDate=req.getParameter("startDate");
//		String endDate=req.getParameter("endDate");
//		String displayType=req.getParameter("displayType");
//		String recordName=req.getParameter("recordName");				
//		response=contractsServiceImpl.getModalQuantityData(selectedOptions,startDate,endDate,displayType,recordName);
//		
//		return response;
//	}
	@ResponseBody
	@RequestMapping(value="/importcountries",method={RequestMethod.GET})
	public String getImportCountries(HttpServletRequest req)
	{
		
		String response=null;
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);				
		List<String> exportCountries=selectedOptions.get(OPTION_SELECTED_EXPORT_COUNTRIES);			
		response=contractsServiceImpl.getImportCountries(exportCountries);
		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/importcompanies",method={RequestMethod.GET})
	public String getImportCompanies(HttpServletRequest req)
	{
		
		String response=null;
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);				
		List<String> exportCompanies=selectedOptions.get(OPTION_SELECTED_EXPORT_COMPANIES);			
		response=contractsServiceImpl.getImportCompanies(exportCompanies);		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/exportcompanies",method={RequestMethod.GET})
	public String getExportCompanies(HttpServletRequest req)
	{
		
		String response=null;					
		response=contractsServiceImpl.getExportCompanies();		
		return response;
	}
	private Map<String,List<String>> getSelectedOptionsData(HttpServletRequest request)
	{
		Enumeration<String> selectedOptions=request.getParameterNames();
		Map<String,List<String>> optionsMap=new HashMap<String,List<String>>();
		List<String> selectedImportCountries=new ArrayList<String>();
		List<String> selectedExportCountries=new ArrayList<String>();
		
		List<String> selectedExportCompanies=new ArrayList<String>();
		List<String> selectedImportCompanies=new ArrayList<String>();
		while(selectedOptions.hasMoreElements())
		{
			String option=selectedOptions.nextElement();
			if(option.contains(IMPORT_COUNTRY))			
				selectedImportCountries.add(request.getParameter(option));			
			else if(option.contains(EXPORT_COUNTRY))			
				selectedExportCountries.add(request.getParameter(option));	
			else if(option.contains(IMPORT_COMPANY))
				selectedImportCompanies.add(request.getParameter(option));
			else if(option.contains(EXPORT_COMPANY))
				selectedExportCompanies.add(request.getParameter(option));
					
		}
		
			optionsMap.put(OPTION_SELECTED_IMPORT_COUNTRIES, selectedImportCountries);
			optionsMap.put(OPTION_SELECTED_EXPORT_COUNTRIES,selectedExportCountries);
			
			optionsMap.put(OPTION_SELECTED_IMPORT_COMPANIES,selectedImportCompanies);
			optionsMap.put(OPTION_SELECTED_EXPORT_COMPANIES,selectedExportCompanies);
						
									
		return optionsMap;
	}
}
