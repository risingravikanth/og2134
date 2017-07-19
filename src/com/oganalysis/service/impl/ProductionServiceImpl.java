package com.oganalysis.service.impl;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.mapping.Array;

import com.oganalysis.business.ProductionBusinessService;
import com.oganalysis.helper.ProductionJsonResponse;
import com.oganalysis.service.ProductionService;

public class ProductionServiceImpl implements ProductionService {
	private ProductionBusinessService productionBusinessServiceImpl;	
	@Override
	public String getAssetData(Map<String, List<String>> selectedOptions,
			String startYear, String endYear, String displayType,
			String filterType) {
		// TODO Auto-generated method stub
		String res=null;
		int startYearVal=Integer.parseInt(startYear);
		int endYearVal=Integer.parseInt(endYear);
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
	public String getCompanyOilGasData(String country, String startYear,
			String endYear, String filterType) {
		// TODO Auto-generated method stub
		List<Map<String,String>> companyOilGasData=new ArrayList<Map<String,String>>();
		String res=null;
		int startYearVal=Integer.parseInt(startYear);
		int endYearVal=Integer.parseInt(endYear);
		ProductionJsonResponse pjr=new ProductionJsonResponse();
		if(GAS.equalsIgnoreCase(filterType))
		{				
			companyOilGasData=productionBusinessServiceImpl.getCompanyOilGasCapacity(country, NATURALGAS);
			res=pjr.createCompanyOilGasResponse(companyOilGasData,startYearVal,endYearVal,filterType).toJSONString();
		}	
		else if(OIL.equalsIgnoreCase(filterType))
		{				
			companyOilGasData=productionBusinessServiceImpl.getCompanyOilGasCapacity(country, CRUDEOIL);
			res=pjr.createCompanyOilGasResponse(companyOilGasData,startYearVal,endYearVal,filterType).toJSONString();
		}	
		else
		{
			Map<String,List<Map<String,String>>> bothCompanyOilGasData=new HashMap<String, List<Map<String,String>>>();
			companyOilGasData=productionBusinessServiceImpl.getCompanyOilGasCapacity(country, NATURALGAS);
			bothCompanyOilGasData.put(GAS, companyOilGasData);
			companyOilGasData=productionBusinessServiceImpl.getCompanyOilGasCapacity(country, CRUDEOIL);
			bothCompanyOilGasData.put(OIL, companyOilGasData);
			res=pjr.createCompanyOilGasResponse(bothCompanyOilGasData, startYearVal, endYearVal).toJSONString();
		}
		
		return res;
		
	}
	public void setProductionBusinessServiceImpl(
			ProductionBusinessService productionBusinessServiceImpl) {
		this.productionBusinessServiceImpl = productionBusinessServiceImpl;
	}
	
	
}
