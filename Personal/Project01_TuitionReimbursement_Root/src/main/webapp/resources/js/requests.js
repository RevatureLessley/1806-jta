function showReimbursements(str) {
	var http;
	if (str == ""){
		document.getElementById("reimbursementsHolder").innerHTML = "";
		return;
	}
	xhttp = new XHMLHttpRequest();
	xhttp.onreadystatechange = function() {
		if (this.readyState == 4 && this.status == 200){
			document.getElementById("reimbursementsHolder").innerHTML = this.responseText;
		}
	};
	xhttp.open("GET", "getReimbursement.asp?q="+str, true);
	xhttp.send();
	
}

function goToDisplayReimPage(reimId) {
	document.location = "/Project01_TuitionReimbursement/user/displayReimbursement.html?reimId="+
	reimId;
}

function getReimId(){
	var url_string = window.location.href;
	var url = new URL(url_string);
	return url.searchParams.get("reimId");
}

function displayReimbursement(){
	var reimId = getReimId();
	var httpReq = "";
	var url = "/Project01_TuitionReimbursement/DisplayReimbursement.do?reimId="+reimId;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.onreadystatechange = function(){
		if ((xhr.readyState == 4) || (xhr.status == 200)){
			document.getElementById("reimbursementHolder").innerHTML = this.responseText;
		}
	}
	xhr.send(httpReq);
}

function openReimbursement(reimId){
	var httpReq = "";
	var url = "/Project01_TuitionReimbursement/OpenReimbursement.do?reimId="+reimId;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.onreadystatechange = function(){
		if ((xhr.readyState == 4) || (xhr.status == 200)){
			document.location = xhr.responseURL;
		}
	}
	xhr.send(httpReq);
}

function approveReimbursement(){
	var httpReq = "";
	var reimId = document.getElementById("reimId").innerHTML;
	var url = "/Project01_TuitionReimbursement/ApproveReimbursement.do?reimId="+reimId;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.onreadystatechange = function(){
		if ((xhr.readyState == 4) || (xhr.status == 200)){
			document.getElementById("responseHolder").innerHTML = xhr.responseText;
		}
	}
	xhr.send(httpReq);
}

function getSupervisor(){
	var httpReq = "";
	var role = document.getElementById("roles").value;
	var url = "/Project01_TuitionReimbursement/GetHigherUps.do?role="+role;
	var xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.onreadystatechange = function(){
		if ((xhr.readyState == 4) || (xhr.status == 200)){
			document.getElementById("supervisors").innerHTML = xhr.responseText;
		}
	}
	xhr.send(httpReq);
}

function onLoad(){
	var httpReq = null;
	var url = "/Project01_TuitionReimbursement/GetReimbursements.do";

	var xhr = new XMLHttpRequest();
	xhr.open('GET', url);
	xhr.onreadystatechange = function(){
		if ((xhr.readyState == 4) || (xhr.status == 200)){
			document.getElementById("reimbursementsHolder").innerHTML = xhr.responseText;
		}
	}
	xhr.send(httpReq);
}

function inValidate(){
	delete_cookie("username");
	var httpReq = null;
	var url = "/Project01_TuitionReimbursement/KillSession.do";
	
	var xhr = new XMLHttpRequest();
	xhr.open('GET', url);
	xhr.onreadystatechange = function () {
		if ((xhr.readyState == 4) || (xhr.status == 200)) {
			console.log("session invalidated");
			window.location.href = "/Project01_TuitionReimbursement/index.html";
		} 
	}
	xhr.send(httpReq);
}