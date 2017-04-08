package com.oganalysis.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.business.ContractsBusinessService;
import com.oganalysis.cache.ContractsCache;
import com.oganalysis.dao.ContractsDao;

public class ContractsBusinessServiceImpl implements ContractsBusinessService {

	private ContractsDao contractsDao;
	private ContractsCache contractsCache;
	@Override
	public Map<String, Map<Integer, Double>> getQuantityByCompany(
			Map<String, List<String>> selectedOptions,int startDate,int endDate) {
		// TODO Auto-generated method stub		
		Map<String,Map<Integer,Double>> companiesQuantity=new HashMap<String, Map<Integer,Double>>();
		List<String> selectedExportCompanies=contractsDao.getSelectedExportCompanies(selectedOptions,startDate,endDate);
		if(selectedExportCompanies.size()>0)
			companiesQuantity=calculateCompaniesQuantity(selectedExportCompanies,selectedOptions,startDate,endDate);
		return companiesQuantity;
	}
	private Map<String,Map<Integer,Double>> calculateCompaniesQuantity(List<String> companies,Map<String,List<String>> selectedOptions,int startDate,int endDate)
	{
		Map<String,Map<Integer,Double>> companiesQuantity=new HashMap<String, Map<Integer,Double>>();		
		List<String> selectedContractIndicators=contractsDao.getSelectedContractIndicators(selectedOptions, startDate, endDate);		
		List<Integer> years=getSelectedYears(startDate, endDate);
		Map<String,Double> contractIndicatorsYearQuantity=null;
		if(null==contractsCache.getContractIndicatorsYearQuantity())
		{
			contractIndicatorsYearQuantity=contractsCache.createContractIndicatorsYearQuantity();
			contractsCache.setContractIndicatorsYearQuantity(contractIndicatorsYearQuantity);
		}
		else
			contractIndicatorsYearQuantity=contractsCache.getContractIndicatorsYearQuantity();
		for(String company:companies)
		{
			List<String> companyContractIndicators=getExportCompanyContractIndicators(company,selectedContractIndicators);
			Map<Integer,Double> yearMap=null;
			if(companyContractIndicators.size()>0)
			{
				yearMap=new HashMap<Integer, Double>();
				for(Integer year:years)
				{
					double soq=0;
					for(String contractIndicators:companyContractIndicators)
					{
						double contractedQuantity=contractIndicatorsYearQuantity.get(contractIndicators.toLowerCase()+year)==null?0:contractIndicatorsYearQuantity.get(contractIndicators.toLowerCase()+year);
						soq=soq+contractedQuantity;						
					}
					soq=round(soq,2);
					yearMap.put(year, soq);
				}
				companiesQuantity.put(company, yearMap);
			}
			
		}
		return companiesQuantity;
	}
	@Override
	public Map<String, Map<Integer, Double>> getQuantityByCountry(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> countriesQuantity=new HashMap<String, Map<Integer,Double>>();
		List<String> exportCountries=contractsDao.getSelectedExportCountries(selectedOptions, startDate, endDate);
		if(exportCountries.size()>0)
			countriesQuantity=calculateCountriesQuantity(exportCountries,selectedOptions,startDate,endDate);
		return countriesQuantity;
	}
	private Map<String,Map<Integer,Double>> calculateCountriesQuantity(List<String> countries,Map<String,List<String>> selectedOptions,int startDate,int endDate)
	{
		Map<String,Map<Integer,Double>> countriesQuantity=new HashMap<String, Map<Integer,Double>>();		
		List<String> selectedContractIndicators=contractsDao.getSelectedContractIndicators(selectedOptions, startDate, endDate);
		Map<String,Double> contractIndicatorsYearQuantity=null;
		if(null==contractsCache.getContractIndicatorsYearQuantity())
		{
			contractIndicatorsYearQuantity=contractsCache.createContractIndicatorsYearQuantity();
			contractsCache.setContractIndicatorsYearQuantity(contractIndicatorsYearQuantity);
		}
		else
			contractIndicatorsYearQuantity=contractsCache.getContractIndicatorsYearQuantity();		
		List<Integer> years=getSelectedYears(startDate, endDate);
		for(String country:countries)
		{
			List<String> countryContractIndicators=getExportCountryContractIndicators(country,selectedContractIndicators);
			Map<Integer,Double> yearMap=null;
			if(countryContractIndicators.size()>0)
			{
				yearMap=new HashMap<Integer, Double>();
				for(Integer year:years)
				{
					double soq=0;
					for(String contractIndicator:countryContractIndicators)
					{
						double contractedQuantity=contractIndicatorsYearQuantity.get(contractIndicator.toLowerCase()+year)==null?0:contractIndicatorsYearQuantity.get(contractIndicator.toLowerCase()+year);
						soq=soq+contractedQuantity;
					}
					
					soq=round(soq,2);
					yearMap.put(year, soq);
				}
				countriesQuantity.put(country, yearMap);
			}
			
		}
		return countriesQuantity;
	}
	@Override
	public Map<String, Map<Integer, Double>> getQuantityByTerminal(
			Map<String, List<String>> selectedOptions, int startDate,
			int endDate) {
		// TODO Auto-generated method stub
		Map<String,Map<Integer,Double>> terminalsQuantity=new HashMap<String, Map<Integer,Double>>();
		List<String> selectedTerminals=contractsDao.getSelectedExportTerminals(selectedOptions, startDate, endDate);
		if(selectedTerminals.size()>0)
			terminalsQuantity=calculateTerminalsQuantity(selectedTerminals,selectedOptions,startDate,endDate);
		return terminalsQuantity;
	}
	private Map<String,Map<Integer,Double>> calculateTerminalsQuantity(List<String> terminals,Map<String,List<String>> selectedOptions,int startDate,int endDate)
	{
		Map<String,Map<Integer,Double>> terminalsQuantity=new HashMap<String, Map<Integer,Double>>();		
		List<String> selectedContractIndicators=contractsDao.getSelectedContractIndicators(selectedOptions, startDate, endDate);		
		Map<String,Double> contractIndicatorsYearQuantity=null;
		if(null==contractsCache.getContractIndicatorsYearQuantity())
		{
			contractIndicatorsYearQuantity=contractsCache.createContractIndicatorsYearQuantity();
			contractsCache.setContractIndicatorsYearQuantity(contractIndicatorsYearQuantity);
		}
		else
			contractIndicatorsYearQuantity=contractsCache.getContractIndicatorsYearQuantity();
		List<Integer> years=getSelectedYears(startDate, endDate);
		Map<Integer,Double> yearMap=null;
		for(String terminal:terminals)
		{
			List<String> terminalContractIndicators=getExportTerminalContractIndicators(terminal,selectedContractIndicators);
			if(terminalContractIndicators.size()>0)
			{
				yearMap=new HashMap<Integer, Double>();
				for(Integer year:years)
				{
					double soq=0;
					for(String contractIndicator:terminalContractIndicators)
					{
						double contractedQuantity=contractIndicatorsYearQuantity.get(contractIndicator.toLowerCase()+year)==null?0:contractIndicatorsYearQuantity.get(contractIndicator.toLowerCase()+year);
						soq=soq+contractedQuantity;
						
					}
					soq=round(soq,2);
					yearMap.put(year, soq);
				}
				terminalsQuantity.put(terminal,yearMap);
			}
			
		}
		return terminalsQuantity;
	}
//	@Override
//	public Map<String, Map<Integer, Double>> getQuantitiesForRecord(
//			Map<String, List<String>> selectedOptions, int startDate,
//			int endDate, String displayType, String recordName) {
//		// TODO Auto-generated method stub
//		Map<String,Map<Integer,Double>> quantitiesForRecord=new HashMap<String, Map<Integer,Double>>();
//		if(null!=displayType && "company".equalsIgnoreCase(displayType))
//			quantitiesForRecord=getTerminalQuantitiesForCompany(recordName,selectedOptions,startDate,endDate);
//		else if(null!=displayType && "country".equalsIgnoreCase(displayType))
//			quantitiesForRecord=getTerminalQuantitiesForCountry(recordName,selectedOptions,startDate,endDate);
//		return quantitiesForRecord;
//	}
//	private Map<String,Map<Integer,Double>> getTerminalQuantitiesForCompany(String recordName,Map<String,List<String>>selectedOptions,int startDate,int endDate)
//	{
//		Map<String,Map<Integer,Double>> exportTerminalsQuanitity=new HashMap<String, Map<Integer,Double>>();
////		List<String> selectedExportTerminals=contractsDao.getSelectedExportTerminals(selectedOptions, startDate, endDate);
////		List<String> companyExportTerminals=contractsDao.getExportCompanyTerminals(recordName,selectedExportTerminals);
////		if(companyExportTerminals.size()>0)
////		exportTerminalsQuanitity=calculateTerminalsQuantity(companyExportTerminals, selectedOptions, startDate, endDate);
//		return exportTerminalsQuanitity;
//	}
//	private Map<String,Map<Integer,Double>> getTerminalQuantitiesForCountry(String recordName,Map<String,List<String>>selectedOptions,int startDate,int endDate)
//	{
//		Map<String,Map<Integer,Double>> exportTerminalsQuanitity=new HashMap<String, Map<Integer,Double>>();
////		List<String> selectedExportTerminals=contractsDao.getSelectedExportTerminals(selectedOptions, startDate, endDate);
////		List<String> countryExportTerminals=contractsDao.getExportCountryTerminals(recordName,selectedExportTerminals);// selectedOptions, startDate, endDate);
////		if(countryExportTerminals.size()>0)
////		exportTerminalsQuanitity=calculateTerminalsQuantity(countryExportTerminals, selectedOptions, startDate, endDate);
//		return exportTerminalsQuanitity;
//	}
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
	public List<String> getExportCompanies()
	{
		return contractsDao.getExportCompanies();
	}
	private List<Integer> getSelectedYears(int startDate,int endDate)
	{
		List<Integer> years=new ArrayList<Integer>();
		for(int i=startDate;i<=endDate;i++)
				years.add(i);
			
		return years;
	}
	private List<String> getExportCompanyContractIndicators(String company,List<String> selectedContractIndicators)
	{
		List<String> companyContractIndicators=new ArrayList<String>();
		List<String> contractIndicatorsList=null;
		if(null==contractsCache.getCompanyContractIndicators())
		{
			Map<String,List<String>> companyContractIndicatorsMap=contractsCache.createCompanyContractIndicators();
			contractsCache.setCompanyContractIndicators(companyContractIndicatorsMap);
			contractIndicatorsList=companyContractIndicatorsMap.get(company.toLowerCase());
		}
		else
			contractIndicatorsList=contractsCache.getCompanyContractIndicators().get(company.toLowerCase());
		for(String contractIndicators:selectedContractIndicators)
		{
			if(contractIndicatorsList.contains(contractIndicators))
				companyContractIndicators.add(contractIndicators);
		}
		return companyContractIndicators;
	}
	private List<String> getExportCountryContractIndicators(String country,List<String> selectedContractIndicators)
	{
		List<String> countryContractIndicators=new ArrayList<String>();
		List<String> contractIndicatorsList=null;
		if(null==contractsCache.getCountryContractIndicators())
		{
			Map<String,List<String>> countryContractIndicatorsMap=contractsCache.createCountryContractIndicators();
			contractsCache.setCountryContractIndicators(countryContractIndicatorsMap);
			contractIndicatorsList=countryContractIndicatorsMap.get(country.toLowerCase());
		}
		else
			contractIndicatorsList=contractsCache.getCountryContractIndicators().get(country.toLowerCase());
		for(String contractIndicators:selectedContractIndicators)
		{
			if(contractIndicatorsList.contains(contractIndicators))
				countryContractIndicators.add(contractIndicators);
		}
		return countryContractIndicators;
	}
	private List<String> getExportTerminalContractIndicators(String terminal,List<String> selectedContractIndicators)
	{
		List<String> terminalContractIndicators=new ArrayList<String>();
		List<String> contractIndicatorsList=null;
		if(null==contractsCache.getTerminalContractIndicators())
		{
			Map<String,List<String>> terminalContractIndicatorsMap=contractsCache.createTerminalContractIndicators();
			contractsCache.setTerminalContractIndicators(terminalContractIndicatorsMap);
			contractIndicatorsList=terminalContractIndicatorsMap.get(terminal.toLowerCase());
		}
		else
			contractIndicatorsList=contractsCache.getTerminalContractIndicators().get(terminal.toLowerCase());
		for(String contractIndicators:selectedContractIndicators)
		{
			if(contractIndicatorsList.contains(contractIndicators))
				terminalContractIndicators.add(contractIndicators);
		}
		return terminalContractIndicators;
	}
	private double round(double value, int places) {	    

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}	
	public ContractsDao getContractsDao() {
		return contractsDao;
	}
	public void setContractsDao(ContractsDao contractsDao) {
		this.contractsDao = contractsDao;
	}
	public ContractsCache getContractsCache() {
		return contractsCache;
	}
	public void setContractsCache(ContractsCache contractsCache) {
		this.contractsCache = contractsCache;
	}
	
	

	
	
}
