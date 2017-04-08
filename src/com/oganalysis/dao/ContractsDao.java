package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.Contracts;

public interface ContractsDao {
		
	
	List<String> getSelectedExportCompanies(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	List<String> getSelectedExportCountries(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	List<String> getSelectedExportTerminals(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	List<String> getSelectedContractIndicators(Map<String,List<String>>selectedOptions,int startDate,int endDate);
	
	
	List<String> getContractIndicators(int startDate,int endDate);
	
	
	List<String> getImportCountries(List<String> exportCountry);
	List<String> getImportCompanies(List<String> exportCompany);	
	
	
//	List<String> getExportCompanyContractIndicators(String company,List<String> contractIndicators);
//	List<String> getExportTerminalContractIndicators(String terminal,List<String> contractIndicators);
//	List<String> getExportCountryContractIndicators(String country,List<String> contractIndicators);
	
//	Below is for Cache
	public List<String> getCompanyContractIndicators(String company);
	public List<String> getCountryContractIndicators(String country);
	public List<String> getTerminalContractIndicators(String terminal);
	
	public List<String> getExportCountries();
	public List<String> getExportCompanies(); //for cache and also companies filter
	public List<String> getExportTerminals();
	public List<Contracts> getContractIndicators(int year);
}
