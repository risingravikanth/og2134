package com.oganalysis.helper;

import static com.oganalysis.constants.ApplicationConstants.*;


import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RefineriesJsonResponse {
	
	public String createCapacityByCompany(Map<String,Map<Integer,Double>> capacityData,int startDate,int endDate)
	{					
		JSONArray capacityByCompany=new JSONArray();
				
		JSONObject capacityJsonObj=new JSONObject();	
		JSONArray allYearsCapacityByCompany=getAllYearsCapacity(capacityData, startDate, endDate);
		JSONObject totalCapacityOfYear=getTotalCapacityOfYear(allYearsCapacityByCompany,startDate,endDate);	
		capacityJsonObj.put(COMPANY, allYearsCapacityByCompany);
		capacityJsonObj.put(JSON_TOTALCAPACITY, totalCapacityOfYear);								
		capacityByCompany.add(capacityJsonObj);
				
		return capacityByCompany.toJSONString();
			
	}
	public String createCapacityByCountry(Map<String,Map<Integer,Double>> capacityData,int startDate,int endDate)
	{					
		JSONArray capacityByCompany=new JSONArray();
				
		JSONObject capacityJsonObj=new JSONObject();	
		JSONArray allYearsCapacityByCountry=getAllYearsCapacity(capacityData, startDate, endDate);
		JSONObject totalCapacityOfYear=getTotalCapacityOfYear(allYearsCapacityByCountry,startDate,endDate);	
		capacityJsonObj.put(COUNTRY, allYearsCapacityByCountry);
		capacityJsonObj.put(JSON_TOTALCAPACITY, totalCapacityOfYear);								
		capacityByCompany.add(capacityJsonObj);
				
		return capacityByCompany.toJSONString();
			
	}
	public String createCapacityByTerminal(Map<String,Map<Integer,Double>> capacityData,int startDate,int endDate)
	{					
		JSONArray capacityByTerminal=new JSONArray();
				
		JSONObject capacityJsonObj=new JSONObject();	
		JSONArray allYearsCapacityByTerminal=getAllYearsCapacity(capacityData, startDate, endDate);
		JSONObject totalCapacityOfYear=getTotalCapacityOfYear(allYearsCapacityByTerminal,startDate,endDate);	
		capacityJsonObj.put(TERMINAL, allYearsCapacityByTerminal);
		capacityJsonObj.put(JSON_TOTALCAPACITY, totalCapacityOfYear);								
		capacityByTerminal.add(capacityJsonObj);
				
		return capacityByTerminal.toJSONString();
			
	}
	private JSONArray getAllYearsCapacity(Map<String,Map<Integer,Double>> capacityData,int startDate,int endDate)
	{
		JSONArray allYearsCapacityArray=new JSONArray();
		Set<String> nameSet=capacityData.keySet();
		
		for(Object nameObj:nameSet)
		{
			JSONObject jsonObj=new JSONObject();
			Map<Integer,Double> yearMap=capacityData.get(nameObj);
			jsonObj.put(JSON_NAME,(String)nameObj);
			for(int yearCount=startDate;yearCount<=endDate;yearCount++)
			{
				if(yearMap.containsKey(yearCount) &&(null!=yearMap.get(yearCount) && (Double)yearMap.get(yearCount)!=0))										
					jsonObj.put(yearCount,yearMap.get(yearCount));				
				else
					jsonObj.put(yearCount,BLANK);			
					
			}
			
			allYearsCapacityArray.add(jsonObj);
		}
		return allYearsCapacityArray;
	}
	private JSONObject getTotalCapacityOfYear(JSONArray allYearsQuantity,int startDate,int endDate)
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
	private double round(double value, int places) {	    

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

}
