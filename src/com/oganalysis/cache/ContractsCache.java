package com.oganalysis.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.dao.ContractsDao;
import com.oganalysis.entities.Contracts;

public class ContractsCache {
	private ContractsDao contractsDao;
	private Map<String,Double> contractIndicatorsYearQuantity;
	private Map<String,List<String>> companyContractIndicators;
	private Map<String,List<String>> countryContractIndicators;
	private Map<String,List<String>> terminalContractIndicators;	

	private int startYear;
	private int endYear;
	public void initCache()
	{
		System.out.println("ContractsCache Init initialized");
		contractIndicatorsYearQuantity=createContractIndicatorsYearQuantity();
		companyContractIndicators=createCompanyContractIndicators();
		terminalContractIndicators=createTerminalContractIndicators();		
		countryContractIndicators=createCountryContractIndicators();		
	}
	public Map<String,Double> createContractIndicatorsYearQuantity()
	{
		Map<String,Double> contractIndicatorsYearCapacityMap=new HashMap<String, Double>();
		for(int year=startYear;year<=endYear;year++)
		{	
			List<Contracts> contracts=contractsDao.getContractIndicators(year);
			for(Contracts contract:contracts)
			{
				if(!contractIndicatorsYearCapacityMap.containsKey(contract.getContractIndicator().toLowerCase()+year));
				contractIndicatorsYearCapacityMap.put(contract.getContractIndicator().toLowerCase()+year,contract.getContractedQuantity());
			}
		}	
		return contractIndicatorsYearCapacityMap;
	}
	public Map<String,List<String>> createTerminalContractIndicators()
	{
		Map<String,List<String>> terminalContractIndicatorsMap=new HashMap<String, List<String>>();
		List<String> terminals=contractsDao.getExportTerminals();
		for(String terminal:terminals)
		{
			List<String> contractIndicators=contractsDao.getTerminalContractIndicators(terminal);
			terminalContractIndicatorsMap.put(terminal.toLowerCase(),contractIndicators);
		}
		return terminalContractIndicatorsMap;
	}
	public Map<String,List<String>> createCompanyContractIndicators()
	{
		Map<String,List<String>> companyContractIndicatorsMap=new HashMap<String, List<String>>();
		List<String> companies=contractsDao.getExportCompanies();
		for(String company:companies)
		{
			List<String> contractIndicators=contractsDao.getCompanyContractIndicators(company);
			companyContractIndicatorsMap.put(company.toLowerCase(),contractIndicators);
		}
		return companyContractIndicatorsMap;
	}
	public Map<String,List<String>> createCountryContractIndicators()
	{
		Map<String,List<String>> countryContractIndicatorsMap=new HashMap<String, List<String>>();
		List<String> countries=contractsDao.getExportCountries();
		for(String country:countries)
		{
			List<String> contractIndicators=contractsDao.getCountryContractIndicators(country);
			countryContractIndicatorsMap.put(country.toLowerCase(),contractIndicators);
		}
		return countryContractIndicatorsMap;
	}
	public ContractsDao getContractsDao() {
		return contractsDao;
	}
	public void setContractsDao(ContractsDao contractsDao) {
		this.contractsDao = contractsDao;
	}
	public Map<String, List<String>> getCompanyContractIndicators() {
		return companyContractIndicators;
	}
	public void setCompanyContractIndicators(
			Map<String, List<String>> companyContractIndicators) {
		this.companyContractIndicators = companyContractIndicators;
	}
	public int getStartYear() {
		return startYear;
	}
	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}
	public int getEndYear() {
		return endYear;
	}
	public void setEndYear(int endYear) {
		this.endYear = endYear;
	}
	public Map<String, Double> getContractIndicatorsYearQuantity() {
		return contractIndicatorsYearQuantity;
	}
	public void setContractIndicatorsYearQuantity(
			Map<String, Double> contractIndicatorsYearQuantity) {
		this.contractIndicatorsYearQuantity = contractIndicatorsYearQuantity;
	}
	public Map<String, List<String>> getCountryContractIndicators() {
		return countryContractIndicators;
	}
	public void setCountryContractIndicators(
			Map<String, List<String>> countryContractIndicators) {
		this.countryContractIndicators = countryContractIndicators;
	}
	public Map<String, List<String>> getTerminalContractIndicators() {
		return terminalContractIndicators;
	}
	public void setTerminalContractIndicators(
			Map<String, List<String>> terminalContractIndicators) {
		this.terminalContractIndicators = terminalContractIndicators;
	}
	
}
