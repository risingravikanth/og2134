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
import com.oganalysis.excel.StorageExcel;
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
