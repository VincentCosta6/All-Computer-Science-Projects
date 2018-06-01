var express = require("express");
var passport = require("passport");
var path = require("path");

var User = require("./models/user");
var router = express.Router();

var sports = require('./sportsModule');
sports('andrewyates', 'T4pQsEYsxXop');

//function ensureAuthenticated(req, res, next) {
//  if (req.isAuthenticated()) {
//    next();
//  } else {
//    req.flash("info", "You must be logged in to see this page.");
//    res.redirect("/login");
//  }
//}

router.use(function(req, res, next) {
  res.locals.currentUserjy = req.user;
  res.locals.errors = req.flash("error");
  res.locals.infos = req.flash("info");
  next();
});

router.get("/sports", function(req, res,next) {
  console.log("Here");
  if (!req.isAuthenticated()) {
    console.log("is not Authenticated");
    res.json({redirect:"/login"});
  } else {
    console.log("is Authenticated");
    console.log(req.query.team);
    sports.NBA.getActivePlayersTeam( req.query.team, function (err, obj) {
        if (err) {
            return console.log('Error occurred active_players: ' + err);
        }
        var result = JSON.stringify(obj);
        console.log(result);
        //console.log(result[0].stats.GamesPlayed['#text']);
        res.json({"info":obj});
    });

  }
});



router.get("/sports/:player", function(req, res,next) {
  console.log("get successroot");

  if (!req.isAuthenticated()) {
    console.log("is not Authenticated");
    res.json({redirect:"/login"});
  } else {
    console.log("is Authenticated");
    console.log("team = " + req.params.player);



    sports.NBA.getActivePlayersTeam(req.params.player, function (err, result) {
          if (err) {
              return console.log('Error occurred active_players: ' + err);
          }
console.log("Here");
      res.json({player:result});
    });




  }
});




router.use(function(req,res) {
  res.statusCode = 404;
  res.end("404 Not Found!");
});


module.exports = router;
