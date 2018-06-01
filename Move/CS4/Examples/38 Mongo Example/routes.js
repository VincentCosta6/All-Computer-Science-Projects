
var express = require("express");
var mongoose = require("mongoose");
var Info = require("./models/Info");

var router = express.Router();

router.get("/",function(request,response){
	response.sendFile(__dirname + "/public/views/index.html");
});


////////////////////////////////////////////////////
const myDatabase = require('./myDatabase');

let db = new myDatabase();

//add or modify.  Use getAllObjects.
router.get('/read', function(req, res){


Info.find({},function(error,info) {
	if (error) {
		return res.json(null);
	} else {
		let objs = [];
		for (let i=0;i<info.length;i++) {
		  objs.push({ident:info[i].ident,name:info[i].name});
		}
		return res.json(objs);
	}
});

//	let objs= db.getAllObjects();
//	res.json(objs);	
});

//add or modify.  Use getObjectWithID and change index to ident.
router.get('/read/:ident', function(req, res){


  Info.find({ident:req.params.ident},function(error,info) {
      if (error) {
          return res.json(null);
      }
      else if (info == null) {
          return res.json(null);
      }

      if (info.length == 1)
      {
        res.json({ name: info[0].name });
      }
      else
      {
          return res.json(null);
      }
   });

//	res.json(db.getObjectWithID(req.params.ident));

});

//add or modify.  Use addObject and no need for index.
//                ident should be part of object.
router.post('/create', function(req, res){
	if (req.body.name == "") {
		res.json(null);
		return;
	}

    Info.create(req.body,function(error,info) {
        if (error) {
            return res.json(null);
        }
	  let obj = {ident:req.body.ident,name:req.body.name};
        return res.json(obj);
    });

//	let obj = {ident:req.body.ident,name:req.body.name};
//	res.json(db.addObject(obj));


});

//add or modify.  Use changeObject and no need for index.
//                ident should be part of object.
router.put('/update', function(req, res){
	if (req.body.name == "") {
		res.json(null);
		return;
	}

Info.findOneAndUpdate({ident:req.body.ident},{name:req.body.name},function(error,info) {
          if (error) {
              return res.json(null);
          }
          else if (info == null) {
              return res.json(null);
          }
          return res.json(req.body);
      });





//	let obj = {ident:req.body.ident,name:req.body.name};
//	res.json(db.changeObject(obj));
});

//add or modify.  Use deleteObjectWithID and change index to ident.
router.delete('/delete/:ident', function(req, res){

    Info.remove({ident:req.params.ident},function(error,removed) {
        if (error) {
            return res.json(null);
        }
        return res.json(removed.result);
    });

//	res.json(db.deleteObjectWithID(req.params.ident));
});




module.exports = router;

