function fizzbuzz() {
	let min = Number(document.getElementById("min").value);
	let max = Number(document.getElementById("max").value);
	let element = document.getElementById('return');
	element.innerHTML = "";
	
	for(let i = min; i <= max; i++) {
		if((i % 3 === 0) & (i % 5 == 0)) {
			element.innerHTML += "<li>" + i + ": FizzBuzz</li>";
		}
		
		else if(i % 3 === 0) {
			element.innerHTML += "<li>" + i + ": Fizz</li>";
		}
		
		else if(i % 5 === 0) {
			element.innerHTML += "<li>" + i + ": Buzz</li>";
		}
		
		else {
			element.innerHTML += "<li>" + i + ": " + i + "</li>";
		}
	}
}