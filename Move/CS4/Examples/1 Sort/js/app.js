//The output of the program should be
//Vincent Costa
//smallest 1
//largest 24
//average 8
//median 6
//

//add or modify.  Implement the constructor function ReturnInfo.
//There should be 3 variables.
//operator should be initialized to "smallest"
//value should be initialized to 0
//display will be a function pointer that will return the string
//operator + " " + value
function ReturnInfo(operator, value) {
	this.operator = operator;
	this.value = value;
	this.display = function(){return ((operator) + " " + value)};
}

const Operator = {
	SMALLEST:0,LARGEST:1,AVERAGE:2,MEDIAN:3
}


//add or modify.  Create an array named list with the values
//1, 4, 24, 6, 7, 11, 3
//Your code can assume that there will be at least 1 value in the array.
//All values will be positive.
//The length of the array will be odd.
//No values will be duplicated.
let list = [1,4,24,6,7,11,3];


console.log(arrayFunction(list).display());
console.log(arrayFunction(list,Operator.LARGEST).display());
console.log(arrayFunction(list,Operator.AVERAGE).display());
console.log(arrayFunction(list,Operator.MEDIAN).display());


//add or modify.  Create the function arrayFunction.  
//The 2nd parameter does not have
//to be passed in and will default to Operator.SMALLEST.  You need to
//use a switch statement for smallest,largest,average,median.  The
//functions smallest, largest, average, and median should be called.
function arrayFunction(list, operator = Operator.SMALLEST)
{
	switch(operator)
	{
		case Operator.SMALLEST:
			return new ReturnInfo(operator, new ReturnInfo(operator,smallest(list).value).value);
		case Operator.LARGEST:
			return new ReturnInfo(operator, new ReturnInfo(operator,largest(list).value).value);
		case Operator.AVERAGE:
			return new ReturnInfo(operator, new ReturnInfo(operator, average(list).value).value);
		case Operator.MEDIAN:
			return new ReturnInfo(operator, new ReturnInfo(operator,median(list).value).value);
	}
}


//add or modify.  Implement the function smallest. A ReturnInfo object
//should be created and a handle to this object should be returned.
//operator should be set to "smallest" and value should contain the smallest
//value in the array list. 
function smallest(list) {
	var small = Infinity;
	for(var i=0;i<list.length;i++)
		if(list[i]<small)
			small = list[i];
	return new ReturnInfo(Operator.SMALLEST, small);
}
//add or modify.  Implement the function largest. A ReturnInfo object
//should be created and a handle to this object should be returned.
//operator should be set to "largest" and value should contain the largest
//value in the array list. 
function largest(list) {
	var large = 0;
		for(var i=0;i<list.length;i++)
			if(list[i]>large)
				large = list[i];
		return new ReturnInfo(Operator.LARGEST, large);
}
//add or modify.  Implement the function average. A ReturnInfo object
//should be created and a handle to this object should be returned.
//operator should be set to "average" and value should contain the average
//of the values in the array list. 
function average(list) {
	var ret = 0;
	for(var i=0;i<list.length;i++)
		ret = ret + list[i];
	ret = ret / list.length;
	return new ReturnInfo(Operator.AVERAGE, ret);
}
//add or modify.  Implement the function median. A ReturnInfo object
//should be created and a handle to this object should be returned.
//operator should be set to "median" and value should contain the median
//of the values in the array list. 
function remover(array, element) {
    const index = array.indexOf(element);
    
    if (index !== -1) {
        array.splice(index, 1);
    }
    return array;
}


function median(list) {
	var ret = [];
	var index=0;
	while(list.length>0)
	{
		ret[index] = smallest(list).value;
		list = remover(list, ret[index]);
		index++;
	}
	return new ReturnInfo(Operator.MEDIAN, ret[Math.round(ret.length/2-1)]);
}







