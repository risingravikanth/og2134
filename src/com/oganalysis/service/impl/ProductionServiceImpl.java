package com.oganalysis.service.impl;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.mapping.Array;

import com.oganalysis.business.ProductionBusinessService;
import com.oganalysis.helper.JsonResponse;
import com.oganalysis.helper.ProductionJsonResponse;
import com.oganalysis.service.ProductionService;

public class ProductionServiceImpl implements ProductionService {
	private ProductionBusinessService productionBusinessServiceImpl;	
	@Override
	public String getAssetData(Map<String, List<String>> selectedOptions,
			String startYear, String endYear, String displayType) {
		// TODO Auto-generated method stub
		String res=null;
		int startYearVal=Integer.parseInt(startYear);
		int endYearVal=Integer.parseInt(endYear);
		String filterType=null;
		if(null!=selectedOptions.get(OPTION_SELECTED_TYPES) && selectedOptions.get(OPTION_SELECTED_TYPES).size()>0)
			filterType=selectedOptions.get(OPTION_SELECTED_TYPES).get(0);
		ProductionJsonResponse pjr=new ProductionJsonResponse();
		if(COUNTRY.equalsIgnoreCase(displayType))
		{
			   List<Map<String,String>> countryData=new ArrayList<Map<String,String>>();
			   if(GAS.equalsIgnoreCase(filterType))
			   {   
				   countryData=productionBusinessServiceImpl.getNaturalGasCapacityByCountry(selectedOptions);
				   res=pjr.createCountryResponse(countryData, startYearVal, endYearVal, displayType,filterType).toJSONString();
			   }	   
			   else if(OIL.equalsIgnoreCase(filterType))
			   {
				   countryData=productionBusinessServiceImpl.getCrudeOilCapacityByCountry(selectedOptions);
				   res=pjr.createCountryResponse(countryData, startYearVal, endYearVal, displayType,filterType).toJSONString();	
			   }
			   else
			   {
				   Map<String,List<Map<String,String>>> bothCountryData=new HashMap<String, List<Map<String,String>>>();
				   countryData=productionBusinessServiceImpl.getNaturalGasCapacityByCountry(selectedOptions);
				   bothCountryData.put(GAS, countryData);
				   countryData=productionBusinessServiceImpl.getCrudeOilCapacityByCountry(selectedOptions);
				   bothCountryData.put(OIL, countryData);
				   res=pjr.createOilAndGasCountryResponse(bothCountryData,startYearVal,endYearVal,displayType).toJSONString();
			   }
				  				   
		}
		else if(FIELD.equalsIgnoreCase(displayType))
		{
				List<Map<String,String>> fieldData=new ArrayList<Map<String,String>>();
			   if(GAS.equalsIgnoreCase(filterType))
			   {   
				   fieldData=productionBusinessServiceImpl.getNaturalGasCapacityByField(selectedOptions);
				   res=pjr.createFieldResponse(fieldData, startYearVal, endYearVal, displayType,filterType).toJSONString();
			   }	   
			   else if(OIL.equalsIgnoreCase(filterType))
			   {
				   fieldData=productionBusinessServiceImpl.getCrudeOilCapacityByField(selectedOptions);
				   res=pjr.createFieldResponse(fieldData, startYearVal, endYearVal, displayType,filterType).toJSONString();	
			   }
			   else
			   {
				   Map<String,List<Map<String,String>>> bothFieldData=new HashMap<String, List<Map<String,String>>>();
				   fieldData=productionBusinessServiceImpl.getNaturalGasCapacityByField(selectedOptions);
				   bothFieldData.put(GAS, fieldData);
				   fieldData=productionBusinessServiceImpl.getCrudeOilCapacityByField(selectedOptions);
				   bothFieldData.put(OIL, fieldData);
				   res=pjr.createOilAndGasFieldResponse(bothFieldData,startYearVal,endYearVal,displayType).toJSONString();
			   }
		}
		
		return res;
	}
	@Override
	public String getCompanyOilGasData(Map<String,List<String>> selectedOptions, String startYear,
			String endYear) {
		// TODO Auto-generated method stub
		List<Map<String,String>> companyOilGasData=new ArrayList<Map<String,String>>();
		String res=null;
		int startYearVal=Integer.parseInt(startYear);
		int endYearVal=Integer.parseInt(endYear);
		String filterType=null;
		if(null!=selectedOptions.get(OPTION_SELECTED_TYPES) && selectedOptions.get(OPTION_SELECTED_TYPES).size()>0)
			filterType=selectedOptions.get(OPTION_SELECTED_TYPES).get(0);
		ProductionJsonResponse pjr=new ProductionJsonResponse();
		if(GAS.equalsIgnoreCase(filterType))
		{				
			companyOilGasData=productionBusinessServiceImpl.getCompanyOilGasCapacity(selectedOptions, NATURALGAS);
			res=pjr.createCompanyOilGasResponse(companyOilGasData,startYearVal,endYearVal,filterType).toJSONString();
		}	
		else if(OIL.equalsIgnoreCase(filterType))
		{				
			companyOilGasData=productionBusinessServiceImpl.getCompanyOilGasCapacity(selectedOptions, CRUDEOIL);
			res=pjr.createCompanyOilGasResponse(companyOilGasData,startYearVal,endYearVal,filterType).toJSONString();
		}	
		else
		{
			Map<String,List<Map<String,String>>> bothCompanyOilGasData=new HashMap<String, List<Map<String,String>>>();
			companyOilGasData=productionBusinessServiceImpl.getCompanyOilGasCapacity(selectedOptions, NATURALGAS);
			bothCompanyOilGasData.put(GAS, companyOilGasData);
			companyOilGasData=productionBusinessServiceImpl.getCompanyOilGasCapacity(selectedOptions, CRUDEOIL);
			bothCompanyOilGasData.put(OIL, companyOilGasData);
			res=pjr.createCompanyOilGasResponse(bothCompanyOilGasData, startYearVal, endYearVal).toJSONString();
		}
		
		return res;
		
	}
	@Override
	public String getAssetRegions() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> regionsList=productionBusinessServiceImpl.getAssetRegions();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createRegions(regionsList);
		return jsonRes;
	}
	@Override
	public String getAssetCountries(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> countriesList=productionBusinessServiceImpl.getAssetCountries(selectedOptions);
		JsonResponse res=new JsonResponse();
		jsonRes=res.createCountries(countriesList);
		return jsonRes;
	}
	@Override
	public String getCompanyCountries() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> countriesList=productionBusinessServiceImpl.getCompanyCountries();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createCountries(countriesList);
		return jsonRes;
	}
	public void setProductionBusinessServiceImpl(
			ProductionBusinessService productionBusinessServiceImpl) {
		this.productionBusinessServiceImpl = productionBusinessServiceImpl;
	}
			
	
}
