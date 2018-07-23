function getSupervisor(){
	let xhr = new XMLHttpRequest();
	let url = "SelectEmployee";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState== 4){

			let data = JSON.parse(xhr.response);
			let count = 1;
			let amountRemaining = 0;
			console.log(data);
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}
