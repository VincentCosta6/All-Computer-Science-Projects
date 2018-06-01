var express = require('express');
var router = express.Router();

let module2 = require('../Modules/randomNumInstance/module');
var random = new module2(0, 100);
var num = random.generate();
console.log(num);

var topics = [topic:"topicName", comments:[]];

router.get("/",function(request,response){
	response.sendFile(__dirname + "/public/views/index.html");
});

router.get('/search', function(req, res){
	var ret = "";
	for(var i = 0; i <topics.length; i++)
	{
		if(topics[i].topic === req.query.topic)
		{
			res.json({comments:topics[i].comments});
			return;
		}
	}
	res.json("Topic does not exist yet");

});

//below is new
router.post('/post', function(req, res){
	for(var i = 0; i <topics.length; i++)
	{
		if(topics[i].topic === req.body.topic)
		{

		}
	}
	topics[topics.length] = {topic:req.body.topic, comments:};
});

module.exports = router;

