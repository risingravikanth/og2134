package com.oganalysis.helper;

import static com.oganalysis.constants.ApplicationConstants.BLANK;
import static com.oganalysis.constants.ApplicationConstants.COMPANY;
import static com.oganalysis.constants.ApplicationConstants.COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.LOCATION;
import static com.oganalysis.constants.ApplicationConstants.OPTION_COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.OPTION_LOCATION;
import static com.oganalysis.constants.ApplicationConstants.OPTION_REGION;
import static com.oganalysis.constants.ApplicationConstants.OPTION_COMPANY;
import static com.oganalysis.constants.ApplicationConstants.OPTION_TECHNOLOGYPROVIDER;
import static com.oganalysis.constants.ApplicationConstants.OPTION_TECHNOLOGY;
import static com.oganalysis.constants.ApplicationConstants.OPTION_STATUS;
import static com.oganalysis.constants.ApplicationConstants.OPTION_TYPE;
import static com.oganalysis.constants.ApplicationConstants.SMALLSCALELNG_LIQUEFACTION_CAPACITY;
import static com.oganalysis.constants.ApplicationConstants.SMALLSCALELNG_REGASIFICATION_CAPACITY;
import static com.oganalysis.constants.ApplicationConstants.SMALLSCALELNG_STORAGE_CAPACITY;
import static com.oganalysis.constants.ApplicationConstants.STARTYEAR;
import static com.oganalysis.constants.ApplicationConstants.STATUS;
import static com.oganalysis.constants.ApplicationConstants.TECHNOLOGY;
import static com.oganalysis.constants.ApplicationConstants.TERMINAL;
import static com.oganalysis.constants.ApplicationConstants.TYPE;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class SmallScaleLngJsonResponse {
	public String createSmallScaleLngResponse(List<Map> smallScaleLngMapList)
	{
		JSONArray smallScaleLngArray=new JSONArray();
		
		for(Map map:smallScaleLngMapList)
		{
			JSONObject sslJson=new JSONObject();
			sslJson.put(TERMINAL, map.get(TERMINAL));
			sslJson.put(COUNTRY, map.get(COUNTRY));
			sslJson.put(TYPE, map.get(TYPE));
			sslJson.put(STATUS, map.get(STATUS));
			sslJson.put(STARTYEAR, map.get(STARTYEAR));
			sslJson.put(LOCATION, map.get(LOCATION));
			sslJson.put(COMPANY, map.get(COMPANY));					
			sslJson.put(TECHNOLOGY,map.get(TECHNOLOGY));
			sslJson.put(SMALLSCALELNG_LIQUEFACTION_CAPACITY, map.get(SMALLSCALELNG_LIQUEFACTION_CAPACITY));
			sslJson.put(SMALLSCALELNG_REGASIFICATION_CAPACITY, map.get(SMALLSCALELNG_REGASIFICATION_CAPACITY));
			sslJson.put(SMALLSCALELNG_STORAGE_CAPACITY, map.get(SMALLSCALELNG_STORAGE_CAPACITY));
			smallScaleLngArray.add(sslJson);				
		}
		
		return smallScaleLngArray.toJSONString();
	}
	public String createRegionsResponse(List<String> dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			
			for(String region:dataList)
			{				
				 JSONObject jsonObj=new JSONObject();				 											  				  		
				  jsonObj.put(OPTION_REGION,region);				  						      			     
			      array.add(jsonObj);			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createCountriesResponse(List<String> dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			
			for(String country:dataList)
			{
				 
				JSONObject jsonObj=new JSONObject();				 											  				  			
				jsonObj.put(OPTION_COUNTRY,country);										      			     
			    array.add(jsonObj);
			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createLocationsResponse(List<String> dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			
			for(String location:dataList)
			{
				 
				JSONObject jsonObj=new JSONObject();				 											  				  			
				jsonObj.put(OPTION_LOCATION,location);										      			     
			    array.add(jsonObj);
			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createCompaniesResponse(List<String> dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			
			for(String company:dataList)
			{
				 
				JSONObject jsonObj=new JSONObject();				 											  				  			
				jsonObj.put(OPTION_COMPANY,company);										      			     
			    array.add(jsonObj);
			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createTechnologyProvidersResponse(List<String> dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			
			for(String technologyProviders:dataList)
			{
				 
				JSONObject jsonObj=new JSONObject();				 											  				  			
				jsonObj.put(OPTION_TECHNOLOGYPROVIDER,technologyProviders);										      			     
			    array.add(jsonObj);
			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createTechnologyResponse(List<String> dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			
			for(String technology:dataList)
			{
				 
				JSONObject jsonObj=new JSONObject();				 											  				  			
				jsonObj.put(OPTION_TECHNOLOGY,technology);										      			     
			    array.add(jsonObj);
			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createStatusResponse(List<String> dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			
			for(String status:dataList)
			{
				 
				JSONObject jsonObj=new JSONObject();				 											  				  			
				jsonObj.put(OPTION_STATUS,status);										      			     
			    array.add(jsonObj);
			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createTypeResponse(List<String> dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			
			for(String type:dataList)
			{
				 
				JSONObject jsonObj=new JSONObject();				 											  				  			
				jsonObj.put(OPTION_TYPE,type);										      			     
			    array.add(jsonObj);
			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
}
