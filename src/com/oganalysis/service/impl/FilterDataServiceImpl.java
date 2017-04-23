package com.oganalysis.service.impl;

import java.util.List;

import com.oganalysis.dao.FilterDataDao;
import com.oganalysis.dao.LngDao;
import com.oganalysis.dao.RefineriesDao;
import com.oganalysis.entities.source.Countries;
import com.oganalysis.entities.source.Region;
import com.oganalysis.entities.source.Status;
import com.oganalysis.entities.source.Type;
import com.oganalysis.helper.JsonResponse;
import com.oganalysis.service.FilterDataService;

public class FilterDataServiceImpl implements FilterDataService {
	private FilterDataDao filterDataDao;
	private LngDao lngDao;
	private RefineriesDao refineriesDao;
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
	@Override
	public String getLngLocations() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> locationsList=lngDao.getLocations();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createLocationsResponse(locationsList);
		return jsonRes;
	}
	@Override
	public String getLngOperators() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> operatorList=lngDao.getOperators();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createOperatorResponse(operatorList);
		return jsonRes;
	}
	@Override
	public String getLngOwners() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> ownersList=lngDao.getOwners();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createOwnersResponse(ownersList);
		return jsonRes;
	}
	@Override
	public String getRefineryLocations() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> locationsList=refineriesDao.getLocations();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createLocationsResponse(locationsList);
		return jsonRes;
	}
	@Override
	public String getRefineryOperators() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> operatorList=refineriesDao.getOperators();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createOperatorResponse(operatorList);
		return jsonRes;
	}
	@Override
	public String getRefineryOwners() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> ownersList=refineriesDao.getOwners();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createOwnersResponse(ownersList);
		return jsonRes;
	}
	public FilterDataDao getFilterDataDao() {
		return filterDataDao;
	}
	public void setFilterDataDao(FilterDataDao filterDataDao) {
		this.filterDataDao = filterDataDao;
	}
	public LngDao getLngDao() {
		return lngDao;
	}
	public void setLngDao(LngDao lngDao) {
		this.lngDao = lngDao;
	}
	public RefineriesDao getRefineriesDao() {
		return refineriesDao;
	}
	public void setRefineriesDao(RefineriesDao refineriesDao) {
		this.refineriesDao = refineriesDao;
	}
	
	
	
	
	

}
