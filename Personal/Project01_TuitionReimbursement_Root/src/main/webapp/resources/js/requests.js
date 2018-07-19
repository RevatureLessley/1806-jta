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
		} 
	}
	xhr.send(httpReq);
}