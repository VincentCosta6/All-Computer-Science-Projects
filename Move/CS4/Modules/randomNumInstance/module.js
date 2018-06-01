let min, max;
var instance = function(min, max)
{
	this.min = min;
	this.max = max;
}
instance.prototype.setRange = function(minimum, maximum)
{
	this.min = minimum;
	this.max = maximum;
}
instance.prototype.resetRange = function()
{
	this.min = NaN;
	this.max = NaN;
}
instance.prototype.getMin = function()
{
	return (isNaN(this.min))?0:this.min;
}
instance.prototype.getMax = function()
{
	return (isNaN(this.max))?0:this.max;
}
instance.prototype.randomRange = function(min, max)
{
	return (Math.floor(Math.random()*(this.max-this.min+1)+this.min));
}
instance.prototype.generate = function()
{
	if(isNaN(this.min)||isNaN(this.max))
		return Math.random();
	else
		return (Math.floor(Math.random()*(this.max-this.min+1)+this.min));
}
instance.prototype.getRange = function()
{
	return this.max - this.min + 1;
}
module.exports = instance;