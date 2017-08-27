package com.oganalysis.entities;

import java.util.Date;

public class Exploration {
	private int id;	
	public String blockNo;
	public String region;
	private String country;
	private String onshoreOrOffshore;
	private String basin;
	private String status;
	private Date startDate;
	private String operator;
	private String equityParterns;
	private double equityStakes;
	private String sourceEquity;
	private double area;
	private Date licenseEnddate;
	private double wellsDrilled;
	private double TwoDSeismicCompleted;
	private double ThreeDSeismic;
	private String moreInfo;
	private String notes;
	private String source;
	private String licenseNo;
	public String getBlockNo() {
		return blockNo;
	}
	public void setBlockNo(String blockNo) {		
		this.blockNo = blockNo;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getOnshoreOrOffshore() {
		return onshoreOrOffshore;
	}
	public void setOnshoreOrOffshore(String onshoreOrOffshore) {
		this.onshoreOrOffshore = onshoreOrOffshore;
	}
	public String getBasin() {
		return basin;
	}
	public void setBasin(String basin) {
		this.basin = basin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getEquityParterns() {
		return equityParterns;
	}
	public void setEquityParterns(String equityParterns) {
		this.equityParterns = equityParterns;
	}	
	
	public double getEquityStakes() {
		return equityStakes;
	}
	public void setEquityStakes(double equityStakes) {
		this.equityStakes = equityStakes;
	}
	public String getSourceEquity() {
		return sourceEquity;
	}
	public void setSourceEquity(String sourceEquity) {
		this.sourceEquity = sourceEquity;
	}	
	public double getArea() {
		return area;
	}
	public void setArea(double area) {
		this.area = area;
	}
	public Date getLicenseEnddate() {
		return licenseEnddate;
	}
	public void setLicenseEnddate(Date licenseEnddate) {
		this.licenseEnddate = licenseEnddate;
	}
	
	
	public double getWellsDrilled() {
		return wellsDrilled;
	}
	public void setWellsDrilled(double wellsDrilled) {
		this.wellsDrilled = wellsDrilled;
	}

	public double getTwoDSeismicCompleted() {
		return TwoDSeismicCompleted;
	}
	public void setTwoDSeismicCompleted(double twoDSeismicCompleted) {
		TwoDSeismicCompleted = twoDSeismicCompleted;
	}
	public double getThreeDSeismic() {
		return ThreeDSeismic;
	}
	public void setThreeDSeismic(double threeDSeismic) {
		ThreeDSeismic = threeDSeismic;
	}
	public String getMoreInfo() {
		return moreInfo;
	}
	public void setMoreInfo(String moreInfo) {
		this.moreInfo = moreInfo;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getLicenseNo() {
		return licenseNo;
	}
	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}
