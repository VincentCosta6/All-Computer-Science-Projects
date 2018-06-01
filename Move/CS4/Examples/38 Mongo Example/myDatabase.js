
let myDatabase = function() {
	this.infoList = [];
}

myDatabase.prototype.getArraySize = function() {
	return this.infoList.length;
}

//add or modify.  Complete getAllObjects function.
myDatabase.prototype.getAllObjects = function() {
	let objs = [];
	for (let i=0;i<this.infoList.length;i++) {
		if (this.infoList[i]) {
			objs.push(this.infoList[i]);
		}
	}
	return(objs);
}

myDatabase.prototype.getObjectWithID = function(ident) {
	for (let i=0;i<this.infoList.length;i++) {
		if (this.infoList[i] && ident == this.infoList[i].ident)
			return (this.infoList[i]);
	}
	return (null);
}

myDatabase.prototype.addObject = function(obj) {
	for (let i=0;i<this.infoList.length;i++) {
		if (this.infoList[i] && obj.ident == this.infoList[i].ident)
			return (null);
	}
	this.infoList.push(obj);
	return (obj);
}


//add or modify.  Complete changeObject function.
myDatabase.prototype.changeObject = function(obj) {
	for (let i=0;i<this.infoList.length;i++) {
		if (this.infoList[i] && obj.ident == this.infoList[i].ident) {
			this.infoList[i] = obj;
			return (obj);
		}
	}
	return (null);
}


//add or modify.  Complete deleteObjectWithID function.
myDatabase.prototype.deleteObjectWithID = function(ident) {
	for (let i=0;i<this.infoList.length;i++) {
		if (this.infoList[i] && ident == this.infoList[i].ident) {
			let temp = this.infoList[i];
			this.infoList[i] = undefined;
			return (temp);
		}
	}	
	return (null);
}


module.exports = myDatabase;