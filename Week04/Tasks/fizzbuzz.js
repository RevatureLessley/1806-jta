


function reset(){
	document.getElementById("nMax").value = 0;
	document.getElementById("nMin").value = 0;
	
	document.getElementById("fbSpace").innerHTML = "";

}

function checkFizzBuzz(){
	let max = parseInt(document.getElementById("nMax").value);
	let min = parseInt(document.getElementById("nMin").value);
	
	document.getElementById("fbSpace").innerHTML = "";
	
	if(min > max){
		document.getElementById("errorP").innerHTML = "MIN cannot be greater than MAX";
		return;
	}if(min < 0 || max < 0){
		document.getElementById("errorP").innerHTML = "Negative values are not legal";
		return;
	}else{
		document.getElementById("errorP").innerHTML = "";
	}
		
	
	printFizzBuzz(min, max);
		
}

function printFizzBuzz(x, y) {
	let i;
	let text;
	let fbSpace = document.getElementById("fbSpace");

	for (i = x; i <= y; i++) {
		if (i % 3 == 0 && i % 5 == 0) {
			text = document.createTextNode("fizz buzz");
		} else if (i % 3 == 0) {
			text = document.createTextNode("fizz");
		} else if (i % 5 == 0) {
			text = document.createTextNode("buzz");
		} else {
			text = document.createTextNode(i);
		}

		fbSpace.appendChild(text);
		fbSpace.appendChild(document.createElement("br"));

	}

}