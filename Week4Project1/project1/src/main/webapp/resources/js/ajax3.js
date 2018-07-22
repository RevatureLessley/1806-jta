window.onload = function()
{
	getApprovedReimbursements();
}

function getApprovedReimbursements()
{
	let xhr = new XMLHttpRequest();
	let reimTable = document.getElementById("employeeapprovedreimbursementtable");
	let head = document.getElementById("approvedreimbursementhead");
	//reimTable.innerHTML = "";
	head.innterHTML = "";
	let url = "../ApprovedReimbursements";
	
	xhr.onreadystatechange = function()
	{
		if(xhr.readyState==4)
		{
			console.log(JSON.parse(xhr.response));
			let data = JSON.parse(xhr.response);
			
			for(index in data)
			{
				console.log("index inside data loop: " + index);
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td4 = document.createElement('td');
				let td5 = document.createElement('td');
				//let td6 = document.createElement('td');
				console.log("data[index][\"eventDesc\"]: " + data[index]["eventDesc"]);
				let td1t = document.createTextNode(data[index]["eventDesc"]);
				console.log("data[index][\"eventDate\"]: " + data[index]["eventDate"]);
				let td2t = document.createTextNode(data[index]["eventDate"]);
				console.log("data[index][\"eventTime\"]: " + data[index]["eventTime"]);
				let td3t = document.createTextNode(data[index]["eventTime"]);
				console.log("data[index][\"eventLocation\"]: " + data[index]["eventLocation"]);
				let td4t = document.createTextNode(data[index]["eventLocation"]);
				console.log("data[index][\"eventCost\"]: " + data[index]["eventCost"]);
				let td5t = document.createTextNode(data[index]["eventCost"]);
				//let td6t = document.createTextNode(data[index]["approvalName"]);
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				td5.appendChild(td5t);
				//td6.appendChild(td6t);
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				//row.appendChild(td6);
				reimTable.appendChild(row);
			}
			
			
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}