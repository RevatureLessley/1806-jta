
function fetchEvents(filter, tableName, createF, isManager){
	
	let xhr = new XMLHttpRequest();
	let url = `/Project1/FetchEventServlet?filter=${filter}&manager=${isManager}`;

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			let data = JSON.parse(xhr.response);
			
			console.log(data);
			createF(data, tableName);
		}

	}

	xhr.open("GET", url);
	xhr.send();
}

function createEventTable(data, tableName) {

	table = document.getElementById(tableName);

	if (data.length > 0)
		table.innerHTML = "";
	else
		table.innerHTML = "no results";

	for (index in data) {
		let row = document.createElement("tr");

		row.setAttribute("onclick", `userViewEvent(${data[index]['id']})`);

		let td0 = document.createElement("td");
		let td1 = document.createElement("td");
		let td2 = document.createElement("td");
		let td3 = document.createElement("td");
		let td4 = document.createElement("td");
		let td5 = document.createElement("td");
		let td6 = document.createElement("td");

		let t0 = getIcon(data[index]);
		let t1 = document.createTextNode(data[index]["id"]);
		let t2 = document.createTextNode(data[index]["name"]);
		let t3 = document.createTextNode(data[index]["typeName"]);
		let t4 = document.createTextNode(data[index]["reimbursementAmount"]);
		let t5 = document.createTextNode(data[index]["date"]);
		let t6 = document.createTextNode(data[index]["status"]);

		td0.appendChild(t0);
		td1.appendChild(t1);
		td2.appendChild(t2);
		td3.appendChild(t3);
		td4.appendChild(t4);
		td5.appendChild(t5);
		td6.appendChild(t6);

		row.appendChild(td0);
		row.appendChild(td1);
		row.appendChild(td2);
		row.appendChild(td3);
		row.appendChild(td4);
		row.appendChild(td5);
		row.appendChild(td6);

		table.appendChild(row);
	}
}

function createEventTableWithEmpName(data, tableName) {

	table = document.getElementById(tableName);

	if (data.length > 0)
		table.innerHTML = "";
	else{
		table.innerHTML = "";
		let row = document.createElement("tr");
		row.innerHTML = "<td>no results</td>";
		table.appendChild(row);
	}

	for (index in data) {
		let row = document.createElement("tr");

		row.setAttribute("onclick", `viewEvent(${data[index]['id']})`);

		let td0 = document.createElement("td");
		let td1 = document.createElement("td");
		let td2 = document.createElement("td");
		let td3 = document.createElement("td");
		let td4 = document.createElement("td");
		let td5 = document.createElement("td");
		let td6 = document.createElement("td");
		let td7 = document.createElement("td");

		let t0 = getIcon(data[index]);
		let t1 = document.createTextNode(data[index]["id"]);
		let t2 = document.createTextNode(data[index]["employeeName"]);
		let t3 = document.createTextNode(data[index]["name"]);
		let t4 = document.createTextNode(data[index]["typeName"]);
		let t5 = document.createTextNode(data[index]["reimbursementAmount"]);
		let t6 = document.createTextNode(data[index]["date"]);
		let t7 = document.createTextNode(data[index]["status"]);

		setRowColor(row, data[index]);

		td0.appendChild(t0);
		td1.appendChild(t1);
		td2.appendChild(t2);
		td3.appendChild(t3);
		td4.appendChild(t4);
		td5.appendChild(t5);
		td6.appendChild(t6);
		td7.appendChild(t7);

		row.appendChild(td0);
		row.appendChild(td1);
		row.appendChild(td2);
		row.appendChild(td3);
		row.appendChild(td4);
		row.appendChild(td5);
		row.appendChild(td6);
		row.appendChild(td7);
		
		table.appendChild(row);
	}
}

function createEventTableSmall(data, tableName){
	
	table = document.getElementById(tableName);
	
	if(data.length > 0)
		table.innerHTML = "";
	
	for (index in data) {
		let row = document.createElement("tr");
		
		row.setAttribute("onclick",`userViewEvent(${data[index]['id']})`);
		
		let td0 = document.createElement("td");
		let td1 = document.createElement("td");
		let td2 = document.createElement("td");
		let td3 = document.createElement("td");
		
		
		let t0 = getIcon(data[index]);
		let t1 = document.createTextNode(data[index]["name"]);
		let t2 = document.createTextNode(data[index]["reimbursementAmount"]);
		let t3 = document.createTextNode(data[index]["date"]);
		
		td0.appendChild(t0);
		td1.appendChild(t1);
		td2.appendChild(t2);
		td3.appendChild(t3);
		
		row.appendChild(td0);
		row.appendChild(td1);
		row.appendChild(td2);
		row.appendChild(td3);
		
		table.appendChild(row);				
	}
}

function setRowColor(row, data) {

	switch (data["status"]) {
	case "Urgent":
		row.setAttribute("class", "table-danger");
		break;
	case "Processing":
	case "UnComfirmed":
		row.setAttribute("class", "table-success");
		break;
	case "Resolved":
		row.setAttribute("class", "table-dark");
		break;
	}
}

function getIcon(data){
	
	
	let el = document.createElement("i");
	el.setAttribute("class", "material-icons");

	
	if(data["updated"] == true)
		el.innerHTML = "markunread";

	return el;
	
	//return document.createTextNode("");
}
