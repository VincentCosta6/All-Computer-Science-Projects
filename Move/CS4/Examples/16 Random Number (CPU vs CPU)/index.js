let module2 = require('../Modules/randomNumInstance/module');

let range = new module2(0,100);
let num = range.generate();
let tries = 0;
let guess = 50;
console.log("Computer Number: "+num);
while(true)
{
	tries++;
	guess = Math.floor(range.getMin()+range.getMax()/2);
	console.log("I guessed "+guess);
	if(guess===num)
	{
		console.log("You guessed it!");
		console.log("Tries: "+tries);
		break;
	}
	else if(guess<num)
	{
		console.log("Guess Larger");
		range.setRange(guess + 1, range.getMax());
	}
	else if(guess>num)
	{
		console.log("Guess Lower");
		range.setRange(range.getMin(), guess - 1);
	}
	console.log(" ");
}