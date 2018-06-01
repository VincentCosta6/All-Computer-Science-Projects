var express = require('express');
var router = express.Router();


router.get("/",function(request,response){
	response.sendFile(__dirname + "/public/views/index.html");
});


var infoList = [{name:"jackjack"}];
router.get('/request', function(req, res){
	if(req.body.index < 0 || req.body.index> infoList.size - 1)
		res.json(null);
	else
		res.json(infoList[req.query.index]);
});

//below is new
router.post('/change', function(req, res){
	if(req.body.index < 0)
		res.json(null);
	else
	{
		var temp = {name:req.body.name}
		infoList[req.body.index] = temp;
		res.json(infoList[req.body.index]);
	}
});

router.get("/size",function(req,res){
	res.json({size:infoList.length});
});

module.exports = router;

