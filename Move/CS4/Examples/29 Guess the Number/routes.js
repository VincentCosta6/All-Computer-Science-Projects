var express = require('express');
var router = express.Router();

let module2 = require('../Modules/randomNumInstance/module');
var random = new module2(0, 100);
var num = random.generate();
console.log(num);

router.get("/",function(request,response){
	response.sendFile(__dirname + "/public/views/index.html");
});
router.get("/modify",function(request,response){
	response.sendFile(__dirname + "/public/views/modify.html");
});

router.get('/request', function(req, res){
	var guess = parseInt(req.query.guess);
	if(guess<num)
		res.json({result:"Guess Higher noob"});
	else if(guess>num)
		res.json({result:"Guess Lower noob"});
	else if(guess===num)
	{
		res.json({result:"You got it loser"});
		num = random.generate();
		console.log(num);
	}
	else
		res.json({result:"Error"});
});

//below is new
router.post('/change', function(req, res){
	random.setRange(parseInt(req.body.min), parseInt(req.body.max));
	res.json({min:req.body.min, max:req.body.max});
});

module.exports = router;

