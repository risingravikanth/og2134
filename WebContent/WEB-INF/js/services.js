/*8080*
*
*
Example Service Calls
HttpService.call("/URL", {}).then(function(resp) { /* Post Request 
		 
});

HttpService.get("/URL").then(function(resp) { /* Get Request 
		 
*/ 
 
//});

 
angular.module('OGAnalysis')
	.service("URL", function(){
		this.LIVE = false; //false
	   	this.contextPath = "http://localhost:8080";
		this.apiversion = "/oganalysis";
		
		this.successObj = { 
				type: 'success',
				className:'alert alert-success',
				messages:[]
		};
		
		this.failureObj = { 
				type: 'danger',
				className:'alert alert-danger',
				messages:[]
		};
		
		this.headerRequest = {	// TODO: Once api need user token then uncomment this.
			"Content-type": 'application/x-www-form-urlencoded',
			'Access-Control-Allow-Origin':'*'
		};
		
		this.loginHeaderRequest = {	// TODO: Once api need user token then uncomment this.
			"Content-type": 'application/json' 
		};
	 	
		if(this.LIVE) { //this.LIVE
			this.contextPath = "http://localhost:8080";
		} 
		this.imageBasePath = this.contextPath+"/images/"; 	 
});

 


angular.module('OGAnalysis').service("HttpService",  function($q, $http,$rootScope,URL,$state){
	
	this.call = function(url, data) {
		var payload = JSON.stringify(data);
		alert(URL.contextPath + URL.apiversion + url);
	 	return this.post(URL.contextPath + URL.apiversion + url, payload)
 	},
 	
 	this.getJsonURL = function(url){
  		if(URL.LIVE == true){
	 		return url;
	 	}else{
 		 	 var jsonMap = {
 				"/countries":"/json/countries.txt",
 				"/regions":"/json/regions.txt",
 				"/capacity":"/json/capacityByCountryWithStartAndEndDate.txt",
 				"/storage":"/json/storage.txt",
 				"/refinery":"/json/refinery.txt",
 				"/naturalgas":"/json/naturalGas.txt",
 				"/pipeline":"/json/pipeline.txt" ,
 				"/pdfReports1":"/json/pdfReports.txt"  ,
 				"/status":"/json/status.txt"
 					
 				 
	 		 };
	  		                 
	 		return (jsonMap[url] == undefined) ? url : jsonMap[url]; 
	 	}
 	},
	 
	this.get = function(url,formData){
	 	var deferred = $q.defer();
	 	url = this.getJsonURL(url);
	 	
	  	var request = {
			method: 'GET',
		 	url: URL.contextPath + URL.apiversion + url,
			headers: URL.headerRequest
		};
 	  	if(formData != undefined){
 	  		//console.log(formData.get('region0'));
	  		request.params = formData;
	  	}
		
		$http(request).success(function (resp){
			if(resp.status == "fail"){
				 
			}
		    deferred.resolve(resp);
	    }).error(function (resp){
			deferred.reject(resp);
        });
		
		return deferred.promise;
 	},
 	
 	this.getHttp = function(url,formData){
	 	var deferred = $q.defer();
	 	url = this.getJsonURL(url);

	 	var request = {
			method: 'GET',
		 	url: URL.contextPath + URL.apiversion + url,
			headers: URL.headerRequest
		};
	  	
 	  	if(formData != undefined){
 	  	 	request.params = formData;
	  	}
		
		$http(request).success(function (resp){
			if(resp.status == "fail"){
				 
			}
		    deferred.resolve(resp);
	    }).error(function (resp){
			deferred.reject(resp);
        });
		
		return deferred.promise;
 	},
 	
 	this.getFomData =function(url,formData){
	 	var deferred = $q.defer();
	 	url = this.getJsonURL(url);
	 	
	 	
	 	var fd = new FormData();
	 	console.log("in service",formData);
	 	for(var key in formData){
 	  	 	fd.append(key ,formData[key]);
 	 	}
	 	
	   	function renderCrudeOilResult(resp){
 	  		deferred.resolve(resp);
 	  		//deferred.reject(resp);
 	   	}
 	  	
 	  	$.ajax({url: URL.contextPath + URL.apiversion + url,
				type:"get",
				data: $.param(fd),
				contentType:"application/x-www-form-urlencoded",
				processData:false,	    				
				success:renderCrudeOilResult		
	 	});
 	  	
		return deferred.promise;
 	},
	
	this.getLocalFile = function(url){
	 	var deferred = $q.defer();
		var request = {
			method: 'GET',
		 	url: URL.contextPath + url,
			headers: URL.headerRequest
		};
		
		$http(request).success(function (resp){
			if(resp.status == "fail"){
				 
			}
		    deferred.resolve(resp);
	    }).error(function (resp){
			deferred.reject(resp);
        });
		
		return deferred.promise;
 	},
	
	this.post = function(url, reqdata){
	 	var deferred = $q.defer();
		var request = {
			method: 'POST',
			url: url,
			data:reqdata,
			headers: URL.headerRequest
		};
		 
		$http(request).success(function (resp){
			if(resp.status == "fail"){
				 
			}
		    deferred.resolve(resp);
	    }).error(function (resp){
			deferred.reject(resp);
        });
		
		return deferred.promise;
 	},
 
	this.uploadimage = function(url,data){

		var deferred = $q.defer();
	  
        $http.post(URL.contextPath + URL.apiversion+url, data, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined, 'Authorization':URL.userToken}
        })
        .success(function(resp){
        	console.log("image uploading process 1 ");
			deferred.resolve(resp);
			console.log("image uploading process 2");
        })
        .error(function(resp){
        	console.log("image uploading process 3");
			deferred.reject(resp);
			console.log("image uploading process 4");
        });
	 	
		return deferred.promise;
	},
 	
	this.setUserSession=function(obj){
		localStorage.setItem("notice-ud-session",null);
		localStorage.setItem("notice-ud-session",JSON.stringify(obj));
		localStorage.setItem("userToken", obj.userToken);
		URL.userToken = obj.userToken;
		URL.headerRequest.Authorization = obj.userToken;  //TODO: Once api needs usertoken then un comment this.
 	},
	
	this.isSessionLive = function(){
		return (localStorage.getItem("notice-ud-session") != null) ? true : false;
	}
	
	this.getData = function(){
		return [{
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2011-03-31",
	"basin": "Oued-Mya",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Guern Cheikh (350b,349)",
	"moreInfo": "",
	"onshoreoroffshore": "",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 11200.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2011-03-31",
	"basin": "ILLIZI ",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Isarène Ouest (225b,227)",
	"moreInfo": "",
	"onshoreoroffshore": "",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 9343.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2011-03-31",
	"basin": "ILLIZI ",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Timissit Est (235, 239c and 244a)",
	"moreInfo": "",
	"onshoreoroffshore": "",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 5710.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2011-03-31",
	"basin": "ILLIZI ",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Bordj Omar Driss N (221b, 222b and 222c)",
	"moreInfo": "",
	"onshoreoroffshore": "",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 5691.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2011-03-31",
	"basin": "Sbâa",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Belrhazi 354a",
	"moreInfo": "",
	"onshoreoroffshore": "",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 14118.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2011-03-31",
	"basin": "BERKINE",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Rhourde Rouni II (401d, 401c and 403f)",
	"moreInfo": "",
	"onshoreoroffshore": "",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 3034.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": "Cepsa\/medgaz\/sonatrach\/others"
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2011-03-31",
	"basin": "BERKINE",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Zemlet En Naga( 403 c\/e)",
	"moreInfo": "",
	"onshoreoroffshore": "",
	"operator": "Anadarko Petroleum Corporation",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 1613.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": "Anadarko Petroleum Corporation(67%)\/Maersk Oil(33%)"
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2011-03-31",
	"basin": "BERKINE",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Rhourde El Louh II (401a and 402b)",
	"moreInfo": "",
	"onshoreoroffshore": "",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 4169.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2011-03-31",
	"basin": "BERKINE",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Rhourde Fares (406B and 209)",
	"moreInfo": "",
	"onshoreoroffshore": "",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 2773.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2011-03-31",
	"basin": "Tell",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Hodna Ouest (104a, 117a, 118a, 137a and 119a)",
	"moreInfo": "",
	"onshoreoroffshore": "",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 6678.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2010-01-16",
	"basin": "GOURARA",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Ahnet (337b, 338b, 339 a2, 339b,340b, 341b, 341a2)",
	"moreInfo": "",
	"onshoreoroffshore": "Onshore",
	"operator": "Sonatrach SPA",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 17358.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": "Total(47%),Partex Oil And Gas (Holdings) Corporation(2%) and Sonatrach SPA(51%)"
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2010-01-16",
	"basin": "ILLIZI ",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Oudoume (223b, 245n)",
	"moreInfo": "",
	"onshoreoroffshore": "Onshore",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 2902.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2010-01-16",
	"basin": "ILLIZI ",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Gara Tesselit \/Ouest Ohanet (245 Sud, 239b, 234c)",
	"moreInfo": "",
	"onshoreoroffshore": "Onshore",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 6665.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2010-01-16",
	"basin": "ILLIZI ",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Sud-Est Illizi (232,241)",
	"moreInfo": "",
	"onshoreoroffshore": "Onshore",
	"operator": "Repsol YPF",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 5641.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": "Repsol (25.725%%) Enel SPA (13.47%),GdF-Suez (9.8%) and Sonatrach SPA(51%). "
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2010-01-16",
	"basin": "AMGUID MESSAOUD",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Touggourt (433a, 416b)",
	"moreInfo": "",
	"onshoreoroffshore": "Onshore",
	"operator": "Groupement Bir Seba ",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 6034.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": "Petrovitnam Exploration and Production Corporation (PVEP-Algeria)(40%),PTTEP Algeria Company Limited (PTTEP AG)(35%) and SONATRACH(25%)"
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2010-01-16",
	"basin": "BERKINE",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Bir Romane (414s,443b,444n)",
	"moreInfo": "",
	"onshoreoroffshore": "Onshore",
	"operator": "Sonatrach SPA",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 4641.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2010-01-16",
	"basin": "BERKINE",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Hassi Bir Rekaiz( 443a-424a-415ext-414ext)",
	"moreInfo": "",
	"onshoreoroffshore": "Onshore",
	"operator": "China National Offshore Oil Corporation",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 5670.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": "PTTEP\/CNOOC"
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2010-01-16",
	"basin": "BERKINE",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "Timissit Ouest (208Est, 211)",
	"moreInfo": "",
	"onshoreoroffshore": "Onshore",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 6240.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2010-01-16",
	"basin": "BERKINE",
	"status": "Awarded",
	"ThreeDSeismic": 0.0,
	"blockNo": "El Aricha El Tahtania( 407)",
	"moreInfo": "",
	"onshoreoroffshore": "Onshore",
	"operator": "Sonatrach SPA",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 4392.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2014-09-05",
	"basin": "Northern area ",
	"status": "Available",
	"ThreeDSeismic": 0.0,
	"blockNo": "Cheliff II (102,112)",
	"moreInfo": "",
	"onshoreoroffshore": "OnShore",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 12832.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2014-09-05",
	"basin": "Northern area ",
	"status": "Available",
	"ThreeDSeismic": 0.0,
	"blockNo": "Hodna Est (104 c, 105a, 119b, 122b, 138a, 139, 140b)",
	"moreInfo": "",
	"onshoreoroffshore": "OnShore",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 17207.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2014-09-05",
	"basin": "Northern area ",
	"status": "Available",
	"ThreeDSeismic": 0.0,
	"blockNo": "Ain Beida (123a, 125b, 127b, 140a)",
	"moreInfo": "",
	"onshoreoroffshore": "OnShore",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 15416.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2014-09-05",
	"basin": "Northern area ",
	"status": "Available",
	"ThreeDSeismic": 0.0,
	"blockNo": "Melrhir (106c, 107 b, 124, 128b)",
	"moreInfo": "",
	"onshoreoroffshore": "OnShore",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 14768.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2014-09-05",
	"basin": "Northern area ",
	"status": "Available",
	"ThreeDSeismic": 0.0,
	"blockNo": "El Ouabed (103, 120, 313)",
	"moreInfo": "",
	"onshoreoroffshore": "OnShore",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 24226.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2014-09-05",
	"basin": "Central area",
	"status": "Available",
	"ThreeDSeismic": 0.0,
	"blockNo": "Mouydir Nord (326, 327, 359)",
	"moreInfo": "",
	"onshoreoroffshore": "OnShore",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 38884.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Africa",
	"licenseEnddate": "",
	"startDate": "2014-09-05",
	"basin": "Central area",
	"status": "Available",
	"ThreeDSeismic": 0.0,
	"blockNo": "Mouydir Sud (360, 361)",
	"moreInfo": "",
	"onshoreoroffshore": "OnShore",
	"operator": "",
	"country": "Algeria",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 25232.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": ""
}, {
	"region": "Asia Pacific",
	"licenseEnddate": "",
	"startDate": "2008-12-22",
	"basin": "KERALA-KONKAN",
	"status": "Relinquished",
	"ThreeDSeismic": 0.0,
	"blockNo": "KK-DWN-2005\/1",
	"moreInfo": "",
	"onshoreoroffshore": "Offshore",
	"operator": "BHP BILLITON",
	"country": "India",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 14675.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": "BHP BILLITON (26) & GVK (74)"
}, {
	"region": "Asia Pacific",
	"licenseEnddate": "",
	"startDate": "2008-12-22",
	"basin": "CAMBAY",
	"status": "Relinquished",
	"ThreeDSeismic": 0.0,
	"blockNo": "CB-ONN-2005\/8",
	"moreInfo": "",
	"onshoreoroffshore": "Offshore",
	"operator": "VASUNDHARA RESOUR ",
	"country": "India",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 133.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": "VASUNDHARA RESOUR (100)"
}, {
	"region": "Asia Pacific",
	"licenseEnddate": "",
	"startDate": "2007-03-02",
	"basin": "KERALA-KONKAN",
	"status": "Relinquished",
	"ThreeDSeismic": 0.0,
	"blockNo": "KK-DWN-2004\/1",
	"moreInfo": "",
	"onshoreoroffshore": "Offshore",
	"operator": "Oil & Natural Gas Corpn. Ltd.",
	"country": "India",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 12324.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": "ONGC(45), CIL(40) & TATA(15)"
}, {
	"region": "Asia Pacific",
	"licenseEnddate": "",
	"startDate": "2007-03-02",
	"basin": "KRISHNA-GODAVARI",
	"status": "Relinquished",
	"ThreeDSeismic": 0.0,
	"blockNo": "KG-DWN-2004\/4",
	"moreInfo": "",
	"onshoreoroffshore": "Offshore",
	"operator": "Reliance Industries Ltd.",
	"country": "India",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 11904.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": "RIL(100)"
}, {
	"region": "Asia Pacific",
	"licenseEnddate": "",
	"startDate": "2007-03-02",
	"basin": "KRISHNA-GODAVARI",
	"status": "Relinquished",
	"ThreeDSeismic": 0.0,
	"blockNo": "KG-DWN-2004\/7",
	"moreInfo": "",
	"onshoreoroffshore": "Offshore",
	"operator": "Reliance Industries Ltd.",
	"country": "India",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 11856.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": "RIL(100)"
}, {
	"region": "Asia Pacific",
	"licenseEnddate": "",
	"startDate": "2007-03-02",
	"basin": "MAHANADI - NEC",
	"status": "Relinquished",
	"ThreeDSeismic": 0.0,
	"blockNo": "MN-DWN-2004\/5",
	"moreInfo": "",
	"onshoreoroffshore": "Offshore",
	"operator": "Reliance Industries Ltd.",
	"country": "India",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 10454.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": "RIL(100)"
}, {
	"region": "Asia Pacific",
	"licenseEnddate": "",
	"startDate": "2007-03-02",
	"basin": "MIZORAM",
	"status": "Relinquished",
	"ThreeDSeismic": 0.0,
	"blockNo": "MZ-ONN-2004\/2",
	"moreInfo": "",
	"onshoreoroffshore": "Offshore",
	"operator": "NAFTOGAZ",
	"country": "India",
	"sourceEquity": "",
	"licenseNo": "",
	"area": 3619.0,
	"source": "",
	"wellsDrilled": 0.0,
	"TwoDSeismicCompleted": 0.0,
	"notes": "",
	"equityParterns": "NAFTOGAZ(10), RNRL(10), GEOPETROL (10) & REL("
}];
		
		
	}
	
 	
});

