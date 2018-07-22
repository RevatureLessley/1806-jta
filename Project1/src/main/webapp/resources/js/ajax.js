window.onload = function(){
	getNpcs();
}
function getEmployee(){
	let xhr = new XMLHttpRequest();
	table.innerHTML = "";
	//Use the servlet url mapping for your url
	//when hitting it with AJAX.
	
	let empId= document.getElementById("empId").value;
	let url = "SelectEmployee";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			//Converts a JSON string into a JSON object.
			let data = JSON.parse(xhr.response);
			
			//To convert to a JSON string, for java use, we use:
			//JSON.stringify()
			console.log(data);
			
		}
	}
	
	xhr.open("POST", url);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("foundID");
	
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