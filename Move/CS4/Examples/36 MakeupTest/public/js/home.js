
/* add or modify.
   change index to ident.
   when you select Read with no ident, display the ident and the name.
*/
  		function readClicked(){
        if ($("#index").val() == "") {
          $.ajax({
            url: "/read",
            type: "GET",
            success: function(data){
              if (!data)
                  alert("ERROR data");
              else {

                $("#list").empty();
               
                for (let i=0;i<data.length;i++) {
                  $("#list").append("<li>" + data[i] + "</li>");
                }
                
              }
            },
            dataType: "json"
          });              
        } else {
          $.ajax({
            url: "/read/" + $("#index").val(),
            type: "GET",
            success: function(data){
              if (!data)
                  alert("ERROR data");
              else
                  $("#name").val(data.name);        
            },
            dataType: "json"
          });   
        }
  		  return false;
  		}
 		
  		function createClicked(){
          if ($("#index").val() == "") {
            alert("ERROR");
            return false;
          }

          $.ajax({
            url: "/create",
            type: "POST",
            data: {index:Number($("#index").val()), name:$("#name").val()},
            success: function(data){
              if (!data)
                alert("ERROR");
              else
                alert("CREATE VALID");
            } ,     
            dataType: "json"
          }); 
    		  return false;
    	}

      function updateClicked(){

          if ($("#index").val() == "") {
            alert("ERROR");
            return false;
          }        
          $.ajax({
          url: "/update",
          type: "PUT",
          data: {index:Number($("#index").val()) , name:$("#name").val()},
          success: function(data){
              if (!data)
                alert("ERROR");
              else
                alert("UPDATE VALID");
            } ,     
          dataType: "json"
        });    
          return false;          
      }


      function deleteClicked(){
          $.ajax({
            url: "/delete/" + Number($("#index").val()),
            type: "DELETE",
            success: function(data) { 
              if (!data)
                alert("NO DELETE");
              else
                alert("GOOD DELETE");
            } ,   
            dataType: "json"
          });  
          return false;             
      }

  		$(document).ready(function(){ 

        $("#name").keydown( function( event ) {
            if ( event.which === 13 ) {
              createClicked();
              event.preventDefault();
              return false;
            }
        });
        
        $("#index").keydown( function( event ) {
            if ( event.which === 13 ) {
              readClicked();
              event.preventDefault();
              return false;
            }
        });

  		  $("#readButton").click(readClicked);
        $("#createButton").click(createClicked);
        $("#updateButton").click(updateClicked);
        $("#deleteButton").click(deleteClicked);

  		});  		
    

