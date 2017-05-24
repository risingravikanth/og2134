package com.oganalysis.constants;

public interface ApplicationConstants {
	
	public static final String COUNTRY="country";
	public static final String COMPANY="company";
	public static final String TERMINAL="terminal";
	public static final String BLANK="";
	public static final String COMMA=",";
	public static final String STARTDATE="startDate";
	public static final String ENDDATE="endDate";
	public static final String DISPLAYTYPE="displayType";
	public static final String RECORDNAME="recordName";
	public static final String UNDERSCORE="_";
	
	
	//LNG Constants
	public static final String LNG_REGASIFICATION="Regasification";
	public static final String LNG_LIQUEFACTION="Liquefaction";
	
	//LNG Capactiy Modal Terminal Data display
	public static final String PROCESSINGCAPACITY="processingCapacity";
	public static final String TRAINSORVAPORIZERS="trainsOrVaporizers";
	public static final String STORAGETANKS="storageTanks";
	public static final String OWNERSHIP="ownership";
	public static final String CONSTRUCTIONPERIOD="constructionPeriod";
	public static final String CONSTRUCTIONDETAILS="constructionDetails";
	public static final String CAPEX="capex";
	public static final String SOURCEFIELDS="sourceFields";
	public static final String DISTRIBUTIONTYPE="distributionType";
	public static final String SCHEDULEDSTARTUP="scheduledStartUp";
	public static final String TYPE="type";
	public static final String ONSHORE_OR_OFFSHORE="onshoreoroffshore";
	public static final String EXPECTEDSTARTUP="expectedStartUp";
	public static final String EQUITYPARTNER="equityPartner";
	public static final String EQUITYSTAKE="equityStake";
	public static final String CONSTRUCTIONSTART="constructionStart";
	public static final String CONSTRUCTIONEND="constructionEnd";
	public static final String CONSTRUCTIONCOMPANYNAME="constructionCompanyName";
	public static final String CONSTRUCTIONCONTRACTDETAILS="constructionContractDetails";
	
	//LNG Infra and Some values are also for LNG Capacity
	public static final String TERMINALNAME="terminalName";
	public static final String STATUS="status";
	public static final String STARTYEAR="startYear";
	public static final String LOCATION="location";
	public static final String TECHNOLOGY="technology";
	public static final String TRAIN="train";
	public static final String OPERATOR="operator";
	public static final String STORAGECAPACITY="storageCapacity";
	public static final String TANKS="tanks";
	public static final String OPERATIONAL="Operational";
	public static final String TRAINS="trains";
	public static final String UNDERCONSTRUCTION="Under Construction";
	public static final String PLANNED="Planned";
	public static final String PROPOSED="Proposed";
	public static final String SHUTDOWN="Shutdown";
	
	//LNG ,Supply Demand selected options keys
	
	public static final String OPTION_SELECTED_COUNTRIES="countries";
	public static final String OPTION_SELECTED_REGIONS="regions";
	public static final String OPTION_SELECTED_LOCATIONS="locations";
	public static final String OPTION_SELECTED_OPERATORS="operators";
	public static final String OPTION_SELECTED_OWNERS="owners";
	public static final String OPTION_SELECTED_STATUSES="statuses";
	public static final String OPTION_SELECTED_UNITS="units";
	public static final String OPTION_SELECTED_OFFONSHORES="offonshores";
	public static final String OPTION_SELECTED_TYPES="types";
	
	// LNG Restrictions in lngDao
	public static final String RESTRICTION_PROPERTY_NAME="name";
	public static final String RESTRICTION_PROPERTY_CAPACITYYEAR="capacityYear";
	public static final String RESTRICTION_PROPERTY_TYPE=TYPE;
	public static final String RESTRICTION_PROPERTY_AREA="area";
	public static final String RESTRICTION_PROPERTY_OPERATOR=OPERATOR;
	public static final String RESTRICTION_PROPERTY_EQUITYPARTNER="equityPartners";
	public static final String RESTRICTION_PROPERTY_COUNTRY=COUNTRY;
	public static final String RESTRICTION_PROPERTY_REGION="region";
	public static final String RESTRICTION_PROPERTY_STATUS=STATUS;
	public static final String RESTRICTION_PROPERTY_ONSHOREOROFFSHORE="onshoreOrOffshore";
	
	//LNG ,Supply Demand , Reports filter options
	public static final String OPTION_REGION=RESTRICTION_PROPERTY_REGION;
	public static final String OPTION_COUNTRY=COUNTRY;
	public static final String OPTION_LOCATION=LOCATION;
	public static final String OPTION_OPERATOR=OPERATOR;
	public static final String OPTION_OWNER="owner";
	public static final String OPTION_STATUS=STATUS;
	public static final String OPTION_UNIT="unit";
	public static final String OPTION_OFFONSHORE="offonshore";
	public static final String OPTION_TYPE=TYPE;
	public static final String OPTION_SECTOR="sector";
	
	
	// Contract Options
	public static final String IMPORT_COUNTRY="importcountry";
	public static final String EXPORT_COUNTRY="exportcountry";
	public static final String IMPORT_COMPANY="importcompany";
	public static final String EXPORT_COMPANY="exportcompany";
	
