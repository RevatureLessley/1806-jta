window.onload = function(){
	getEmp();
	getReimb();
	dispSupRForms();
}

function getReimb(){
	let xhr = new XMLHttpRequest();
	let list = document.getElementById("empreimb");
	list.innerHTML = "";
	
	let url = "GetReimbursements.do";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			let data = JSON.parse(xhr.response);
			for(index in data){
				let l1 = document.createElement('li');
				l1.setAttribute("id",data[index]["rFormId"]);
				l1.appendChild(document.createTextNode(data[index]["empName"]
						+ ": " + data[index]["eventName"]	+ "   "));
				let b1 = document.createElement('button');
				let jsonobj = JSON.stringify(data[index]);
				b1.setAttribute("onclick","rformDetails(" + jsonobj + ")");
				b1.appendChild(document.createTextNode("+"));
				l1.appendChild(b1);
				list.appendChild(l1);
				
			}
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}

function rformDetails(jsonobj){
	let l = document.getElementById(jsonobj["rFormId"]);
	l.innerHTML = "";
	let b1 = document.createElement('button');
	let jsonobj2 = JSON.stringify(jsonobj);
	b1.setAttribute("onclick","delRFormDetails(" + jsonobj2 + ")");
	b1.appendChild(document.createTextNode("-"));
	l.appendChild(b1);
	
	let ul1 = document.createElement('ul');
	
	let l1 = document.createElement('li');
	l1.appendChild(document.createTextNode("Event: " + jsonobj["eventName"]));
	ul1.appendChild(l1);
	
	let applvl = jsonobj["appLvl"];
	if (applvl == 3) {
		applvl = "Approved";
	}else if (applvl == 9){
		applvl = "Denied";
	}else{
		applvl = "Pending";
	}
	let l2 = document.createElement('li');
	l2.appendChild(document.createTextNode(applvl));
	ul1.appendChild(l2);
	
	let l3 = document.createElement('li');
	l3.appendChild(document.createTextNode("Event Start Date: " + jsonobj["rFormDate"]));
	ul1.appendChild(l3);
	
	let l4 = document.createElement('li');
	l4.appendChild(document.createTextNode("Event Type: " + jsonobj["eventTypeName"]));
	ul1.appendChild(l4);
	
	let l5 = document.createElement('li');
	l5.appendChild(document.createTextNode("Event Cost: " + jsonobj["eventCost"]));
	ul1.appendChild(l5);
	
	let l6 = document.createElement('li');
	l6.appendChild(document.createTextNode("Proposed Reimbursement Percentage: " + jsonobj["propReim"]));
	ul1.appendChild(l6);
	
	let l7 = document.createElement('li');
	l7.appendChild(document.createTextNode("Work Hours Missed: " + jsonobj["timeMissed"]));
	ul1.appendChild(l7);
	
	let gf = "Presentation";
	if(jsonobj["gradeFormat"] ==  1){
		gf = "Percentage";
	}
	let l8 = document.createElement('li');
	l8.appendChild(document.createTextNode("gradeFormat: " + gf));
	ul1.appendChild(l8);

	if(jsonobj["gradeFormat"] == 1){
		let l9 = document.createElement('li');
		l9.appendChild(document.createTextNode("Cutoff Grade: " + jsonobj["timeMissed"]));
		ul1.appendChild(l9);
	}
	
	let l10 = document.createElement('li');
	l10.appendChild(document.createTextNode("Event Location: " + jsonobj["place"]));
	ul1.appendChild(l10);
	
	let l11 = document.createElement('li');
	l11.appendChild(document.createTextNode("Event Info: " + jsonobj["info"]));
	ul1.appendChild(l11);
	
	let l12 = document.createElement('li');
	l12.appendChild(document.createTextNode("Justification: " + jsonobj["justification"]));
	ul1.appendChild(l12);
	
	l.appendChild(ul1);
	
	
}
function delRFormDetails(jsonobj){
	let l = document.getElementById(jsonobj["rFormId"]);
	l.innerHTML = "";
	l.appendChild(document.createTextNode(jsonobj["empName"]
	+ ": " + jsonobj["eventName"]	+ "   "));
	let b1 = document.createElement('button');
	let jsonobj2 = JSON.stringify(jsonobj);
	b1.setAttribute("onclick","rformDetails(" + jsonobj2 + ")");
	b1.appendChild(document.createTextNode("+"));
	l.appendChild(b1);
}

function getEmp(){
	let xhr = new XMLHttpRequest();
	let list = document.getElementById("empinfo");
	list.innerHTML = "";
	
	let url = "SelectEmployee.do";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			let data = JSON.parse(xhr.response);
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
		}
	}
	xhr.open("GET", url);
	xhr.send();
}
function dispSupRForms(){
	let xhr = new XMLHttpRequest();
	let list = document.getElementById("pendingapps");
	list.innerHTML = "";
	
	let url = "GetSupReimbersements.do";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			let data = JSON.parse(xhr.response);
			for(index in data){
				if(data[index]["isSup"] == 1){
					header = document.getElementById("Pending Approvals");
					header.innerHTML = "Subordinate Reimbursements";
					let l1 = document.createElement('li');
					l1.setAttribute("id",data[index]["rFormId"]);
					l1.appendChild(document.createTextNode(data[index]["empName"]
							+ ": " + data[index]["eventName"]	+ "   "));
					let b1 = document.createElement('button');
					let jsonobj = JSON.stringify(data[index]);
					b1.setAttribute("onclick","rformDetails(" + jsonobj + ")");
					b1.appendChild(document.createTextNode("+"));
					l1.appendChild(b1);
					list.appendChild(l1);
				}
			}
		}
	}
	xhr.open("GET", url);
	xhr.send();
}