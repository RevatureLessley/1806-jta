
function fillEventData(data){
	
	console.log(data);
	
	document.getElementById("eventName").innerHTML = data["name"];
	document.getElementById("eventId").innerHTML = data["id"];
	document.getElementById("employeeName").innerHTML = data["employeeName"];
	document.getElementById("eventType").innerHTML = data["typeName"];
	document.getElementById("gradeFormat").innerHTML = data["gradeScaleName"];
	document.getElementById("date").innerHTML = data["date"];
	document.getElementById("reimbursement").innerHTML = data["expectedAmount"];
	
	document.getElementById("location").innerHTML = data["event"]["location"];
	document.getElementById("description").innerHTML = data["event"]["description"];
	document.getElementById("justification").innerHTML = data["event"]["justification"];
		
}