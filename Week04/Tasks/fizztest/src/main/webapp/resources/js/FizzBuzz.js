
function Reset(){
	
	document.getElementById("numMAX").value = 0;
	document.getElementById("numMIN").value = 0;
	document.getElementById("test").innerHTML = "";

}

function Start(){
	console.log();
	let MAX = parseInt(document.getElementById("numMAX").value);
	let MIN = parseInt(document.getElementById("numMIN").value);
	document.getElementById("test").innerHTML = "";
	
	print(MIN, MAX);
		
}

function print(x, y) {
	let i;
	let text;
	let test = document.getElementById("test");

	for (i = x; i <= y; i++) {
		if (i % 3 == 0 && i % 5 == 0) {
			text = document.createTextNode("FizzBuzz");
		} else if (i % 3 == 0) {
			text = document.createTextNode("Fizz");
		} else if (i % 5 == 0) {
			text = document.createTextNode("Buzz");
		} else {
			text = document.createTextNode(i);
		}

		test.appendChild(text);
		test.appendChild(document.createElement("br"));

	}

}