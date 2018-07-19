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

function displayPastReimbursements(employee) {
	let count = document.getElementById("numReimbursements");
	let list = document.getElementById("pastReimbursements");
	list.innerHTML = "";
	let reimbursements = employee["reimbursements"];
	let i = 0;
	
	for(r in reimbursements) {
		let event = reimbursements[r]["event"];
		list.innerHTML += "<tr>" + 
						  	"<td>" + event["type"] + "</td>" +
							"<td>" + 
								new Date(event["datetime"]).toLocaleString() + 
							"</td>" +
							"<td>" + event["location"] + "</td>" +
						  "</tr>";
		i++;
	}
	
	count.innerHTML = "You have " + i + " past reimbursements:";
}

function displayWelcome() {
	let servlet = "SessionServlet";
	let xhr = new XMLHttpRequest();
	let div = document.getElementById("welcomeMessage");
	
	xhr.onreadystatechange = function() {

		if(xhr.readyState == 4) {
			
			let employee = JSON.parse(xhr.response);
			console.log(employee);
			
			let firstname = employee["firstname"];
			div.innerHTML = "Hi " + firstname + ", welcome to your account.";
			displayPastReimbursements(employee);
		}
	}

	xhr.open("GET", servlet);
	xhr.send();
}

window.onload = function() {
	displayWelcome();
}