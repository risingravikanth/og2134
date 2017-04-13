package com.oganalysis.service.impl;

import static com.oganalysis.constants.ApplicationConstants.COMPANY;
import static com.oganalysis.constants.ApplicationConstants.COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.TERMINAL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.business.RefineriesCapacityBusinessService;
import com.oganalysis.business.RefineriesInfraBusinessService;
import com.oganalysis.helper.RefineriesJsonResponse;
import com.oganalysis.service.RefineriesService;

public class RefineriesServiceImpl implements RefineriesService {
	
	private RefineriesCapacityBusinessService refineriesCapacityBusinessServiceImpl;
	private RefineriesInfraBusinessService refineriesInfraBusinessServiceImpl;
	@Override
	public String getRefineriesData(Map<String, List<String>> selectedOptions,
			String startDate, String endDate, String displayType) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		if(null!=displayType && COMPANY.equalsIgnoreCase(displayType))
		{
			Map<String,Map<Integer,Double>> companiesCapacity=refineriesCapacityBusinessServiceImpl.getCapacityByCompany(selectedOptions, startDateVal, endDateVal);
			RefineriesJsonResponse jsonRes=new RefineriesJsonResponse();
			return jsonRes.createCapacityByCompany(companiesCapacity, startDateVal, endDateVal);			
		}	
		else if(null!=displayType && COUNTRY.equalsIgnoreCase(displayType))
		{	
			Map<String,Map<Integer,Double>> countriesCapacity=refineriesCapacityBusinessServiceImpl.getCapacityByCountry(selectedOptions, startDateVal, endDateVal);
			RefineriesJsonResponse jsonRes=new RefineriesJsonResponse();
			return jsonRes.createCapacityByCountry(countriesCapacity, startDateVal, endDateVal);
		}
		else if(null!=displayType && TERMINAL.equalsIgnoreCase(displayType))
		{	
			Map<String,Map<Integer,Double>> terminalsCapacity=refineriesCapacityBusinessServiceImpl.getCapacityByTerminal(selectedOptions, startDateVal, endDateVal);
			RefineriesJsonResponse jsonRes=new RefineriesJsonResponse();
			return jsonRes.createCapacityByTerminal(terminalsCapacity, startDateVal, endDateVal);
		}
		return null;
		
	}
	@Override
	public String getModalCapacityData(
			Map<String, List<String>> selectedOptions, String startDate,
			String endDate, String displayType, String recordName) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> modalCapacityData=new HashMap<String, Map<Integer,Double>>();		
		RefineriesJsonResponse jsonRes=new RefineriesJsonResponse();
		String modalCapacityDataRes=null;
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		
		if(null!=displayType && !displayType.equalsIgnoreCase(TERMINAL))
		{
			modalCapacityData=refineriesCapacityBusinessServiceImpl.getModalCapacityForRecord(selectedOptions,startDateVal, endDateVal,displayType,recordName);
			modalCapacityDataRes=jsonRes.createCapacityByTerminal(modalCapacityData, startDateVal, endDateVal);
		}			
		// Modal is different for Terminal displayType because of which below condition is required 
		else if(null!=displayType && displayType.equalsIgnoreCase(TERMINAL))
		{
			Map modalTerminal=refineriesCapacityBusinessServiceImpl.getTerminalData(recordName);
			modalCapacityDataRes=jsonRes.createTerminalData(modalTerminal);
		}
								
		return modalCapacityDataRes;
	}
	@Override
	public String getInfrastructureData(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String infrastructureDataRes=null;;
		List<Map<String,String>> refineriesInfra=refineriesInfraBusinessServiceImpl.getInfrastructure(selectedOptions);
		RefineriesJsonResponse jsonResponse=new RefineriesJsonResponse();
		infrastructureDataRes=jsonResponse.createInfrastructureRes(refineriesInfra);
		return infrastructureDataRes;
	}
	public RefineriesCapacityBusinessService getRefineriesCapacityBusinessServiceImpl() {
		return refineriesCapacityBusinessServiceImpl;
	}
	public void setRefineriesCapacityBusinessServiceImpl(
			RefineriesCapacityBusinessService refineriesCapacityBusinessServiceImpl) {
		this.refineriesCapacityBusinessServiceImpl = refineriesCapacityBusinessServiceImpl;
	}
	public RefineriesInfraBusinessService getRefineriesInfraBusinessServiceImpl() {
		return refineriesInfraBusinessServiceImpl;
	}
	public void setRefineriesInfraBusinessServiceImpl(
			RefineriesInfraBusinessService refineriesInfraBusinessServiceImpl) {
		this.refineriesInfraBusinessServiceImpl = refineriesInfraBusinessServiceImpl;
	}			
	
}
