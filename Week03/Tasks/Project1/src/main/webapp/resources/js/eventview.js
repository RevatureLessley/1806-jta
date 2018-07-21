function fillEventData(data) {

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

function fillEventManageData(data, empData) {

	if (data["phase"] == "Approval")
		fillApprovalPhaseData(data, empData);
	if (data["phase"] == "Confirmation") {
		fillConfirmationPhaseData(data, empData);
	}
}

function fillApprovalPhaseData(data) {
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

}

function fillConfirmationPhaseData(data, empData) {

	if (data["requiresPresentation"] == 1
			&& empData["typeName"] == "Department Head") {
		// show to department head
		addFromTemplate("eventconfirm", "#container");
		fillPresentationData(data);
	}
	if (data["requiresPresentation"] == 0
			&& empData["typeName"] == "Benefits Coordinator") {
		// show to benco
		addFromTemplate("eventconfirm", "#container");
		fillGradeData(data);
	}

}
