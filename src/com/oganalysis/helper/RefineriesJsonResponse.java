package com.oganalysis.helper;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class RefineriesJsonResponse {
	
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
			infraDataObj.put(STATUS,map.get(STATUS));
			infraDataObj.put(STARTYEAR,map.get(STARTYEAR));
			infraDataObj.put(OPERATOR, map.get(OPERATOR));
			infraDataObj.put(CDUCAPACITY,map.get(CDUCAPACITY));
			infraDataObj.put(COKINGCAPACITY,map.get(COKINGCAPACITY));
			infraDataObj.put(FCCCAPACITY,map.get(FCCCAPACITY));
			infraDataObj.put(HYDROCRACKINGCAPACITY,map.get(HYDROCRACKINGCAPACITY));			
			infraArray.add(infraDataObj);
		}
		infraJsonObj.put(DATA_KEY, infraArray);
		return infraJsonObj.toString();
	}
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
	public String createTerminalData(Map terminalData)
	{
		JSONObject jsonTerminalData=new JSONObject();
		Set<String> keys=terminalData.keySet();
		for(String key:keys)
		{
			if(null!=key && key.equalsIgnoreCase(CDUCAPACITY))
			{
				
				Map<Integer,Double> cdu=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject cduCapJson=createCapacityDetails(cdu);
				jsonTerminalData.put(key, cduCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(VDUCAPACITY))
			{
				Map<Integer,Double> vdu=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject vduCapJson=createCapacityDetails(vdu);
				jsonTerminalData.put(key, vduCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(COKINGCAPACITY))
			{
				Map<Integer,Double> coking=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject cokingCapJson=createCapacityDetails(coking);
				jsonTerminalData.put(key, cokingCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(FCCCAPACITY))
			{
				Map<Integer,Double> fcc=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject fccCapJson=createCapacityDetails(fcc);
				jsonTerminalData.put(key, fccCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(HYDROCRACKINGCAPACITY))
			{
				Map<Integer,Double> hydroCracking=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject hydroCrackingCapJson=createCapacityDetails(hydroCracking);
				jsonTerminalData.put(key, hydroCrackingCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(CRUDESTORAGEORTANK))
			{
				Map<Integer,Double> crudeStorageOrTank=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject crudeStorageOrTankCapJson=createCapacityDetails(crudeStorageOrTank);
				jsonTerminalData.put(key, crudeStorageOrTankCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(CRUDESTORAGECAPACITY))
			{
				Map<Integer,Double> crudeStorage=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject crudeStorageCapJson=createCapacityDetails(crudeStorage);
				jsonTerminalData.put(key, crudeStorageCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(VISBREAKINGCAPACITY))
			{
				Map<Integer,Double> visbreaking=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject visbreakingCapJson=createCapacityDetails(visbreaking);
				jsonTerminalData.put(key, visbreakingCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(REFORMERCAPACITY))
			{
				Map<Integer,Double> reformer=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject reformerCapJson=createCapacityDetails(reformer);
				jsonTerminalData.put(key, reformerCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(HYDROTREATINGCAPACITY))
			{
				Map<Integer,Double> hydroTreating=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject hydroTreatingCapJson=createCapacityDetails(hydroTreating);
				jsonTerminalData.put(key, hydroTreatingCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(ALKYLATIONCAPACITY))
			{
				Map<Integer,Double> alkylation=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject alkylationCapJson=createCapacityDetails(alkylation);
				jsonTerminalData.put(key, alkylationCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(AROMACTICSCAPACITY))
			{
				Map<Integer,Double> aromactics=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject aromacticsCapJson=createCapacityDetails(aromactics);
				jsonTerminalData.put(key, aromacticsCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(ISOMERIZATIONCAPACITY))
			{
				Map<Integer,Double> isomerization=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject isomerizationCapJson=createCapacityDetails(isomerization);
				jsonTerminalData.put(key, isomerizationCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(POLYMERIZATIONCAPACITY))
			{
				Map<Integer,Double> polymerization=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject polymerizationCapJson=createCapacityDetails(polymerization);
				jsonTerminalData.put(key, polymerizationCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(LUBESCAPACITY))
			{
				Map<Integer,Double> lubes=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject lubesCapJson=createCapacityDetails(lubes);
				jsonTerminalData.put(key, lubesCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(OXYGENATESCAPACITY))
			{
				Map<Integer,Double> oxygenates=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject oxygenatesCapJson=createCapacityDetails(oxygenates);
				jsonTerminalData.put(key, oxygenatesCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(COKECAPACITY))
			{
				Map<Integer,Double> coke=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject cokeCapJson=createCapacityDetails(coke);
				jsonTerminalData.put(key, cokeCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(SULPHURCAPACITY))
			{
				Map<Integer,Double> sulphur=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject sulphurCapJson=createCapacityDetails(sulphur);
				jsonTerminalData.put(key, sulphurCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(HYDROGENCAPACITY))
			{
				Map<Integer,Double> hydrogen=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject hydrogenCapJson=createCapacityDetails(hydrogen);
				jsonTerminalData.put(key, hydrogenCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(ASPHALTCAPACITY))
			{
				Map<Integer,Double> asphalt=(Map<Integer,Double>)terminalData.get(key);	
				JSONObject asphaltCapJson=createCapacityDetails(asphalt);
				jsonTerminalData.put(key, asphaltCapJson);
			}
			else if(null!=key && key.equalsIgnoreCase(OWNERSHIP))
			{
				List<Map<String,String>> ownerShipList=(List<Map<String,String>>)terminalData.get(key);
				jsonTerminalData.put(key, createOwnership(ownerShipList));
			}
			else if(null!=key && key.equalsIgnoreCase(HISTORICOWNERSHIP))
			{
				List<Map<String,String>> historicOwnerShipList=(List<Map<String,String>>)terminalData.get(key);
				jsonTerminalData.put(key, createHistoricOwnership(historicOwnerShipList));
			}
			else if(null!=key && key.equalsIgnoreCase(MAINTENANCEDETAILS))
			{
				List<Map<String,String>> maintenanceDetailsList=(List<Map<String,String>>)terminalData.get(key);
				jsonTerminalData.put(key,createMaintenanceDetails(maintenanceDetailsList));
			}
			else if(null!=key && key.equalsIgnoreCase(CONSTRUCTIONDETAILS))
			{
				List<Map<String,String>> constructionDetailsList=(List<Map<String,String>>)terminalData.get(key);
				jsonTerminalData.put(key,createConstructionDetails(constructionDetailsList));
			}
			else
				jsonTerminalData.put(key,terminalData.get(key));
		}
		return jsonTerminalData.toJSONString();
	}
	private JSONArray createMaintenanceDetails(List<Map<String,String>> maintenanceDetailsList)
	{
		JSONArray maintenanceDetailsArray=new JSONArray();
		JSONObject maintenanceDetailsObj=null;
		for(Map<String,String> maintenanceDetails:maintenanceDetailsList)
		{
			maintenanceDetailsObj=new JSONObject();
			maintenanceDetailsObj.put(MAINTENANCESTARTDATE,maintenanceDetails.get(MAINTENANCESTARTDATE));
			maintenanceDetailsObj.put(MAINTENANCEENDDATE,maintenanceDetails.get(MAINTENANCEENDDATE));
			maintenanceDetailsArray.add(maintenanceDetailsObj);
		}
		return maintenanceDetailsArray;
	}
	private JSONObject createCapacityDetails(Map<Integer,Double> capacityData)
	{
		JSONObject capacityDetailsJson=new JSONObject();
		Set<Integer> years=capacityData.keySet();
		for(Integer year:years)
			capacityDetailsJson.put(year,capacityData.get(year));
		
		return capacityDetailsJson;
	}
	private JSONArray createOwnership(List<Map<String,String>> ownerShipList)
	{
		JSONArray ownerShipArray=new JSONArray();
		JSONObject ownerShipJsonObj=null;
		for(Map<String,String> ownership:ownerShipList)
		{
			ownerShipJsonObj=new JSONObject();
			ownerShipJsonObj.put(CURRENTEQUITYPARTNER,ownership.get(CURRENTEQUITYPARTNER));
			ownerShipJsonObj.put(CURRENTEQUITYSTAKE,ownership.get(CURRENTEQUITYSTAKE));
			ownerShipArray.add(ownerShipJsonObj);
		}
		return ownerShipArray;
	}
	private JSONArray createHistoricOwnership(List<Map<String,String>> ownerShipList)
	{
		JSONArray ownerShipArray=new JSONArray();
		JSONObject ownerShipJsonObj=null;
		for(Map<String,String> ownership:ownerShipList)
		{
			ownerShipJsonObj=new JSONObject();
			ownerShipJsonObj.put(HISTORICEQUITYPARTNER,ownership.get(HISTORICEQUITYPARTNER));
			ownerShipJsonObj.put(HISTORICEQUITYSTAKE,ownership.get(HISTORICEQUITYSTAKE));
			ownerShipArray.add(ownerShipJsonObj);
		}
		return ownerShipArray;
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
