package com.oganalysis.service.impl;

import static com.oganalysis.constants.ApplicationConstants.COMPANY;
import static com.oganalysis.constants.ApplicationConstants.COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.TERMINAL;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Workbook;

import com.oganalysis.business.StorageCapacityBusinessService;
import com.oganalysis.business.StorageInfraBusinessService;
import com.oganalysis.entities.source.Countries;
import com.oganalysis.entities.source.Region;
import com.oganalysis.entities.source.Status;
import com.oganalysis.excel.StorageExcel;
import com.oganalysis.helper.JsonResponse;
import com.oganalysis.helper.StorageJsonResponse;
import com.oganalysis.service.StorageService;

public class StorageServiceImpl implements StorageService {
	private StorageCapacityBusinessService storageCapacityBusinessServiceImpl;
	private StorageInfraBusinessService storageInfraBusinessServiceImpl;
	private StorageExcel storageExcel;
	@Override
	public String getStorageData(Map<String, List<String>> selectedOptions,
			String startYear, String endYear, String displayType) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		int startYearVal=Integer.parseInt(startYear);
		int endYearVal=Integer.parseInt(endYear);
		String response=null;
		if(null!=displayType && COMPANY.equalsIgnoreCase(displayType))
		{
			Map<String,Map<Integer,Double>> companiesCapacity=storageCapacityBusinessServiceImpl.getCapacityByCompany(selectedOptions, startYearVal, endYearVal);
			StorageJsonResponse jsonRes=new StorageJsonResponse();
			response=jsonRes.createCapacityByCompany(companiesCapacity, startYearVal, endYearVal);			
		}	
		else if(null!=displayType && COUNTRY.equalsIgnoreCase(displayType))
		{	
			Map<String,Map<Integer,Double>> countriesCapacity=storageCapacityBusinessServiceImpl.getCapacityByCountry(selectedOptions, startYearVal, endYearVal);
			StorageJsonResponse jsonRes=new StorageJsonResponse();
			response=jsonRes.createCapacityByCountry(countriesCapacity, startYearVal, endYearVal);
		}
		else if(null!=displayType && TERMINAL.equalsIgnoreCase(displayType))
		{	
			Map<String,Map<Integer,Double>> terminalsCapacity=storageCapacityBusinessServiceImpl.getCapacityByTerminal(selectedOptions, startYearVal, endYearVal);
			StorageJsonResponse jsonRes=new StorageJsonResponse();
			response=jsonRes.createCapacityByTerminal(terminalsCapacity, startYearVal, endYearVal);
		}
		return response;
		
	
	}
	@Override
	public String getModalCapacityData(
			Map<String, List<String>> selectedOptions, String startYear,
			String endYear, String displayType, String recordName) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> modalCapacityData=new HashMap<String, Map<Integer,Double>>();		
		StorageJsonResponse jsonRes=new StorageJsonResponse();
		String modalCapacityDataRes=null;
				
		if(null!=displayType && !displayType.equalsIgnoreCase(TERMINAL))
		{
			int startDateVal=Integer.parseInt(startYear);
			int endDateVal=Integer.parseInt(endYear);
			modalCapacityData=storageCapacityBusinessServiceImpl.getModalCapacityForRecord(selectedOptions,startDateVal, endDateVal,displayType,recordName);
			modalCapacityDataRes=jsonRes.createCapacityByTerminal(modalCapacityData, startDateVal, endDateVal);
		}			
		// Modal is different for Terminal displayType because of which below condition is required 
		else if(null!=displayType && displayType.equalsIgnoreCase(TERMINAL))
		{
			Map modalTerminal=storageCapacityBusinessServiceImpl.getTerminalData(recordName);
			modalCapacityDataRes=jsonRes.createTerminalData(modalTerminal);
		}
								
		return modalCapacityDataRes;
	}
	@Override
	public String getInfrastructureData(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String infrastructureDataRes=null;;
		List<Map<String,String>> storageInfra=storageInfraBusinessServiceImpl.getInfrastructure(selectedOptions);
		StorageJsonResponse jsonResponse=new StorageJsonResponse();
		infrastructureDataRes=jsonResponse.createInfrastructureRes(storageInfra);
		return infrastructureDataRes;
	}
	@Override
	public String getRegions() {
		// TODO Auto-generated method stub
		String jsonRes=null;
//		List<Region> regionsList=storageCapacityBusinessServiceImpl.getRegions();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createRegionsResponse(regionsList);
		return jsonRes;
	}
	@Override
	public String getCountries() {
		// TODO Auto-generated method stub
		String jsonRes=null;
//		List<Countries> countriesList=storageCapacityBusinessServiceImpl.getCountries();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createCountriesResponse(countriesList);
		return jsonRes;
	}
	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		String jsonRes=null;
//		List<Status> statusList=storageCapacityBusinessServiceImpl.getStatus();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createStatusResponse(statusList);
		return jsonRes;
	}
	@Override
	public String getLocations() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> locationsList=storageCapacityBusinessServiceImpl.getLocations();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createLocationsResponse(locationsList);
		return jsonRes;
	}
	@Override
	public String getOperators() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> operatorList=storageCapacityBusinessServiceImpl.getOperators();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createOperatorResponse(operatorList);
		return jsonRes;
	}
	@Override
	public String getOwners() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> ownersList=storageCapacityBusinessServiceImpl.getOwners();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createOwnersResponse(ownersList);
		return jsonRes;
	}
	@Override
	public Workbook getExcelTerminalData(String recordName,InputStream is) {
		// TODO Auto-generated method stub
		Map<String,String> terminalData=storageCapacityBusinessServiceImpl.getTerminalData(recordName);		
		Workbook wb=storageExcel.getExcelTerminalData(terminalData,is);
		return wb;
	}
	public void setStorageCapacityBusinessServiceImpl(
			StorageCapacityBusinessService storageCapacityBusinessServiceImpl) {
		this.storageCapacityBusinessServiceImpl = storageCapacityBusinessServiceImpl;
	}
	public void setStorageInfraBusinessServiceImpl(
			StorageInfraBusinessService storageInfraBusinessServiceImpl) {
		this.storageInfraBusinessServiceImpl = storageInfraBusinessServiceImpl;
	}
	public void setStorageExcel(StorageExcel storageExcel) {
		this.storageExcel = storageExcel;
	}
		
	
}
