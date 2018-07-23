window.onload = function()
{
	getDeclinedReimbursements();
}

function getDeclinedReimbursements()
{
	let xhr = new XMLHttpRequest();
	let reimTable = document.getElementById("employeedeclinedreimbursementtable");
	let head = document.getElementById("declinedreimbursementhead");
	let url = "../DeclinedReimbursements";
	
	xhr.onreadystatechange = function()
	{
		if(xhr.readyState==4)
		{
			let data = JSON.parse(xhr.response);
			
			for(index in data)
			{
				let row = document.createElement('tr');
				
				let td1 = document.createElement('td');
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td4 = document.createElement('td');
				let td5 = document.createElement('td');
				
				let td1t = document.createTextNode(data[index]["eventDesc"]);
				let td2t = document.createTextNode(data[index]["eventDate"]);
				let td3t = document.createTextNode(data[index]["eventTime"]);
				let td4t = document.createTextNode(data[index]["eventLocation"]);
				let td5t = document.createTextNode(data[index]["eventCost"]);
				
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				td5.appendChild(td5t);
				
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				
				reimTable.appendChild(row);
			}
			
			
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}