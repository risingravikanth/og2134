
 angular.module('OGAnalysis').controller('SupplyDemandCtrl', function($scope,$state,$rootScope,URL,HttpService,$timeout) {
	console.log("In SupplyDemandCtrl");
 	console.log($state)
	
 	$scope.setConfigurations = function(){
	 		$scope.url = "/supplyDemand";
	 		$rootScope.loadSupplyDemandFilter();

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
 	};
	
	$scope.setDisplayPeriod = function(){
		for(var i = URL.displayFrom;i <= URL.displayTo ;i++){
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
			 		 
		 	  		 	
			 		$scope.columns.push({title:"Country"  ,data:"country"});
		 	 		if(resp.data != undefined && resp.data[0] != undefined){
					 	for(var key in resp.data[0]){
					 		if(key != "country"){
								var colObj = {
										title:key.toUpperCase(),
										data:key
								};
								
								$scope.columns.push(colObj);
					 		}
				 	  	}
					}
					$scope.gridDataList = [];
					$scope.liquefactionData = resp.data;
				  	 
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
			  
		 		$scope.columns.push({title:"Country"  ,data:"country"});
	 	 		if(resp.data != undefined && resp.data[0] != undefined){
				 	for(var key in resp.data[0]){
				 		if(key != "country"){
							var colObj = {
									title:key.toUpperCase(),
									data:key
							};
							
							$scope.columns.push(colObj);
				 		}
			 	  	}
				}
				$scope.gridDataList = [];
				$scope.liquefactionData = resp.data;
			 	 
			   
			 	$rootScope.inItDataTable();
				
				
				
			 }
	 	});
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
 		$('#liquefaction').empty();
 		$("#regasification").empty();
 		if ( $.fn.dataTable.isDataTable( '#liquefaction' ) ) {
 		    table = $('#liquefaction').DataTable();
 		}
 		else {
 			var liquefactionInst = $("#liquefaction").DataTable({  				
 				scrollX:true, 				
 				"drawCallback": function( settings ) {
 					if(!$("#liquefaction").parent().hasClass("table-responsive")){
// 						$("#liquefaction").wrap( "<div class='table-responsive'></div>" );
 						
				     }
 		 	    },
 		 	    fixedColumns:{
 		 	    	leftColumns:1
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
 				
 			$timeout(function(){
 				liquefactionInst.columns.adjust().draw(); 				
			},100);
 				$rootScope.table.liquefactionInst = liquefactionInst;
 			}
 	 		
	 		if ( $.fn.dataTable.isDataTable( '#regasification' ) ) {
	 		    table = $('#regasification').DataTable();
	 		}
	 		else {
	 			var regasificationInst = $("#regasification").DataTable({
	 				scrollX:true,
	 				"drawCallback": function( settings ) {
	 					if(!$("#regasification").parent().hasClass("table-responsive")){
//	 						$("#regasification").wrap( "<div class='table-responsive'></div>" );
					     }
	 		 	    },
	 		 	    fixedColumns:{
	 		 	    	leftColumns:1
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
	 			$timeout(function(){
	 				regasificationInst.columns.adjust().draw();					
				},100);
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
					$scope.columns =[];
					
					$scope.columns.push({title:"Country"  ,data:"country"});
		 	 		if(resp.data != undefined && resp.data[0] != undefined){
					 	for(var key in resp.data[0]){
					 		if(key != "country"){
								var colObj = {
										title:key.toUpperCase(),
										data:key
								};
								
								$scope.columns.push(colObj);
					 		}
				 	  	}
					}
					$scope.gridDataList = [];
					$scope.liquefactionData = resp.data;
			 		
					$rootScope.inItDataTable();
	 			}
	 		});
		}
 		$rootScope.loadSupplyDemandFilter();
 	};
	
	
	
	$scope.init = function(){
		$scope.title = $state.current.name;
		$scope.title = "Supply & Demand";
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
 		$rootScope.capacityFilterJSON ={};
		
		
		$scope.occurrenceOptions = [
        {
			name : "Import",
			value : "import",
			checked :true
        },{
			name : "Export",
			value : "export",
			checked :false
        }];
		
		$rootScope.searchFilterObj = {
				startDate: $scope.dateObj.getFullYear(),
				endDate:URL.displayTo,
				displayType:"import"
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
					$scope.columns.push({title:"Country"  ,data:"country"});
		 	 		if($scope.gridDataList.data[0] != undefined){
					 	for(var key in $scope.gridDataList.data[0]){
					 		if(key != "country"){
								var colObj = {
										title:key.toUpperCase(),
										data:key
								};
								
								$scope.columns.push(colObj);
					 		}
				 	  	}
					}
					$scope.gridDataList = [];
					$scope.liquefactionData = resp.data;
				 	
					$rootScope.inItDataTable();
	 			}
	 		});
		}
 	}
	  

 });