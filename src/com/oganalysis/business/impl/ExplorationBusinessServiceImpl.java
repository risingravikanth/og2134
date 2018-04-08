package com.oganalysis.business.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.oganalysis.business.ExplorationBusinessService;
import com.oganalysis.dao.ExplorationDao;
import com.oganalysis.entities.Exploration;

import static com.oganalysis.constants.ApplicationConstants.*;

public class ExplorationBusinessServiceImpl implements
		ExplorationBusinessService {
	private ExplorationDao explorationDao;
	@Override
	public List<Map> getExplorationByBlockName(
			Map<String, List<String>> selectedOptions) {
		// TODO Auto-generated method stub
		List<Map> expMapList=new ArrayList<Map>();
		List<Exploration> explorationList=explorationDao.getSelectedExploration(selectedOptions);
		List<String> blockNos=getBlockNo(explorationList);		
		for(String blockNo:blockNos)
		{
			Map exploration=new HashMap();
			for(Exploration e:explorationList)
			{
				if(blockNo.equalsIgnoreCase(e.getBlockNo()))
				{
					exploration.put(BLOCKNAME, e.getBlockNo());
					exploration.put(COUNTRY,e.getCountry());
					exploration.put(BASIN,e.getBasin());
					exploration.put(ON_OFF_SHORE,e.getOnshoreOrOffshore());
					exploration.put(STATUS,e.getStatus());
					exploration.put(DATE_AWAREDED,"2017");
					exploration.put(OPERATOR,getOperator(blockNo,explorationList));
					exploration.put(OWNERS, getOwnerAndStakeList(blockNo,explorationList));
					exploration.put(AREA, getArea(blockNo,explorationList));
					break;
				}
			}
			expMapList.add(exploration);
		}
		
		return expMapList;
	}
	
	private List<String> getBlockNo(List<Exploration> explorationList)
	{
		List<String> blockNos=new ArrayList<String>();
		for(Exploration e:explorationList)
		{
			if(!blockNos.contains(e.getBlockNo()))
				blockNos.add(e.getBlockNo());
		}
		return blockNos;
	}
	private String getArea(String blockNo,List<Exploration> explorationList)
	{
		StringBuffer sb=new StringBuffer();		
		for(Exploration e:explorationList)
		{
			if(null!=e && 0!=e.getArea() && blockNo.equals(e.getBlockNo()))
				sb.append(e.getArea()).append(COMMA);
		}
		removeCommaAtEnd(sb);
		return sb.toString();
	}
	private List<String> getOwnerAndStakeList(String blockNo,List<Exploration> explorationList)
	{
		List<String> list=new ArrayList<String>();
		String hypen="-";
		for(Exploration e:explorationList)
		{
			if(null!=e && null!=e.getEquityPartners() && blockNo.equals(e.getBlockNo())&& !BLANK.equals(e.getEquityPartners()))
				list.add(e.getEquityPartners()+hypen+e.getEquityStakes());
		}
		
		return list;
	}
	private String getDateAwarded(String blockNo,List<Exploration> explorationList)
	{
		StringBuffer sb=new StringBuffer();
		SimpleDateFormat sd=new SimpleDateFormat("yyyy");
		for(Exploration e:explorationList)
		{
			if(null!=e && null!=e.getStartDate() && blockNo.equals(e.getBlockNo()) && !BLANK.equals(e.getStartDate()))
				sb.append(sd.format(e.getStartDate())).append(COMMA);
		}
		removeCommaAtEnd(sb);
		return sb.toString();
	}
	private String getOperator(String blockNo,List<Exploration> explorationList)
	{
		StringBuffer sb=new StringBuffer();		
		for(Exploration e:explorationList)
		{
			if(null!=e && null!=e.getOperator() && blockNo.equals(e.getBlockNo()) && !BLANK.equals(e.getOperator()))
				sb.append(e.getOperator()).append(COMMA);
		}
		removeCommaAtEnd(sb);
		return sb.toString();
	}
	private void removeCommaAtEnd(StringBuffer sb)
	{		
		if(sb.length()>0)
		sb.deleteCharAt(sb.length()-1);
	}
	public void setExplorationDao(ExplorationDao explorationDao) {
		this.explorationDao = explorationDao;
	}

	@Override
	public List<String> getRegions() {
		// TODO Auto-generated method stub
		return explorationDao.getRegions();
	}

	@Override
	public List<String> getCountries() {
		// TODO Auto-generated method stub
		return explorationDao.getCountries();
	}

	@Override
	public List<String> getBasins() {
		// TODO Auto-generated method stub
		return explorationDao.getBasins();
	}

	@Override
	public List<String> getOwners() {
		// TODO Auto-generated method stub
		return explorationDao.getOwners();
	}

	@Override
	public List<String> getOperators() {
		// TODO Auto-generated method stub
		return explorationDao.getOperators();
	}

	@Override
	public List<String> getStatus() {
		// TODO Auto-generated method stub
		return explorationDao.getStatus();
	}

	@Override
	public List<String> getType() {
		// TODO Auto-generated method stub
		return explorationDao.getType();
	}
		
	
}
