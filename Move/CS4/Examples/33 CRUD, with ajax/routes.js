
let var express = require("express");
var router = express.Router();
//var path = require("path");

//request is info sending to server from client.
//response is info sending to client from server.

router.get("/",function(request,response){
	response.sendFile(__dirname + "/public/views/index.html");
//below is new
//	var thePath = path.resolve(__dirname,"public/views/index.html");
//	response.sendFile(thePath);	
});

var infoList = [];

router.get('/request', function(req, res){

	if (req.query.index < 0 || req.query.index >= infoList.length) {
		res.json(null);
	} else {
		if (!infoList[req.query.index]) {
			res.json(null);
		} else {
			res.json(infoList[req.query.index]);
		}
	}
});


router.post('/change', function(req, res){
	if (req.body.index < 0 || req.body.name == "") {
		res.json(null);
	} else {
		let temp = {name:req.body.name};
		infoList[req.body.index] = temp;
		res.json(infoList[req.body.index]);
	}
});
router.put('/put', function(req, res){
	if (req.body.index < 0 || req.body.name == "") {
		res.json(null);
	} else {
		if(!infoList[req.body.index])
		{
			res.json(null);
			return;
		}
		let temp = {name:req.body.name};
		infoList[req.body.index] = temp;
		res.json({message:"PUT"});
	}
});
router.delete('/delete', function(req, res){
	if (req.body.index < 0 || req.body.name == "") {
		res.json(null);
	} else {
		if(!infoList[req.body.index])
		{
			res.json(null);
			return;
		}
		infoList[req.body.index] = null;
		res.json({message:"DELETE"});
	}
});
module.exports = router;

