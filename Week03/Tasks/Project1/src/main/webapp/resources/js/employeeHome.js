
function setAmountRemaining(data){
	h6 = document.getElementById("amountRemaining");
	
	h6.innerHTML = "amount remaining: " + data["balance"];	
}

function createEmployeeSummary(data){
	div = document.getElementById("userInfo");
	
	let t1 = document.createTextNode(data["name"]);
	let t2 = document.createTextNode("Role: " + data["typeName"]);
	let t3 = document.createTextNode("Email: " + data["email"]);
	let t4 = document.createTextNode("Department: " + data["departmentName"]);
	let t5 = document.createTextNode("Supervisor: " + data["supervisorName"]);
	
	let p1 = document.createElement("p");
	let p2 = document.createElement("p");
	let p3 = document.createElement("p");
	let p4 = document.createElement("p");
	let p5 = document.createElement("p");
	
	p1.appendChild(t1);
	p2.appendChild(t2);
	p3.appendChild(t3);
	p4.appendChild(t4);
	p5.appendChild(t5);
	
	div.appendChild(p1);
	div.appendChild(p2);
	div.appendChild(p3);
	div.appendChild(p4);
	div.appendChild(p5);
	
}

function createEventTableSmall(data, tableName){
	
	table = document.getElementById(tableName);
	
	if(data.length > 0)
		table.innerHTML = "";
	
	for (index in data) {
		let row = document.createElement("tr");
		
		row.setAttribute("onclick",`viewEvent(${data[index]['id']})`);
		
		let td1 = document.createElement("td");
		let td2 = document.createElement("td");
		let td3 = document.createElement("td");
		
		
		let t1 = document.createTextNode(data[index]["name"]);
		let t2 = document.createTextNode(data[index]["expectedAmount"]);
		let t3 = document.createTextNode(data[index]["date"]);
		
		td1.appendChild(t1);
		td2.appendChild(t2);
		td3.appendChild(t3);
		
		row.appendChild(td1);
		row.appendChild(td2);
		row.appendChild(td3);
		
		table.appendChild(row);				
	}
}

function viewEvent(eventId){
	window.location.href = `../manage/eventview.jsp?eventId=${eventId}`;
}