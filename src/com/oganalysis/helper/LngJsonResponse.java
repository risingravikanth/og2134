package com.oganalysis.helper;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import static com.oganalysis.constants.ApplicationConstants.*;

public class LngJsonResponse {
		
	
	public String createInfrastructureRes(Map<String,List<Map<String,String>>> infrastructureMap)
	{
		
		JSONArray infrastructureArray=new JSONArray();
		//--- Liquefaction -----------
		List<Map<String,String>> liquefactionList=infrastructureMap.get(LNG_LIQUEFACTION);
		JSONObject infraLiquefactionJsonObj=new JSONObject();
		infraLiquefactionJsonObj.put(TYPE, LNG_LIQUEFACTION);
		
		JSONArray infraLiquefactionArray=new JSONArray();
		JSONObject infraLiquefactionDataObj=null;
		for(Map<String,String> map:liquefactionList)
		{
			infraLiquefactionDataObj=new JSONObject();
			infraLiquefactionDataObj.put(TERMINALNAME,map.get(TERMINALNAME));
			infraLiquefactionDataObj.put(STATUS,map.get(STATUS));
			infraLiquefactionDataObj.put(STARTYEAR,map.get(STARTYEAR));
			infraLiquefactionDataObj.put(LOCATION,map.get(LOCATION));
			infraLiquefactionDataObj.put(TECHNOLOGY,map.get(TECHNOLOGY));
			infraLiquefactionDataObj.put(TRAIN,map.get(TRAIN));
			infraLiquefactionDataObj.put(OPERATOR,map.get(OPERATOR));
			infraLiquefactionDataObj.put(STORAGECAPACITY,map.get(STORAGECAPACITY));
			infraLiquefactionDataObj.put(TANKS,map.get(TANKS));
			
			infraLiquefactionArray.add(infraLiquefactionDataObj);
		}
		infraLiquefactionJsonObj.put(DATA_KEY, infraLiquefactionArray);
		//------ End Liquefacion --------------------
		
		//--- Regasification -----------
		List<Map<String,String>> regasificationList=infrastructureMap.get(LNG_REGASIFICATION);
		JSONObject infraRegasificationJsonObj=new JSONObject();
		infraRegasificationJsonObj.put(TYPE, LNG_REGASIFICATION);
				
		JSONArray infraRegasificationArray=new JSONArray();
		JSONObject infraRegasificationDataObj=null;
		for(Map<String,String> map:regasificationList)
		{
			infraRegasificationDataObj=new JSONObject();
			infraRegasificationDataObj.put(TERMINALNAME,map.get(TERMINALNAME));
			infraRegasificationDataObj.put(STATUS,map.get(STATUS));
			infraRegasificationDataObj.put(STARTYEAR,map.get(STARTYEAR));
			infraRegasificationDataObj.put(LOCATION,map.get(LOCATION));
			infraRegasificationDataObj.put(TECHNOLOGY,map.get(TECHNOLOGY));
			infraRegasificationDataObj.put(TRAIN,map.get(TRAIN));
			infraRegasificationDataObj.put(OPERATOR,map.get(OPERATOR));
			infraRegasificationDataObj.put(STORAGECAPACITY,map.get(STORAGECAPACITY));
			infraRegasificationDataObj.put(TANKS,map.get(TANKS));
					
			infraRegasificationArray.add(infraRegasificationDataObj);
		}
		infraRegasificationJsonObj.put(DATA_KEY, infraRegasificationArray);
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
		
		Map<String,Map<Integer,Double>> liquefaction=capacityData.get(LNG_LIQUEFACTION);
		JSONObject capacityLiquefactionJsonObj=createCapacityLiquefactionRes(liquefaction, startDateVal, endDateVal, COMPANY);
//		System.out.println(capacityLiquefactionJsonObj.toJSONString());
		
		// --------------------end of Liquefaction-----------------------
		
		// -------------------Start of Regasifcation-------------------
		Map<String,Map<Integer,Double>> regasification=capacityData.get(LNG_REGASIFICATION);
		
		JSONObject capacityRegasificationJsonObj=createCapacityRegasificationRes(regasification, startDateVal, endDateVal, COMPANY);
					
		
//		System.out.println(capacityRegasificationJsonObj.toJSONString());
		
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
		Map<String,Map<Integer,Double>> liquefaction=capacityData.get(LNG_LIQUEFACTION);
				 
		JSONObject capacityLiquefactionJsonObj=createCapacityLiquefactionRes(liquefaction, startDateVal, endDateVal, TERMINAL);	
//		System.out.println(capacityLiquefactionJsonObj.toJSONString());
		
		// --------------------end of Liquefaction-----------------------
		
		// -------------------Start of Regasifcation-------------------
		Map<String,Map<Integer,Double>> regasification=capacityData.get(LNG_REGASIFICATION);
		
		JSONObject capacityRegasificationJsonObj=createCapacityRegasificationRes(regasification, startDateVal, endDateVal, TERMINAL);
		
//		System.out.println(capacityRegasificationJsonObj.toJSONString());
		
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
		Map<String,Map<Integer,Double>> liquefaction=capacityData.get(LNG_LIQUEFACTION);
				 
		JSONObject capacityLiquefactionJsonObj=createCapacityLiquefactionRes(liquefaction, startDateVal, endDateVal, COUNTRY);
//		System.out.println(capacityLiquefactionJsonObj.toJSONString());
		
		// --------------------end of Liquefaction-----------------------
		
		// -------------------Start of Regasifcation-------------------
		Map<String,Map<Integer,Double>> regasification=capacityData.get(LNG_REGASIFICATION);
		
		JSONObject capacityRegasificationJsonObj=createCapacityRegasificationRes(regasification, startDateVal, endDateVal, COUNTRY);
		
//		System.out.println(capacityRegasificationJsonObj.toJSONString());
		
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
			double capacityVal=0;
			for(int i=0;i<allYearsCapacity.size();i++)
			{
				JSONObject yearJsonObj=(JSONObject)allYearsCapacity.get(i);
				if(null!=yearJsonObj.get(yearCount) && !yearJsonObj.get(yearCount).equals(BLANK))
					capacityVal=(Double)yearJsonObj.get(yearCount);
				else
					capacityVal=0.0;
				totalCapacity=totalCapacity+capacityVal;
			}
			totalCapacity=round(totalCapacity,2);
			totalCapcityJsonObj.put(yearCount, String.valueOf(totalCapacity));
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
	public JSONObject createCapacityLiquefactionRes(Map<String,Map<Integer,Double>> liquefaction,int startDate,int endDate,String displayType)
	{
				
		JSONObject capacityLiquefactionJsonObj=new JSONObject();	
		JSONArray allYearsLiqueCapacityByCompany=getAllYearsCapacity(liquefaction, startDate, endDate);
		JSONObject liqueTotalCapacityOfYear=getTotalCapacityOfYear(allYearsLiqueCapacityByCompany,startDate,endDate);	
		capacityLiquefactionJsonObj.put(displayType, allYearsLiqueCapacityByCompany);
		capacityLiquefactionJsonObj.put(TYPE, LNG_LIQUEFACTION);
		capacityLiquefactionJsonObj.put(JSON_TOTALCAPACITY, liqueTotalCapacityOfYear);
		return capacityLiquefactionJsonObj;
	}
	public JSONObject createCapacityRegasificationRes(Map<String,Map<Integer,Double>> regasification,int startDate,int endDate,String displayType)
	{
			
		JSONObject capacityRegasificationJsonObj=new JSONObject();
		JSONArray allYearsRegasCapacityByCompany=getAllYearsCapacity(regasification, startDate, endDate);
		JSONObject regastotalCapacityOfYear=getTotalCapacityOfYear(allYearsRegasCapacityByCompany,startDate,endDate);
		
		capacityRegasificationJsonObj.put(displayType, allYearsRegasCapacityByCompany);
		capacityRegasificationJsonObj.put(TYPE, LNG_REGASIFICATION);
		capacityRegasificationJsonObj.put(JSON_TOTALCAPACITY, regastotalCapacityOfYear);
		
		return capacityRegasificationJsonObj;
	}
	public JSONObject createTerminalDataRes(Map terminalData)
	{
		JSONObject jsonTerminalData=new JSONObject();
		Set<String> keys=terminalData.keySet();
		jsonTerminalData.put(TERMINALNAME, terminalData.get(TERMINALNAME));
		jsonTerminalData.put(REGION, terminalData.get(REGION));
		jsonTerminalData.put(COUNTRY, terminalData.get(COUNTRY));
		jsonTerminalData.put(LOCATION, terminalData.get(LOCATION));
		jsonTerminalData.put(TYPE, terminalData.get(TYPE));
		jsonTerminalData.put(ONSHORE_OR_OFFSHORE, terminalData.get(ONSHORE_OR_OFFSHORE));
		jsonTerminalData.put(STATUS, terminalData.get(STATUS));
		jsonTerminalData.put(OTHER_DETAILS, terminalData.get(OTHER_DETAILS));
		jsonTerminalData.put(EXPECTEDSTARTUP, terminalData.get(EXPECTEDSTARTUP));
		jsonTerminalData.put(OPERATOR, terminalData.get(OPERATOR));
		List<Map<String,String>> ownerShipList=(List<Map<String,String>>)terminalData.get(OWNERSHIP);
		jsonTerminalData.put(OWNERSHIP, createOwnership(ownerShipList));
//		for(String key:keys)
//		{
////			if(null!=key && key.equalsIgnoreCase(PROCESSINGCAPACITY))
////			{
////				
////				Map<Integer,Double> pcd=(Map<Integer,Double>)terminalData.get(key);	
////				JSONObject processingCapJson=createThroughOutPeriodData(pcd);
////				jsonTerminalData.put(key, processingCapJson);
////			}
////			else if(null!=key && key.equalsIgnoreCase(TRAINSORVAPORIZERS))
////			{
////				Map<Integer,Double> tov=(Map<Integer,Double>)terminalData.get(key);
////				JSONObject tovJson=createThroughOutPeriodData(tov);
////				jsonTerminalData.put(key,tovJson);
////			}
////			else if(null!=key && key.equalsIgnoreCase(STORAGECAPACITY))
////			{
////				Map<Integer,Double> sc=(Map<Integer,Double>)terminalData.get(key);
////				JSONObject scJson=createThroughOutPeriodData(sc);
////				jsonTerminalData.put(key,scJson);
////			}
////			else if(null!=key && key.equalsIgnoreCase(STORAGETANKS))
////			{
////				Map<Integer,Double> st=(Map<Integer,Double>)terminalData.get(key);
////				JSONObject stJson=createThroughOutPeriodData(st);
////				jsonTerminalData.put(key,stJson);
////			}
//			if(null!=key && key.equalsIgnoreCase(OWNERSHIP))
//			{
//				List<Map<String,String>> ownerShipList=(List<Map<String,String>>)terminalData.get(key);
//				jsonTerminalData.put(key, createOwnership(ownerShipList));
//			}
////			else if(null!=key && key.equalsIgnoreCase(CONSTRUCTIONPERIOD))
////			{
////				List<Map<String,String>> constructionPeriodList=(List<Map<String,String>>)terminalData.get(key);
////				jsonTerminalData.put(key,createConstructionPeriod(constructionPeriodList));
////			}
////			else if(null!=key && key.equalsIgnoreCase(CONSTRUCTIONDETAILS))
////			{
////				List<Map<String,String>> constructionDetailsList=(List<Map<String,String>>)terminalData.get(key);
////				jsonTerminalData.put(key,createConstructionDetails(constructionDetailsList));
////			}
//			else if(null!=key && !key.equalsIgnoreCase(PROCESSINGCAPACITY) && !key.equalsIgnoreCase(TRAINSORVAPORIZERS) && !key.equalsIgnoreCase(STORAGECAPACITY) && !key.equalsIgnoreCase(STORAGETANKS) && !key.equalsIgnoreCase(CONSTRUCTIONPERIOD) && !key.equalsIgnoreCase(CONSTRUCTIONDETAILS))
//				jsonTerminalData.put(key,terminalData.get(key));
//		}
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
			ownerShipJsonObj.put(EQUITYPARTNER,ownership.get(EQUITYPARTNER));
			ownerShipJsonObj.put(EQUITYSTAKE,ownership.get(EQUITYSTAKE));
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
			constructionPeriodObj.put(CONSTRUCTIONSTART,constructionPeriod.get(CONSTRUCTIONSTART));
			constructionPeriodObj.put(CONSTRUCTIONEND,constructionPeriod.get(CONSTRUCTIONEND));
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
			if(null!=constructionPeriod.get(CONSTRUCTIONCOMPANYNAME))
			constructionDetailsObj.put(CONSTRUCTIONCOMPANYNAME,constructionPeriod.get(CONSTRUCTIONCOMPANYNAME));
			else
				constructionDetailsObj.put(CONSTRUCTIONCOMPANYNAME,BLANK);
			if(null!=constructionPeriod.get(CONSTRUCTIONCONTRACTDETAILS))
			constructionDetailsObj.put(CONSTRUCTIONCONTRACTDETAILS,constructionPeriod.get(CONSTRUCTIONCONTRACTDETAILS));
			else
				constructionDetailsObj.put(CONSTRUCTIONCONTRACTDETAILS,BLANK);
			constructionDetailsArray.add(constructionDetailsObj);
		}
		return constructionDetailsArray;
	}
	private double round(double value, int places) {	    

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
}
