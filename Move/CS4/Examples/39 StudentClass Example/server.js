// mongoose is what will let us connect to our database
var mongoose = require('mongoose');
// our Student Object, check out Student.js
var Student = require('./Student');

// Use mongoose to connect to our database
mongoose.connect('mongodb://localhost/StudentShow');




// This is how you create a student
// Notice how all the fields in Student.js are provided
// If you don't specify a value, it will be null/false/0/''
/*
let aaroh = Student.create({
  ident: '1523',
  name: 'Aaroh',
  gradeLevel: 12,
},function(err,info){
	console.log(err);
});
*/
// This is how we can access the data of any student
//aaroh.then(function (aarohData) {
//  console.log(aarohData);
//});


/*
Student.findOne({ident:"1523"},function(error,info){
	console.log(error);
	console.log(info.name);
	console.log(info.gradeLevel);	
});
*/

/*
Student.findOneAndUpdate({ident:"1523"},
{name:"Kiki",gradeLevel:9},function(error,info){
	console.log(error);
	console.log(info.name);
	console.log(info.gradeLevel);	
});
Student.findOne({ident:"1523"},function(error,info){
	console.log(error);
	console.log(info.name);
	console.log(info.gradeLevel);	
});
*/

/*
let rocky = Student.create({
  ident: '28',
  name: 'Rocky',
  gradeLevel: 11,
},function(err,info){
	console.log(err);
});
*/

/*
Student.find({},function(error,info){
	console.log(error);
	console.log(info.length);
	for (var i=0;i<info.length;i++)
		console.log(info[i].name);	
});
*/

/*
Student.remove({ident:"28"},function(error,info){
	console.log(error);
	console.log(info.result);
});
*/



