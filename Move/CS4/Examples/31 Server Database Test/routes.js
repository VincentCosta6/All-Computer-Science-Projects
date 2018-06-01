
var express = require("express");
var router = express.Router();

router.get("/",function(request,response){
	response.sendFile(__dirname + "/public/views/index.html");
});

router.get("/info",function(request,response){
	response.sendFile(__dirname + "/public/views/info.html");
});

var items = [];

router.post("/change", function(req, res){
	var indexer = req.body.item.index;
	if(indexer < 0)
	{
		res.json({message:"Error"});
		return;
	}
	var objecter = req.body.item.object;
	if(objecter === "")
	{
		res.json({message:"Error"});
		return;
	}
	var colorer = req.body.item.color;
	var ratinger = req.body.item.rating;

	items[indexer] = {item:{index:indexer, object:objecter, color:colorer, rating:ratinger}};
	res.json({message:"completed"});
});


router.get("/getter", function(req, res){
	if(items[req.query.index])
		res.json(items[req.query.index]);
	else
		res.json({message:"Error"});
});

module.exports = router;

