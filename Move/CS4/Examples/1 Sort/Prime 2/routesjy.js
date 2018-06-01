var express = require("express");
var router = express.Router();
var path = require("path");

router.get("/",function(req,res){	
	var thePath = path.resolve(__dirname,"views/index.html");
	res.sendFile(thePath);	
});

var infoList = [{"name":"joe"},{"name":"jim"},{"name":"john"},{"name":"jeff"}];

router.get('/request', function(req, res){
	res.json(infoList[req.query.index]);
});

router.post('/change', function(req, res){
	infoList[req.body.index].name = req.body.name;
	res.json(infoList[req.body.index]);
});

module.exports = router;
