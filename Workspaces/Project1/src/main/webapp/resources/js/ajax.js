window.onload = function(){
	selectEmployee();
	selectReimbursement();
	selectSupervisorReimbursements();
	selectDeptHeadReimbursements();
	selectBencoReimbursements();
	
}

function selectEmployee(){
	let xhr = new XMLHttpRequest();
	
	let url = "SelectEmployee";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			let data = JSON.parse(xhr.response);
			let table = document.getElementById('empTable');
			let wel = document.getElementById('welcome');
			console.log(data);				
			
			let row = document.createElement('tr');
			let td1 = document.createElement('td');
			let td2 = document.createElement('td');
			let td3 = document.createElement('td');
			let td4 = document.createElement('td');
			let td5 = document.createElement('td');
			let td6 = document.createElement('td');
			let td1t = document.createTextNode(data["id"])
			let td2t = document.createTextNode(data["firstName"])
			let td3t = document.createTextNode(data["lastName"])
			let td4t = document.createTextNode(data["availReim"])
			let td5t = document.createTextNode(data["pendingReim"])
			let td6t = document.createTextNode(data["awardedReim"])
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
			console.log(table);
			table.appendChild(row);
			
			wel.innerHTML = "Welcome " + data["firstName"];
				
		}
	}
	xhr.open("GET", url);
	xhr.send();
}
function selectReimbursement(){
	let xhr = new XMLHttpRequest();
	
	let url = "SelectReimbursement";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			let data = JSON.parse(xhr.response);
			let table = document.getElementById('reimTable');
			console.log(data);	
			
			for(index in data){
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				
				let td1t = document.createTextNode(data[index]["id"]);
				let td2t = document.createTextNode(data[index]["description"]);
				let td3t = document.createTextNode((data[index]["bencoApproval"]) ? "Yes":"No");
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				table.appendChild(row);
			}			
		}
	}
	xhr.open("GET", url);
	xhr.send();
}
function selectSupervisorReimbursements(){
	let xhr = new XMLHttpRequest();
	
	let url = "SelectSupervisorReimbursements";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			let data = JSON.parse(xhr.response);
			let table = document.getElementById('subTable');
			console.log(data);	
			
			if(table != null){
				for(index in data){
					let rId = data[index]['id'];
										
					let row = document.createElement('tr');
					let td1 = document.createElement('td');
					td1.setAttribute("id", rId);			
					
					let td2 = document.createElement('td');
					let td3 = document.createElement('td');
					let td4 = document.createElement('td');
					let td5 = document.createElement('td');
					
					let app = document.createTextNode("Approve");
					let td6 = document.createElement('td');				
					let b = document.createElement('button');
					b.setAttribute("onclick", "approveSuperReim(" + rId + ")");
					b.appendChild(app);
							
					
					let td1t = document.createTextNode(data[index]["id"]);
					let td2t = document.createTextNode(data[index]["empId"]);
					let td3t = document.createTextNode(data[index]["firstName"]);
					let td4t = document.createTextNode(data[index]["lastName"]);
					let td5t = document.createTextNode(data[index]["description"]);
					td1.appendChild(td1t);
					td2.appendChild(td2t);
					td3.appendChild(td3t);
					td4.appendChild(td4t);
					td5.appendChild(td5t);
					td6.appendChild(b)
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
	xhr.open("GET", url);
	xhr.send();
}

function selectDeptHeadReimbursements(){
	let xhr = new XMLHttpRequest();
	
	let url = 'SelectDeptHeadReimbursements';
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			let data = JSON.parse(xhr.response);
			let table = document.getElementById('dhTable')
			console.log(data);
			
			for(index in data){
				let rId = data[index]['id'];
				
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				td1.setAttribute("id", rId);			
				
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td4 = document.createElement('td');
				let td5 = document.createElement('td');
				
				let app = document.createTextNode("Approve");
				let td6 = document.createElement('td');				
				let b = document.createElement('button');
				b.setAttribute("onclick", "approveDeptReim(" + rId + ")");
				b.appendChild(app);						
				
				let td1t = document.createTextNode(data[index]["id"]);
				let td2t = document.createTextNode(data[index]["empId"]);
				let td3t = document.createTextNode(data[index]["firstName"]);
				let td4t = document.createTextNode(data[index]["lastName"]);
				let td5t = document.createTextNode(data[index]["description"]);
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				td5.appendChild(td5t);
				td6.appendChild(b)
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
	xhr.open("GET", url);
	xhr.send();
}

function selectBencoReimbursements(){
	let xhr = new XMLHttpRequest();
	
	let url = "SelectBencoReimbursements";	
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			
			let data = JSON.parse(xhr.response);
			let table = document.getElementById('bencoTable');
			console.log(data);
			
			for(index in data){
				let rId = data[index]['id'];
				
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				td1.setAttribute("id", rId);			
				
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td4 = document.createElement('td');
				let td5 = document.createElement('td');
				
				let app = document.createTextNode("Approve");
				let td6 = document.createElement('td');				
				let b = document.createElement('button');
				b.setAttribute("onclick", "approveBencoReim(" + rId + ")");
				b.appendChild(app);						
				
				let td1t = document.createTextNode(data[index]["id"]);
				let td2t = document.createTextNode(data[index]["empId"]);
				let td3t = document.createTextNode(data[index]["firstName"]);
				let td4t = document.createTextNode(data[index]["lastName"]);
				let td5t = document.createTextNode(data[index]["description"]);
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				td5.appendChild(td5t);
				td6.appendChild(b)
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
	xhr.open("GET", url);
	xhr.send();
}

function approveSuperReim(x){
	console.log("Method called")
	console.log(x)
	let xhr = new XMLHttpRequest();
	
	let url = 'SupervisorApproval';
	
	xhr.open("POST", url);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("rId="+x);
	location.reload();	
}

function approveDeptReim(x){
	console.log("Method called")
	console.log(x)
	let xhr = new XMLHttpRequest();
	
	let url = 'DeptHeadApproval';
	
	xhr.open("POST", url);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("rId="+x);
	location.reload();	
}

function approveBencoReim(x){
	console.log("Method called")
	console.log(x)
	let xhr = new XMLHttpRequest();
	
	let url = 'BencoApproval';
	
	xhr.open("POST", url);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send("rId="+x);
	location.reload();	
}