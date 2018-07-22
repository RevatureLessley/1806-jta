
function setAmountRemaining(data){
	document.getElementById("amountRemaining").innerHTML = data["reimbursementAvailable"];	
	document.getElementById("amountAwarded").innerHTML = data["balance"];
}

function createEmployeeSummary(data){
	document.getElementById("empName").innerHTML = data["name"]
	document.getElementById("empRole").innerHTML = data["typeName"]
	document.getElementById("empEmail").innerHTML = data["email"]
	document.getElementById("empDept").innerHTML = data["departmentName"]
	document.getElementById("empSuper").innerHTML = data["supervisorName"]
}



