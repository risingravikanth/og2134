package com.oganalysis.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@RequestMapping(value="/lng/locations",method={RequestMethod.GET})
	public String getLngLocations()
	{			
		
		String response=filterDataServiceImpl.getLngLocations();		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/lng/operators",method={RequestMethod.GET})
	public String getLngOperators()
	{			
		
		String response=filterDataServiceImpl.getLngOperators();		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/lng/owners",method={RequestMethod.GET})
	public String getLngOwners()
	{			
		
		String response=filterDataServiceImpl.getLngOwners();		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/refineries/locations",method={RequestMethod.GET})
	public String getRefineryLocations()
	{			
		
		String response=filterDataServiceImpl.getRefineryLocations();		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/refineries/operators",method={RequestMethod.GET})
	public String getRefineryOperators()
	{			
		
		String response=filterDataServiceImpl.getRefineryOperators();		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/refineries/owners",method={RequestMethod.GET})
	public String getRefineryOwners()
	{			
		
		String response=filterDataServiceImpl.getRefineryOwners();		
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
