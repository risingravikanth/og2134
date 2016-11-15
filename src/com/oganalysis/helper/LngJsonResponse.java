package com.oganalysis.helper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class LngJsonResponse {
	public static final String REGASIFICATION="Regasification";
	public static final String LIQUEFACTION="Liquefaction";
	
	public String createInfrastructureRes(Map<String,List<Map<String,String>>> infrastructureMap)
	{
		
		JSONArray infrastructureArray=new JSONArray();
		//--- Liquefaction -----------
		List<Map<String,String>> liquefactionList=infrastructureMap.get(LIQUEFACTION);
		JSONObject infraLiquefactionJsonObj=new JSONObject();
		infraLiquefactionJsonObj.put("type", LIQUEFACTION);
		
		JSONArray infraLiquefactionArray=new JSONArray();
		JSONObject infraLiquefactionDataObj=new JSONObject();
		for(Map<String,String> map:liquefactionList)
		{
			infraLiquefactionDataObj.put("terminalName",map.get("terminal"));
			infraLiquefactionDataObj.put("status",map.get("status"));
			infraLiquefactionDataObj.put("startYear",map.get("startYear"));
			infraLiquefactionDataObj.put("location",map.get("location"));
			infraLiquefactionDataObj.put("technology",map.get("technology"));
			infraLiquefactionDataObj.put("train",map.get("train"));
			infraLiquefactionDataObj.put("operator",map.get("operator"));
			infraLiquefactionDataObj.put("storageCapacity",map.get("storageCapacity"));
			infraLiquefactionDataObj.put("tanks",map.get("tanks"));
			
			infraLiquefactionArray.add(infraLiquefactionDataObj);
		}
		infraLiquefactionJsonObj.put("data", infraLiquefactionArray);
		//------ End Liquefacion --------------------
		
		//--- Regasification -----------
				List<Map<String,String>> regasificationList=infrastructureMap.get(REGASIFICATION);
				JSONObject infraRegasificationJsonObj=new JSONObject();
				infraRegasificationJsonObj.put("type", REGASIFICATION);
				
				JSONArray infraRegasificationArray=new JSONArray();
				JSONObject infraRegasificationDataObj=new JSONObject();
				for(Map<String,String> map:regasificationList)
				{
					infraRegasificationDataObj.put("terminalName",map.get("terminal"));
					infraRegasificationDataObj.put("status",map.get("status"));
					infraRegasificationDataObj.put("startYear",map.get("startYear"));
					infraRegasificationDataObj.put("location",map.get("location"));
					infraRegasificationDataObj.put("technology",map.get("technology"));
					infraRegasificationDataObj.put("train",map.get("train"));
					infraRegasificationDataObj.put("operator",map.get("operator"));
					infraRegasificationDataObj.put("storageCapacity",map.get("storageCapacity"));
					infraRegasificationDataObj.put("tanks",map.get("tanks"));
					
					infraRegasificationArray.add(infraRegasificationDataObj);
				}
				infraRegasificationJsonObj.put("data", infraRegasificationArray);
			//------- End Regasification --------------
			infrastructureArray.add(infraLiquefactionJsonObj);
			infrastructureArray.add(infraRegasificationJsonObj);
			
		return infrastructureArray.toJSONString();
	}
	public String createCapacityByCompanyRes(Map<String,Map<String,Map<Integer,Double>>> capacityData,String startDate,String endDate)
	{

		
		
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		
//		This is the  array return value of capacityByCountry
		JSONArray capacityByCompany=new JSONArray();
		
//		-----------------------Start of Liquefaction-----------------------
		Map<String,Map<Integer,Double>> liquefaction=capacityData.get(LIQUEFACTION);
				 
		JSONObject capacityLiquefactionJsonObj=new JSONObject();	
		JSONArray allYearsLiqueCapacityByCompany=getAllYearsCapacity(liquefaction, startDateVal, endDateVal);
		JSONObject liqueTotalCapacityOfYear=getTotalCapacityOfYear(allYearsLiqueCapacityByCompany,startDateVal,endDateVal);	
		capacityLiquefactionJsonObj.put("company", allYearsLiqueCapacityByCompany);
		capacityLiquefactionJsonObj.put("type", LIQUEFACTION);
		capacityLiquefactionJsonObj.put("totalCapacity", liqueTotalCapacityOfYear);
		
		System.out.println(capacityLiquefactionJsonObj.toJSONString());
		
		// --------------------end of Liquefaction-----------------------
		
		// -------------------Start of Regasifcation-------------------
		Map<String,Map<Integer,Double>> regasification=capacityData.get(REGASIFICATION);
		
		 
		JSONObject capacityRegasificationJsonObj=new JSONObject();	
		JSONArray allYearsRegasCapacityByCompany=getAllYearsCapacity(regasification, startDateVal, endDateVal);
		JSONObject regastotalCapacityOfYear=getTotalCapacityOfYear(allYearsRegasCapacityByCompany,startDateVal,endDateVal);
		
		capacityRegasificationJsonObj.put("company", allYearsRegasCapacityByCompany);
		capacityRegasificationJsonObj.put("type", REGASIFICATION);
		capacityRegasificationJsonObj.put("totalCapacity", regastotalCapacityOfYear);
		
		System.out.println(capacityRegasificationJsonObj.toJSONString());
		
		// ------------------- End of Regasifaction---------------------------
		
		capacityByCompany.add(capacityLiquefactionJsonObj);
		capacityByCompany.add(capacityRegasificationJsonObj);
		return capacityByCompany.toJSONString();
		
	
	}
	public String createCapacityByTerminalRes(Map<String,Map<String,Map<Integer,Double>>> capacityData,String startDate,String endDate)
	{

		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		
//		This is the  array return value of capacityByCountry
		JSONArray capacityByTerminal=new JSONArray();
		
//		-----------------------Start of Liquefaction-----------------------
		Map<String,Map<Integer,Double>> liquefaction=capacityData.get(LIQUEFACTION);
				 
		JSONObject capacityLiquefactionJsonObj=new JSONObject();	
		JSONArray allYearsLiqueCapacityByTerminal=getAllYearsCapacity(liquefaction, startDateVal, endDateVal);
		JSONObject liqueTotalCapacityOfYear=getTotalCapacityOfYear(allYearsLiqueCapacityByTerminal,startDateVal,endDateVal);	
		capacityLiquefactionJsonObj.put("terminal", allYearsLiqueCapacityByTerminal);
		capacityLiquefactionJsonObj.put("type", LIQUEFACTION);
		capacityLiquefactionJsonObj.put("totalCapacity", liqueTotalCapacityOfYear);
		
		System.out.println(capacityLiquefactionJsonObj.toJSONString());
		
		// --------------------end of Liquefaction-----------------------
		
		// -------------------Start of Regasifcation-------------------
		Map<String,Map<Integer,Double>> regasification=capacityData.get(REGASIFICATION);
		
		 
		JSONObject capacityRegasificationJsonObj=new JSONObject();	
		JSONArray allYearsRegasCapacityByTerminal=getAllYearsCapacity(regasification, startDateVal, endDateVal);
		JSONObject regastotalCapacityOfYear=getTotalCapacityOfYear(allYearsRegasCapacityByTerminal,startDateVal,endDateVal);
		
		capacityRegasificationJsonObj.put("terminal", allYearsRegasCapacityByTerminal);
		capacityRegasificationJsonObj.put("type", REGASIFICATION);
		capacityRegasificationJsonObj.put("totalCapacity", regastotalCapacityOfYear);
		
		System.out.println(capacityRegasificationJsonObj.toJSONString());
		
		// ------------------- End of Regasifaction---------------------------
		
		capacityByTerminal.add(capacityLiquefactionJsonObj);
		capacityByTerminal.add(capacityRegasificationJsonObj);
		return capacityByTerminal.toJSONString();
		
	
	}
	public String createCapacityByCountryRes(Map<String,Map<String,Map<Integer,Double>>> capacityData,String startDate,String endDate)
	{
		
		
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		
//		This is the  array return value of capacityByCountry
		JSONArray capacityByCountry=new JSONArray();
		
//		-----------------------Start of Liquefaction-----------------------
		Map<String,Map<Integer,Double>> liquefaction=capacityData.get(LIQUEFACTION);
				 
		JSONObject capacityLiquefactionJsonObj=new JSONObject();	
		JSONArray allYearsLiqueCapacityByCountry=getAllYearsCapacity(liquefaction, startDateVal, endDateVal);
		JSONObject liqueTotalCapacityOfYear=getTotalCapacityOfYear(allYearsLiqueCapacityByCountry,startDateVal,endDateVal);	
		capacityLiquefactionJsonObj.put("country", allYearsLiqueCapacityByCountry);
		capacityLiquefactionJsonObj.put("type", LIQUEFACTION);
		capacityLiquefactionJsonObj.put("totalCapacity", liqueTotalCapacityOfYear);
		
		System.out.println(capacityLiquefactionJsonObj.toJSONString());
		
		// --------------------end of Liquefaction-----------------------
		
		// -------------------Start of Regasifcation-------------------
		Map<String,Map<Integer,Double>> regasification=capacityData.get(REGASIFICATION);
		
		 
		JSONObject capacityRegasificationJsonObj=new JSONObject();	
		JSONArray allYearsRegasCapacityByCountry=getAllYearsCapacity(regasification, startDateVal, endDateVal);
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
	private JSONObject getTotalCapacityOfYear(JSONArray allYearsCapacity,int startDate,int endDate)
	{
		JSONObject totalCapcityJsonObj=new JSONObject();
		for(int yearCount=startDate;yearCount<=endDate;yearCount++)
		{
			double totalCapacity=0;
			for(int i=0;i<allYearsCapacity.size();i++)
			{
				JSONObject yearJsonObj=(JSONObject)allYearsCapacity.get(i);
				double capacityVal=(Double)yearJsonObj.get(yearCount);
				totalCapacity=totalCapacity+capacityVal;
			}
			totalCapcityJsonObj.put(yearCount, totalCapacity);
		}
		return totalCapcityJsonObj;
	}
	private JSONArray getAllYearsCapacity(Map<String,Map<Integer,Double>> capacityData,int startDate,int endDate)
	{
		JSONArray allYearsCapacityArray=new JSONArray();
		Set<String> nameSet=capacityData.keySet();
		for(Object nameObj:nameSet)
		{
			JSONObject jsonObj=new JSONObject();
			Map<Integer,Double> yearMap=capacityData.get(nameObj);
			jsonObj.put("name",(String)nameObj);
			for(int yearCount=startDate;yearCount<=endDate;yearCount++)
			{
				if(yearMap.containsKey(yearCount))
				{
					jsonObj.put(yearCount,yearMap.get(yearCount));
				}
				else
					jsonObj.put(yearCount,0.0);			
					
			}
			
			allYearsCapacityArray.add(jsonObj);
		}
		return allYearsCapacityArray;
	}
}
