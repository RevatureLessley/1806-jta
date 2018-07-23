function fillEventData(data) {

	document.getElementById("eventName").innerHTML = data["name"];
	document.getElementById("eventId").innerHTML = data["id"];
	document.getElementById("eventStatus").innerHTML = data["status"];
	document.getElementById("employeeName").innerHTML = data["employeeName"];
	document.getElementById("eventType").innerHTML = data["typeName"];
	document.getElementById("gradeFormat").innerHTML = data["gradeScaleName"];
	document.getElementById("finalGrade").innerHTML = data["finalGrade"];
	document.getElementById("date").innerHTML = data["date"];
	document.getElementById("cost").innerHTML = data["cost"];
	document.getElementById("reimbursement").innerHTML = data["reimbursementAmount"];

	document.getElementById("location").innerHTML = data["event"]["location"];
	document.getElementById("description").innerHTML = data["event"]["description"];
	document.getElementById("justification").innerHTML = data["event"]["justification"];

	fetchDocuments(data["id"])
}

function fillEventManageData(data, empData) {

	if (data["phase"] == "Approval")
		fillApprovalPhaseData(data, empData);
	if (data["status"] == "UnConfirmed") {
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
		prepareEventAwardChange(data)
	}

}

function prepareEventAwardChange(data) {

	let xhr = new XMLHttpRequest();
	let url = `/Project1/FetchEmployeeServlet?empId=${data["event"]["empId"]}`

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			let empData = JSON.parse(xhr.response);

			addFromTemplate("eventchangeaward", "#manageRow");
			approveForm = document.getElementById("changeAwardForm");
			approveForm.setAttribute("action",
					`changeAward.do?eventId=${data["id"]}`);

			document.getElementById("amountPossible").innerHTML = empData["reimbursementAvailable"];
			document.getElementById("inputAward").setAttribute("max",
					empData["employee"]["reimbursementAvailable"]);

			console.log(data["event"]["reimbursementAmount"]);
			console.log(parseFloat(empData["employee"]["reimbursementAvailable"]));

			if (data["event"]["reimbursementAmount"] > empData["employee"]["reimbursementAvailable"]) {

				let approveButton = document.getElementById("approveButton");
				approveButton.setAttribute("disabled", "disabled");
				approveButton.innerHTML = "Invalid Award";
			}
		}

	}

	xhr.open("GET", url);
	xhr.send();

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

function fetchDocuments(eventId) {
	let gradeSelect = document.getElementById("gradeSelect");

	let xhr = new XMLHttpRequest();
	let url = `/Project1/FetchEventDocServlet?eventId=${eventId}`;

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			let data = JSON.parse(xhr.response);

			fillDocuments(data);
		}
	}

	xhr.open("GET", url);
	xhr.send();
}

function fillDocuments(data) {
	let documentsEl = document.getElementById("normalDocs");
	let approveEl = document.getElementById("approvalDoc");

	if (data.length > 0)
		documentsEl.innerHTML = "";

	for (d in data) {

		let t = document.createTextNode(data[d]["name"]);
		let a = document.createElement("a");
		a.setAttribute("href",
				`/Project1/DownloadEventDocServlet?evdocId=${data[d]["id"]}`)
		a.setAttribute("download", data[d]["name"]);

		let dl = document.createElement("i");
		dl.setAttribute("class", "material-icons mat_middle");
		a.appendChild(dl);
		dl.innerHTML = "file_download";

		if (data[d]["docType"] == "preapproval") {
			approveEl.appendChild(t);
			approveEl.appendChild(a);
		} else {
			let el = document.createElement("li")
			el.appendChild(t);
			el.appendChild(a);
			documentsEl.appendChild(el);
		}
	}
}