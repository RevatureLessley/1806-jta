function fillEventData(data) {

	console.log(data);

	document.getElementById("eventName").innerHTML = data["name"];
	document.getElementById("eventId").innerHTML = data["id"];
	document.getElementById("eventStatus").innerHTML = data["status"];
	document.getElementById("employeeName").innerHTML = data["employeeName"];
	document.getElementById("eventType").innerHTML = data["typeName"];
	document.getElementById("gradeFormat").innerHTML = data["gradeScaleName"];
	document.getElementById("finalGrade").innerHTML = data["gradeScaleName"];
	document.getElementById("date").innerHTML = data["date"];
	document.getElementById("cost").innerHTML = data["cost"];
	document.getElementById("reimbursement").innerHTML = data["reimbursementAmount"];

	document.getElementById("location").innerHTML = data["event"]["location"];
	document.getElementById("description").innerHTML = data["event"]["description"];
	document.getElementById("justification").innerHTML = data["event"]["justification"];

}

function fillEventManageData(data, empData) {
	console.log(data["phase"]);
	if (data["phase"] == "Approval")
		fillApprovalPhaseData(data, empData);
	if (data["phase"] == "Confirmation") {
		fillConfirmationPhaseData(data, empData);
	}
}

function fillApprovalPhaseData(data, empData) {
	addFromTemplate("eventmanage", "#container");

	approveForm = document.getElementById("approveForm");
	approveForm.setAttribute("action", "eventApprove.do?eventId=" + data["id"]);

	approveForm = document.getElementById("commentForm");
	approveForm.setAttribute("action", "eventComment.do?eventId=" + data["id"])

	let date
	date = data["superApprove"];
	if (date != null) {
		document.getElementById("superApprove").innerHTML = date;
		if (empData["typeName"] == "Supervisor")
			document.getElementById("approveButton").disabled = true;
	}

	date = data["headApprove"];
	if (date != null) {
		document.getElementById("headApprove").innerHTML = date;
		if (empData["typeName"] == "Department Head")
			document.getElementById("approveButton").disabled = true;
	}

	date = data["bencoApprove"];
	if (date != null) {
		document.getElementById("bencoApprove").innerHTML = date;
	}

	if (empData["typeName"] == "Benefits Coordinator") {
		addFromTemplate("eventchangeaward", "#manageRow");
		approveForm = document.getElementById("changeAwardForm");
		approveForm.setAttribute("action", "changeAward.do?eventId="
				+ data["id"])
	}

}

function fillConfirmationPhaseData(data, empData) {

	if (data["requiresPresentation"] == 1
			&& empData["typeName"] == "Department Head") {
		// show to department head
		addFromTemplate("eventconfirm", "#container");
		
		document.getElementById("eventConfirm").setAttribute("action",
				"eventConfirm.do?eventId=" + data["id"])
	}
	if (data["requiresPresentation"] == 0
			&& empData["typeName"] == "Benefits Coordinator") {
		// show to benco
		addFromTemplate("eventconfirm", "#container");
		
		document.getElementById("eventConfirm").setAttribute("action",
				"eventConfirm.do?eventId=" + data["id"])

	}

}

function userfillConfirmationPhaseData(data) {
	if (data["status"] == "Pending") {

		addFromTemplate("eventgrade", "#container");
		document.getElementById("eventIdField").setAttribute("value",
				"" + data["id"]);
		fillGrades(data["event"]["gradeScale"]);

		if (data["requiresPresentation"] == 0) {
			// requires grade
			el = document.getElementById("presDiv");
			el.parentNode.removeChild(el);
		}
	}

}

function fillGrades(gradeScale) {

	let gradeSelect = document.getElementById("gradeSelect");

	let xhr = new XMLHttpRequest();
	let url = "/Project1/GradeValueServlet?gradeScale=" + gradeScale;

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			let data = JSON.parse(xhr.response);
			console.log(data);

			for (i in data) {
				let t = document.createTextNode(data[i]["name"]);
				let option = document.createElement("option");
				option.setAttribute("value", data[i]["id"]);

				option.appendChild(t);
				gradeSelect.appendChild(option);
			}
		}

	}

	xhr.open("GET", url);
	xhr.send();

}