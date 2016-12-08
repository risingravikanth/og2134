package com.oganalysis.helper;

import java.text.DecimalFormat;
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
		
		
		final String displayType="company";
//		This is the  array return value of capacityByCountry
		JSONArray capacityByCompany=new JSONArray();
		
//		-----------------------Start of Liquefaction-----------------------
		
		Map<String,Map<Integer,Double>> liquefaction=capacityData.get(LIQUEFACTION);
		JSONObject capacityLiquefactionJsonObj=createCapacityLiquefactionRes(liquefaction, startDateVal, endDateVal, displayType);
		System.out.println(capacityLiquefactionJsonObj.toJSONString());
		
		// --------------------end of Liquefaction-----------------------
		
		// -------------------Start of Regasifcation-------------------
		Map<String,Map<Integer,Double>> regasification=capacityData.get(REGASIFICATION);
		
		JSONObject capacityRegasificationJsonObj=createCapacityRegasificationRes(regasification, startDateVal, endDateVal, displayType);
					
		
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
		final String displayType="terminal";
//		-----------------------Start of Liquefaction-----------------------
		Map<String,Map<Integer,Double>> liquefaction=capacityData.get(LIQUEFACTION);
				 
		JSONObject capacityLiquefactionJsonObj=createCapacityLiquefactionRes(liquefaction, startDateVal, endDateVal, displayType);	
		System.out.println(capacityLiquefactionJsonObj.toJSONString());
		
		// --------------------end of Liquefaction-----------------------
		
		// -------------------Start of Regasifcation-------------------
		Map<String,Map<Integer,Double>> regasification=capacityData.get(REGASIFICATION);
		
		JSONObject capacityRegasificationJsonObj=createCapacityRegasificationRes(regasification, startDateVal, endDateVal, displayType);
		
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
		final String displayType="country";
