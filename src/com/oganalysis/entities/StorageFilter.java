package com.oganalysis.entities;


public class StorageFilter {
	private int id;	
	private String tankFarm;
	private String region;
	private String country;
	private String location;
	private String status;	
	private String currentOperator;
	private String currentOwners;
	private double currentOwnership;
	private double capacityM3;	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTankFarm() {
		return tankFarm;
	}
	public void setTankFarm(String tankFarm) {
		this.tankFarm = tankFarm;
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
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
	public String getCurrentOwners() {
		return currentOwners;
	}
	public void setCurrentOwners(String currentOwners) {
		this.currentOwners = currentOwners;
	}
	public double getCurrentOwnership() {
		return currentOwnership;
	}
	public void setCurrentOwnership(double currentOwnership) {
		this.currentOwnership = currentOwnership;
	}
	public double getCapacityM3() {
		return capacityM3;
	}
	public void setCapacityM3(double capacityM3) {
		this.capacityM3 = capacityM3;
	}		
}
