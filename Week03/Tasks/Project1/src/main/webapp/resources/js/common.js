
function addFromTemplate(name, target){
	var link = document.querySelector('link[rel="import"][href="../template/'+name+'.html"]');
	
	var template = link.import.querySelector("template");
	var clone = document.importNode(template.content, true);
    document.querySelector(target).appendChild(clone);
	
}

function goBack() {
	window.history.back();
}

function viewEvent(eventId){
	window.location.href = `../manage/eventview.jsp?eventId=${eventId}`;
}

function userViewEvent(eventId){
	window.location.href = `../user/eventview.jsp?eventId=${eventId}`;
}

function createNavbar(){
	addFromTemplate("navbar", "#navDiv");
	
	let xhr = new XMLHttpRequest();
	let url = `/Project1/user/UserNotificationsServlet`;
	
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			let data = JSON.parse(xhr.response);
			
			console.log(data);
			createNavbarMessages(data);
		}

	}
	
	xhr.open("GET", url);
	xhr.send();
}

function setMessageRead(id){
	let xhr = new XMLHttpRequest();
	let url = `/Project1/user/NotificationReadServlet?ntId=${id}`;
	xhr.open("GET", url);
	xhr.send();
}

function createNavbarMessages(messages){
	
	console.log(messages);
	messageArea = document.getElementById("navMessageDiv");
	table = document.createElement("table");
	table.setAttribute("class","table table-hover borderless");
	tbody= document.createElement("tbody");
	table.appendChild(tbody);
	
	for(m in messages){
		let messageRow = document.createElement("tr");
		
		t1 = document.createTextNode(messages[m]["sourceName"]);
		t2 = document.createTextNode(messages[m]["dateString"]);
		t3 = document.createTextNode(messages[m]["notification"]["message"]);
		
		td1 = document.createElement("td");
		td2 = document.createElement("td");
		td3 = document.createElement("td");
		
		td1.setAttribute("class", "light-label");
		td2.setAttribute("class", "light-label");
		
		td1.appendChild(t1);
		td2.appendChild(t2);
		td3.appendChild(t3);
		
		messageRow.appendChild(td2);
		messageRow.appendChild(td1);
		messageRow.appendChild(td3);
		
		messageRow.setAttribute("id", `messageRow${messages[m]['notification']['id']}`);
		
		if(messages[m]["notification"]["read"]){
			messageRow.setAttribute("class", "");
		}else{
			messageRow.setAttribute("class", "table-primary");
			messageRow.setAttribute("onmouseenter", `setMessageRead("${messages[m]['notification']['id']}")`);
		}
		
		tbody.appendChild(messageRow);
	}
	
	messageArea.appendChild(table);
}

function setMessageReadFromNavbar(id){
	messageBox = document.getElementById(`messageRow${id}`)
	messageBox.setAttribute("class", "");
	messageBox.setAttribute("onmouseenter", "");
	
	setMessageRead(id);	
	
}