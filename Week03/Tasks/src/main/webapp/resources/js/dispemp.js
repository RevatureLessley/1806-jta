window.onload = function(){
	getEmp();
}

function getEmp(){
	let xhr = new XMLHttpRequest();
	let list = document.getElementById("empinfo");
	list.innerHTML = "";
	
	let url = "SelectEmployeeServlet";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			let data = JSON.parse(xhr.response);
			console.log(data);
			
			list.innerHTML += "<li>Employee ID: "
				+ data["empid"] + "</li>";
			list.innerHTML += "<li>Employee Name: "
				+ data["firstN"] + " " + data["lastN"] + "</li>";
			list.innerHTML += "<li>Position: "
				+ data["empTypeName"] + "</li>";
			list.innerHTML += "<li>Direct Supervisor ID: "
				+ data["dirSupId"] + "</li>";
			list.innerHTML += "<li>Direct Supervisor Name: "
				+ data["dirSupName"] + "</li>";
			list.innerHTML += "<li>Department: "
				+ data["depName"] + "</li>";
			list.innerHTML += "<li>Pending Reimbursements Total: "
				+ data["pending"] + "</li>";
			list.innerHTML += "<li>Available Reimbursements Total: "
				+ data["awarded"] + "</li>";
			//table.appendChild(document.createElement('tr').appendChild(document.createElement('td').
			//		appendChild(document.createTextNode('asdf'))));
			/*let r1 = document.createElement('tr');
			let r1d1 = document.createElement('td');
			let r1d2 = document.createElement('td');
			let r1td1 = document.createTextNode('Employee ID: ')
			let r1td2 = document.createTextNode(data["empid"]);
			r1d1.appendChild(r1td1);
			r1d2.appendChild(r1td2);
			r1.appendChild(r1d1);
			r1.appendChild(r1d2);
			
			let r2 = document.createElement('tr');
			let r2d1 = document.createElement('td');
			let r2d2 = document.createElement('td');
			let r2d3 = document.createElement('td');
			let r2d4 = document.createElement('td');
			let r2td1 = document.createTextNode('Name: ')
			let r2td2 = document.createTextNode(data["firstN"]);
			let r2td3 = document.createTextNode(data["lastN"]);
			let r2td4 = document.createTextNode(data["empTypeName"]);
			r2d1.appendChild(r2td1);
			r2d2.appendChild(r2td2);
			r2d3.appendChild(r2td3);
			r2d4.appendChild(r2td4);
			r2.appendChild(r2d1);
			r2.appendChild(r2d2);
			r2.appendChild(r2d3);
			r2.appendChild(r2d4);
			
			let r3 = document.createElement('tr');
			let r3d1 = document.createElement('td');
			let r3d2 = document.createElement('td');
			let r3d3 = document.createElement('td');
			let r3td1 = document.createTextNode('Direct Supevisor: ')
			let r3td2 = document.createTextNode(data["dirSupId"]);
			let r3td3 = document.createTextNode(data["dirSupName"]);
			r3d1.appendChild(r3td1);
			r3d2.appendChild(r3td2);
			r3d3.appendChild(r3td3);
			r3.appendChild(r3d1);
			r3.appendChild(r3d2);
			r3.appendChild(r3d3);
			
			let r4 = document.createElement('tr');
			let r4d1 = document.createElement('td');
			let r4d2 = document.createElement('td');
			let r4td1 = document.createTextNode('Department: ')
			let r4td2 = document.createTextNode(data["depName"]);
			r4d1.appendChild(r4td1);
			r4d2.appendChild(r4td2);
			r4.appendChild(r4d1);
			r4.appendChild(r4d2);
			
			let r5 = document.createElement('tr');
			let r5d1 = document.createElement('td');
			let r5d2 = document.createElement('td');
			let r5td1 = document.createTextNode('Pending Reimbursements Total: ')
			let r5td2 = document.createTextNode(data["pending"]);
			r5d1.appendChild(r5td1);
			r5d2.appendChild(r5td2);
			r5.appendChild(r3d1);
			r5.appendChild(r3d2);
			
			let r6 = document.createElement('tr');
			let r6d1 = document.createElement('td');
			let r6d2 = document.createElement('td');
			let r6td1 = document.createTextNode('Awarded Reimbursements Total: ')
			let r6td2 = document.createTextNode(data["awarded"]);
			r6d1.appendChild(r6td1);
			r6d2.appendChild(r6td2);
			r6.appendChild(r6d1);
			r6.appendChild(r6d2);
			
			table.appendChild(r1);
			table.appendChild(r2);
			table.appendChild(r3);
			table.appendChild(r4);
			table.appendChild(r5);
			table.appendChild(r6);*/
			
		}
	}
	xhr.open("GET", url);
	xhr.send();
}