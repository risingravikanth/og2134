package com.oganalysis.web;

import static com.oganalysis.constants.ApplicationConstants.BLANK;

import java.util.ArrayList;
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
		return BLANK;
	}
	
	@ResponseBody
	@RequestMapping(value="/countries",method={RequestMethod.GET})
	public String getCountries()
	{				
		List<String> countries=new ArrayList<String>();
		countries.add("Angola");
		countries.add("Trinidad & Tobago");
		JsonResponse res=new JsonResponse();
		return res.createCountries(countries);
//		return BLANK;
	}
	
	@ResponseBody
	@RequestMapping(value="/status",method={RequestMethod.GET})
	public String getStatus()
	{			
		
		return BLANK;
	}
	@ResponseBody
	@RequestMapping(value="/type",method={RequestMethod.GET})
	public String getType()
	{					
		return BLANK;
	}
	
	public FilterDataService getFilterDataServiceImpl() {
		return filterDataServiceImpl;
	}
	@Autowired
	public void setFilterDataServiceImpl(FilterDataService filterDataServiceImpl) {
		this.filterDataServiceImpl = filterDataServiceImpl;
	}
	
	
		
	

}
