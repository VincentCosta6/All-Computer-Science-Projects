var express = require("express");
//var http = require("http");

var app = express();

app.use(function(req,res,next) {
  console.log(req.method + " to " + req.url);
  next();
});

app.get("/",function(req,res){
	res.end("Welcome to the home page with express");
});
app.get("/stuff",function(req,res){
	res.end("Welcome to the stuff page with express");
});

app.use(function(req,res) {
     res.end("Error! File not found with express");
});

//var server = http.createServer(requestHandler);
//server.listen(3000);

app.listen(3000);


