
  // mongoose is what will let us connect to our database
  var mongoose = require('mongoose');


//Schema is a decription (the definition) of the mongoDB document.
var studentSchema = mongoose.Schema({
  ident: {
    required: true,
    unique: true,
    type:String
  },
  name: String,
  gradeLevel: Number
});


  // Create a Student model
  // Specify type of each model variable
//  var Student = mongoose.model('Student', {
//  ident: {
//    required: true,
//    unique: true,
//    type:String
//  },  
//    name: String,
//    gradeLevel: Number,
//  });

  // Set Student as our module export.
  // Now other files can require this file 
  // and access the Student model and create students.

var Student = mongoose.model("Student", studentSchema);

module.exports = Student;



