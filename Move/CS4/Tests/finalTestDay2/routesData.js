
var express = require("express");
var mongoose = require("mongoose");
var Info = require("./models/Info");
var User = require("./models/user");

var router = express.Router();

////////////////////////////////////////////////////
const myDatabase = require('./mongoDatabase');

let db = new myDatabase();

//add or modify.  Use getAllObjects.
router.get('/people', function(req, res){
console.log("doing a get");
	return(db.getAllObjects(res));
});

//add or modify.  Use getObjectWithID and change index to ident.
router.get('/people/:ident', function(req, res){
console.log("doing a get with an ident");
console.log(req.params.ident);
	return (db.getObjectWithID(req.params.ident,res));

});

//add or modify.  Use addObject and no need for index.
//                ident should be part of object.
router.post('/people', function(req, res){
console.log("doing a create");
console.log(req.body);


  if (!req.isAuthenticated()) {
    console.log("is not Authenticated");
    res.json({redirect:"/login"});
  } else {
    console.log("is Authenticated");
	if (req.body.ident == "") {
		res.json(null);
		return;
	}
	if (req.body.name == "") {
		res.json(null);
		return;
	}
	let obj = {ident:req.body.ident,name:req.body.name};
	return(db.addObject(obj,res));

res.json(null);

  }


});

router.get("/getTeams", (req, res) => {
	User.findOne({username: req.user.username}, function(err, user) {
		res.json({teams: user.teams});
	});
});

router.post('/addTeam', function(req, res){


  if (!req.isAuthenticated()) {
    console.log("is not Authenticated");
    res.json({redirect:"/login"});
  } else {
    console.log("is Authenticated");
	}

	var username = req.user.username;
	console.log(username);
	User.findOne({username: username}, function(err, user) {
		console.log(user);
		user.teams.push(req.body.name);
		user.save();
		res.json({name: req.body.name});
	});

});

//add or modify.  Use changeObject and no need for index.
//                ident should be part of object.
router.put('/people', function(req, res){
console.log("doing a put");
console.log(req.body);

	if (req.body.name == "") {
		res.json(null);
		return;
	}


	let obj = {ident:req.body.ident,name:req.body.name};
	return(db.changeObject(obj,res));
});

//add or modify.  Use deleteObjectWithID and change index to ident.
router.delete('/people/:ident', function(req, res){
console.log("doing a delete with an ident");
console.log(req.params.ident);

	return(db.deleteObjectWithID(req.params.ident,res));
});




module.exports = router;
