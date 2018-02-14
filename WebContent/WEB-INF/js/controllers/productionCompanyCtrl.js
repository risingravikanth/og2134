


 angular.module('OGAnalysis').controller('ProductionCompanyCtrl', function($scope,$state,$rootScope,URL,HttpService) {
	console.log("In ProductionCompanyCtrl");
 	console.log($state)
	
 	$scope.setConfigurations = function(){
	 		$scope.url = "/production/company";
			$rootScope.filterObj = {
				regionField :false,
				countryField :false,
				locationField : false,
				operatorField : false,
				ownerField : false,
				statusField : false,
				unitsField : false,
				offshoreField : false,
				typeField :false,
				assetTypeField :true,
				assetUnitField :true,
				assetCountryField :true
			};
			$rootScope.loadProductionCompanyFilter()
 	};
	
	$scope.setDisplayPeriod = function(){
		for(var i = URL.displayFrom_2005;i <= URL.displayTo_2017 ;i++){
			var obj = {
				id : i,
				name : i
			}
			$scope.displayPeriodList.push(obj);
		}
		
	};
 	 
	$rootScope.typeChangeFn = function(){
		if(parseInt($rootScope.searchFilterObj.endDate) >= parseInt($rootScope.searchFilterObj.startDate)){
			$scope.destroyTable();
			
			for(var key in $rootScope.searchFilterObj){
	 			$rootScope.capacityFilterJSON[key] = $rootScope.searchFilterObj[key];
	  		}
			 
			HttpService.getHttp($scope.url,$rootScope.capacityFilterJSON).then(function(resp) {
				 if($rootScope.table.liquefactionInst != ""){
					 
					if(resp != "" && resp != undefined ){
	 					resp = resp;
	 				}else{
	 					resp = [];
	 				}
				 
			 		$scope.columns =[];
			 		$scope.liquefactionData = [];
			 		$scope.regasificationData =[];
			 		 
		 	  		 	
			 		if(resp[0] != undefined){
						var columnName = $rootScope.searchFilterObj.displayType.charAt(0).toUpperCase() +  $rootScope.searchFilterObj.displayType.slice(1);
						$scope.columns.push({title:columnName  ,data:"name"});
						for(var key in resp[0][$rootScope.searchFilterObj.displayType][0]){
							if(key != "name"){
								var colObj = {
										title:key.toUpperCase(),
										data:key
								};
								
								$scope.columns.push(colObj);
							}
				 	  	}
					}
					$scope.gridDataList = [];
					$scope.loadTableData(resp);
			 		$rootScope.inItDataTable();
				 }
		 	});
 		}else{
 			alert("Display period From is always greater than Dispaly period to field.")
 		}
		
		
	};
	
	$scope.generateFormData = function(ary,key){
		for(var i=0;i< ary.length; i++){
			var fromkey = key+i
	 		$rootScope.capacityFilterJSON[fromkey] = ary[i].id;
 		}
	}
	
	$rootScope.filterSubmit = function(){
		$scope.CO_unit = "Kb/d";
 		$scope.NG_unit = "Mmcf/d";
		$scope.destroyTable();
		$rootScope.capacityFilterJSON = {};
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
 		if($rootScope.filterObj.assetTypeField == true){
 		 	if($rootScope.assetTypeModel.id != undefined){
 		 		$rootScope.assetTypeModel.length =0;
 				$rootScope.assetTypeModel.push({id:$rootScope.assetTypeModel.id});
 			}else if ($rootScope.assetTypeModel.length >0){
 				if($rootScope.assetTypeModel.id == undefined)
						$rootScope.assetTypeModel.length =0
 	 		}else{
 				$rootScope.assetTypeModel.length =0;
 			}
 			$scope.generateFormData($rootScope.assetTypeModel,'type');
 		}
 		if($rootScope.filterObj.assetUnitField == true){
 	 		if($rootScope.assetUnitModel.id != undefined){
 	 			$rootScope.assetUnitModel.length =0;
 				$rootScope.assetUnitModel.push({id:$rootScope.assetUnitModel.id});
 				$scope.CO_unit = $rootScope.assetUnitModel.id;
 	 	 		$scope.NG_unit = $rootScope.assetUnitModel.id;
 			}else if ($rootScope.assetUnitModel.length >0){
 					if($rootScope.assetUnitModel.id == undefined)
 						$rootScope.assetUnitModel.length =0
 	 		}else{
 				$rootScope.assetUnitModel.length =0;
 			}
 			$scope.generateFormData($rootScope.assetUnitModel,'unit');
 			
 		}
 		if($rootScope.filterObj.assetCountryField == true){
 	 		if($rootScope.assetCountryModel.id != undefined){
 	 			$rootScope.assetCountryModel.length =0;
 				$rootScope.assetCountryModel.push({id:$rootScope.assetCountryModel.id});
 		 	}else if ($rootScope.assetCountryModel.length >0){
 					if($rootScope.assetCountryModel.id == undefined)
 						$rootScope.assetCountryModel.length =0
 	 		}else{
 				$rootScope.assetCountryModel.length =0;
 			}
 			$scope.generateFormData($rootScope.assetCountryModel,'country');
 			
 		}
 		
 		
  		for(var key in $rootScope.searchFilterObj){
 			$rootScope.capacityFilterJSON[key] = $rootScope.searchFilterObj[key];
  		}
 
 		HttpService.getHttp($scope.url,$rootScope.capacityFilterJSON).then(function(resp) {
			 if($rootScope.table.liquefactionInst != ""){
				 if(resp != "" && resp != undefined ){
	 					resp = resp;
	 				}else{
	 					resp = [];
	 				}
				 
				
		 		$scope.columns =[];
		 		if(!Array.isArray(resp)){
		 			var tempresp = resp;
		 			resp = [];
		 			resp.push(tempresp);
		 		}
			  
		 		if(resp[0] != undefined){
					var columnName = $rootScope.searchFilterObj.displayType.charAt(0).toUpperCase() +  $rootScope.searchFilterObj.displayType.slice(1);
					$scope.columns.push({title:columnName  ,data:"name"});
					for(var key in resp[0][$rootScope.searchFilterObj.displayType][0]){
						if(key != "name"){
							var colObj = {
									title:key.toUpperCase(),
									data:key
							};
							
							$scope.columns.push(colObj);
						}
			 	  	}
				}
				$scope.gridDataList = [];
				$scope.loadTableData(resp);
		 		$rootScope.inItDataTable();
				
	 		 }
	 	});
 	};
 	
 	$rootScope.onFilterSelect = function(item,filterType){
 		console.log($scope.countryModel);
 		
  	};
 	
 	$scope.destroyTable = function(){
 		if($rootScope.table.liquefactionInst != undefined && $rootScope.table.liquefactionInst != "" ){
 			$rootScope.table.liquefactionInst.destroy();
 			$("#liquefaction").empty();
 		}
 			
 		if($rootScope.table.regasificationInst != undefined && $rootScope.table.regasificationInst != ""){
 			$rootScope.table.regasificationInst.destroy();
 			$("#regasification").empty();
 		}
 			
 	};
 	
 	$rootScope.inItDataTable = function(){
 		
 		if ( $.fn.dataTable.isDataTable( '#liquefaction' ) ) {
 		    table = $('#liquefaction').DataTable();
 		}
 		else {
 			var liquefactionInst = $("#liquefaction").DataTable({
 				"drawCallback": function( settings ) {
 					if(!$("#liquefaction").parent().hasClass("table-responsive")){
 						$("#liquefaction").wrap( "<div class='table-responsive'></div>" );
				     }
 		 	    },
 				"columnDefs": [
 				{
 					// The `data` parameter refers to the data for the cell (defined by the
 					// `data` option, which defaults to the column being worked with, in
 					// this case `data: 0`.
 					"render": function ( data, type, row ) {
 						return '<p>'+data+'</p>';
 					},
 					"targets": 0
 							}
 							],
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
	 				"drawCallback": function( settings ) {
	 					if(!$("#regasification").parent().hasClass("table-responsive")){
	 						$("#regasification").wrap( "<div class='table-responsive'></div>" );
					     }
	 		 	    },
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
	 					 		columns: $scope.columns,
	 							data : $scope.regasificationData
	 				 });
	 			$rootScope.table.regasificationInst = regasificationInst;
	 		}
 			 
  	};
  	
  	$rootScope.resetFilter = function(){
		console.log("In side capacity ctrl reset filter");
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
 		$rootScope.assetTypeModel =[];
 		$rootScope.assetUnitModel =[];
 		$rootScope.assetCountryModel = [];
 		$rootScope.capacityFilterJSON ={};
 		$scope.CO_unit = "Kb/d";
 		$scope.NG_unit = "Mmcf/d";
 		
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
					$scope.columns =[];
					
					if($scope.gridDataList[0] != undefined){
						var columnName = $rootScope.searchFilterObj.displayType.charAt(0).toUpperCase() +  $rootScope.searchFilterObj.displayType.slice(1);
						$scope.columns.push({title:columnName  ,data:"name"});
						for(var key in $scope.gridDataList[0][$rootScope.searchFilterObj.displayType][0]){
							if(key != "name"){
								var colObj = {
										title:key.toUpperCase(),
										data:key
								};
								
								$scope.columns.push(colObj);
							}
				 	  	}
					}
					$scope.gridDataList = [];
					$scope.loadTableData(resp);
			 		$rootScope.inItDataTable();
	 			}
	 		});
		}
 	};
	
 	$scope.loadTableData = function(resp){
 		$scope.liquefactionData = [];
 		$scope.regasificationData = [];
 		
 	
 		
   		for(var k=0; k < resp.length; k++){
			if(resp[k].type =="gas"){
		 		$scope.liquefactionData = resp[k][$rootScope.searchFilterObj.displayType];
		 		
		 		/*if($scope.liquefactionData && $scope.liquefactionData.length > 0){
			 		var tempCapacity = resp[k].totalCapacity;
			 		tempCapacity.name = " Total";
			 	 	$scope.liquefactionData.push(tempCapacity);
			 	 	
			 	 	var reverseOrder = $scope.liquefactionData.slice();
			 	 	$scope.liquefactionData = [];
			 	 	$scope.liquefactionData = reverseOrder.reverse();
		 		}*/
			}
			
			if(resp[k].type =="oil"){
			 	$scope.regasificationData = resp[k][$rootScope.searchFilterObj.displayType];
			 	
			 	/*if($scope.regasificationData && $scope.regasificationData.length > 0){
			 		var tempCapacity = resp[k].totalCapacity;
			 		tempCapacity.name = " Total";
			 	 	$scope.regasificationData.push(tempCapacity);
			 	 	
			 	 	var reverseOrder = $scope.regasificationData.slice();
			 	 	$scope.regasificationData = [];
			 	 	$scope.regasificationData = reverseOrder.reverse();
			 	}*/
			 	
			}
			
		}
   		
   		if($scope.regasificationData.length == 0 && $scope.liquefactionData.length ==0 ){
   			$scope.noDataAvailable = false;
   		}else{
   			$scope.noDataAvailable = true;
   		}
   		
	};
 	
	
	
	$scope.init = function(){
		$scope.title = $state.current.name;
		$scope.title = "Company";
		$scope.gridDataList = [];
		$scope.liquefactionData = [];
		$scope.regasificationData = [];
		$scope.displayPeriodList = [];
		$scope.url = "";
		$rootScope.capacityFilterJSON = {};
		$scope.dateObj = new Date();
		$scope.currentYear = $scope.dateObj.getFullYear();
		$rootScope.table = {
				liquefactionInst : "",
				regasificationInst:"",
				modelDatatableInst:"",
				terminalDatatableInst:""
		};
		
		$scope.setConfigurations();
		$scope.setDisplayPeriod();
		
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
 		$rootScope.assetTypeModel =[];
 		$rootScope.assetUnitModel =[];
 		$rootScope.assetCountryModel = [];
 		$rootScope.capacityFilterJSON ={};
 		
 		$scope.CO_unit = "Kb/d";
 		$scope.NG_unit = "Mmcf/d";
 		
 		
		$scope.occurrenceOptions = [
        {
			name : "Company",
			value : "company",
			checked :true
        },{
			name : "Field",
			value : "field",
			checked :false
        }];
		
		$rootScope.searchFilterObj = {
				startDate: URL.displayFrom_2005,
				endDate:URL.displayTo_2016,
				displayType:"company"
 		};
  
		if($scope.url != ''){
			var initailReq = angular.copy($rootScope.searchFilterObj)
			//initailReq['type'] = "both(oil,gas)";
	 		HttpService.getHttp($scope.url,initailReq).then(function(resp) {
	 			
	 			
	 			if(resp != "" && resp != undefined ){
 					resp = resp;
 				}else{
 					resp = [];
 				}
			  
	 			if(resp != "" && resp != undefined){
					$scope.gridDataList = angular.copy(resp);
					$scope.columns =[];
				 	 
					if($scope.gridDataList[0] != undefined){
						var columnName = $rootScope.searchFilterObj.displayType.charAt(0).toUpperCase() +  $rootScope.searchFilterObj.displayType.slice(1);
						$scope.columns.push({title:columnName  ,data:"name"});
						for(var key in $scope.gridDataList[0][$rootScope.searchFilterObj.displayType][0]){
							if(key != "name"){
								var colObj = {
										title:key.toUpperCase(),
										data:key
								};
								
								$scope.columns.push(colObj);
							}
				 	  	}
					}
					$scope.gridDataList = [];
					$scope.loadTableData(resp);
			 		$rootScope.inItDataTable();
		 			
	 			}
	 		});
		}
 	}
	  

 });


 