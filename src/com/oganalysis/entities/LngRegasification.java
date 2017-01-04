package com.oganalysis.entities;

import java.util.Date;

public class LngRegasification extends Lng{
//	private int id;
//	private int recordId;
//	private String name;
//	private String country;
//	private String area;
//	private String region;
//	private String type;
//	private String status;
//	private String feedDetails;
//	private String fidDetails;
//	private String constructionStatusDetails;
//	private String sources;
//	private String OtherStatusDetails;
//	private Date statusDate;
//	private String statusSource;
//	private String OnshoreOrOffshore;
//	private Date expectedStartYear;
//	private Date scheduledStartYear;
//	private Date delayOrInitialStartYear;
//	private String delayDetails;
//	private String technologyDetails;
//	private double numberOfTrainsOrNumberOfVaporizers;
//	private double capacity;
//	private int capacityYear;
//	private String expansionDetail;
//	private String source;
//	private String additionalProducts;
//	private double additionalProductsProduction;
//	private String additionalProductsProductionUnit;
//	private double numberOfStorageTanks;
//	private double totalLngStorageCapacity;
//	private String sourcesOther;
//	private String operator;
//	private String equityPartners;
//	private double equityStakes;
//	private String equityNotes;
//	private String historicOperator;
//	private String historicEquityPartner;
//	private double historicEquityStake;
//	private int historicEquityYear;
//	private String equitySources;
//	private String feedOrInputType;
//	private String feedOrInputName;
//	private String feedOrInputDetails;
//	private String disttributionOrOutputType;
//	private String disttributionOrOutputName;
//	private String disttributionOrOutputDetails;
//	private double jettyInfo_m3;
//	private double capex;
//	private int capexYear;
//	private String capexDetails;
//	private String sources1;
//	private int constructionStart;
//	private int constructionEnd;
//	private String constructionCompanyName;
//	private String constructionContractDetails;
//	private String otherContractsDetails;
//	private String lngTerminalOtherNames;
//	private String relatedCompanies;
//	private String relatedCompanyDetails;
//	private String sources2;
//	private String moreInfo;
//	private String units;
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
//	public int getRecordId() {
//		return recordId;
//	}
//	public void setRecordId(int recordId) {
//		this.recordId = recordId;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getCountry() {
//		return country;
//	}
//	public void setCountry(String country) {
//		this.country = country;
//	}
//	public String getArea() {
//		return area;
//	}
//	public void setArea(String area) {
//		this.area = area;
//	}
//	public String getRegion() {
//		return region;
//	}
//	public void setRegion(String region) {
//		this.region = region;
//	}
//	public String getType() {
//		return type;
//	}
//	public void setType(String type) {
//		this.type = type;
//	}
//	public String getStatus() {
//		return status;
//	}
//	public void setStatus(String status) {
//		this.status = status;
//	}
//	public String getFeedDetails() {
//		return feedDetails;
//	}
//	public void setFeedDetails(String feedDetails) {
//		this.feedDetails = feedDetails;
//	}
//	public String getFidDetails() {
//		return fidDetails;
//	}
//	public void setFidDetails(String fidDetails) {
//		this.fidDetails = fidDetails;
//	}
//	public String getConstructionStatusDetails() {
//		return constructionStatusDetails;
//	}
//	public void setConstructionStatusDetails(String constructionStatusDetails) {
//		this.constructionStatusDetails = constructionStatusDetails;
//	}
//	public String getSources() {
//		return sources;
//	}
//	public void setSources(String sources) {
//		this.sources = sources;
//	}
//	public String getOtherStatusDetails() {
//		return OtherStatusDetails;
//	}
//	public void setOtherStatusDetails(String otherStatusDetails) {
//		OtherStatusDetails = otherStatusDetails;
//	}
//	public Date getStatusDate() {
//		return statusDate;
//	}
//	public void setStatusDate(Date statusDate) {
//		this.statusDate = statusDate;
//	}
//	public String getStatusSource() {
//		return statusSource;
//	}
//	public void setStatusSource(String statusSource) {
//		this.statusSource = statusSource;
//	}
//	public String getOnshoreOrOffshore() {
//		return OnshoreOrOffshore;
//	}
//	public void setOnshoreOrOffshore(String onshoreOrOffshore) {
//		OnshoreOrOffshore = onshoreOrOffshore;
//	}
//	public Date getExpectedStartYear() {
//		return expectedStartYear;
//	}
//	public void setExpectedStartYear(Date expectedStartYear) {
//		this.expectedStartYear = expectedStartYear;
//	}
//	public Date getScheduledStartYear() {
//		return scheduledStartYear;
//	}
//	public void setScheduledStartYear(Date scheduledStartYear) {
//		this.scheduledStartYear = scheduledStartYear;
//	}
//	public Date getDelayOrInitialStartYear() {
//		return delayOrInitialStartYear;
//	}
//	public void setDelayOrInitialStartYear(Date delayOrInitialStartYear) {
//		this.delayOrInitialStartYear = delayOrInitialStartYear;
//	}
//	public String getDelayDetails() {
//		return delayDetails;
//	}
//	public void setDelayDetails(String delayDetails) {
//		this.delayDetails = delayDetails;
//	}
//	public String getTechnologyDetails() {
//		return technologyDetails;
//	}
//	public void setTechnologyDetails(String technologyDetails) {
//		this.technologyDetails = technologyDetails;
//	}
//	public double getNumberOfTrainsOrNumberOfVaporizers() {
//		return numberOfTrainsOrNumberOfVaporizers;
//	}
//	public void setNumberOfTrainsOrNumberOfVaporizers(
//			double numberOfTrainsOrNumberOfVaporizers) {
//		this.numberOfTrainsOrNumberOfVaporizers = numberOfTrainsOrNumberOfVaporizers;
//	}
//	public double getCapacity() {
//		return capacity;
//	}
//	public void setCapacity(double capacity) {
//		this.capacity = capacity;
//	}
//	public int getCapacityYear() {
//		return capacityYear;
//	}
//	public void setCapacityYear(int capacityYear) {
//		this.capacityYear = capacityYear;
//	}
//	public String getExpansionDetail() {
//		return expansionDetail;
//	}
//	public void setExpansionDetail(String expansionDetail) {
//		this.expansionDetail = expansionDetail;
//	}
//	public String getSource() {
//		return source;
//	}
//	public void setSource(String source) {
//		this.source = source;
//	}
//	public String getAdditionalProducts() {
//		return additionalProducts;
//	}
//	public void setAdditionalProducts(String additionalProducts) {
//		this.additionalProducts = additionalProducts;
//	}
//	public double getAdditionalProductsProduction() {
//		return additionalProductsProduction;
//	}
//	public void setAdditionalProductsProduction(double additionalProductsProduction) {
//		this.additionalProductsProduction = additionalProductsProduction;
//	}
//	public String getAdditionalProductsProductionUnit() {
//		return additionalProductsProductionUnit;
//	}
//	public void setAdditionalProductsProductionUnit(
//			String additionalProductsProductionUnit) {
//		this.additionalProductsProductionUnit = additionalProductsProductionUnit;
//	}
//	public double getNumberOfStorageTanks() {
//		return numberOfStorageTanks;
//	}
//	public void setNumberOfStorageTanks(double numberOfStorageTanks) {
//		this.numberOfStorageTanks = numberOfStorageTanks;
//	}
//	public double getTotalLngStorageCapacity() {
//		return totalLngStorageCapacity;
//	}
//	public void setTotalLngStorageCapacity(double totalLngStorageCapacity) {
//		this.totalLngStorageCapacity = totalLngStorageCapacity;
//	}
//	public String getSourcesOther() {
//		return sourcesOther;
//	}
//	public void setSourcesOther(String sourcesOther) {
//		this.sourcesOther = sourcesOther;
//	}
//	public String getOperator() {
//		return operator;
//	}
//	public void setOperator(String operator) {
//		this.operator = operator;
//	}
//	public String getEquityPartners() {
//		return equityPartners;
//	}
//	public void setEquityPartners(String equityPartners) {
//		this.equityPartners = equityPartners;
//	}
//	public double getEquityStakes() {
//		return equityStakes;
//	}
//	public void setEquityStakes(double equityStakes) {
//		this.equityStakes = equityStakes;
//	}
//	public String getEquityNotes() {
//		return equityNotes;
//	}
//	public void setEquityNotes(String equityNotes) {
//		this.equityNotes = equityNotes;
//	}
//	public String getHistoricOperator() {
//		return historicOperator;
//	}
//	public void setHistoricOperator(String historicOperator) {
//		this.historicOperator = historicOperator;
//	}
//	public String getHistoricEquityPartner() {
//		return historicEquityPartner;
//	}
//	public void setHistoricEquityPartner(String historicEquityPartner) {
//		this.historicEquityPartner = historicEquityPartner;
//	}
//	public double getHistoricEquityStake() {
//		return historicEquityStake;
//	}
//	public void setHistoricEquityStake(double historicEquityStake) {
//		this.historicEquityStake = historicEquityStake;
//	}
//	public int getHistoricEquityYear() {
//		return historicEquityYear;
//	}
//	public void setHistoricEquityYear(int historicEquityYear) {
//		this.historicEquityYear = historicEquityYear;
//	}
//	public String getEquitySources() {
//		return equitySources;
//	}
//	public void setEquitySources(String equitySources) {
//		this.equitySources = equitySources;
//	}
//	public String getFeedOrInputType() {
//		return feedOrInputType;
//	}
//	public void setFeedOrInputType(String feedOrInputType) {
//		this.feedOrInputType = feedOrInputType;
//	}
//	public String getFeedOrInputName() {
//		return feedOrInputName;
//	}
//	public void setFeedOrInputName(String feedOrInputName) {
//		this.feedOrInputName = feedOrInputName;
//	}
//	public String getFeedOrInputDetails() {
//		return feedOrInputDetails;
//	}
//	public void setFeedOrInputDetails(String feedOrInputDetails) {
//		this.feedOrInputDetails = feedOrInputDetails;
//	}
//	public String getDisttributionOrOutputType() {
//		return disttributionOrOutputType;
//	}
//	public void setDisttributionOrOutputType(String disttributionOrOutputType) {
//		this.disttributionOrOutputType = disttributionOrOutputType;
//	}
//	public String getDisttributionOrOutputName() {
//		return disttributionOrOutputName;
//	}
//	public void setDisttributionOrOutputName(String disttributionOrOutputName) {
//		this.disttributionOrOutputName = disttributionOrOutputName;
//	}
//	public String getDisttributionOrOutputDetails() {
//		return disttributionOrOutputDetails;
//	}
//	public void setDisttributionOrOutputDetails(String disttributionOrOutputDetails) {
//		this.disttributionOrOutputDetails = disttributionOrOutputDetails;
//	}
//	public double getJettyInfo_m3() {
//		return jettyInfo_m3;
//	}
//	public void setJettyInfo_m3(double jettyInfo_m3) {
//		this.jettyInfo_m3 = jettyInfo_m3;
//	}
//	public double getCapex() {
//		return capex;
//	}
//	public void setCapex(double capex) {
//		this.capex = capex;
//	}
//	public int getCapexYear() {
//		return capexYear;
//	}
//	public void setCapexYear(int capexYear) {
//		this.capexYear = capexYear;
//	}
//	public String getCapexDetails() {
//		return capexDetails;
//	}
//	public void setCapexDetails(String capexDetails) {
//		this.capexDetails = capexDetails;
//	}
//	public String getSources1() {
//		return sources1;
//	}
//	public void setSources1(String sources1) {
//		this.sources1 = sources1;
//	}
//	public int getConstructionStart() {
//		return constructionStart;
//	}
//	public void setConstructionStart(int constructionStart) {
//		this.constructionStart = constructionStart;
//	}
//	public int getConstructionEnd() {
//		return constructionEnd;
//	}
//	public void setConstructionEnd(int constructionEnd) {
//		this.constructionEnd = constructionEnd;
//	}
//	public String getConstructionCompanyName() {
//		return constructionCompanyName;
//	}
//	public void setConstructionCompanyName(String constructionCompanyName) {
//		this.constructionCompanyName = constructionCompanyName;
//	}
//	public String getConstructionContractDetails() {
//		return constructionContractDetails;
//	}
//	public void setConstructionContractDetails(String constructionContractDetails) {
//		this.constructionContractDetails = constructionContractDetails;
//	}
//	public String getOtherContractsDetails() {
//		return otherContractsDetails;
//	}
//	public void setOtherContractsDetails(String otherContractsDetails) {
//		this.otherContractsDetails = otherContractsDetails;
//	}
//	public String getLngTerminalOtherNames() {
//		return lngTerminalOtherNames;
//	}
//	public void setLngTerminalOtherNames(String lngTerminalOtherNames) {
//		this.lngTerminalOtherNames = lngTerminalOtherNames;
//	}
//	public String getRelatedCompanies() {
//		return relatedCompanies;
//	}
//	public void setRelatedCompanies(String relatedCompanies) {
//		this.relatedCompanies = relatedCompanies;
//	}
//	public String getRelatedCompanyDetails() {
//		return relatedCompanyDetails;
//	}
//	public void setRelatedCompanyDetails(String relatedCompanyDetails) {
//		this.relatedCompanyDetails = relatedCompanyDetails;
//	}
//	public String getSources2() {
//		return sources2;
//	}
//	public void setSources2(String sources2) {
//		this.sources2 = sources2;
//	}
//	public String getMoreInfo() {
//		return moreInfo;
//	}
//	public void setMoreInfo(String moreInfo) {
//		this.moreInfo = moreInfo;
//	}
//	public String getUnits() {
//		return units;
//	}
//	public void setUnits(String units) {
//		this.units = units;
//	}
//	
}
