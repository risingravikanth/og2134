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
	public static final String REGION="region";
	public static final String UNDERSCORE="_";
	public static final String NA="NA";
	
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
	public static final String OTHER_DETAILS="otherDetails";
	public static final String CAPACITY="Capacity";
	
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
	
	//Storage Restrictions in StorageDaoImpl
	public static final String RESTRICTION_PROPERTY_CURRENTOWNERS="currentOwners";
	public static final String RESTRICTION_PROPERTY_TANKFARM="tankFarm";
	public static final String STORAGE_CAPACITY="capacity";
	public static final String TANKSIZERANGE_MIN="tankSizeRangeMin";
	public static final String TANKSIZERANGE_MAX="tankSizeRangeMax";
	public static final String CURRENTOWNERS=RESTRICTION_PROPERTY_CURRENTOWNERS;
	public static final String CURRENTOWNERSHIP="currentOwnerShip";
		
	//Refineries Capactiy Modal Terminal Data display
	public static final String HISTORICOPERATOR="historicOperator";
	public static final String CURRENTEQUITYPARTNER="currentEquityPartners";
	public static final String CURRENTEQUITYSTAKE="currentEquityStake";
	public static final String RECENTDEVELOPMENTS="recentdevelopments";
	public static final String STARTUP="startUp";
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
	public static final String LOGIN_STATUS="st";
	public static final String LOGIN_USER="user";
	
	public static final String SUCCESS="success";
	public static final String FAIL="fail";
	
	//Production Tab
	public static final String FIELD="field";
	public static final String BOTH="both";
	public static final String OIL="oil";
	public static final String GAS="gas";
	public static final String NATURALGAS="Natural Gas";
	public static final String CRUDEOIL="Crude Oil";
	public static final String MTOE="MToe";
	public static final String MBOE="MBoE";
	public static final String BCMNG="BcMNG";
	
	//Pipeline Tab
	public static final String OPTION_COMMODITY="commodity";
	public static final String OPTION_STARTPOINT="startPoint";
	public static final String OPTION_ENDPOINT="endPoint";
	public static final String OPTION_SELECTED_COMMODITIES="commodities";
	public static final String OPTION_SELECTED_STARTPOINTS="startpoints";
	public static final String OPTION_SELECTED_ENDPOINTS="endpoints";
	
	public static final String RESTRICTION_PROPERTY_COMMODITY="commodity";
	public static final String RESTRICTION_PROPERTY_STARTPOINT=OPTION_STARTPOINT;
	public static final String RESTRICTION_PROPERTY_ENDPOINT=OPTION_ENDPOINT;
	public static final String RESTRICTION_PROPERTY_SUBPIPELINE="subPipelines";
	public static final String RESTRICTION_PROPERTY_PARENT_CHILD="parentChildRelation";
	public static final String RESTRICTION_PROPERTY_PIPELINE_TYPE="pipelineType";
	public static final String DOMESTIC="domestic";
	public static final String TRANSNATIONAL="transnational";
	public static final String PARENT="P";
	public static final String CHILD="C";
	public static final String PIPELINE="pipeline";
	public static final String SUBPIPELINE="subpipeline";
	public static final String LENGTH="length";
	public static final String DIAMETER="diameter";
	public static final String STARTPOINT=OPTION_STARTPOINT;
	public static final String ENDPOINT=OPTION_ENDPOINT;
	public static final String STARTCOUNTRY="startCountry";
	public static final String ENDCOUNTRY="endCountry";
	public static final String RELATION="relation";
	
	// SmallScaleLng Tab
	public static final String OPTION_COMPANY="company";
	public static final String OPTION_TECHNOLOGYPROVIDER="technologyprovider";
	public static final String OPTION_TECHNOLOGY="technology";
	public static final String OPTION_SELECTED_COMPANIES="companies";
	public static final String OPTION_SELECTED_TECHNOLOGYPROVIDERS="technologyproviders";
	public static final String OPTION_SELECTED_TECHNOLOGIES="technologies";
	
	public static final String RESTRICTION_PROPERTY_COMPANY=OPTION_COMPANY;
	public static final String RESTRICTION_PROPERTY_TECHNOLOGYPROVIDER="technologyProviderCompany";
	public static final String RESTRICTION_PROPERTY_TECHNOLOGY=OPTION_TECHNOLOGY;
	public static final String SMALLSCALELNG_LIQUEFACTION_CAPACITY="liquefactionCapacity";
	public static final String SMALLSCALELNG_REGASIFICATION_CAPACITY="regasificationCapacity";
	public static final String SMALLSCALELNG_STORAGE_CAPACITY="storageCapacity";
	
	
	public static final String YEAR2005="2005";
	public static final String YEAR2006="2006";
	public static final String YEAR2007="2007";
	public static final String YEAR2008="2008";
	public static final String YEAR2009="2009";
	public static final String YEAR2010="2010";
	public static final String YEAR2011="2011";
	public static final String YEAR2012="2012";
	public static final String YEAR2013="2013";
	public static final String YEAR2014="2014";
	public static final String YEAR2015="2015";
	public static final String YEAR2016="2016";
	public static final String YEAR2017="2017";
	
	//Excel
	public static final String EXCEL_LNG="LNG For ";
	public static final String EXCEL_REFINERY="Refinery For ";
	public static final String EXCEL_STORAGE="Storage For ";
	public static final String EXCEL_ERROR=ERROR_MSG;
	public static final String EXCEL_CONTENT_TYPE="application/vnd.ms-excel";
	public static final String EXCEL_CONTENT_DISPOSITION="Content-Disposition";
	public static final String EXCEL_ATTACHMENT="attachment; filename=";
	public static final String EXCEL_FILE_EXTENSION=".xlsx";
	public static final String EXCEL_REDIRECT="redirect:/";
	public static final String EXCEL_LOGO="/WEB-INF/oglogo.jpg";
	public static final String GENERAL_INFO="General Information";
	public static final String COMPANY_INFO="Company Information";
	public static final String INVESTMENT_INFO="Investment Information";
	public static final String CAPACITY_FORECASTS="Capacity Forecasts";
	
	public static final String EXCEL_TERMINAL="Terminal";
	public static final String GENERAL_INFO_REGION="Region";
	public static final String GENERAL_INFO_COUNTRY="Country";
	public static final String GENERAL_INFO_LOCATION="Location";
	public static final String GENERAL_INFO_TYPE="Type";
	public static final String GENERAL_INFO_STATUS="Status";
	public static final String GENERAL_INFO_REFINERINGCAPACITY="Refining Capacity (Kb/d)";
	public static final String GENERAL_INFO_RECENT_DEV="Recent Developments";
	public static final String GENERAL_INFO_STARTUP="Start Up";
	public static final String GENERAL_INFO_TANKS="Tanks(#)";
	public static final String GENERAL_INFO_TANKS_RANGE_MIN="Tank Size Range - Min (m3)";
	public static final String GENERAL_INFO_TANKS_RANGE_MAX="Tank Size Range - Max (m3)";
	
	public static final String COMPANY_INFO_OPERATOR="Operator";
	public static final String COMPANY_INFO_EQUITYHOLDERS="Equity Holders";
	public static final String COMPANY_INFO_STAKE="Stake(%)";
	
	public static final String INVESTMENT_INFO_CAPEX="Capex($)";
	public static final String CAPACITY_FORECASTS_NAME="Name";
	
	//Exploration
	public static final String OPTION_BASIN="basin";
	public static final String OPTION_SELECTED_BASINS="basins";
	public static final String BLOCK="block";
	public static final String RESTRICTION_PROPERTY_BASIN=OPTION_BASIN;
//	public static final String RESTRICTION_PROPERTY_ONOFFSHORE="onShoreOrOffShore";
	
	public static final String BLOCKNAME="blockName";
	public static final String BASIN=OPTION_BASIN;
	public static final String ON_OFF_SHORE=ONSHORE_OR_OFFSHORE;
	public static final String DATE_AWAREDED="dateawarded";
	public static final String AREA=RESTRICTION_PROPERTY_AREA;
	public static final String OWNERS=OPTION_OWNER;
	
	public static final String PDF_REDIRECT=EXCEL_REDIRECT;
	public static final String PDF_ATTACHMENT=EXCEL_ATTACHMENT;
}
