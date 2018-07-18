
function createMessages(messages, target){
	
	console.log(messages);
	
	var link = document.querySelector('link[rel="import"][href="../template/message.html"]');
	var template = link.import.querySelector("template");
	var clone, a; 
	
	for(m in messages){
		tclone = document.importNode(template.content, true);
		tclone.getElementById("messageHead").innerHTML = messages[m]["sourceName"]
		tclone.getElementById("message").innerHTML = messages[m]["notification"]["message"]
		tclone.getElementById("messageDate").innerHTML = messages[m]["dateString"]
	    document.querySelector(target).appendChild(tclone);
	}
	
}