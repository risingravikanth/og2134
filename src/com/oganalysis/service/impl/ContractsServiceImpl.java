package com.oganalysis.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.business.ContractsBusinessService;
import com.oganalysis.helper.ContractsJsonResponse;
import com.oganalysis.service.ContractsService;

public class ContractsServiceImpl implements ContractsService {

	private ContractsBusinessService contractsBusinessServiceImpl;
	@Override
	public String getContractsData(
			Map<String, List<String>> selectedOptions, String startDate,
			String endDate,String displayType) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.valueOf(startDate);
		int endDateVal=Integer.valueOf(endDate);
		String contractsDataRes=null;
		if(null!=displayType && "company".equalsIgnoreCase(displayType))
			contractsDataRes=getQuantityByCompany(selectedOptions, startDateVal, endDateVal); 
		else if(null!=displayType && "country".equalsIgnoreCase(displayType))
			contractsDataRes=getQuantityByCountry(selectedOptions, startDateVal, endDateVal);
		else if(null!=displayType && "terminal".equalsIgnoreCase(displayType))
			contractsDataRes=getQuantityByTerminal(selectedOptions, startDateVal, endDateVal);
		return contractsDataRes;
	}
	
	private String getQuantityByCompany(Map<String,List<String>> selectedOptions,int startDate,int endDate)
	{
		Map<String,Map<Integer,Float>> companyQuantities=contractsBusinessServiceImpl.getQuantityByCompany(selectedOptions, startDate, endDate);
		ContractsJsonResponse jsonRes=new ContractsJsonResponse();
		String quantityByCompanies=jsonRes.createQuantityByCompanyRes(companyQuantities, startDate, endDate);
		return quantityByCompanies;
	}
	private String getQuantityByCountry(Map<String,List<String>> selectedOptions,int startDate,int endDate)
	{
		Map<String,Map<Integer,Float>> countryQuantities=contractsBusinessServiceImpl.getQuantityByCountry(selectedOptions, startDate, endDate);
		ContractsJsonResponse jsonRes=new ContractsJsonResponse();
		String quantityByCountries=jsonRes.createQuantityByCountryRes(countryQuantities, startDate, endDate);
		return quantityByCountries;
	}
	private String getQuantityByTerminal(Map<String,List<String>> selectedOptions,int startDate,int endDate)
	{
		Map<String,Map<Integer,Float>> terminalQuantities=contractsBusinessServiceImpl.getQuantityByTerminal(selectedOptions, startDate, endDate);
		ContractsJsonResponse jsonRes=new ContractsJsonResponse();
		String quantityByCountries=jsonRes.createQuantityByTerminalRes(terminalQuantities, startDate, endDate);
		return quantityByCountries;
	}
	@Override
	public String getModalQuantityData(
			Map<String, List<String>> selectedOptions, String startDate,
			String endDate, String displayType, String recordName) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Float>> modalQuantityData=new HashMap<String, Map<Integer,Float>>();		
		ContractsJsonResponse contractsJsonResponse=new ContractsJsonResponse();
		String modalQuantityDataRes=null;
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		
		if(null!=displayType && !displayType.equalsIgnoreCase("terminal"))
		{
			modalQuantityData=contractsBusinessServiceImpl.getQuantitiesForRecord(selectedOptions,startDateVal, endDateVal, displayType, recordName);
			modalQuantityDataRes=contractsJsonResponse.createQuantityByTerminalRes(modalQuantityData, startDateVal, endDateVal);
		}							 	
							
		return modalQuantityDataRes;
	}
	@Override
	public String getImportCountries(List<String> exportCountries) {
		// TODO Auto-generated method stub
		List<String> importCountries=contractsBusinessServiceImpl.getImportCountries(exportCountries);
		ContractsJsonResponse contractsJsonResponse=new ContractsJsonResponse();
		String importCountriesRes=contractsJsonResponse.createImportCountries(importCountries);
		return importCountriesRes;
	}

	@Override
	public String getImportCompanies(List<String> exportCompanies) {
		// TODO Auto-generated method stub
		List<String> importCompanies=contractsBusinessServiceImpl.getImportCompanies(exportCompanies);
		ContractsJsonResponse contractsJsonResponse=new ContractsJsonResponse();
		String importCompaniesRes=contractsJsonResponse.createImportCompanies(importCompanies);
		return importCompaniesRes;
	}
	@Override
	public String getExportCompanies() {
		// TODO Auto-generated method stub
		List<String> exportCompanies=contractsBusinessServiceImpl.getExportCompanies();
		ContractsJsonResponse contractsJsonResponse=new ContractsJsonResponse();
		String importCompaniesRes=contractsJsonResponse.createExportCompanies(exportCompanies);
		return importCompaniesRes;
	}
	public ContractsBusinessService getContractsBusinessServiceImpl() {
		return contractsBusinessServiceImpl;
	}
	public void setContractsBusinessServiceImpl(
			ContractsBusinessService contractsBusinessServiceImpl) {
		this.contractsBusinessServiceImpl = contractsBusinessServiceImpl;
	}

	

	

	
	
}
