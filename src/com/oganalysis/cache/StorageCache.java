package com.oganalysis.cache;

import static com.oganalysis.constants.ApplicationConstants.UNDERSCORE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.business.StorageInfraBusinessService;
import com.oganalysis.dao.StorageDao;
import com.oganalysis.entities.Storage;
import com.oganalysis.entities.StorageFilter;

public class StorageCache {
	
	private StorageDao storageDao;
	private StorageInfraBusinessService storageInfraBusinessServiceImpl;
	private Map<String,Double> terminalsYearCapacity;
	private Map<String,List<String>> companyTerminals;
	private Map<String,List<String>> countryTerminals;
	private Map<String,Double> companyStakeForTerminal;
	private Map<String,Map<String,String>> infrastructure;

	private int startYear;
	private int endYear;
//	public void initCache()
//	{
//		System.out.println("RefineryCache Init initialized");
//		terminalsYearCapacity=createTerminalsYearCapacity();
//		companyTerminals=createCompanyTerminals();
//		companyStakeForTerminal=createCompanyStakeForTerminal();
//		countryTerminals=createCountryTerminals();	
//		infrastructure=createInfrastructure();
//	}
	
	public Map<String,Double> createTerminalsYearCapacity()
	{
		Map<String,Double> terminalsYearCapacityMap=new HashMap<String, Double>();
		for(int year=startYear;year<=endYear;year++)
		{	
			List<Storage> storageList=storageDao.getStorage(year);
			for(Storage storage:storageList)
			{
				if(!terminalsYearCapacityMap.containsKey(storage.getTankFarm().toLowerCase()+year));
				terminalsYearCapacityMap.put(storage.getTankFarm().toLowerCase()+year, storage.getCapacityM3());
			}
		}	
		return terminalsYearCapacityMap;
	}
	public Map<String,Double> createCompanyStakeForTerminal()
	{
		List<StorageFilter> storageFilterList=storageDao.getStorageFilter();
		Map<String,Double> companyStakeForTerminal=new HashMap<String, Double>();
		for(StorageFilter storageFilter:storageFilterList)
		{
			String companyName=storageFilter.getCurrentOwners();
			String terminalName=storageFilter.getTankFarm();
			String key=companyName.toLowerCase()+UNDERSCORE+terminalName.toLowerCase();
			companyStakeForTerminal.put(key, storageFilter.getCurrentOwnership());
		}
		return companyStakeForTerminal;
	}
	public Map<String,List<String>> createCompanyTerminals()
	{
		Map<String,List<String>> companyTerminalsMap=new HashMap<String, List<String>>();
		List<String> companies=storageDao.getCompanies();
		for(String company:companies)
		{
			List<String> terminals=storageDao.getCompanyTerminals(company);
			companyTerminalsMap.put(company.toLowerCase(),terminals);
		}
		return companyTerminalsMap;
	}
	public Map<String,List<String>> createCountryTerminals()
	{
		Map<String,List<String>> countryTerminalsMap=new HashMap<String, List<String>>();
		List<String> countries=storageDao.getCountries(new HashMap<String,List<String>>());
		for(String country:countries)
		{
			List<String> terminals=storageDao.getCountryTerminals(country);
			countryTerminalsMap.put(country.toLowerCase(),terminals);
		}
		return countryTerminalsMap;
	}
	public Map<String,Map<String,String>> createInfrastructure()
	{		
		List<String> terminals=storageDao.getTerminals();		
		Map<String,Map<String,String>> infrastructureMap=storageInfraBusinessServiceImpl.createInfrastructure(terminals);		
		return infrastructureMap;
	}
	public void setStorageDao(StorageDao storageDao) {
		this.storageDao = storageDao;
	}
	public Map<String, Double> getTerminalsYearCapacity() {
		return terminalsYearCapacity;
	}
	public void setTerminalsYearCapacity(Map<String, Double> terminalsYearCapacity) {
		this.terminalsYearCapacity = terminalsYearCapacity;
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
	public Map<String, Map<String, String>> getInfrastructure() {
		return infrastructure;
	}
	public void setInfrastructure(Map<String, Map<String, String>> infrastructure) {
		this.infrastructure = infrastructure;
	}
	public void setStorageInfraBusinessServiceImpl(
			StorageInfraBusinessService storageInfraBusinessServiceImpl) {
		this.storageInfraBusinessServiceImpl = storageInfraBusinessServiceImpl;
	}
	public Map<String, Double> getCompanyStakeForTerminal() {
		return companyStakeForTerminal;
	}
	public void setCompanyStakeForTerminal(
			Map<String, Double> companyStakeForTerminal) {
		this.companyStakeForTerminal = companyStakeForTerminal;
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
	
}
