package com.oganalysis.helper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import static com.oganalysis.constants.ApplicationConstants.*;

public class ContractsJsonResponse {
	
	public String createQuantityByCompanyRes(Map<String,Map<Integer,Double>> quantityData,int startDate,int endDate)
	{
		
		final String displayType=COMPANY;
		
		JSONArray quantityByCompany=new JSONArray();
				
		JSONObject quantityJsonObj=new JSONObject();	
		JSONArray allYearsQuantityByCompany=getAllYearsQuantity(quantityData, startDate, endDate);
		JSONObject totalQuantityOfYear=getTotalQuantityOfYear(allYearsQuantityByCompany,startDate,endDate);	
		quantityJsonObj.put(displayType, allYearsQuantityByCompany);
		quantityJsonObj.put(JSON_TOTALCAPACITY, totalQuantityOfYear);								
		quantityByCompany.add(quantityJsonObj);
				
		return quantityByCompany.toJSONString();
		
	
	}
	public String createQuantityByCountryRes(Map<String,Map<Integer,Double>> quantityData,int startDate,int endDate)
	{
		
		final String displayType=COUNTRY;
		
		JSONArray quantityByCountry=new JSONArray();
				
		JSONObject quantityJsonObj=new JSONObject();	
		JSONArray allYearsQuantityByCountry=getAllYearsQuantity(quantityData, startDate, endDate);
		JSONObject totalQuantityOfYear=getTotalQuantityOfYear(allYearsQuantityByCountry,startDate,endDate);	
		quantityJsonObj.put(displayType, allYearsQuantityByCountry);
		quantityJsonObj.put(JSON_TOTALCAPACITY, totalQuantityOfYear);								
		quantityByCountry.add(quantityJsonObj);
				
		return quantityByCountry.toJSONString();		
	
	}
	public String createQuantityByTerminalRes(Map<String,Map<Integer,Double>> quantityData,int startDate,int endDate)
	{
		
		final String displayType=TERMINAL;
		
		JSONArray quantityByCountry=new JSONArray();
				
		JSONObject quantityJsonObj=new JSONObject();	
		JSONArray allYearsQuantityByCountry=getAllYearsQuantity(quantityData, startDate, endDate);
		JSONObject totalQuantityOfYear=getTotalQuantityOfYear(allYearsQuantityByCountry,startDate,endDate);	
		quantityJsonObj.put(displayType, allYearsQuantityByCountry);
		quantityJsonObj.put(JSON_TOTALCAPACITY, totalQuantityOfYear);								
		quantityByCountry.add(quantityJsonObj);
				
		return quantityByCountry.toJSONString();		
	
	}
	
	
	private JSONArray getAllYearsQuantity(Map<String,Map<Integer,Double>> quantityData,int startDate,int endDate)
	{
		JSONArray allYearsQuantityArray=new JSONArray();
		Set<String> nameSet=quantityData.keySet();
		
		for(Object nameObj:nameSet)
		{
			JSONObject jsonObj=new JSONObject();
			Map<Integer,Double> yearMap=quantityData.get(nameObj);
			jsonObj.put(JSON_NAME,(String)nameObj);
			for(int yearCount=startDate;yearCount<=endDate;yearCount++)
			{
				if(yearMap.containsKey(yearCount) &&(null!=yearMap.get(yearCount) && (Double)yearMap.get(yearCount)!=0))										
					jsonObj.put(yearCount,yearMap.get(yearCount));				
				else
					jsonObj.put(yearCount,BLANK);			
					
			}
			
			allYearsQuantityArray.add(jsonObj);
		}
		return allYearsQuantityArray;
	}
	private JSONObject getTotalQuantityOfYear(JSONArray allYearsQuantity,int startDate,int endDate)
	{
		JSONObject totalCapacityJsonObj=new JSONObject();
		for(int yearCount=startDate;yearCount<=endDate;yearCount++)
		{
			double totalCapacity=0;
			double capacityVal=0;
			for(int i=0;i<allYearsQuantity.size();i++)
			{
				JSONObject yearJsonObj=(JSONObject)allYearsQuantity.get(i);
				if(null!=yearJsonObj.get(yearCount) && !yearJsonObj.get(yearCount).equals(BLANK))
					capacityVal=(Double)yearJsonObj.get(yearCount);
				else
					capacityVal=0;
				totalCapacity=totalCapacity+capacityVal;
			}
			totalCapacity=round(totalCapacity, 2);
			totalCapacityJsonObj.put(yearCount, String.valueOf(totalCapacity));
		}
		return totalCapacityJsonObj;
	}
	public String createImportCompanies(List<String> importCompanies)
	{
		String response=null;
		if(importCompanies.size()>0)
		{
			JSONArray array=new JSONArray();
			
			for(String company:importCompanies)
			{
				 
				 JSONObject jsonObj=new JSONObject();
				 							
				  				  			 
				  jsonObj.put(COMPANY,company);
				  						      			      
			      array.add(jsonObj);
			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createImportCountries(List<String> importCountries)
	{
		String response=null;
		if(importCountries.size()>0)
		{
			JSONArray array=new JSONArray();
			
			for(String country:importCountries)
			{
				 
				 JSONObject jsonObj=new JSONObject();
				 							
				  				  			 
				  jsonObj.put(COUNTRY,country);
				  						      			      
			      array.add(jsonObj);
			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createExportCompanies(List<String> exportCompanies)
	{
		String response=null;
		if(exportCompanies.size()>0)
		{
			JSONArray array=new JSONArray();
			
			for(String company:exportCompanies)
			{
				 
				 JSONObject jsonObj=new JSONObject();
				 							
				  				  			 
				  jsonObj.put(COMPANY,company);
				  						      			      
			      array.add(jsonObj);
			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createExportCountries(List<String> exportCountries)
	{
		String response=null;
		if(exportCountries.size()>0)
		{
			JSONArray array=new JSONArray();
			
			for(String country:exportCountries)
			{
				 
				 JSONObject jsonObj=new JSONObject();
				 							
				  				  			 
				  jsonObj.put(COUNTRY,country);
				  						      			      
			      array.add(jsonObj);
			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	private double round(double value, int places) {	    

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
