package com.oganalysis.service.impl;

import static com.oganalysis.constants.ApplicationConstants.COMPANY;
import static com.oganalysis.constants.ApplicationConstants.COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.LNG_LIQUEFACTION;
import static com.oganalysis.constants.ApplicationConstants.LNG_REGASIFICATION;
import static com.oganalysis.constants.ApplicationConstants.TERMINAL;
import static com.oganalysis.constants.ApplicationConstants.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;
import org.json.simple.JSONObject;

import com.oganalysis.business.LngCapacityBusinessService;
import com.oganalysis.business.LngInfraBusinessService;
import com.oganalysis.excel.LngExcel;
import com.oganalysis.helper.JsonResponse;
import com.oganalysis.helper.LngJsonResponse;
import com.oganalysis.service.LngDataService;

public class LngDataServiceImpl implements LngDataService{
	
	
	private LngCapacityBusinessService lngCapacityBusinessServiceImpl;
	private LngInfraBusinessService lngInfraBusinessServiceImpl;
	private LngExcel lngExcel;
	
	
	@Override
	public String getCapacityData(
			Map<String, List<String>> selectedOptions, String startDate,
			String endDate, String displayType) {
		// TODO Auto-generated method stub
		
		String capacityDataRes=null;
		if(null!=displayType && displayType.equalsIgnoreCase(COUNTRY))		
			capacityDataRes=getCapacityByCountry(selectedOptions,startDate,endDate);		
		else if(null!=displayType && displayType.equalsIgnoreCase(TERMINAL))		
			capacityDataRes=getCapacityByTerminal(selectedOptions,startDate,endDate);		
		else if(null!=displayType && displayType.equalsIgnoreCase(COMPANY))		
			capacityDataRes=getCapacityByCompany(selectedOptions,startDate,endDate);		
		
		return capacityDataRes;
	}
	@Override
	public String getModalCapacityData(Map<String, List<String>> selectedOptions,
			String startDate, String endDate, String displayType, String type,
			String recordName) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> modalCapacityData=new HashMap<String, Map<Integer,Double>>();		
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		JSONObject modalCapacityDataRes=null;
		int startDateVal=0;
		int endDateVal=0;
		if(null!=displayType && !displayType.equalsIgnoreCase(TERMINAL))
		{
			startDateVal=Integer.parseInt(startDate);
			endDateVal=Integer.parseInt(endDate);
		}		
		
		if(null!=displayType && !displayType.equalsIgnoreCase(TERMINAL) && null!=type && LNG_LIQUEFACTION.equalsIgnoreCase(type))
		{
			modalCapacityData=lngCapacityBusinessServiceImpl.getLiqueModalCapacityForRecord(selectedOptions,startDate, endDate, displayType, recordName);
			modalCapacityDataRes=lngJsonResponse.createCapacityLiquefactionRes(modalCapacityData, startDateVal, endDateVal, TERMINAL);
		}			
		else if(null!=displayType && !displayType.equalsIgnoreCase(TERMINAL) && null!=type && LNG_REGASIFICATION.equalsIgnoreCase(type))
		{
			modalCapacityData=lngCapacityBusinessServiceImpl.getRegasModalCapacityForRecord(selectedOptions,startDate, endDate, displayType, recordName);
			modalCapacityDataRes=lngJsonResponse.createCapacityRegasificationRes(modalCapacityData, startDateVal, endDateVal, TERMINAL);
		}
		// Modal is different for Terminal displayType because of which below conditions are required 
		else if(null!=displayType && displayType.equalsIgnoreCase(TERMINAL) && null!=type && LNG_LIQUEFACTION.equalsIgnoreCase(type))
		{
			Map modalTerminal=lngCapacityBusinessServiceImpl.getTerminalData(recordName,type);
			modalCapacityDataRes=lngJsonResponse.createTerminalDataRes(modalTerminal);
		}
		else if(null!=displayType && displayType.equalsIgnoreCase(TERMINAL) && null!=type && LNG_REGASIFICATION.equalsIgnoreCase(type))
		{
			Map modalTerminal=lngCapacityBusinessServiceImpl.getTerminalData(recordName,type);
			modalCapacityDataRes=lngJsonResponse.createTerminalDataRes(modalTerminal);
		}
							
