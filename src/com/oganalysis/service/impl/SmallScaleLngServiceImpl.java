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
	public String getCountries(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> countries=smallScaleLngBusinessServiceImpl.getCountries(selectedOptions);
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();		
		return sslJson.createCountriesResponse(countries);
	}

	@Override
	public String getLocations(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> locations=smallScaleLngBusinessServiceImpl.getLocations(selectedOptions);
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();		
		return sslJson.createLocationsResponse(locations);
	}

	@Override
	public String getCompanies(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> companies=smallScaleLngBusinessServiceImpl.getCompanies(selectedOptions);
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();		
		return sslJson.createCompaniesResponse(companies);
	}

	@Override
	public String getTechnologyProviders(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> technologyProviders=smallScaleLngBusinessServiceImpl.getTechnologyProviders(selectedOptions);
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();		
		return sslJson.createTechnologyProvidersResponse(technologyProviders);
	}

	@Override
	public String getTechnologies(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> technologies=smallScaleLngBusinessServiceImpl.getTechnologies(selectedOptions);
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();		
		return sslJson.createTechnologyResponse(technologies);
	}

	@Override
	public String getStatuses(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> statuses=smallScaleLngBusinessServiceImpl.getStatuses(selectedOptions);
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();		
		return sslJson.createStatusResponse(statuses);
	}

	@Override
	public String getTypes(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<String> types=smallScaleLngBusinessServiceImpl.getTypes(selectedOptions);
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();		
		return sslJson.createTypeResponse(types);
	}
	
}
