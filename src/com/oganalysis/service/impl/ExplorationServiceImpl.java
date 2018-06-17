package com.oganalysis.service.impl;

import static com.oganalysis.constants.ApplicationConstants.BLOCK;

import java.util.List;
import java.util.Map;

import com.oganalysis.business.ExplorationBusinessService;
import com.oganalysis.entities.source.Countries;
import com.oganalysis.entities.source.Region;
import com.oganalysis.entities.source.Status;
import com.oganalysis.entities.source.Type;
import com.oganalysis.helper.ExplorationJsonResponse;
import com.oganalysis.helper.JsonResponse;
import com.oganalysis.service.ExplorationService;

public class ExplorationServiceImpl implements ExplorationService {
	private ExplorationBusinessService explorationBusinessServiceImpl;
	@Override
	public String getExplorationData(Map<String, List<String>> selectedOptions,
			String displayType) {
		// TODO Auto-generated method stub
		String explorationDataRes=null;
		if(null!=displayType && displayType.equalsIgnoreCase(BLOCK))		
			explorationDataRes=getExplorationByBlockWise(selectedOptions);				
		
		return explorationDataRes;
	}
	private String getExplorationByBlockWise(Map<String,List<String>> selectedOptions)
	{
		List<Map> blockWise=explorationBusinessServiceImpl.getExplorationByBlockName(selectedOptions);
		ExplorationJsonResponse explorationJson=new ExplorationJsonResponse();
		String blockWiseRes=explorationJson.createBlockWiseResponse(blockWise);
		return blockWiseRes;
	}
	@Override
	public String getRegions() {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> regionsList=explorationBusinessServiceImpl.getRegions();
		JsonResponse res=new JsonResponse();
		jsonRes=res.createRegions(regionsList);
		return jsonRes;
	}
	@Override
	public String getCountries(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> countriesList=explorationBusinessServiceImpl.getCountries(selectedOptions);
		JsonResponse res=new JsonResponse();
		jsonRes=res.createCountries(countriesList);
		return jsonRes;
	}
	@Override
	public String getBasins(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> basinList=explorationBusinessServiceImpl.getBasins(selectedOptions);
		JsonResponse res=new JsonResponse();
		jsonRes=res.createBasinResponse(basinList);
		return jsonRes;	
	}
	@Override
	public String getOwners(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> ownersList=explorationBusinessServiceImpl.getOwners(selectedOptions);
		JsonResponse res=new JsonResponse();
		jsonRes=res.createOwnersResponse(ownersList);
		return jsonRes;
	}
	@Override
	public String getOperators(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> operatorsList=explorationBusinessServiceImpl.getOperators(selectedOptions);
		JsonResponse res=new JsonResponse();
		jsonRes=res.createOperatorResponse(operatorsList);
		return jsonRes;
	}
	@Override
	public String getStatus(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> statusList=explorationBusinessServiceImpl.getStatus(selectedOptions);
		JsonResponse res=new JsonResponse();
		jsonRes=res.createStatus(statusList);
		return jsonRes;
	}
	@Override
	public String getType(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		String jsonRes=null;
		List<String> typeList=explorationBusinessServiceImpl.getType(selectedOptions);
		JsonResponse res=new JsonResponse();
		jsonRes=res.createType(typeList);
		return jsonRes;
	}
	public void setExplorationBusinessServiceImpl(
			ExplorationBusinessService explorationBusinessServiceImpl) {
		this.explorationBusinessServiceImpl = explorationBusinessServiceImpl;
	}
	
	
}
