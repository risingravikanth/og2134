 angular.module('OGAnalysis').controller('InfrastructureCtrl', function($scope,$state,$rootScope,URL,HttpService) {
	console.log("In Infra ctrl");
 	console.log($state);
 	
 	$scope.setConfigurations = function(){
 		$scope.url = "/infrastructure";
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
		
		$scope.columns = [
			{ title: "Terminal Name",
				  data: "terminalName"
			},
 	 		{ title: "Status",
			  data: "status"
			},
			{ title: "Start Year",
				  data: "startYear"
			},
			{ title: "Location",
				  data: "location"
			},
			{ title: "Technology",
			  data: "technology"
			},
			{ title: "#Train",
				  data: "train"
			},
			{ title: "Operator",
				  data: "operator"
			},
	 		{ title: "Storage Capacity",
			  data: "storageCapacity"
			},
	 		{ title: "#Tanks",
			  data: "tanks"
			}
	 	];
	};
	
	$scope.destroyTable = function(){
 		if($rootScope.table.liquefactionInst != undefined && $rootScope.table.liquefactionInst != "" ){
 			$rootScope.table.liquefactionInst.destroy();
 			$("#liquefaction").html('');
 		}
 			
 		if($rootScope.table.regasificationInst != undefined && $rootScope.table.regasificationInst != ""){
 			$rootScope.table.regasificationInst.destroy();
 			$("#regasification").html('');
 		}
 			
 	};
 	
 	$rootScope.inItDataTable = function(){
 		
 		if ( $.fn.dataTable.isDataTable( '#liquefaction' ) ) {
 		    table = $('#liquefaction').DataTable();
 		}
 		else {
 			var liquefactionInst = $("#liquefaction").DataTable({
 				"columnDefs": [
 				{
 					// The `data` parameter refers to the data for the cell (defined by the
 					// `data` option, which defaults to the column being worked with, in
 					// this case `data: 0`.
 					"render": function ( data, type, row ) {
 						var commonHref = "";
 				 			commonHref =  '<p>'+data+'</p>';
 				 		return commonHref;
 					},
 					"targets": 0
 							}
 							],
 							scrollX: true,
 							columns: $scope.columns,
 							data : $scope.liquefactionData
 				});
 			
 				$rootScope.table.liquefactionInst = liquefactionInst;
 			}
 	 		
	 		if ( $.fn.dataTable.isDataTable( '#regasification' ) ) {
	 		    table = $('#regasification').DataTable();
	 		}
	 		else {
	 			var regasificationInst = $("#regasification").DataTable({
	 				"columnDefs": [
	 				{
	 					// The `data` parameter refers to the data for the cell (defined by the
	 				// `data` option, which defaults to the column being worked with, in
	 				// this case `data: 0`.
	 				"render": function ( data, type, row ) {
	 				 		var commonHref =  '<p>'+data+'</p>';
	 				 		return commonHref;
	 				},
	 				"targets": 0
	 							}
	 							],
	 							scrollX: true,
	 							columns: $scope.columns,
	 							data : $scope.regasificationData
	 				 });
	 			$rootScope.table.regasificationInst = regasificationInst;
	 		}
		 			
  	};
  	
  	$scope.generateFormData = function(ary,key){
		for(var i=0;i< ary.length; i++){
			var fromkey = key+i
	 		$rootScope.capacityFilterJSON[fromkey] = ary[i].id;
 		}
	}
  	
  	$rootScope.filterSubmit = function(){
		$scope.destroyTable();
		$rootScope.capacityFilterJSON ={};
		
		if($rootScope.filterObj.regionField == true){
			$scope.generateFormData($rootScope.regionModel,'region');
 		}
		if($rootScope.filterObj.countryField == true){
 			$scope.generateFormData($rootScope.countryModel,'country');
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
 		if($rootScope.filterObj.unitsField == true){
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
 		
  		for(var key in $rootScope.searchFilterObj){
 			$rootScope.capacityFilterJSON[key] = $rootScope.searchFilterObj[key];
  		}
 
 		HttpService.getHttp($scope.url,$rootScope.capacityFilterJSON).then(function(resp) {
			 	 if(resp != "" && resp != undefined ){
	 					resp = resp;
	 				}else{
	 					resp = [];
	 				}
				 
 			
				$scope.gridDataList = [];
				$scope.liquefactionData =[];
				$scope.regasificationData =[];
				for(var k=0; k < resp.length; k++){
					if(resp[k].type == URL.liquefactionType){
				 		$scope.liquefactionData = resp[k]['data'];
 					}

					if(resp[k].type == URL.regasificationType){
					 	$scope.regasificationData = resp[k]['data'];
		 			}
				}
				$rootScope.inItDataTable();
	 	});
 	};
  	
  	$rootScope.resetFilter = function(){
		console.log("In side infra ctrl reset filter");
		$scope.destroyTable();
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
 		$rootScope.capacityFilterJSON ={};
 		
 		if($scope.url != ''){
			var resetReq = angular.copy($rootScope.searchFilterObj);
	 		HttpService.getHttp($scope.url,resetReq).then(function(resp) {
	 			
	 			
	 			if(resp != "" && resp != undefined ){
 					resp = resp;
 				}else{
 					resp = [];
 				}
			 
	 			if(resp != "" && resp != undefined){
					$scope.gridDataList = angular.copy(resp);
		 			
		 	 	 	$scope.gridDataList = [];
					for(var k=0; k < resp.length; k++){
						if(resp[k].type == URL.liquefactionType){
					 		$scope.liquefactionData = resp[k]['data'];
	 					}

						if(resp[k].type == URL.regasificationType){
						 	$scope.regasificationData = resp[k]['data'];
			 			}
						
					}
			 		
					$rootScope.inItDataTable();
	 			}
	 		});
		}
   	};
	
	
 	$scope.init = function(){
		$scope.title = $state.current.name;
		$scope.gridDataList = [];
		$scope.liquefactionData = [];
		$scope.regasificationData = [];
		$scope.url = "";
		$rootScope.capacityFilterJSON = {};
		
		$rootScope.table = {
				liquefactionInst : "",
				regasificationInst:"",
				modelDatatableInst:"",
				terminalDatatableInst:""
		};
		$rootScope.searchFilterObj = {
 		};
		
		$scope.setConfigurations();
		
		$scope.destroyTable();
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
 		$rootScope.capacityFilterJSON ={};
 		
 		if($scope.url != ''){
			var initailReq = angular.copy($rootScope.searchFilterObj)
	 		HttpService.getHttp($scope.url,initailReq).then(function(resp) {
	 			
	 			
	 			if(resp != "" && resp != undefined ){
 					resp = resp;
 				}else{
 					resp = [];
 				}
	 			$scope.gridDataList = [];
	 			if(resp != "" && resp != undefined){
					$scope.gridDataList = angular.copy(resp);
			 		for(var k=0; k < resp.length; k++){
			 			if(resp[k].type == URL.liquefactionType){
					 		$scope.liquefactionData = resp[k]['data'];
	 					}

						if(resp[k].type == URL.regasificationType){
						 	$scope.regasificationData = resp[k]['data'];
			 			}
			 		}
			 		
					$rootScope.inItDataTable();
	 			}
	 		});
		}
 	};
 	
 });