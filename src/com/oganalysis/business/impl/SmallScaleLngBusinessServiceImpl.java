package com.oganalysis.business.impl;

import static com.oganalysis.constants.ApplicationConstants.COUNTRY;
import static com.oganalysis.constants.ApplicationConstants.TERMINAL;
import static com.oganalysis.constants.ApplicationConstants.TYPE;
import static com.oganalysis.constants.ApplicationConstants.STATUS;
import static com.oganalysis.constants.ApplicationConstants.STARTYEAR;
import static com.oganalysis.constants.ApplicationConstants.LOCATION;
import static com.oganalysis.constants.ApplicationConstants.COMPANY;
import static com.oganalysis.constants.ApplicationConstants.TECHNOLOGY;
import static com.oganalysis.constants.ApplicationConstants.SMALLSCALELNG_LIQUEFACTION_CAPACITY;
import static com.oganalysis.constants.ApplicationConstants.SMALLSCALELNG_REGASIFICATION_CAPACITY;
import static com.oganalysis.constants.ApplicationConstants.SMALLSCALELNG_STORAGE_CAPACITY;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.business.SmallScaleLngBusinessService;
import com.oganalysis.dao.SmallScaleLngDao;
import com.oganalysis.entities.SmallScaleLng;

public class SmallScaleLngBusinessServiceImpl implements
		SmallScaleLngBusinessService {
	private SmallScaleLngDao smallScaleLngDao;
	@Override
	public List<Map> getSmallScaleLng(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<Map> smallScaleLngMapList=new ArrayList<Map>();
		List<SmallScaleLng> smallScaleLngDataList=smallScaleLngDao.getSelectedSmallScaleLng(selectedOptions);
		List<String> terminals=getTerminals(smallScaleLngDataList);
		for(String terminal:terminals)
		{
			Map smallScaleLng=new HashMap();
			for(SmallScaleLng ssl:smallScaleLngDataList)
			{
				if(terminal.equalsIgnoreCase(ssl.getTerminalName()))
				{
					smallScaleLng.put(TERMINAL,ssl.getTerminalName());
					smallScaleLng.put(COUNTRY, ssl.getCountry());
					smallScaleLng.put(TYPE, ssl.getType());
					smallScaleLng.put(STATUS, ssl.getStatus());
					smallScaleLng.put(STARTYEAR, ssl.getStartDate());
					smallScaleLng.put(LOCATION, ssl.getLocation());
					smallScaleLng.put(COMPANY, ssl.getCompany());
					smallScaleLng.put(TECHNOLOGY, ssl.getTechnology());
					smallScaleLng.put(SMALLSCALELNG_LIQUEFACTION_CAPACITY, ssl.getLiquefactionCapacity());
					smallScaleLng.put(SMALLSCALELNG_REGASIFICATION_CAPACITY, ssl.getRegasificationCapacity());
					smallScaleLng.put(SMALLSCALELNG_STORAGE_CAPACITY, ssl.getStorageCapacity());
					break;
				}
			}
			smallScaleLngMapList.add(smallScaleLng);
		}
		return smallScaleLngMapList;
	}
	private List<String> getTerminals(List<SmallScaleLng> smallScaleLngList)
	{
		List<String> terminals=new ArrayList<String>();
		for(SmallScaleLng ssl:smallScaleLngList)
		{
			if(!terminals.contains(ssl.getTerminalName()))
				terminals.add(ssl.getTerminalName());
		}
		return terminals;
	}
	
	public void setSmallScaleLngDao(SmallScaleLngDao smallScaleLngDao) {
		this.smallScaleLngDao = smallScaleLngDao;
	}
	@Override
	public List<String> getRegions() {
		// TODO Auto-generated method stub
		return smallScaleLngDao.getRegions();
	}
	@Override
	public List<String> getCountries() {
		// TODO Auto-generated method stub
		return smallScaleLngDao.getCountries();
	}
	@Override
	public List<String> getLocations() {
		// TODO Auto-generated method stub
		return smallScaleLngDao.getLocations();
	}
	@Override
	public List<String> getCompanies() {
		// TODO Auto-generated method stub
		return smallScaleLngDao.getCompanies();
	}
	@Override
	public List<String> getTechnologyProviders() {
		// TODO Auto-generated method stub
		return smallScaleLngDao.getTechnologyProviders();
	}
	@Override
	public List<String> getTechnologies() {
		// TODO Auto-generated method stub
		return smallScaleLngDao.getTechnologies();
	}
	@Override
	public List<String> getStatuses() {
		// TODO Auto-generated method stub
		return smallScaleLngDao.getStatuses();
	}
	@Override
	public List<String> getTypes() {
		// TODO Auto-generated method stub
		return smallScaleLngDao.getTypes();
	}
	
	
	
}
