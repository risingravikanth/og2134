
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
		
	}
 	
	openModel = function(){
		console.log("in model");
		$('#myModal').modal("show")
		
	};
	
	$rootScope.typeChangeFn = function(){
		console.log("capacity ctrl",$rootScope.searchFilterObj.displayType);
		
		HttpService.getFomData($scope.url,$rootScope.searchFilterObj).then(function(resp) {
			 if($rootScope.table.inst != ""){
				if(resp != ""){
					resp = JSON.parse(resp);
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
					 	$scope.regasification = resp[k][$rootScope.searchFilterObj.displayType];
					 	
					 	var tempCapacity = resp[k].totalCapacity;
				 		tempCapacity.name = " Total";
				 	 	$scope.regasification.push(tempCapacity);
				 	 	
				 	 	var reverseOrder = $scope.regasification.slice();
				 	 	$scope.regasification = [];
				 	 	$scope.regasification = reverseOrder.reverse();
					}
					
				}
			 	
			  	$rootScope.table.inst.clear().draw();
				$rootScope.table.inst.rows.add(liquefactionData);
				$rootScope.table.inst.draw();
			 }
	 	});
		
	};
	
	
	
	$scope.init = function(){
		$scope.title = $state.current.name;
		$scope.gridDataList = [];
		$scope.liquefactionData = [];
		$scope.regasification = [];
		$scope.displayPeriodList = [];
		$scope.url = "";
		$scope.dateObj = new Date();
		$scope.currentYear = $scope.dateObj.getFullYear();
		$rootScope.table = {
				inst : ""
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
				displayType:"country",
				displayFrom:$scope.currentYear,
				displayTo:""
		};
  
		if($scope.url != ''){
	 		HttpService.get($scope.url).then(function(resp) {
				$scope.gridDataList = angular.copy(resp);
				$scope.columns =[];
				
	 			//for(var i =0 ; i < $scope.gridDataList[0].country.length; i++){
				for(var key in $scope.gridDataList[0].country[0]){
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
					 	$scope.regasification = resp[k][$rootScope.searchFilterObj.displayType];
					 	
					 	var tempCapacity = resp[k].totalCapacity;
				 		tempCapacity.name = " Total";
				 	 	$scope.regasification.push(tempCapacity);
				 	 	
				 	 	var reverseOrder = $scope.regasification.slice();
				 	 	$scope.regasification = [];
				 	 	$scope.regasification = reverseOrder.reverse();
					}
					
				}
		 		
				var tableInst = $("#example1").DataTable({
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
							columns: $scope.columns.reverse(),
							data : $scope.liquefactionData
				});
				$rootScope.table.inst = tableInst;
	 		});
		}
 	}

 });