package com.oganalysis.helper;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import static com.oganalysis.constants.ApplicationConstants.*;

public class ExplorationJsonResponse {
	public String createBlockWiseResponse(List<Map> explorationMapList)
	{
		JSONArray explorationArray=new JSONArray();		
		for(Map map:explorationMapList)
		{
			JSONObject explorationJson=new JSONObject();
			explorationJson.put(BLOCKNAME, map.get(BLOCKNAME));
			explorationJson.put(COUNTRY, map.get(COUNTRY));
			explorationJson.put(BASIN, map.get(BASIN));
			explorationJson.put(ON_OFF_SHORE, map.get(ON_OFF_SHORE));
			explorationJson.put(STATUS, map.get(STATUS));
			explorationJson.put(DATE_AWAREDED, map.get(DATE_AWAREDED));
			explorationJson.put(OPERATOR, map.get(OPERATOR));					
			explorationJson.put(OWNERS,getOwners((List<String>)map.get(OWNERS)));
			explorationJson.put(AREA, map.get(AREA));
			explorationArray.add(explorationJson);
		}
		
		return explorationArray.toJSONString();
	
	}
	private JSONArray getOwners(List<String> owners)
	{
		JSONArray ownerArray=new JSONArray();
		for(String owner:owners)
		{
			ownerArray.add(owner);
		}
		return ownerArray;
	}
}
