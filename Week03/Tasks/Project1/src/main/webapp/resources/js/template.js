function addFromTemplate(template, target){
	var link = document.querySelector('link[rel="import"]');
	
	var template = link.import.querySelector(template);
	var clone = document.importNode(template.content, true);
    document.querySelector(target).appendChild(clone);
	
}