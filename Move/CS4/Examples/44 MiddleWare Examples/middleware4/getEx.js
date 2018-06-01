var express = require("express");
var path = require("path");
var http = require("http");

var app = express();



/*
//If this is first then done before app.get
//and everything will display 404!
//This is why it is last.
app.use(function(request, response) {
  response.statusCode = 404;
  response.end("404!");
});
*/

app.use(function(request, response,next) {
  console.log("done first");
  next();
});

app.get("/", function(request, response) {
  response.end("Welcome to my homepage!");
});

app.get("/about", function(request, response) {
  response.end("Welcome to the about page!");
});


app.use(function(request, response,next) {
  console.log("done if not root or about");
  next();
});


app.get("/weather", function(request, response) {
  response.end("The current weather is NICE.");
});

app.get("/hello/:who", function(request, response) {
  response.end("Hello, " + request.params.who + ".");
  // Fun fact: this has some security issues, which we will get to!
});

app.get("/redirect_home", function (request, response) {
  response.redirect("/");
});

app.get("/donothing", function (request, response,next) {
  console.log("donothing");
  next();
});

app.get("/sendfile", function (request, response) {
  var filePath = path.resolve(__dirname, "cool-facts.txt");
  response.sendFile(filePath);
});




app.use(function(request, response) {
  response.statusCode = 404;
  response.end("404!");
});

http.createServer(app).listen(3000);
