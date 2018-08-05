package com.oganalysis.service.impl;

import static com.oganalysis.constants.ApplicationConstants.COMPANY;
import static com.oganalysis.constants.ApplicationConstants.COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.oganalysis.business.RefineriesCapacityBusinessService;
import com.oganalysis.business.RefineriesInfraBusinessService;
import com.oganalysis.entities.source.Countries;
import com.oganalysis.entities.source.Region;
import com.oganalysis.entities.source.Status;
import com.oganalysis.excel.RefineriesExcel;
import com.oganalysis.helper.JsonResponse;
import com.oganalysis.helper.RefineriesJsonResponse;
import com.oganalysis.service.RefineriesService;

public class RefineriesServiceImpl implements RefineriesService {
	
	private RefineriesCapacityBusinessService refineriesCapacityBusinessServiceImpl;
	private RefineriesInfraBusinessService refineriesInfraBusinessServiceImpl;
	private RefineriesExcel refineriesExcel;
	@Override
	public String getRefineriesData(Map<String, List<String>> selectedOptions,
			String startDate, String endDate, String displayType) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		String response=null;
		if(null!=displayType && COMPANY.equalsIgnoreCase(displayType))
		{
			Map<String,Map<Integer,Double>> companiesCapacity=refineriesCapacityBusinessServiceImpl.getCapacityByCompany(selectedOptions, startDateVal, endDateVal);
			RefineriesJsonResponse jsonRes=new RefineriesJsonResponse();
			response=jsonRes.createCapacityByCompany(companiesCapacity, startDateVal, endDateVal);			
		}	
		else if(null!=displayType && COUNTRY.equalsIgnoreCase(displayType))
		{	
			Map<String,Map<Integer,Double>> countriesCapacity=refineriesCapacityBusinessServiceImpl.getCapacityByCountry(selectedOptions, startDateVal, endDateVal);
			RefineriesJsonResponse jsonRes=new RefineriesJsonResponse();
			response=jsonRes.createCapacityByCountry(countriesCapacity, startDateVal, endDateVal);
		}
		else if(null!=displayType && TERMINAL.equalsIgnoreCase(displayType))
		{	
			Map<String,Map<Integer,Double>> terminalsCapacity=refineriesCapacityBusinessServiceImpl.getCapacityByTerminal(selectedOptions, startDateVal, endDateVal);
			RefineriesJsonResponse jsonRes=new RefineriesJsonResponse();
			response=jsonRes.createCapacityByTerminal(terminalsCapacity, startDateVal, endDateVal);
		}
		return response;
		
	}
	@Override
	public String getModalCapacityData(
			Map<String, List<String>> selectedOptions, String startDate,
			String endDate, String displayType, String recordName) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> modalCapacityData=new HashMap<String, Map<Integer,Double>>();		
		RefineriesJsonResponse jsonRes=new RefineriesJsonResponse();
		String modalCapacityDataRes=null;
				
		if(null!=displayType && !displayType.equalsIgnoreCase(TERMINAL))
		{
			int startDateVal=Integer.parseInt(startDate);
			int endDateVal=Integer.parseInt(endDate);
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
	public Workbook getExcelTerminalData(String recordName,InputStream is) {
		// TODO Auto-generated method stub
		Map terminalData=refineriesCapacityBusinessServiceImpl.getTerminalData(recordName);
		Workbook wb=refineriesExcel.getExcelTerminalData(terminalData,is);
		return wb;
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
	@Override
	public String getRegions() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> regionsList=refineriesCapacityBusinessServiceImpl.getRegions();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createRegions(regionsList);
		return jsonRes;
	}
	@Override
	public String getCountries(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_REGIONS).isEmpty())//country is dependent on region filters
			return res.createEmptyJsonArray();
		else
		{
			List<String> countriesList=refineriesCapacityBusinessServiceImpl.getCountries(selectedOptions);			
			return res.createCountries(countriesList);
			
		}
		
	}
	@Override
	public String getStatus(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_OWNERS).isEmpty())//status is dependent on owner filters
			return res.createEmptyJsonArray();
		else
		{
			List<String> statusList=refineriesCapacityBusinessServiceImpl.getStatus(selectedOptions);			
			return res.createStatus(statusList);			
		}
		
	}
	@Override
	public String getLocations(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_COUNTRIES).isEmpty())//location is dependent on country filters.
			return res.createEmptyJsonArray();
		else
		{
			List<String> locationsList=refineriesCapacityBusinessServiceImpl.getLocations(selectedOptions);			
			return res.createLocationsResponse(locationsList);			
		}
		
	}
	@Override
	public String getOperators(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_LOCATIONS).isEmpty())//operator is dependent on location filters
			return res.createEmptyJsonArray();
		else
		{
			List<String> operatorList=refineriesCapacityBusinessServiceImpl.getOperators(selectedOptions);			
			return res.createOperatorResponse(operatorList);			
		}
		
	}
	@Override
	public String getOwners(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_OPERATORS).isEmpty())//owner is dependent on operator filters
			return res.createEmptyJsonArray();
		else
		{
			List<String> ownersList=refineriesCapacityBusinessServiceImpl.getOwners(selectedOptions);			
			return res.createOwnersResponse(ownersList);			
		}
		
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
	public void setRefineriesExcel(RefineriesExcel refineriesExcel) {
		this.refineriesExcel = refineriesExcel;
	}
		
}
