package com.oganalysis.dao;

import java.util.List;
import java.util.Map;

import com.oganalysis.entities.Report;

public interface PdfReportsDao {
	public List<Report> getPdfReports(Map<String,List> selectedOptions);
	public List<String> getRegions();
	public List<String> getCountries();
	public List<String> getSecotors();
}
