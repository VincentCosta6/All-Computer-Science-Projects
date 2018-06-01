

var mongoose = require("mongoose");

var Info = mongoose.model("Info",{
	ident: {
		required: true,
		unique: true,
		type:String
	},
	name: String
});

module.exports = Info;



