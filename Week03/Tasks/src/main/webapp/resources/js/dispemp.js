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
			for(index in data){
				let applvl = data[index]["appLvl"];
				let gformat = "";
				if (applvl == 4) {
					if(data[index]["gradeFormat"] == 1){
						applvl = "Approved, pending grade";
					}else{
						applvl = "Approved, pending presentation";
					}
				}else if (applvl == 9){
					applvl = "Denied";
				}else if (applvl == 5){
					applvl = "Grade Submitted, pending approval";
				}else if(applvl == 6){
					applvl = "Reimbursed";
				}else{
					applvl = "Pending";
				}
				let urgent = "    ";
				let l1 = document.createElement('li');
				l1.setAttribute("id",data[index]["rFormId"]);
				l1.appendChild(document.createTextNode(data[index]["empName"]
				+ ": " + data[index]["eventName"]	+ ": "
				+ applvl + urgent));
				
				let b1 = document.createElement('button');
				b1.setAttribute("class","btn btn-primary");
				let jsonobj = JSON.stringify(data[index]);
				b1.setAttribute("onclick","rformDetails(" + jsonobj + ")");
				b1.appendChild(document.createTextNode("+"));
				l1.appendChild(b1);
				
				if(applvl == "Approved, pending grade"){
					let f1 = document.createElement('form');
					f1.setAttribute("id",data[index]["rFormId"] + "gradelist");
					f1.setAttribute("onsubmit","submitGrade(" + data[index]["rFormId"] + ")")
					let l2 = document.createElement('li');
					let i1 = document.createElement('input');
					i1.setAttribute("type","number");
					i1.setAttribute("min","0");
					i1.setAttribute("max","100");
					i1.setAttribute("id",data[index]["rFormId"] + "grade");
					i1.required = true;
					let b2 = document.createElement('button');
					b2.setAttribute("class","btn btn-primary");
					b2.setAttribute("onclick","submit");

					b2.appendChild(document.createTextNode("Submit Grade"));
					l2.appendChild(i1);
					l2.appendChild(b2);
					f1.appendChild(l2);
					list.appendChild(f1);
				}
				if(data[index]["filekey"] == null){
					let fu = document.createElement('form');
					fu.setAttribute("action","Upload.do");
					fu.setAttribute("method","post");
					fu.setAttribute("enctype","multipart/form-data");
					
					let rfid = document.createElement('input');
					rfid.setAttribute("name","rformidFUpload");
					rfid.setAttribute("id","rformidFUpload");
					rfid.style.visibility = 'hidden';
					rfid.value = data[index]["rFormId"];
					rfid.appendChild(document.createTextNode(data[index]["rFormId"]));
					fu.appendChild(rfid);
					
					let in1 = document.createElement('input');
					in1.setAttribute("type","file");
					in1.setAttribute("name","file");
					
					let in2 = document.createElement('input');
					in2.setAttribute("type","submit");
					
					fu.appendChild(in1);
					fu.appendChild(in2);
					
					list.appendChild(fu);
				}
				
				list.appendChild(l1);
				
			}
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}

function submitGrade(rformid){
	let grade = document.getElementById(rformid + "grade").value;
	$.ajax({
	    url: 'SubmitGrade.do',
	    data: {
	    	currFormId: rformid,
	    	currGrade: grade
	        
	    },
	    type: 'POST'});
	document.getElementById(rformid + "gradelist").remove();
}


