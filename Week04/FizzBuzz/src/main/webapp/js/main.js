
function writeToPage(){
	document.write("<h1>Document.write!</h1>");
	
}

count = 0;

function fizzBuzz(){
	let Start = document.getElementById("Start").value;
	let End = document.getElementById("End").value;
	
	if(count != 0){
		for(let j = 1; j < count; j++){
			removeRow(j);
		}
		let count = 0;
	}
	for(let i = Start; i < End; i++){
		count++;
		
		window.alert("it is working");

		let td1 = document.createElement('td');
		let td2 = document.createElement('td');
		

		td1.appendChild(i);
		let test = i;
		if(test % 3 == 0 && test % 5 == 0){
			let test = 'FizzBuzz';
		}
		else if(test % 3 == 0){
			let test = 'Fizz';
		}else if(test % 5 == 0){
			let test = 'Buzz'
		}
		td2.appendChild(test);
		
		let row = document.createElement("tr");
		row.appendChild(td1);
		row.appendChild(td2);
		row.setAttribute("id", npcId);

		document.getElementById("theTable").appendChild(row);
	}
	
	document.getElementById("Start").value = ""; //resets input field
	document.getElementById("End").value = "";//resets input field
	
}

function removeRow(x){
	document.getElementById(x).remove();
}

//Any functions executed here will be gauranteed that all elements are fully loaded and
//ready for manipulation.
window.onload = function(){
	var d1 = document.getElementById("1");
	var d2 = document.getElementById("2");
	var d3 = document.getElementById("3");
	
	d1.addEventListener("click", d1click, true);
	d2.addEventListener("click", d2click, true);
	d3.addEventListener("click", d3click, true);
	//AddEventListener passes 3 arguments
	//the event, the callback function, useCapture <- This is set to false by default
	
	function d1click(){
		window.alert("D1 CLICKED");
		event.stopPropagation();
		//Use this line to prevent full capturing
		//or bubbling.
		
	}
	function d2click(){
		
		window.alert("D2 CLICKED");
	}
	function d3click(){
		window.alert("D3 CLICKED");
	}
	
};