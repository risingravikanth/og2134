package com.oganalysis.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oganalysis.service.LngDataService;
import com.oganalysis.service.impl.LngDataServiceImpl;

@Controller
public class LngDataDisplayController {
	
	@Autowired
	private LngDataService lngDataServiceImpl;
	
	@Autowired
	private LngDataServiceImpl lngDataServiceImpl1;
	
	@ResponseBody
	@RequestMapping(value="/capacity",method={RequestMethod.GET})
	public String getLngCapacityData(HttpServletRequest req)
	{
		String name=req.getParameter("userName");
		
		System.out.println("lng"+name);
		
		Enumeration<String> countryNames=req.getParameterNames();
		Map<String,List> selectedOptions=getSelectedOptionsData(req);
		
		String startDate=req.getParameter("startDate");
		String endDate=req.getParameter("endDate");
		
		String displayType=req.getParameter("displayType");
		String response=null;
		System.out.println(lngDataServiceImpl1);
		response=lngDataServiceImpl.getCapacityData(selectedOptions,startDate,endDate,displayType);
		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/infrastructure",method={RequestMethod.GET})
	public String getLngInfrastructureData(HttpServletRequest req)
	{
		
		String response=null;
		Enumeration<String> countryNames=req.getParameterNames();
		Map<String,List> selectedOptions=getSelectedOptionsData(req);				
				
		response=lngDataServiceImpl.getInfrastructureData(selectedOptions);
		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/modalcapacity",method={RequestMethod.GET})
	public String getLngCapacityModalData(HttpServletRequest req)
	{
		
		String response=null;
		Enumeration<String> countryNames=req.getParameterNames();
		Map<String,List> selectedOptions=getSelectedOptionsData(req);				
		String startDate=req.getParameter("startDate");
		String endDate=req.getParameter("endDate");
		String displayType=req.getParameter("displayType");
		String type=req.getParameter("type");
		String recordName=req.getParameter("recordName");				
		response=lngDataServiceImpl.getModalCapacityData(selectedOptions,startDate,endDate,displayType,type,recordName);
		
		return response;
	}
	private Map<String,List> getSelectedOptionsData(HttpServletRequest request)
	{
		Enumeration<String> selectedOptions=request.getParameterNames();
		Map<String,List> optionsMap=new HashMap<String,List>();
		List<String> selectedCountries=new ArrayList<String>();
		List<String> selectedRegions=new ArrayList<String>();
		List<String> selectedLocations=new ArrayList<String>();
		List<String> selectedOperators=new ArrayList<String>();
		List<String> selectedOwners=new ArrayList<String>();
		List<String> selectedStatuses=new ArrayList<String>();
		List<String> selectedOffOnshores=new ArrayList<String>();
		List<String> selectedTypes=new ArrayList<String>();
		List<String> selectedTerminals=new ArrayList<String>();
		
		while(selectedOptions.hasMoreElements())
		{
			String option=selectedOptions.nextElement();
			if(option.contains("country"))			
				selectedCountries.add(request.getParameter(option));			
			else if(option.contains("region"))			
				selectedRegions.add(request.getParameter(option));			
			else if(option.contains("location"))
				selectedLocations.add(request.getParameter(option));
			else if(option.contains("operator"))
				selectedOperators.add(request.getParameter(option));
			else if(option.contains("owner"))
				selectedOwners.add(request.getParameter(option));			
			else if(option.contains("status"))
				selectedStatuses.add(request.getParameter(option));
			else if(option.contains("offonshore"))
				selectedOffOnshores.add(request.getParameter(option));
			else if(option.contains("type"))
				selectedTypes.add(request.getParameter(option));
			
		}
		Map<String,Set<String>> companyTerminals=lngDataServiceImpl.getCompanyTerminals();
		Map<String,Set<String>> operatorTerminals=lngDataServiceImpl.getOperatorTerminals();
		for(String owners:selectedOwners)
		{
			Set<String> terminals=companyTerminals.get(owners);
			for(String terminal:terminals)
			{	
				if(!selectedTerminals.contains(terminal))
				selectedTerminals.add(terminal);
			}	
		}
		for(String operator:selectedOperators)
		{
			Set<String> terminals=operatorTerminals.get(operator);
			for(String terminal:terminals)
			{
				if(!selectedTerminals.contains(terminal))
					selectedTerminals.add(terminal);
			}
		}
			optionsMap.put("countries", selectedCountries);
			optionsMap.put("regions",selectedRegions);
			optionsMap.put("locations",selectedLocations);
			optionsMap.put("operators",selectedOperators);
			optionsMap.put("owners",selectedOwners);
			optionsMap.put("statuses",selectedStatuses);
			optionsMap.put("offonshores",selectedOffOnshores);
			optionsMap.put("types",selectedTypes);
			optionsMap.put("terminals",selectedTerminals);
									
		return optionsMap;
	}
	private boolean validateUser(HttpServletRequest req)
	{
		HttpSession session=req.getSession();
		String email=(String)session.getAttribute("email");
		if(email!=null)
			return true;
		else
			return false;
	}
	
//	public LngDataService getLngDataServiceImpl() {
//		return lngDataServiceImpl;
//	}
//	
//	public void setLngDataServiceImpl(LngDataService lngDataServiceImpl) {
//		this.lngDataServiceImpl = lngDataServiceImpl;
//	}
	
}
