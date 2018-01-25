package com.oganalysis.helper;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class StorageJsonResponse {
	public String createInfrastructureRes(List<Map<String,String>> infraList)
	{
		
		JSONObject infraJsonObj=new JSONObject();			
		JSONArray infraArray=new JSONArray();
		JSONObject infraDataObj=null;
		for(Map<String,String> map:infraList)
		{
			infraDataObj=new JSONObject();
			infraDataObj.put(TERMINALNAME,map.get(TERMINALNAME));
			infraDataObj.put(LOCATION,map.get(LOCATION));
			infraDataObj.put(OPERATOR, map.get(OPERATOR));
			infraDataObj.put(STORAGE_CAPACITY,map.get(STORAGE_CAPACITY));
					
			infraArray.add(infraDataObj);						
		}
		infraJsonObj.put(DATA_KEY, infraArray);
		return infraJsonObj.toString();
	}
	public String createCapacityByTerminal(Map<String,Map<Integer,Double>> capacityData,int startYear,int endYear)
	{
		JSONArray capacityByTerminal=new JSONArray();
		
		JSONObject capacityJsonObj=new JSONObject();	
		JSONArray allYearsCapacityByTerminal=getAllYearsCapacity(capacityData, startYear, endYear);
		JSONObject totalCapacityOfYear=getTotalCapacityOfYear(allYearsCapacityByTerminal,startYear,endYear);	
		capacityJsonObj.put(TERMINAL, allYearsCapacityByTerminal);
		capacityJsonObj.put(JSON_TOTALCAPACITY, totalCapacityOfYear);								
		capacityByTerminal.add(capacityJsonObj);
				
		return capacityByTerminal.toJSONString();
	}
	public String createCapacityByCountry(Map<String,Map<Integer,Double>> capacityData,int startYear,int endYear)
	{
		JSONArray capacityByCountry=new JSONArray();
		
		JSONObject capacityJsonObj=new JSONObject();	
		JSONArray allYearsCapacityByCountry=getAllYearsCapacity(capacityData, startYear, endYear);
		JSONObject totalCapacityOfYear=getTotalCapacityOfYear(allYearsCapacityByCountry,startYear,endYear);	
		capacityJsonObj.put(COUNTRY, allYearsCapacityByCountry);
		capacityJsonObj.put(JSON_TOTALCAPACITY, totalCapacityOfYear);								
		capacityByCountry.add(capacityJsonObj);
				
		return capacityByCountry.toJSONString();
	}
	public String createCapacityByCompany(Map<String,Map<Integer,Double>> capacityData,int startYear,int endYear)
	{					
		JSONArray capacityByCompany=new JSONArray();
				
		JSONObject capacityJsonObj=new JSONObject();	
		JSONArray allYearsCapacityByCompany=getAllYearsCapacity(capacityData, startYear, endYear);
		JSONObject totalCapacityOfYear=getTotalCapacityOfYear(allYearsCapacityByCompany,startYear,endYear);	
		capacityJsonObj.put(COMPANY, allYearsCapacityByCompany);
		capacityJsonObj.put(JSON_TOTALCAPACITY, totalCapacityOfYear);								
		capacityByCompany.add(capacityJsonObj);
				
		return capacityByCompany.toJSONString();
			
	}	
	private JSONArray getAllYearsCapacity(Map<String,Map<Integer,Double>> capacityData,int startYear,int endYear)
	{
		JSONArray allYearsCapacityArray=new JSONArray();
		Set<String> nameSet=capacityData.keySet();
		
		for(Object nameObj:nameSet)
		{
			JSONObject jsonObj=new JSONObject();
			Map<Integer,Double> yearMap=capacityData.get(nameObj);
			jsonObj.put(JSON_NAME,(String)nameObj);
			for(int yearCount=startYear;yearCount<=endYear;yearCount++)
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
	private JSONObject getTotalCapacityOfYear(JSONArray allYearsQuantity,int startYear,int endYear)
	{
		JSONObject totalCapacityJsonObj=new JSONObject();
		for(int yearCount=startYear;yearCount<=endYear;yearCount++)
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
			if(totalCapacity!=0)
				totalCapacityJsonObj.put(yearCount, String.valueOf(totalCapacity));
			else
				totalCapacityJsonObj.put(yearCount, BLANK);
		}
		return totalCapacityJsonObj;
	}	
	public String createTerminalData(Map terminalData)
	{
		JSONObject jsonTerminalData=new JSONObject();
		jsonTerminalData.put(TERMINALNAME,terminalData.get(TERMINALNAME));		
		jsonTerminalData.put(REGION, terminalData.get(REGION));
		jsonTerminalData.put(COUNTRY,terminalData.get(COUNTRY));
		jsonTerminalData.put(LOCATION,terminalData.get(LOCATION));		
		jsonTerminalData.put(STATUS,terminalData.get(STATUS));
		jsonTerminalData.put(EXPECTEDSTARTUP,terminalData.get(STARTUP));
		jsonTerminalData.put(TANKS, terminalData.get(TANKS));
		jsonTerminalData.put(TANKSIZERANGE_MIN,  terminalData.get(TANKSIZERANGE_MIN));
		jsonTerminalData.put(TANKSIZERANGE_MAX,  terminalData.get(TANKSIZERANGE_MAX));
		jsonTerminalData.put(OPERATOR, terminalData.get(OPERATOR));
		List<Map<String,String>> ownerShipList=(List<Map<String,String>>)terminalData.get(OWNERSHIP);
		jsonTerminalData.put(OWNERSHIP, createOwnership(ownerShipList));
		
		return jsonTerminalData.toJSONString();
	}
	private JSONArray createOwnership(List<Map<String,String>> ownerShipList)
	{
		JSONArray ownerShipArray=new JSONArray();
		JSONObject ownerShipJsonObj=null;
		for(Map<String,String> ownership:ownerShipList)
		{
			ownerShipJsonObj=new JSONObject();
			ownerShipJsonObj.put(EQUITYPARTNER,ownership.get(CURRENTOWNERS));
			ownerShipJsonObj.put(EQUITYSTAKE,ownership.get(CURRENTOWNERSHIP));
			ownerShipArray.add(ownerShipJsonObj);
		}
		return ownerShipArray;
	}
	private double round(double value, int places) {	    

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
