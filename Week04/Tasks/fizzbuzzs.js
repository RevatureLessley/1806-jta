function fizzBuzz() {
	let start = Math.round(document.getElementById("start").value);
	let end = Math.round(document.getElementById("end").value);

	let row = document.createElement('tr');
	let rowBreak = document.createElement('tr');
	let result = document.createElement('td');
	
	for (i = start; i < end; i++) {
		
		if (i % 3 == 0 && i % 5 == 0) {
			// Fizz Buzz
			let row = document.createElement('tr');
			let result = document.createElement('td');
			let resultText = document.createTextNode(i + ": Fizz Buzz");
			result.appendChild(resultText);
			row.appendChild(result);
			document.getElementById("resultTable").appendChild(row);
		}
		else if (i % 5 == 0) {
			// Buzz
			let row = document.createElement('tr');
			let result = document.createElement('td');
			let resultText = document.createTextNode(i + ": Buzz");
			result.appendChild(resultText);
			row.appendChild(result);
			document.getElementById("resultTable").appendChild(row);
		} 
		else if (i % 3 == 0) {
			// Fizz
			let row = document.createElement('tr');
			let result = document.createElement('td');
			let resultText = document.createTextNode(i + ": Fizz");
			result.appendChild(resultText);
			row.appendChild(result);
			document.getElementById("resultTable").appendChild(row);
		} else {
			continue;
		}
	}
}

function clear() {
	document.getElementById('tableDiv').innerHTML = "";
}