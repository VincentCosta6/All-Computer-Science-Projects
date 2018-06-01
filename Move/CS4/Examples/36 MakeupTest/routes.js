
var express = require("express");
var router = express.Router();

router.get("/",function(request,response){
	response.sendFile(__dirname + "/public/views/index.html");
});


////////////////////////////////////////////////////
const myDatabase = require('./myDatabase');

let db = new myDatabase();

//add or modify.  Use getAllObjects.
router.get('/read', function(req, res){
	let names= db.getAllObjects();
	res.json(names);	
});

//add or modify.  Use getObjectWithID and change index to ident.
router.get('/read/:index', function(req, res){
	res.json(db.getObjectWithID(req.params.ident));
});

//add or modify.  Use addObject and no need for index.
//                ident should be part of object.
router.post('/create', function(req, res){
	if (req.body.name == "") {
		res.json(null);
		return;
	}
	let obj = {name:req.body.name};
	res.json(db.addObject(obj));
});

//add or modify.  Use changeObject and no need for index.
//                ident should be part of object.
router.put('/update', function(req, res){
	if (req.body.name == "") {
		res.json(null);
		return;
	}
	let obj = {name:req.body.name, ident:req.body.ident};
	res.json(db.changeObject(req.body.ident, obj));
});

//add or modify.  Use deleteObjectWithID and change index to ident.
router.delete('/delete/:index', function(req, res){
	res.json(db.deleteObjectWithID(req.params.ident));
});




module.exports = router;

