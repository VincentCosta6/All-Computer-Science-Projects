var log = function(s) {console.log(s)};
log("");

function calc(data, returnString = false)
{
	var operation2 = ["+", "-", "*", "/", "!", "^", "areaT:"];
	var operation = "";
	var result;
	var array;
	for(var i=0;i<operation2.length;i++)
	{
		if(data.includes(operation2[i]))
		{
			operation = operation2[i];
			array = data.split(operation2[i]);
			break;
		}
	}
	switch(operation)
	{
		case "+":
			result = +array[0]+ +array[1];
			return ((returnString)?(+array[0] + operation + +array[1] + "="+(result)):(result));
		case "-":
			result = +array[0]- +array[1];
			return (returnString)?(+array[0] + operation + +array[1] + "="+(result)):(result);
		case "*":
			result = +array[0]* +array[1];
			return (returnString)?(+array[0] + operation + +array[1] + "="+(result)):(result);
		case "/":
			result = +array[0]/ +array[1];
			return (returnString)?(+array[0] + operation + +array[1] + "="+(result)):(result);
		case "^":
			result = exponent(+array[0],+array[1]);
			return (returnString)?(+array[0] + operation + +array[1] + "="+(result)):(result);
		case "!":
			result = factorial(+array[0]);
			return (+array[1])?(+array[0] + operation + "="+(result)):(result);
		case "areaT:" :
			array = data.substring(6);
			array = array.split(" ");
			result = (+array[0]* +array[1])/2;
			return (returnString)?(+array[0]+"*"+ +array[1]+"/2"+ "=" +(result)):(result);
		default:
			return "Operation not found! \'"+data+"\'";
	}
}
function arrayInfo(array, request)
{
	var operation = ["largest", "smallest", "avg", "average", "med", "median", "sort"];
	for(var i=0;i<operation.length;i++)
	{
		if(request.includes(operation[i]))
		{
			operation = operation[i];
			break;
		}
	}
	switch(operation)
	{
		case "largest":
			break;
		case "smallest":
			return smallest(array);
		case "avg":
			;;;;;
		case "average":
			average(array);
			break;
		case "med":
			;;;;;;
		case "median":
			median(array, true);
			break;
		case "sort":
			break;
	}

}
function smallest(list) {
	var small = Infinity;
	for(var i=0;i<list.length;i++)
		if(list[i]<small)
			small = list[i];
	return small;
}
function average(list) {
	var ret = 0;
	for(var i=0;i<list.length;i++)
		ret = ret + list[i];
	return ret / list.length;
}
function factorial(n) {
  if (n === 0) 
  	return 1;
  return n * factorial(n - 1);
}
function exponent(value, pow)
{
	let result = 1;
	for(let i = 0;i<pow;i++)
		result = value * result;
	return result;
}
function remover(array, element) {
    const index = array.indexOf(element);
    
    if (index !== -1) {
        array.splice(index, 1);
    }
    return array;
}
function sort(list)
{
	var ret = [];
	var index=0;
	while(list.length>0)
	{
		ret[index] = smallest(list).value;
		list.splice(list.indexOf(ret[index]),1);
		index++;
	}
	return ret;
}

function median(list, sorted = false) {
	list = (sorted)?list:sort(list);
	return list[Math.round(list.length/2-1)];
}