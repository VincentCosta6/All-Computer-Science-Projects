
var express = require('express');
var bodyParser = require('body-parser');
var path = require("path");

var routes = require("./routesjy");

var app = express();
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());
app.use('/', express.static('./'));

//app.set("views",path.resolve(__dirname,"views"));


app.use(routes);

app.listen(3000);
