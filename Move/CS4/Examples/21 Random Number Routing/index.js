var express = require("express");
var app = express();

var module = new (require('../Modules/randomNumInstance/module')) ();
var rand;

console.log(__dirname);

app.get("/random/:min/:max",function (request, response){
	var min = parseInt(request.params.min);
	var max = parseInt(request.params.max);

	if(isNaN(min)||isNaN(max))
	{
		response.status(400);
		res.json({error:"Bad request."});
		return;
	}

	rand = new module(min,max);
	var resultVal = rand.generate();

	response.json({result:resultVal});
	console.log("Sent " + resultVal + " to " + request.connection.remoteAddress);
});

app.get("/",function (request, response){
	response.sendFile(__dirname + "/index2.html");
});

var port = process.env.PORT || 3000;
app.listen(port);
console.log("Started listening on port: " + port);

var contains = function(array, element)
{
	for(let i = 0;i<array.length;i++)
		if(array[i]===element)
			return true;
	return false;
}