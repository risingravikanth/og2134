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
import com.oganalysis.entities.source.Status;
import com.oganalysis.entities.source.Type;
import static com.oganalysis.constants.ApplicationConstants.*;

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
				  jsonObj.put("onshoreoroffshore", exploration.getOnshoreOrOffshore());
				  jsonObj.put("basin",exploration.getBasin());
				  jsonObj.put("status",exploration.getStatus());
				  jsonObj.put("startDate",exploration.getStartDate().toString());
				  jsonObj.put("operator",exploration.getOperator());
				  jsonObj.put("equityParterns",exploration.getEquityPartners());
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
				  
				  jsonObj.put("currentOperator", refinery.getCurrentOperator());
				  jsonObj.put("currentEquityPartners",refinery.getCurrentEquityPartners());
				  jsonObj.put("currentEquityStakes", refinery.getCurrentEquityStakes());
				  jsonObj.put("equityDetails", refinery.getEquityDetails());
				  jsonObj.put("historicOperator", refinery.getHistoricOperator());
				  jsonObj.put("historicEquityPartners",refinery.getHistoricEquityPartners());
				  jsonObj.put("historicEquityStakes",refinery.getHistoricEquityStakes());
				  jsonObj.put("historicEquityYear",refinery.getHistoricEquityYear());
				  jsonObj.put("equitySource",refinery.getEquitySource());
				  jsonObj.put("refiningCapacity",refinery.getRefiningCapacity());
				  jsonObj.put("vduCapacity",refinery.getVduCapacity());
				  jsonObj.put("cokingCapacity",refinery.getCokingCapacity());
				  jsonObj.put("fcc",refinery.getFcc());
				  jsonObj.put("hydroCrackingCapacity",refinery.getHydroCrackingCapacity());
				  jsonObj.put("sourceCapacities",refinery.getSourceCapacities());
				  jsonObj.put("crudeStorageUnitOrTanksNo",refinery.getCrudeStorageUnitOrTanksNo());
				  jsonObj.put("crudeStorageCapacity",refinery.getCrudeStorageCapacity());
				  jsonObj.put("nelsonComplexityIndex",refinery.getNelsonComplexityIndex());
				  jsonObj.put("crudeThroughput", refinery.getCrudeThroughput());
				  jsonObj.put("crudeType",refinery.getCrudeType());
				  jsonObj.put("api",refinery.getApi());
				  jsonObj.put("sulphur",refinery.getSulphur());
				  jsonObj.put("sourceInput",refinery.getSourceInput());
				  jsonObj.put("visbreakingCapacity",refinery.getVisbreakingCapacity());
				  jsonObj.put("reformerCapacity",refinery.getReformerCapacity());
				  jsonObj.put("hydrotreatingCapacity",refinery.getHydrotreatingCapacity());
				  jsonObj.put("alkylationCapacity",refinery.getAlkylationCapacity());
				  jsonObj.put("alkylationCapacityUnit",refinery.getAlkylationCapacityUnit());
				  jsonObj.put("aromaticsCapacity",refinery.getAromaticsCapacity());
				  jsonObj.put("aromaticsCapacityUnit",refinery.getAromaticsCapacityUnit());
				  jsonObj.put("isomerizationCapacity",refinery.getIsomerizationCapacity());
				  jsonObj.put("polymerizationCapacity",refinery.getPolymerizationCapacity());
				  jsonObj.put("polymerizationCapacityUnit",refinery.getPolymerizationCapacityUnit());
				  jsonObj.put("lubesCapacity",refinery.getLubesCapacity());
				  jsonObj.put("lubesCapacityUnit",refinery.getLubesCapacityUnit());
				  jsonObj.put("oxygenatesCapacity",refinery.getOxygenatesCapacity());
				  jsonObj.put("oxygenatesCapacityUnit",refinery.getOxygenatesCapacityUnit());
				  jsonObj.put("cokeCapacity",refinery.getCokeCapacity());
				  jsonObj.put("cokeCapacityUnit",refinery.getCokeCapacityUnit());
				  jsonObj.put("sulphurCapacity",refinery.getSulphurCapacity());
				  jsonObj.put("sulphurCapacityUnit",refinery.getSulphurCapacityUnit());
				  jsonObj.put("hydrogenCapacity",refinery.getHydrogenCapacity());
				  jsonObj.put("hydrogenCapacityUnit",refinery.getHydrogenCapacityUnit());
				  jsonObj.put("asphaltCapacity",refinery.getAsphaltCapacity());
				  jsonObj.put("asphaltCapacityUnit",refinery.getAsphaltCapacityUnit());
				  jsonObj.put("othersCapacity",refinery.getOthersCapacity());
				  jsonObj.put("refineryProducts",refinery.getRefineryProducts());
				  jsonObj.put("sourceProducts",refinery.getSourceProducts());
				  jsonObj.put("refineryUtilization",refinery.getRefineryUtilization());
				  jsonObj.put("grm",refinery.getGrm());
				  jsonObj.put("capex",refinery.getCapex());
				  jsonObj.put("capexYear",refinery.getCapexYear());
				  jsonObj.put("capexDetails",refinery.getCapexDetails());
				  jsonObj.put("sourceCapex",refinery.getSourceCapex());
				  jsonObj.put("constructionCompanyName",refinery.getConstructionCompanyName());
				  jsonObj.put("constructionContractDetails",refinery.getConstructionContractDetails());
				  jsonObj.put("refineryOtherNames",refinery.getRefineryOtherNames());
				  jsonObj.put("otherSources",refinery.getOtherSources());
				  jsonObj.put("contact",refinery.getContact());
				  jsonObj.put("gasolinePetrol",refinery.getGasolinePetrol());
				  jsonObj.put("lpg",refinery.getLpg());
				  jsonObj.put("kerosine",refinery.getKerosine());
				  jsonObj.put("jetFuel",refinery.getJetFuel());
				  jsonObj.put("diesel",refinery.getDiesel());
				  jsonObj.put("propylene",refinery.getPropylene());
				  jsonObj.put("lightNaphtha",refinery.getLightNaphtha());
				  jsonObj.put("heavyNaphtha",refinery.getHeavyNaphtha());
				  jsonObj.put("kerojet", refinery.getKerojet());
				  jsonObj.put("benzeneSaturationUnit",refinery.getBenzeneSaturationUnit());
				  jsonObj.put("productsSources",refinery.getProductsSources());
				  			      			      
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
				  jsonObj.put(OPTION_REGION,region.getName());				  						      			     
			      array.add(jsonObj);
			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
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
				jsonObj.put(OPTION_COUNTRY,countries.getName());										      			     
			    array.add(jsonObj);
			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createStatusResponse(List<Status> dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			JSONArray array=new JSONArray();
			
			for(Status status:dataList)
			{				 
				JSONObject jsonObj=new JSONObject();				 											 				  			
				jsonObj.put(OPTION_STATUS,status.getName());				  						      			     
			    array.add(jsonObj);			     
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createTypeResponse(List<Type> dataList)
	{
		String response=null;
		if(dataList.size()>0)
		{
			
			JSONArray array=new JSONArray();
			for(Type type:dataList)
			{
				 
				 JSONObject jsonObj=new JSONObject();				 											  			  		
				  jsonObj.put(OPTION_TYPE,type.getName());				  					      			     
			      array.add(jsonObj);			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	
	public String createLocationsResponse(List<String> locationsList)
	{
		String response=null;
		if(locationsList.size()>0)
		{
			
			JSONArray array=new JSONArray();
			for(String locations:locationsList)
			{				 
				 JSONObject jsonObj=new JSONObject();									
				 if(locations!=null && !locations.equalsIgnoreCase(BLANK))
				 {
					  jsonObj.put(OPTION_LOCATION,locations);				  						      			     
				      array.add(jsonObj);
				 }				 			     
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	
	public String createOperatorResponse(List<String> operatorsList)
	{
		String response=null;
		if(operatorsList.size()>0)
		{
			
			JSONArray array=new JSONArray();
			for(String operator:operatorsList)
			{
				 
				 JSONObject jsonObj=new JSONObject();
				 							
				 if(operator!=null && !operator.equalsIgnoreCase(BLANK))
				 { 
					 jsonObj.put(OPTION_OPERATOR,operator);				  						      			     				
			      	 array.add(jsonObj);
				 }
			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createOwnersResponse(List<String> ownersList)
	{
		String response=null;
		if(ownersList.size()>0)
		{
			
			JSONArray array=new JSONArray();
			for(String owner:ownersList)
			{
				 
				 JSONObject jsonObj=new JSONObject();
				 							
				 if(owner!=null && !owner.equalsIgnoreCase(BLANK))
				 { 
					 jsonObj.put(OPTION_OWNER,owner);				  						      			     				
			      	 array.add(jsonObj);
				 }
			      
			}
			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createCommoditiesResponse(List<String> commoditiesList)
	{
		String response=null;
		if(commoditiesList.size()>0)
		{
			
			JSONArray array=new JSONArray();
			for(String commodity:commoditiesList)
			{
				 
				 JSONObject jsonObj=new JSONObject();
				 							
				 if(commodity!=null && !commodity.equalsIgnoreCase(BLANK))
				 { 
					 jsonObj.put(OPTION_COMMODITY,commodity);				  						      			     				
			      	 array.add(jsonObj);
				 }
			      
			}			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createStartPointResponse(List<String> startPointList)
	{
		String response=null;
		if(startPointList.size()>0)
		{
			
			JSONArray array=new JSONArray();
			for(String startPoint:startPointList)
			{
				 
				 JSONObject jsonObj=new JSONObject();
				 							
				 if(startPoint!=null && !startPoint.equalsIgnoreCase(BLANK))
				 { 
					 jsonObj.put(OPTION_STARTPOINT,startPoint);				  						      			     				
			      	 array.add(jsonObj);
				 }
			      
			}			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createEndPointResponse(List<String> endPointList)
	{
		String response=null;
		if(endPointList.size()>0)
		{
			
			JSONArray array=new JSONArray();
			for(String endPoint:endPointList)
			{
				 
				 JSONObject jsonObj=new JSONObject();
				 							
				 if(endPoint!=null && !endPoint.equalsIgnoreCase(BLANK))
				 { 
					 jsonObj.put(OPTION_ENDPOINT,endPoint);				  						      			     				
			      	 array.add(jsonObj);
				 }
			      
			}			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
	public String createBasinResponse(List<String> basinList)
	{
		String response=null;
		if(basinList.size()>0)
		{
			
			JSONArray array=new JSONArray();
			for(String basin:basinList)
			{
				 
				 JSONObject jsonObj=new JSONObject();
				 							
				 if(basin!=null && !basin.equalsIgnoreCase(BLANK))
				 { 
					 jsonObj.put(OPTION_BASIN,basin);				  						      			     				
			      	 array.add(jsonObj);
				 }
			      
			}			
			response=array.toString();		      
		}
		else
			response=BLANK;
		return response;
	}
}
