package com.oganalysis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONObject;

import com.oganalysis.business.LngCapacityBusinessService;
import com.oganalysis.business.LngInfraBusinessService;
import com.oganalysis.business.impl.LngCapacityBusinessServiceImpl;
import com.oganalysis.helper.LngJsonResponse;
import com.oganalysis.service.LngDataService;

public class LngDataServiceImpl implements LngDataService{
	
	
	private LngCapacityBusinessService lngCapacityBusinessServiceImpl;
	private LngInfraBusinessService lngInfraBusinessServiceImpl;
	
	public static final String REGASIFICATION="Regasification";
	public static final String LIQUEFACTION="Liquefaction";
	
	@Override
	public String getCapacityData(
			Map<String, List> selectedOptions, String startDate,
			String endDate, String displayType) {
		// TODO Auto-generated method stub
		String capacityDataRes=null;
		if(null!=displayType && displayType.equalsIgnoreCase("country"))
		{
			capacityDataRes=getCapacityByCountry(selectedOptions,startDate,endDate);
		}
		else if(null!=displayType && displayType.equalsIgnoreCase("terminal"))
		{
			capacityDataRes=getCapacityByTerminal(selectedOptions,startDate,endDate);
		}
		else if(null!=displayType && displayType.equalsIgnoreCase("company"))
		{
			capacityDataRes=getCapacityByCompany(selectedOptions,startDate,endDate);
		}
		
		return capacityDataRes;
	}
	@Override
	public String getModalCapacityData(Map<String, List> selectedOptions,
			String startDate, String endDate, String displayType, String type,
			String recordName) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> modalCapacityData=new HashMap<String, Map<Integer,Double>>();		
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		JSONObject modalCapacityDataRes=null;
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		
		if(null!=displayType && !displayType.equalsIgnoreCase("terminal") && null!=type && LIQUEFACTION.equalsIgnoreCase(type))
		{
			modalCapacityData=lngCapacityBusinessServiceImpl.getLiqueModalCapacityForRecord(startDate, endDate, displayType, recordName);
			modalCapacityDataRes=lngJsonResponse.createCapacityLiquefactionRes(modalCapacityData, startDateVal, endDateVal, "terminal");
		}			
		else if(null!=displayType && !displayType.equalsIgnoreCase("terminal") && null!=type && REGASIFICATION.equalsIgnoreCase(type))
		{
			modalCapacityData=lngCapacityBusinessServiceImpl.getRegasModalCapacityForRecord(startDate, endDate, displayType, recordName);
			modalCapacityDataRes=lngJsonResponse.createCapacityRegasificationRes(modalCapacityData, startDateVal, endDateVal, "terminal");
		}
		// Modal is different for Terminal displayType because of which below conditions are required 
		else if(null!=displayType && displayType.equalsIgnoreCase("terminal") && null!=type && LIQUEFACTION.equalsIgnoreCase(type))
		{
			Map modalTerminal=lngCapacityBusinessServiceImpl.getTerminalData(recordName,type);
			modalCapacityDataRes=lngJsonResponse.createTerminalDataRes(modalTerminal);
		}
		else if(null!=displayType && displayType.equalsIgnoreCase("terminal") && null!=type && REGASIFICATION.equalsIgnoreCase(type))
		{
			Map modalTerminal=lngCapacityBusinessServiceImpl.getTerminalData(recordName,type);
			modalCapacityDataRes=lngJsonResponse.createTerminalDataRes(modalTerminal);
		}
							
		return modalCapacityDataRes.toJSONString();
	}
	private String getCapacityByCompany(Map<String, List> selectedOptions, String startDate,String endDate)
	{
		Map<String,Map<Integer,Double>> liquefaction=lngCapacityBusinessServiceImpl.getLiquefactionCapacityByCompany(selectedOptions, startDate, endDate);
		Map<String,Map<Integer,Double>> regasification=lngCapacityBusinessServiceImpl.getRegasificationCapacityByCompany(selectedOptions, startDate, endDate);
		
		Map<String,Map<String,Map<Integer,Double>>> capacityDataByCompany=new HashMap<String, Map<String,Map<Integer,Double>>>(); 
		capacityDataByCompany.put(LIQUEFACTION, liquefaction);
		capacityDataByCompany.put(REGASIFICATION, regasification);
		
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		String capacityByCompanyRes=lngJsonResponse.createCapacityByCompanyRes(capacityDataByCompany, startDate, endDate);
		return capacityByCompanyRes;
	}
	private String getCapacityByTerminal(Map<String, List> selectedOptions, String startDate,String endDate)
	{
		Map<String,Map<Integer,Double>> liquefaction=lngCapacityBusinessServiceImpl.getLiquefactionCapacityByTerminal(selectedOptions, startDate, endDate);
		Map<String,Map<Integer,Double>> regasification=lngCapacityBusinessServiceImpl.getRegasificationCapacityByTerminal(selectedOptions, startDate, endDate);
		
		Map<String,Map<String,Map<Integer,Double>>> capacityDataByTerminal=new HashMap<String, Map<String,Map<Integer,Double>>>(); 
		capacityDataByTerminal.put(LIQUEFACTION, liquefaction);
		capacityDataByTerminal.put(REGASIFICATION, regasification);
		
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		String capacityByTerminalRes=lngJsonResponse.createCapacityByTerminalRes(capacityDataByTerminal, startDate, endDate);
		return capacityByTerminalRes;
	}
	private String getCapacityByCountry(Map<String, List> selectedOptions, String startDate,String endDate)
	{
		Map<String,Map<Integer,Double>> liquefaction=lngCapacityBusinessServiceImpl.getLiquefactionCapacityByCountry(selectedOptions,startDate,endDate);
		Map<String,Map<Integer,Double>> regasification=lngCapacityBusinessServiceImpl.getRegasificationCapacityByCountry(selectedOptions, startDate, endDate);
		
		Map<String,Map<String,Map<Integer,Double>>> capacityDataByCountry=new HashMap<String, Map<String,Map<Integer,Double>>>();
		capacityDataByCountry.put(LIQUEFACTION, liquefaction);
		capacityDataByCountry.put(REGASIFICATION, regasification);
		
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		String capacityDataByCountryRes=lngJsonResponse.createCapacityByCountryRes(capacityDataByCountry,startDate,endDate);
		return capacityDataByCountryRes;
	}
	@Override
	public String getInfrastructureData(Map<String,List> selectedOptions) {
		// TODO Auto-generated method stub
		String infrastructureDataRes=null;;
		List<Map<String,String>> liquefaction=lngInfraBusinessServiceImpl.getLiquefactionInfrastructure(selectedOptions);
		List<Map<String,String>> regasification=lngInfraBusinessServiceImpl.getRegasificationInfrastructure(selectedOptions);
		Map<String,List<Map<String,String>>> infrastructureMap=new HashMap<String, List<Map<String,String>>>();
		infrastructureMap.put(LIQUEFACTION, liquefaction);
		infrastructureMap.put(REGASIFICATION, regasification);
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		infrastructureDataRes=lngJsonResponse.createInfrastructureRes(infrastructureMap);
		return infrastructureDataRes;
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
	
	
	


}
