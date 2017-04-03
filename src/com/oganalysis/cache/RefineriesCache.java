package com.oganalysis.cache;

import static com.oganalysis.constants.ApplicationConstants.UNDERSCORE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.dao.RefineriesDao;
import com.oganalysis.entities.RefineriesFilter;
import com.oganalysis.entities.Refinery;

public class RefineriesCache {
	private RefineriesDao refineriesDao;
	private Map<String,Double> terminalsYearCapacity;
	private Map<String,List<String>> companyTerminals;
	private Map<String,List<String>> countryTerminals;
	private Map<String,Double> companyStakeForTerminal;
	private List<String> terminals;
	private List<String> companies;
	private List<String> countries;
	
	private int startYear;//=2000;
	private int endYear;//=2022;
	public void initCache()
	{
		System.out.println("RefineryCache Init initialized");
		terminalsYearCapacity=createTerminalsYearCapacity();
		companyTerminals=createCompanyTerminals();
		companyStakeForTerminal=createCompanyStakeForTerminal();
		countryTerminals=createCountryTerminals();
		companies=createCompaniesList();
		terminals=createTerminalsList();
		countries=createCountriesList();
	}
	public List<String> createCountriesList()
	{
		List<String> countriesList=refineriesDao.getCountries();
		return countriesList;
	}
	public List<String> createTerminalsList()
	{
		List<String> terminalsList=refineriesDao.getTerminals();
		return terminalsList;
	}
	public List<String> createCompaniesList()
	{
		List<String> countriesList=refineriesDao.getCompanies();
		return countriesList;
	}
	public Map<String,Double> createTerminalsYearCapacity()
	{
		Map<String,Double> terminalsYearCapacityMap=new HashMap<String, Double>();
		for(int year=startYear;year<=endYear;year++)
		{	
			List<Refinery> refineries=refineriesDao.getRefineries(year);
			for(Refinery refinery:refineries)
			{
				if(!terminalsYearCapacityMap.containsKey(refinery.getName().toLowerCase()+year));
				terminalsYearCapacityMap.put(refinery.getName().toLowerCase()+year, refinery.getRefiningCapacity());
			}
		}	
		return terminalsYearCapacityMap;
	}
	public Map<String,List<String>> createCompanyTerminals()
	{
		Map<String,List<String>> companyTerminalsMap=new HashMap<String, List<String>>();
		List<String> companies=refineriesDao.getCompanies();
		for(String company:companies)
		{
			List<String> terminals=refineriesDao.getCompanyTerminals(company);
			companyTerminalsMap.put(company.toLowerCase(),terminals);
		}
		return companyTerminalsMap;
	}
	public Map<String,List<String>> createCountryTerminals()
	{
		Map<String,List<String>> countryTerminalsMap=new HashMap<String, List<String>>();
		List<String> countries=refineriesDao.getCountries();
		for(String country:countries)
		{
			List<String> terminals=refineriesDao.getCountryTerminals(country);
			countryTerminalsMap.put(country.toLowerCase(),terminals);
		}
		return countryTerminalsMap;
	}
	public Map<String,Double> createCompanyStakeForTerminal()
	{
		List<RefineriesFilter> refineriesFilterList=refineriesDao.getRefineriesFilter();
		Map<String,Double> companyStakeForTerminal=new HashMap<String, Double>();
		for(RefineriesFilter refineriesFilter:refineriesFilterList)
		{
			String companyName=refineriesFilter.getCurrentEquityPartners();
			String terminalName=refineriesFilter.getName();
			String key=companyName.toLowerCase()+UNDERSCORE+terminalName.toLowerCase();
			companyStakeForTerminal.put(key, refineriesFilter.getCurrentEquityStakes());
		}
		return companyStakeForTerminal;
	}
	public void resetCache()
	{
		 terminalsYearCapacity=null;
		 companyTerminals=null;
		 companyStakeForTerminal=null;
	}
	
	public List<String> getCompanies() {
		return companies;
	}
	public void setCompanies(List<String> companies) {
		this.companies = companies;
	}
	public Map<String, Double> getCompanyStakeForTerminal() {
		return companyStakeForTerminal;
	}
	public void setCompanyStakeForTerminal(
			Map<String, Double> companyStakeForTerminal) {
		this.companyStakeForTerminal = companyStakeForTerminal;
	}
	public Map<String, Double> getTerminalsYearCapacity() {
		return terminalsYearCapacity;
	}

	public void setTerminalsYearCapacity(Map<String, Double> terminalsYearCapacity) {
		this.terminalsYearCapacity = terminalsYearCapacity;
	}

	public RefineriesDao getRefineriesDao() {
		return refineriesDao;
	}

	public void setRefineriesDao(RefineriesDao refineriesDao) {
		this.refineriesDao = refineriesDao;
	}
	public Map<String, List<String>> getCompanyTerminals() {
		return companyTerminals;
	}
	public void setCompanyTerminals(Map<String, List<String>> companyTerminals) {
		this.companyTerminals = companyTerminals;
	}
	public Map<String, List<String>> getCountryTerminals() {
		return countryTerminals;
	}
	public void setCountryTerminals(Map<String, List<String>> countryTerminals) {
		this.countryTerminals = countryTerminals;
	}
	public List<String> getTerminals() {
		return terminals;
	}
	public void setTerminals(List<String> terminals) {
		this.terminals = terminals;
	}
	public List<String> getCountries() {
		return countries;
	}
	public void setCountries(List<String> countries) {
		this.countries = countries;
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
	
	
}
