window.onload = function(){
	selectEmployee();	
}

function selectEmployee(){
	let xhr = new XMLHttpRequest();
	
	let url = "SelectEmployee";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState == 4){
			let data = JSON.parse(xhr.response);
			let nav = document.getElementById('navbar-brand')
			let list = document.getElementById('empList');
			let table = document.getElementById('money');
			console.log(data);				
			
			nav.innerHTML = data['firstName'] + " " + data['lastName'];
			
			let td1t = document.createTextNode("Employee ID: " + data["id"]);
			let td2t = document.createTextNode("First Name: " + data["firstName"]);
			let td3t = document.createTextNode("Last Name: " + data["lastName"]);
			let td4t = document.createTextNode("Email: " + data["email"]);
			let td5t = document.createTextNode("Phone Number: " + data["phoneNumber"]);			
			
			let li1 = document.createElement('li');
			let li2 = document.createElement('li');
			let li3 = document.createElement('li');
			let li4 = document.createElement('li');
			let li5 = document.createElement('li');
			
			li1.appendChild(td1t);
			li2.appendChild(td2t);
			li3.appendChild(td3t);
			li4.appendChild(td4t);
			li5.appendChild(td5t);
			
			list.appendChild(li1);
			list.appendChild(li2);
			list.appendChild(li3);
			list.appendChild(li4);
			list.appendChild(li5);
			
			let row = document.createElement('tr');
			let td1 = document.createElement('td');
			let td2 = document.createElement('td');
			let td3 = document.createElement('td');
			
			let d1t = document.createTextNode(data["availReim"]);
			let d2t = document.createTextNode(data["pendingReim"]);				
			let d3t = document.createTextNode(data["awardedReim"]);
			
			td1.appendChild(d1t);
			td2.appendChild(d2t);
			td3.appendChild(d3t);
			row.appendChild(td1);
			row.appendChild(td2);
			row.appendChild(td3);
			table.appendChild(row);
			
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
			let div = document.getElementById('reim');
			let table = document.createElement('table');
			table.setAttribute("id", "reimTable");
			table.setAttribute("class", "table")
			div.innerHTML = "";
			console.log(data);	
			
			let headRow = document.createElement('tr');
			let head1 = document.createElement('th');
			let head2 = document.createElement('th');
			let head3 = document.createElement('th');
			let head4 = document.createElement('th');
			let head5 = document.createElement('th');
			
			let h1t = document.createTextNode('ID');
			let h2t = document.createTextNode('DESCRIPTION');
			let h3t = document.createTextNode('COST');
			let h4t = document.createTextNode('PROJECTED COVERAGE');
			let h5t = document.createTextNode('APPROVED');
			
 			head1.appendChild(h1t);
 			head2.appendChild(h2t);
 			head3.appendChild(h3t);
 			head4.appendChild(h4t);
 			head5.appendChild(h5t);
			headRow.appendChild(head1);
			headRow.appendChild(head2);
			headRow.appendChild(head3);
			headRow.appendChild(head4);
			headRow.appendChild(head5);
			
			table.appendChild(headRow);
			div.appendChild(table);
			
			for(index in data){
				
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td4 = document.createElement('td');
				let td5 = document.createElement('td');
								
				let td1t = document.createTextNode(data[index]["id"]);
				let td2t = document.createTextNode(data[index]["description"]);				
				let td3t = document.createTextNode(data[index]["cost"]);
				let td4t = document.createTextNode(data[index]["coverage"]);
				let td5t = document.createTextNode((data[index]["bencoApproval"]) ? "Yes":"No");
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
			let div = document.getElementById('sub');
			let table = document.createElement('table');
			table.setAttribute("id", "subTable");
			table.setAttribute("class", "table");
			div.innerHTML = "";
			console.log(data);	
			
			let headRow = document.createElement('tr');
			let head1 = document.createElement('th');
			let head2 = document.createElement('th');
			let head3 = document.createElement('th');
			let head4 = document.createElement('th');
			let head5 = document.createElement('th');
			let head6 = document.createElement('th');
			let head7 = document.createElement('th');
			
			let h1t = document.createTextNode('ID');
			let h2t = document.createTextNode('EMPLOYEE ID');
			let h3t = document.createTextNode('FIRST NAME');
			let h4t = document.createTextNode('LAST NAME');
			let h5t = document.createTextNode('DESCRIPTION');
			let h6t = document.createTextNode('URGENT');
			let h7t = document.createTextNode('APPROVE');
			
			head1.appendChild(h1t);
 			head2.appendChild(h2t);
 			head3.appendChild(h3t);
 			head4.appendChild(h4t);
 			head5.appendChild(h5t);
 			head6.appendChild(h6t);
 			head7.appendChild(h7t);
			headRow.appendChild(head1);
			headRow.appendChild(head2);
			headRow.appendChild(head3);
			headRow.appendChild(head4);
			headRow.appendChild(head5);
			headRow.appendChild(head6);
			headRow.appendChild(head7);
			
			table.appendChild(headRow);
			div.appendChild(table);
			
			for(index in data){
				let rId = data[index]['id'];
									
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				td1.setAttribute("id", rId);			
				
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td4 = document.createElement('td');
				let td5 = document.createElement('td');
				let td6 = document.createElement('td');
				
				let app = document.createTextNode("Approve");
				let td7 = document.createElement('td');				
				let b = document.createElement('button');
				b.setAttribute("class", "btn btn-success");
				b.setAttribute("onclick", "approveSuperReim(" + rId + ")");
				b.appendChild(app);
				
				
				let img = document.createElement('img');
				if(data[index]["urgent"]){						
					img.setAttribute("src", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/Green_tick.svg/768px-Green_tick.svg.png");
					img.setAttribute("width", 20);
					img.setAttribute("height", 20);
				}
						
				
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
				td6.appendChild(img);
				td7.appendChild(b);
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				row.appendChild(td6);
				row.appendChild(td7);
				table.appendChild(row);
				
				setTimeout(function(){approveSuperReim(rId)}, 20000);
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
			let div = document.getElementById('sub');
			let table = document.createElement('table');
			table.setAttribute("id", "subTable");
			table.setAttribute("class", "table");
			div.innerHTML = "";
			console.log(data);
			
			let headRow = document.createElement('tr');
			let head1 = document.createElement('th');
			let head2 = document.createElement('th');
			let head3 = document.createElement('th');
			let head4 = document.createElement('th');
			let head5 = document.createElement('th');
			let head6 = document.createElement('th');
			let head7 = document.createElement('th');
			let head8 = document.createElement('th');
			
			let h1t = document.createTextNode('ID');
			let h2t = document.createTextNode('EMPLOYEE ID');
			let h3t = document.createTextNode('FIRST NAME');
			let h4t = document.createTextNode('LAST NAME');
			let h5t = document.createTextNode('DESCRIPTION');
			let h6t = document.createTextNode('JUSTIFICATION');
			let h7t = document.createTextNode('URGENT');
			let h8t = document.createTextNode('APPROVE')
			
			head1.appendChild(h1t);
 			head2.appendChild(h2t);
 			head3.appendChild(h3t);
 			head4.appendChild(h4t);
 			head5.appendChild(h5t);
 			head6.appendChild(h6t);
 			head7.appendChild(h7t);
 			head8.appendChild(h8t);
			headRow.appendChild(head1);
			headRow.appendChild(head2);
			headRow.appendChild(head3);
			headRow.appendChild(head4);
			headRow.appendChild(head5);
			headRow.appendChild(head6);
			headRow.appendChild(head7);
			headRow.appendChild(head8);
			
			table.appendChild(headRow);
			div.appendChild(table);
			
			for(index in data){
				let rId = data[index]['id'];
				
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				td1.setAttribute("id", rId);			
				
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td4 = document.createElement('td');
				let td5 = document.createElement('td');
				let td6 = document.createElement('td');
				let td7 = document.createElement('td');
				
				let app = document.createTextNode("Approve");
				let td8 = document.createElement('td');				
				let b = document.createElement('button');
				b.setAttribute("onclick", "approveDeptReim(" + rId + ")");
				b.setAttribute("class", "btn btn-success");
				b.appendChild(app);	
				
				let img = checkMark(data[index]["urgent"]);
				
				let td1t = document.createTextNode(data[index]["id"]);
				let td2t = document.createTextNode(data[index]["empId"]);
				let td3t = document.createTextNode(data[index]["firstName"]);
				let td4t = document.createTextNode(data[index]["lastName"]);
				let td5t = document.createTextNode(data[index]["description"]);
				let td6t = document.createTextNode(data[index]["justification"]);
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				td5.appendChild(td5t);
				td6.appendChild(td6t);
				td7.appendChild(img);
				td8.appendChild(b);
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				row.appendChild(td6);
				row.appendChild(td7);
				row.appendChild(td8);
				table.appendChild(row);
				
				setTimeout(function(){approveDeptReim(rId)}, 10000);
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
			let div = document.getElementById('sub');
			let table = document.createElement('table');
			table.setAttribute("id", "subTable");
			table.setAttribute("class", "table");
			div.innerHTML = "";
			console.log(data);
			
			let headRow = document.createElement('tr');
			let head1 = document.createElement('th');
			let head2 = document.createElement('th');
			let head3 = document.createElement('th');
			let head4 = document.createElement('th');
			let head5 = document.createElement('th');
			let head6 = document.createElement('th');
			let head7 = document.createElement('th');
			let head8 = document.createElement('th');
			let head9 = document.createElement('th');
			let head10 = document.createElement('th');
			let head11 = document.createElement('th');
			
			let h1t = document.createTextNode('ID');
			let h2t = document.createTextNode('EMPLOYEE ID');
			let h3t = document.createTextNode('FIRST NAME');
			let h4t = document.createTextNode('LAST NAME');
			let h5t = document.createTextNode('DESCRIPTION');
			let h6t = document.createTextNode('JUSTIFICATION');
			let h7t = document.createTextNode('COST');
			let h8t = document.createTextNode('PROJECTED COVERAGE')
			let h9t = document.createTextNode('LOCATION');
			let h10t = document.createTextNode('URGENT');
			let h11t = document.createTextNode('APPROVE')
			
			head1.appendChild(h1t);
 			head2.appendChild(h2t);
 			head3.appendChild(h3t);
 			head4.appendChild(h4t);
 			head5.appendChild(h5t);
 			head6.appendChild(h6t);
 			head7.appendChild(h7t);
 			head8.appendChild(h8t);
 			head9.appendChild(h9t);
 			head10.appendChild(h10t);
 			head11.appendChild(h11t);
			headRow.appendChild(head1);
			headRow.appendChild(head2);
			headRow.appendChild(head3);
			headRow.appendChild(head4);
			headRow.appendChild(head5);
			headRow.appendChild(head6);
			headRow.appendChild(head7);
			headRow.appendChild(head8);
			headRow.appendChild(head9);
			headRow.appendChild(head10);
			headRow.appendChild(head11);
			
			table.appendChild(headRow);
			div.appendChild(table);
			
			for(index in data){
				let rId = data[index]['id'];
				
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				td1.setAttribute("id", rId);			
				
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td4 = document.createElement('td');
				let td5 = document.createElement('td');
				let td6 = document.createElement('td');
				let td7 = document.createElement('td');
				let td8 = document.createElement('td');
				let td9 = document.createElement('td');
				let td10 = document.createElement('td');
				
				
				let app = document.createTextNode("Approve");
				let td11 = document.createElement('td');				
				let b = document.createElement('button');
				b.setAttribute("onclick", "approveBencoReim(" + rId + ")");
				b.setAttribute("class", "btn btn-success");
				b.appendChild(app);		
				
				let img = checkMark(data[index]['urgent']);
				
				let td1t = document.createTextNode(data[index]["id"]);
				let td2t = document.createTextNode(data[index]["empId"]);
				let td3t = document.createTextNode(data[index]["firstName"]);
				let td4t = document.createTextNode(data[index]["lastName"]);
				let td5t = document.createTextNode(data[index]["description"]);
				let td6t = document.createTextNode(data[index]["justification"]);
				let td7t = document.createTextNode(data[index]["cost"]);
				let td8t = document.createTextNode(data[index]["coverage"]);
				let td9t = document.createTextNode(data[index]["location"]);
				
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				td5.appendChild(td5t);
				td6.appendChild(td6t);
				td7.appendChild(td7t);
				td8.appendChild(td8t);
				td9.appendChild(td9t);
				td10.appendChild(img);
				td11.appendChild(b)
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				row.appendChild(td6);	
				row.appendChild(td7);
				row.appendChild(td8);
				row.appendChild(td9);
				row.appendChild(td10);
				row.appendChild(td11);
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

function checkMark(x){
	let img = document.createElement('img');
	if(x){						
		img.setAttribute("src", "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/Green_tick.svg/768px-Green_tick.svg.png");
		img.setAttribute("width", 20);
		img.setAttribute("height", 20);
	}
	return img;
}