
var express = require('express');


var bodyParser = require('body-parser');   //new

var routes = require("./routes");

var app = express();
app.use(bodyParser.urlencoded({ extended: true }));  //new
app.use(bodyParser.json());                          //new


app.use('/', express.static('./'));
app.use('/js', express.static('./public/js'));
app.use(routes);

app.listen(3000);





