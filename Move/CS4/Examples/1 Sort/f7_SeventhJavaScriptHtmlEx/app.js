//handle to the text edit box element.
var list_input = document.getElementById("list-input");


list_input.addEventListener("keypress",function(event) {
	if (event.which != 13) //13 is the enter key.
		return;
// will only get here when return key is pressed.
//handle to a newly created list item.
	var list_item = document.createElement("li");
	list_item.textContent = list_input.value;

//handle to the unordered list element.
	var list = document.getElementById("list");
	list.appendChild(list_item);
	list_input.value = "";
});

