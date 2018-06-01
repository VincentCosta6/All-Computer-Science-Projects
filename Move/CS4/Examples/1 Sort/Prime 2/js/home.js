
  		function success(data) { 
  			alert(data.name);
  		}
  		
  		var currIndex = 0;
  		function callRequest(){
  			
//    		  $.get("/request", {index:currIndex},success);
    		  $.ajax({
    			  url: "/request",
    			  type: "GET",
    			  data: {index:currIndex},
    			  success: function(data){
    				  alert(data.name);
    				  } ,		  
    			  dataType: "json"
    			});    	    		  
    		  
    		currIndex++;  
    		if (currIndex > 3)
    			currIndex = 0;
    		  return false;
    		}
  		function callChange(){
  			
 //   		  $.post("/change", {index:$("#formA input:text").first().val() , name:$("#formA input:text").last().val()},success);
    		  
    		  $.ajax({
    			  url: "/change",
    			  type: "POST",
    			  data: {index:$("#formA input:text").first().val() , name:$("#formA input:text").last().val()},
    			  success: function(data){
    				  alert(data.name);
    				  } ,		  
    			  dataType: "json"
    			});    		  
    		  
    		  return false;
    		}
  		
  		$(document).ready(function(){  
  			
  			$("#formA input:text").keydown( function( event ) {
  			    if ( event.which === 13 ) {
  			        // Do something
  			    	callChange();
  			        // Disable sending the related form
  			        event.preventDefault();
  			        return false;
  			    }
  			});
			
			
  			$("#requestButton").click(callRequest);
  		  
  		  	$("#changeButton").click(callChange);
  		  	
  		});  		
  		