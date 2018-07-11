function fizzBuzz(){
	let a = parseInt(document.getElementById("x").value);
	let b = parseInt(document.getElementById("y").value); 	

	for(var i = a; i <= b; i++){
		let br = document.createElement('br');
		let fizz = document.createTextNode("fizz ");
		let buzz = document.createTextNode("buzz ");
		let fizzbuzz = document.createTextNode("fizzbuzz ")
		let s = document.createTextNode(i.toString()+ " ")
		
		if(i % 3 == 0 && i % 5 == 0){
			document.getElementById("output").appendChild(fizzbuzz);
			document.getElementById("output").appendChild(br);
		}
		else if(i % 3 == 0){
			document.getElementById("output").appendChild(fizz);
			document.getElementById("output").appendChild(br);
		}
		else if(i % 5 == 0){
			document.getElementById("output").appendChild(buzz);
			document.getElementById("output").appendChild(br);
		}
		else{
			document.getElementById("output").appendChild(s);
			document.getElementById("output").appendChild(br);
		}
	}
}

function reset(){
	document.getElementById("output").innerHTML = "";
}