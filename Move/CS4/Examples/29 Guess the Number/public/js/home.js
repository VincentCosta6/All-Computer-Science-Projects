function successRequest(data) { 
    $("#changer").html(data.result);
}
function successChange(data) { 
  $("#changer").html(data.min + " " + data.max);
}
let value = 0;
function requestClicked(){
  $.get("/request", {guess:$("#guess").val()}, successRequest);
}
function changeClicked(){
  $.post("/change", {min:$("#min").val(), max:$("#max").val()}, successChange);
}
/*function changeClicked(){
  console.log(parseInt($("#index").val()));
  console.log($("#name").val());
  $.post("/change", {index:parseInt($("#index").val()) , name:$("#name").val()},successChange);

  return false;
}*/

$(document).ready(function(){        
  $("#requestButton").click(requestClicked);
  $("#changeButton").click(changeClicked);
});  		