		return modalCapacityDataRes.toJSONString();
	}
	private String getCapacityByCompany(Map<String, List<String>> selectedOptions, String startDate,String endDate)
	{
		Map<String,Map<Integer,Double>> liquefaction=lngCapacityBusinessServiceImpl.getLiquefactionCapacityByCompany(selectedOptions, startDate, endDate);
		Map<String,Map<Integer,Double>> regasification=lngCapacityBusinessServiceImpl.getRegasificationCapacityByCompany(selectedOptions, startDate, endDate);
		
		Map<String,Map<String,Map<Integer,Double>>> capacityDataByCompany=new HashMap<String, Map<String,Map<Integer,Double>>>(); 
		capacityDataByCompany.put(LNG_LIQUEFACTION, liquefaction);
		capacityDataByCompany.put(LNG_REGASIFICATION, regasification);
		
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		String capacityByCompanyRes=lngJsonResponse.createCapacityByCompanyRes(capacityDataByCompany, startDate, endDate);
		return capacityByCompanyRes;
	}
	private String getCapacityByTerminal(Map<String, List<String>> selectedOptions, String startDate,String endDate)
	{
		Map<String,Map<Integer,Double>> liquefaction=lngCapacityBusinessServiceImpl.getLiquefactionCapacityByTerminal(selectedOptions, startDate, endDate);
		Map<String,Map<Integer,Double>> regasification=lngCapacityBusinessServiceImpl.getRegasificationCapacityByTerminal(selectedOptions, startDate, endDate);
		
		Map<String,Map<String,Map<Integer,Double>>> capacityDataByTerminal=new HashMap<String, Map<String,Map<Integer,Double>>>(); 
		capacityDataByTerminal.put(LNG_LIQUEFACTION, liquefaction);
		capacityDataByTerminal.put(LNG_REGASIFICATION, regasification);
		
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		String capacityByTerminalRes=lngJsonResponse.createCapacityByTerminalRes(capacityDataByTerminal, startDate, endDate);
		return capacityByTerminalRes;
	}
	private String getCapacityByCountry(Map<String, List<String>> selectedOptions, String startDate,String endDate)
	{
		Map<String,Map<Integer,Double>> liquefaction=lngCapacityBusinessServiceImpl.getLiquefactionCapacityByCountry(selectedOptions,startDate,endDate);
		Map<String,Map<Integer,Double>> regasification=lngCapacityBusinessServiceImpl.getRegasificationCapacityByCountry(selectedOptions, startDate, endDate);
		
		Map<String,Map<String,Map<Integer,Double>>> capacityDataByCountry=new HashMap<String, Map<String,Map<Integer,Double>>>();
		capacityDataByCountry.put(LNG_LIQUEFACTION, liquefaction);
		capacityDataByCountry.put(LNG_REGASIFICATION, regasification);
		
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		String capacityDataByCountryRes=lngJsonResponse.createCapacityByCountryRes(capacityDataByCountry,startDate,endDate);
		return capacityDataByCountryRes;
	}
	@Override
	public Workbook getExcelTerminalData(String recordName, String type,InputStream is) {
		// TODO Auto-generated method stub
		Map<String,String> terminalData=lngCapacityBusinessServiceImpl.getTerminalData(recordName, type);		
		Workbook wb=lngExcel.getExcelTerminalData(terminalData,is);
		return wb;
	}
	@Override
	public String getInfrastructureData(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String infrastructureDataRes=null;;
		List<Map<String,String>> liquefaction=lngInfraBusinessServiceImpl.getLiquefactionInfrastructure(selectedOptions);
		List<Map<String,String>> regasification=lngInfraBusinessServiceImpl.getRegasificationInfrastructure(selectedOptions);
		Map<String,List<Map<String,String>>> infrastructureMap=new HashMap<String, List<Map<String,String>>>();
		infrastructureMap.put(LNG_LIQUEFACTION, liquefaction);
		infrastructureMap.put(LNG_REGASIFICATION, regasification);
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		infrastructureDataRes=lngJsonResponse.createInfrastructureRes(infrastructureMap);
		return infrastructureDataRes;
	}
	@Override
	public String getRegions() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> regionsList=lngCapacityBusinessServiceImpl.getRegions();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createRegions(regionsList);
		return jsonRes;
	}
	@Override
	public String getCountries(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String jsonRes=null;
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_REGIONS).isEmpty())// country is dependent on region filters
				return res.createEmptyJsonArray();
		else
		{
			List<String> countriesList=lngCapacityBusinessServiceImpl.getCountries(selectedOptions);			
			jsonRes=res.createCountries(countriesList);
			return jsonRes;
		}				
	}
	@Override
	public String getStatus(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_OWNERS).isEmpty()) //status is dependent on owner filters
			return res.createEmptyJsonArray();
		else
		{
			List<String> statusList=lngCapacityBusinessServiceImpl.getStatus(selectedOptions);
			return res.createStatus(statusList);			
		}
		
	}
	@Override
	public String getType(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_STATUSES).isEmpty())//Type is dependent on status filters
			return res.createEmptyJsonArray();
		else
		{
			List<String> typeList=lngCapacityBusinessServiceImpl.getType(selectedOptions);			
			return res.createType(typeList);			
		}
		
	}
	@Override
	public String getLocations(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_COUNTRIES).isEmpty())// location is dependent on countries filers
			return res.createEmptyJsonArray();
		else
		{
			List<String> locationsList=lngCapacityBusinessServiceImpl.getLocations(selectedOptions);			
			return res.createLocationsResponse(locationsList);			
		}
		
	}
	@Override
	public String getOperators(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_LOCATIONS).isEmpty())// operators is dependent on Locations filters
			return res.createEmptyJsonArray();
		else
		{
			List<String> operatorList=lngCapacityBusinessServiceImpl.getOperators(selectedOptions);			
			return res.createOperatorResponse(operatorList);
			
		}
	}
		
	@Override
	public String getOwners(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_OPERATORS).isEmpty()) // owner is dependent on Operator filters
			return res.createEmptyJsonArray();
		else
		{
			List<String> ownersList=lngCapacityBusinessServiceImpl.getOwners(selectedOptions);			
			return res.createOwnersResponse(ownersList);			
		}
		
	}
	public LngCapacityBusinessService getLngCapacityBusinessServiceImpl() {
		return lngCapacityBusinessServiceImpl;
	}
	public void setLngCapacityBusinessServiceImpl(
			LngCapacityBusinessService lngCapacityBusinessServiceImpl) {
		this.lngCapacityBusinessServiceImpl = lngCapacityBusinessServiceImpl;
	}
	public LngInfraBusinessService getLngInfraBusinessServiceImpl() {
		return lngInfraBusinessServiceImpl;
	}
	public void setLngInfraBusinessServiceImpl(
			LngInfraBusinessService lngInfraBusinessServiceImpl) {
		this.lngInfraBusinessServiceImpl = lngInfraBusinessServiceImpl;
	}
	public void setLngExcel(LngExcel lngExcel) {
		this.lngExcel = lngExcel;
	}
	
	
	
	
	


}
