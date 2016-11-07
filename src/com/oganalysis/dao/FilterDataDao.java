package com.oganalysis.dao;

import java.util.List;

import com.oganalysis.entities.source.Countries;
import com.oganalysis.entities.source.Region;
import com.oganalysis.entities.source.Status;
import com.oganalysis.entities.source.Type;

public interface FilterDataDao {
	public List<Region> getRegions();
	public List<Countries> getCountries();
	public List<Status> getStatus();
	public List<Type> getType();
}
