
function createMessages(messages, target){
	
	console.log(messages);
	messageArea = document.createElement("div");
	messageArea.setAttribute("class","row justify-content-md-center");
	document.querySelector(target).appendChild(messageArea);
	
	var link = document.querySelector('link[rel="import"][href="../template/message.html"]');
	var template = link.import.querySelector("template");
	var clone, a; 
	
	for(m in messages){
		tclone = document.importNode(template.content, true);
		tclone.getElementById("messageHead").innerHTML = messages[m]["sourceName"]
		tclone.getElementById("message").innerHTML = messages[m]["notification"]["message"]
		tclone.getElementById("messageDate").innerHTML = messages[m]["dateString"]
		
		let messageBox = tclone.getElementById("messageBox");
		messageBox.setAttribute("id", `messageBox${messages[m]['notification']['id']}`);
		
		if(messages[m]["notification"]["read"]){
			messageBox.setAttribute("class", "alert alert-secondary");
		}else{
			messageBox.setAttribute("onmouseenter", `setMessageReadFromEvent("${messages[m]['notification']['id']}")`);
		}
		
		messageArea.appendChild(tclone);
	}
	
	for(let i = 0; i < 12; i++){
		let br = document.createElement("br");
		document.querySelector(target).appendChild(br);
	}
}


function setMessageReadFromEvent(id){
	messageBox = document.getElementById(`messageBox${id}`)
	messageBox.setAttribute("class", "alert alert-secondary");
	messageBox.setAttribute("onmouseenter", "");
	
	setMessageRead(id);	
}