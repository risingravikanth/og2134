package com.oganalysis.helper;

import static com.oganalysis.constants.ApplicationConstants.*;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class ProductionJsonResponse {
	public JSONObject createCountryResponse(List<Map<String,String>> dataList,int startYear,int endYear,String displayType,String type)
	{
		JSONObject productionJsonObj=new JSONObject();
		productionJsonObj.put(TYPE,type);//displayType
 		JSONArray productionArray=new JSONArray();
		for(Map<String,String> productionMap:dataList)
		{
			JSONObject prodObj=new JSONObject();
			prodObj.put(JSON_NAME, productionMap.get(COUNTRY));
			for(int i=startYear;i<=endYear;i++)
			{
				String year=String.valueOf(i);
				if(null!=productionMap && null!=productionMap.get(year) && Double.parseDouble(productionMap.get(year))!=0)
					prodObj.put(year,productionMap.get(year));
				else
					prodObj.put(year,BLANK);
			}
			productionArray.add(prodObj);
		}		
		productionJsonObj.put(displayType,productionArray);
//		if(type.equalsIgnoreCase(GAS))
//			productionJsonObj.put(GAS,productionArray);
//		else if(type.equalsIgnoreCase(OIL))
//			productionJsonObj.put(OIL,productionArray);
		return productionJsonObj;
		
	}
	public JSONArray createOilAndGasCountryResponse(Map<String,List<Map<String,String>>> bothCountryData,int startYear,int endYear,String displayType)
	{
		JSONArray bothArray=new JSONArray();
		JSONObject gasJsonObject=createCountryResponse(bothCountryData.get(GAS), startYear, endYear, displayType, GAS);
		JSONObject oilJsonObject=createCountryResponse(bothCountryData.get(OIL), startYear, endYear, displayType, OIL);
		bothArray.add(gasJsonObject);
		bothArray.add(oilJsonObject);
		return bothArray;
	}
	public JSONObject createFieldResponse(List<Map<String,String>> dataList,int startYear,int endYear,String displayType,String type)
	{
		JSONObject productionJsonObj=new JSONObject();
		productionJsonObj.put(TYPE,type);//displayType
 		JSONArray productionArray=new JSONArray();
		for(Map<String,String> productionMap:dataList)
		{
			JSONObject prodObj=new JSONObject();
			prodObj.put(JSON_NAME, productionMap.get(FIELD));
			for(int i=startYear;i<=endYear;i++)
			{
				String year=String.valueOf(i);
				if(null!=productionMap && null!=productionMap.get(year) && Double.parseDouble(productionMap.get(year))!=0)
					prodObj.put(year,productionMap.get(year));
				else
					prodObj.put(year,BLANK);
			}
			productionArray.add(prodObj);
		}		
		productionJsonObj.put(displayType, productionArray);
//		if(type.equalsIgnoreCase(GAS))
//			productionJsonObj.put(GAS,productionArray);
//		else if(type.equalsIgnoreCase(OIL))
//			productionJsonObj.put(OIL,productionArray);
		return productionJsonObj;
		
	}
	public JSONArray createOilAndGasFieldResponse(Map<String,List<Map<String,String>>> bothCountryData,int startYear,int endYear,String displayType)
	{
		JSONArray bothArray=new JSONArray();
		JSONObject gasJsonObject=createFieldResponse(bothCountryData.get(GAS), startYear, endYear, displayType, GAS);
		JSONObject oilJsonObject=createFieldResponse(bothCountryData.get(OIL), startYear, endYear, displayType, OIL);
		bothArray.add(gasJsonObject);
		bothArray.add(oilJsonObject);
		return bothArray;
	}
	public JSONObject createCompanyOilGasResponse(List<Map<String,String>> dataList,int startYear,int endYear,String type)
	{
		JSONObject productionJsonObj=new JSONObject();		
 		JSONArray productionArray=new JSONArray();
 		productionJsonObj.put(TYPE,type);
		for(Map<String,String> productionMap:dataList)
		{
			JSONObject prodObj=new JSONObject();
			prodObj.put(JSON_NAME, productionMap.get(COMPANY));
			for(int i=startYear;i<=endYear;i++)
			{
				String year=String.valueOf(i);
				if(null!=productionMap && null!=productionMap.get(year) && Double.parseDouble(productionMap.get(year))!=0)
					prodObj.put(year,productionMap.get(year));
				else
					prodObj.put(year,BLANK);
			}
			productionArray.add(prodObj);
		}		
		productionJsonObj.put(COMPANY,productionArray);
//		if(type.equalsIgnoreCase(GAS))
//			productionJsonObj.put(GAS,productionArray);
//		else if(type.equalsIgnoreCase(OIL))
//			productionJsonObj.put(OIL,productionArray);
		return productionJsonObj;
		
	}
	public JSONArray createCompanyOilGasResponse(Map<String,List<Map<String,String>>> bothCountryData,int startYear,int endYear)
	{
		JSONArray bothArray=new JSONArray();
		JSONObject gasJsonObject=createCompanyOilGasResponse(bothCountryData.get(GAS), startYear, endYear,GAS);
		JSONObject oilJsonObject=createCompanyOilGasResponse(bothCountryData.get(OIL), startYear, endYear,OIL);
		bothArray.add(gasJsonObject);
		bothArray.add(oilJsonObject);
		return bothArray;
	}
	
}
