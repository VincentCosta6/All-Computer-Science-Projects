var express = require('express');
var app = express();

app.get("/", function (req, res) {
	res.send("Hello world!");
});
app.get("/about", function (req, res) {
	res.send("Hello About!");
});

app.listen(3000, function() {
	console.log("App listening on 3000");
});