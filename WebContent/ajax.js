// Empty JS for your own code to be here


$(document).ready(function(){
	
	 $('input[type=radio][name=tabs]').change(function() {
//	            alert("jeevan"+ this.value);
	            
	         var selectedTab=this.value;
	         var formVal=$("#myform");
	         
	 		var formData = formVal.serialize();
	 		 if(selectedTab=='crudeoil')
	         {
	        	 $.ajax({url: "/oganalysis/crudeoil", 
	        			type:"get",
	        			data:formData,
	        			contentType:"application/x-www-form-urlencoded",
	    				processData:false,	    				
	        			success:renderCrudeOilResult		
	        								
	        	});
	         }	 		
	         else if(selectedTab=='exploration')
	         {
	        	 $.ajax({url: "/oganalysis/exploration", 
	        			type:"get",
	        			data:formData,
	        			contentType:"application/x-www-form-urlencoded",
	    				processData:false,	    				
	        			success:renderExplorationResult		
	        								
	        	});
	         }
	         else if(selectedTab=='lng')
	         {
	        	 $.ajax({url: "/oganalysis/lng", 
	        			type:"get",
	        			data:formData,
	        			contentType:"application/x-www-form-urlencoded",
	    				processData:false,	    				
	        			success:renderLngResult		
	        								
	        	});
	         }
	        
	         else if(selectedTab=='naturalgas')
	         {
	        	 $.ajax({url: "/oganalysis/naturalgas", 
	        			type:"get",
	        			data:formData,
	        			contentType:"application/x-www-form-urlencoded",
	    				processData:false,	    				
	        			success:renderNaturalGasResult		
	        								
	        	});
	         }
	         else if(selectedTab=='pipeline')
	         {
	        	 $.ajax({url: "/oganalysis/pipeline", 
	        			type:"get",
	        			data:formData,
	        			contentType:"application/x-www-form-urlencoded",
	    				processData:false,	    				
	        			success:renderPipeLineResult		
	        								
	        	});
	         }
	         else if(selectedTab=='refinery')
	         {
	        	 $.ajax({url: "/oganalysis/refinery", 
	        			type:"get",  
	        			data:formData,
	        			contentType:"application/x-www-form-urlencoded",
	    				processData:false,	    				
	        			success:renderRefineryResult		
	        								
	        	});
	         }
	         else if(selectedTab=='storage')
	         {
	        	 $.ajax({url: "/oganalysis/storage", 
	        			type:"get",  
	        			data:formData,
	        			contentType:"application/x-www-form-urlencoded",
	    				processData:false,	    				
	        			success:renderStorageResult		
	        								
	        	});
	         }
	    });

    	
}); // This the closing of document.onReady()
function renderCrudeOilResult(res)
{
	if(res=='')
    {
    	$("#resultBody").html("");
    }	
    else
    {
    	var dataArray=JSON.parse(res);
		var htmlContent="";
		
		for(var i=0;i<dataArray.length;i++)
		{
			var val=dataArray[i];
			htmlContent=htmlContent+"<tr><td>"+val.field+"</td><td>"+ val.region+"</td><td>"+val.country+"</td></tr>";
		}
		
		$("#resultBody").html(htmlContent);
    }
}
function renderExplorationResult(res)
{
	    if(res=='')
	    {
	    	$("#resultBody").html("");
	    }	
	    else
	    {
	    	var dataArray=JSON.parse(res);
			var htmlContent="";
			
			for(var i=0;i<dataArray.length;i++)
			{
				var val=dataArray[i];
				htmlContent=htmlContent+"<tr><td>"+val.blockNo+"</td><td>"+ val.region+"</td><td>"+val.country+"</td></tr>";
			}
			
			$("#resultBody").html(htmlContent);
	    }
		
}
function renderLngResult(res)
{
	 	if(res=='')
	    {
	    	$("#resultBody").html("");
	    }	
	    else
	    {
	    	var dataArray=JSON.parse(res);
			var htmlContent="";
			
			for(var i=0;i<dataArray.length;i++)
			{
				var val=dataArray[i];
				htmlContent=htmlContent+"<tr><td>"+val.name+"</td><td>"+ val.region+"</td><td>"+val.country+"</td></tr>";
			}
			
			$("#resultBody").html(htmlContent);
	    }
}
function renderNaturalGasResult(res)
{
	if(res=='')
    {
    	$("#resultBody").html("");
    }	
    else
    {
    	var dataArray=JSON.parse(res);
		var htmlContent="";
		
		for(var i=0;i<dataArray.length;i++)
		{
			var val=dataArray[i];
			htmlContent=htmlContent+"<tr><td>"+val.field+"</td><td>"+ val.region+"</td><td>"+val.country+"</td></tr>";
		}
		
		$("#resultBody").html(htmlContent);
    }
}
function renderPipeLineResult(res)
{
	if(res=='')
    {
    	$("#resultBody").html("");
    }	
    else
    {
    	var dataArray=JSON.parse(res);
		var htmlContent="";
		
		for(var i=0;i<dataArray.length;i++)
		{
			var val=dataArray[i];
			htmlContent=htmlContent+"<tr><td>"+val.name+"</td><td>"+ val.region+"</td><td>"+val.country+"</td></tr>";
		}
		
		$("#resultBody").html(htmlContent);
    }
}
function renderRefineryResult(res)
{
	 if(res=='')
	    {
	    	$("#resultBody").html("");
	    }	
	    else
	    {
	    	var dataArray=JSON.parse(res);
			var htmlContent="";
			
			for(var i=0;i<dataArray.length;i++)
			{
				var val=dataArray[i];
				htmlContent=htmlContent+"<tr><td>"+val.name+"</td><td>"+ val.region+"</td><td>"+val.country+"</td></tr>";
			}
			
			$("#resultBody").html(htmlContent);
	    }
}
function renderStorageResult(res)
{
	 if(res=='')
	    {
	    	$("#resultBody").html("");
	    }	
	    else
	    {
	    	var dataArray=JSON.parse(res);
			var htmlContent="";
			
			for(var i=0;i<dataArray.length;i++)
			{
				var val=dataArray[i];
				htmlContent=htmlContent+"<tr><td>"+val.name+"</td><td>"+ val.region+"</td><td>"+val.country+"</td></tr>";
			}
			
			$("#resultBody").html(htmlContent);
	    }
}