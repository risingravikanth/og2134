 angular.module('OGAnalysis').controller('InfrastructureCtrl', function($scope,$state,$rootScope,URL,HttpService) {
	console.log("In Infra ctrl");
 	console.log($state);
 	
 	$scope.setConfigurations = function(){
 		$scope.url = "/lng/infrastructure";
 		$rootScope.loadLngFilter();
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
		
		$scope.columnsLiquefaction = [
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
		$scope.columnsRegasification = [
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
		                  			{ title: "#Vaporizers",
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
	
	$scope.downloadReport = function(){
 		
 		  
		HttpService.get("/lng/download/terminaldetails",$scope.currentDownloadReq ).then(function(resp) {
			
		});
 	}
	$scope.generateUrl = function(url,data){
		$scope.moreDetailsHrefUrl = url+data.recordName+"&type="+data.type;
		 
	}
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
 	 	
 	 	$scope.currentDownloadReq = angular.copy(modalReq);
		 
	 	
		for(var key in $rootScope.capacityFilterJSON){
			modalReq[key] = $rootScope.capacityFilterJSON[key];
  		}
		
		HttpService.get("/lng/modalcapacity",modalReq).then(function(resp) {
			$scope.gridDataList = angular.copy(resp);
			$scope.currentDownloadReq = angular.copy(modalReq);
			$scope.generateUrl('lng/download/terminaldetails?recordName=',$scope.currentDownloadReq);
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
 						var commonHref = "";
 			 	 			commonHref =  '<a  recordName="'+data+'" type="liquefaction" class="openModel">'+data +'</a>';
 		 		  		return commonHref;
 					},
 
 					"targets": 0
 							}
 							],
 							 
 							columns: $scope.columnsLiquefaction,
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
	 				 		var commonHref =  '<p>'+data+'</p>';
	 						commonHref =  '<a recordName="'+data+'"  type="regasification"  class="openModel">'+data +'</a>';
	 	 		 		return commonHref;
	 				},
 
	 				"targets": 0
	 							}
	 							],
	 							 
	 							columns: $scope.columnsRegasification,
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
 		if($rootScope.filterObj.ownerField == true){
 			$scope.generateFormData($rootScope.ownerModel,'owner');
 		}
 		if($rootScope.filterObj.statusField == true){
 			$scope.generateFormData($rootScope.statusModel,'status');
 		}
 		if($rootScope.filterObj.unitsField == true){
 			if($rootScope.unitsModel.id != undefined){
 				$rootScope.unitsModel.push({id:$rootScope.unitsModel.id});
 			} else{
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
 		$rootScope.loadLngFilter();
   	};
   	
	$scope.loadTableData = function(resp){
		
		for(var k=0; k < resp.length; k++){
			if(resp[k].type == URL.liquefactionType){
		 		$scope.liquefactionData = resp[k]['data'];
			}

			if(resp[k].type == URL.regasificationType){
			 	$scope.regasificationData = resp[k]['data'];
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
		$scope.title = "Infrastrcture";
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
	 			if(resp != "" && resp != undefined){
					$scope.gridDataList = angular.copy(resp);
					$scope.loadTableData(resp);
	 		 		$rootScope.inItDataTable();
	 			}
	 		});
		}
 	};
 	
 });