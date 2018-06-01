var indexSize = 0;
function successRequest(data) { 
  if(!data)
    alert("False index");
  else
    alert(data.name);
}
function successChange(data) { 
  if(!data)
    alert("Failed!!");
  else
    alert(data.name);
}
let value = 0;
function requestClicked(){
  $.get("/request", {index:(value++) % indexSize},successRequest);
}
function changeClicked(){
  console.log(parseInt($("#index").val()));
  console.log($("#name").val());
  $.post("/change", {index:parseInt($("#index").val()) , name:$("#name").val()},successChange);

  return false;
}

$(document).ready(function(){        
  $("#requestButton").click(requestClicked);
  $("#changeButton").click(changeClicked);
  $.get("/size", function(data){
    indexSize = parseInt(data.size);
    console.log(indexSize);
  });
});  		
