package com.oganalysis.web;

import static com.oganalysis.constants.ApplicationConstants.*;

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

import com.oganalysis.service.ProductionService;
import com.oganalysis.service.SupplyDemandService;

@Controller
@RequestMapping("/production")
public class ProductionController {
	@Autowired
	private ProductionService productionServiceImpl;
	
	@ResponseBody
	@RequestMapping(value="/asset",method={RequestMethod.POST})
	public String getProductionAsset(HttpServletRequest req)
	{
		String response=LOGIN;
		if(null!=req.getSession().getAttribute(EMAIL))
		{
			String startYear=req.getParameter(STARTDATE);
			String endYear=req.getParameter(ENDDATE);
			String displayType=req.getParameter(DISPLAYTYPE);			
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
			response=productionServiceImpl.getAssetData(selectedOptions, startYear, endYear, displayType);
		}		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/company",method={RequestMethod.POST})
	public String getProductionCompany(HttpServletRequest req)
	{
		String response=LOGIN;
		if(null!=req.getSession().getAttribute(EMAIL))
		{
			String startYear=req.getParameter(STARTDATE);
			String endYear=req.getParameter(ENDDATE);						
			Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);
			response=productionServiceImpl.getCompanyOilGasData(selectedOptions,startYear,endYear);
		}		
		return response;
	}
	@ResponseBody
	@RequestMapping(value="/asset/regions",method={RequestMethod.GET})
	public String getAssetRegions(HttpServletRequest req)
	{				
		return productionServiceImpl.getAssetRegions();
	}
	@ResponseBody
	@RequestMapping(value="/asset/countries",method={RequestMethod.POST})
	public String getAssetCountries(HttpServletRequest req)
	{
		Map<String,List<String>> selectedOptions=getSelectedOptionsData(req);		
		return productionServiceImpl.getAssetCountries(selectedOptions);
	}
	@ResponseBody
	@RequestMapping(value="/company/countries",method={RequestMethod.GET})
	public String getCompanyCountries(HttpServletRequest req)
	{			
		return productionServiceImpl.getCompanyCountries();
	}
	private Map<String,List<String>> getSelectedOptionsData(HttpServletRequest request)
	{
		Enumeration<String> selectedOptions=request.getParameterNames();
		Map<String,List<String>> optionsMap=new HashMap<String,List<String>>();
		List<String> selectedCountries=new ArrayList<String>();
		List<String> selectedRegions=new ArrayList<String>();
		List<String> selectedUnits=new ArrayList<String>();
		List<String> selectedTypes=new ArrayList<String>();
					
		while(selectedOptions.hasMoreElements())
		{
			String option=selectedOptions.nextElement();
			if(option.contains(OPTION_COUNTRY))			
				selectedCountries.add(request.getParameter(option));			
			else if(option.contains(OPTION_REGION))			
				selectedRegions.add(request.getParameter(option));
			else if(option.contains(OPTION_UNIT))
				selectedUnits.add(request.getParameter(option));
			else if(option.contains(TYPE))
				selectedTypes.add(request.getParameter(option));
					
		}
		
			optionsMap.put(OPTION_SELECTED_COUNTRIES, selectedCountries);
			optionsMap.put(OPTION_SELECTED_REGIONS,selectedRegions);
			optionsMap.put(OPTION_SELECTED_UNITS,selectedUnits);
			optionsMap.put(OPTION_SELECTED_TYPES,selectedTypes);
									
		return optionsMap;
	}
}
