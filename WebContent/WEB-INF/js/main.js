 
 angular.module('OGAnalysis', [
    'ui.router',
	'angularjs-dropdown-multiselect'
    
  ])
  .config(['$stateProvider','$urlRouterProvider',function ($stateProvider,$urlRouterProvider) {
	  
	  $urlRouterProvider.otherwise('/exploration');
	  
	  $stateProvider
      .state('dashboard', {
        url:'/dashboard',
        templateUrl: 'views/home.html',
		controller:"CommonCtrl"
	  })
	  .state('exploration', {
        url:'/exploration',
        templateUrl: 'views/commonpage.html',
		controller:"CommonCtrl"
	  })
	  
	  .state('lng', {
        url:'/lng',
        templateUrl: 'views/commonpage.html',
		controller:"CommonCtrl"
	  })
	  .state('pipelines', {
        url:'/pipelines',
        templateUrl: 'views/commonpage.html',
		controller:"CommonCtrl"
	  })
	  .state('production', {
        url:'/production',
        templateUrl: 'views/commonpage.html',
		controller:"CommonCtrl"
	  })
	  .state('refineries', {
        url:'/refineries',
        templateUrl: 'views/commonpage.html',
		controller:"CommonCtrl"
	  })
	   .state('storage', {
        url:'/storage',
        templateUrl: 'views/commonpage.html',
		controller:"CommonCtrl"
	  })
	   .state('crude', {
        url:'/crude',
        templateUrl: 'views/commonpage.html',
		controller:"CommonCtrl"
	  }) 
	  .state('naturalgas', {
        url:'/naturalgas',
        templateUrl: 'views/commonpage.html',
		controller:"CommonCtrl"
	  })
	  .state('capacity', {
        url:'/capacity',
        templateUrl: 'views/capacity.html',
		controller:"CapacityCtrl"
	  })
	  .state('contracts', {
        url:'/contracts',
        templateUrl: 'views/contracts.html',
		controller:"ContractsCtrl"
	  })
	  .state('supplydemand', {
        url:'/supplydemand',
        templateUrl: 'views/supplydemand.html',
		controller:"SupplyDemandCtrl"
	  })
	   .state('infrastructure', {
        url:'/infrastructure',
        templateUrl: 'views/infrastructure.html',
		controller:"InfrastructureCtrl"
	  })
	  .state('reports', {
        url:'/reports',
        templateUrl: 'views/commonpage.html',
		controller:"CommonCtrl"
	  })
  }]);
  
  
  angular.module('OGAnalysis').controller('FilterCtrl', function($scope,$state,$rootScope,URL,HttpService) {
	console.log("In FilterCtrl ctrl");
	/*	
	Region
	Country
	Locations
	Operator
	Owner
	Status
	Units
	Offshore/Onshore
	Type
	*/

	$scope.example1model = []; 
	$scope.example1data = [ {id: 1, label: "David"}, {id: 2, label: "Jhon"}, {id: 3, label: "Danny"}];


	// JavaScript
	$scope.example9model = [];
	$scope.example9data = [ {id: 1, label: "David"}, {id: 2, label: "Jhon"}, {id: 3, label: "Danny"}];
	$scope.example9settings = {enableSearch: true};
	
	
	/* region filter */
	$scope.regionData = [];
	$rootScope.regionModel = [];
	$scope.regionEvents = {
		onItemSelect: function(item) { $rootScope.onFilterSelect(item,'region') }
	};
	
	/* country filter */
	$scope.countryData = [];
	$rootScope.countryModel = [];
	$scope.countrySettings = {enableSearch: true};
	$scope.countryEvents = {
			onItemSelect: function(item) { $rootScope.onFilterSelect(item,'country') }
		};
	
	/* exportedCountries filter */
	$scope.exportedCountriesData = [];
	$rootScope.exportedCountriesModel = [];
	$scope.exportedCountriesSettings = {enableSearch: true};
	$scope.exportedCountriesEvents = {
			onItemSelect: function(item) { 
					$rootScope.onFilterSelect(item,'importedCompanies') 
			}
			/*,
			onItemDeselect : function(item) {
				console.log("dasf") 
				$rootScope.onFilterDeSelect(item,'importedCompanies') 
			}*/
		};
	
	/* importedCountries filter */
	$scope.importedCountriesData = [];
	$rootScope.importedCountriesModel = [];
	$scope.importedCountriesSettings = {enableSearch: true};
	$scope.importedCountriesEvents = {
			onItemSelect: function(item) { 
					$rootScope.onFilterSelect(item,'importedCompanies') 
			}
		};
	
	/* exportedCompanies filter */
	$scope.exportedCompaniesData = [];
	$rootScope.exportedCompaniesModel = [];
	$scope.exportedCompaniesSettings = {enableSearch: true};
	$scope.exportedCompaniesEvents = {
			onItemSelect: function(item) { 
					$rootScope.onFilterSelect(item,'importedCompanies') 
			}
			/*,
			onItemDeselect : function(item) {
				console.log("dasf") 
				$rootScope.onFilterDeSelect(item,'importedCompanies') 
			}*/
		};
	
	/* importedCompanies filter */
	$scope.importedCompaniesData = [];
	$rootScope.importedCompaniesModel = [];
	$scope.importedCompaniesSettings = {enableSearch: true};
	$scope.importedCompaniesEvents = {
			onItemSelect: function(item) { 
					$rootScope.onFilterSelect(item,'importedCompanies') 
			}
		 
		};
	
	
	/*location */
		$scope.locationData = [];
		$rootScope.locationModel = [];
		$scope.locationSettings = {enableSearch: true};
	/*operator*/	
		$scope.operatorData = [];
		$rootScope.operatorModel = [];
 	
	/* owner*/
		$scope.ownerData = [];
		$rootScope.ownerModel = [];
  
	/* Status filter */
	$scope.statusData = [];
	$rootScope.statusModel = [];
	
 	
	/*units filter*/
	$rootScope.unitsModel = [];
	$scope.unitsData = [{id: 'BCF', label: "BCF"}];
	$scope.unitsSettings = {selectionLimit: 1};
	
 	/*offshore filter*/
	$rootScope.offshoreModel = [];
	$scope.offshoreData = [{id: 'Not_Decided', label: "Not Decided"}, {id: 'Offshore', label: "Offshore"}, {id: 'Onshore', label: "Onshore"}];
	 	
	/*type filter*/
	$rootScope.typeModel = [];
	$scope.typeData = [];
	 	
	/*sector filter*/
	$rootScope.sectorModel = [];
	$scope.sectorData = [{id: 'Exploration', label: "Exploration"}, {id: 'LNG', label: "LNG"}, {id: 'Pipeline', label: "Pipe line"}, {id: 'Refinery', label: "Refinery"}, {id: 'Storage', label: "Storage"}];
	$scope.sectorSettings = {selectionLimit: 1};
	$scope.sectorEvents = {
			onItemSelect: function(item) { $rootScope.onFilterSelect(item,'sector') }
		};
	
	
	
	$rootScope.filterObj = {
		regionField :true,
		countryField :true,
		locationField : true,
		operatorField : true,
		ownerField : true,
		statusField : true,
		unitsField : true,
		offshoreField : true,
		typeField :true,
		sectorField :true,
		imports: false,
		exports: false
	};
 
	 	
 	HttpService.get('/regions').then(function(resp) {
		for(var i=0;i< resp.length;i++){
			var obj = {
					id : resp[i].region ,
					label : resp[i].region
			}
			$scope.regionData.push(obj);
		}
	});
	
	HttpService.get('/countries').then(function(resp) {
		for(var i=0;i< resp.length;i++){
			var obj = {
					id : resp[i].country ,
					label : resp[i].country
			}
			$scope.countryData.push(obj);
			$scope.exportedCountriesData.push(obj);
		}
	});
 	
 	
	
	HttpService.get('/locations').then(function(resp) {
		for(var i=0;i< resp.length;i++){
			var obj = {
					id : resp[i].location ,
					label : resp[i].location
			}
			$scope.locationData.push(obj);
		}
	});
	
	HttpService.get('/operators').then(function(resp) {
		for(var i=0;i< resp.length;i++){
			var obj = {
					id : resp[i].operator ,
					label : resp[i].operator
			}
			$scope.operatorData.push(obj);
		}
	});
	
	HttpService.get('/owners').then(function(resp) {
		for(var i=0;i< resp.length;i++){
			var obj = {
					id : resp[i].owner ,
					label : resp[i].owner
			}
			$scope.ownerData.push(obj);
			 
		}
	});
	
	HttpService.get('/contracts/exportcompanies').then(function(resp) {
		for(var i=0;i< resp.length;i++){
			var obj = {
					id : resp[i].company ,
					label : resp[i].company
			}
 			$scope.exportedCompaniesData.push(obj);
		}
	});
 	
	HttpService.get('/status').then(function(resp) {
		for(var i=0;i< resp.length;i++){
			var obj = {
					id : resp[i].status ,
					label : resp[i].status
			}
			$scope.statusData.push(obj);
		}
	});
	 
	HttpService.get('/type').then(function(resp) {
 		for(var i=0;i< resp.length;i++){
			var obj = {
					id : resp[i].type ,
					label : resp[i].type
			}
			$scope.typeData.push(obj);
		}
	});
	
	$scope.generateFormData = function(ary,key){
		for(var i=0;i< ary.length; i++){
			var fromkey = key+i
	 		$rootScope.capacityFilterJSON[fromkey] = ary[i].id;
 		}
	}
	 
	$rootScope.loadImports = function(item,filterType){
 		console.log("In filter CTRL",$rootScope.countryModel);
 		$scope.importedCountriesData = [];
 		$scope.importedCompaniesData =[];
 		
 		if($rootScope.exportedCountriesModel.length >0){
 			$scope.generateFormData($rootScope.exportedCountriesModel,'exportcountry');
 	 		HttpService.get("/contracts/importcountries",$rootScope.capacityFilterJSON).then(function(resp) {
 	 			for(var i=0;i< resp.length;i++){
 	 				var obj = {
 	 						id : resp[i].country ,
 	 						label : resp[i].country
 	 				}
 	 				$scope.importedCountriesData.push(obj);
 	 			}
 	 			$rootScope.capacityFilterJSON ={};
 	  		});
 		}
 		
 		if($rootScope.exportedCompaniesModel.length >0){
 			$scope.generateFormData($rootScope.exportedCompaniesModel,'exportcompany');
 	 		HttpService.get("/contracts/importcompanies",$rootScope.capacityFilterJSON).then(function(resp) {
 	 			for(var i=0;i< resp.length;i++){
 	 				var obj = {
 	 						id : resp[i].company ,
 	 						label : resp[i].company
 	 				}
 	 				$scope.importedCompaniesData.push(obj);
 	 			}
 	 			$rootScope.capacityFilterJSON ={};
 	  		});
 		}
 		$rootScope.filterObj.imports = true;
   	};
   	
   	$rootScope.onFilterDeSelect = function(item,filterType){
 		console.log("In filter CTRL",$rootScope.countryModel);
 		$scope.generateFormData($rootScope.countryModel,'exportcompany');
 		HttpService.get("/contracts/importcompanies",$rootScope.capacityFilterJSON).then(function(resp) {
 			for(var i=0;i< resp.length;i++){
 				var obj = {
 						id : resp[i].company ,
 						label : resp[i].company
 				}
 				$scope.importedCompaniesData.push(obj);
 			}
  		});
   	};
	
	
	
	/*$scope.initFilter = function(){
		console.log("in side filter initialization")
		
	};)*/
	
	
 

 });
 
 angular.module('OGAnalysis').controller('CommonCtrl', function($scope,$state,$rootScope,URL,HttpService) {
	console.log("In common ctrl");
 	console.log($state);
 
 	$scope.formData = new FormData();
 	$scope.formDataJSON = {};
 	$scope.selectedRegions = [];
	$scope.selectedCountries =[];
	$scope.selectedSectors =[];
 	
	$scope.setConfigurations = function(){
		
		if($state.current.name == "exploration"){
			$scope.url = "/exploration";
			$rootScope.filterObj = {
				regionField :false,
				countryField :false,
				locationField : false,
				operatorField : false,
				ownerField : false,
				statusField : false,
				unitsField : false,
				offshoreField : false,
				typeField :true,
				sectorField :false
			};
		}else if($state.current.name == "lng"){
			$scope.url = "/lng";
			$rootScope.filterObj = {
				regionField :true,
				countryField :true,
				locationField : true,
				operatorField : true,
				ownerField : true,
				statusField : true,
				unitsField : true,
				offshoreField : true,
				typeField :true,
				sectorField :false
			};
		}else if($state.current.name == "pipelines"){
			$scope.url = "/pipeline";
			$rootScope.filterObj = {
				regionField :false,
				countryField :false,
				locationField : false,
				operatorField : true,
				ownerField : true,
				statusField : false,
				unitsField : true,
				offshoreField : false,
				typeField :false,
				sectorField :false
			};
			$scope.columns = [
								{ title: "Name",
									  data: "name"
								} ,
								{ title: "Region",
								  data: "region"
								},
						 		{ title: "Country",
								  data: "country"
								}
								
							];
		}else if($state.current.name == "crude"){
			$scope.url = "/crudeoil";
			$rootScope.filterObj = {
				regionField :false,
				countryField :false,
				locationField : false,
				operatorField : true,
				ownerField : true,
				statusField : false,
				unitsField : true,
				offshoreField : false,
				typeField :false,
				sectorField :false
			};
		}else if($state.current.name == "naturalgas"){
			$scope.url = "/naturalgas";
			$rootScope.filterObj = {
				regionField :false,
				countryField :false,
				locationField : false,
				operatorField : true,
				ownerField : true,
				statusField : false,
				unitsField : true,
				offshoreField : false,
				typeField :false,
				sectorField :false
			};
			$scope.columns = [
								{ title: "Name",
									  data: "name"
								} ,
								{ title: "Region",
								  data: "region"
								},
						 		{ title: "Country",
								  data: "country"
								}
								
							];
		} else if($state.current.name == "refineries"){
			$scope.url = "/refinery";
			$rootScope.filterObj = {
				regionField :false,
				countryField :false,
				locationField : false,
				operatorField : true,
				ownerField : true,
				statusField : false,
				unitsField : true,
				offshoreField : false,
				typeField :false,
				sectorField :false
			};
		} else if($state.current.name == "storage"){
			$scope.url = "/storage";
			$rootScope.filterObj = {
				regionField :false,
				countryField :false,
				locationField : false,
				operatorField : true,
				ownerField : true,
				statusField : false,
				unitsField : true,
				offshoreField : false,
				typeField :false,
				sectorField :false
			};
			
			$scope.columns = [
								{ title: "Name",
									  data: "name"
								} ,
								{ title: "Region",
								  data: "region"
								},
						 		{ title: "Country",
								  data: "country"
								}
								
							];
		}  else if($state.current.name == "reports"){
			$scope.url = "/pdfReports";
			$rootScope.filterObj = {
				regionField :true,
				countryField :true,
				locationField : false,
				operatorField : false,
				ownerField : false,
				statusField : false,
				unitsField : false,
				offshoreField : false,
				typeField :false,
				sectorField :true
			};
			
			$scope.columns = [
								{ title: "Title",
								  data: "reportName"
								}  
						 	];
		} 
		
	};
	
	$scope.setDisplayPeriod = function(){
		for(var i = URL.displayFrom;i <= URL.displayTo ;i++){
			var obj = {
				id : i,
				name : i
			}
			$scope.displayPeriodList.push(obj);
		}
	}
	
	
	openModel = function(){
		console.log("in model");
		$('#myModal').modal("show")
		
	};
	
	$scope.generateFormData = function(ary,key){
		for(var i=0;i< ary.length; i++){
			var fromkey = key+i
			$scope.formData.append(fromkey, ary[i].id);
			$scope.formDataJSON[fromkey] = ary[i].id;
 		}
	}
	
	$rootScope.filterSubmit = function(){
 		
		if($rootScope.filterObj.regionField == true){
			$scope.generateFormData($rootScope.regionModel,'region');
 		}
		if($rootScope.filterObj.countryField == true){
 			$scope.generateFormData($rootScope.countryModel,'country');
 		}
		if($rootScope.filterObj.locationField == true){
		
 		}
		if($rootScope.filterObj.operatorField == true){
		
 		}
 		if($rootScope.filterObj.ownerField == true){
		
 		}
 		if($rootScope.filterObj.statusField == true){
		
 		}
 		if($rootScope.filterObj.unitsField == true){
 			if($rootScope.unitsModel.id != undefined){
 				$rootScope.unitsModel.push({id:$rootScope.unitsModel.id});
 			} 
 			$scope.generateFormData($rootScope.unitsModel,'units');
 		}
 		if($rootScope.filterObj.offshoreField == true){
 			$scope.generateFormData($rootScope.offshoreModel,'offonshore');
 		} 
 		if($rootScope.filterObj.typeField == true){
 			$scope.generateFormData($rootScope.typeModel,'type');
 		}
 		if($rootScope.filterObj.sectorField == true){
 			$scope.generateFormData($rootScope.sectorModel,'sector');
 		}
 
 		HttpService.getHttp($scope.url,$scope.formDataJSON).then(function(resp) {
 			 if($rootScope.table.inst != ""){
 				if(resp != "" && resp != undefined ){
 					resp = resp;
 				}else{
 					resp = [];
 				}
 			 	$scope.formDataJSON ={};
 				$rootScope.table.inst.clear().draw();
 				$rootScope.table.inst.rows.add(resp);
 				$rootScope.table.inst.draw();
 			 }
 	 	});
 	};
 	
 	$rootScope.onFilterSelect = function(item,filterType){
 		console.log($scope.countryModel);
 		
  	};
  	
	/*$rootScope.onFilterSelect = function(item,filterType){
 		if( filterType == 'region'){
			$scope.selectedRegions.push(item.id);
		}else if(filterType == 'country'){
			$scope.selectedCountries.push(item.id);
		}else if(filterType == 'sector'){
			$scope.selectedSectors.push(item.id);
		}
		
		for(var i=0;i< $scope.selectedRegions.length; i++){
			$scope.formData.append("region"+i, $scope.selectedRegions[i]);
		}
		
		for(var i=0;i< $scope.selectedCountries.length; i++){
			$scope.formData.append("country"+i, $scope.selectedCountries[i]);
		}
		
		for(var i=0;i< $scope.selectedSectors.length; i++){
			$scope.formData.append("sector"+i, $scope.selectedSectors[i]);
		}
		
  		HttpService.get($scope.url,$scope.formData).then(function(resp) {
  			 if($rootScope.table.inst != ""){
  				$rootScope.table.inst.clear().draw();
  				$rootScope.table.inst.rows.add(resp);
  				$rootScope.table.inst.draw();
  			 }
  	 	});
		
  	};*/
  	
	$rootScope.typeChangeFn = function(){
		 
	};
	
	$rootScope.resetFilter = function(){
		console.log("IN side common ctrl");
 		$rootScope.regionModel = [];
 		$rootScope.countryModel =[];
 	 	$rootScope.locationModel= [];
 		$rootScope.operatorModel= [];
 		$rootScope.ownerModel= [];
 		$rootScope.statusModel= [];
 		$rootScope.unitsModel= [];
 		$rootScope.offshoreModel= [];
 		$rootScope.typeModel= [];
 		$rootScope.sectorModel =[];
 		$scope.formDataJSON ={};
   	};
	
	
	$scope.init = function(){
		$scope.title = $state.current.name;
		$scope.gridDataList = [];
		$scope.displayPeriodList = [];
		$scope.url = "";
		$rootScope.tableInst = "";
		$rootScope.table = {
				inst : ""
		};
 		
		$scope.occurrenceOptions = [
            {
    			name : "Country",
    			value : "country",
    			checked :true
            },{
    			name : "Company",
    			value : "company",
    			checked :false
            },{
    			name : "Terminal",
    			value : "terminal",
    			checked :false
            }];
		 
		
		$scope.columns = [
               { title: "Region",
				  data: "region"
				},
				{ title: "Status",
				  data: "status"
				},
				{ title: "Country",
				  data: "country"
				},
				{ title: "Block No",
				  data: "blockNo"
				} 
			];
		
		$rootScope.searchFilterObj = {
				displayType:"country",
				startDate:"2016",
				endDate:"2020"
		};
		
		$scope.setConfigurations();
		$scope.setDisplayPeriod();
		$rootScope.resetFilter();
		 
		if($scope.url != ''){
			HttpService.get($scope.url).then(function(resp) {
				$scope.gridDataList = angular.copy(resp);
				var tableInst = $("#example1").DataTable({
					"columnDefs": [
					{
						// The `data` parameter refers to the data for the cell (defined by the
						// `data` option, which defaults to the column being worked with, in
						// this case `data: 0`.
						"render": function ( data, type, row ) {
							var commonHref = "";
							if($scope.url == "/pdfReports"){
								commonHref =  '<a href="pdf/reports/'+data+'">'+data +'</a>';
							}else if($scope.url == "/capacity"){
								if(data != 'Total'){
									commonHref =  '<a data-toggle="modal" href="#myModal">'+data +'</a>';
								}else{
									commonHref =  '<p>'+data+'</p>';
								}
							}else{
								 commonHref = '<a data-toggle="modal" href="#myModal">'+data +'</a>'
							}
							return commonHref;
						},
						"targets": 0
					}
					],
					scrollX: true,
					columns: $scope.columns,
					data : $scope.gridDataList
				});
				$rootScope.table.inst = tableInst;
 			});
		}
 	}

 });