
 angular.module('OGAnalysis').controller('CapacityCtrl', function($scope,$state,$rootScope,URL,HttpService,$timeout) {
	console.log("In CapacityCtrl ctrl");
 	console.log($state)
	
 	$scope.setConfigurations = function(){
	 		$scope.url = "/lng/capacity";
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
 	};
 	
 	
 	$scope.downloadReport = function(){
  	 
		HttpService.get("/lng/download/terminaldetails",$scope.currentDownloadReq).then(function(resp) {
			
		});
 	}
	
	$scope.setDisplayPeriod = function(){
		for(var i = URL.displayFrom;i <= URL.displayTo ;i++){
			var obj = {
				id : i,
				name : i
			}
			$scope.displayPeriodList.push(obj);
		}
		
	};
	
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
		 				scrollY: "100px",
					 	scrollX: true,
					 	columns: $scope.modelcolumns,
						data :$scope.ModelDataList,
						fixedColumns :{
							leftColumns:1
						}
					});
		 			//tableInst.columns.adjust().draw();
		 			$timeout(function(){
		 				tableInst.draw()
		 			},100);
		 			
//		 			new $.fn.dataTable.FixedColumns(tableInst);
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
	
	$rootScope.typeChangeFn = function(){
		
		if($rootScope.searchFilterObj.displayType == 'country')
		{
			$rootScope.tableHeaderObj.title="Country Wise";
		}
		else if($rootScope.searchFilterObj.displayType == 'company'){
			$rootScope.tableHeaderObj.title="Company Wise";
		}
		else if($rootScope.searchFilterObj.displayType == 'terminal'){
			$rootScope.tableHeaderObj.title="Terminal Wise";
		}
		
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
			 		 
			 		//if(resp[0][$rootScope.searchFilterObj.displayType].length != 0){
			  		 	
			 			if(resp[0] != undefined){
							var columnName = $rootScope.searchFilterObj.displayType.charAt(0).toUpperCase() +  $rootScope.searchFilterObj.displayType.slice(1);
							$scope.columns.push({title:columnName  ,data:"name"});
							for(var key in resp[0]['totalCapacity']){
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
 			if($rootScope.unitsModel.id != undefined){
 				$rootScope.unitsModel.push({id:$rootScope.unitsModel.id});
 			}else if ($rootScope.unitsModel.length >0){
 				
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
			 if($rootScope.table.liquefactionInst != ""){
				 if(resp != "" && resp != undefined ){
	 					resp = resp;
	 				}else{
	 					resp = [];
	 				}
				 
				
		 		$scope.columns =[];
			  
		 		
		 		if(resp[0] != undefined){
					var columnName = $rootScope.searchFilterObj.displayType.charAt(0).toUpperCase() +  $rootScope.searchFilterObj.displayType.slice(1);
					$scope.columns.push({title:columnName  ,data:"name"});
					for(var key in resp[0]['totalCapacity']){
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
 		var liquefactionInst;
 		
 		if ( $.fn.dataTable.isDataTable( '#liquefaction' ) ) {
 		    table = $('#liquefaction').DataTable();
 		   
 		}
 		else {
 			var liquefactionInst = $("#liquefaction").DataTable({
 				scrollY:"400px",
 				scrollX:true,
 				"drawCallback": function( settings ) {
 					if(!$("#liquefaction").parent().hasClass("table-responsive")){
// 						$("#liquefaction").wrap( "<div class='table-responsive'></div>" ); 						
				     }
 		 	    },
 				fixedColumns :{
 					leftColumns:1
 				},
 				"columnDefs": [
 				{
 					// The `data` parameter refers to the data for the cell (defined by the
 					// `data` option, which defaults to the column being worked with, in
 					// this case `data: 0`. 					
 					"render": function ( data, type, row ) {
 						var commonHref = "";
 						if(data != ' Total'){
// 							var modalParam = "'"+data+"'";
 							commonHref =  '<a  recordName="'+data+'" type="liquefaction" class="openModel">'+data +'</a>';
 						}else{
 							commonHref =  '<p>'+data+'</p>';
 						}
 						return commonHref;
 					},
 					"targets": 0
 							}
 							],
 		 					columns: $scope.columns,
 			 				data : $scope.liquefactionData,
 						 
 				});
 				
	 			
	 			$timeout(function(){
	 				liquefactionInst.columns.adjust().draw();
//					liquefactionInst.draw();
				},100);
 			
 			
	 			
	 			 	
//	 				new $.fn.dataTable.FixedColumns(liquefactionInst,{
//		 				leftColumns:1
//		 			});		 			
//			   
//	 				$timeout(function(){
//						liquefactionInst.draw()
//					},100);
	 			
 				$rootScope.table.liquefactionInst = liquefactionInst;
 			}
 	 		
// 	
		
			
	 		if ( $.fn.dataTable.isDataTable( '#regasification' ) ) {
	 		    table = $('#regasification').DataTable();
	 		}
	 		else {
	 			var regasificationInst = $("#regasification").DataTable({
	 				scrollY :"400px",
	 				scrollX :true,
	 				"drawCallback": function( settings ) {
	 					if(!$("#regasification").parent().hasClass("table-responsive")){
//	 						$("#regasification").wrap( "<div class='table-responsive'></div>" );	 						
					     }
	 		 	    },
	 				fixedColumns :{
	 					leftColumns:1
	 				},
	 				"columnDefs": [
	 				{
	 					// The `data` parameter refers to the data for the cell (defined by the
	 				// `data` option, which defaults to the column being worked with, in
	 				// this case `data: 0`.
	 				"render": function ( data, type, row ) {
	 					var commonHref = "";
	 					if(data != ' Total'){
	 						commonHref =  '<a recordName="'+data+'"  type="regasification"  class="openModel">'+data +'</a>';
	 					}else{
	 						commonHref =  '<p>'+data+'</p>';
	 					}
	 					return commonHref;
	 				},
	 				"targets": 0
	 							}
	 							],
	 					 		columns: $scope.columns,
	 					 		data : $scope.regasificationData,
	 			 	 });
	 			
	 			$timeout(function(){
//	 				regasificationInst.draw();	
	 				regasificationInst.columns.adjust().draw();
	 			},100);
	 			
	 		
//	 				new $.fn.dataTable.FixedColumns(regasificationInst,{
//		 				leftColumns:1
//		 			});
//			     
//	 				$timeout(function(){
//		 				regasificationInst.draw()
//		 			},100);
	 			
	 			
	 			
	 			$rootScope.table.regasificationInst = regasificationInst;
	 		}
		 			
		 
			$("#liquefaction tbody tr:first").addClass('total-row');
			$("#regasification tbody tr:first").addClass('total-row');
			
 		 
			
			$("#liquefaction").unbind( "click" );
	 	 	
			$("#liquefaction").on("click", "td .openModel",function(e){
			  	openModel(e.currentTarget.getAttribute('recordName'),e.currentTarget.getAttribute('type'),e);
				e.preventDefault();
				e.stopPropagation(); 
			});
			
			$("#regasification").unbind( "click" );
			
			$("#regasification").on("click", "td .openModel",function(e){
				openModel(e.currentTarget.getAttribute('recordName'),e.currentTarget.getAttribute('type'),e);
				e.preventDefault();
				e.stopPropagation(); 
			});
 	 			 
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
 		$rootScope.exportedCountriesModel = [];
 		$rootScope.exportedCompaniesModel =[];
 		
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
					
		 			//for(var i =0 ; i < $scope.gridDataList[0].country.length; i++){
					if($scope.gridDataList[0] != undefined){
						var columnName = $rootScope.searchFilterObj.displayType.charAt(0).toUpperCase() +  $rootScope.searchFilterObj.displayType.slice(1);
						$scope.columns.push({title:columnName  ,data:"name"});
						for(var key in $scope.gridDataList[0]['totalCapacity']){
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
 		$rootScope.loadLngFilter();
   	};
   	
   	$scope.loadTableData = function(resp){
   		 
   		for(var k=0; k < resp.length; k++){
			if(resp[k].type =="Liquefaction"){
		 		$scope.liquefactionData = resp[k][$rootScope.searchFilterObj.displayType];
		 		
		 		if($scope.liquefactionData && $scope.liquefactionData.length > 0){
			 		var tempCapacity = resp[k].totalCapacity;
			 		tempCapacity.name = " Total";
			 	 	$scope.liquefactionData.push(tempCapacity);
			 	 	
			 	 	var reverseOrder = $scope.liquefactionData.slice();
			 	 	$scope.liquefactionData = [];
			 	 	$scope.liquefactionData = reverseOrder.reverse();
		 		}
			}
			
			if(resp[k].type =="Regasification"){
			 	$scope.regasificationData = resp[k][$rootScope.searchFilterObj.displayType];
			 	
			 	if($scope.regasificationData && $scope.regasificationData.length > 0){
			 		var tempCapacity = resp[k].totalCapacity;
			 		tempCapacity.name = " Total";
			 	 	$scope.regasificationData.push(tempCapacity);
			 	 	
			 	 	var reverseOrder = $scope.regasificationData.slice();
			 	 	$scope.regasificationData = [];
			 	 	$scope.regasificationData = reverseOrder.reverse();
			 	}
			 	
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
		$scope.moreDetailsHrefUrl = "";
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
 		$rootScope.exportedCountriesModel = [];
 		$rootScope.exportedCompaniesModel =[];
 		$scope.noDataAvailable = true;
		
		
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
				endDate:"2022",
				displayType:"country"
 		};
		
		$rootScope.tableHeaderObj = {
				title:"Country Wise",
				unit :"MTPA"
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