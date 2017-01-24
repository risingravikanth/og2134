package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.Contracts;

public interface ContractsDao {

//	List<Contracts> getContractsCriteriaData(int startDate,int endDate);
	
	
	
	List<String> getSelectedExportCompanies(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	List<String> getSelectedExportCountries(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	List<String> getSelectedExportTerminals(Map<String,List<String>> selectedOptions,int startDate,int endDate);
	
	
	List<String> getExportTerminals(int startDate,int endDate);
	
	List<String> getImportCountries(List<String> exportCountry);
	List<String> getImportCompanies(List<String> exportCompany);
	
	List<String> getExportCompanies();
	
	List<Contracts> getContractsCriteriaData(Map<String,List<String>> selectedOptions,List<String> exportTerminals,int startDate,int endDate);
	
//	List<String> getExportCompanyTerminals(String company,Map<String,List<String>> selectedOptions,int startDate,int endDate);
	List<String> getExportCompanyTerminals(String company,List<String> exportTerminals);
//	List<String> getExportCountryTerminals(String country,Map<String,List<String>> selectedOptions,int startDate,int endDate);
	List<String> getExportCountryTerminals(String country,List<String> exportTerminals);
}
