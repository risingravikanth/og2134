package com.oganalysis.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oganalysis.helper.JsonResponse;
import com.oganalysis.service.FilterDataService;
@Controller
public class FilterDataController {
		
	private FilterDataService filterDataServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/regions",method={RequestMethod.GET})
	public String getRegionsList()
	{			
		
		String response=filterDataServiceImpl.getRegions();		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/countries",method={RequestMethod.GET})
	public String getCountries()
	{			
		
		String response=filterDataServiceImpl.getCountries();		
		return response;
	}
	
	@ResponseBody
	@RequestMapping(value="/status",method={RequestMethod.GET})
	public String getStatus()
	{			
		
		String response=filterDataServiceImpl.getStatus();		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/type",method={RequestMethod.GET})
	public String getType()
	{			
		
		String response=filterDataServiceImpl.getType();		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/locations",method={RequestMethod.GET})
	public String getLocations()
	{			
		
		String response=filterDataServiceImpl.getLocations();		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/operators",method={RequestMethod.GET})
	public String getOperator()
	{			
		
		String response=filterDataServiceImpl.getOperator();		
		return response;
	}
	public FilterDataService getFilterDataServiceImpl() {
		return filterDataServiceImpl;
	}
	@Autowired
	public void setFilterDataServiceImpl(FilterDataService filterDataServiceImpl) {
		this.filterDataServiceImpl = filterDataServiceImpl;
	}
	
	
		
	

}
