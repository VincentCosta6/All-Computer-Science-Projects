let min = NaN, max = NaN;



exports.setRange = function(minimum, maximum)
{
	min = minimum;
	max = maximum;
}
exports.resetRange = function()
{
	min = NaN;
	max = NaN;
}
exports.getMin = function()
{
	return (isNaN(min))?0:min;
}
exports.getMax = function()
{
	return (isNaN(max))?0:max;
}
exports.randomRange = function(min, max)
{
	return (Math.floor(Math.random()*(max-min1)+min));
}
exports.generate = function()
{
	if(isNaN(min)||isNaN(max))
		return Math.random();
	else
		return (Math.floor(Math.random()*(max-min+1)+min));
}
exports.getRange = function()
{
	return max - min + 1;
}