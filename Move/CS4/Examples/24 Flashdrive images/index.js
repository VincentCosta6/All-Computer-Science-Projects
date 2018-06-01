var express = require('express');
var app = express();


app.use(express.static(__dirname + "/"));

//use all files from "__dirname/virtual/private" when /private is requested
//app.use('/private', express.static(__dirname + "/virtual/private"));

//request is info sending to server from client.
//response is info sending to client from server.
app.get("/",function(request,response){
	response.sendFile(__dirname + "/index.html");
});
var infoList = [{"name":"bison.jpg"},{"name":"deer.jpg"}];
app.get("/request", function(req, res){
	res.json(infoList[req.query.index]);
});

var port = process.env.PORT || 3000;
app.listen(port);
