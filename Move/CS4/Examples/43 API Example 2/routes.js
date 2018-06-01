var express = require("express");
var router = express.Router();

var sports = require('./sportsModule');
sports('CostaVincent6', 'password12345');

router.get("/",function(request,response){
    response.sendFile(__dirname + "/index.html");
});

////////////////////////////////////

router.get("/requestActive", function(req, res, next) {
	sports.NBA.getActivePlayers( function (err, obj) {
		if (err)
    		return console.log('Error occurred active_players: ' + err);
    var result = JSON.stringify(obj);
    console.log(result);
    //console.log(result[0].stats.GamesPlayed['#text']);
		res.json({"info":obj});
	});
});
router.get("/requestPlayer", function(req, res, next) {

	sports.NBA.getPlayerStats( {playername : req.query.name}, function (err, obj) {
  		if (err) { return console.log('Error occurred active_players: ' + err); }

      var result = JSON.stringify(obj);
      console.log(result);
      res.json({"player":obj});
	});
});

////////////////////////////////////

router.use(function(req,res,next) {
	next();
});

router.use(function(req,res) {
	res.statusCode = 404;
	res.end("404!");
});

module.exports = router;
