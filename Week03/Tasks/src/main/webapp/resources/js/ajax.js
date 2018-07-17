function checkSupervisor() {
	let servlet = "CheckUsernameServlet";
	let xhr = new XMLHttpRequest();
	let div = document.getElementById("beforeSupervisor");
	div.innerHTML = "";
	let usernameValue = document.getElementById("supervisor").value;
	let message = "username" + "=" + usernameValue;

	
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
	let usernameValue = document.getElementById("rusername").value;
	let message = "username" + "=" + usernameValue;

	
	xhr.onreadystatechange = function() {

		if(xhr.readyState == 4) {
			console.log(xhr.response.trim());
			if(xhr.response.trim() == "true") {
				div.innerHTML = "Username already exists.";
			}
		}
	}

	xhr.open("POST", servlet);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(message);
}

function displayWelcome() {
	let servlet = "SessionServlet";
	let xhr = new XMLHttpRequest();
	let div = document.getElementById("welcomeMessage");
	
	xhr.onreadystatechange = function() {

		if(xhr.readyState == 4) {
			
			let firstname = xhr.response.trim();
			div.innerHTML = "Hi " + firstname + ", welcome to your account.";
		}
	}

	xhr.open("GET", servlet);
	xhr.send();
}

window.onload = function() {
	displayWelcome();
}