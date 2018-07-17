function checkSupervisor() {
	let servlet = "CheckUsernameServlet";
	let xhr = new XMLHttpRequest();
	let div = document.getElementById("beforeSupervisor");
	div.innerHTML = "";
	let usernameKey = "supervisor";
	let usernameValue = document.getElementById(usernameKey).value;
	let message = usernameKey + "=" + usernameValue;

	
	xhr.onreadystatechange = function() {

		if(xhr.readyState == 4) {

			if(xhr.response.trim() == "false") {
				div.innerHTML = "Supervisor does not exist.";
			}
		}
	}

	xhr.open("POST", servlet);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(message);
}

function checkUsername() {
	let servlet = "CheckUsernameServlet";
	let xhr = new XMLHttpRequest();
	let div = document.getElementById("beforeUsername");
	div.innerHTML = "";
	let usernameKey = "username";
	let usernameValue = document.getElementById(usernameKey).value;
	let message = usernameKey + "=" + usernameValue;

	
	xhr.onreadystatechange = function() {

		if(xhr.readyState == 4) {

			if(xhr.response.trim() == "true") {
				div.innerHTML = "Username already exists.";
			}
		}
	}

	xhr.open("POST", servlet);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(message);
}