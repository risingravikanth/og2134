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
		
		Map<String,Map<Integer,Double>> liquefaction=lngBusinessServiceImpl.getLiquefactionCapacityByCountry(selectedOptions,startDate,endDate);
		Map<String,Map<Integer,Double>> regasification=lngBusinessServiceImpl.getRegasificationCapacityByCountry(selectedOptions, startDate, endDate);
		
		Map<String,Map<String,Map<Integer,Double>>> capacityData=new HashMap<String, Map<String,Map<Integer,Double>>>();
		capacityData.put(LIQUEFACTION, liquefaction);
		capacityData.put(REGASIFICATION, regasification);
		
		LngJsonResponse lngJsonResponse=new LngJsonResponse();
		String capacityDataRes=lngJsonResponse.createCapacityByCountryRes(capacityData,startDate,endDate);
		
		return capacityDataRes;
	}

	public LngBusinessService getLngBusinessServiceImpl() {
		return lngBusinessServiceImpl;
	}

	public void setLngBusinessServiceImpl(LngBusinessService lngBusinessServiceImpl) {
		this.lngBusinessServiceImpl = lngBusinessServiceImpl;
	}

	

	

}
