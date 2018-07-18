
function createMessages(messages, target){
	
	var link = document.querySelector('link[rel="import"][href="../template/'+name+'.html"]');
	var template = link.import.querySelector("template");;
	var clone, a; 
	
	console.log(messages);
	tclone = document.importNode(template.content, true);
	
	for(m in messages){

	    document.querySelector(target).appendChild(tclone);
	}
	
}