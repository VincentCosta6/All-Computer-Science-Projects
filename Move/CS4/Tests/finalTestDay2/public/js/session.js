



function logoutClicked(){
	$.get("/logout",function(data){
		window.location = data.redirect;
	});
	return false;
}



function createClicked(){
//  if ($("#ident").val() == "") {
//    alert("ERROR");
//    return false;
//  }

  $.ajax({
    url: "/people",
    type: "POST",
    data: {ident:Number($("#ident").val()), name:$("#name").val()},
    success: function(data){
      if (!data)
        alert("ERROR");
      else if (data.redirect)
		window.location = data.redirect;
	 else
        alert("CREATE VALID");
    } ,
    dataType: "json"
  });

  return false;
}

function createTeam()
{
  $.post("/addTeam", {name:$("#team").val()},(data) => {
    $("#player").append("<option value=\"" + data.name + "\"> " + data.name + "</option>");
  })
}

function sportsClicked(){
	var todo = $("#player").val();
  console.log(todo);
	$.get("/sports", {team:$("#player").val()},function(data){
		if (data.redirect)
			window.location = data.redirect;
		else {
			console.log(data);

        $("#list").empty();

        for(let i in data.info.activeplayers.playerentry)
        {
          let to = "";
          if(data.info.activeplayers.playerentry[i].player.IsRookie)
            to = "R";
            else {
              to="";
            }
          if(data.info.activeplayers.playerentry[i].player.Height)
            $("#list").append("<li>" + data.info.activeplayers.playerentry[i].player.FirstName + " " + data.info.activeplayers.playerentry[i].player.LastName + " Height: " + data.info.activeplayers.playerentry[i].player.Height + ((data.info.activeplayers.playerentry[i].player.IsRookie === "true") ? "R" : "") + "</li>");
        }

//	        for (let i=0;i<data.team.length;i++) {
//
//	        	if (data.team[i].player.Height) {
//	           		$("#list").append("<li>" +
//	           		data.team[i].player.FirstName + " " +
//	           		data.team[i].player.LastName + " " +
//	           		data.team[i].player.Height + "</li>");
//			}
//	        }



		}
	});

/*
	$.get("/sports",function(data){

		if (data.redirect)
			window.location = data.redirect;
		else {
			console.log(data);
			$("#list").empty();
	        for (let i=0;i<data.info.length;i++) {
	           $("#list").append("<li>" + data.info[i].player.FirstName + " " + data.info[i].player.LastName + " " + data.info[i].player.Height + "</li>");
	        }
		}
	});
*/
	return false;
}


$(document).ready(function(){

	$.get("/userInfo",function(data){
		if (data.username)
			$("#session").html("Session " + data.username);
	});

  $("#createButton").click(createTeam);

  $.get("/getTeams", (data) => {
    for(let v in data.teams)
    {
        $("#player").append("<option value=\"" + data.teams[v] + "\"> " + data.teams[v] + "</option>");
    }
  })
	$("#logout").click(logoutClicked);
	$("#sports").click(sportsClicked);

});
