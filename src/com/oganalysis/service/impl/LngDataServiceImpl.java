package com.oganalysis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.business.LngBusinessService;
import com.oganalysis.helper.LngJsonResponse;
import com.oganalysis.service.LngDataService;

public class LngDataServiceImpl implements LngDataService{
	
	
	private LngBusinessService lngBusinessServiceImpl;
	
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
	private String getCapacityByCompany(Map<String, List> selectedOptions, String startDate,String endDate)
	{
		Map<String,Map<Integer,Double>> liquefaction=lngBusinessServiceImpl.getLiquefactionCapacityByCompany(selectedOptions, startDate, endDate);
		Map<String,Map<Integer,Double>> regasification=lngBusinessServiceImpl.getRegasificationCapacityByCompany(selectedOptions, startDate, endDate);
		
		Map<String,Map<String,Map<Integer,Double>>> capacityDataByCompany=new HashMap<String, Map<String,Map<Integer,Double>>>(); 
		capacityDataByCompany.put(LIQUEFACTION, liquefaction);
		capacityDataByCompany.put(REGASIFICATION, regasification);
		
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		String capacityByCompanyRes=lngJsonResponse.createCapacityByCompanyRes(capacityDataByCompany, startDate, endDate);
		return capacityByCompanyRes;
	}
	private String getCapacityByTerminal(Map<String, List> selectedOptions, String startDate,String endDate)
	{
		Map<String,Map<Integer,Double>> liquefaction=lngBusinessServiceImpl.getLiquefactionCapacityByTerminal(selectedOptions, startDate, endDate);
		Map<String,Map<Integer,Double>> regasification=lngBusinessServiceImpl.getRegasificationCapacityByTerminal(selectedOptions, startDate, endDate);
		
		Map<String,Map<String,Map<Integer,Double>>> capacityDataByTerminal=new HashMap<String, Map<String,Map<Integer,Double>>>(); 
		capacityDataByTerminal.put(LIQUEFACTION, liquefaction);
		capacityDataByTerminal.put(REGASIFICATION, regasification);
		
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		String capacityByTerminalRes=lngJsonResponse.createCapacityByTerminalRes(capacityDataByTerminal, startDate, endDate);
		return capacityByTerminalRes;
	}
	private String getCapacityByCountry(Map<String, List> selectedOptions, String startDate,String endDate)
	{
		Map<String,Map<Integer,Double>> liquefaction=lngBusinessServiceImpl.getLiquefactionCapacityByCountry(selectedOptions,startDate,endDate);
		Map<String,Map<Integer,Double>> regasification=lngBusinessServiceImpl.getRegasificationCapacityByCountry(selectedOptions, startDate, endDate);
		
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
		List<Map<String,String>> liquefaction=lngBusinessServiceImpl.getLiquefactionInfrastructure(selectedOptions);
		List<Map<String,String>> regasification=lngBusinessServiceImpl.getRegasificationInfrastructure(selectedOptions);
		Map<String,List<Map<String,String>>> infrastructureMap=new HashMap<String, List<Map<String,String>>>();
		infrastructureMap.put(LIQUEFACTION, liquefaction);
		infrastructureMap.put(REGASIFICATION, regasification);
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		infrastructureDataRes=lngJsonResponse.createInfrastructureRes(infrastructureMap);
		return infrastructureDataRes;
	}
	public LngBusinessService getLngBusinessServiceImpl() {
		return lngBusinessServiceImpl;
	}

	public void setLngBusinessServiceImpl(LngBusinessService lngBusinessServiceImpl) {
		this.lngBusinessServiceImpl = lngBusinessServiceImpl;
	}
	

	

	

}
