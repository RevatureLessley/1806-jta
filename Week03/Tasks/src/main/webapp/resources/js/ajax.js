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

function displayDetails(reimbursement, r, i) {
	let event = reimbursement["event"];
	let files = event["attachments"];
	let l = "";
	
	for(let f in files) {
		let file = event["attachments"][f];
		l += "<tr class=\"collapse collapse" + i + "\">" +
			 	"<td " +
			 		"class=\"file-link\" " +
			 		"colspan=\"4\" " +
			 		"onclick=\"getAttachment(\'EventAttachmentServlet\', " + 
			 								 r + ", " + f + ")\"" +
			 	">" + 
			 		"<p>" + file["filename"] + "</p>" +  
			 	"</td>" +
			 "</tr>";
	}
	
	return "<tr>" + 
		   	"<th>" + event["type"] + "</th>" +
		   	"<th>" + 
		   		new Date(event["datetime"]).toLocaleString() + 
		   	"</th>" +
		    "<th>" +
		   	"<button " +
		   		"class=\"btn btn-link\" " +
		   			"data-toggle=\"collapse\" " +
					"data-target=\".collapse" + i + "\" " +
			">" + 
						"Toggle Details" + 
			"</button>" + 
		    "</th>" +
		   "</tr>" + 
		   "<tr class=\"collapse collapse" + i + "\">" + 
		   	"<td>Cost: $" + event["cost"] + "</td>" +
		   	"<td>" + event["location"] + "</td>" +
		   	"<td>" + event["workMissed"].split(" ")[0] + " days</td>" +
		   "<tr class=\"collapse collapse" + i + "\">" +
		   	"<td>Description:</td>" +
		   	"<td colspan=\"3\">" + event["description"] + "</td>" +
		   "</tr>" +
		   "<tr class=\"collapse collapse" + i + "\">" +
		   	"<td>Justification:</td>" +
		   	"<td colspan=\"3\">" + reimbursement["justification"] + "</td>" +
		   "</tr>" + l +
		   "</tr>";
}

function displayPastReimbursements(employee) {
	let count = document.getElementById("numReimbursements");
	let list = document.getElementById("pastReimbursements");
	list.innerHTML = "";
	let reimbursements = employee["reimbursements"];
	let i = 1;
	
	for(let r in reimbursements) {
		list.innerHTML += displayDetails(reimbursements[r], r, i);
		i++;
	}
	
	count.innerHTML = "You have " + (--i) + " past reimbursements:";
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

function getAttachment(servlet, r, f) {
	let xhr = new XMLHttpRequest();
	let message = "paramr=" + r + "&paramf=" + f;
	xhr.responseType = "blob";
	console.log(message);
	
	xhr.onreadystatechange = function() {

		if(xhr.readyState == 4) {
			let buffer = xhr.response;
			
			if(buffer) {
				let reader = new FileReader();
			      // Closure to capture the file information.
			      reader.onload = (function(theFile) {
			    	 
			        return function(e) {
			        	document.getElementById('fileDisplay').innerHTML = 
			        		['<object width=100% height=100% data="', e.target.result,
			                '" title="', escape(theFile.name), '"/>'].join('');
			        };
			      })(buffer);

			      // Read in the image file as a data URL.
			      reader.readAsDataURL(buffer);
			}
		}
	}
	
	xhr.open("POST", servlet);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send(message);
}

function checkAttachment(fil, extensions) {

	for(let e in extensions) {
			let ext = extensions[e][0];
			
			if(fil == ext) {
				return true;
			}
	}
	
	return false;
}

function checkEventAttachments(eveAtts) {
	let servlet = "MIMEServlet";
	let div = document.getElementById("afterEventAttachments");
	div.innerHTML = "";
	let files = eveAtts.target.files;
	let xhr = new XMLHttpRequest();
	
	xhr.onreadystatechange = function() {

		if(xhr.readyState == 4) {
			
			let extensions = JSON.parse(xhr.response);
			
			for(let f in files) {
				let name = files[f].name;
				
				if(name != undefined) {
					let fil = "." + name.split(".")[1];
					
					if(fil != ".undefined") {
						if(!checkAttachment(fil, extensions)) {
							div.innerHTML = 
								fil + " file extensions not supported.";
						}
					}
				}
			}
		}
	}

	xhr.open("GET", servlet);
	xhr.send();
}

window.onload = function() {
	displayWelcome();
//	getAttachment();
}