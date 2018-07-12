window.onload = function(){
	getNpcs();
}
function getNpcs(){
	let xhr = new XMLHttpRequest();
	let table = document.getElementById("npctable");
	table.innerHTML = "";
	//Use the servlet url mapping for your url
	//when hitting it with AJAX.
	let url = "SelectNpc";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			//Converts a JSON string into a JSON object.
			let data = JSON.parse(xhr.response);
			
			//To convert to a JSON string, for java use, we use:
			//JSON.stringify()
			console.log(data);
			
			for(index in data){
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td1t = document.createTextNode(data[index]["id"]);
				let td2t = document.createTextNode(data[index]["name"]);
				let td3t = document.createTextNode(data[index]["jobClassString"]);
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				table.appendChild(row);
			}
			
			
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
	
	/*
	 * If you want to use POST, you probably want to pass parameters from the clientside.
	 * To do this, use the following line:
	 * xhr.open("POST", url) //Or GET
	 * xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	 * xhr.send("param1=value1&param2=value2&etc");
	 * 
	 * THEN, on the servlet, you have access to request.getParameter("param1, etc");
	 *  
	 * 
	 */
}