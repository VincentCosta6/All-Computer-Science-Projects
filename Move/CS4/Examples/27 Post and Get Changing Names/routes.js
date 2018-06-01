var express = require('express');
var router = express.Router();
router.get("/", function(request, response){
	response.sendFile(__dirname + "/public/html/index.html");
});

var infoList = [{"name":"Joe"},{"name":"John"},{"name":"Dilly"},{"name":"Billy"}];

router.get("/request", function(request, response){
	response.json(infoList[request.query.index]);
});

router.post("/change", function(request, response){
	infoList[request.body.index].name = request.body.name;
	response.json(infoList[request.query.index]);
});

module.exports = router;