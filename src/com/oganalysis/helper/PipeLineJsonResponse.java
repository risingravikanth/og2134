package com.oganalysis.helper;

import static com.oganalysis.constants.ApplicationConstants.*;


import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
public class PipeLineJsonResponse {
	public String createLengthByCountry(Map<String,Double> lengthByCountry)
	{
		JSONObject countryObj=new JSONObject();
		JSONArray countryArray=new JSONArray();
		Set<String> countryKeys=lengthByCountry.keySet();
		for(String key:countryKeys)
		{
			JSONObject jsonVal=new JSONObject();
			jsonVal.put(COUNTRY, key);
			if(null!=lengthByCountry.get(key) && lengthByCountry.get(key)!=0)
				jsonVal.put("length",lengthByCountry.get(key));
			else
				jsonVal.put("length",BLANK);
			
			countryArray.add(jsonVal);
		}
		countryObj.put(DATA_KEY, countryArray);
		return countryObj.toJSONString();
	}
	public String createLengthByCompany(Map<String,Double> lengthByCompany)
	{
		JSONObject companyObj=new JSONObject();
		JSONArray companyArray=new JSONArray();
		Set<String> countryKeys=lengthByCompany.keySet();
		for(String key:countryKeys)
		{
			JSONObject jsonVal=new JSONObject();
			jsonVal.put(COMPANY, key);
			if(null!=lengthByCompany.get(key) && lengthByCompany.get(key)!=0)
				jsonVal.put("length",lengthByCompany.get(key));
			else
				jsonVal.put("length",BLANK);
			
			companyArray.add(jsonVal);
		}
		companyObj.put(DATA_KEY, companyArray);
		return companyObj.toJSONString();
	}
	public String createPipelineData(Map<String,List<Map>> pipelines)
	{
		JSONObject pipelineJson=new JSONObject();
		JSONArray pipelineJsonArray=new JSONArray();
		Set<String> pipelineSetKeys=pipelines.keySet();
		TreeSet<String> sortedSetKeys=new TreeSet<String>(pipelineSetKeys);
		for(String key:sortedSetKeys)
		{
			List<Map> pipelineList=pipelines.get(key);
			JSONArray pipelineJsonDataArr=new JSONArray();
			int i=0;
			for(Map pipelineMap:pipelineList)
			{				
				JSONObject pipelineDataJson=new JSONObject();
				pipelineDataJson.put(PIPELINE, pipelineMap.get(PIPELINE));
				pipelineDataJson.put(SUBPIPELINE, pipelineMap.get(SUBPIPELINE));
				pipelineDataJson.put(STARTPOINT, pipelineMap.get(STARTPOINT));
				pipelineDataJson.put(ENDPOINT, pipelineMap.get(ENDPOINT));
				pipelineDataJson.put(LENGTH, pipelineMap.get(LENGTH));
				pipelineDataJson.put(DIAMETER, pipelineMap.get(PIPELINE));
				pipelineDataJson.put(CAPACITY, pipelineMap.get(CAPACITY));
				pipelineJsonDataArr.add(i,pipelineDataJson);
				i++;
			}
			pipelineJsonArray.add(pipelineJsonDataArr);
		}
		return pipelineJsonArray.toJSONString();
	}
}
