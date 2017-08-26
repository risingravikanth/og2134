
 angular.module('OGAnalysis').controller('PipelinesTransNationalCtrl', function($scope,$state,$rootScope,URL,HttpService,$timeout) {
	console.log("In PipelinesTransNationalCtrl ctrl");
 	console.log($state)
	
 	$scope.setConfigurations = function(){
	 		$scope.url = "/pipeline/transnational/length";
	 		$rootScope.loadPipelinesDomesticFilter();
			$rootScope.filterObj = {
				regionField :true,
				countryField :true,
				locationField : false,
	 			operatorField : false,
				ownerField : false,
				statusField : true,
				unitsField : false,
				offshoreField : false,
				typeField :false,
				pdCommodityField :true,
				pdStartPointField :true,
				pdEndPointField :true
			};
 	} ;
	
 	
 	$scope.getBreakString= function(value){
 		var result = "";
 		var stringCount = 50;
 		if(value != undefined){
 			if(value.length > stringCount){
 				result = value.substr(0,stringCount)+'</br>'+value.substr(stringCount+1,value.length)
 			}
 		}
 		return value
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
 		
		for(var key in $rootScope.capacityFilterJSON){
			modalReq[key] = $rootScope.capacityFilterJSON[key];
  		}
		 
		HttpService.get("/refineries/modalcapacity",modalReq).then(function(resp) {
			$scope.gridDataList = angular.copy(resp);
			
			if(resp != "" && resp != undefined ){
					resp = resp;
				}else{
					resp = [];
				}
		 
	 		$scope.modelcolumns =[];
	 		
	 		if( modalReq['displayType'] != "terminal"){
	  		 	
	 			if(resp != undefined){
	 				
	 				if(resp != "" && resp != undefined ){
						resp = resp[0];
					}else{
						resp = [];
					}
	 				
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
				 
				 
			 		$scope.ModelDataList = resp['terminal'];
			 		var tempCapacity = resp.totalCapacity;
			 		tempCapacity.name = " Total";
			 	 	$scope.ModelDataList.push(tempCapacity);
			 	 	
			 	 	var reverseOrder = $scope.ModelDataList.slice();
			 	 	$scope.ModelDataList = [];
			 	 	$scope.ModelDataList = reverseOrder.reverse();
			 	 	
				 if(resp.type =="Regasification"){
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
						data :$scope.ModelDataList,
						autowidth: false
					});
		 			//tableInst.columns.adjust().draw();
		 			//debugger;
		 			$timeout(function(){
		 				tableInst.draw()
		 			},100);
		 			
		 			//tableInst.find('thead th').css('width', 'auto');
		 			$rootScope.table.modelDatatableInst = tableInst;
	 	 		}
	  	 	} else{
	  	 		$scope.terminalcolumns = [];
	  	 		$scope.terminalcolumns.push({title:"Name" ,data:"name"});  
				for(var i =URL.displayFrom ; i<= URL.displayTo_2020; i++){
					var terminalColObj = {
							title:i.toString().toUpperCase(),
							data:i
					};
					 
			 		$scope.terminalcolumns.push(terminalColObj)
				}
				
					$scope.terminalDataList =[];
				 
					var obj = {
						"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
						"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
	 				};
					
					for(var i in $scope.gridDataList.cduCapacity){
						obj["name"]="CDU Capacity (Kb/d)";
						obj["desc"]="Refining Capacity(Kb/d)";
						
						if($scope.gridDataList.cduCapacity[i] != undefined ){
							obj[i] = $scope.gridDataList.cduCapacity[i]; 
						}else{
							obj[i] = "-" 
						}
			 		}
				 	$scope.terminalDataList.push(obj);
				 	
				 	obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.vduCapacity){
							obj["name"]="VDU Capacity (Kb/d)";
							obj["desc"]="VDU Capacity (Kb/d)";
							
							if($scope.gridDataList.vduCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.vduCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
				 	
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.cokingCapacity){
							obj["name"]="Coking Capacity (Kb/d)";
							obj["desc"]="Coking Capacity";
							
							if($scope.gridDataList.cokingCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.cokingCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
				 	
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.fccCapacity){
							obj["name"]="Fluid Catalytic Cracking Capacity (Kb/d)";
							obj["desc"]="FCC (Kb/d)";
							
							if($scope.gridDataList.fccCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.fccCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.hydroCrackingCapacity){
							obj["name"]="Hydrocracking Capacity (Kb/d)";
							obj["desc"]="HydroCracking Capacity(Kb/D)";
							
							if($scope.gridDataList.hydroCrackingCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.hydroCrackingCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.crudeStorageOrTank){
							obj["name"]="Crude Storage UNIT / Tanks No.";
							obj["desc"]="Crude Storage UNIT / Tanks No.";
							
							if($scope.gridDataList.crudeStorageOrTank[i] != undefined ){
								obj[i] = $scope.gridDataList.crudeStorageOrTank[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.crudeStorageCapacity){
							obj["name"]="Crude Storage Capacity";
							obj["desc"]="Crude Storage Capacity";
							
							if($scope.gridDataList.crudeStorageCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.crudeStorageCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.visbreakingCapacity){
							obj["name"]="Visbreaking Capacity(Kb/d)";
							obj["desc"]="Visbreaking Capacity(Kb/d)";
							
							if($scope.gridDataList.visbreakingCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.visbreakingCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.reformerCapacity){
							obj["name"]="Reformer Capacity(Kb/d)";
							obj["desc"]="Reformer Capacity(Kb/d)";
							
							if($scope.gridDataList.reformerCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.reformerCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.hydroTreatingCapacity){
							obj["name"]="Hydrotreating capacity(kb/d)";
							obj["desc"]="Hydrotreating capacity(kb/d)";
							
							if($scope.gridDataList.hydroTreatingCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.hydroTreatingCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.alkylationCapacity){
							obj["name"]="Alkylation Capacity (Kb/d)";
							obj["desc"]="Alkylation Capacity";
							
							if($scope.gridDataList.alkylationCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.alkylationCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.aromaticsCapacity){
							obj["name"]="Aromatics Capacity (Kb/d)";
							obj["desc"]="Aromatics Capacity";
							
							if($scope.gridDataList.aromaticsCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.aromaticsCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.isomerizationCapacity){
							obj["name"]="Isomerization Capacity (Kb/d)";
							obj["desc"]="Isomerization Capacity";
							
							if($scope.gridDataList.isomerizationCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.isomerizationCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.polymerizationCapacity){
							obj["name"]="Polymerization Capacity (Kb/d)";
							obj["desc"]="Polymerization Capacity";
							
							if($scope.gridDataList.polymerizationCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.polymerizationCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.lubesCapacity){
							obj["name"]="Lubes Capacity (Kb/d)";
							obj["desc"]="Lubes Capacity";
							
							if($scope.gridDataList.lubesCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.lubesCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.oxygenatesCapacity){
							obj["name"]="Oxygenates Capacity (Kb/d)";
							obj["desc"]="Oxygenates Capacity";
							
							if($scope.gridDataList.oxygenatesCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.oxygenatesCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.cokeCapacity){
							obj["name"]="Coke Capacity (Kb/d)";
							obj["desc"]="Coke Capacity";
							
							if($scope.gridDataList.cokeCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.cokeCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.sulphurCapacity){
							obj["name"]="Sulphur Capacity (Kb/d)";
							obj["desc"]="Sulphur Capacity";
							
							if($scope.gridDataList.sulphurCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.sulphurCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		
			 		

			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.hydrogenCapacity){
							obj["name"]="Hydrogen Capacity (Kb/d)";
							obj["desc"]="Hydrogen Capacity";
							
							if($scope.gridDataList.hydrogenCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.hydrogenCapacity[i]; 
							}else{
								obj[i] = "-" 
							}
				 		}
			 		$scope.terminalDataList.push(obj);
			 		
			 		obj = {
							"name":"","desc":"", "2000":"","2001":"","2002":"","2003":"","2004":"","2005":"","2006":"","2007":"","2008":"","2009":"","2010":"","2011":"","2012":"","2013":"",
							"2014":"","2015":"","2016":"","2017":"","2018":"","2019":"","2020":""
		 				};
						
						for(var i in $scope.gridDataList.asphaltCapacity){
							obj["name"]="Asphalt Capacity (Kb/d)";
							obj["desc"]="Asphalt Capacity";
							
							if($scope.gridDataList.asphaltCapacity[i] != undefined ){
								obj[i] = $scope.gridDataList.asphaltCapacity[i]; 
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
 		$('#myModal').modal("show");
 	};
	
	$rootScope.typeChangeFn = function(){
	 		$scope.destroyTable();
			
			for(var key in $rootScope.searchFilterObj){
	 			$rootScope.capacityFilterJSON[key] = $rootScope.searchFilterObj[key];
	  		}
			 
			HttpService.getHttp($scope.url,$rootScope.capacityFilterJSON).then(function(resp) {
				 if($rootScope.table.liquefactionInst != ""){
					 
					if(resp != "" && resp != undefined ){
						if($rootScope.searchFilterObj.displayType != "pipeline"){
		 					resp = resp.data;
		 				}else{
		 					resp = resp;
		 				}
	 				}else{
	 					resp = [];
	 				}
				 
			 		$scope.columns =[];
			 		$scope.liquefactionData = [];
			 		$scope.regasificationData =[];
			 		 
			 		//if(resp[0][$rootScope.searchFilterObj.displayType].length != 0){
			  		 	
			 			if(resp != undefined){
			 				var columnName = $rootScope.searchFilterObj.displayType.charAt(0).toUpperCase() +  $rootScope.searchFilterObj.displayType.slice(1);
						 	$scope.columns.push({title:columnName  ,data:$rootScope.searchFilterObj.displayType});
							$scope.columns.push({title: "Length"  ,data: "length"});
							
							if($rootScope.searchFilterObj.displayType == "pipeline"){
								$scope.columns = [];
								$scope.columns.push({title: "pipeline"  ,data: "pipeline"});
								$scope.columns.push({title: "Sub-pipeline"  ,data: "subpipeline"});
								$scope.columns.push({title: "Start Point"  ,data: "startPoint"});
								$scope.columns.push({title: "End Point"  ,data: "endPoint"});
								$scope.columns.push({title: "Length"  ,data: "length"});
								$scope.columns.push({title: "Diameter"  ,data: "diameter"});
								$scope.columns.push({title: "Capacity"  ,data: "Capacity"});
							}
					 	}
			 		 	
						$scope.gridDataList = [];
						$scope.loadTableData(resp);
						$rootScope.inItDataTable();
				 }
		 	});
 	 };
	
	$scope.generateFormData = function(ary,key){
		for(var i=0;i< ary.length; i++){
			var fromkey = key+i
			if(key == 'singleSelection'){
				 fromkey = key;
			}
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
 			$scope.generateFormData($rootScope.pdCommodityModel,'singleSelection');
 		}
 		
 		if($rootScope.filterObj.pdStartPointField == true){
 			$scope.generateFormData($rootScope.pdStartPointModel,'startpoint');
 		} 
 		
 		if($rootScope.filterObj.pdEndPointField == true){
 			$scope.generateFormData($rootScope.pdEndPointModel,'endpoint');
 		} 
 	 	
  		for(var key in $rootScope.searchFilterObj){
 			$rootScope.capacityFilterJSON[key] = $rootScope.searchFilterObj[key];
  		}
   
 		HttpService.getHttp($scope.url,$rootScope.capacityFilterJSON).then(function(resp) {
			 if($rootScope.table.liquefactionInst != ""){
				 if(resp != "" && resp != undefined ){
					 	if($rootScope.searchFilterObj.displayType != "pipeline"){
		 					resp = resp.data;
		 				}else{
		 					resp = resp;
		 				}
	 				}else{
	 					resp = [];
	 				}
				 
				
		 		$scope.columns =[];
	  		
		 		if(resp != undefined){
		 			var columnName = $rootScope.searchFilterObj.displayType.charAt(0).toUpperCase() +  $rootScope.searchFilterObj.displayType.slice(1);
				 	$scope.columns.push({title:columnName  ,data:$rootScope.searchFilterObj.displayType});
					$scope.columns.push({title: "Length"  ,data: "length"});
					

					if($rootScope.searchFilterObj.displayType == "pipeline"){
						$scope.columns = [];
						$scope.columns.push({title: "pipeline"  ,data: "pipeline"});
						$scope.columns.push({title: "Sub-pipeline"  ,data: "subpipeline"});
						$scope.columns.push({title: "Start Point"  ,data: "startPoint"});
						$scope.columns.push({title: "End Point"  ,data: "endPoint"});
						$scope.columns.push({title: "Length"  ,data: "length"});
						$scope.columns.push({title: "Diameter"  ,data: "diameter"});
						$scope.columns.push({title: "Capacity"  ,data: "Capacity"});
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
 						var commonHref = "";
 						if(data != ' Total'){
 							var modalParam = "'"+data+"'";
 							commonHref =  '<p>'+data+'</p>';
 						}else{
 							commonHref =  '<p>'+data+'</p>';
 						}
 						return commonHref;
 					},
 					"targets": 0
 							}
 							],
 							"bSort" : false,
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
	 							data : $scope.regasificationData
	 				 });
	 			$rootScope.table.regasificationInst = regasificationInst;
	 		}
		 			
		 
			//$("#liquefaction tbody tr:first").addClass('total-row');
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
 		$rootScope.pdEndPointModel = [];
 		$rootScope.pdStartPointModel = [];
 		$rootScope.pdCommodityModel = [];
 		$rootScope.capacityFilterJSON ={};
 		
 		if($scope.url != ''){
			var resetReq = angular.copy($rootScope.searchFilterObj);
	 		HttpService.getHttp($scope.url,resetReq).then(function(resp) {
	 			
	 			
	 			if(resp != "" && resp != undefined ){
	 				if($rootScope.searchFilterObj.displayType != "pipeline"){
	 					resp = resp.data;
	 				}else{
	 					resp = resp;
	 				}
 				}else{
 					resp = [];
 				}
			 
	 			if(resp != "" && resp != undefined){
					$scope.gridDataList = angular.copy(resp);
					$scope.columns =[];
					
		 			//for(var i =0 ; i < $scope.gridDataList[0].country.length; i++){
					if($scope.gridDataList[0] != undefined){
						var columnName = $rootScope.searchFilterObj.displayType.charAt(0).toUpperCase() +  $rootScope.searchFilterObj.displayType.slice(1);
					 	$scope.columns.push({title:columnName  ,data:$rootScope.searchFilterObj.displayType});
						$scope.columns.push({title: "Length"  ,data: "length"});
						

						if($rootScope.searchFilterObj.displayType == "pipeline"){
							$scope.columns = [];
							$scope.columns.push({title: "pipeline"  ,data: "pipeline"});
							$scope.columns.push({title: "Sub-pipeline"  ,data: "subpipeline"});
							$scope.columns.push({title: "Start Point"  ,data: "startPoint"});
							$scope.columns.push({title: "End Point"  ,data: "endPoint"});
							$scope.columns.push({title: "Length"  ,data: "length"});
							$scope.columns.push({title: "Diameter"  ,data: "diameter"});
							$scope.columns.push({title: "Capacity"  ,data: "Capacity"});
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
	 	
   		if($rootScope.searchFilterObj.displayType == "pipeline"){
   			var pipeLineAry = [];
   			if(resp != undefined && resp.length >0){
   				for(var i=0; i< resp.length; i++){
   					for(var j=0; j< resp[i].length; j++){
   						pipeLineAry.push(resp[i][j])
   					}
   			 	}
   			}
   			$scope.liquefactionData = pipeLineAry;
   		}else{
   			$scope.liquefactionData = resp;
   		}
 		
	  		
		if($scope.liquefactionData.length ==0){
   			$scope.noDataAvailable = false;
   		}else{
   			$scope.noDataAvailable = true;
   		}
  	}
	
	
	
	$scope.init = function(){
		$scope.title = $state.current.name;
		$scope.title = "Trans National Pipeline";
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
		//$scope.setDisplayPeriod();
		
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
 		$rootScope.pdEndPointModel = [];
 		$rootScope.pdStartPointModel = [];
 		$rootScope.pdCommodityModel = [];
 		$rootScope.capacityFilterJSON ={};
 		
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
			name : "Pipeline",
			value : "pipeline",
			checked :false
        }];
		
		$rootScope.searchFilterObj = {
	 			displayType:"pipeline"
 		};
  
		if($scope.url != ''){
			var initailReq = angular.copy($rootScope.searchFilterObj)
	 		HttpService.getHttp($scope.url,initailReq).then(function(resp) {
	 			
	 			
	 			if(resp != "" && resp != undefined ){
	 				if($rootScope.searchFilterObj.displayType != "pipeline"){
	 					resp = resp.data;
	 				}else{
	 					resp = resp;
	 				}
 					
 				}else{
 					resp = [];
 				}
			 
	 			if(resp != "" && resp != undefined){
					$scope.gridDataList = angular.copy(resp);
					$scope.columns =[];
					
		 			//for(var i =0 ; i < $scope.gridDataList[0].country.length; i++){
					if($scope.gridDataList[0] != undefined){
						var columnName = $rootScope.searchFilterObj.displayType.charAt(0).toUpperCase() +  $rootScope.searchFilterObj.displayType.slice(1);
						$scope.columns.push({title:columnName  ,data:$rootScope.searchFilterObj.displayType});
						$scope.columns.push({title: "Length"  ,data: "length"});
						

						if($rootScope.searchFilterObj.displayType == "pipeline"){
							$scope.columns = [];
							$scope.columns.push({title: "pipeline"  ,data: "pipeline"});
							$scope.columns.push({title: "Sub-pipeline"  ,data: "subpipeline"});
							$scope.columns.push({title: "Start Point"  ,data: "startPoint"});
							$scope.columns.push({title: "End Point"  ,data: "endPoint"});
							$scope.columns.push({title: "Length"  ,data: "length"});
							$scope.columns.push({title: "Diameter"  ,data: "diameter"});
							$scope.columns.push({title: "Capacity"  ,data: "Capacity"});
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