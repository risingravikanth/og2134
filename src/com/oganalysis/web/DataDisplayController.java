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

import com.oganalysis.service.DataService;

@Controller
public class DataDisplayController {
	
	
	private DataService dataServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/crudeoil",method={RequestMethod.GET})
	public String getCrudeOilData(HttpServletRequest req)
	{
		String response=null;
//		if(validateUser(req)) This is to check whether user login and available in session
//		{
			String name=req.getParameter("userName");				
			System.out.println("CrudeOil"+name+"session:"+req.getSession());
			
			Enumeration<String> countryNames=req.getParameterNames();
			Map<String,List> selectedOptions=getSelectedOptionsData(req);			
			
			response=dataServiceImpl.getCrudeOilData(selectedOptions);
//		}
		
			
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/exploration",method={RequestMethod.GET})
	public String getExplorationData(HttpServletRequest req)
	{
		String response=null;
//		if(validateUser(req))This is to check whether user login and available in session
//		{
			String name=req.getParameter("userName");
			String startDate = req.getParameter("startDate");
			String endDate=req.getParameter("endDate");
			System.out.println("Exploration"+name +"startDate:"+startDate +"  End date:"+endDate);
			
			Enumeration<String> countryNames=req.getParameterNames();
			Map<String,List> selectedOptions=getSelectedOptionsData(req);			
			
			response=dataServiceImpl.getExplorationData(selectedOptions);
//		}
	
		
		return response;
	}
//	@ResponseBody
//	@RequestMapping(value="/lng",method={RequestMethod.GET})
//	public String getLngData(HttpServletRequest req)
//	{
//		String name=req.getParameter("userName");
//		
//		System.out.println("lng"+name);
//		
//		Enumeration<String> countryNames=req.getParameterNames();
//		Map<String,List> selectedOptions=getSelectedOptionsData(req);
//		
//		String startDate=req.getParameter("startDate");
//		String endDate=req.getParameter("endDate");
//		
//		String displayType=req.getParameter("displayType");
//		String response=null;
//		
//			response=dataServiceImpl.getLngData(selectedOptions);
//		
//		return response;
//	}
	@ResponseBody
	@RequestMapping(value="/naturalgas",method={RequestMethod.GET})
	public String getNaturalGasData(HttpServletRequest req)
	{
		String name=req.getParameter("userName");
		
		System.out.println("naturalgas"+name);
		
		Enumeration<String> countryNames=req.getParameterNames();
		Map<String,List> selectedOptions=getSelectedOptionsData(req);
		String response=null;
		
			response=dataServiceImpl.getNaturalGasData(selectedOptions);
		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/pipeline",method={RequestMethod.GET})
	public String getPipeLineData(HttpServletRequest req)
	{
		String name=req.getParameter("userName");
		
		System.out.println("pipeline"+name);
		
		Enumeration<String> countryNames=req.getParameterNames();
		Map<String,List> selectedOptions=getSelectedOptionsData(req);
		String response=null;
		
			response=dataServiceImpl.getPipeLineData(selectedOptions);
		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/refinery",method={RequestMethod.GET})
	public String getRefineryData(HttpServletRequest req)
	{
		String name=req.getParameter("userName");
		
		System.out.println("Refinery"+name+"session:"+req.getSession());
		
		Enumeration<String> countryNames=req.getParameterNames();
		Map<String,List> selectedOptions=getSelectedOptionsData(req);
		String response=null;
		
			response=dataServiceImpl.getRefineryData(selectedOptions);
			
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/storage",method={RequestMethod.GET})
	public String getStorageData(HttpServletRequest req)
	{
		String name=req.getParameter("userName");
		
		System.out.println("Storage"+name);
		
		Enumeration<String> countryNames=req.getParameterNames();
		Map<String,List> selectedOptions=getSelectedOptionsData(req);
		String response=null;
		
			response=dataServiceImpl.getStorageData(selectedOptions);
		
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
	public DataService getDataServiceImpl() {
		return dataServiceImpl;
	}
	@Autowired
	public void setDataServiceImpl(DataService dataServiceImpl) {
		this.dataServiceImpl = dataServiceImpl;
	}
    
	
	
}
