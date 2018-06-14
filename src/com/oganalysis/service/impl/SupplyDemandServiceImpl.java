package com.oganalysis.service.impl;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;

import com.oganalysis.business.SupplyDemandBusinessService;
import com.oganalysis.entities.source.Countries;
import com.oganalysis.entities.source.Region;
import com.oganalysis.helper.JsonResponse;
import com.oganalysis.helper.SupplyDemandJsonResponse;
import com.oganalysis.service.SupplyDemandService;

public class SupplyDemandServiceImpl implements SupplyDemandService {

	public SupplyDemandBusinessService supplyDemandBusinessServiceImpl;
	@Override
	public String getSupplyDemandData(
			Map<String, List<String>> selectedOptions,String startDate,
			String endDate, String displayType) {
		// TODO Auto-generated method stub
		int startDateVal=Integer.parseInt(startDate);
		int endDateVal=Integer.parseInt(endDate);
		SupplyDemandJsonResponse sdj=new SupplyDemandJsonResponse();
		List<Map<String,String>> supplyDemandData=supplyDemandBusinessServiceImpl.getSupplyDemand(selectedOptions, displayType);
		String supplyDemandRes=sdj.createSupplyDemandResponse(supplyDemandData,startDateVal,endDateVal,displayType);
		return supplyDemandRes;
	}
	@Override
	public String getRegions() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> regionsList=supplyDemandBusinessServiceImpl.getRegions();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createRegions(regionsList);
		return jsonRes;
	}

	@Override
	public String getCountries(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> countriesList=supplyDemandBusinessServiceImpl.getCountries(selectedOptions);
		JsonResponse res=new JsonResponse();
		jsonRes=res.createCountries(countriesList);
		return jsonRes;
	}
	public SupplyDemandBusinessService getSupplyDemandBusinessServiceImpl() {
		return supplyDemandBusinessServiceImpl;
	}
	public void setSupplyDemandBusinessServiceImpl(
			SupplyDemandBusinessService supplyDemandBusinessServiceImpl) {
		this.supplyDemandBusinessServiceImpl = supplyDemandBusinessServiceImpl;
	}
	
}
