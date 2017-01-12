package com.oganalysis.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.oganalysis.business.ContractsBusinessService;
import com.oganalysis.dao.ContractsDao;
import com.oganalysis.entities.Contracts;

public class ContractsBusinessServiceImpl implements ContractsBusinessService {

	private ContractsDao contractsDao;
	@Override
	public Map<String, Map<Integer, Float>> getQuantityByCompany(
			Map<String, List<String>> selectedOptions,int startDate,int endDate) {
		// TODO Auto-generated method stub		
		Map<String,Map<Integer,Float>> companiesQuantity=new HashMap<String, Map<Integer,Float>>();
		List<String> selectedExportCompanies=contractsDao.getSelectedExportCompanies(selectedOptions,startDate,endDate);
		if(selectedExportCompanies.size()>0)
			companiesQuantity=calculateCompaniesQuantity(selectedExportCompanies,selectedOptions,startDate,endDate);
		return companiesQuantity;
	}
	private Map<String,Map<Integer,Float>> calculateCompaniesQuantity(List<String> companies,Map<String,List<String>> selectedOptions,int startDate,int endDate)
	{
		Map<String,Map<Integer,Float>> companiesQuantity=new HashMap<String, Map<Integer,Float>>();
		Map<String,List<String>> selectedOptionsWithoutCompanies=getSelectedOptionsWithoutCompanies(selectedOptions);
		List<String> selectedExportTerminals=contractsDao.getSelectedExportTerminals(selectedOptions, startDate, endDate);
		List<Contracts> contractsList=contractsDao.getContractsCriteriaData(selectedOptionsWithoutCompanies,selectedExportTerminals,startDate, endDate);
		List<Integer> years=getSelectedYears(startDate, endDate);
		for(String company:companies)
		{
			List<String> exportTerminals=contractsDao.getExportCompanyTerminals(company,selectedExportTerminals);//selectedOptions,startDate,endDate);
			Map<Integer,Float> yearMap=null;
			if(exportTerminals.size()>0)
			{
				yearMap=new HashMap<Integer, Float>();
				for(Integer year:years)
				{
					float soq=0;
					for(String terminal:exportTerminals)
					{
						for(Contracts contracts:contractsList)
						{
							if(terminal.equals(contracts.getExportTerminal()) && year==contracts.getYear())
								soq=soq+contracts.getContractedQuantity();
						}
					}
					yearMap.put(year, soq);
				}
				companiesQuantity.put(company, yearMap);
			}
			
		}
		return companiesQuantity;
	}
	@Override
	public Map<String, Map<Integer, Float>> getQuantityByCountry(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Float>> countriesQuantity=new HashMap<String, Map<Integer,Float>>();
		List<String> exportCountries=contractsDao.getSelectedExportCountries(selectedOptions, startDate, endDate);
		if(exportCountries.size()>0)
			countriesQuantity=calculateCountriesQuantity(exportCountries,selectedOptions,startDate,endDate);
		return countriesQuantity;
	}
	private Map<String,Map<Integer,Float>> calculateCountriesQuantity(List<String> countries,Map<String,List<String>> selectedOptions,int startDate,int endDate)
	{
		Map<String,Map<Integer,Float>> countriesQuantity=new HashMap<String, Map<Integer,Float>>();
		Map<String,List<String>> selectedOptionsWithoutCompanies=getSelectedOptionsWithoutCompanies(selectedOptions);
		List<String> selectedExportTerminals=contractsDao.getSelectedExportTerminals(selectedOptions, startDate, endDate);
		List<Contracts> contractsList=contractsDao.getContractsCriteriaData(selectedOptionsWithoutCompanies,selectedExportTerminals,startDate, endDate);
		List<Integer> years=getSelectedYears(startDate, endDate);
		for(String country:countries)
		{
			List<String> exportTerminals=contractsDao.getExportCountryTerminals(country,selectedExportTerminals);//selectedOptions,startDate,endDate);
			Map<Integer,Float> yearMap=null;
			if(exportTerminals.size()>0)
			{
				yearMap=new HashMap<Integer, Float>();
				for(Integer year:years)
				{
					float soq=0;
					for(String terminal:exportTerminals)
					{
						for(Contracts contracts:contractsList)
						{
							if(terminal.equals(contracts.getExportTerminal()) && year==contracts.getYear() && country.equalsIgnoreCase(contracts.getExportCountry()))
								soq=soq+contracts.getContractedQuantity();
						}
					}
					yearMap.put(year, soq);
				}
				countriesQuantity.put(country, yearMap);
			}
			
		}
		return countriesQuantity;
	}
	@Override
	public Map<String, Map<Integer, Float>> getQuantityByTerminal(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Float>> terminalsQuantity=new HashMap<String, Map<Integer,Float>>();
		List<String> selectedTerminals=contractsDao.getSelectedExportTerminals(selectedOptions, startDate, endDate);
		if(selectedTerminals.size()>0)
			terminalsQuantity=calculateTerminalsQuantity(selectedTerminals,selectedOptions,startDate,endDate);
		return terminalsQuantity;
	}
	private Map<String,Map<Integer,Float>> calculateTerminalsQuantity(List<String> terminals,Map<String,List<String>> selectedOptions,int startDate,int endDate)
	{
		Map<String,Map<Integer,Float>> terminalsQuantity=new HashMap<String, Map<Integer,Float>>();
		Map<String,List<String>> selectedOptionsWithoutCompanies=getSelectedOptionsWithoutCompanies(selectedOptions);
		List<Contracts> contractsList=contractsDao.getContractsCriteriaData(selectedOptionsWithoutCompanies,terminals,startDate, endDate);
		List<Integer> years=getSelectedYears(startDate, endDate);
		Map<Integer,Float> yearMap=null;
		for(String terminal:terminals)
		{
			yearMap=new HashMap<Integer, Float>();
			for(Integer year:years)
			{
				float soq=0;
				for(Contracts contracts:contractsList)
				{
					if(terminal.equals(contracts.getExportTerminal()) && year==contracts.getYear())
						soq=soq+contracts.getContractedQuantity();
				}
				yearMap.put(year, soq);
			}
			terminalsQuantity.put(terminal,yearMap);
		}
		return terminalsQuantity;
	}
	@Override
	public Map<String, Map<Integer, Float>> getQuantitiesForRecord(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate, String displayType, String recordName) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Float>> quantitiesForRecord=new HashMap<String, Map<Integer,Float>>();
		if(null!=displayType && "company".equalsIgnoreCase(displayType))
			quantitiesForRecord=getTerminalQuantitiesForCompany(recordName,selectedOptions,startDate,endDate);
		else if(null!=displayType && "country".equalsIgnoreCase(displayType))
			quantitiesForRecord=getTerminalQuantitiesForCountry(recordName,selectedOptions,startDate,endDate);
		return quantitiesForRecord;
	}
	private Map<String,Map<Integer,Float>> getTerminalQuantitiesForCompany(String recordName,Map<String,List<String>>selectedOptions,int startDate,int endDate)
	{
		Map<String,Map<Integer,Float>> exportTerminalsQuanitity=new HashMap<String, Map<Integer,Float>>();
		List<String> selectedExportTerminals=contractsDao.getSelectedExportTerminals(selectedOptions, startDate, endDate);
		List<String> companyExportTerminals=contractsDao.getExportCompanyTerminals(recordName,selectedExportTerminals);
		if(companyExportTerminals.size()>0)
		exportTerminalsQuanitity=calculateTerminalsQuantity(companyExportTerminals, selectedOptions, startDate, endDate);
		return exportTerminalsQuanitity;
	}
	private Map<String,Map<Integer,Float>> getTerminalQuantitiesForCountry(String recordName,Map<String,List<String>>selectedOptions,int startDate,int endDate)
	{
		Map<String,Map<Integer,Float>> exportTerminalsQuanitity=new HashMap<String, Map<Integer,Float>>();
		List<String> selectedExportTerminals=contractsDao.getSelectedExportTerminals(selectedOptions, startDate, endDate);
		List<String> countryExportTerminals=contractsDao.getExportCountryTerminals(recordName,selectedExportTerminals);// selectedOptions, startDate, endDate);
		if(countryExportTerminals.size()>0)
		exportTerminalsQuanitity=calculateTerminalsQuantity(countryExportTerminals, selectedOptions, startDate, endDate);
		return exportTerminalsQuanitity;
	}
	@Override
	public List<String> getImportCountries(List<String> exportCountries) {
		// TODO Auto-generated method stub
		return contractsDao.getImportCountries(exportCountries);
	}

	@Override
	public List<String> getImportCompanies(List<String> exportCompanies) {
		// TODO Auto-generated method stub
		return contractsDao.getImportCompanies(exportCompanies);
	}
	private List<Integer> getSelectedYears(int startDate,int endDate)
	{
		List<Integer> years=new ArrayList<Integer>();
		for(int i=startDate;i<=endDate;i++)
				years.add(i);
			
		return years;
	}
	private Map<String,List<String>> getSelectedOptionsWithoutCompanies(Map<String,List<String>> selectedOptions)
	{
		Map<String,List<String>> modifiedOptions=new HashMap<String, List<String>>();
		Set<String> keys=selectedOptions.keySet();
		for(String key:keys)
		{
			if(!"importCompanies".equalsIgnoreCase(key) && !"exportCompanies".equalsIgnoreCase(key))
				modifiedOptions.put(key,selectedOptions.get(key));
		}
		return modifiedOptions;
	}
	public ContractsDao getContractsDao() {
		return contractsDao;
	}
	public void setContractsDao(ContractsDao contractsDao) {
		this.contractsDao = contractsDao;
	}
	
	

	
	
}
