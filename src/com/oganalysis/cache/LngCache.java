package com.oganalysis.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.constants.ApplicationConstants;
import com.oganalysis.dao.LngDao;
import com.oganalysis.entities.Lng;
import com.oganalysis.entities.LngFilter;

public class LngCache {
	private LngDao lngDao;
	private Map<String,Double> liqueTerminalsYearCapacity;
	private Map<String,Double> regasTerminalsYearCapacity;
		
	private Map<String,List<String>> liqueCompanyTerminals;
	private Map<String,List<String>> regasCompanyTerminals;
	private Map<String,Double> liqueCompanyStakeForTerminal;
	private Map<String,Double> regasCompanyStakeForTerminal;
	
	private Map<String,List<String>> liqueCountryTerminals;
	private Map<String,List<String>> regasCountryTerminals;
	
	
	private int startYear;//=2000;
	private int endYear;//=2022;
	public void initCache()
	{
		System.out.println("LngCache Init initialized");
		liqueTerminalsYearCapacity=createTerminalsYearCapacity(ApplicationConstants.LNG_LIQUEFACTION);
		regasTerminalsYearCapacity=createTerminalsYearCapacity(ApplicationConstants.LNG_REGASIFICATION);
		
		liqueCompanyStakeForTerminal=createCompanyStakeForTerminal(ApplicationConstants.LNG_LIQUEFACTION);
		regasCompanyStakeForTerminal=createCompanyStakeForTerminal(ApplicationConstants.LNG_REGASIFICATION);
		liqueCompanyTerminals=createCompanyTerminals(ApplicationConstants.LNG_LIQUEFACTION);
		regasCompanyTerminals=createCompanyTerminals(ApplicationConstants.LNG_REGASIFICATION);
		
		liqueCountryTerminals=createCountryTerminals(ApplicationConstants.LNG_LIQUEFACTION);
		regasCountryTerminals=createCountryTerminals(ApplicationConstants.LNG_REGASIFICATION);
	}
	public Map<String,Double> createTerminalsYearCapacity(String type)
	{
		Map<String,Double> terminalsYearCapacityMap=new HashMap<String, Double>();
		for(int year=startYear;year<=endYear;year++)
		{	
			List<Lng> lngList=lngDao.getLng(year,type);
			for(Lng lng:lngList)
			{
				if(!terminalsYearCapacityMap.containsKey(lng.getName().toLowerCase()+year));
				terminalsYearCapacityMap.put(lng.getName().toLowerCase()+year, lng.getCapacity());
			}
		}	
		return terminalsYearCapacityMap;
	}
	public Map<String,List<String>> createCountryTerminals(String type)
	{
		Map<String,List<String>> countryTerminalsMap=new HashMap<String, List<String>>();
		List<String> countries=lngDao.getCountries(type);
		for(String country:countries)
		{
			List<String> terminals=lngDao.getCountryTerminals(country);
			countryTerminalsMap.put(country.toLowerCase(),terminals);
		}
		return countryTerminalsMap;
	}
	public Map<String,List<String>> createCompanyTerminals(String type)
	{
		Map<String,List<String>> companyTerminalsMap=new HashMap<String, List<String>>();
		List<String> companies=lngDao.getCompanies(type);
		for(String company:companies)
		{
			List<String> terminals=lngDao.getCompanyTerminals(company);
			companyTerminalsMap.put(company.toLowerCase(),terminals);
		}
		return companyTerminalsMap;
	}
	public Map<String,Double> createCompanyStakeForTerminal(String type)
	{
		List<LngFilter> lngFilterList=lngDao.getLngFilter(type);
		Map<String,Double> companyStakeForTerminal=new HashMap<String, Double>();
		for(LngFilter lngFilter:lngFilterList)
		{
			String companyName=lngFilter.getEquityPartners();
			String terminalName=lngFilter.getName();
			String key=companyName.toLowerCase()+ApplicationConstants.UNDERSCORE+terminalName.toLowerCase();
			companyStakeForTerminal.put(key, lngFilter.getEquityStakes());
		}
		return companyStakeForTerminal;
	}
	public LngDao getLngDao() {
		return lngDao;
	}
	public void setLngDao(LngDao lngDao) {
		this.lngDao = lngDao;
	}
	public Map<String, Double> getLiqueTerminalsYearCapacity() {
		return liqueTerminalsYearCapacity;
	}
	public void setLiqueTerminalsYearCapacity(
			Map<String, Double> liqueTerminalsYearCapacity) {
		this.liqueTerminalsYearCapacity = liqueTerminalsYearCapacity;
	}
	public Map<String, Double> getRegasTerminalsYearCapacity() {
		return regasTerminalsYearCapacity;
	}
	public void setRegasTerminalsYearCapacity(
			Map<String, Double> regasTerminalsYearCapacity) {
		this.regasTerminalsYearCapacity = regasTerminalsYearCapacity;
	}
	public Map<String, List<String>> getLiqueCompanyTerminals() {
		return liqueCompanyTerminals;
	}
	public void setLiqueCompanyTerminals(
			Map<String, List<String>> liqueCompanyTerminals) {
		this.liqueCompanyTerminals = liqueCompanyTerminals;
	}
	public Map<String, List<String>> getRegasCompanyTerminals() {
		return regasCompanyTerminals;
	}
	public void setRegasCompanyTerminals(
			Map<String, List<String>> regasCompanyTerminals) {
		this.regasCompanyTerminals = regasCompanyTerminals;
	}
	public Map<String, Double> getLiqueCompanyStakeForTerminal() {
		return liqueCompanyStakeForTerminal;
	}
	public void setLiqueCompanyStakeForTerminal(
			Map<String, Double> liqueCompanyStakeForTerminal) {
		this.liqueCompanyStakeForTerminal = liqueCompanyStakeForTerminal;
	}
	public Map<String, Double> getRegasCompanyStakeForTerminal() {
		return regasCompanyStakeForTerminal;
	}
	public void setRegasCompanyStakeForTerminal(
			Map<String, Double> regasCompanyStakeForTerminal) {
		this.regasCompanyStakeForTerminal = regasCompanyStakeForTerminal;
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
	public Map<String, List<String>> getLiqueCountryTerminals() {
		return liqueCountryTerminals;
	}
	public void setLiqueCountryTerminals(
			Map<String, List<String>> liqueCountryTerminals) {
		this.liqueCountryTerminals = liqueCountryTerminals;
	}
	public Map<String, List<String>> getRegasCountryTerminals() {
		return regasCountryTerminals;
	}
	public void setRegasCountryTerminals(
			Map<String, List<String>> regasCountryTerminals) {
		this.regasCountryTerminals = regasCountryTerminals;
	}
	
	
}
