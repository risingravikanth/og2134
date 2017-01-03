package com.oganalysis.helper;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oganalysis.entities.SupplyDemand;

public class SupplyDemandJsonResponse {
	
	public String createSupplyDemandResponse(List<Map<String,String>> supplyDemandList,int startDate,int endDate,String displayType)
	{
		JSONObject supplyDemandJsonObj=new JSONObject();
		supplyDemandJsonObj.put("type",displayType);
 		JSONArray supplyDemandDataArray=new JSONArray();
		for(Map<String,String> supplyDemandMap:supplyDemandList)
		{
			JSONObject supplyDemandDataObj=new JSONObject();
			supplyDemandDataObj.put("country", supplyDemandMap.get("country"));
			for(int i=startDate;i<=endDate;i++)
			{
				String year=String.valueOf(i);
				supplyDemandDataObj.put(year,supplyDemandMap.get(year));
			}
			supplyDemandDataArray.add(supplyDemandDataObj);
		}		
		supplyDemandJsonObj.put("data",supplyDemandDataArray);
		
		return supplyDemandJsonObj.toJSONString();
		
	}
}
