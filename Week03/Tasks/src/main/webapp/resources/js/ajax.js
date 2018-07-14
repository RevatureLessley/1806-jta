function login() {
	let servlet = "CheckEmployeeServlet";
	let xhr = new XMLHttpRequest();
	let div = document.getElementById("beforeLogin");
	div.innerHTML = "<br>";
	let usernameKey = "username";
	let passwordKey = "password";
	let usernameValue = document.getElementById(usernameKey).value;
	let passwordValue = document.getElementById(passwordKey).value;
	let message = usernameKey + "=" + usernameValue + "&" + 
				  passwordKey + "=" + passwordValue;
	
	xhr.onreadystatechange = function() {

		if(xhr.readyState == 4) {
			console.log(xhr.responseType);
			
			if(xhr.response.trim() == "false") {
				console.log("4");
				div.innerHTML = "<p style='color:red'>" + 
									"Username or Password incorrect."+
								"<p>";
			}
		}
	}

	xhr.open("POST", servlet);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(message);
}