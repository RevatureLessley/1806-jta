window.onload = function(){
	selectEmployee();
	selectReimbursement();
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
			let wel = document.getElementById('welcome');
			console.log(data);	
			
			for(index in data){
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td4 = document.createElement('td');
				let td1t = document.createTextNode(data[index]["description"])
				let td2t = document.createTextNode(data[index]["dsApproval"])
				let td3t = document.createTextNode(data[index]["dhApproval"])
				let td4t = document.createTextNode(data[index]["bencoApproval"])
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				table.appendChild(row);
			}			
		}
	}
	xhr.open("GET", url);
	xhr.send();
}