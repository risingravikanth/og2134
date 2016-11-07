package com.oganalysis.service.impl;

import java.util.List;

import com.oganalysis.dao.FilterDataDao;
import com.oganalysis.entities.source.Countries;
import com.oganalysis.entities.source.Region;
import com.oganalysis.entities.source.Status;
import com.oganalysis.entities.source.Type;
import com.oganalysis.helper.JsonResponse;
import com.oganalysis.service.FilterDataService;

public class FilterDataServiceImpl implements FilterDataService {
	private FilterDataDao filterDataDao;
	@Override
	public String getRegions() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<Region> regionsList=filterDataDao.getRegions();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createRegionsResponse(regionsList);
		return jsonRes;
	}
	@Override
	public String getCountries() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<Countries> countriesList=filterDataDao.getCountries();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createCountriesResponse(countriesList);
		return jsonRes;
	}
	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<Status> statusList=filterDataDao.getStatus();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createStatusResponse(statusList);
		return jsonRes;
	}
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<Type> typeList=filterDataDao.getType();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createTypeResponse(typeList);
		return jsonRes;
	}
	public FilterDataDao getFilterDataDao() {
		return filterDataDao;
	}
	public void setFilterDataDao(FilterDataDao filterDataDao) {
		this.filterDataDao = filterDataDao;
	}
	
	

}
