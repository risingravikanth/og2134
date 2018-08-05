package com.oganalysis.service.impl;

import java.util.List;
import java.util.Map;

import com.oganalysis.business.SmallScaleLngBusinessService;
import com.oganalysis.helper.ExplorationJsonResponse;
import com.oganalysis.helper.SmallScaleLngJsonResponse;
import com.oganalysis.service.SmallScaleLngService;
import static com.oganalysis.constants.ApplicationConstants.*;

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
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_REGIONS).isEmpty())//country is dependent on region filters
			return sslJson.createEmptyJsonArray();
		else
		{
			List<String> countries=smallScaleLngBusinessServiceImpl.getCountries(selectedOptions);			
			return sslJson.createCountriesResponse(countries);
		}
		
	}

	@Override
	public String getLocations(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_TECHNOLOGIES).isEmpty())//location is dependent on technology filters
			return sslJson.createEmptyJsonArray();
		else
		{
			List<String> locations=smallScaleLngBusinessServiceImpl.getLocations(selectedOptions);			
			return sslJson.createLocationsResponse(locations);
		}
		
	}

	@Override
	public String getCompanies(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();	
		if(selectedOptions.get(OPTION_SELECTED_COUNTRIES).isEmpty())//company is dependent on country filters
			return sslJson.createEmptyJsonArray();
		else
		{
			List<String> companies=smallScaleLngBusinessServiceImpl.getCompanies(selectedOptions);			
			return sslJson.createCompaniesResponse(companies);
		}
		
	}

	@Override
	public String getTechnologyProviders(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();//technologyProvider is dependent on company filters
		if(selectedOptions.get(OPTION_SELECTED_COMPANIES).isEmpty())
			return sslJson.createEmptyJsonArray();
		else
		{
			List<String> technologyProviders=smallScaleLngBusinessServiceImpl.getTechnologyProviders(selectedOptions);			
			return sslJson.createTechnologyProvidersResponse(technologyProviders);
		}
		
	}

	@Override
	public String getTechnologies(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();	
		if(selectedOptions.get(OPTION_SELECTED_TECHNOLOGYPROVIDERS).isEmpty())//technology is dependent on technologyProvider filters
			return sslJson.createEmptyJsonArray();
		else
		{
			List<String> technologies=smallScaleLngBusinessServiceImpl.getTechnologies(selectedOptions);			
			return sslJson.createTechnologyResponse(technologies);
		}
		
	}

	@Override
	public String getStatuses(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_LOCATIONS).isEmpty())//status is dependent on location filter
			return sslJson.createEmptyJsonArray();
		else
		{
			List<String> statuses=smallScaleLngBusinessServiceImpl.getStatuses(selectedOptions);			
			return sslJson.createStatusResponse(statuses);
		}
		
	}

	@Override
	public String getTypes(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		SmallScaleLngJsonResponse sslJson=new SmallScaleLngJsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_STATUSES).isEmpty())//type is dependent on status filter
			return sslJson.createEmptyJsonArray();
		else
		{
			List<String> types=smallScaleLngBusinessServiceImpl.getTypes(selectedOptions);			
			return sslJson.createTypeResponse(types);
		}
		
	}
	
}
