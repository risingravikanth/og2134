package com.oganalysis.dao;

import java.util.List;

import com.oganalysis.entities.source.Countries;
import com.oganalysis.entities.source.Region;

public interface FilterDataDao {
	public List<Region> getRegions();
	public List<Countries> getCountries();
}