//		-----------------------Start of Liquefaction-----------------------
		Map<String,Map<Integer,Double>> liquefaction=capacityData.get(LIQUEFACTION);
				 
		JSONObject capacityLiquefactionJsonObj=createCapacityLiquefactionRes(liquefaction, startDateVal, endDateVal, displayType);
		System.out.println(capacityLiquefactionJsonObj.toJSONString());
		
		// --------------------end of Liquefaction-----------------------
		
		// -------------------Start of Regasifcation-------------------
		Map<String,Map<Integer,Double>> regasification=capacityData.get(REGASIFICATION);
		
		JSONObject capacityRegasificationJsonObj=createCapacityRegasificationRes(regasification, startDateVal, endDateVal, displayType);
		
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
//		DecimalFormat df=new DecimalFormat(".#");
		for(Object nameObj:nameSet)
		{
			JSONObject jsonObj=new JSONObject();
			Map<Integer,Double> yearMap=capacityData.get(nameObj);
			jsonObj.put("name",(String)nameObj);
			for(int yearCount=startDate;yearCount<=endDate;yearCount++)
			{
				if(yearMap.containsKey(yearCount))
				{
//					Double capacity=Double.valueOf(df.format(yearMap.get(yearCount)));
					jsonObj.put(yearCount,yearMap.get(yearCount));
				}
				else
					jsonObj.put(yearCount,0.0);			
					
			}
			
			allYearsCapacityArray.add(jsonObj);
		}
		return allYearsCapacityArray;
	}
	public JSONObject createCapacityLiquefactionRes(Map<String,Map<Integer,Double>> liquefaction,int startDate,int endDate,String displayType)
	{
				
		JSONObject capacityLiquefactionJsonObj=new JSONObject();	
		JSONArray allYearsLiqueCapacityByCompany=getAllYearsCapacity(liquefaction, startDate, endDate);
		JSONObject liqueTotalCapacityOfYear=getTotalCapacityOfYear(allYearsLiqueCapacityByCompany,startDate,endDate);	
		capacityLiquefactionJsonObj.put(displayType, allYearsLiqueCapacityByCompany);
		capacityLiquefactionJsonObj.put("type", LIQUEFACTION);
		capacityLiquefactionJsonObj.put("totalCapacity", liqueTotalCapacityOfYear);
		return capacityLiquefactionJsonObj;
	}
	public JSONObject createCapacityRegasificationRes(Map<String,Map<Integer,Double>> regasification,int startDate,int endDate,String displayType)
	{
			
		JSONObject capacityRegasificationJsonObj=new JSONObject();
		JSONArray allYearsRegasCapacityByCompany=getAllYearsCapacity(regasification, startDate, endDate);
		JSONObject regastotalCapacityOfYear=getTotalCapacityOfYear(allYearsRegasCapacityByCompany,startDate,endDate);
		
		capacityRegasificationJsonObj.put(displayType, allYearsRegasCapacityByCompany);
		capacityRegasificationJsonObj.put("type", REGASIFICATION);
		capacityRegasificationJsonObj.put("totalCapacity", regastotalCapacityOfYear);
		
		return capacityRegasificationJsonObj;
	}
	public JSONObject createTerminalDataRes(Map terminalData)
	{
		JSONObject jsonTerminalData=new JSONObject();
		Set<String> keys=terminalData.keySet();
		for(String key:keys)
		{
			if(null!=key && key.equalsIgnoreCase("processingCapacity"))
			{
				
				Map<Integer,Double> pcd=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject processingCapJson=createThroughOutPeriodData(pcd);
				jsonTerminalData.put(key, processingCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase("trainsOrVaporizers"))
			{
				Map<Integer,Double> tov=(Map<Integer,Double>)terminalData.get(key);
				JSONObject tovJson=createThroughOutPeriodData(tov);
				jsonTerminalData.put(key,tovJson);
			}
			else if(null!=key && key.equalsIgnoreCase("storageCapacity"))
			{
				Map<Integer,Double> sc=(Map<Integer,Double>)terminalData.get(key);
				JSONObject scJson=createThroughOutPeriodData(sc);
				jsonTerminalData.put(key,scJson);
			}
			else if(null!=key && key.equalsIgnoreCase("storageTanks"))
			{
				Map<Integer,Double> st=(Map<Integer,Double>)terminalData.get(key);
				JSONObject stJson=createThroughOutPeriodData(st);
				jsonTerminalData.put(key,stJson);
			}
			else if(null!=key && key.equalsIgnoreCase("ownership"))
			{
				List<Map<String,String>> ownerShipList=(List<Map<String,String>>)terminalData.get(key);
				jsonTerminalData.put(key, createOwnership(ownerShipList));
			}
			else if(null!=key && key.equalsIgnoreCase("constructionPeriod"))
			{
				List<Map<String,String>> constructionPeriodList=(List<Map<String,String>>)terminalData.get(key);
				jsonTerminalData.put(key,createConstructionPeriod(constructionPeriodList));
			}
			else if(null!=key && key.equalsIgnoreCase("constructionDetails"))
			{
				List<Map<String,String>> constructionDetailsList=(List<Map<String,String>>)terminalData.get(key);
				jsonTerminalData.put(key,createConstructionDetails(constructionDetailsList));
			}
			else
				jsonTerminalData.put(key,terminalData.get(key));
		}
		return jsonTerminalData;
	}
	private JSONObject createThroughOutPeriodData(Map<Integer,Double> throughtData)
	{
		JSONObject processingCapJson=new JSONObject();
		Set<Integer> processingCapacityKeys=throughtData.keySet();
		for(Integer pdcKey:processingCapacityKeys)
			processingCapJson.put(pdcKey,throughtData.get(pdcKey));
		
		return processingCapJson;
	}
	private JSONArray createOwnership(List<Map<String,String>> ownerShipList)
	{
		JSONArray ownerShipArray=new JSONArray();
		JSONObject ownerShipJsonObj=null;
		for(Map<String,String> ownership:ownerShipList)
		{
			ownerShipJsonObj=new JSONObject();
			ownerShipJsonObj.put("equityPartner",ownership.get("equityPartner"));
			ownerShipJsonObj.put("equityStake",ownership.get("equityStake"));
			ownerShipArray.add(ownerShipJsonObj);
		}
		return ownerShipArray;
	}
	private JSONArray createConstructionPeriod(List<Map<String,String>> constructionPeriodList)
	{
		JSONArray constructionPeriodArray=new JSONArray();
		JSONObject constructionPeriodObj=null;
		for(Map<String,String> constructionPeriod:constructionPeriodList)
		{
			constructionPeriodObj=new JSONObject();
			constructionPeriodObj.put("constructionStart",constructionPeriod.get("constructionStart"));
			constructionPeriodObj.put("constructionEnd",constructionPeriod.get("constructionEnd"));
			constructionPeriodArray.add(constructionPeriodObj);
		}
		return constructionPeriodArray;
	}
	private JSONArray createConstructionDetails(List<Map<String,String>> constructionDetailsList)
	{
		JSONArray constructionDetailsArray=new JSONArray();
		JSONObject constructionDetailsObj=null;
		for(Map<String,String> constructionPeriod:constructionDetailsList)
		{
			constructionDetailsObj=new JSONObject();
			if(null!=constructionPeriod.get("constructionCompanyName"))
			constructionDetailsObj.put("constructionCompanyName",constructionPeriod.get("constructionCompanyName"));
			else
				constructionDetailsObj.put("constructionCompanyName","");
			if(null!=constructionPeriod.get("constructionContractDetails"))
			constructionDetailsObj.put("constructionContractDetails",constructionPeriod.get("constructionContractDetails"));
			else
				constructionDetailsObj.put("constructionContractDetails","");
			constructionDetailsArray.add(constructionDetailsObj);
		}
		return constructionDetailsArray;
	}
}
