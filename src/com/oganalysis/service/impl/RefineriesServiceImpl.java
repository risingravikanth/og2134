package com.oganalysis.service.impl;

import static com.oganalysis.constants.ApplicationConstants.COMPANY;
import static com.oganalysis.constants.ApplicationConstants.COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.TERMINAL;

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
//		List<Region> regionsList=refineriesCapacityBusinessServiceImpl.getRegions();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createRegionsResponse(regionsList);
		return jsonRes;
	}
	@Override
	public String getCountries() {
		// TODO Auto-generated method stub
		String jsonRes=null;
//		List<Countries> countriesList=refineriesCapacityBusinessServiceImpl.getCountries();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createCountriesResponse(countriesList);
		return jsonRes;
	}
	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		String jsonRes=null;
//		List<Status> statusList=refineriesCapacityBusinessServiceImpl.getStatus();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createStatusResponse(statusList);
		return jsonRes;
	}
	@Override
	public String getLocations() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> locationsList=refineriesCapacityBusinessServiceImpl.getLocations();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createLocationsResponse(locationsList);
		return jsonRes;
	}
	@Override
	public String getOperators() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> operatorList=refineriesCapacityBusinessServiceImpl.getOperators();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createOperatorResponse(operatorList);
		return jsonRes;
	}
	@Override
	public String getOwners() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> ownersList=refineriesCapacityBusinessServiceImpl.getOwners();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createOwnersResponse(ownersList);
		return jsonRes;
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