function rformDetails(jsonobj){
	let l = document.getElementById(jsonobj["rFormId"]);
	l.innerHTML = "";
	let b1 = document.createElement('button');
	let jsonobj2 = JSON.stringify(jsonobj);
	b1.setAttribute("onclick","delRFormDetails(" + jsonobj2 + ")");
	b1.setAttribute("class","btn btn-primary");
	b1.appendChild(document.createTextNode("-"));
	l.appendChild(b1);
	
	let ul1 = document.createElement('ul');
	
	let l1 = document.createElement('li');
	l1.appendChild(document.createTextNode("Event: " + jsonobj["eventName"]));
	ul1.appendChild(l1);
	
	let applvl = jsonobj["appLvl"];
	if (applvl == 4) {
		if(jsonobj["gradeFormat"] == 1){
			applvl = "Approved, pending grade";
		}else{
			applvl = "Approved, pending presentation";
		}
	}else if (applvl == 9){
		applvl = "Denied";
	}else if (applvl == 5){
		applvl = "Grade Submitted, pending approval";
	}else if(applvl == 6){
		applvl = "Reimbursed";
	}else{
		applvl = "Pending";
	}
	if(jsonobj["filekey"] != null){
		let fu = document.createElement('form');
		fu.setAttribute("action","Download.do");
		fu.setAttribute("method","post");
		
		
		let rfid = document.createElement('input');
		rfid.setAttribute("name","rformidFUpload");
		rfid.setAttribute("id","rformidFUpload");
		rfid.style.visibility = 'hidden';
		rfid.value = jsonobj["filekey"];
		rfid.appendChild(document.createTextNode(jsonobj["filekey"]));
		fu.appendChild(rfid);
		
		let in2 = document.createElement('input');
		in2.setAttribute("type","submit");
		in2.setAttribute("value","Download");
		
		
		fu.appendChild(in2);
		
		ul1.appendChild(fu);
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
	
	let l13 = document.createElement('li');
	l13.appendChild(document.createTextNode("Actual Reimbursement Percentage: " + jsonobj["finalperc"]));
	ul1.appendChild(l13);
	
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
	
	
	if(jsonobj["isSup"] == 1){
		let b2 = document.createElement('button');
		b2.setAttribute("class","btn btn-primary");
		b2.setAttribute("onclick","approveRForm(" + jsonobj["rFormId"] 
						+"," + jsonobj["appLvl"] 
						+"," + jsonobj["empid"] 
						+"," + jsonobj["finalperc"] 
						+"," + jsonobj["eventCost"] + ")");
		b2.appendChild(document.createTextNode("Approve"));
		ul1.appendChild(b2);
		
		let b3 = document.createElement('button');
		b3.setAttribute("class","btn btn-primary");
		b3.setAttribute("onclick","denyRForm(" + jsonobj["rFormId"] 
						+"," + jsonobj["empid"] 
						+"," + jsonobj["finalperc"] 
						+"," + jsonobj["eventCost"] + ")");
		b3.appendChild(document.createTextNode("Deny"));
		ul1.appendChild(b3);
	}
	
	l.appendChild(ul1);
	
	
}

function delRFormDetails(jsonobj){
	let l = document.getElementById(jsonobj["rFormId"]);
	l.innerHTML = "";
	l.appendChild(document.createTextNode(jsonobj["empName"]
	+ ": " + jsonobj["eventName"]	+ "   "));
	let b1 = document.createElement('button');
	b1.setAttribute("class","btn btn-primary");
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
			let avail = data["available"];
			if (avail < 0){
				avail = 0;
			}
			list.innerHTML += "<li>Available Reimbursements Total: "
				+ avail + "</li>";
			list.innerHTML += "<li>Awarded Reimbursements Total: "
				+ data["awarded"] + "</li>";
			if(data["empType"] > 0 || data["depId"] == 1){
				dispSupRForms();
			}
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
					let applvl = data[index]["appLvl"];
					if (applvl == 4) {
						if(data[index]["gradeFormat"] == 1){
							applvl = "Approved, pending grade";
						}else{
							applvl = "Approved, pending presentation";
						}
					}else if (applvl == 9){
						applvl = "Denied";
					}else if (applvl == 5){
						applvl = "Grade Submitted, pending approval";
					}else if(applvl == 6){
						applvl = "Reimbursed";
					}
					else{
						applvl = "Pending";
					}

					header = document.getElementById("Pending Approvals");
					header.innerHTML = "Subordinate Reimbursements";
					let l1 = document.createElement('li');
					l1.setAttribute("id",data[index]["rFormId"]);
					l1.appendChild(document.createTextNode(data[index]["empName"]
							+ ": " + data[index]["eventName"]	+ ": "
							+ applvl + "    "));
					let b1 = document.createElement('button');
					b1.setAttribute("class","btn btn-primary");
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
function approveRForm(rformid,applvl,empid,finalperc,eventcost){
	$.ajax({
	    url: 'ApproveRForm.do',
	    data: {
	    	currFormId: rformid,
	        currapplvl: applvl,
	        currempid: empid,
	        currfinalperc: finalperc,
	        curreventcost: eventcost
	        
	    },
	    type: 'POST'});
	document.getElementById(rformid).remove();

}
function denyRForm(rformid,empid,finalperc,eventcost){
	$.ajax({
	    url: 'DenyRForm.do',
	    data: {
	    	currFormId: rformid,
	        currempid: empid,
	        currfinalperc: finalperc,
	        curreventcost: eventcost
	    },
	    type: 'POST'});
	document.getElementById(rformid).remove();

}
