window.onload = function(){
	console.log("this is working so far");
	dothis();
}

function dothis(){
	let xhr = new XMLHttpRequest();
	let url = "SelectEmployee";
	
	xhr.onreadystatechange = function(){
		
		console.log("And it did the get 2");
	}
	
	xhr.open("GET", url);
	xhr.send();
}