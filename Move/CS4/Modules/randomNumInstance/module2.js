function RandomNumInst(minimum, maximum)
{
	if(minimum<=maximum) {
		this.min = minimum;
		this.max = maximum;
	}
	else {
		this.min = maximum;
		this.max = minimum;
	}
	this.set = function(minimum, maximum) {
		this.min = minimum;
		this.max = maximum;
	};
	this.reset = function() {
		this.min = 0;
		this.max = 1;
	};
	this.randomInt = function() {
		return (Math.floor(Math.random()*(this.max-this.min+1)+this.min));
	};
	this.randomFloat = function() {
		return (Math.random()*(this.max-this.min)+this.min);
	};
	this.getRange = function() {
		return this.max - this.min + 1;
	};
	this.create = function(min, max) {
		return new RandomNumInst(min, max);
	};
	return this;
}
module.exports = RandomNumInst;