window.onload = function(){
	getEmp();
	getReimb();
}

function getReimb(){
	let xhr = new XMLHttpRequest();
	let list = document.getElementById("empreimb");
	list.innerHTML = "";
	
	let url = "GetReimbursements.do";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			let data = JSON.parse(xhr.response);
			console.log(data);
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}

function getEmp(){
	let xhr = new XMLHttpRequest();
	let list = document.getElementById("empinfo");
	list.innerHTML = "";
	
	let url = "SelectEmployee.do";
	
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
			
		}
	}
	xhr.open("GET", url);
	xhr.send();
}