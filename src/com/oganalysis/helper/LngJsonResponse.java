package com.oganalysis.helper;

import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LngJsonResponse {
	public static final String REGASIFICATION="Regasification";
	public static final String LIQUEFACTION="Liquefaction";
	
	public String createCapacityByCountryRes(Map<String,Map<String,Map<Integer,Double>>> capacityData,String startDate,String endDate)
	{
		String response=null;
		
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		
//		This is the  array return value of capacityByCountry
		JSONArray capacityByCountry=new JSONArray();
		
//		-----------------------Start of Liquefaction-----------------------
		Map<String,Map<Integer,Double>> liquefaction=capacityData.get(LIQUEFACTION);
				 
		JSONObject capacityLiquefactionJsonObj=new JSONObject();	
		JSONArray allYearsLiqueCapacityByCountry=getAllYearsCapacityByCountry(liquefaction, startDateVal, endDateVal);
		JSONObject liqueTotalCapacityOfYear=getTotalCapacityOfYear(allYearsLiqueCapacityByCountry,startDateVal,endDateVal);	
		capacityLiquefactionJsonObj.put("country", allYearsLiqueCapacityByCountry);
		capacityLiquefactionJsonObj.put("type", LIQUEFACTION);
		capacityLiquefactionJsonObj.put("totalCapacity", liqueTotalCapacityOfYear);
		
		System.out.println(capacityLiquefactionJsonObj.toJSONString());
		
		// --------------------end of Liquefaction-----------------------
		
		// -------------------Start of Regasifcation-------------------
		Map<String,Map<Integer,Double>> regasification=capacityData.get(REGASIFICATION);
		
		 
		JSONObject capacityRegasificationJsonObj=new JSONObject();	
		JSONArray allYearsRegasCapacityByCountry=getAllYearsCapacityByCountry(regasification, startDateVal, endDateVal);
		JSONObject regastotalCapacityOfYear=getTotalCapacityOfYear(allYearsRegasCapacityByCountry,startDateVal,endDateVal);
		
		capacityRegasificationJsonObj.put("country", allYearsRegasCapacityByCountry);
		capacityRegasificationJsonObj.put("type", REGASIFICATION);
		capacityRegasificationJsonObj.put("totalCapacity", regastotalCapacityOfYear);
		
		System.out.println(capacityRegasificationJsonObj.toJSONString());
		
		// ------------------- End of Regasifaction---------------------------
		
		capacityByCountry.add(capacityLiquefactionJsonObj);
		capacityByCountry.add(capacityRegasificationJsonObj);
		return capacityByCountry.toJSONString();
		
	}
	private JSONObject getTotalCapacityOfYear(JSONArray allYearsCapacityByCountry,int startDate,int endDate)
	{
		JSONObject totalCapcityJsonObj=new JSONObject();
		for(int yearCount=startDate;yearCount<=endDate;yearCount++)
		{
			double totalCapacity=0;
			for(int i=0;i<allYearsCapacityByCountry.size();i++)
			{
				JSONObject yearJsonObj=(JSONObject)allYearsCapacityByCountry.get(i);
				double capacityVal=(Double)yearJsonObj.get(yearCount);
				totalCapacity=totalCapacity+capacityVal;
			}
			totalCapcityJsonObj.put(yearCount, totalCapacity);
		}
		return totalCapcityJsonObj;
	}
	private JSONArray getAllYearsCapacityByCountry(Map<String,Map<Integer,Double>> capacityDataByCountry,int startDate,int endDate)
	{
		JSONArray countryArray=new JSONArray();
		Set<String> countrySet=capacityDataByCountry.keySet();
		for(Object countryName:countrySet)
		{
			JSONObject countryJsonObj=new JSONObject();
			Map<Integer,Double> yearMap=capacityDataByCountry.get(countryName);
			countryJsonObj.put("name",(String)countryName);
			for(int yearCount=startDate;yearCount<=endDate;yearCount++)
			{
				if(yearMap.containsKey(yearCount))
				{
					countryJsonObj.put(yearCount,yearMap.get(yearCount));
				}
				else
					countryJsonObj.put(yearCount,0.0);			
					
			}
			
			countryArray.add(countryJsonObj);
		}
		return countryArray;
	}
}
