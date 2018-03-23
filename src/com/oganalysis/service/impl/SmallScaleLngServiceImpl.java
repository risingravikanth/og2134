package com.oganalysis.service.impl;

import java.util.List;
import java.util.Map;

import com.oganalysis.business.SmallScaleLngBusinessService;
import com.oganalysis.helper.ExplorationJsonResponse;
import com.oganalysis.helper.SmallScaleLngJsonResponse;
import com.oganalysis.service.SmallScaleLngService;

public class SmallScaleLngServiceImpl implements SmallScaleLngService {
	private SmallScaleLngBusinessService smallScaleLngBusinessServiceImpl;
	@Override
	public String getSmallScaleLngData(Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<Map> smallScaleLngData=smallScaleLngBusinessServiceImpl.getSmallScaleLng(selectedOptions);
		SmallScaleLngJsonResponse sslJsonRes=new SmallScaleLngJsonResponse();		
		return sslJsonRes.createSmallScaleLngResponse(smallScaleLngData);
	}
	
	public void setSmallScaleLngBusinessServiceImpl(
			SmallScaleLngBusinessService smallScaleLngBusinessServiceImpl) {
		this.smallScaleLngBusinessServiceImpl = smallScaleLngBusinessServiceImpl;
	}

	@Override
	public String getRegions() {
		// TODO Auto-generated method stub
		List<String> regions=smallScaleLngBusinessServiceImpl.getRegions();
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();		
		return sslJson.createRegionsResponse(regions);
	}

	@Override
	public String getCountries() {
		// TODO Auto-generated method stub
		List<String> countries=smallScaleLngBusinessServiceImpl.getCountries();
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();		
		return sslJson.createCountriesResponse(countries);
	}

	@Override
	public String getLocations() {
		// TODO Auto-generated method stub
		List<String> locations=smallScaleLngBusinessServiceImpl.getLocations();
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();		
		return sslJson.createLocationsResponse(locations);
	}

	@Override
	public String getCompanies() {
		// TODO Auto-generated method stub
		List<String> companies=smallScaleLngBusinessServiceImpl.getCompanies();
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();		
		return sslJson.createCompaniesResponse(companies);
	}

	@Override
	public String getTechnologyProviders() {
		// TODO Auto-generated method stub
		List<String> technologyProviders=smallScaleLngBusinessServiceImpl.getTechnologyProviders();
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();		
		return sslJson.createTechnologyProvidersResponse(technologyProviders);
	}

	@Override
	public String getTechnologies() {
		// TODO Auto-generated method stub
		List<String> technologies=smallScaleLngBusinessServiceImpl.getTechnologies();
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();		
		return sslJson.createTechnologyResponse(technologies);
	}

	@Override
	public String getStatuses() {
		// TODO Auto-generated method stub
		List<String> statuses=smallScaleLngBusinessServiceImpl.getStatuses();
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();		
		return sslJson.createStatusResponse(statuses);
	}

	@Override
	public String getTypes() {
		// TODO Auto-generated method stub
		List<String> types=smallScaleLngBusinessServiceImpl.getTypes();
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();		
		return sslJson.createTypeResponse(types);
	}
	
}
