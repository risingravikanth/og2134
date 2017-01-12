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
		String startDate=req.getParameter("startDate");
		String endDate=req.getParameter("endDate");
		String displayType=req.getParameter("displayType");
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
		String response=contractsServiceImpl.getContractsData(selectedOptions, startDate, endDate, displayType);
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/modalquantity",method={RequestMethod.GET})
	public String getQuantityModalData(HttpServletRequest req)
	{
		
		String response=null;
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);				
		String startDate=req.getParameter("startDate");
		String endDate=req.getParameter("endDate");
		String displayType=req.getParameter("displayType");
		String recordName=req.getParameter("recordName");				
		response=contractsServiceImpl.getModalQuantityData(selectedOptions,startDate,endDate,displayType,recordName);
		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/importcountries",method={RequestMethod.GET})
	public String getImportCountries(HttpServletRequest req)
	{
		
		String response=null;
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);				
		List<String> exportCountries=selectedOptions.get("exportCountries");			
		response=contractsServiceImpl.getImportCountries(exportCountries);
		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/importcompanies",method={RequestMethod.GET})
	public String getImportCompanies(HttpServletRequest req)
	{
		
		String response=null;
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);				
		List<String> exportCompanies=selectedOptions.get("exportCompanies");			
		response=contractsServiceImpl.getImportCompanies(exportCompanies);		
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
			if(option.contains("importcountry"))			
				selectedImportCountries.add(request.getParameter(option));			
			else if(option.contains("exportcountry"))			
				selectedExportCountries.add(request.getParameter(option));	
			else if(option.contains("importcompany"))
				selectedImportCompanies.add(request.getParameter(option));
			else if(option.contains("exportcompany"))
				selectedExportCompanies.add(request.getParameter(option));
					
		}
//			selectedExportCompanies.add("BG Group Plc");
//			
//			selectedImportCountries.add("Japan");
//			selectedImportCountries.add("China");
//			selectedImportCountries.add("india");
//			
//			selectedExportCompanies.add("Sonatrach");
//			selectedImportCountries.add("italy");
//			selectedImportCountries.add("france");
			
			optionsMap.put("importCountries", selectedImportCountries);
			optionsMap.put("exportCountries",selectedExportCountries);
			
			optionsMap.put("importCompanies",selectedImportCompanies);
			optionsMap.put("exportCompanies",selectedExportCompanies);
						
									
		return optionsMap;
	}
}
