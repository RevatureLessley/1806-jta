window.onload = function()
{
	getLevelTwoReimbursements();
}

function getLevelTwoReimbursements()
{
	let xhr = new XMLHttpRequest();
	let reimTable = document.getElementById("employeependingleveloneortworeimbursementtable");
	let head = document.getElementById("pendingleveloneortworeimbursementhead");
	let url = "../GetAllLevelOneOrTwoPendingReimbursements";
	
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
				let td6 = document.createElement('td');
				let td7 = document.createElement('td');
				
				let td1t = document.createTextNode(data[index]["eventDesc"]);
				let td2t = document.createTextNode(data[index]["eventDate"]);
				let td3t = document.createTextNode(data[index]["eventTime"]);
				let td4t = document.createTextNode(data[index]["eventLocation"]);
				let td5t = document.createTextNode(data[index]["eventCost"]);
				
				var approveForm = document.createElement("form");
				approveForm.setAttribute('method',"post");
				approveForm.setAttribute('action',"../BothApproveServlet");
				
				var approveSubmit = document.createElement("input"); //input element, Submit button
				approveSubmit.setAttribute('type',"submit");
				approveSubmit.setAttribute('value',"approve");
				
				approveForm.appendChild(approveSubmit);
				
				var declineForm = document.createElement("form");
				declineForm.setAttribute('method',"post");
				declineForm.setAttribute('action',"../BothDeclineServlet");
				
				var declineSubmit = document.createElement("input"); //input element, Submit button
				declineSubmit.setAttribute('type',"submit");
				declineSubmit.setAttribute('value',"decline");
				
				declineForm.appendChild(declineSubmit);
				
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				td5.appendChild(td5t);
				td6.appendChild(approveForm);
				td7.appendChild(declineForm);
				
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