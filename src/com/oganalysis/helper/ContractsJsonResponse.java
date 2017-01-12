package com.oganalysis.helper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ContractsJsonResponse {
	
	public String createQuantityByCompanyRes(Map<String,Map<Integer,Float>> quantityData,int startDate,int endDate)
	{
		
		final String displayType="company";
		
		JSONArray quantityByCompany=new JSONArray();
				
		JSONObject quantityJsonObj=new JSONObject();	
		JSONArray allYearsQuantityByCompany=getAllYearsQuantity(quantityData, startDate, endDate);
		JSONObject totalQuantityOfYear=getTotalQuantityOfYear(allYearsQuantityByCompany,startDate,endDate);	
		quantityJsonObj.put(displayType, allYearsQuantityByCompany);
		quantityJsonObj.put("totalCapacity", totalQuantityOfYear);								
		quantityByCompany.add(quantityJsonObj);
				
		return quantityByCompany.toJSONString();
		
	
	}
	public String createQuantityByCountryRes(Map<String,Map<Integer,Float>> quantityData,int startDate,int endDate)
	{
		
		final String displayType="country";
		
		JSONArray quantityByCountry=new JSONArray();
				
		JSONObject quantityJsonObj=new JSONObject();	
		JSONArray allYearsQuantityByCountry=getAllYearsQuantity(quantityData, startDate, endDate);
		JSONObject totalQuantityOfYear=getTotalQuantityOfYear(allYearsQuantityByCountry,startDate,endDate);	
		quantityJsonObj.put(displayType, allYearsQuantityByCountry);
		quantityJsonObj.put("totalCapacity", totalQuantityOfYear);								
		quantityByCountry.add(quantityJsonObj);
				
		return quantityByCountry.toJSONString();		
	
	}
	public String createQuantityByTerminalRes(Map<String,Map<Integer,Float>> quantityData,int startDate,int endDate)
	{
		
		final String displayType="terminal";
		
		JSONArray quantityByCountry=new JSONArray();
				
		JSONObject quantityJsonObj=new JSONObject();	
		JSONArray allYearsQuantityByCountry=getAllYearsQuantity(quantityData, startDate, endDate);
		JSONObject totalQuantityOfYear=getTotalQuantityOfYear(allYearsQuantityByCountry,startDate,endDate);	
		quantityJsonObj.put(displayType, allYearsQuantityByCountry);
		quantityJsonObj.put("totalCapacity", totalQuantityOfYear);								
		quantityByCountry.add(quantityJsonObj);
				
		return quantityByCountry.toJSONString();		
	
	}
	
	
	private JSONArray getAllYearsQuantity(Map<String,Map<Integer,Float>> quantityData,int startDate,int endDate)
	{
		JSONArray allYearsQuantityArray=new JSONArray();
		Set<String> nameSet=quantityData.keySet();
		
		for(Object nameObj:nameSet)
		{
			JSONObject jsonObj=new JSONObject();
			Map<Integer,Float> yearMap=quantityData.get(nameObj);
			jsonObj.put("name",(String)nameObj);
			for(int yearCount=startDate;yearCount<=endDate;yearCount++)
			{
				if(yearMap.containsKey(yearCount) &&(null!=yearMap.get(yearCount) && (Float)yearMap.get(yearCount)!=0))										
					jsonObj.put(yearCount,yearMap.get(yearCount));				
				else
					jsonObj.put(yearCount,"");			
					
			}
			
			allYearsQuantityArray.add(jsonObj);
		}
		return allYearsQuantityArray;
	}
	private JSONObject getTotalQuantityOfYear(JSONArray allYearsQuantity,int startDate,int endDate)
	{
		JSONObject totalCapcityJsonObj=new JSONObject();
		for(int yearCount=startDate;yearCount<=endDate;yearCount++)
		{
			float totalCapacity=0;
			float capacityVal=0;
			for(int i=0;i<allYearsQuantity.size();i++)
			{
				JSONObject yearJsonObj=(JSONObject)allYearsQuantity.get(i);
				if(null!=yearJsonObj.get(yearCount) && !yearJsonObj.get(yearCount).equals(""))
					capacityVal=(Float)yearJsonObj.get(yearCount);
				else
					capacityVal=0;
				totalCapacity=totalCapacity+capacityVal;
			}
			totalCapcityJsonObj.put(yearCount, String.valueOf(totalCapacity));
		}
		return totalCapcityJsonObj;
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
				 							
				  				  			 
				  jsonObj.put("company",company);
				  						      			      
			      array.add(jsonObj);
			      
			}
			
			response=array.toString();		      
		}
		else
			response="";
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
				 							
				  				  			 
				  jsonObj.put("country",country);
				  						      			      
			      array.add(jsonObj);
			      
			}
			
			response=array.toString();		      
		}
		else
			response="";
		return response;
	}
}
