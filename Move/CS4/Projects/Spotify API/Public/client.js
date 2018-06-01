var express = require('express');
var server = express();


var audio = new Audio();

function playStream(request, response)
{
	audio.play(request.query.audio);
	response.json({"state":"playing"});
}
function stopStream(request, response)
{
	audio.stop();
	response.json({"state":"stopped"});
}
function resumeStream(request, response)
{
	audio.play();
	response.json({"state":"resumed"});
}

server.get("play", playStream(request, response));
server.get("stop", stopStream(request, response));
server.get("resume", resumeStream(request, response));

server.listen(3001);
