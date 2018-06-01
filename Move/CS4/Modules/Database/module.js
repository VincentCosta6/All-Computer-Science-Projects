function DataBase()
{
	this.Elements = [];

	this.add = function(element){
		this.Elements[this.Elements.length] = element;
	};
	this.set = function(index, element) {
		this.Elements[index] = element;
	};
	this.get = function(index) {
		return this.Elements[index];
	};
	this.addAt = function(element, index){
		this.Elements.splice(index, 0, element);
	};
	this.removeAt = function(index){
		this.Elements.splice(index, 1);
	};

	this.findAndRemove = function(element){
		this.removeAt(this.getElementIndex(element));
	};
	this.findAndReplace = function(element, newElement){
		this.set(this.getElementIndex(element), newElement);
	};
	this.ElementExists = function(element) {
		for (var i = 0; i < this.Elements.length; i++) 
		  if(this.compareTo(this.Elements[i], element))
		  	return true;
		return false;
	};
	this.getElementIndex = function(element) {
		for (var i = 0; i < this.Elements.length; i++) 
		  if(this.compareTo(this.Elements[i], element))
		  	return i;
		return null;
	};
	this.compareTo = (object1, object2) => {
		return JSON.stringify(object1) === JSON.stringify(object2);
	};

	return this;
}
module.exports = DataBase;