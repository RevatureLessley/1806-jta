window.onload = function() {
	getRequests();
	getUsers();
	getHistory();
}
function getRequests() {
	let xhr = new XMLHttpRequest();
	let table = $("tbodyReq");
	table.innerHTML = "";
	// Use the servlet url mapping for your url
	// when hitting it with AJAX.
	let url = "RecordLoadingServlet";

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			// Converts a JSON string into a JSON object.
			let data = JSON.parse(xhr.response);

			// To convert to a JSON string, for java use, we use:
			// JSON.stringify()
			console.log(data);
			if (data != "") {
				for (index in data) {
					let row = document.createElement('tr');
					let td1 = document.createElement('td');
					let td2 = document.createElement('td');
					let td3 = document.createElement('td');
					let td4 = document.createElement('td');
					let td5 = document.createElement('td');
					let td6 = document.createElement('td');
					let td1t = document
							.createTextNode(data[index]["requestID"]);
					let td2t = document.createTextNode(data[index]["requestorID"]);
					let td3t = document
							.createTextNode(data[index]["statusID"]);
					let td4t = document
							.createTextNode(data[index]["eventID"]);
					let td5t = document
					.createTextNode(data[index]["requestDate"]);					
					let td6t = document
					.createTextNode(data[index]["cost"]);
					td1.appendChild(td1t);
					td2.appendChild(td2t);
					td3.appendChild(td3t);
					td4.appendChild(td4t);
					td5.appendChild(td5t);
					td6.appendChild(td6t);
					row.appendChild(td1);
					row.appendChild(td2);
					row.appendChild(td3);
					row.appendChild(td4);
					row.appendChild(td5);
					row.appendChild(td6);
					table.appendChild(row);
				}
			}

		}
	}
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.open("GET", url);
	xhr.send("type=request");

	/*
	 * If you want to use POST, you probably want to pass parameters from the
	 * clientside. To do this, use the following line: xhr.open("POST", url)
	 * //Or GET xhr.setRequestHeader("Content-type",
	 * "application/x-www-form-urlencoded");
	 * xhr.send("param1=value1&param2=value2&etc");
	 * 
	 * THEN, on the servlet, you have access to request.getParameter("param1,
	 * etc");
	 * 
	 * 
	 */
}
function getNUsers() {
	let xhr = new XMLHttpRequest();
	let table = $("tbodyUsers");
	table.innerHTML = "";
	// Use the servlet url mapping for your url
	// when hitting it with AJAX.
	let url = "RecordLoadingServlet";

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			// Converts a JSON string into a JSON object.
			let data = JSON.parse(xhr.response);

			// To convert to a JSON string, for java use, we use:
			// JSON.stringify()
			console.log(data);

			for (index in data) {
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td4 = document.createElement('td');
				let td5 = document.createElement('td');
				let td6 = document.createElement('td');
				let td7 = document.createElement('td');
				let td8 = document.createElement('td');
				let td1t = document.createTextNode(data[index]["ID"]);
				let td2t = document.createTextNode(data[index]["username"]);
				let td3t = document.createTextNode(data[index]["fName"]);
				let td4t = document.createTextNode(data[index]["lName"]);
				let td5t = document.createTextNode(data[index]["birthDate"]);
				let td6t = document.createTextNode(data[index]["hireDate"]);
				let td7t = document.createTextNode(data[index]["address"]);
				let td8t = document.createTextNode(data[index]["postalCode"]);
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				td5.appendChild(td5t);
				td6.appendChild(td6t);
				td7.appendChild(td7t);
				td8.appendChild(td8t);
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				row.appendChild(td6);
				row.appendChild(td7);
				row.appendChild(td8);
				table.appendChild(row);
			}

		}
	}
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.open("GET", url);
	xhr.send("type=user");
}

function getHistory() {
	let xhr = new XMLHttpRequest();
	let table = $("tbodyHist");
	table.innerHTML = "";
	// Use the servlet url mapping for your url
	// when hitting it with AJAX.
	let url = "RecordLoadingServlet";

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			// Converts a JSON string into a JSON object.
			let data = JSON.parse(xhr.response);

			console.log(data);

			for (index in data) {
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				let td2 = document.createElement('td');
				let td1t = document.createTextNode(data[index]["historyID"]);
				let td2t = document.createTextNode(data[index]["content"]);
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				row.appendChild(td1);
				row.appendChild(td2);
				table.appendChild(row);
			}

		}
	}
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.open("GET", url);
	xhr.send("type=history");
}