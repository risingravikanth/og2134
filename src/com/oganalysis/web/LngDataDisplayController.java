package com.oganalysis.web;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oganalysis.service.LngDataService;

@Controller
public class LngDataDisplayController {
	
	private LngDataService lngDataServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/capacity",method={RequestMethod.GET})
	public String getLngData(HttpServletRequest req)
	{
		String name=req.getParameter("userName");
		
		System.out.println("lng"+name);
		
		Enumeration<String> countryNames=req.getParameterNames();
		Map<String,List> selectedOptions=getSelectedOptionsData(req);
		
		String startDate=req.getParameter("startDate");
		String endDate=req.getParameter("endDate");
		
		String displayType=req.getParameter("displayType");
		String response=null;
		
		response=lngDataServiceImpl.getCapacityData(selectedOptions,startDate,endDate,displayType);
		
		return response;
	}
	private Map<String,List> getSelectedOptionsData(HttpServletRequest request)
	{
		Enumeration<String> selectedOptions=request.getParameterNames();
		Map<String,List> optionsMap=new HashMap<String,List>();
		List<String> selectedCountries=new ArrayList<String>();
		List<String> selectedRegions=new ArrayList<String>();
		while(selectedOptions.hasMoreElements())
		{
			String option=selectedOptions.nextElement();
			if(option.contains("country"))
			{
				selectedCountries.add(request.getParameter(option));
			}
			else if(option.contains("region"))
			{
				selectedRegions.add(request.getParameter(option));
			}
			else if(option.equalsIgnoreCase("displayType"))
			{
				
			}
		}
		if(selectedCountries.size()>0 || selectedRegions.size()>0)
		{	
			optionsMap.put("countries", selectedCountries);
			optionsMap.put("regions",selectedRegions);
		}	
		
		
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
	
	public LngDataService getLngDataServiceImpl() {
		return lngDataServiceImpl;
	}
	@Autowired
	public void setLngDataServiceImpl(LngDataService lngDataServiceImpl) {
		this.lngDataServiceImpl = lngDataServiceImpl;
	}
	
}
