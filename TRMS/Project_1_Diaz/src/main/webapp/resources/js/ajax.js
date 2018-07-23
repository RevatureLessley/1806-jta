window.onload = function(){
	getClams();
}
window.onload = function(){
	getEmp();
}

function getClams(){
	let xhr = new XMLHttpRequest();
	let table = document.getElementById("table");
	table.innerHTML = "";
	let url = "SelectClams";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			let data = JSON.parse(xhr.response);
			console.log(data);
			
			for(index in data){
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td4 = document.createElement('td');
				let td5 = document.createElement('td');
				let td6 = document.createElement('td');
				let td7 = document.createElement('td');
				let td8 = document.createElement('td');
				let td9 = document.createElement('td');
				let td10 = document.createElement('td');
				let td11 = document.createElement('td');
				let td12 = document.createElement('td');
				let td13 = document.createElement('td');
				let td14 = document.createElement('td');
				let td1t = document.createTextNode(data[index]["rfId"]);
				let td2t = document.createTextNode(data[index]["empId"]);
				let td3t = document.createTextNode(data[index]["formStatus"]);
				let td4t = document.createTextNode(data[index]["formStatus2"]);
				let td5t = document.createTextNode(data[index]["formStatus3"]);
				let td6t = document.createTextNode(data[index]["eventType"]);
				let td7t = document.createTextNode(data[index]["eventLocation"]);
				let td8t = document.createTextNode(data[index]["eventDescribtion"]);
				let td9t = document.createTextNode(data[index]["eventCost"]);
				let td10t = document.createTextNode(data[index]["formsDate"]);
				let td11t = document.createTextNode(data[index]["startDate"]);
				let td12t = document.createTextNode(data[index]["gradeFormat"]);
				let td13t = document.createTextNode(data[index]["gradeCutOff"]);
				let td14t = document.createTextNode(data[index]["workTimeMissed"]);
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				td5.appendChild(td5t);
				td6.appendChild(td6t);
				td7.appendChild(td7t);
				td8.appendChild(td8t);
				td9.appendChild(td9t);
				td10.appendChild(td10t);
				td11.appendChild(td11t);
				td12.appendChild(td12t);
				td13.appendChild(td13t);
				td14.appendChild(td14t);
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
				row.appendChild(td12);
				row.appendChild(td13);
				row.appendChild(td14);
				table.appendChild(row);
			}
			
			
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}
function getEmp(){
	let xhr = new XMLHttpRequest();
	let table = document.getElementById("table2");
	table.innerHTML = "";
	let url = "SelectEmp";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			let data = JSON.parse(xhr.response);
			console.log(data);
			
			for(index in data){
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td4 = document.createElement('td');
				let td5 = document.createElement('td');
				let td6 = document.createElement('td');
				let td7 = document.createElement('td');
				let td8 = document.createElement('td');
				let td9 = document.createElement('td');
				let td10 = document.createElement('td');
				let td11 = document.createElement('td');
				let td12 = document.createElement('td');
				let td1t = document.createTextNode(data[index]["empId"]);
				let td2t = document.createTextNode(data[index]["fName"]);
				let td3t = document.createTextNode(data[index]["lName"]);
				let td4t = document.createTextNode(data[index]["userName"]);
				let td5t = document.createTextNode(data[index]["empPassword"]);
				let td6t = document.createTextNode(data[index]["empPhone"]);
				let td7t = document.createTextNode(data[index]["empEmail"]);
				let td8t = document.createTextNode(data[index]["empDept"]);
				let td9t = document.createTextNode(data[index]["role"]);
				let td10t = document.createTextNode(data[index]["role2"]);
				let td11t = document.createTextNode(data[index]["formId"]);
				let td12t = document.createTextNode(data[index]["availableReimburstment"]);
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				td5.appendChild(td5t);
				td6.appendChild(td6t);
				td7.appendChild(td7t);
				td8.appendChild(td8t);
				td9.appendChild(td9t);
				td10.appendChild(td10t);
				td11.appendChild(td11t);
				td12.appendChild(td12t);
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
				row.appendChild(td12);
				table.appendChild(row);
			}
			
			
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}
