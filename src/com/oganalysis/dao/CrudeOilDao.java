package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.CrudeOil;

public interface CrudeOilDao {
	List<CrudeOil> getCrudeOil(Map<String,List<String>> selectedOptions);
}
