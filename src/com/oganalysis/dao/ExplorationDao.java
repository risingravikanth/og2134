package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.Exploration;

public interface ExplorationDao {
	public List<Exploration> getSelectedExploration(Map<String, List<String>> selectedOptions); 
}
