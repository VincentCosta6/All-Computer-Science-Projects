var express = require("express");
var app = express();

console.log(__dirname);

app.get("/",function (request, response){
	response.sendFile(__dirname + "/index2.html");
});

app.listen(3000);