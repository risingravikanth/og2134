package com.oganalysis.service.impl;

import java.util.HashMap;
import java.util.List;

import com.oganalysis.dao.ExplorationDao;
import com.oganalysis.dao.FilterDataDao;
import com.oganalysis.dao.LngDao;
import com.oganalysis.dao.PipeLineDao;
import com.oganalysis.dao.RefineriesDao;
import com.oganalysis.dao.StorageDao;
import com.oganalysis.entities.source.Countries;
import com.oganalysis.entities.source.Region;
import com.oganalysis.entities.source.Status;
import com.oganalysis.entities.source.Type;
import com.oganalysis.helper.JsonResponse;
import com.oganalysis.service.FilterDataService;
import static com.oganalysis.constants.ApplicationConstants.BLANK;

public class FilterDataServiceImpl implements FilterDataService {
	private FilterDataDao filterDataDao;
	private LngDao lngDao;
	private RefineriesDao refineriesDao;
	private StorageDao storageDao;
	private PipeLineDao pipelineDao;
	private ExplorationDao explorationDao;
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
//		String jsonRes=null;
//		List<String> locationsList=lngDao.getLocations(new HashMap<String,List<String>>());
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createLocationsResponse(locationsList);
//		return jsonRes;
		return BLANK;
	}
	@Override
	public String getLngOperators() {
		// TODO Auto-generated method stub
//		String jsonRes=null;
//		List<String> operatorList=lngDao.getOperators(new HashMap<String,List<String>>());
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createOperatorResponse(operatorList);
//		return jsonRes;
		return BLANK;
	}
	@Override
	public String getLngOwners() {
		// TODO Auto-generated method stub
//		String jsonRes=null;
//		List<String> ownersList=lngDao.getOwners(new HashMap<String,List<String>>());
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createOwnersResponse(ownersList);
//		return jsonRes;
		return BLANK;
	}
	@Override
	public String getRefineryLocations() {
		// TODO Auto-generated method stub
//		String jsonRes=null;
//		List<String> locationsList=refineriesDao.getLocations();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createLocationsResponse(locationsList);
		return BLANK;
	}
	@Override
	public String getRefineryOperators() {
		// TODO Auto-generated method stub
//		String jsonRes=null;
//		List<String> operatorList=refineriesDao.getOperators();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createOperatorResponse(operatorList);
		return BLANK;
	}
	@Override
	public String getRefineryOwners() {
		// TODO Auto-generated method stub
//		String jsonRes=null;
//		List<String> ownersList=refineriesDao.getOwners();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createOwnersResponse(ownersList);
		return BLANK;
	}
	@Override
	public String getStorageLocations() {
		// TODO Auto-generated method stub
//		String jsonRes=null;
//		List<String> locationsList=storageDao.getLocations();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createLocationsResponse(locationsList);
		return BLANK;
	}
	@Override
	public String getStorageOwners() {
		// TODO Auto-generated method stub
//		String jsonRes=null;
//		List<String> ownersList=storageDao.getOwners();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createOwnersResponse(ownersList);
		return BLANK;
	}
	@Override
	public String getStorageOperators() {
		// TODO Auto-generated method stub
//		String jsonRes=null;
//		List<String> operatorList=storageDao.getOperators();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createOperatorResponse(operatorList);
		return BLANK;
	}
	@Override
	public String getPipeLineCommodities() {
		// TODO Auto-generated method stub
//		String jsonRes=null;
//		List<String> commodityList=pipelineDao.getCommodities();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createCommoditiesResponse(commodityList);
//		return jsonRes;		
		return BLANK;
	}
	@Override
	public String getPipeLineStartPoints() {
		// TODO Auto-generated method stub
//		String jsonRes=null;
//		List<String> startPointList=pipelineDao.getStartPoints();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createStartPointResponse(startPointList);
//		return jsonRes;
		return BLANK;
	}
	@Override
	public String getPipeLineEndPoints() {
		// TODO Auto-generated method stub
//		String jsonRes=null;
//		List<String> endPointList=pipelineDao.getEndPoints();
//		JsonResponse res=new JsonResponse();
//		jsonRes=res.createEndPointResponse(endPointList);
//		return jsonRes;	
		return BLANK;
	}		
	@Override
	public String getExplorationBasins() {
		// TODO Auto-generated method stub
		return BLANK;
	}
	@Override
	public String getExplorationOwners() {
		// TODO Auto-generated method stub
		return BLANK;
	}
	@Override
	public String getExplorationOperators() {
		// TODO Auto-generated method stub
		return BLANK;
	}
	
	
	public void setExplorationDao(ExplorationDao explorationDao) {
		this.explorationDao = explorationDao;
	}
	
	public void setFilterDataDao(FilterDataDao filterDataDao) {
		this.filterDataDao = filterDataDao;
	}
	
	public void setLngDao(LngDao lngDao) {
		this.lngDao = lngDao;
	}
	
	public void setRefineriesDao(RefineriesDao refineriesDao) {
		this.refineriesDao = refineriesDao;
	}
	
	public void setStorageDao(StorageDao storageDao) {
		this.storageDao = storageDao;
	}
	
	public void setPipelineDao(PipeLineDao pipelineDao) {
		this.pipelineDao = pipelineDao;
	}
	
}
