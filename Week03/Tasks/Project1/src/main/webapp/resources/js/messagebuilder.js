
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
	   
		messageArea.appendChild(tclone);
	}
	
	for(let i = 0; i < 12; i++){
		let br = document.createElement("br");
		document.querySelector(target).appendChild(br);
	}
}