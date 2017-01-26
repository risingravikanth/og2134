package com.oganalysis.entities;

public class Contracts {
	private int id;
	private String contractIndicator;
	private int year;
	private String exportTerminal;
	private String exportCountry;
	private String exportCompany;
	private String importTerminal;
	private String importCountry;
	private String importCompany;
	private double contractedQuantity;
	private String contractAgreementDate;
	private int contractStartFrom;
	private int contractEndsIn;
	private String typeOfTransportation;
	private String contractAgreementStatus;
	private String contractAdditionalDetails;
	private String sources;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContractIndicator() {
		return contractIndicator;
	}
	public void setContractIndicator(String contractIndicator) {
		this.contractIndicator = contractIndicator;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getExportTerminal() {
		return exportTerminal;
	}
	public void setExportTerminal(String exportTerminal) {
		this.exportTerminal = exportTerminal;
	}
	public String getExportCountry() {
		return exportCountry;
	}
	public void setExportCountry(String exportCountry) {
		this.exportCountry = exportCountry;
	}
	public String getExportCompany() {
		return exportCompany;
	}
	public void setExportCompany(String exportCompany) {
		this.exportCompany = exportCompany;
	}
	public String getImportTerminal() {
		return importTerminal;
	}
	public void setImportTerminal(String importTerminal) {
		this.importTerminal = importTerminal;
	}
	public String getImportCountry() {
		return importCountry;
	}
	public void setImportCountry(String importCountry) {
		this.importCountry = importCountry;
	}
	public String getImportCompany() {
		return importCompany;
	}
	public void setImportCompany(String importCompany) {
		this.importCompany = importCompany;
	}	
	public double getContractedQuantity() {
		return contractedQuantity;
	}
	public void setContractedQuantity(double contractedQuantity) {
		this.contractedQuantity = contractedQuantity;
	}
	public String getContractAgreementDate() {
		return contractAgreementDate;
	}
	public void setContractAgreementDate(String contractAgreementDate) {
		this.contractAgreementDate = contractAgreementDate;
	}
	public int getContractStartFrom() {
		return contractStartFrom;
	}
	public void setContractStartFrom(int contractStartFrom) {
		this.contractStartFrom = contractStartFrom;
	}
	public int getContractEndsIn() {
		return contractEndsIn;
	}
	public void setContractEndsIn(int contractEndsIn) {
		this.contractEndsIn = contractEndsIn;
	}
	public String getTypeOfTransportation() {
		return typeOfTransportation;
	}
	public void setTypeOfTransportation(String typeOfTransportation) {
		this.typeOfTransportation = typeOfTransportation;
	}
	public String getContractAgreementStatus() {
		return contractAgreementStatus;
	}
	public void setContractAgreementStatus(String contractAgreementStatus) {
		this.contractAgreementStatus = contractAgreementStatus;
	}
	public String getContractAdditionalDetails() {
		return contractAdditionalDetails;
	}
	public void setContractAdditionalDetails(String contractAdditionalDetails) {
		this.contractAdditionalDetails = contractAdditionalDetails;
	}
	public String getSources() {
		return sources;
	}
	public void setSources(String sources) {
		this.sources = sources;
	}
	
}
