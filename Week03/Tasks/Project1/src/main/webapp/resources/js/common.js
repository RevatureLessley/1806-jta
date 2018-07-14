
function addFromTemplate(name, target){
	var link = document.querySelector('link[rel="import"][href="../template/'+name+'.html"]');
	
	var template = link.import.querySelector("template");
	var clone = document.importNode(template.content, true);
    document.querySelector(target).appendChild(clone);
	
}

function goBack() {
	window.history.back();
}

function logout(){
	
	
}