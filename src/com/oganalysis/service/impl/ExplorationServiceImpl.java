package com.oganalysis.service.impl;

import static com.oganalysis.constants.ApplicationConstants.BLOCK;
import static com.oganalysis.constants.ApplicationConstants.*;

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
		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_REGIONS).isEmpty())//country is dependent on regions filter
			return res.createEmptyJsonArray();
		else
		{
			List<String> countriesList=explorationBusinessServiceImpl.getCountries(selectedOptions);			
			return res.createCountries(countriesList);			
		}
		
	}
	@Override
	public String getBasins(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_COUNTRIES).isEmpty())//basin is dependent on country filters
			return res.createEmptyJsonArray();
		else
		{
			List<String> basinList=explorationBusinessServiceImpl.getBasins(selectedOptions);			
			return res.createBasinResponse(basinList);				
		}
		
	}
	@Override
	public String getOwners(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_OPERATORS).isEmpty())//owner is dependent on operators
			return res.createEmptyJsonArray();
		else
		{
			List<String> ownersList=explorationBusinessServiceImpl.getOwners(selectedOptions);			
			return res.createOwnersResponse(ownersList);			
		}
		
	}
	@Override
	public String getOperators(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_BASINS).isEmpty())//operator is dependent on basins
			return res.createEmptyJsonArray();
		else
		{
			List<String> operatorsList=explorationBusinessServiceImpl.getOperators(selectedOptions);			
			return res.createOperatorResponse(operatorsList);			
		}
		
	}
	@Override
	public String getStatus(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_OWNERS).isEmpty())// status is dependent on owner filters
			return res.createEmptyJsonArray();
		else
		{
			List<String> statusList=explorationBusinessServiceImpl.getStatus(selectedOptions);			
			return res.createStatus(statusList);
			
		}
		
	}
	@Override
	public String getType(Map<String,List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		
		JsonResponse res=new JsonResponse();
		if(selectedOptions.get(OPTION_SELECTED_STATUSES).isEmpty())//type is dependent on status filters
			return res.createEmptyJsonArray();
		else
		{
			List<String> typeList=explorationBusinessServiceImpl.getType(selectedOptions);			
			return res.createType(typeList);
			
		}
		
	}
	public void setExplorationBusinessServiceImpl(
			ExplorationBusinessService explorationBusinessServiceImpl) {
		this.explorationBusinessServiceImpl = explorationBusinessServiceImpl;
	}
	
	
}
