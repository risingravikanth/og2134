
 angular.module('OGAnalysis').controller('CapacityCtrl', function($scope,$state,$rootScope,URL,HttpService) {
	console.log("In CapacityCtrl ctrl");
 	console.log($state)
	
	$scope.example1model = []; 
	$scope.example1data = [ {id: 1, label: "David"}, {id: 2, label: "Jhon"}, {id: 3, label: "Danny"}];
	
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
	
	$scope.init = function(){
		$scope.title = $state.current.name;
		$scope.gridDataList = [];
		$scope.liquefactionData = [];
		$scope.regasification = [];
		$scope.displayPeriodList = [];
		$scope.url = "";
		
		$scope.setConfigurations();
		$scope.setDisplayPeriod();
		
	 
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
			 		$scope.liquefactionData = resp[k].country;
				}else if(resp[k].type =="Regasification"){
				 	$scope.regasification = resp[k].country;
				}
				
			}
	 		
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
					columns: $scope.columns.reverse(),
					data : $scope.liquefactionData
					});
				});
		}
	 		
	}

 });