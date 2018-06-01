var express = require("express");
var app = express();

app.use(express.static(__dirname+"/"));

app.get("/", function(request, response){
	response.sendFile(__dirname + "/index2.html");
	console.log("Giving "+request.connection.remoteAddress + " index2.html");
})

var port = process.env.PORT || 3000;
app.listen(port);
console.log("Listening on port: "+port);
