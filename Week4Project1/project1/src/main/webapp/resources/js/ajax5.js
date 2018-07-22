window.onload = function()
{
	getReimbursements();
}

function getReimbursements()
{
	let xhr = new XMLHttpRequest();
	let reimTable = document.getElementById("employeependingreimbursementtable");
	let head = document.getElementById("pendingreimbursementhead");
	//reimTable.innerHTML = "";
	head.innterHTML = "";
	let url = "../GetReimbursements";
	
	xhr.onreadystatechange = function()
	{
		if(xhr.readyState==4)
		{
			console.log(JSON.parse(xhr.response));
			let data = JSON.parse(xhr.response);
			
			for(index in data)
			{
				let row = document.createElement('tr');
				
				let td1 = document.createElement('td');
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td4 = document.createElement('td');
				let td5 = document.createElement('td');
				let td6 = document.createElement('td');
				let td7 = document.createElement('td');
				
				let td1t = document.createTextNode(data[index]["eventDesc"]);
				let td2t = document.createTextNode(data[index]["eventDate"]);
				let td3t = document.createTextNode(data[index]["eventTime"]);
				let td4t = document.createTextNode(data[index]["eventLocation"]);
				let td5t = document.createTextNode(data[index]["eventCost"]);
				
				let td6t = document.createElement('button');
				let att1 = document.createAttribute('type');
				att1.value = 'submit';
				let att2 = document.createAttribute('value');
				att2.value = 'ApproveServlet';
				let att3 = document.createAttribute('onclick');
				att3.value = 'form.action="ApproveServlet"';
				td6t.innerHTML='&#10003;';
				
				let td7t = document.createElement('button');
				let att6 = document.createAttribute('type');
				att6.value = 'submit';
				let att7 = document.createAttribute('value');
				att7.value = 'DeclineServlet';
				let att8 = document.createAttribute('onclick');
				att8.value = 'form.action="DeclineServlet"';
				td7t.innerHTML='&#10005;';
				
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				td5.appendChild(td5t);
				td6.appendChild(td6t);
				td7.appendChild(td7t);
				
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				row.appendChild(td6);
				row.appendChild(td7);
				
				reimTable.appendChild(row);
			}
			
			
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}