package com.oganalysis.service.impl;

import static com.oganalysis.constants.ApplicationConstants.BLOCK;

import java.util.List;
import java.util.Map;

import com.oganalysis.business.ExplorationBusinessService;
import com.oganalysis.helper.ExplorationJsonResponse;
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
	
	public void setExplorationBusinessServiceImpl(
			ExplorationBusinessService explorationBusinessServiceImpl) {
		this.explorationBusinessServiceImpl = explorationBusinessServiceImpl;
	}
	
}
