package com.oganalysis.entities;

import java.util.Date;

public class RefineriesFilter {
	private int id;
	private String name;
	private String location;
	private String country;
	private String region;
	private String status;
	private String currentOperator;
	private String currentEquityPartners;
	private double currentEquityStakes;
	private double refiningCapacity;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCurrentOperator() {
		return currentOperator;
	}
	public void setCurrentOperator(String currentOperator) {
		this.currentOperator = currentOperator;
	}
	public String getCurrentEquityPartners() {
		return currentEquityPartners;
	}
	public void setCurrentEquityPartners(String currentEquityPartners) {
		this.currentEquityPartners = currentEquityPartners;
	}
	public double getCurrentEquityStakes() {
		return currentEquityStakes;
	}
	public void setCurrentEquityStakes(double currentEquityStakes) {
		this.currentEquityStakes = currentEquityStakes;
	}
	public double getRefiningCapacity() {
		return refiningCapacity;
	}
	public void setRefiningCapacity(double refiningCapacity) {
		this.refiningCapacity = refiningCapacity;
	}
	
}
