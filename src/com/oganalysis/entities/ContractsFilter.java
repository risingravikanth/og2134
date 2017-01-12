package com.oganalysis.entities;

public class ContractsFilter {
	private int id;
	private String contractIndicator;
	private String exportTerminal;
	private String exportCountry;
	private String exportCompany;
	private String importTerminal;
	private String importCountry;
	private String importCompany;
	private float contractedQuantity;
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
	public float getContractedQuantity() {
		return contractedQuantity;
	}
	public void setContractedQuantity(float contractedQuantity) {
		this.contractedQuantity = contractedQuantity;
	}
	
	
}
