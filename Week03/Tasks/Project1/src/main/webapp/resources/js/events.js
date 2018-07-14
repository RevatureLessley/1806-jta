
function createEventTable(data, tableName){
	
	table = document.getElementById(tableName);
	
	if(data.length > 0)
		table.innerHTML = "";
	
	for (index in data) {
		let row = document.createElement("tr");
		
		row.setAttribute("onclick",`viewEvent(${data[index]['id']})`);
		
		let td1 = document.createElement("td");
		let td2 = document.createElement("td");
		let td3 = document.createElement("td");
		let td4 = document.createElement("td");
		let td5 = document.createElement("td");
		let td6 = document.createElement("td");
		
		
		let t1 = document.createTextNode(data[index]["id"]);
		let t2 = document.createTextNode(data[index]["name"]);
		let t3 = document.createTextNode(data[index]["typeName"]);
		let t4 = document.createTextNode(data[index]["expectedAmount"]);
		let t5 = document.createTextNode(data[index]["date"]);
		let t6 = document.createTextNode(data[index]["status"]);
		
		td1.appendChild(t1);
		td2.appendChild(t2);
		td3.appendChild(t3);
		td4.appendChild(t4);
		td5.appendChild(t5);
		td6.appendChild(t6);
		
		row.appendChild(td1);
		row.appendChild(td2);
		row.appendChild(td3);
		row.appendChild(td4);
		row.appendChild(td5);
		row.appendChild(td6);
		
		table.appendChild(row);				
	}
}

function createEventTableWithEmpName(data, tableName){
	
	table = document.getElementById(tableName);
	
	if(data.length > 0)
		table.innerHTML = "";
	
	for (index in data) {
		let row = document.createElement("tr");
		
		row.setAttribute("onclick",`viewEvent(${data[index]['id']})`);
		
		let td1 = document.createElement("td");
		let td2 = document.createElement("td");
		let td3 = document.createElement("td");
		let td4 = document.createElement("td");
		let td5 = document.createElement("td");
		let td6 = document.createElement("td");
		let td7 = document.createElement("td");
		
		let t1 = document.createTextNode(data[index]["id"]);
		let t2 = document.createTextNode(data[index]["employeeName"]);
		let t3 = document.createTextNode(data[index]["name"]);
		let t4 = document.createTextNode(data[index]["typeName"]);
		let t5 = document.createTextNode(data[index]["expectedAmount"]);
		let t6 = document.createTextNode(data[index]["date"]);
		let t7 = document.createTextNode(data[index]["status"]);
		
		td1.appendChild(t1);
		td2.appendChild(t2);
		td3.appendChild(t3);
		td4.appendChild(t4);
		td5.appendChild(t5);
		td6.appendChild(t6);
		td7.appendChild(t7);
		
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

function viewEvent(eventId){
	window.location.href = `./eventview.jsp?eventId=${eventId}`;
}




