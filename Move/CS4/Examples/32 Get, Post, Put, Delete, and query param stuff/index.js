var express = require('express');
var app = express();

var array = [];

app.get("/animal/type", (req, res) => {
	console.log(req.query);

});
//a get request "/animal/type" will print { name = 'brett' } when calling req.query

app.get("/animal/type/:name", (req, res) => {
	console.log(req.params);
});
//a get request "/animal/type/brett" will print { name = 'brett' } when calling req.query

app.listen(3000);
console.log("started on port 3000");