	// Contract Selected Options
	public static final String OPTION_SELECTED_IMPORT_COUNTRIES="importCountries";
	public static final String OPTION_SELECTED_EXPORT_COUNTRIES="exportCountries";
	public static final String OPTION_SELECTED_IMPORT_COMPANIES="importCompanies";
	public static final String OPTION_SELECTED_EXPORT_COMPANIES="exportCompanies";
	
	//Contract Restrictions in ContractDaoImpl
	public static final String RESTRICTION_PROPERTY_YEAR="year";
	public static final String RESTRICTION_PROPERTY_CONTRACTINDICATOR="contractIndicator";
	public static final String RESTRICTION_PROPERTY_EXPORTCOMPANY="exportCompany";
	public static final String RESTRICTION_PROPERTY_EXPORTCOUNTRY="exportCountry";
	public static final String RESTRICTION_PROPERTY_EXPORTTERMINAL="exportTerminal";
	public static final String RESTRICTION_PROPERTY_EXPORTCOUNTRIES=OPTION_SELECTED_EXPORT_COUNTRIES;
	public static final String RESTRICTION_PROPERTY_EXPORTCOMPANIES=OPTION_SELECTED_EXPORT_COMPANIES;
	public static final String RESTRICTION_PROPERTY_IMPORTCOUNTRY="importCountry";	
	public static final String RESTRICTION_PROPERTY_IMPORTCOMPANY="importCompany";
	
	// Json Response Contracts , LNG ,Supply Demand
	public static final String JSON_TOTALCAPACITY="totalCapacity";
	public static final String JSON_NAME="name";
	public static final String DATA_KEY="data";
	
	//Supply Demand
	public static final String EXPORT="Export";
	
	//Reports
	public static final String OPTION_SELECTED_SECTORS="sectors";
	public static final String RESTRICTION_PROPERTY_SECTOR="sector";
	//Reports Json
	public static final String REPORTNAME="reportName";
	
	//Refineries Restrictions in RefineriesDaoImpl
	public static final String RESTRICTION_PROPERTY_CURRENTEQUITYPARTNERS="currentEquityPartners";
	public static final String RESTRICTION_PROPERTY_CURRENTOPERATOR="currentOperator";
	public static final String RESTRICTION_PROPERTY_LOCATION=LOCATION;
	
	//Refineries Capactiy Modal Terminal Data display
	public static final String HISTORICOPERATOR="historicOperator";
	public static final String CURRENTEQUITYPARTNER="currentEquityPartners";
	public static final String CURRENTEQUITYSTAKE="currentEquityStake";
	public static final String COMMENCEMENT="commencement";	
	public static final String DECOMISSIONEDYEAR="decomissionedYear";
	public static final String HISTORICEQUITYPARTNER="historicEquityPartner";
	public static final String HISTORICEQUITYSTAKE="historicEquityStake";
	public static final String HISTORICOWNERSHIP="historicOwnership";
	public static final String CDUCAPACITY="cduCapacity";
	public static final String VDUCAPACITY="vduCapacity";
	public static final String COKINGCAPACITY="cokingCapacity";
	public static final String FCCCAPACITY="fccCapacity";
	public static final String HYDROCRACKINGCAPACITY="hydroCrackingCapacity";
	public static final String CRUDESTORAGEORTANK="crudeStorageOrTank";
	public static final String CRUDESTORAGECAPACITY="crudeStorageCapacity";
	public static final String VISBREAKINGCAPACITY="visbreakingCapacity";
	public static final String REFORMERCAPACITY="reformerCapacity";
	public static final String HYDROTREATINGCAPACITY="hydroTreatingCapacity";
	public static final String ALKYLATIONCAPACITY="alkylationCapacity";
	public static final String AROMACTICSCAPACITY="aromaticsCapacity";
	public static final String ISOMERIZATIONCAPACITY="isomerizationCapacity";
	public static final String POLYMERIZATIONCAPACITY="polymerizationCapacity";
	public static final String LUBESCAPACITY="lubesCapacity";
	public static final String OXYGENATESCAPACITY="oxygenatesCapacity";
	public static final String COKECAPACITY="cokeCapacity";
	public static final String SULPHURCAPACITY="sulphurCapacity";
	public static final String HYDROGENCAPACITY="hydrogenCapacity";
	public static final String ASPHALTCAPACITY="asphaltCapacity";
	public static final String GASOLINE="gasoline";
	public static final String LPG="lpg";
	public static final String KEROSINE="kerosine";
	public static final String JETFUEL="jetFuel";
	public static final String DIESEL="diesel";
	public static final String PROPYLENE="propylene";
	public static final String LIGHTNAPHTHA="lightNaphtha";
	public static final String HEAVYNAPHTHA="heavyNaphtha";
	public static final String KEROJET="kerojet";
	public static final String MAINTENANCEDETAILS="maintenanceDetails";
	public static final String MAINTENANCESTARTDATE="maintenanceStartDate";
	public static final String MAINTENANCEENDDATE="maintenanceEndDate";
	public static final String MAINTENANCENOTE="maintenanceNote";
	public static final String NELSONCOMPLEXINDEX="nelsonComplexIndex";
	public static final String REFINERYUTILIZATIONRATE="refineryUtilizationRate";
	public static final String OTHERSNAMESOFREFINERY="otherNamesOfRefinery";
	
	//Exception
	public static final String ERROR_MSG="error";
	
	//Login
	public static final String INCORRECT="incorrect";
	public static final String CORRECT="correct";
	
	//session email
	public static final String EMAIL="email";
	public static final String LOGIN="login";
}
