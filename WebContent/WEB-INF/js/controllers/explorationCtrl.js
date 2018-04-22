 angular.module('OGAnalysis').controller('ExplorationCtrl', function($scope,$state,$rootScope,URL,HttpService) {
	console.log("In ExplorationCtrl ctrl");
 	console.log($state);
 	
 	$scope.setConfigurations = function(){
 		$scope.url = "/exploration";
 		$rootScope.loadExplorationFilter();
		$rootScope.filterObj = {
			regionField :true,
			countryField :true,
			locationField : false,
			operatorField : true,
			ownerField : true,
			statusField : true,
			unitsField : true,
			offshoreField : false,
			typeField :true,
			
			basinField : true,
		};
		
		$scope.columns = [
			{ 
				title: "Name of the Block",
				  data: "blockName"
			},
 	 		{ title: "Country",
			  data: "country"
			},
			{ title: "Basin",
				  data: "basin"
			},
			{ title: "Onshore/Offshore",
				  data: "onshoreoroffshore"
			},
			{ title: "Status",
			  data: "status"
			},
			{ title: "Date Awarded",
				  data: "dateawarded"
			},
			{ title: "Operator",
				  data: "operator"
			},
	 		{ title: "Owners",
			  data: "owner"
			},
	 		{ title: "Acreage(KM2)",
			  data: "area"
			}
	 	];
	};
	
	openModel = function(inputName,type,event){
		
		if($rootScope.table.modelDatatableInst != undefined && $rootScope.table.modelDatatableInst != "" && $rootScope.searchFilterObj.displayType != "terminal" ){
 			$rootScope.table.modelDatatableInst.destroy();
 			$("#modelDatatable").empty();
 		}
		
		if($rootScope.table.terminalDatatableInst != undefined && $rootScope.table.terminalDatatableInst != "" && $rootScope.searchFilterObj.displayType == "terminal" ){
 			$rootScope.table.terminalDatatableInst.destroy();
 			$("#terminalDatatable").empty();
 		}
		
		var modalReq = angular.copy($rootScope.searchFilterObj);
		modalReq['recordName']= inputName;
		modalReq['type']= type;
 	 	modalReq['displayType']= "terminal";
	 	
		for(var key in $rootScope.capacityFilterJSON){
			modalReq[key] = $rootScope.capacityFilterJSON[key];
  		}
		
		HttpService.get("/modalcapacity",modalReq).then(function(resp) {
			$scope.gridDataList = angular.copy(resp);
			
			if(resp != "" && resp != undefined ){
					resp = resp;
				}else{
					resp = [];
				}
		 
	 		$scope.modelcolumns =[];
	 		
	 		if(resp['terminal'] != undefined && resp['terminal'].length != 0){
	  		 	
	 			if(resp != undefined){
					var columnName = 'Terminal'
					$scope.modelcolumns.push({title:columnName  ,data:"name"});
					for(var key in resp['terminal'][0]){
						if(key != "name"){
							var colObj = {
									title:key.toUpperCase(),
									data:key
							};
							
							$scope.modelcolumns.push(colObj);
						}
			 	  	}
		 		}
	 			
	 			$scope.ModelDataList = [];
				 
				if(resp.type =="Liquefaction"){
			 		$scope.ModelDataList = resp['terminal'];
			 		var tempCapacity = resp.totalCapacity;
			 		tempCapacity.name = " Total";
			 	 	$scope.ModelDataList.push(tempCapacity);
			 	 	
			 	 	var reverseOrder = $scope.ModelDataList.slice();
			 	 	$scope.ModelDataList = [];
			 	 	$scope.ModelDataList = reverseOrder.reverse();
			 	 	
				}else if(resp.type =="Regasification"){
				 	$scope.ModelDataList = resp['terminal'];
				 	
				 	var tempCapacity = resp.totalCapacity;
			 		tempCapacity.name = " Total";
			 	 	$scope.ModelDataList.push(tempCapacity);
			 	 	
			 	 	var reverseOrder = $scope.ModelDataList.slice();
			 	 	$scope.ModelDataList = [];
			 	 	$scope.ModelDataList = reverseOrder.reverse();
				}
					
				 
	 			
	 			if ( $.fn.dataTable.isDataTable( '#modelDatatable') ) {
	 				tableInst = $('#modelDatatable').DataTable();
	 	 		}
	 	 		else {
			 
		 			var tableInst = $("#modelDatatable").DataTable({
					 	scrollX: true,
					 	columns: $scope.modelcolumns,
						data :$scope.ModelDataList 
					});
		 			//tableInst.columns.adjust().draw();
		 			$timeout(function(){
		 				tableInst.draw()
		 			},100);
		 			
		 			$rootScope.table.modelDatatableInst = tableInst;
	 	 		}
	  	 	} else{
	  	 		$scope.terminalcolumns = [];
	  	 		$scope.terminalcolumns.push({title:"Name" ,data:"name"});
				for(var i =2005 ; i<= 2020; i++){
					var terminalColObj = {
							title:i.toString().toUpperCase(),
							data:i
					};
					 
			 		$scope.terminalcolumns.push(terminalColObj)
				}
				
					$scope.terminalDataList =[];
				 
					var obj = {
						"name":"", "2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
						"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
	 				};
					
					for(var i in $scope.gridDataList.trainsOrVaporizers){
						obj["name"]="Trains Or Vaporizers";
						
						if($scope.gridDataList.trainsOrVaporizers[i] != undefined ){
							obj[i] = $scope.gridDataList.trainsOrVaporizers[i]; 
						}else{
							obj[i] = "-" 
						}
			 		}
					
					$scope.terminalDataList.push(obj);
					obj = {
							"name":"", "2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
					
					for(var i in $scope.gridDataList.storageCapacity){
						obj["name"]="Storage Capacity";
						
						if($scope.gridDataList.storageCapacity[i] != undefined ){
							obj[i] = $scope.gridDataList.storageCapacity[i] 
						}else{
							obj[i] = "-" 
						}
			 		}
					$scope.terminalDataList.push(obj);
					obj = {
							"name":"", "2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
					 
					
					for(var i in $scope.gridDataList.processingCapacity){
						obj["name"]="Processing Capacity";
						
						if($scope.gridDataList.processingCapacity[i] != undefined ){
							obj[i] = $scope.gridDataList.processingCapacity[i] 
						}else{
							obj[i] = "-" 
						}
		 	 		}
					$scope.terminalDataList.push(obj);
					obj = {
							"name":"", "2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
					
					for(var i in $scope.gridDataList.storageTanks){
						obj["name"]="Storage Tanks";
						
						if($scope.gridDataList.storageTanks[i] != undefined ){
							obj[i] = $scope.gridDataList.storageTanks[i] 
						}else{
							obj[i] = "-" 
						}
	  				}
					$scope.terminalDataList.push(obj);
		 	 	 
		  	 		if ( $.fn.dataTable.isDataTable( '#terminalDatatable' ) ) {
		 				tableInst = $('#terminalDatatable').DataTable();
		 	 		}
		 	 		else {
				 
		 			var terminaltableInst = $("#terminalDatatable").DataTable({
				 		scrollX: true,
						bFilter:false,
						columns: $scope.terminalcolumns,
						bPaginate:false,
						data :$scope.terminalDataList 
					});
		 			
		 			$rootScope.table.terminalDatatableInst = terminaltableInst;
	 	 		}
 	  	 	}
		});
		
		$('#terminalDatatable th').click();
		$('#myModal').modal("show");
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
 				"drawCallback": function( settings ) {
 					if(!$("#liquefaction").parent().hasClass("table-responsive")){
 						$("#liquefaction").wrap( "<div class='table-responsive'></div>" );
				     }
 		 	    },
 				"columnDefs": [
 				{
 		 			"render": function ( data, type, row ) {
 		 					var htmlStr  = data.join('<br>');
 		 				 
 		 					return '<p>'+htmlStr+'</p>';
 		 		 	},
 
 					"targets": 7
 					
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
	  				"render": function ( data, type, row ) {
	 	 			 	return '<p>'+data+'</p>';;
	 				},
 
	 				"targets": 0
	 							}
	 							],
	 							 
	 							columns: $scope.columns,
	 							data : $scope.regasificationData
	 				 });
	 			$rootScope.table.regasificationInst = regasificationInst;
	 		}
	 		
 			$("#liquefaction").unbind( "click" );
	 	 	
			$("#liquefaction").on("click", "td .openModel",function(e){
			  	openModel(e.currentTarget.getAttribute('recordName'),'liquefaction',e);
				e.preventDefault();
				e.stopPropagation(); 
			});
			
			$("#regasification").unbind( "click" );
			
			$("#regasification").on("click", "td .openModel",function(e){
				openModel(e.currentTarget.getAttribute('recordName'),'regasification',e);
				e.preventDefault();
				e.stopPropagation(); 
			});
		 			
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
		
		if($rootScope.filterObj.basinField == true){
			$scope.generateFormData($rootScope.basinModel,'basin');
 		}
		
		
 		if($rootScope.filterObj.ownerField == true){
 			$scope.generateFormData($rootScope.ownerModel,'owner');
 		}
 		if($rootScope.filterObj.statusField == true){
 			$scope.generateFormData($rootScope.statusModel,'status');
 		}
 		if($rootScope.filterObj.unitsField == true){
 			 
 			if($rootScope.unitsModel.id != undefined){
 		 		$rootScope.unitsModel.length =0;
 				$rootScope.unitsModel.push({id:$rootScope.unitsModel.id});
 			}else if ($rootScope.unitsModel.length >0){
 				if($rootScope.unitsModel.id == undefined)
						$rootScope.unitsModel.length =0
 	 		}else{
 				$rootScope.unitsModel.length =0;
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
				$scope.loadTableData(resp);
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
 		$rootScope.basinModel =[];
 		
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
		 	 	 	$scope.loadTableData(resp);
		 			$rootScope.inItDataTable();
	 			}
	 		});
		}
   	};
   	
	$scope.loadTableData = function(resp){
		
	 
		$scope.liquefactionData = resp;
		
		if($scope.liquefactionData.length == 0 ){
   			$scope.noDataAvailable = false;
   		}else{
   			$scope.noDataAvailable = true;
   		}
 	};
	
	
 	$scope.init = function(){
		$scope.title = $state.current.name;
		$scope.title = "Exploration";
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
				displayType: "block"
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
 		$rootScope.basinModel =[];
 		$rootScope.capacityFilterJSON ={};
 		
 		$scope.noDataAvailable = true;
 		
 		if($scope.url != ''){
			var initailReq = angular.copy($rootScope.searchFilterObj)
	 		HttpService.getHttp($scope.url,initailReq).then(function(resp) {
	 			
	 			
	 			if(resp != "" && resp != undefined ){
 					resp = resp;
 				}else{
 					resp = [];
 				}
	 			$scope.gridDataList = [];
	 			$scope.loadTableData(resp);
	 			if(resp != "" && resp != undefined){
					$scope.gridDataList = angular.copy(resp);					
	 		 		$rootScope.inItDataTable();
	 			}
	 		});
		}
 	};
 	
 });