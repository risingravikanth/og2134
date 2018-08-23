 angular.module('OGAnalysis', [
         'ui.router',
         'angularjs-dropdown-multiselect'

     ])
     .run(function($state, $rootScope) {
         $rootScope.$state = $state;
     })
     .config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

         $urlRouterProvider.otherwise('/exploration');

         $stateProvider
             .state('dashboard', {
                 url: '/dashboard',
                 templateUrl: 'views/home.html',
                 controller: "CommonCtrl"
             })
             .state('exploration', {
                 url: '/exploration',
                 templateUrl: 'views/exploration.html',
                 controller: "ExplorationCtrl"
             })

             .state('lng', {
                 url: '/lng',
                 templateUrl: 'views/commonpage.html',
                 controller: "CommonCtrl"
             })
             .state('lng/capacity', {
                 url: '/lng/capacity',
                 templateUrl: 'views/capacity.html',
                 controller: "CapacityCtrl"
             })
             .state('lng/infrastructure', {
                 url: '/lng/infrastructure',
                 templateUrl: 'views/infrastructure.html',
                 controller: "InfrastructureCtrl"
             })
             .state('lng/contracts', {
                 url: '/lng/contracts',
                 templateUrl: 'views/contracts.html',
                 controller: "ContractsCtrl"
             })
             .state('lng/supplydemand', {
                 url: '/lng/supplydemand',
                 templateUrl: 'views/supplydemand.html',
                 controller: "SupplyDemandCtrl"
             })
             .state('lng/smallscale', {
                 url: '/lng/smallscale',
                 templateUrl: 'views/smallscale.html',
                 controller: "SmallScaleCtrl"
             })




             .state('pipelines', {
                 url: '/pipelines',
                 templateUrl: 'views/commonpage.html',
                 controller: "CommonCtrl"
             })
             .state('pipeline/domestic', {
                 url: '/pipeline/domestic',
                 templateUrl: 'views/pipelinesDomestic.html',
                 controller: "PipelinesDomesticCtrl"
             })
             .state('pipeline/trans-national', {
                 url: '/pipeline/trans-national',
                 templateUrl: 'views/pipelinesTransNational.html',
                 controller: "PipelinesTransNationalCtrl"
             })




             .state('refineries/capacity', {
                 url: '/refineries/capacity',
                 templateUrl: 'views/refineries.html',
                 controller: "RefineriesCtrl"
             })
             .state('refineries/infrastructure', {
                 url: '/refineries/infrastructure',
                 templateUrl: 'views/refineriesInfra.html',
                 controller: "RefineriesInfraCtrl"
             })


             .state('storage', {
                 url: '/storage',
                 templateUrl: 'views/commonpage.html',
                 controller: "CommonCtrl"
             })
             .state('storage/capacity', {
                 url: '/storage/capacity',
                 templateUrl: 'views/storageCapacity.html',
                 controller: "StorageCapacityCtrl"
             })
             .state('storage/infrastructure', {
                 url: '/storage/infrastructure',
                 templateUrl: 'views/storageInfra.html',
                 controller: "StorageInfraCtrl"
             })


             .state('production', {
                 url: '/production',
                 templateUrl: 'views/commonpage.html',
                 controller: "CommonCtrl"
             })
             .state('crude', {
                 url: '/crude',
                 templateUrl: 'views/commonpage.html',
                 controller: "CommonCtrl"
             })
             .state('naturalgas', {
                 url: '/naturalgas',
                 templateUrl: 'views/commonpage.html',
                 controller: "CommonCtrl"
             })
             .state('production/asset', {
                 url: '/production/asset',
                 templateUrl: 'views/productionAsset.html',
                 controller: "ProductionAssetCtrl"
             })
             .state('production/company', {
                 url: '/production/company',
                 templateUrl: 'views/productionCompany.html',
                 controller: "ProductionCompanyCtrl"
             })



             .state('reports', {
                 url: '/reports',
                 templateUrl: 'views/commonpage.html',
                 controller: "CommonCtrl"
             })
             .state('login', {
                 url: '/login',
                 templateUrl: 'views/login.html',
                 controller: "CommonCtrl"
             })
     }]);


 angular.module('OGAnalysis').controller('FilterCtrl', function($scope, $state, $rootScope, URL, HttpService, $location) {
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
     $rootScope.onFilterSelect = function(item, filterType) {
         //console.log($scope.countryModel);

     };

     $(".right-filter")
         .mouseleave(function() {
             $("body").click();
         });

     $scope.sortedOrder = function(array) {
         var updatedArray = [];
         updatedArray = array.sort(function(a, b) {
             if (a.id < b.id) return -1;
             if (a.id > b.id) return 1;
             return 0;
         });
         return updatedArray;
     }
     //path constants
     $scope.explorationPath='/exploration';
     $scope.lngCapacityPath='/lng/capacity';
     $scope.lngInfraPath='/lng/infrastructure';
     $scope.lngSupplyDemandPath='/lng/supplydemand';
     $scope.lngSmallScalePath='/lng/smallscale';
     $scope.pipelineDomesticPath='/pipeline/domestic';
     $scope.pipelineTransNationalPath='/pipeline/trans-national';
     $scope.productionAssetPath='/production/asset';
     $scope.productionCompanyPath='/production/company';
     $scope.refineriesCapacityPath='/refineries/capacity';
     $scope.refineriesInfraPath='/refineries/infrastructure';
     $scope.storageCapacityPath='/storage/capacity';
     $scope.storageInfraPath='/storage/infrastructure';
     $scope.reportsPath='/reports';
     
     $scope.region='region';
     $scope.country='country';
     $scope.basin='basin';
     $scope.operator='operator';
     $scope.owner='owner';
     $scope.status='status';
     $scope.location='location';
     $scope.companyData='company';
     $scope.technologyProvider='technologyProvider';
     $scope.technology='technology';
     $scope.commodity='commodity';
     $scope.startPoint='startPoint';
     $scope.endPoint='endPoint';

     /* region filter */
     $scope.regionData = [];
     $rootScope.regionModel = [];    
     $scope.selectAllClicked=false;
     $scope.selectAllCount=0;
     $scope.regionSettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.regionEvents = {
         
         onItemSelect: function(item) {     
        	         	 
        	 if($scope.selectAllClicked==false)
        	 {
        		 $scope.clearFilters($scope.region,$location.path());
        		 $scope.loadFiltersForRegion($scope.region,$location.path());
        		 console.log('Select:'+$scope.selectAllCount);
        	 }	 
        	 else if($scope.selectAllClicked==true)
        	 {        		 
        		 console.log('Select:'+$scope.selectAllCount); 
        		 $scope.selectAllCount=$scope.selectAllCount+1;
        	 }
        	 if($scope.selectAllCount == $scope.regionData.length)
        	 {
        		 $scope.selectAllCount=0;
        		 $scope.selectAllClicked=false;
        		 $scope.clearFilters($scope.region,$location.path());
        		 $scope.loadFiltersForRegion($scope.region,$location.path());
        		 console.log('Select:'+$scope.selectAllCount);        		 
        	 }       	
             $rootScope.onFilterSelect(item, $scope.region);
         },
         onItemDeselect: function(item) {     
        	 console.log('Deselect:'+$location.path());
        	 $scope.clearFilters($scope.region,$location.path());
    		 $scope.loadFiltersForRegion($scope.region,$location.path());
        	 $rootScope.onFilterSelect(item, $scope.region);
         },
         onSelectAll: function(item) {     
        	 console.log('onSelectAll:'+$location.path());
        	 console.log('Select:'+$scope.selectAllCount);
        	 $scope.selectAllCount=0;
        	 $scope.selectAllClicked=true;        	         	
             $rootScope.onFilterSelect(item, $scope.region);
         },
         onDeselectAll: function(item) {     
        	 console.log('onDeselectAll:'+$location.path());        	 
        	 if($scope.selectAllClicked!=true)
        	 {
        		 $rootScope.regionModel=[];//when we deselectAll the model is not becoming empty.So doing it here.
        		 $scope.clearFilters($scope.region,$location.path());
        		 $scope.loadFiltersForRegion($scope.region,$location.path());
        	 }	         	 
             $rootScope.onFilterSelect(item, $scope.region)
         }
     };
     
     /* country filter */
     $scope.countryData = [];
     $rootScope.countryModel = [];
     $scope.countrySettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.countryEvents = {
         onItemSelect: function(item) {        	 	
        	 
        	 if($scope.selectAllClicked==false)
        	 {
        		 $scope.clearFilters($scope.country,$location.path());
        		 $scope.loadFiltersForCountry($scope.country,$location.path());
        		 console.log('Select:'+$scope.selectAllCount);
        	 }	 
        	 else if($scope.selectAllClicked==true)
        	 {        		 
        		 console.log('Select:'+$scope.selectAllCount); 
        		 $scope.selectAllCount=$scope.selectAllCount+1;
        	 }
        	 if($scope.selectAllCount == $scope.countryData.length)
        	 {
        		 $scope.selectAllCount=0;
        		 $scope.selectAllClicked=false;
        		 $scope.clearFilters($scope.country,$location.path());
        		 $scope.loadFiltersForCountry($scope.country,$location.path());
        		 console.log('Select:'+$scope.selectAllCount);        		 
        	 } 
             $rootScope.onFilterSelect(item,$scope.country);
         },
     onItemDeselect: function(item) {         	 
    	 $scope.clearFilters($scope.country,$location.path());
		 $scope.loadFiltersForCountry($scope.country,$location.path());
    	 $rootScope.onFilterSelect(item, $scope.country);
     },
     onSelectAll: function(item) {     
    	 
    	 $scope.selectAllCount=0;
    	 $scope.selectAllClicked=true;        
         $rootScope.onFilterSelect(item, $scope.country);
     },
     onDeselectAll: function(item) {     
    	 
    	 if($scope.selectAllClicked!=true)
    	 {
    		 $rootScope.countryModel=[];//when we deselectAll the model is not becoming empty.So doing it here.
    		 $scope.clearFilters($scope.country,$location.path());
    		 $scope.loadFiltersForCountry($scope.country,$location.path());
    	 }	  
         $rootScope.onFilterSelect(item, $scope.country)
     }
     };
     
     /* basinField filter */
     $scope.basinData = [];
     $rootScope.basinModel = [];
     $scope.basinSettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.basinEvents = {
         onItemSelect: function(item) {             
             if($scope.selectAllClicked==false)
             {
            	 $scope.clearFilters($scope.basin,$location.path());
            	 $scope.loadFiltersForBasin($scope.basin,$location.path());
             }
             else if($scope.selectAllClicked==true)
             {
            	 $scope.selectAllCount=$scope.selectAllCount+1; 
             }
             if($scope.selectAllCount == $scope.basinData.length)
        	 {
        		 $scope.selectAllCount=0;
        		 $scope.selectAllClicked=false;
        		 $scope.clearFilters($scope.basin,$location.path());
        		 $scope.loadFiltersForBasin($scope.basin,$location.path());
        		 console.log('Select:'+$scope.selectAllCount);        		 
        	 }       	
             $rootScope.onFilterSelect(item, $scope.basin)
         },
         onItemDeselect: function(item) {             	 
        	 $scope.clearFilters($scope.basin,$location.path());
    		 $scope.loadFiltersForBasin($scope.basin,$location.path());
        	 $rootScope.onFilterSelect(item, $scope.basin);
         },
         onSelectAll: function(item) {             	         	
        	 $scope.selectAllCount=0;
        	 $scope.selectAllClicked=true;        	         	
             $rootScope.onFilterSelect(item, $scope.basin);
         },
         onDeselectAll: function(item) {     
        	 console.log('onDeselectAll:'+$location.path());        	 
        	 if($scope.selectAllClicked!=true)
        	 {
        		 $rootScope.basinModel=[];//when we deselectAll the model is not becoming empty.So doing it here.
        		 $scope.clearFilters($scope.basin,$location.path());
        		 $scope.loadFiltersForBasin($scope.basin,$location.path());
        	 }	         	 
             $rootScope.onFilterSelect(item, $scope.basin)
         }
     };
    
     
     /*operator*/
     $scope.operatorData = [];
     $rootScope.operatorModel = [];
     $scope.operatorSettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.operatorEvents={
    		 onItemSelect: function(item) {
    			 if($scope.selectAllClicked==false)
            	 {
            		 $scope.clearFilters($scope.operator,$location.path());
            		 $scope.loadFiltersForOperator($scope.operator,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);
            	 }	 
    			 else if($scope.selectAllClicked==true)
    			 {
    				 $scope.selectAllCount=$scope.selectAllCount+1;
    			 }
    			 if($scope.selectAllCount == $scope.operatorData.length)
            	 {
            		 $scope.selectAllCount=0;
            		 $scope.selectAllClicked=false;
            		 $scope.clearFilters($scope.operator,$location.path());
            		 $scope.loadFiltersForOperator($scope.operator,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);        		 
            	 }
    			 $rootScope.onFilterSelect(item, $scope.operator)
    		 },
    		 onItemDeselect: function(item) {     
            	 console.log('Deselect:'+$location.path());
            	 $scope.clearFilters($scope.operator,$location.path());
        		 $scope.loadFiltersForOperator($scope.operator,$location.path());
            	 $rootScope.onFilterSelect(item, $scope.operator);
             },
             onSelectAll: function(item) {                 	 
            	 console.log('Select:'+$scope.selectAllCount);
            	 $scope.selectAllCount=0;
            	 $scope.selectAllClicked=true;        	         	
                 $rootScope.onFilterSelect(item, $scope.operator)
             },
             onDeselectAll: function(item) {                 	         	
            	 if($scope.selectAllClicked!=true)
            	 {
            		 $rootScope.operatorModel=[];//when we deselectAll the model is not becoming empty.So doing it here.
            		 $scope.clearFilters($scope.operator,$location.path());
            		 $scope.loadFiltersForOperator($scope.operator,$location.path());
            	 }	         	 
                 $rootScope.onFilterSelect(item, $scope.operator);
             }
     };
     /* owner*/
     $scope.ownerData = [];
     $rootScope.ownerModel = [];
     $scope.ownerSettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.ownerEvents ={
    		 onItemSelect: function(item) {                 	 
            	 
            	 if($scope.selectAllClicked==false)
            	 {
            		 $scope.clearFilters($scope.owner,$location.path());
            		 $scope.loadFiltersForOwner($scope.owner,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);
            	 }	 
            	 else if($scope.selectAllClicked==true)
            	 {        		             		 
            		 $scope.selectAllCount=$scope.selectAllCount+1;
            	 }
            	 if($scope.selectAllCount == $scope.ownerData.length)
            	 {
            		 $scope.selectAllCount=0;
            		 $scope.selectAllClicked=false;
            		 $scope.clearFilters($scope.owner,$location.path());
            		 $scope.loadFiltersForOwner($scope.owner,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);        		 
            	 }       	
                 $rootScope.onFilterSelect(item, $scope.owner);
             },
             onItemDeselect: function(item) {     
            	 console.log('Deselect:'+$location.path());
            	 $scope.clearFilters($scope.owner,$location.path());
        		 $scope.loadFiltersForOwner($scope.owner,$location.path());
            	 $rootScope.onFilterSelect(item, $scope.owner);
             },
             onSelectAll: function(item) {     
            	 console.log('onSelectAll:'+$location.path());
            	 console.log('Select:'+$scope.selectAllCount);
            	 $scope.selectAllCount=0;
            	 $scope.selectAllClicked=true;        	         	
                 $rootScope.onFilterSelect(item, $scope.owner);
             },
             onDeselectAll: function(item) {     
            	 console.log('onDeselectAll:'+$location.path());        	 
            	 if($scope.selectAllClicked!=true)
            	 {
            		 $rootScope.ownerModel=[];//when we deselectAll the model is not becoming empty.So doing it here.
            		 $scope.clearFilters($scope.owner,$location.path());
            		 $scope.loadFiltersForOwner($scope.owner,$location.path());
            	 }	         	 
                 $rootScope.onFilterSelect(item, $scope.owner);
             }
     };
     /* Status filter */
     $scope.statusData = [];
     $rootScope.statusModel = [];
     $scope.statusSettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.statusEvents={
    		 onItemSelect: function(item) {     
            	             	 
            	 if($scope.selectAllClicked==false)
            	 {
            		 $scope.clearFilters($scope.status,$location.path());
            		 $scope.loadFiltersForStatus($scope.status,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);
            	 }	 
            	 else if($scope.selectAllClicked==true)
            	 {        		             		 
            		 $scope.selectAllCount=$scope.selectAllCount+1;
            	 }
            	 if($scope.selectAllCount == $scope.statusData.length)
            	 {
            		 $scope.selectAllCount=0;
            		 $scope.selectAllClicked=false;
            		 $scope.clearFilters($scope.status,$location.path());
            		 $scope.loadFiltersForStatus($scope.status,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);        		 
            	 }       	
                 $rootScope.onFilterSelect(item, $scope.status);
             },
             onItemDeselect: function(item) {     
            	 console.log('Deselect:'+$location.path());
            	 $scope.clearFilters($scope.status,$location.path());
        		 $scope.loadFiltersForStatus($scope.status,$location.path());
            	 $rootScope.onFilterSelect(item, $scope.status);
             },
             onSelectAll: function(item) {     
            	 console.log('onSelectAll:'+$location.path());
            	 console.log('Select:'+$scope.selectAllCount);
            	 $scope.selectAllCount=0;
            	 $scope.selectAllClicked=true;        	         	
                 $rootScope.onFilterSelect(item, $scope.status);
             },
             onDeselectAll: function(item) {     
            	 console.log('onDeselectAll:'+$location.path());        	 
            	 if($scope.selectAllClicked!=true)
            	 {
            		 $rootScope.statusModel=[];//when we deselectAll the model is not becoming empty.So doing it here.
            		 $scope.clearFilters($scope.status,$location.path());
            		 $scope.loadFiltersForStatus($scope.status,$location.path());
            	 }	         	 
                 $rootScope.onFilterSelect(item, $scope.status);
             }
     };
     
     /*units filter*/
     $scope.unitsData=[];
     $rootScope.unitsModel = [];
//     $scope.unitsData = [{
//         id: 'BCF',
//         label: "BCF"
//     }];
     $scope.unitsSettings = {
         selectionLimit: 1
     };
     
     /*type filter*/
     $rootScope.typeModel = [];
     $scope.typeData = [];
     $scope.typeSettings = {
         enableSearch: true,
         scrollable: true
     };
     
     /*location */
     $scope.locationData = [];
     $rootScope.locationModel = [];
     $scope.locationSettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.locationEvents= {
    		 onItemSelect: function(item) {     
	         	 
            	 if($scope.selectAllClicked==false)
            	 {
            		 $scope.clearFilters($scope.location,$location.path());
            		 $scope.loadFiltersForLocation($scope.location,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);
            	 }	 
            	 else if($scope.selectAllClicked==true)
            	 {        		 
            		 console.log('Select:'+$scope.selectAllCount); 
            		 $scope.selectAllCount=$scope.selectAllCount+1;
            	 }
            	 if($scope.selectAllCount == $scope.locationData.length)
            	 {
            		 $scope.selectAllCount=0;
            		 $scope.selectAllClicked=false;
            		 $scope.clearFilters($scope.location,$location.path());
            		 $scope.loadFiltersForLocation($scope.location,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);        		 
            	 }       	
                 $rootScope.onFilterSelect(item, $scope.location);
             },
             onItemDeselect: function(item) {     
            	 console.log('Deselect:'+$location.path());
            	 $scope.clearFilters($scope.location,$location.path());
        		 $scope.loadFiltersForLocation($scope.location,$location.path());
            	 $rootScope.onFilterSelect(item, $scope.location);
             },
             onSelectAll: function(item) {     
            	 console.log('onSelectAll:'+$location.path());
            	 console.log('Select:'+$scope.selectAllCount);
            	 $scope.selectAllCount=0;
            	 $scope.selectAllClicked=true;        	         	
                 $rootScope.onFilterSelect(item, $scope.location);
             },
             onDeselectAll: function(item) {     
            	 console.log('onDeselectAll:'+$location.path());        	 
            	 if($scope.selectAllClicked!=true)
            	 {
            		 $rootScope.locationModel=[];//when we deselectAll the model is not becoming empty.So doing it here.
            		 $scope.clearFilters($scope.location,$location.path());
            		 $scope.loadFiltersForLocation($scope.location,$location.path());
            	 }	         	 
                 $rootScope.onFilterSelect(item, $scope.region)
             }
     }
     /*offshore filter*/
     $rootScope.offshoreModel = [];
//     $scope.offshoreData = [{
//         id: 'Offshore',
//         label: "Offshore"
//     }, {
//         id: 'Onshore',
//         label: "Onshore"
//     }]; //Timebeing Removed as client don't want{id: 'Not_Decided', label: "Not Decided"},
//     $scope.offshoreData = $scope.sortedOrder($scope.offshoreData);
     
     /* company  filter */
     $scope.companyData = [];
     $rootScope.companyModel = [];
     $scope.companySettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.companyEvents = {
    		 onItemSelect: function(item) {     
	         	 
            	 if($scope.selectAllClicked==false)
            	 {
            		 $scope.clearFilters($scope.company,$location.path());
            		 $scope.loadFiltersForCompany($scope.company,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);
            	 }	 
            	 else if($scope.selectAllClicked==true)
            	 {        		 
            		 console.log('Select:'+$scope.selectAllCount); 
            		 $scope.selectAllCount=$scope.selectAllCount+1;
            	 }
            	 if($scope.selectAllCount == $scope.companyData.length)
            	 {
            		 $scope.selectAllCount=0;
            		 $scope.selectAllClicked=false;
            		 $scope.clearFilters($scope.company,$location.path());
            		 $scope.loadFiltersForCompany($scope.company,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);        		 
            	 }       	
                 $rootScope.onFilterSelect(item, $scope.company);
             },
             onItemDeselect: function(item) {     
            	 console.log('Deselect:'+$location.path());
            	 $scope.clearFilters($scope.company,$location.path());
        		 $scope.loadFiltersForCompany($scope.company,$location.path());
            	 $rootScope.onFilterSelect(item, $scope.company);
             },
             onSelectAll: function(item) {     
            	 console.log('onSelectAll:'+$location.path());
            	 console.log('Select:'+$scope.selectAllCount);
            	 $scope.selectAllCount=0;
            	 $scope.selectAllClicked=true;        	         	
                 $rootScope.onFilterSelect(item, $scope.company);
             },
             onDeselectAll: function(item) {     
            	 console.log('onDeselectAll:'+$location.path());        	 
            	 if($scope.selectAllClicked!=true)
            	 {
            		 $rootScope.companyModel=[];//when we deselectAll the model is not becoming empty.So doing it here.
            		 $scope.clearFilters($scope.company,$location.path());
            		 $scope.loadFiltersForCompany($scope.company,$location.path());
            	 }	         	 
                 $rootScope.onFilterSelect(item, $scope.company)
             }
     };
     
     /* technologyProvider   filter */
     $scope.technologyProviderData = [];
     $rootScope.technologyProviderModel = [];
     $scope.technologyProviderSettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.technologyProviderEvents = {
    		 onItemSelect: function(item) {     
	         	 
            	 if($scope.selectAllClicked==false)
            	 {
            		 $scope.clearFilters($scope.technologyProvider,$location.path());
            		 $scope.loadFiltersForTechnologyProvider($scope.technologyProvider,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);
            	 }	 
            	 else if($scope.selectAllClicked==true)
            	 {        		 
            		 console.log('Select:'+$scope.selectAllCount); 
            		 $scope.selectAllCount=$scope.selectAllCount+1;
            	 }
            	 if($scope.selectAllCount == $scope.technologyProviderData.length)
            	 {
            		 $scope.selectAllCount=0;
            		 $scope.selectAllClicked=false;
            		 $scope.clearFilters($scope.technologyProvider,$location.path());
            		 $scope.loadFiltersForTechnologyProvider($scope.technologyProvider,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);        		 
            	 }       	
                 $rootScope.onFilterSelect(item, $scope.technologyProvider);
             },
             onItemDeselect: function(item) {     
            	 console.log('Deselect:'+$location.path());
            	 $scope.clearFilters($scope.technologyProvider,$location.path());
        		 $scope.loadFiltersForTechnologyProvider($scope.technologyProvider,$location.path());
            	 $rootScope.onFilterSelect(item, $scope.technologyProvider);
             },
             onSelectAll: function(item) {     
            	 console.log('onSelectAll:'+$location.path());
            	 console.log('Select:'+$scope.selectAllCount);
            	 $scope.selectAllCount=0;
            	 $scope.selectAllClicked=true;        	         	
                 $rootScope.onFilterSelect(item, $scope.technologyProvider);
             },
             onDeselectAll: function(item) {     
            	 console.log('onDeselectAll:'+$location.path());        	 
            	 if($scope.selectAllClicked!=true)
            	 {
            		 $rootScope.technologyProviderModel=[];//when we deselectAll the model is not becoming empty.So doing it here.
            		 $scope.clearFilters($scope.technologyProvider,$location.path());
            		 $scope.loadFiltersForTechnologyProvider($scope.technologyProvider,$location.path());
            	 }	         	 
                 $rootScope.onFilterSelect(item, $scope.technologyProvider)
             }
     };

     /* technologyField   filter */
     $scope.technologyData = [];
     $rootScope.technologyModel = [];
     $scope.technologySettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.technologyEvents = {
    		 onItemSelect: function(item) {     
	         	 
            	 if($scope.selectAllClicked==false)
            	 {
            		 $scope.clearFilters($scope.technology,$location.path());
            		 $scope.loadFiltersForTechnology($scope.technology,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);
            	 }	 
            	 else if($scope.selectAllClicked==true)
            	 {        		 
            		 console.log('Select:'+$scope.selectAllCount); 
            		 $scope.selectAllCount=$scope.selectAllCount+1;
            	 }
            	 if($scope.selectAllCount == $scope.technologyData.length)
            	 {
            		 $scope.selectAllCount=0;
            		 $scope.selectAllClicked=false;
            		 $scope.clearFilters($scope.technology,$location.path());
            		 $scope.loadFiltersForTechnology($scope.technology,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);        		 
            	 }       	
                 $rootScope.onFilterSelect(item, $scope.technology);
             },
             onItemDeselect: function(item) {     
            	 console.log('Deselect:'+$location.path());
            	 $scope.clearFilters($scope.technology,$location.path());
        		 $scope.loadFiltersForTechnology($scope.technology,$location.path());
            	 $rootScope.onFilterSelect(item, $scope.technology);
             },
             onSelectAll: function(item) {     
            	 console.log('onSelectAll:'+$location.path());
            	 console.log('Select:'+$scope.selectAllCount);
            	 $scope.selectAllCount=0;
            	 $scope.selectAllClicked=true;        	         	
                 $rootScope.onFilterSelect(item, $scope.Technology);
             },
             onDeselectAll: function(item) {     
            	 console.log('onDeselectAll:'+$location.path());        	 
            	 if($scope.selectAllClicked!=true)
            	 {
            		 $rootScope.technologyModel=[];//when we deselectAll the model is not becoming empty.So doing it here.
            		 $scope.clearFilters($scope.technology,$location.path());
            		 $scope.loadFiltersForTechnology($scope.technology,$location.path());
            	 }	         	 
                 $rootScope.onFilterSelect(item, $scope.region)
             }
     };
     
     /*pipeline commodity field */
     $scope.pdCommodityData = [];
     $rootScope.pdCommodityModel = [];
     $scope.pdCommoditySettings = {
         selectionLimit: 1,
         enableSearch: true,
         scrollable: true
     };
     $scope.pdCommodityEvents ={
    		 onItemSelect: function(item) {     
	         	 
            	 if($scope.selectAllClicked==false)
            	 {
            		 $scope.clearFilters($scope.commodity,$location.path());
            		 $scope.loadFiltersForCommodity($scope.commodity,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);
            	 }	 
            	 else if($scope.selectAllClicked==true)
            	 {        		 
            		 console.log('Select:'+$scope.selectAllCount); 
            		 $scope.selectAllCount=$scope.selectAllCount+1;
            	 }
            	 if($scope.selectAllCount == $scope.pdCommodityData.length)//For commodity we made only single Selection from dropdown.so this condition is required but i kept it intentionally
            	 {
            		 $scope.selectAllCount=0;
            		 $scope.selectAllClicked=false;
            		 $scope.clearFilters($scope.commodity,$location.path());
            		 $scope.loadFiltersForCommodity($scope.commodity,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);        		 
            	 }       	
                 $rootScope.onFilterSelect(item, $scope.commodity);
             },
             onItemDeselect: function(item) {     
            	 console.log('Deselect:'+$location.path());
            	 $scope.clearFilters($scope.commodity,$location.path());
        		 $scope.loadFiltersForCommodity($scope.commodity,$location.path());
            	 $rootScope.onFilterSelect(item, $scope.commodity);
             },
             onSelectAll: function(item) {     
            	 console.log('onSelectAll:'+$location.path());
            	 console.log('Select:'+$scope.selectAllCount);
            	 $scope.selectAllCount=0;
            	 $scope.selectAllClicked=true;        	         	
                 $rootScope.onFilterSelect(item, $scope.commodity);
             },
             onDeselectAll: function(item) {     
            	 console.log('onDeselectAll:'+$location.path());        	 
            	 if($scope.selectAllClicked!=true)
            	 {
            		 $rootScope.pdCommodityModel=[];//when we deselectAll the model is not becoming empty.So doing it here.
            		 $scope.clearFilters($scope.commodity,$location.path());
            		 $scope.loadFiltersForCommodity($scope.commodity,$location.path());
            	 }	         	 
                 $rootScope.onFilterSelect(item, $scope.commodity)
             }
     };


     /*pipeline statpoint field */
     $scope.pdStartPointData = [];
     $rootScope.pdStartPointModel = [];
     $scope.pdStartPointSettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.pdStartPointEvents ={
    		 onItemSelect: function(item) {     
	         	 
            	 if($scope.selectAllClicked==false)
            	 {
            		 $scope.clearFilters($scope.startPoint,$location.path());
            		 $scope.loadFiltersForStartPoint($scope.startPoint,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);
            	 }	 
            	 else if($scope.selectAllClicked==true)
            	 {        		 
            		 console.log('Select:'+$scope.selectAllCount); 
            		 $scope.selectAllCount=$scope.selectAllCount+1;
            	 }
            	 if($scope.selectAllCount == $scope.pdStartPointData.length)
            	 {
            		 $scope.selectAllCount=0;
            		 $scope.selectAllClicked=false;
            		 $scope.clearFilters($scope.startPoint,$location.path());
            		 $scope.loadFiltersForStartPoint($scope.startPoint,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);        		 
            	 }       	
                 $rootScope.onFilterSelect(item, $scope.startPoint);
             },
             onItemDeselect: function(item) {     
            	 console.log('Deselect:'+$location.path());
            	 $scope.clearFilters($scope.startPoint,$location.path());
        		 $scope.loadFiltersForStartPoint($scope.startPoint,$location.path());
            	 $rootScope.onFilterSelect(item, $scope.startPoint);
             },
             onSelectAll: function(item) {     
            	 console.log('onSelectAll:'+$location.path());
            	 console.log('Select:'+$scope.selectAllCount);
            	 $scope.selectAllCount=0;
            	 $scope.selectAllClicked=true;        	         	
                 $rootScope.onFilterSelect(item, $scope.startPoint);
             },
             onDeselectAll: function(item) {     
            	 console.log('onDeselectAll:'+$location.path());        	 
            	 if($scope.selectAllClicked!=true)
            	 {
            		 $rootScope.pdStartPointModel=[];//when we deselectAll the model is not becoming empty.So doing it here.
            		 $scope.clearFilters($scope.startPoint,$location.path());
            		 $scope.loadFiltersForStartPoint($scope.startPoint,$location.path());
            	 }	         	 
                 $rootScope.onFilterSelect(item, $scope.startPoint);
             }
     };
     /*pipeline endpoint field */
     $scope.pdEndPointData = [];
     $rootScope.pdEndPointModel = [];
     $scope.pdEndPointSettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.pdEndPointEvents ={
    		 onItemSelect: function(item) {     
	         	 
            	 if($scope.selectAllClicked==false)
            	 {
            		 $scope.clearFilters($scope.endPoint,$location.path());
            		 $scope.loadFiltersForEndPoint($scope.endPoint,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);
            	 }	 
            	 else if($scope.selectAllClicked==true)
            	 {        		 
            		 console.log('Select:'+$scope.selectAllCount); 
            		 $scope.selectAllCount=$scope.selectAllCount+1;
            	 }
            	 if($scope.selectAllCount == $scope.pdEndPointData.length)
            	 {
            		 $scope.selectAllCount=0;
            		 $scope.selectAllClicked=false;
            		 $scope.clearFilters($scope.endPoint,$location.path());
            		 $scope.loadFiltersForEndPoint($scope.endPoint,$location.path());
            		 console.log('Select:'+$scope.selectAllCount);        		 
            	 }       	
                 $rootScope.onFilterSelect(item, $scope.endPoint);
             },
             onItemDeselect: function(item) {     
            	 console.log('Deselect:'+$location.path());
            	 $scope.clearFilters($scope.endPoint,$location.path());
        		 $scope.loadFiltersForEndPoint($scope.endPoint,$location.path());
            	 $rootScope.onFilterSelect(item, $scope.endPoint);
             },
             onSelectAll: function(item) {     
            	 console.log('onSelectAll:'+$location.path());
            	 console.log('Select:'+$scope.selectAllCount);
            	 $scope.selectAllCount=0;
            	 $scope.selectAllClicked=true;        	         	
                 $rootScope.onFilterSelect(item, $scope.endPoint);
             },
             onDeselectAll: function(item) {     
            	 console.log('onDeselectAll:'+$location.path());        	 
            	 if($scope.selectAllClicked!=true)
            	 {
            		 $rootScope.pdEndPointModel=[];//when we deselectAll the model is not becoming empty.So doing it here.
            		 $scope.clearFilters($scope.endPoint,$location.path());
            		 $scope.loadFiltersForEndPoint($scope.endPoint,$location.path());
            	 }	         	 
                 $rootScope.onFilterSelect(item, $scope.endPoint);
             }
     };
     $rootScope.assetTypeModel = [];
     $scope.assetTypeData = [{
         id: 'both',
         label: "Both"
     }, {
         id: 'gas',
         label: "Gas"
     }, {
         id: 'oil',
         label: "Oil"
     }];
     $scope.assetTypeSettings = {
         selectionLimit: 1,
         enableSearch: true,
         scrollable: true
     };

     /*sector filter*/
     $rootScope.assetUnitModel = [];
     $scope.assetUnitData = [{
         id: 'MToe',
         label: "MToe"
     }, {
         id: 'MBoE',
         label: "MBoE"
     }, {
         id: 'BcMNG',
         label: "BcMNG"
     }];
     $scope.assetUnitSettings = {
         selectionLimit: 1,
         enableSearch: true,
         scrollable: true
     };

     /*asset counry field */
     $scope.assetCountryData = [];
     $rootScope.assetCountryModel = [];
     $scope.assetCountrySettings = {
         selectionLimit: 1,
         enableSearch: true,
         scrollable: true
     };  
     
     /*sector filter*/
     $scope.sectorData=[];
     $rootScope.sectorModel = [];
//     $scope.sectorData = [{
//         id: 'Exploration',
//         label: "Exploration"
//     }, {
//         id: 'LNG',
//         label: "LNG"
//     }, {
//         id: 'Pipeline',
//         label: "Pipe line"
//     }, {
//         id: 'Refinery',
//         label: "Refinery"
//     }, {
//         id: 'Storage',
//         label: "Storage"
//     }];
     $scope.sectorSettings = {
         selectionLimit: 1
     };
//     $scope.sectorData = $scope.sortedOrder($scope.sectorData);
     $scope.sectorEvents = {
         onItemSelect: function(item) {
             $rootScope.onFilterSelect(item, 'sector')
         }
     };

    $rootScope.resetFilters = function(filter,path){
    	$scope.clearFilters(filter, path);
    } 
    $scope.clearFilters = function(filter,path)
    {
    	if(path==$scope.explorationPath)
    	{
    		if(filter==$scope.region)
    		{
    			$scope.countryData = [];
        		$rootScope.countryModel=[];
        		$scope.basinData = [];
           	    $rootScope.basinModel = [];
           	    $scope.operatorData =[];
           	    $rootScope.operatorModel=[];
           	    $scope.ownerData =[];
        	    $rootScope.ownerModel=[];
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];
        	    $scope.typeData=[];
        	    $rootScope.typeModel=[];
    		}	
    		else if(filter==$scope.country)
    		{
    			$scope.basinData = [];
           	    $rootScope.basinModel = [];
           	    $scope.operatorData =[];
           	    $rootScope.operatorModel=[];
           	    $scope.ownerData =[];
        	    $rootScope.ownerModel=[];
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];
        	    $scope.typeData=[];
        	    $rootScope.typeModel=[];
    		}
    		else if(filter==$scope.basin)
    		{
    			$scope.operatorData =[];
           	    $rootScope.operatorModel=[];
           	    $scope.ownerData =[];
        	    $rootScope.ownerModel=[];
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];
        	    $scope.typeData=[];
        	    $rootScope.typeModel=[];
    		}
    		else if(filter==$scope.operator)
    		{    			
           	    $scope.ownerData =[];
        	    $rootScope.ownerModel=[];
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];
        	    $scope.typeData=[];
        	    $rootScope.typeModel=[];
    		}
    		else if(filter==$scope.owner)
    		{    			           	    
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];
        	    $scope.typeData=[];
        	    $rootScope.typeModel=[];
    		}
    		else if(filter==$scope.status)
    		{
    			$scope.typeData=[];
        	    $rootScope.typeModel=[];
    		}
       	    
    	}    	
    	if(path==$scope.lngCapacityPath || path==$scope.lngInfraPath)
    	{
    		if(filter==$scope.region)
    		{
    			$scope.countryData = [];
        		$rootScope.countryModel=[];        		
        		$scope.locationData = [];
           	    $rootScope.locationModel = [];
           	    $scope.operatorData =[];
           	    $rootScope.operatorModel=[];
           	    $scope.ownerData =[];
        	    $rootScope.ownerModel=[];
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];
        	    $scope.typeData=[];
        	    $rootScope.typeModel=[];
    		}
    		else if(filter==$scope.country)
    		{    			       	
        		$scope.locationData = [];
           	    $rootScope.locationModel = [];
           	    $scope.operatorData =[];
           	    $rootScope.operatorModel=[];
           	    $scope.ownerData =[];
        	    $rootScope.ownerModel=[];
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];
        	    $scope.typeData=[];
        	    $rootScope.typeModel=[];
    		}
    		else if(filter==$scope.location)
    		{    			       	        		
           	    $scope.operatorData =[];
           	    $rootScope.operatorModel=[];
           	    $scope.ownerData =[];
        	    $rootScope.ownerModel=[];
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];
        	    $scope.typeData=[];
        	    $rootScope.typeModel=[];
    		}
    		else if(filter==$scope.operator)
    		{    			       	        		           	   
           	    $scope.ownerData =[];
        	    $rootScope.ownerModel=[];
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];
        	    $scope.typeData=[];
        	    $rootScope.typeModel=[];
    		}
    		else if(filter==$scope.owner)
    		{    			       	        		           	              	   
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];
        	    $scope.typeData=[];
        	    $rootScope.typeModel=[];
    		}
    		else if(filter==$scope.status)
    		{    			       	        		           	              	           	 
        	    $scope.typeData=[];
        	    $rootScope.typeModel=[];
    		}	
    	}
    	if(path==$scope.lngSupplyDemandPath)
    	{
    		if(filter==$scope.region)
    		{
    			$scope.countryData = [];
        		$rootScope.countryModel=[];        		        		
    		}	
    	}
    	if(path==$scope.lngSmallScalePath)
    	{
    		if(filter==$scope.region)
    		{
    			$scope.countryData = [];
        		$rootScope.countryModel=[];
        		$scope.companyData = [];
        		$rootScope.companyModel=[]; 
        		$scope.technologyProviderData = [];
        		$rootScope.technologyProviderModel=[];
        		$scope.technologyData = [];
        		$rootScope.technologyModel=[];
        		$scope.locationData = [];
           	    $rootScope.locationModel = [];
           	    $scope.statusData =[];
           	    $rootScope.statusModel =[];
           	    $scope.typeData=[];
           	    $rootScope.typeModel=[];
        		
    		}	
    		else if(filter==$scope.country)
    		{
    			$scope.companyData = [];
        		$rootScope.companyModel=[]; 
        		$scope.technologyProviderData = [];
        		$rootScope.technologyProviderModel=[];
        		$scope.technologyData = [];
        		$rootScope.technologyModel=[];
        		$scope.locationData = [];
           	    $rootScope.locationModel = [];
           	    $scope.statusData =[];
           	    $rootScope.statusModel =[];
           	    $scope.typeData=[];
           	    $rootScope.typeModel=[];
    		}
    		else if(filter==$scope.company)
    		{
    			$scope.technologyProviderData = [];
        		$rootScope.technologyProviderModel=[];
        		$scope.technologyData = [];
        		$rootScope.technologyModel=[];
        		$scope.locationData = [];
           	    $rootScope.locationModel = [];
           	    $scope.statusData =[];
           	    $rootScope.statusModel =[];
           	    $scope.typeData=[];
           	    $rootScope.typeModel=[];
    		}
    		else if(filter==$scope.technologyProvider){
    			$scope.technologyData = [];
        		$rootScope.technologyModel=[];
        		$scope.locationData = [];
           	    $rootScope.locationModel = [];
           	    $scope.statusData =[];
           	    $rootScope.statusModel =[];
           	    $scope.typeData=[];
           	    $rootScope.typeModel=[];
    		}
    		else if(filter==$scope.technology){
    			$scope.locationData = [];
           	    $rootScope.locationModel = [];
           	    $scope.statusData =[];
           	    $rootScope.statusModel =[];
           	    $scope.typeData=[];
           	    $rootScope.typeModel=[];
    		}
    		else if(filter==$scope.location){
    			$scope.statusData =[];
           	    $rootScope.statusModel =[];
           	    $scope.typeData=[];
           	    $rootScope.typeModel=[];
    		}
    		else if(filter==$scope.status){
    			 $scope.typeData=[];
            	 $rootScope.typeModel=[];
    		}
    	}
    	if(path==$scope.pipelineDomesticPath || path==$scope.pipelineTransNationalPath)
    	{
    		if(filter==$scope.region)
    		{
    			$scope.countryData = [];
        		$rootScope.countryModel=[];
        		$scope.pdCommodityData = [];
        		$rootScope.pdCommodityModel=[]; 
        		$scope.pdStartPointData = [];
        		$rootScope.pdStartPointModel=[];
        		$scope.pdEndPointData = [];
        		$rootScope.pdEndPointModel=[]; 
        		$scope.statusData = [];
        		$rootScope.statusModel=[];        		
        		
    		}
    		else if(filter==$scope.country){
    			$scope.pdCommodityData = [];
        		$rootScope.pdCommodityModel=[]; 
        		$scope.pdStartPointData = [];
        		$rootScope.pdStartPointModel=[];
        		$scope.pdEndPointData = [];
        		$rootScope.pdEndPointModel=[]; 
        		$scope.statusData = [];
        		$rootScope.statusModel=[];
    		}
    		else if(filter==$scope.commodity){
    			$scope.pdStartPointData = [];
        		$rootScope.pdStartPointModel=[];
        		$scope.pdEndPointData = [];
        		$rootScope.pdEndPointModel=[]; 
        		$scope.statusData = [];
        		$rootScope.statusModel=[];
    		}
    		else if(filter==$scope.startPoint){
    			$scope.pdEndPointData = [];
        		$rootScope.pdEndPointModel=[]; 
        		$scope.statusData = [];
        		$rootScope.statusModel=[];
    		}
    		else if(filter==$scope.endPoint){
    			$scope.statusData = [];
        		$rootScope.statusModel=[];
    		}
    	}
    	if(path==$scope.productionAssetPath){
    		if(filter==$scope.region)
    		{
    			$scope.countryData = [];
        		$rootScope.countryModel=[];
        		   		        		
    		}
    	}
    	if(path==$scope.refineriesCapacityPath || path==$scope.refineriesInfraPath)
    	{
    		if(filter==$scope.region){
    			$scope.countryData = [];
        		$rootScope.countryModel=[];        		
        		$scope.locationData = [];
           	    $rootScope.locationModel = [];
           	    $scope.operatorData =[];
           	    $rootScope.operatorModel=[];
           	    $scope.ownerData =[];
        	    $rootScope.ownerModel=[];
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];        	   
    		}
    		else if(filter==$scope.country){
    			$scope.locationData = [];
           	    $rootScope.locationModel = [];
           	    $scope.operatorData =[];
           	    $rootScope.operatorModel=[];
           	    $scope.ownerData =[];
        	    $rootScope.ownerModel=[];
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];
    		}
    		else if(filter==$scope.location)
    		{
    			$scope.operatorData =[];
           	    $rootScope.operatorModel=[];
           	    $scope.ownerData =[];
        	    $rootScope.ownerModel=[];
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];
    		}
    		else if(filter==$scope.operator){
    			$scope.ownerData =[];
         	    $rootScope.ownerModel=[];
         	    $scope.statusData =[];
         	    $rootScope.statusModel =[];
    		}
    		else if(filter==$scope.owner){
    			$scope.statusData =[];
         	    $rootScope.statusModel =[];
    		}
    	}
    	if(path==$scope.storageCapacityPath || path==$scope.storageInfraPath)
    	{
    		if(filter==$scope.region){
    			$scope.countryData = [];
        		$rootScope.countryModel=[];        		
        		$scope.locationData = [];
           	    $rootScope.locationModel = [];
           	    $scope.operatorData =[];
           	    $rootScope.operatorModel=[];
           	    $scope.ownerData =[];
        	    $rootScope.ownerModel=[];
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];        	   
    		}
    		else if(filter==$scope.country){
    			$scope.locationData = [];
           	    $rootScope.locationModel = [];
           	    $scope.operatorData =[];
           	    $rootScope.operatorModel=[];
           	    $scope.ownerData =[];
        	    $rootScope.ownerModel=[];
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];
    		}
    		else if(filter==$scope.location)
    		{
    			$scope.operatorData =[];
           	    $rootScope.operatorModel=[];
           	    $scope.ownerData =[];
        	    $rootScope.ownerModel=[];
        	    $scope.statusData =[];
        	    $rootScope.statusModel =[];
    		}
    		else if(filter==$scope.operator){
    			$scope.ownerData =[];
         	    $rootScope.ownerModel=[];
         	    $scope.statusData =[];
         	    $rootScope.statusModel =[];
    		}
    		else if(filter==$scope.owner){
    			$scope.statusData =[];
         	    $rootScope.statusModel =[];
    		}
    	}
        if(path==$scope.reportsPath)
    	{
        	if(filter==$scope.region)
        	{
        		$scope.countryData = [];
            	$rootScope.countryModel=[];
        		$scope.sectorData=[];
        	    $rootScope.sectorModel = [];
        	}
        	else if(filter==$scope.country)
        	{
        		$scope.sectorData=[];
        	    $rootScope.sectorModel = [];
        	}
        	
    	}
    };
     $scope.loadFiltersForRegion = function(filter,path){
    	     	 
    	 $rootScope.capacityFilterJSON = {};
    	 if($rootScope.filterObj.regionField == true){
 			$scope.generateFormData($rootScope.regionModel,'region');
  		}
    	 if(path==$scope.explorationPath)
    	 {
    		 HttpService.get('/exploration/countries',$rootScope.capacityFilterJSON).then(function(resp) {
  	           for (var i = 0; i < resp.length; i++) {
  	               var obj = {
  	                   id: resp[i].country,
  	                   label: resp[i].country
  	               }
  	               $scope.countryData.push(obj);                   
  	           }
  	           $scope.countryData = $scope.sortedOrder($scope.countryData);  	           
  	       });
    	 }	
    	 else if(path==$scope.lngCapacityPath || path==$scope.lngInfraPath)
    	 {
    		 HttpService.get('/lng/countries',$rootScope.capacityFilterJSON).then(function(resp) {
    	           for (var i = 0; i < resp.length; i++) {
    	               var obj = {
    	                   id: resp[i].country,
    	                   label: resp[i].country
    	               }
    	               $scope.countryData.push(obj);                   
    	           }
    	           $scope.countryData = $scope.sortedOrder($scope.countryData);
    	           
    	       });
    	 } 
    	 else if(path==$scope.lngSupplyDemandPath)
    	 {
    		 HttpService.get('/supplyDemand/countries',$rootScope.capacityFilterJSON).then(function(resp) {
    	           for (var i = 0; i < resp.length; i++) {
    	               var obj = {
    	                   id: resp[i].country,
    	                   label: resp[i].country
    	               }
    	               $scope.countryData.push(obj);                   
    	           }
    	           $scope.countryData = $scope.sortedOrder($scope.countryData);
    	           
    	       });
    	 }
    	 else if(path==$scope.lngSmallScalePath){
    		 HttpService.get('/smallscalelng/countries',$rootScope.capacityFilterJSON).then(function(resp) {
               for (var i = 0; i < resp.length; i++) {
                   var obj = {
                       id: resp[i].country,
                       label: resp[i].country
                   }
                   $scope.countryData.push(obj);
               }
               $scope.countryData = $scope.sortedOrder($scope.countryData);
           });
    	 }
    	 else if(path==$scope.pipelineDomesticPath || path==$scope.pipelineTransNationalPath){
    		 HttpService.get('/pipeline/countries',$rootScope.capacityFilterJSON).then(function(resp) {
  	           for (var i = 0; i < resp.length; i++) {
  	               var obj = {
  	                   id: resp[i].country,
  	                   label: resp[i].country
  	               }
  	               $scope.countryData.push(obj);                   
  	           }
  	           $scope.countryData = $scope.sortedOrder($scope.countryData);
  	           
  	       });
    	 }
    	 else if(path==$scope.productionAssetPath){
    		 HttpService.get('/production/asset/countries',$rootScope.capacityFilterJSON).then(function(resp) {
    	           for (var i = 0; i < resp.length; i++) {
    	               var obj = {
    	                   id: resp[i].country,
    	                   label: resp[i].country
    	               }
    	               $scope.countryData.push(obj);                   
    	           }
    	           $scope.countryData = $scope.sortedOrder($scope.countryData);
    	           
    	       });
    	 }
    	 else if(path==$scope.refineriesCapacityPath || path ==$scope.refineriesInfraPath){
    		 
    		 HttpService.get('/refineries/countries',$rootScope.capacityFilterJSON).then(function(resp) {
  	           for (var i = 0; i < resp.length; i++) {
  	               var obj = {
  	                   id: resp[i].country,
  	                   label: resp[i].country
  	               }
  	               $scope.countryData.push(obj);                   
  	           }
  	           $scope.countryData = $scope.sortedOrder($scope.countryData);
  	           
  	       });
    	 }
    	 else if(path==$scope.storageCapacityPath || path==$scope.storageInfraPath){
    		 HttpService.get('/storage/countries',$rootScope.capacityFilterJSON).then(function(resp) {
    	           for (var i = 0; i < resp.length; i++) {
    	               var obj = {
    	                   id: resp[i].country,
    	                   label: resp[i].country
    	               }
    	               $scope.countryData.push(obj);                   
    	           }
    	           $scope.countryData = $scope.sortedOrder($scope.countryData);
    	           
    	       });
    	 }
    	 else if(path==$scope.reportsPath)
    	 {
    		 HttpService.get('/pdfreport/countries',$rootScope.capacityFilterJSON).then(function(resp) {
               for (var i = 0; i < resp.length; i++) {
                   var obj = {
                       id: resp[i].country,
                       label: resp[i].country
                   }
                   $scope.countryData.push(obj);
               }
               $scope.countryData = $scope.sortedOrder($scope.countryData);
           });
    	 }
    	 
     };
     
     $scope.loadFiltersForCountry = function(filter,path){
     	 
    	$rootScope.capacityFilterJSON = {};
    	if($rootScope.filterObj.regionField == true){
 			$scope.generateFormData($rootScope.regionModel,'region');
  		}    	 
    	if($rootScope.filterObj.countryField == true){
   			$scope.generateFormData($rootScope.countryModel,'country');
    	}
    	
    	if(path==$scope.explorationPath)
   	 	{
    		HttpService.get('/exploration/basins',$rootScope.capacityFilterJSON).then(function(resp) {
              for (var i = 0; i < resp.length; i++) {
                  var obj = {
                      id: resp[i].basin,
                      label: resp[i].basin
                  }
                  $scope.basinData.push(obj);
 
              }
              $scope.basinData = $scope.sortedOrder($scope.basinData);
          });
   	 	}	 
    	 else if(path==$scope.lngCapacityPath || path==$scope.lngInfraPath)
    	 {
    		 HttpService.get('/lng/locations',$rootScope.capacityFilterJSON).then(function(resp) {
               for (var i = 0; i < resp.length; i++) {
                   var obj = {
                       id: resp[i].location,
                       label: resp[i].location
                   }
                   $scope.locationData.push(obj);
               }
               $scope.locationData = $scope.sortedOrder($scope.locationData);
           });
    	 } 
    	 else if(path==$scope.lngSmallScalePath)
    	 {
    		 HttpService.get('/smallscalelng/companies',$rootScope.capacityFilterJSON).then(function(resp) {
               for (var i = 0; i < resp.length; i++) {
                   var obj = {
                       id: resp[i].company,
                       label: resp[i].company
                   }
                   $scope.companyData.push(obj);
               }
               $scope.companyData = $scope.sortedOrder($scope.companyData);
           });
    		 
    	 }
    	 else if(path==$scope.pipelineDomesticPath || path==$scope.pipelineTransNationalPath)
    	 {
    		 HttpService.get('/pipeline/commodities',$rootScope.capacityFilterJSON).then(function(resp) {
                 for (var i = 0; i < resp.length; i++) {
                     var obj = {
                         id: resp[i].commodity,
                         label: resp[i].commodity
                     }
                     $scope.pdCommodityData.push(obj);
                 }
                 $scope.pdCommodityData = $scope.sortedOrder($scope.pdCommodityData);
             });
    		 
    	 }
    	 else if(path==$scope.refineriesCapacityPath || path==$scope.refineriesInfraPath){
    		 HttpService.get('/refineries/locations',$rootScope.capacityFilterJSON).then(function(resp) {
               for (var i = 0; i < resp.length; i++) {
                   var obj = {
                       id: resp[i].location,
                       label: resp[i].location
                   }
                   $scope.locationData.push(obj);
               }
               $scope.locationData = $scope.sortedOrder($scope.locationData);
           });
    	 }
    	 else if(path == $scope.storageCapacityPath || path==$scope.storageInfraPath){
    		 HttpService.get('/storage/locations',$rootScope.capacityFilterJSON).then(function(resp) {
               for (var i = 0; i < resp.length; i++) {
                   var obj = {
                       id: resp[i].location,
                       label: resp[i].location
                   }
                   $scope.locationData.push(obj);
               }
               $scope.locationData = $scope.sortedOrder($scope.locationData);
           });
    	 }
    	 else if(path==$scope.reportsPath)
    	 {
    		 HttpService.get('/pdfreport/sectors',$rootScope.capacityFilterJSON).then(function(resp) {
               for (var i = 0; i < resp.length; i++) {
                   var obj = {
                       id: resp[i].sector,
                       label: resp[i].sector
                   }
                   $scope.sectorData.push(obj);
               }
               $scope.sectorData = $scope.sortedOrder($scope.sectorData);
           });
    	 }
    	 
     };
     
     

     $scope.loadFiltersForBasin = function(filter,path){
    	 
    	$rootScope.capacityFilterJSON = {};
    	if($rootScope.filterObj.regionField == true){
 			$scope.generateFormData($rootScope.regionModel,'region');
  		}
    	if($rootScope.filterObj.countryField == true){
  			$scope.generateFormData($rootScope.countryModel,'country');
   		}
    	if($rootScope.filterObj.basinField == true){
			$scope.generateFormData($rootScope.basinModel,'basin');
 		}
    	 if($scope.explorationPath==path)
    	 {
    		 HttpService.get('/exploration/operators',$rootScope.capacityFilterJSON).then(function(resp) {
               for (var i = 0; i < resp.length; i++) {
                   var obj = {
                       id: resp[i].operator,
                       label: resp[i].operator
                   }
                   $scope.operatorData.push(obj);
               }
               $scope.operatorData = $scope.sortedOrder($scope.operatorData);
           });
    	 }	 
    		 
    	 
     };
     $scope.loadFiltersForOperator =function(filter,path){
    	 $rootScope.capacityFilterJSON = {};
     	if($rootScope.filterObj.regionField == true){
  			$scope.generateFormData($rootScope.regionModel,'region');
   		}
     	if($rootScope.filterObj.countryField == true){
   			$scope.generateFormData($rootScope.countryModel,'country');
    		}
     	if($rootScope.filterObj.basinField == true){
 			$scope.generateFormData($rootScope.basinModel,'basin');
  		}
     	if($rootScope.filterObj.locationField == true){
			$scope.generateFormData($rootScope.locationModel,'location');
 		}
     	if($rootScope.filterObj.operatorField == true){
			$scope.generateFormData($rootScope.operatorModel,'operator');
 		}
     	
     	if($scope.explorationPath==path)
     	{
     		HttpService.get('/exploration/owners',$rootScope.capacityFilterJSON).then(function(resp) {
              for (var i = 0; i < resp.length; i++) {
                  var obj = {
                      id: resp[i].owner,
                      label: resp[i].owner
                  }
                  $scope.ownerData.push(obj);
 
              }
              $scope.ownerData = $scope.sortedOrder($scope.ownerData);
          });
     	}
     	else if(path==$scope.lngCapacityPath || path==$scope.lngInfraPath)
     	{
     		 HttpService.get('/lng/owners',$rootScope.capacityFilterJSON).then(function(resp) {
               for (var i = 0; i < resp.length; i++) {
                   var obj = {
                       id: resp[i].owner,
                       label: resp[i].owner
                   }
                   $scope.ownerData.push(obj);
  
               }
               $scope.ownerData = $scope.sortedOrder($scope.ownerData);
           });
     		
     	}
     	else if(path==$scope.refineriesCapacityPath || path==$scope.refineriesInfraPath){
     		HttpService.get('/refineries/owners',$rootScope.capacityFilterJSON).then(function(resp) {
              for (var i = 0; i < resp.length; i++) {
                  var obj = {
                      id: resp[i].owner,
                      label: resp[i].owner
                  }
                  $scope.ownerData.push(obj);
 
              }
              $scope.ownerData = $scope.sortedOrder($scope.ownerData);
          });
     	}
     	else if(path == $scope.storageCapacityPath || path==$scope.storageInfraPath){
     		HttpService.get('/storage/owners',$rootScope.capacityFilterJSON).then(function(resp) {
              for (var i = 0; i < resp.length; i++) {
                  var obj = {
                      id: resp[i].owner,
                      label: resp[i].owner
                  }
                  $scope.ownerData.push(obj);
 
              }
              $scope.ownerData = $scope.sortedOrder($scope.ownerData);
          });
     	}
     };
     $scope.loadFiltersForOwner = function(filter,path)
     {
    	 $rootScope.capacityFilterJSON = {};
      	if($rootScope.filterObj.regionField == true){
   			$scope.generateFormData($rootScope.regionModel,'region');
    		}
      	if($rootScope.filterObj.countryField == true){
    			$scope.generateFormData($rootScope.countryModel,'country');
     		}
      	if($rootScope.filterObj.basinField == true){
  			$scope.generateFormData($rootScope.basinModel,'basin');
   		}
      	if($rootScope.filterObj.locationField == true){
			$scope.generateFormData($rootScope.locationModel,'location');
 		}
      	if($rootScope.filterObj.operatorField == true){
 			$scope.generateFormData($rootScope.operatorModel,'operator');
  		}
      	if($rootScope.filterObj.ownerField == true){
 			$scope.generateFormData($rootScope.ownerModel,'owner');
 		}
      	if($scope.explorationPath==path)
     	{
      		HttpService.get('/exploration/status',$rootScope.capacityFilterJSON).then(function(resp) {
              for (var i = 0; i < resp.length; i++) {
                  var obj = {
                      id: resp[i].status,
                      label: resp[i].status
                  }
                  $scope.statusData.push(obj);
              }
              $scope.statusData = $scope.sortedOrder($scope.statusData);
          });
     	}	 
      	else if(path==$scope.lngCapacityPath || path==$scope.lngInfraPath)
      	{
      		HttpService.get('/lng/status',$rootScope.capacityFilterJSON).then(function(resp) {
              for (var i = 0; i < resp.length; i++) {
                  var obj = {
                      id: resp[i].status,
                      label: resp[i].status
                  }
                  $scope.statusData.push(obj);
              }
              $scope.statusData = $scope.sortedOrder($scope.statusData);
          });
      	}
      	else if(path==$scope.refineriesCapacityPath || path==$scope.refineriesInfraPath){
      		HttpService.get('/refineries/status',$rootScope.capacityFilterJSON).then(function(resp) {
                for (var i = 0; i < resp.length; i++) {
                    var obj = {
                        id: resp[i].status,
                        label: resp[i].status
                    }
                    $scope.statusData.push(obj);
                }
                $scope.statusData = $scope.sortedOrder($scope.statusData);
            });
      	}
      	else if(path ==$scope.storageCapacityPath || path==$scope.storageInfraPath){
      		HttpService.get('/storage/status',$rootScope.capacityFilterJSON).then(function(resp) {
                for (var i = 0; i < resp.length; i++) {
                    var obj = {
                        id: resp[i].status,
                        label: resp[i].status
                    }
                    $scope.statusData.push(obj);
                }
                $scope.statusData = $scope.sortedOrder($scope.statusData);
            });
      	}
     };
     
     $scope.loadFiltersForStatus = function(filter,path){
    	 $rootScope.capacityFilterJSON = {};
       	if($rootScope.filterObj.regionField == true){
    			$scope.generateFormData($rootScope.regionModel,'region');
     		}
       	if($rootScope.filterObj.countryField == true){
     			$scope.generateFormData($rootScope.countryModel,'country');
      		}
       	if($rootScope.filterObj.basinField == true){
   			$scope.generateFormData($rootScope.basinModel,'basin');
    	}
       	if($rootScope.filterObj.locationField == true){
			$scope.generateFormData($rootScope.locationModel,'location');
 		}
       	if($rootScope.filterObj.operatorField == true){
  			$scope.generateFormData($rootScope.operatorModel,'operator');
   		}
       	if($rootScope.filterObj.ownerField == true){
  			$scope.generateFormData($rootScope.ownerModel,'owner');
  		}
       	if($rootScope.filterObj.statusField == true){
 			$scope.generateFormData($rootScope.statusModel,'status');
 		}
       	if($rootScope.filterObj.companyField == true){
 			$scope.generateFormData($rootScope.companyModel,'company');
 		}
       	if($rootScope.filterObj.technologyProviderField == true){
 			$scope.generateFormData($rootScope.technologyProviderModel,'technologyprovider');
 		}
       	if($rootScope.filterObj.technologyField == true){
 			$scope.generateFormData($rootScope.technologyModel,'technology');
 		}
       	
       	if($scope.explorationPath==path)
     	{
       		HttpService.get('/exploration/type',$rootScope.capacityFilterJSON).then(function(resp) {            	            	                  
            	         for (var i = 0; i < resp.length; i++) {
            	             var obj = {
            	                 id: resp[i].type,
            	                 label: resp[i].type
            	             }
            	             $scope.typeData.push(obj);
            	         }
            	         $scope.typeData = $scope.sortedOrder($scope.typeData);
            	     });
     	}	 
       	else if(path== $scope.lngCapacityPath || path==$scope.lngInfraPath)
     	{
       		HttpService.get('/lng/type',$rootScope.capacityFilterJSON).then(function(resp) {            	            	                  
            	         for (var i = 0; i < resp.length; i++) {
            	             var obj = {
            	                 id: resp[i].type,
            	                 label: resp[i].type
            	             }
            	             $scope.typeData.push(obj);
            	         }
            	         $scope.typeData = $scope.sortedOrder($scope.typeData);
            	     });
     	}
       	else if(path==$scope.lngSmallScalePath)
       	{
       		HttpService.get('/smallscalelng/types',$rootScope.capacityFilterJSON).then(function(resp) {
          	 for (var i = 0; i < resp.length; i++) {
              	 var obj = {
                  	 id: resp[i].type,
                   	label: resp[i].type
               	}
               	$scope.typeData.push(obj);
           	}
          	 $scope.typeData = $scope.sortedOrder($scope.typeData);
       		});
       	}
     };
     $scope.loadFiltersForLocation = function(filter,path){
    	 $rootScope.capacityFilterJSON = {};
        	if($rootScope.filterObj.regionField == true){
     			$scope.generateFormData($rootScope.regionModel,'region');
      		}
        	if($rootScope.filterObj.countryField == true){
      			$scope.generateFormData($rootScope.countryModel,'country');
       		}
        	if($rootScope.filterObj.companyField == true){
     			$scope.generateFormData($rootScope.companyModel,'company');
     		}
    		
    		if($rootScope.filterObj.technologyProviderField == true){
     			$scope.generateFormData($rootScope.technologyProviderModel,'technologyprovider');
     		}
    		if($rootScope.filterObj.technologyField == true){
     			$scope.generateFormData($rootScope.technologyModel,'technology');
     		}
        	if($rootScope.filterObj.locationField == true){
    			$scope.generateFormData($rootScope.locationModel,'location');
     		}
        	
        	
        	if(path==$scope.lngCapacityPath || path==$scope.lngInfraPath)
        	{
        		 HttpService.get('/lng/operators',$rootScope.capacityFilterJSON).then(function(resp) {
                   for (var i = 0; i < resp.length; i++) {
                       var obj = {
                           id: resp[i].operator,
                           label: resp[i].operator
                       }
                       $scope.operatorData.push(obj);
                   }
                   $scope.operatorData = $scope.sortedOrder($scope.operatorData);
               });
        	}
        	else if(path==$scope.lngSmallScalePath)
        	{
        		HttpService.get('/smallscalelng/statuses',$rootScope.capacityFilterJSON).then(function(resp) {
                  for (var i = 0; i < resp.length; i++) {
                      var obj = {
                          id: resp[i].status,
                          label: resp[i].status
                      }
                      $scope.statusData.push(obj);
                  }
                  $scope.statusData = $scope.sortedOrder($scope.statusData);
              });
        	}
        	else if(path==$scope.refineriesCapacityPath || path==$scope.refineriesInfraPath){
        		 HttpService.get('/refineries/operators',$rootScope.capacityFilterJSON).then(function(resp) {
                   for (var i = 0; i < resp.length; i++) {
                       var obj = {
                           id: resp[i].operator,
                           label: resp[i].operator
                       }
                       $scope.operatorData.push(obj);
                   }
                   $scope.operatorData = $scope.sortedOrder($scope.operatorData);
               });
        	}
        	else if(path==$scope.storageCapacityPath || path==$scope.storageInfraPath){
        		 HttpService.get('/storage/operators',$rootScope.capacityFilterJSON).then(function(resp) {
                   for (var i = 0; i < resp.length; i++) {
                       var obj = {
                           id: resp[i].operator,
                           label: resp[i].operator
                       }
                       $scope.operatorData.push(obj);
                   }
                   $scope.operatorData = $scope.sortedOrder($scope.operatorData);
               });
        	}
        	
     };
     $scope.loadFiltersForCompany =function(filter,path){
    	 $rootScope.capacityFilterJSON = {};
     	if($rootScope.filterObj.regionField == true){
  			$scope.generateFormData($rootScope.regionModel,'region');
   		}
     	if($rootScope.filterObj.countryField == true){
   			$scope.generateFormData($rootScope.countryModel,'country');
    		}
     	if($rootScope.filterObj.companyField == true){
 			$scope.generateFormData($rootScope.companyModel,'company');
 		}
     	
     	if(path==$scope.lngSmallScalePath)
    	{
     		HttpService.get('/smallscalelng/technologyproviders',$rootScope.capacityFilterJSON).then(function(resp) {
              for (var i = 0; i < resp.length; i++) {
                  var obj = {
                      id: resp[i].technologyprovider,
                      label: resp[i].technologyprovider
                  }
                  $scope.technologyProviderData.push(obj);
              }
              $scope.technologyProviderData = $scope.sortedOrder($scope.technologyProviderData);
          });
    	}
     	
     };
     $scope.loadFiltersForTechnologyProvider =function(filter,path){
    	 $rootScope.capacityFilterJSON = {};
      	if($rootScope.filterObj.regionField == true){
   			$scope.generateFormData($rootScope.regionModel,'region');
    		}
      	if($rootScope.filterObj.countryField == true){
    			$scope.generateFormData($rootScope.countryModel,'country');
     	}
      	if($rootScope.filterObj.companyField == true){
  			$scope.generateFormData($rootScope.companyModel,'company');
  		}
      	if($rootScope.filterObj.technologyProviderField == true){
 			$scope.generateFormData($rootScope.technologyProviderModel,'technologyprovider');
 		}
      	if(path==$scope.lngSmallScalePath)
    	{
      		HttpService.get('/smallscalelng/technologies',$rootScope.capacityFilterJSON).then(function(resp) {
              for (var i = 0; i < resp.length; i++) {
                  var obj = {
                      id: resp[i].technology,
                      label: resp[i].technology
                  }
                  $scope.technologyData.push(obj);
              }
              $scope.technologyData = $scope.sortedOrder($scope.technologyData);
          });
    	}
     };
     $scope.loadFiltersForTechnology =function(filter,path){
    	 $rootScope.capacityFilterJSON = {};
       	if($rootScope.filterObj.regionField == true){
    			$scope.generateFormData($rootScope.regionModel,'region');
     		}
       	if($rootScope.filterObj.countryField == true){
     			$scope.generateFormData($rootScope.countryModel,'country');
      	}
       	if($rootScope.filterObj.companyField == true){
   			$scope.generateFormData($rootScope.companyModel,'company');
   		}
       	if($rootScope.filterObj.technologyProviderField == true){
  			$scope.generateFormData($rootScope.technologyProviderModel,'technologyprovider');
  		}
       	if($rootScope.filterObj.technologyField == true){
 			$scope.generateFormData($rootScope.technologyModel,'technology');
 		}
       	if(path==$scope.lngSmallScalePath)
    	{
       		HttpService.get('/smallscalelng/locations',$rootScope.capacityFilterJSON).then(function(resp) {
              for (var i = 0; i < resp.length; i++) {
                  var obj = {
                      id: resp[i].location,
                      label: resp[i].location
                  }
                  $scope.locationData.push(obj);
              }
              $scope.locationData = $scope.sortedOrder($scope.locationData);
          });
    	}
       	
     };
     $scope.loadFiltersForCommodity =function(filter,path){
    	 $rootScope.capacityFilterJSON = {};
        	if($rootScope.filterObj.regionField == true){
     			$scope.generateFormData($rootScope.regionModel,'region');
      		}
        	if($rootScope.filterObj.countryField == true){
      			$scope.generateFormData($rootScope.countryModel,'country');
        	}
        	if($rootScope.filterObj.pdCommodityField == true){
     		 	if($rootScope.pdCommodityModel.id != undefined){
     		 		$rootScope.pdCommodityModel.length =0;
     				$rootScope.pdCommodityModel.push({id:$rootScope.pdCommodityModel.id});
     			}else if ($rootScope.pdCommodityModel.length >0){
     				if($rootScope.pdCommodityModel.id == undefined)
    						$rootScope.pdCommodityModel.length =0
     	 		}else{
     				$rootScope.pdCommodityModel.length =0;
     			}
     			$scope.generateFormData($rootScope.pdCommodityModel,'commodity');
     		}
        	if(path==$scope.pipelineDomesticPath || path==$scope.pipelineTransNationalPath){
        		HttpService.get('/pipeline/startpoints',$rootScope.capacityFilterJSON).then(function(resp) {
                    for (var i = 0; i < resp.length; i++) {
                        var obj = {
                            id: resp[i].startPoint,
                            label: resp[i].startPoint
                        }
                        $scope.pdStartPointData.push(obj);
                    }
                    $scope.pdStartPointData = $scope.sortedOrder($scope.pdStartPointData);
                });
        	}
     };
     $scope.loadFiltersForStartPoint=function(filter,path){
    	 $rootScope.capacityFilterJSON = {};
     	if($rootScope.filterObj.regionField == true){
  			$scope.generateFormData($rootScope.regionModel,'region');
   		}
     	if($rootScope.filterObj.countryField == true){
   			$scope.generateFormData($rootScope.countryModel,'country');
     	}
     	if($rootScope.filterObj.pdCommodityField == true){
  		 	if($rootScope.pdCommodityModel.id != undefined){
  		 		$rootScope.pdCommodityModel.length =0;
  				$rootScope.pdCommodityModel.push({id:$rootScope.pdCommodityModel.id});
  			}else if ($rootScope.pdCommodityModel.length >0){
  				if($rootScope.pdCommodityModel.id == undefined)
 						$rootScope.pdCommodityModel.length =0
  	 		}else{
  				$rootScope.pdCommodityModel.length =0;
  			}
  			$scope.generateFormData($rootScope.pdCommodityModel,'commodity');
  		}
     	if($rootScope.filterObj.pdStartPointField == true){
 			$scope.generateFormData($rootScope.pdStartPointModel,'startPoint');
 		} 
     	
     	if(path==$scope.pipelineDomesticPath || path==$scope.pipelineTransNationalPath){
     		HttpService.get('/pipeline/endpoints',$rootScope.capacityFilterJSON).then(function(resp) {
                for (var i = 0; i < resp.length; i++) {
                    var obj = {
                        id: resp[i].endPoint,
                        label: resp[i].endPoint
                    }
                    $scope.pdEndPointData.push(obj);
                }
                $scope.pdEndPointData = $scope.sortedOrder($scope.pdEndPointData);
            });
    	}
     };
     $scope.loadFiltersForEndPoint =function(filter,path){
    	 $rootScope.capacityFilterJSON = {};
      	if($rootScope.filterObj.regionField == true){
   			$scope.generateFormData($rootScope.regionModel,'region');
    		}
      	if($rootScope.filterObj.countryField == true){
    			$scope.generateFormData($rootScope.countryModel,'country');
      	}
      	if($rootScope.filterObj.pdCommodityField == true){
   		 	if($rootScope.pdCommodityModel.id != undefined){
   		 		$rootScope.pdCommodityModel.length =0;
   				$rootScope.pdCommodityModel.push({id:$rootScope.pdCommodityModel.id});
   			}else if ($rootScope.pdCommodityModel.length >0){
   				if($rootScope.pdCommodityModel.id == undefined)
  						$rootScope.pdCommodityModel.length =0
   	 		}else{
   				$rootScope.pdCommodityModel.length =0;
   			}
   			$scope.generateFormData($rootScope.pdCommodityModel,'commodity');
   		}
      	if($rootScope.filterObj.pdStartPointField == true){
  			$scope.generateFormData($rootScope.pdStartPointModel,'startPoint');
  		} 
      	if($rootScope.filterObj.pdEndPointField == true){
 			$scope.generateFormData($rootScope.pdEndPointModel,'endPoint');
 		} 
      	
      	if(path==$scope.pipelineDomesticPath || path==$scope.pipelineTransNationalPath){
      		HttpService.get('/pipeline/status',$rootScope.capacityFilterJSON).then(function(resp) {
                for (var i = 0; i < resp.length; i++) {
                    var obj = {
                        id: resp[i].status,
                        label: resp[i].status
                    }
                    $scope.statusData.push(obj);
                }
                $scope.statusData = $scope.sortedOrder($scope.statusData);
            });
    	}
      	
     };
     /* exportedCountries filter */
     $scope.exportedCountriesData = [];
     $rootScope.exportedCountriesModel = [];
     $scope.exportedCountriesSettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.exportedCountriesEvents = {
         onItemSelect: function(item) {
             $rootScope.onFilterSelect(item, 'importedCompanies')
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
     $scope.importedCountriesSettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.importedCountriesEvents = {
         onItemSelect: function(item) {
             $rootScope.onFilterSelect(item, 'importedCompanies')
         }
     };

     /* exportedCompanies filter */
     $scope.exportedCompaniesData = [];
     $rootScope.exportedCompaniesModel = [];
     $scope.exportedCompaniesSettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.exportedCompaniesEvents = {
         onItemSelect: function(item) {
             $rootScope.onFilterSelect(item, 'importedCompanies')
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
     $scope.importedCompaniesSettings = {
         enableSearch: true,
         scrollable: true
     };
     $scope.importedCompaniesEvents = {
         onItemSelect: function(item) {
             $rootScope.onFilterSelect(item, 'importedCompanies')
         }

     };
        
     $rootScope.filterObj = {
         regionField: true,
         countryField: true,
         locationField: true,
         operatorField: true,
         ownerField: true,
         statusField: true,
         unitsField: true,
         offshoreField: true,
         typeField: true,
         sectorField: true,
         companyField: false,
         technologyProviderField: false,
         technologyField: false,
         imports: false,
         exports: false,
         assetTypeField: false,
         assetUnitField: false,
         assetCountryField: false,
         pdCommodityField: false,
         pdStartPointField: false,
         pdEndPointField: false,
         BasinField: false
     };

//     HttpService.get('/regions').then(function(resp) {
//         for (var i = 0; i < resp.length; i++) {
//             var obj = {
//                 id: resp[i].region,
//                 label: resp[i].region
//             }
//             $scope.regionData.push(obj);
//         }
//         $scope.regionData = $scope.sortedOrder($scope.regionData);
//     });



//     HttpService.get('/countries').then(function(resp) {
//         for (var i = 0; i < resp.length; i++) {
//             var obj = {
//                 id: resp[i].country,
//                 label: resp[i].country
//             }
//             $scope.countryData.push(obj);
//             $scope.assetCountryData.push(obj);
//         }
//         $scope.countryData = $scope.sortedOrder($scope.countryData);
//     });

     HttpService.get('/contracts/exportcountries').then(function(resp) {
         for (var i = 0; i < resp.length; i++) {
             var obj = {
                 id: resp[i].country,
                 label: resp[i].country
             }

             $scope.exportedCountriesData.push(obj);
         }
         $scope.exportedCountriesData = $scope.sortedOrder($scope.exportedCountriesData);
     });
    

     HttpService.get('/contracts/exportcompanies').then(function(resp) {
         for (var i = 0; i < resp.length; i++) {
             var obj = {
                 id: resp[i].company,
                 label: resp[i].company
             }
             $scope.exportedCompaniesData.push(obj);
         }
         $scope.exportedCompaniesData = $scope.sortedOrder($scope.exportedCompaniesData);
     });

     

//     HttpService.get('/type').then(function(resp) {
//
//         if ($location.path() == "/exploration")
//             return;
//
//         for (var i = 0; i < resp.length; i++) {
//             var obj = {
//                 id: resp[i].type,
//                 label: resp[i].type
//             }
//             $scope.typeData.push(obj);
//         }
//         $scope.typeData = $scope.sortedOrder($scope.typeData);
//     });

    

     

     $scope.generateFormData = function(ary, key) {
         for (var i = 0; i < ary.length; i++) {
             var fromkey = key + i
             $rootScope.capacityFilterJSON[fromkey] = ary[i].id;
         }
     }

     $rootScope.loadImports = function(item, filterType) {
         //console.log("In filter CTRL",$rootScope.countryModel);
         $scope.importedCountriesData = [];
         $scope.importedCompaniesData = [];

         if ($rootScope.exportedCountriesModel.length > 0) {
             $scope.generateFormData($rootScope.exportedCountriesModel, 'exportcountry');
             HttpService.get("/contracts/importcountries", $rootScope.capacityFilterJSON).then(function(resp) {
                 for (var i = 0; i < resp.length; i++) {
                     var obj = {
                         id: resp[i].country,
                         label: resp[i].country
                     }
                     $scope.importedCountriesData.push(obj);
                 }
                 $rootScope.capacityFilterJSON = {};
             });
         }

         if ($rootScope.exportedCompaniesModel.length > 0) {
             $scope.generateFormData($rootScope.exportedCompaniesModel, 'exportcompany');
             HttpService.get("/contracts/importcompanies", $rootScope.capacityFilterJSON).then(function(resp) {
                 for (var i = 0; i < resp.length; i++) {
                     var obj = {
                         id: resp[i].company,
                         label: resp[i].company
                     }
                     $scope.importedCompaniesData.push(obj);
                 }
                 $rootScope.capacityFilterJSON = {};
             });
         }
         $rootScope.filterObj.imports = true;
     };

     $rootScope.onFilterDeSelect = function(item, filterType) {
         //console.log("In filter CTRL",$rootScope.countryModel);
         $scope.generateFormData($rootScope.countryModel, 'exportcompany');
         HttpService.get("/contracts/importcompanies", $rootScope.capacityFilterJSON).then(function(resp) {
             for (var i = 0; i < resp.length; i++) {
                 var obj = {
                     id: resp[i].company,
                     label: resp[i].company
                 }
                 $scope.importedCompaniesData.push(obj);
             }
             $scope.importedCompaniesData = $scope.sortedOrder($scope.importedCompaniesData);
         });
     };


     $rootScope.loadLngFilter = function() {
    	 $scope.regionData = [];
    	 $scope.countryData = [];
         $scope.locationData = [];
         $scope.operatorData = [];         
         $scope.ownerData = [];
         $scope.statusData = [];
         $scope.unitsData = [];
         $rootScope.offshoreModel = [];
         $scope.typeData = [];
                  
         
         $scope.offshoreData = [{
             id: 'Offshore',
             label: "Offshore"
         }, {
             id: 'Onshore',
             label: "Onshore"
         }]; //Timebeing Removed as client don't want{id: 'Not_Decided', label: "Not Decided"},
         $scope.offshoreData = $scope.sortedOrder($scope.offshoreData);
         
         $scope.unitsData = [{
             id: 'BCF',
             label: "BCF"
         }];
         
         
         


         HttpService.get('/lng/regions').then(function(resp) {
             for (var i = 0; i < resp.length; i++) {
                 var obj = {
                     id: resp[i].region,
                     label: resp[i].region
                 }
                 $scope.regionData.push(obj);
             }
             $scope.regionData = $scope.sortedOrder($scope.regionData);
         });

//         HttpService.get('/lng/countries').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].country,
//                     label: resp[i].country
//                 }
//                 $scope.countryData.push(obj);
//             }
//             $scope.countryData = $scope.sortedOrder($scope.countryData);
//         });
//
//
//         HttpService.get('/lng/status').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].status,
//                     label: resp[i].status
//                 }
//                 $scope.statusData.push(obj);
//             }
//             $scope.statusData = $scope.sortedOrder($scope.statusData);
//         });
//
//         HttpService.get('/lng/locations').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].location,
//                     label: resp[i].location
//                 }
//                 $scope.locationData.push(obj);
//             }
//             $scope.locationData = $scope.sortedOrder($scope.locationData);
//         });
//
//         HttpService.get('/lng/operators').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].operator,
//                     label: resp[i].operator
//                 }
//                 $scope.operatorData.push(obj);
//             }
//             $scope.operatorData = $scope.sortedOrder($scope.operatorData);
//         });
//
//         HttpService.get('/lng/owners').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].owner,
//                     label: resp[i].owner
//                 }
//                 $scope.ownerData.push(obj);
//
//             }
//             $scope.ownerData = $scope.sortedOrder($scope.ownerData);
//         });
//
//         HttpService.get('/lng/type').then(function(resp) {
//
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].type,
//                     label: resp[i].type
//                 }
//                 $scope.typeData.push(obj);
//             }
//             $scope.typeData = $scope.sortedOrder($scope.typeData);
//         });




     };

     $rootScope.loadReportsFilter = function() {
         $scope.regionData = [];
         $scope.countryData = [];
         $scope.sectorData = [];


         HttpService.get('/pdfreport/regions').then(function(resp) {
             for (var i = 0; i < resp.length; i++) {
                 var obj = {
                     id: resp[i].region,
                     label: resp[i].region
                 }
                 $scope.regionData.push(obj);
             }
             $scope.regionData = $scope.sortedOrder($scope.regionData);
         });

//         HttpService.get('/pdfreport/sectors').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].sector,
//                     label: resp[i].sector
//                 }
//                 $scope.sectorData.push(obj);
//             }
//             $scope.sectorData = $scope.sortedOrder($scope.sectorData);
//         });
//
//         HttpService.get('/pdfreport/countries').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].country,
//                     label: resp[i].country
//                 }
//                 $scope.countryData.push(obj);
//             }
//             $scope.countryData = $scope.sortedOrder($scope.countryData);
//         });



     };

     $rootScope.loadSupplyDemandFilter = function() {
         $scope.regionData = [];
         $scope.countryData = [];

         HttpService.get('/supplyDemand/regions').then(function(resp) {
             for (var i = 0; i < resp.length; i++) {
                 var obj = {
                     id: resp[i].region,
                     label: resp[i].region
                 }
                 $scope.regionData.push(obj);
             }
             $scope.regionData = $scope.sortedOrder($scope.regionData);
         });

//         HttpService.get('/supplyDemand/countries').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].country,
//                     label: resp[i].country
//                 }
//                 $scope.countryData.push(obj);
//             }
//             $scope.countryData = $scope.sortedOrder($scope.countryData);
//         });
     };


     $rootScope.loadRefineriesFilter = function() {
    	 $scope.regionData = [];
    	 $scope.countryData = [];
         $scope.locationData = [];
         $scope.operatorData = [];
         $scope.ownerData = [];
         $scope.statusData = [];
         
         HttpService.get('/refineries/regions').then(function(resp) {
             for (var i = 0; i < resp.length; i++) {
                 var obj = {
                     id: resp[i].region,
                     label: resp[i].region
                 }
                 $scope.regionData.push(obj);
             }
             $scope.regionData = $scope.sortedOrder($scope.regionData);
         });
//         HttpService.get('/refineries/locations').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].location,
//                     label: resp[i].location
//                 }
//                 $scope.locationData.push(obj);
//             }
//             $scope.locationData = $scope.sortedOrder($scope.locationData);
//         });
//
//         HttpService.get('/refineries/operators').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].operator,
//                     label: resp[i].operator
//                 }
//                 $scope.operatorData.push(obj);
//             }
//             $scope.operatorData = $scope.sortedOrder($scope.operatorData);
//         });
//
//         HttpService.get('/refineries/owners').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].owner,
//                     label: resp[i].owner
//                 }
//                 $scope.ownerData.push(obj);
//
//             }
//             $scope.ownerData = $scope.sortedOrder($scope.ownerData);
//         });


     };

     $rootScope.loadStorageFilter = function() {
    	 $scope.regionData = [];
    	 $scope.countryData = [];
         $scope.locationData = [];
         $scope.operatorData = [];
         $scope.ownerData = [];
         $scope.statusData = [];
         
         HttpService.get('/storage/regions').then(function(resp) {
             for (var i = 0; i < resp.length; i++) {
                 var obj = {
                     id: resp[i].region,
                     label: resp[i].region
                 }
                 $scope.regionData.push(obj);
             }
             $scope.regionData = $scope.sortedOrder($scope.regionData);
         });

//         HttpService.get('/storage/locations').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].location,
//                     label: resp[i].location
//                 }
//                 $scope.locationData.push(obj);
//             }
//             $scope.locationData = $scope.sortedOrder($scope.locationData);
//         });
//
//         HttpService.get('/storage/operators').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].operator,
//                     label: resp[i].operator
//                 }
//                 $scope.operatorData.push(obj);
//             }
//             $scope.operatorData = $scope.sortedOrder($scope.operatorData);
//         });
//
//         HttpService.get('/storage/owners').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].owner,
//                     label: resp[i].owner
//                 }
//                 $scope.ownerData.push(obj);
//
//             }
//             $scope.ownerData = $scope.sortedOrder($scope.ownerData);
//         });


     };

     $rootScope.loadProductionCompanyFilter = function() {
    	 $scope.assetCountryData = [];
         $scope.countrySettings = {
             enableSearch: true,
             scrollable: true,
             selectionLimit: 1
         };
         HttpService.get('/production/company/countries').then(function(resp) {
           for (var i = 0; i < resp.length; i++) {
               var obj = {
                   id: resp[i].country,
                   label: resp[i].country
               }
               $scope.assetCountryData.push(obj);
           }
           $scope.assetCountryData = $scope.sortedOrder($scope.assetCountryData);
       });
     };
     
     $rootScope.loadProductionAssetFilter = function() {
    	 $scope.regionData = [];
    	 $scope.countryData = [];
    	 
    	 HttpService.get('/production/asset/regions').then(function(resp) {
             for (var i = 0; i < resp.length; i++) {
                 var obj = {
                     id: resp[i].region,
                     label: resp[i].region
                 }
                 $scope.regionData.push(obj);
             }
             $scope.regionData = $scope.sortedOrder($scope.regionData);
         });
     };


     $rootScope.loadPipelinesDomesticFilter = function() {
    	 $scope.regionData = [];
    	 $scope.countryData = [];
    	 $scope.pdCommodityData = [];
    	 $scope.pdStartPointData = [];
    	 $scope.pdEndPointData = [];
    	 $scope.statusData = [];
    	 
    	 HttpService.get('/pipeline/regions').then(function(resp) {
             for (var i = 0; i < resp.length; i++) {
                 var obj = {
                     id: resp[i].region,
                     label: resp[i].region
                 }
                 $scope.regionData.push(obj);
             }
             $scope.regionData = $scope.sortedOrder($scope.regionData);
         });
//    	 HttpService.get('/pipeline/commodities').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].commodity,
//                     label: resp[i].commodity
//                 }
//                 $scope.pdCommodityData.push(obj);
//             }
//             $scope.pdCommodityData = $scope.sortedOrder($scope.pdCommodityData);
//         });
//    	 
//    	 HttpService.get('/pipeline/startpoints').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].startPoint,
//                     label: resp[i].startPoint
//                 }
//                 $scope.pdStartPointData.push(obj);
//             }
//             $scope.pdStartPointData = $scope.sortedOrder($scope.pdStartPointData);
//         });
//    	 HttpService.get('/pipeline/endpoints').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].endPoint,
//                     label: resp[i].endPoint
//                 }
//                 $scope.pdEndPointData.push(obj);
//             }
//             $scope.pdEndPointData = $scope.sortedOrder($scope.pdEndPointData);
//         });
//    	 
//    	 HttpService.get('/status').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].status,
//                     label: resp[i].status
//                 }
//                 $scope.statusData.push(obj);
//             }
//             $scope.statusData = $scope.sortedOrder($scope.statusData);
//         });
     }

     $rootScope.loadExplorationFilter = function() {
    	 $scope.regionData = [];
    	 $scope.countryData = [];
    	 $scope.basinData = [];
         $scope.operatorData = [];
         $scope.ownerData = [];
         $scope.statusData = [];
         $scope.unitsData = [];
         $scope.typeData = [];         
         /* KMs, Miles (Hardcode this values)
	Type – Onshore, Offshore (Hardcode this values)*/
         //console.log("in /loadExplorationFilter")

         $scope.unitsData = [{
             id: 'KMs',
             label: "KMs"
         }, {
             id: 'Miles',
             label: "Miles"
         }];
//         $scope.typeData = [{
//             id: 'Onshore',
//             label: "Onshore"
//         }, {
//             id: 'Offshore',
//             label: "Offshore"
//         }];
         
         HttpService.get('/exploration/regions').then(function(resp) {
             for (var i = 0; i < resp.length; i++) {
                 var obj = {
                     id: resp[i].region,
                     label: resp[i].region
                 }
                 $scope.regionData.push(obj);
             }
             $scope.regionData = $scope.sortedOrder($scope.regionData);
         });
         
//         HttpService.get('/exploration/basins').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].basin,
//                     label: resp[i].basin
//                 }
//                 $scope.basinData.push(obj);
//
//             }
//             $scope.basinData = $scope.sortedOrder($scope.basinData);
//         });
//
//
//         HttpService.get('/exploration/operators').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].operator,
//                     label: resp[i].operator
//                 }
//                 $scope.operatorData.push(obj);
//             }
//             $scope.operatorData = $scope.sortedOrder($scope.operatorData);
//         });
//
//         HttpService.get('/exploration/owners').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].owner,
//                     label: resp[i].owner
//                 }
//                 $scope.ownerData.push(obj);
//
//             }
//             $scope.ownerData = $scope.sortedOrder($scope.ownerData);
//         });
     }


     $rootScope.loadSmallScaleFilter = function() {

         $scope.regionData = [];
         $scope.countryData = [];
         $scope.companyData=[];
         $scope.technologyProviderData=[];
         $scope.technologyData=[];
         $scope.locationData = [];
         $scope.statusData = [];
         $scope.typeData = [];


         HttpService.get('/smallscalelng/regions').then(function(resp) {
             for (var i = 0; i < resp.length; i++) {
                 var obj = {
                     id: resp[i].region,
                     label: resp[i].region
                 }
                 $scope.regionData.push(obj);
             }
             $scope.regionData = $scope.sortedOrder($scope.regionData);
         });

//         HttpService.get('/smallscalelng/countries').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].country,
//                     label: resp[i].country
//                 }
//                 $scope.countryData.push(obj);
//             }
//             $scope.countryData = $scope.sortedOrder($scope.countryData);
//         });
//
//         HttpService.get('/smallscalelng/locations').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].location,
//                     label: resp[i].location
//                 }
//                 $scope.locationData.push(obj);
//             }
//             $scope.locationData = $scope.sortedOrder($scope.locationData);
//         });
//
//         HttpService.get('/smallscalelng/statuses').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].status,
//                     label: resp[i].status
//                 }
//                 $scope.statusData.push(obj);
//             }
//             $scope.statusData = $scope.sortedOrder($scope.statusData);
//         });
//
//
//         HttpService.get('/smallscalelng/types').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].type,
//                     label: resp[i].type
//                 }
//                 $scope.typeData.push(obj);
//             }
//             $scope.typeData = $scope.sortedOrder($scope.typeData);
//         });
//
//         HttpService.get('/smallscalelng/companies').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].company,
//                     label: resp[i].company
//                 }
//                 $scope.companyData.push(obj);
//             }
//             $scope.companyData = $scope.sortedOrder($scope.companyData);
//         });
//
//         HttpService.get('/smallscalelng/technologyproviders').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].technologyprovider,
//                     label: resp[i].technologyprovider
//                 }
//                 $scope.technologyProviderData.push(obj);
//             }
//             $scope.technologyProviderData = $scope.sortedOrder($scope.technologyProviderData);
//         });
//
//         HttpService.get('/smallscalelng/technologies').then(function(resp) {
//             for (var i = 0; i < resp.length; i++) {
//                 var obj = {
//                     id: resp[i].technology,
//                     label: resp[i].technology
//                 }
//                 $scope.technologyData.push(obj);
//             }
//             $scope.technologyData = $scope.sortedOrder($scope.technologyData);
//         });




     };



 });

 angular.module('OGAnalysis').controller('HeaderCtrl', function($scope, $state, $rootScope, URL, HttpService, $timeout) {
	 
	 
	 $scope.changePwdErrorMsg="";
	 $scope.changePwdErrorMsg1="";
	 
	 $rootScope.changePwdObj ={
			 currpwd:"",
	 		 newpwd:"",
	 		 confirmpwd:""
	 }
	 
	
     $rootScope.toggleNav = function() {

         if ($rootScope.table.liquefactionInst != undefined && $rootScope.table.liquefactionInst != "") {

             //$timeout(function(){
             $rootScope.table.liquefactionInst.draw();
             //},100);
         }

         if ($rootScope.table.regasificationInst != undefined && $rootScope.table.regasificationInst != "") {

             //$timeout(function(){
             $rootScope.table.regasificationInst.draw();
             //},100);
         }
     };

     $(".sidebar-menu .side-item").click(function() {
         //$('body').removeClass('control-sidebar-open');
         //$('body').addClass('sidebar-collapse');

         if ($rootScope.table.liquefactionInst != undefined && $rootScope.table.liquefactionInst != "") {

             $timeout(function() {
                 if ($rootScope.table.liquefactionInst != undefined && $rootScope.table.liquefactionInst != "")
                     $rootScope.table.liquefactionInst.draw();
             }, 100);
         }

         if ($rootScope.table.regasificationInst != undefined && $rootScope.table.regasificationInst != "") {

             $timeout(function() {
                 if ($rootScope.table.regasificationInst != undefined && $rootScope.table.regasificationInst != "")
                     $rootScope.table.regasificationInst.draw();
             }, 100);
         }


     })

     $scope.userName = HttpService.getUserName();
     
     $scope.showChangePwdModal=function(){ 
    	 $rootScope.changePwdObj.currpwd="";
    	 $rootScope.changePwdObj.newpwd="";
    	 $rootScope.changePwdObj.confirmpwd="";
		 $("#myModalChangePwd").modal("show");			 
     }
         
     $scope.changePwd=function(){    	
    	 
		   HttpService.get("/changepwd",$rootScope.changePwdObj).then(function(resp) {
//				$http(request).success(function (resp){								 
					 if(resp.st == 'success'){									 								
						 $scope.changePwdErrorMsg = "Successfully Changed Password.";									 
						 $timeout(function(){	
							 $scope.changePwdErrorMsg = "";
							 $("#myModalChangePwd").modal("hide");
						 },3000)
					 }else if(resp.st =='incorrect'){
						 //alert("login failed!")
						 $scope.changePwdErrorMsg = "Invalid Current Password.";									 
						 $timeout(function(){
							 $scope.changePwdErrorMsg = "";
						 },3000)
					 }else if(resp.st=='fail'){
						 $scope.changePwdErrorMsg = "Unable to Change Password.";
						 $scope.changePwdErrorMsg1="Please contact sales@ogexplorer.com.";
						 $timeout(function(){
							 $scope.changePwdErrorMsg = "";
							 $scope.changePwdErrorMsg1="";
						 },3000)
					 }
					 
				});
			
     }

     $scope.logout = function() {
         HttpService.get('/logout').then(function(resp) {
             if (resp == "login") {
                 localStorage.setItem("oganalysis-ud-name", null);
                 window.location.href = window.location.origin + URL.apiversion + '/'
             }
         });
     }
 });
 
 

 angular.module('OGAnalysis').controller('CommonCtrl', function($scope, $state, $rootScope, URL, HttpService) {
     console.log("In common ctrl");
     console.log($state);

     $scope.formData = new FormData();
     $scope.formDataJSON = {};
     $scope.selectedRegions = [];
     $scope.selectedCountries = [];
     $scope.selectedSectors = [];


     $scope.setConfigurations = function() {

         if ($state.current.name == "exploration") {
             $scope.url = "/exploration";
             $rootScope.filterObj = {
                 regionField: false,
                 countryField: false,
                 locationField: false,
                 operatorField: false,
                 ownerField: false,
                 statusField: false,
                 unitsField: false,
                 offshoreField: false,
                 typeField: true,
                 sectorField: false
             };
         } else if ($state.current.name == "lng") {
             $scope.url = "/lng";
             $rootScope.filterObj = {
                 regionField: true,
                 countryField: true,
                 locationField: true,
                 operatorField: true,
                 ownerField: true,
                 statusField: true,
                 unitsField: true,
                 offshoreField: true,
                 typeField: true,
                 sectorField: false
             };
         } else if ($state.current.name == "pipelines") {
             $scope.url = "/pipeline";
             $rootScope.filterObj = {
                 regionField: false,
                 countryField: false,
                 locationField: false,
                 operatorField: true,
                 ownerField: true,
                 statusField: false,
                 unitsField: true,
                 offshoreField: false,
                 typeField: false,
                 sectorField: false
             };
             $scope.columns = [{
                     title: "Name",
                     data: "name"
                 },
                 {
                     title: "Region",
                     data: "region"
                 },
                 {
                     title: "Country",
                     data: "country"
                 }

             ];
         } else if ($state.current.name == "crude") {
             $scope.url = "/crudeoil";
             $rootScope.filterObj = {
                 regionField: false,
                 countryField: false,
                 locationField: false,
                 operatorField: true,
                 ownerField: true,
                 statusField: false,
                 unitsField: true,
                 offshoreField: false,
                 typeField: false,
                 sectorField: false
             };
         } else if ($state.current.name == "naturalgas") {
             $scope.url = "/naturalgas";
             $rootScope.filterObj = {
                 regionField: false,
                 countryField: false,
                 locationField: false,
                 operatorField: true,
                 ownerField: true,
                 statusField: false,
                 unitsField: true,
                 offshoreField: false,
                 typeField: false,
                 sectorField: false
             };
             $scope.columns = [{
                     title: "Name",
                     data: "name"
                 },
                 {
                     title: "Region",
                     data: "region"
                 },
                 {
                     title: "Country",
                     data: "country"
                 }

             ];
         } else if ($state.current.name == "refineries") {
             $scope.url = "/refinery";
             $rootScope.filterObj = {
                 regionField: false,
                 countryField: false,
                 locationField: false,
                 operatorField: true,
                 ownerField: true,
                 statusField: false,
                 unitsField: true,
                 offshoreField: false,
                 typeField: false,
                 sectorField: false
             };
         } else if ($state.current.name == "storage") {
             $scope.url = "/storage";
             $rootScope.filterObj = {
                 regionField: false,
                 countryField: false,
                 locationField: false,
                 operatorField: true,
                 ownerField: true,
                 statusField: false,
                 unitsField: true,
                 offshoreField: false,
                 typeField: false,
                 sectorField: false
             };

             $scope.columns = [{
                     title: "Name",
                     data: "name"
                 },
                 {
                     title: "Region",
                     data: "region"
                 },
                 {
                     title: "Country",
                     data: "country"
                 }

             ];
         } else if ($state.current.name == "reports") {
             $scope.url = "/pdfReports";
             $rootScope.loadReportsFilter();
             $rootScope.filterObj = {
                 regionField: true,
                 countryField: true,
                 locationField: false,
                 operatorField: false,
                 ownerField: false,
                 statusField: false,
                 unitsField: false,
                 offshoreField: false,
                 typeField: false,
                 sectorField: true
             };

             $scope.columns = [{
                 title: "Title",
                 data: "reportName"
             }];
         }

     };

     $scope.setDisplayPeriod = function() {
         for (var i = URL.displayFrom; i <= URL.displayTo; i++) {
             var obj = {
                 id: i,
                 name: i
             }
             $scope.displayPeriodList.push(obj);
         }
     }


     openModel = function() {
         //console.log("in model");
         $('#myModal').modal("show")

     };

     $scope.generateFormData = function(ary, key) {
         for (var i = 0; i < ary.length; i++) {
             var fromkey = key + i
             $scope.formData.append(fromkey, ary[i].id);
             $scope.formDataJSON[fromkey] = ary[i].id;
         }
     }
//     $rootScope.loadCountries = function(){
//    	 
//    	 
//    	 if($rootScope.filterObj.regionField == true){
// 			$scope.generateFormData($rootScope.regionModel,'region');
//  		}
//    	 
////    	 HttpService.get('/lng/countries').then(function(resp) {
////           for (var i = 0; i < resp.length; i++) {
////               var obj = {
////                   id: resp[i].country,
////                   label: resp[i].country
////               }
////               $scope.countryData.push(obj);
////           }
////           $scope.countryData = $scope.sortedOrder($scope.countryData);
////       });
//    	 
//     }
     $rootScope.filterSubmit = function() {

         if ($rootScope.filterObj.regionField == true) {
             $scope.generateFormData($rootScope.regionModel, 'region');
         }
         if ($rootScope.filterObj.countryField == true) {
             $scope.generateFormData($rootScope.countryModel, 'country');
         }
         if ($rootScope.filterObj.locationField == true) {

         }
         if ($rootScope.filterObj.operatorField == true) {

         }
         if ($rootScope.filterObj.ownerField == true) {

         }
         if ($rootScope.filterObj.statusField == true) {

         }
         if ($rootScope.filterObj.unitsField == true) {
             if ($rootScope.unitsModel.id != undefined) {
                 $rootScope.unitsModel.push({
                     id: $rootScope.unitsModel.id
                 });
             } else if ($rootScope.unitsModel.length > 0) {

             } else {
                 $rootScope.unitsModel.length = 0;
             }
             $scope.generateFormData($rootScope.unitsModel, 'units');
         }
         if ($rootScope.filterObj.offshoreField == true) {
             $scope.generateFormData($rootScope.offshoreModel, 'offonshore');
         }
         if ($rootScope.filterObj.typeField == true) {
             $scope.generateFormData($rootScope.typeModel, 'type');
         }
         if ($rootScope.filterObj.sectorField == true) {
             $scope.generateFormData($rootScope.sectorModel, 'sector');
         }

         if ($rootScope.filterObj.assetTypeField == true) {
             $scope.generateFormData($rootScope.assetTypeModel, 'type');
         }

         if ($rootScope.filterObj.assetTypeField == true) {
             $scope.generateFormData($rootScope.assetUnitModel, 'unit');
         }


         HttpService.getHttp($scope.url, $scope.formDataJSON).then(function(resp) {
             if ($rootScope.table.inst != "") {
                 if (resp != "" && resp != undefined) {
                     resp = resp;
                 } else {
                     resp = [];
                 }
                 $scope.formDataJSON = {};
                 $rootScope.table.inst.clear().draw();
                 $rootScope.table.inst.rows.add(resp);
                 $rootScope.table.inst.draw();
             }
         });
     };

     $rootScope.onFilterSelect = function(item, filterType) {
         //console.log($scope.countryModel);

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

     $rootScope.typeChangeFn = function() {

     };

     $rootScope.resetFilter = function() {
         console.log("IN side common ctrl");
         $rootScope.regionModel = [];
         $rootScope.countryModel = [];
         $rootScope.locationModel = [];
         $rootScope.operatorModel = [];
         $rootScope.ownerModel = [];
         $rootScope.statusModel = [];
         $rootScope.unitsModel = [];
         $rootScope.offshoreModel = [];
         $rootScope.typeModel = [];
         $rootScope.sectorModel = [];
         $rootScope.assetTypeModel = [];
         $rootScope.assetUnitModel = [];
         $scope.formDataJSON = {};


         $rootScope.companyModel = [];
         $rootScope.technologyProviderModel = [];
         $rootScope.technologyModel = [];


         if ($scope.url != '') {
             HttpService.get($scope.url).then(function(resp) {
                 $scope.gridDataList = angular.copy(resp);
                 if ($rootScope.table.inst != "") {
                     if (resp != "" && resp != undefined) {
                         resp = resp;
                     } else {
                         resp = [];
                     }
                     $scope.formDataJSON = {};
                     $rootScope.table.inst.clear().draw();
                     $rootScope.table.inst.rows.add(resp);
                     $rootScope.table.inst.draw();
                 }
             });
         }         
         $rootScope.loadReportsFilter();
     };


     $scope.init = function() {
         $scope.title = $state.current.name;
         $scope.gridDataList = [];
         $scope.displayPeriodList = [];
         $scope.url = "";
         $rootScope.tableInst = "";
         $rootScope.table = {
             inst: ""
         };
                
         
         $scope.occurrenceOptions = [{
             name: "Country",
             value: "country",
             checked: true
         }, {
             name: "Company",
             value: "company",
             checked: false
         }, {
             name: "Terminal",
             value: "terminal",
             checked: false
         }];


         $scope.columns = [{
                 title: "Region",
                 data: "region"
             },
             {
                 title: "Status",
                 data: "status"
             },
             {
                 title: "Country",
                 data: "country"
             },
             {
                 title: "Block No",
                 data: "blockNo"
             }
         ];

         $rootScope.searchFilterObj = {
             displayType: "country",
             startDate: "2016",
             endDate: "2020"
         };

         $scope.setConfigurations();
         $scope.setDisplayPeriod();
//         $rootScope.resetFilter();

         if ($scope.url != '') {
             HttpService.get($scope.url).then(function(resp) {
                 $scope.gridDataList = angular.copy(resp);
                 var tableInst = $("#example1").DataTable({
                     "drawCallback": function(settings) {
                         if (!$("#example1").parent().hasClass("table-responsive")) {
                             $("#example1").wrap("<div class='table-responsive'></div>");
                         }
                     },
                     "columnDefs": [{
                         // The `data` parameter refers to the data for the cell (defined by the
                         // `data` option, which defaults to the column being worked with, in
                         // this case `data: 0`.
                         "render": function(data, type, row) {
                             var commonHref = "";
                             if ($scope.url == "/pdfReports") {
                                 commonHref = '<a href="pdf/reports/' + data + '">' + data + '</a>';
                             } else if ($scope.url == "/capacity") {
                                 if (data != 'Total') {
                                     commonHref = '<a data-toggle="modal" href="#myModal">' + data + '</a>';
                                 } else {
                                     commonHref = '<p>' + data + '</p>';
                                 }
                             } else {
                                 commonHref = '<a data-toggle="modal" href="#myModal">' + data + '</a>'
                             }
                             return commonHref;
                         },
                         "targets": 0
                     }],
                     columns: $scope.columns,
                     data: $scope.gridDataList
                 });
                 $rootScope.table.inst = tableInst;
             });
         }
     }

 });