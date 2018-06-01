  		function requestClicked(){
//        $.get("/request?index=3",successRequest);  //below line is also a query.
//        $.get("/request", {index:$("#index").val()},successRequest);

        $.ajax({
          url:"/request", 
          type:"GET", 
          data: {index:$("#index").val()}, 
          success: function(data){
            if(!data)
              alert("Error");
            else
              $("#name").val(data.name);
          }, 
          dataType : "json"
        });

  		  return false;
  		}
 		function method(data){
      if(!data.message)
            alert("Error");
      else
        alert(data.message);
    }
  		function changeClicked(){
    		  //$.post("/change", {index:$("#index").val() , name:$("#name").val()},successChange);
    		  $.ajax({
            url:"/change", 
            type:"POST", 
            data: {index:$("#index").val(), name:$("#name").val()}, 
            success: function(data){
              if(!data)
                alert("Error");
              else
                alert("Created");
            }, 
            dataType : "json"
        });
          return false;
    	}
      function putClicked(){
        PUT("/put", {index:$("#index").val(), name:$("#name").val()}, method);
      }
      function deleteClicked(){
        DELETE("/delete", {index:$("#index").val()}, method);
      }
  		$(document).ready(function(){ 

        $("#name").keydown( function( event ) {
            if ( event.which === 13 ) {
              changeClicked();
              event.preventDefault();
              return false;
            }
        });
        
        $("#index").keydown( function( event ) {
            if ( event.which === 13 ) {
              requestClicked();
              event.preventDefault();
              return false;
            }
        });

  		  $("#requestButton").click(requestClicked);
  		  $("#changeButton").click(changeClicked);  //new
        $("#put").click(putClicked);
        $("#delete").click(deleteClicked);
  		});  		
    

      function cleanAjax(URL, TYPE, DATA, successFunction, dType)
      {
        $.ajax({
          url:URL, 
          type:TYPE, 
          data: DATA,
          success: successFunction, 
          dataType : dType
        });
      }
      function PUT(url, data, success)
      {
        cleanAjax(url, "PUT", data, success, "json");
      }
      function DELETE(url, data, success)
      {
        cleanAjax(url, "DELETE", data, success, "json");
      }
