
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
}