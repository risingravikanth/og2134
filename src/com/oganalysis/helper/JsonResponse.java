package com.oganalysis.helper;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.oganalysis.entities.CrudeOil;
import com.oganalysis.entities.Exploration;
import com.oganalysis.entities.Lng;
import com.oganalysis.entities.NaturalGas;
import com.oganalysis.entities.PipeLine;
import com.oganalysis.entities.Refinery;
import com.oganalysis.entities.Storage;
import com.oganalysis.entities.source.Countries;
import com.oganalysis.entities.source.Region;

public class JsonResponse {
	
	public String createExplorationResponse(List dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			for(Object object:dataList)
			{
				 Exploration exploration=(Exploration)object;
				 JSONObject jsonObj=new JSONObject();
				 							
				  jsonObj.put("blockNo",exploration.getBlockNo());
				  jsonObj.put("region",exploration.getRegion());
				  jsonObj.put("country",exploration.getCountry());
				  jsonObj.put("onshoreoroffshore", exploration.getOnShoreOrOffShore());
				  jsonObj.put("basin",exploration.getBasin());
				  jsonObj.put("status",exploration.getStatus());
				  jsonObj.put("startDate",exploration.getStartDate().toString());
				  jsonObj.put("operator",exploration.getOperator());
				  jsonObj.put("equityParterns",exploration.getEquityParterns());
				  jsonObj.put("sourceEquity",exploration.getSourceEquity());
				  jsonObj.put("area",exploration.getArea());
				  if(exploration.getLicenseEnddate()!=null)
				   jsonObj.put("licenseEnddate",exploration.getLicenseEnddate().toString());
				  else
					  jsonObj.put("licenseEnddate","");	  
				  jsonObj.put("wellsDrilled",exploration.getWellsDrilled());
				  jsonObj.put("TwoDSeismicCompleted", exploration.getTwoDSeismicCompleted());
				  jsonObj.put("ThreeDSeismic",exploration.getThreeDSeismic());
				  jsonObj.put("moreInfo",exploration.getMoreInfo());
				  jsonObj.put("notes",exploration.getNotes());
				  jsonObj.put("source",exploration.getSource());
				  jsonObj.put("licenseNo", exploration.getLicenseNo());
			      			      
			      array.add(jsonObj);
			}
			
			
			response=array.toString();		      
		}
		else
			response="";
		return response;
	}
	public String createRefineryResponse(List dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			for(Object object:dataList)
			{
				 Refinery refinery=(Refinery)object;
				 JSONObject jsonObj=new JSONObject();
				 							
				  jsonObj.put("name",refinery.getName());
				  jsonObj.put("location", refinery.getLocation());				  
				  jsonObj.put("country",refinery.getCountry());
				  jsonObj.put("region",refinery.getRegion());
				  jsonObj.put("status",refinery.getStatus());
				  jsonObj.put("capacityYear",refinery.getCapacityYear());
				  jsonObj.put("statusDetails",refinery.getStatusDetails());
				  if(refinery.getStatusDate()!=null)
					  jsonObj.put("statusDate", refinery.getStatusDate().toString());
				  else
					  jsonObj.put("statusDate","");
				  jsonObj.put("statusSource",refinery.getStatusSource());
				  jsonObj.put("moreInfo",refinery.getMoreInfo());
				  jsonObj.put("type",refinery.getType());
				  if(refinery.getStartYear()!=null)
					  jsonObj.put("startYear",refinery.getStartYear().toString());
				  else
					  jsonObj.put("startYear","");
				  if(refinery.getDecomissionedYear()!=null)					
					  jsonObj.put("decomissionedYear",refinery.getDecomissionedYear().toString());
				  else
					  jsonObj.put("decomissionedYear","");
				  jsonObj.put("assetOrStartSource",refinery.getAssetOrStartSource());
			      			      
			      array.add(jsonObj);
			}
			
			response=array.toString();		      
		}
		else
			response="";
		return response;
	}
	public String createCrudeOilResponse(List dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			for(Object object:dataList)
			{
				 CrudeOil crudeOil=(CrudeOil)object;
				 JSONObject jsonObj=new JSONObject();
				 							
				  jsonObj.put("field",crudeOil.getField());
				  jsonObj.put("location", crudeOil.getLocation());				  
				  jsonObj.put("country",crudeOil.getCountry());
				  jsonObj.put("region",crudeOil.getRegion());
				
			      			      
			      array.add(jsonObj);
			}
			
			response=array.toString();		      
		}
		else
			response="";
		return response;
		
	}
	public String createLngResponse(List dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			for(Object object:dataList)
			{
				 Lng lng=(Lng)object;
				 JSONObject jsonObj=new JSONObject();
				 							
				  jsonObj.put("name",lng.getName());				  			 
				  jsonObj.put("country",lng.getCountry());
				  jsonObj.put("region",lng.getRegion());
				
			      			      
			      array.add(jsonObj);
			}
			
			response=array.toString();		      
		}
		else
			response="";
		return response;
	}
	public String createNaturalGasResponse(List dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			for(Object object:dataList)
			{
				 NaturalGas naturalGas=(NaturalGas)object;
				 JSONObject jsonObj=new JSONObject();
				 							
				  jsonObj.put("field",naturalGas.getField());				  			 
				  jsonObj.put("country",naturalGas.getCountry());
				  jsonObj.put("region",naturalGas.getRegion());							      			      
			      array.add(jsonObj);
			}
			
			response=array.toString();		      
		}
		else
			response="";
		return response;
	}
	public String createPipeLineResponse(List dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			for(Object object:dataList)
			{
				 PipeLine pipeLine=(PipeLine)object;
				 JSONObject jsonObj=new JSONObject();
				 							
				  jsonObj.put("name",pipeLine.getPipeline());				  			 
				  jsonObj.put("country",pipeLine.getCountry());
				  jsonObj.put("region",pipeLine.getRegion());							      			      
			      array.add(jsonObj);
			}
			
			response=array.toString();		      
		}
		else
			response="";
		return response;
	}
	public String createStorageResponse(List dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			for(Object object:dataList)
			{
				 Storage storage=(Storage)object;
				 JSONObject jsonObj=new JSONObject();
				 							
				  jsonObj.put("name",storage.getTankFarm());				  			 
				  jsonObj.put("country",storage.getCountry());
				  jsonObj.put("region",storage.getRegion());							      			      
			      array.add(jsonObj);
			}
			
			response=array.toString();		      
		}
		else
			response="";
		return response;
	}
	public String createRegionsResponse(List dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			for(Object object:dataList)
			{
				 Region region=(Region)object;
				 JSONObject jsonObj=new JSONObject();
				 							
				  jsonObj.put("id",region.getId());				  			 
				  jsonObj.put("name",region.getName());
				  						      			      
			      array.add(jsonObj);
			}
			
			response=array.toString();		      
		}
		else
			response="";
		return response;
	}
	public String createCountriesResponse(List<Countries> dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			for(Countries countries:dataList)
			{
				 
				 JSONObject jsonObj=new JSONObject();
				 							
				  jsonObj.put("id",countries.getId());				  			 
				  jsonObj.put("name",countries.getName());
				  						      			      
			      array.add(jsonObj);
			}
			
			response=array.toString();		      
		}
		else
			response="";
		return response;
	}
}
