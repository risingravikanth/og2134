
 angular.module('OGAnalysis').controller('CapacityCtrl', function($scope,$state,$rootScope,URL,HttpService) {
	console.log("In CapacityCtrl ctrl");
 	console.log($state)
	
 	$scope.setConfigurations = function(){
	 		$scope.url = "/capacity";
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
 	};
	
	$scope.setDisplayPeriod = function(){
		for(var i =2000;i <= 2020 ;i++){
			var obj = {
				id : i,
				name : i
			}
			$scope.displayPeriodList.push(obj);
		}
		
	};
 	
	openModel = function(){
		console.log("in model");
		$('#myModal').modal("show")
		
	};
	
	$rootScope.typeChangeFn = function(){
		if(parseInt($rootScope.searchFilterObj.endDate) >= parseInt($rootScope.searchFilterObj.startDate)){
			$rootScope.resetFilter();
			HttpService.getHttp($scope.url,$rootScope.searchFilterObj).then(function(resp) {
				 if($rootScope.table.liquefactionInst != ""){
					 
					if(resp != "" && resp != undefined ){
	 					resp = resp;
	 				}else{
	 					resp = [];
	 				}
				 
			 		$scope.columns =[];
			 		$scope.liquefactionData = [];
			 		$scope.regasificationData =[];
			 		
			 		if(resp[0][$rootScope.searchFilterObj.displayType].length != 0){
			  		 	for(var key in resp[0][$rootScope.searchFilterObj.displayType][0]){
							var colObj = {
									title:key.toUpperCase(),
									data:key
							};
							
							$scope.columns.push(colObj);
					 	}
						
						$scope.gridDataList = [];
						for(var k=0; k < resp.length; k++){
							if(resp[k].type =="Liquefaction"){
						 		$scope.liquefactionData = resp[k][$rootScope.searchFilterObj.displayType];
						 		var tempCapacity = resp[k].totalCapacity;
						 		tempCapacity.name = " Total";
						 	 	$scope.liquefactionData.push(tempCapacity);
						 	 	
						 	 	var reverseOrder = $scope.liquefactionData.slice();
						 	 	$scope.liquefactionData = [];
						 	 	$scope.liquefactionData = reverseOrder.reverse();
						 	 	
							}else if(resp[k].type =="Regasification"){
							 	$scope.regasificationData = resp[k][$rootScope.searchFilterObj.displayType];
							 	
							 	var tempCapacity = resp[k].totalCapacity;
						 		tempCapacity.name = " Total";
						 	 	$scope.regasificationData.push(tempCapacity);
						 	 	
						 	 	var reverseOrder = $scope.regasificationData.slice();
						 	 	$scope.regasificationData = [];
						 	 	$scope.regasificationData = reverseOrder.reverse();
							}
							
						}
			 		}
				  	/*$rootScope.table.liquefactionInst.clear().draw();
					$rootScope.table.liquefactionInst.rows.add($scope.liquefactionData);
					$rootScope.table.liquefactionInst.draw();
					
					$rootScope.table.regasificationInst.clear().draw();
					$rootScope.table.regasificationInst.rows.add($scope.regasificationData);
					$rootScope.table.regasificationInst.draw();*/
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
	 	console.log("In side capacity ctrl filet submit",$rootScope.unitsModel);
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
 			$scope.generateFormData($rootScope.offshoreModel,'offshore');
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
			 if($rootScope.table.liquefactionInst != ""){
				 if(resp != "" && resp != undefined ){
	 					resp = resp;
	 				}else{
	 					resp = [];
	 				}
				 
				
		 		$scope.columns =[];
			 	for(var key in resp[0][$rootScope.searchFilterObj.displayType][0]){
					var colObj = {
							title:key.toUpperCase(),
							data:key
					};
					
					$scope.columns.push(colObj);
			 	}
				
				$scope.gridDataList = [];
				for(var k=0; k < resp.length; k++){
					if(resp[k].type =="Liquefaction"){
				 		$scope.liquefactionData = resp[k][$rootScope.searchFilterObj.displayType];
				 		var tempCapacity = resp[k].totalCapacity;
				 		tempCapacity.name = " Total";
				 	 	$scope.liquefactionData.push(tempCapacity);
				 	 	
				 	 	var reverseOrder = $scope.liquefactionData.slice();
				 	 	$scope.liquefactionData = [];
				 	 	$scope.liquefactionData = reverseOrder.reverse();
				 	 	
					}else if(resp[k].type =="Regasification"){
					 	$scope.regasificationData = resp[k][$rootScope.searchFilterObj.displayType];
					 	
					 	var tempCapacity = resp[k].totalCapacity;
				 		tempCapacity.name = " Total";
				 	 	$scope.regasificationData.push(tempCapacity);
				 	 	
				 	 	var reverseOrder = $scope.regasificationData.slice();
				 	 	$scope.regasificationData = [];
				 	 	$scope.regasificationData = reverseOrder.reverse();
					}
					
				}
			 	$rootScope.capacityFilterJSON ={};
			  	/*$rootScope.table.liquefactionInst.clear().draw();
				$rootScope.table.liquefactionInst.rows.add($scope.liquefactionData);
				$rootScope.table.liquefactionInst.draw();
				
				$rootScope.table.regasificationInst.clear().draw();
				$rootScope.table.regasificationInst.rows.add($scope.regasificationData);
				$rootScope.table.regasificationInst.draw();*/
			 	$rootScope.inItDataTable();
				
				
				
			 }
	 	});
 	};
 	
 	$rootScope.inItDataTable = function(){
 		if($rootScope.table.liquefactionInst != undefined && $rootScope.table.liquefactionInst != "" )
 			$rootScope.table.liquefactionInst.destroy();
 		if($rootScope.table.regasificationInst != undefined && $rootScope.table.regasificationInst != "")
 			$rootScope.table.regasificationInst.destroy();
 		
 	 	var liquefactionInst = $("#liquefaction").DataTable({
			"columnDefs": [
			{
				// The `data` parameter refers to the data for the cell (defined by the
				// `data` option, which defaults to the column being worked with, in
				// this case `data: 0`.
				"render": function ( data, type, row ) {
					var commonHref = "";
					if(data != ' Total'){
						commonHref =  '<a data-toggle="modal" href="#myModal">'+data +'</a>';
					}else{
						commonHref =  '<p>'+data+'</p>';
					}
					return commonHref;
				},
				"targets": 0
						}
						],
						scrollX: true,
						columns: $scope.columns,
						data : $scope.liquefactionData
			});
			
			var regasificationInst = $("#regasification").DataTable({
			"columnDefs": [
			{
				// The `data` parameter refers to the data for the cell (defined by the
			// `data` option, which defaults to the column being worked with, in
			// this case `data: 0`.
			"render": function ( data, type, row ) {
				var commonHref = "";
				if(data != ' Total'){
					commonHref =  '<a data-toggle="modal" href="#myModal">'+data +'</a>';
				}else{
					commonHref =  '<p>'+data+'</p>';
				}
				return commonHref;
			},
			"targets": 0
						}
						],
						scrollX: true,
						columns: $scope.columns,
						data : $scope.regasificationData
			 });
					
			$rootScope.table.liquefactionInst = liquefactionInst;
			$rootScope.table.regasificationInst = regasificationInst;
  	};
	
	
	
	$scope.init = function(){
		$scope.title = $state.current.name;
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
				regasificationInst:""
		};
		
		$scope.setConfigurations();
		$scope.setDisplayPeriod();
		$rootScope.resetFilter();
		
		
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
		
		$rootScope.searchFilterObj = {
				startDate: $scope.dateObj.getFullYear(),
				endDate:"2020",
				displayType:"country"
 		};
  
		if($scope.url != ''){
			var initailReq = angular.copy($rootScope.searchFilterObj)
	 		HttpService.getHttp($scope.url,initailReq).then(function(resp) {
	 			
	 			
	 			if(resp != "" && resp != undefined ){
 					resp = resp;
 				}else{
 					resp = [];
 				}
			 
	 			if(resp != "" && resp != undefined){
					$scope.gridDataList = angular.copy(resp);
					$scope.columns =[];
					
		 			//for(var i =0 ; i < $scope.gridDataList[0].country.length; i++){
					if($scope.gridDataList[0] != undefined){
						for(var key in $scope.gridDataList[0].country[0]){
							var colObj = {
									title:key.toUpperCase(),
									data:key
							};
							
							$scope.columns.unshift(colObj);
							console.log($scope.columns)
					 	}
					}
					$scope.gridDataList = [];
					for(var k=0; k < resp.length; k++){
						if(resp[k].type =="Liquefaction"){
					 		$scope.liquefactionData = resp[k][$rootScope.searchFilterObj.displayType];
					 		
					 		var tempCapacity = resp[k].totalCapacity;
					 		tempCapacity.name = " Total";
					 	 	$scope.liquefactionData.push(tempCapacity);
					 	 	
					 	 	var reverseOrder = $scope.liquefactionData.slice();
					 	 	$scope.liquefactionData = [];
					 	 	$scope.liquefactionData = reverseOrder.reverse();
					 	 	
						}else if(resp[k].type =="Regasification"){
						 	$scope.regasificationData = resp[k][$rootScope.searchFilterObj.displayType];
						 	
						 	var tempCapacity = resp[k].totalCapacity;
					 		tempCapacity.name = " Total";
					 	 	$scope.regasificationData.push(tempCapacity);
					 	 	
					 	 	var reverseOrder = $scope.regasificationData.slice();
					 	 	$scope.regasificationData = [];
					 	 	$scope.regasificationData = reverseOrder.reverse();
						}
						
					}
			 		
					$rootScope.inItDataTable();
	 			}
	 		});
		}
 	}
	  

 });