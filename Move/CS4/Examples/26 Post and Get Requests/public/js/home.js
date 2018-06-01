var indexer = 0;
function sucess(data)
{
	alert(data.name);
}
function requestClicked()
{
	$.get("/request", {index:(indexer++) % 4}, sucess);
	return false;
}
function changeClicked()
{
	$.post("/change", {index:0, name:"Jack"},sucess);
	return false;
}
$(document).ready(function(){
	$("#request").click(requestClicked);
	$("#change").click(changeClicked);
});