 
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
        templateUrl: 'views/commonpage.html',
		controller:"CapacityCtrl"
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
	$scope.regionModel = [];
	
	/* country filter */
	$scope.countryData = [];
	$scope.countryModel = [];
	$scope.countrySettings = {enableSearch: true};
	
	
	$rootScope.filterObj = {
		regionField :true,
		countryField :true,
		locationField : true,
		operatorField : true,
		ownerField : true,
		statusField : true,
		unitsField : true,
		offshoreField : true,
		typeField :true
	};
 
	 	
 	HttpService.get('/regions').then(function(resp) {
		for(var i=0;i< resp.length;i++){
			var obj = {
					id : i ,
					label : resp[i].region
			}
			$scope.regionData.push(obj);
		}
	});
	
	HttpService.get('/countries').then(function(resp) {
		for(var i=0;i< resp.length;i++){
			var obj = {
					id : i ,
					label : resp[i].country
			}
			$scope.countryData.push(obj);
		}
	});
	 
	
	/*$scope.initFilter = function(){
		console.log("in side filter initialization")
		
	};)*/
	
	
 

 });
 
 angular.module('OGAnalysis').controller('CommonCtrl', function($scope,$state,$rootScope,URL,HttpService) {
	console.log("In common ctrl");
 	console.log($state)
	
	$scope.example1model = []; 
	$scope.example1data = [ {id: 1, label: "David"}, {id: 2, label: "Jhon"}, {id: 3, label: "Danny"}];
	
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
				typeField :true
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
				typeField :true
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
				typeField :false
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
				typeField :false
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
				typeField :false
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
				typeField :false
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
				typeField :false
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
			$scope.url = "/reports";
			$rootScope.filterObj = {
				regionField :true,
				countryField :true,
				locationField : false,
				operatorField : false,
				ownerField : false,
				statusField : false,
				unitsField : false,
				offshoreField : false,
				typeField :false
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
		} 
		
	};
	
	$scope.setDisplayPeriod = function(){
		for(var i =2000;i <= 2020 ;i++){
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
	
	$scope.init = function(){
		$scope.title = $state.current.name;
		$scope.gridDataList = [];
		$scope.displayPeriodList = [];
		$scope.url = "";
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
		
		$scope.setConfigurations();
		$scope.setDisplayPeriod();
		
		 
		if($scope.url != ''){
			HttpService.get($scope.url).then(function(resp) {
				$scope.gridDataList = angular.copy(resp);
				$("#example1").DataTable({
					"columnDefs": [
					{
						// The `data` parameter refers to the data for the cell (defined by the
						// `data` option, which defaults to the column being worked with, in
						// this case `data: 0`.
						"render": function ( data, type, row ) {
							return '<a data-toggle="modal" href="#myModal">'+data +'</a>';
						},
						"targets": 0
					}
					],
					columns: $scope.columns,
					data : $scope.gridDataList
				});
			});
		}
 	}

 });