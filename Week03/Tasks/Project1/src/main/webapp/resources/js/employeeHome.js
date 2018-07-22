
function setAmountRemaining(data){
	document.getElementById("amountRemaining").innerHTML = data["reimbursementAvailable"];	
	document.getElementById("amountAwarded").innerHTML = data["balance"];
}

function createEmployeeSummary(data){
	document.getElementById("empName").innerHTML = data["name"]
	document.getElementById("empRole").innerHTML = data["typeName"]
	document.getElementById("empEmail").innerHTML = data["email"]
	document.getElementById("empDept").innerHTML = data["departmentName"]
	document.getElementById("empSuper").innerHTML = data["supervisorName"]
}

function createEventTableSmall(data, tableName){
	
	table = document.getElementById(tableName);
	
	if(data.length > 0)
		table.innerHTML = "";
	
	for (index in data) {
		let row = document.createElement("tr");
		
		row.setAttribute("onclick",`userViewEvent(${data[index]['id']})`);
		
		let td1 = document.createElement("td");
		let td2 = document.createElement("td");
		let td3 = document.createElement("td");
		
		
		let t1 = document.createTextNode(data[index]["name"]);
		let t2 = document.createTextNode(data[index]["reimbursementAmount"]);
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

