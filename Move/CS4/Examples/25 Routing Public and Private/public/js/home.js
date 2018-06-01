function sucess(data)
{
	alert(data.name);
}
function requestClicked()
{
	$.get("/request", sucess);
	return false;
}
$(document).ready(function(){
	$("#request").click(requestClicked);
});