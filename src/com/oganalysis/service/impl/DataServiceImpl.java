package com.oganalysis.service.impl;

import java.util.List;
import java.util.Map;

import com.oganalysis.dao.OGDao;
import com.oganalysis.entities.CrudeOil;
import com.oganalysis.entities.Exploration;
import com.oganalysis.entities.Lng;
import com.oganalysis.entities.NaturalGas;
import com.oganalysis.entities.PipeLine;
import com.oganalysis.entities.Refinery;
import com.oganalysis.entities.Storage;
import com.oganalysis.helper.JsonResponse;
import com.oganalysis.service.DataService;


public class DataServiceImpl implements DataService {
	
	
	private OGDao crudeOilDao;
//	private OGDao explorationDao;
//	private LDao lngDao;
	private OGDao naturalGasDao;
//	private OGDao pipeLineDao;
	private OGDao refineryDao;
	private OGDao storageDao;
	
	@Override
	public String getCrudeOilData(Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
		List crudeOilList=null;
		if(selectedOptions.size()>0)
			crudeOilList=crudeOilDao.getOGAnalysisCriteriaData(selectedOptions);
		else
			crudeOilList=crudeOilDao.getOGAnalysisData();
		for(int i=0;i<crudeOilList.size();i++)
		{
			CrudeOil crudeOil=(CrudeOil)crudeOilList.get(i);
			
			
			System.out.println("ID:"+crudeOil.getId()+" field:"+crudeOil.getField()+" Region:"+crudeOil.getRegion()+" Country:"+crudeOil.getCountry());
		}
		JsonResponse jsonRes=new JsonResponse();
		String res=jsonRes.createCrudeOilResponse(crudeOilList);
		return res;
	}
	@Override
	public String getExplorationData(Map<String,List> selectedOptions) {
		return null;
//		// TODO Auto-generated method stub
//		List explorationList=null;
//		if(selectedOptions.size()>0)
//			explorationList=explorationDao.getOGAnalysisCriteriaData(selectedOptions);
//		else
//			explorationList=explorationDao.getOGAnalysisData();
//		for(int i=0;i<explorationList.size();i++)
//		{
//			Exploration exploration=(Exploration)explorationList.get(i);
//			
//			
//			System.out.println("ID:"+exploration.getId()+" blockNo:"+exploration.getBlockNo()+" Region:"+exploration.getRegion()+" Country:"+exploration.getCountry());
//		}
//		JsonResponse res=new JsonResponse();
//		String response=res.createExplorationResponse(explorationList);
//		return response;
	}
//	@Override
//	public String getLngData(Map<String, List> selectedOptions) {
//		// TODO Auto-generated method stub
//		List lngList=null;
//		if(selectedOptions.size()>0)
//			lngList=lngDao.getOGAnalysisCriteriaData(selectedOptions);
//		else
//			lngList=lngDao.getOGAnalysisData();
//		for(int i=0;i<lngList.size();i++)
//		{
//			Lng lng=(Lng)lngList.get(i);
//			
//			
//			System.out.println("ID:"+lng.getId()+" Name:"+lng.getName()+" Region:"+lng.getRegion()+" Country:"+lng.getCountry());
//		}
//		JsonResponse res=new JsonResponse();
//		String response=res.createLngResponse(lngList);
//		return response;
//	}
	@Override
	public String getNaturalGasData(Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
				List naturalGasList=null;
				if(selectedOptions.size()>0)
					naturalGasList=naturalGasDao.getOGAnalysisCriteriaData(selectedOptions);
				else
					naturalGasList=naturalGasDao.getOGAnalysisData();
				for(int i=0;i<naturalGasList.size();i++)
				{
					NaturalGas naturalGas=(NaturalGas)naturalGasList.get(i);
					
					
					System.out.println("ID:"+naturalGas.getId()+" Field:"+naturalGas.getField()+" Region:"+naturalGas.getRegion()+" Country:"+naturalGas.getCountry());
				}
				JsonResponse res=new JsonResponse();
				String response=res.createNaturalGasResponse(naturalGasList);
				return response;
	}
//	@Override
//	public String getPipeLineData(Map<String, List> selectedOptions) {
//		// TODO Auto-generated method stub
//				List pipeLineList=null;
//				if(selectedOptions.size()>0)
//					pipeLineList=pipeLineDao.getOGAnalysisCriteriaData(selectedOptions);
//				else
//					pipeLineList=pipeLineDao.getOGAnalysisData();
//				for(int i=0;i<pipeLineList.size();i++)
//				{
//					PipeLine pipeLine=(PipeLine)pipeLineList.get(i);
//				
//				
//					System.out.println("ID:"+pipeLine.getId()+" pipeline:"+pipeLine.getPipeline()+" Region:"+pipeLine.getRegion()+" Country:"+pipeLine.getCountry());
//				}
//				JsonResponse res=new JsonResponse();
//				String response=res.createPipeLineResponse(pipeLineList);
//				return response;
//	}
	@Override
	public String getRefineryData(Map<String,List> selectedOptions) {
		// TODO Auto-generated method stub
		List refineryList=null;
		if(selectedOptions.size()>0)
			refineryList=refineryDao.getOGAnalysisCriteriaData(selectedOptions);
		else
			refineryList=refineryDao.getOGAnalysisData();
		for(int i=0;i<refineryList.size();i++)
		{
			Refinery refinery=(Refinery)refineryList.get(i);
			
			
			System.out.println("ID:"+refinery.getId()+" Name:"+refinery.getName()+" Region:"+refinery.getRegion()+" Country:"+refinery.getCountry());
		}
		JsonResponse res=new JsonResponse();
		String response=res.createRefineryResponse(refineryList);
		return response;
	}
	@Override
	public String getStorageData(Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
		List storageList=null;
		if(selectedOptions.size()>0)
			storageList=storageDao.getOGAnalysisCriteriaData(selectedOptions);
		else
			storageList=storageDao.getOGAnalysisData();
		for(int i=0;i<storageList.size();i++)
		{
			Storage storage=(Storage)storageList.get(i);
		
		
			System.out.println("ID:"+storage.getId()+" pipeline:"+storage.getTankFarm()+" Region:"+storage.getRegion()+" Country:"+storage.getCountry());
		}
		JsonResponse res=new JsonResponse();
		String response=res.createStorageResponse(storageList);
		return response;
	}
//	public OGDao getExplorationDao() {
//		return explorationDao;
//	}
//	
//	public void setExplorationDao(OGDao explorationDao) {
//		this.explorationDao = explorationDao;
//	}
	public OGDao getRefineryDao() {
		return refineryDao;
	}
	public void setRefineryDao(OGDao refineryDao) {
		this.refineryDao = refineryDao;
	}
	public OGDao getCrudeOilDao() {
		return crudeOilDao;
	}
	public void setCrudeOilDao(OGDao crudeOilDao) {
		this.crudeOilDao = crudeOilDao;
	}
//	public OGDao getLngDao() {
//		return lngDao;
//	}
//	public void setLngDao(OGDao lngDao) {
//		this.lngDao = lngDao;
//	}
	public OGDao getNaturalGasDao() {
		return naturalGasDao;
	}
	public void setNaturalGasDao(OGDao naturalGasDao) {
		this.naturalGasDao = naturalGasDao;
	}
	public OGDao getStorageDao() {
		return storageDao;
	}
	public void setStorageDao(OGDao storageDao) {
		this.storageDao = storageDao;
	}	
//	public OGDao getPipeLineDao() {
//		return pipeLineDao;
//	}
//	public void setPipeLineDao(OGDao pipeLineDao) {
//		this.pipeLineDao = pipeLineDao;
//	}
//	
	@Override
	public String getPipeLineData(Map<String, List> selectedOptions) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
		
	
}
