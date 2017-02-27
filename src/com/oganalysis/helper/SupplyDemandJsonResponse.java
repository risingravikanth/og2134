package com.oganalysis.helper;

import static com.oganalysis.constants.ApplicationConstants.*;


import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SupplyDemandJsonResponse {
	
	public String createSupplyDemandResponse(List<Map<String,String>> supplyDemandList,int startDate,int endDate,String displayType)
	{
		JSONObject supplyDemandJsonObj=new JSONObject();
		supplyDemandJsonObj.put(TYPE,displayType);
 		JSONArray supplyDemandDataArray=new JSONArray();
		for(Map<String,String> supplyDemandMap:supplyDemandList)
		{
			JSONObject supplyDemandDataObj=new JSONObject();
			supplyDemandDataObj.put(COUNTRY, supplyDemandMap.get(COUNTRY));
			for(int i=startDate;i<=endDate;i++)
			{
				String year=String.valueOf(i);
				if(null!=supplyDemandMap && null!=supplyDemandMap.get(year) && Float.parseFloat(supplyDemandMap.get(year))!=0)
					supplyDemandDataObj.put(year,supplyDemandMap.get(year));
				else
					supplyDemandDataObj.put(year,BLANK);
			}
			supplyDemandDataArray.add(supplyDemandDataObj);
		}		
		supplyDemandJsonObj.put(DATA_KEY,supplyDemandDataArray);
		
		return supplyDemandJsonObj.toJSONString();
		
	}
}
