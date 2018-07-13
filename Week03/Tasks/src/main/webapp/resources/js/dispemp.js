window.onload = function(){
	getEmp();
}

function getEmp(){
	let xhr = new XMLHttpRequest();
	let table = document.getElementById("emptable");
	table.innerHTML = "";
	
	let url = "SelectEmployeeServlet";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			let data = JSON.parse(xhr.response);
			
			let r1 = document.createElement('tr');
			let td1 = document.createELement('td');
			let r1td1 = document.createTextNode('Employee ID: ')
			let r1td2 = document.createTextNode(data[index]["empid"]);
			
			let r2 = document.createElement('tr');
			let td2 = document.createELement('td');
			let r2td1 = document.createTextNode('Name: ')
			let r2td2 = document.createTextNode(data[index]["firstN"]);
			let r2td3 = document.createTextNode(data[index]["lastN"]);
			
			let r3 = document.createElement('tr');
			let td3 = document.createELement('td');
			let r3td1 = document.createTextNode('Direct Supevisor: ')
			let r3td2 = document.createTextNode(data[index]["dirSupId"]);
			let r3td3 = document.createTextNode(data[index]["lastN"]);
			
			let r4 = document.createElement('tr');
			let r5 = document.createElement('tr');
			let r6 = document.createElement('tr');
			let r7 = document.createElement('tr');
			let r8 = document.createElement('tr');
			
			for(index in data){
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td1t = document.createTextNode(data[index]["id"]);
				let td2t = document.createTextNode(data[index]["name"]);
				let td3t = document.createTextNode(data[index]["jobClassString"]);
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
}