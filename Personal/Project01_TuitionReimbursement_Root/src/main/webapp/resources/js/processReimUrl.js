function setForm(){
	var url_string = window.location.href;
	var url = new URL(url_string);
	var type = url.searchParams.get("type");
	var format = url.searchParams.get("format");
	var date = url.searchParams.get("date");
	var location = url.searchParams.get("location");
	var desc = url.searchParams.get("desc");
	var just = url.searchParams.get("just");
	var cost = url.searchParams.get("cost");
	var id = url.searchParams.get("id");
	
	document.getElementById("eventTypes").value = type;
	document.getElementById("formats").value = format;
	document.getElementById("eventDate").value = date;
	document.getElementById("locations").value = location;
	document.getElementById("eventDescription").value = desc;
	document.getElementById("eventJustification").value = just;
	document.getElementById("eventCost").value = cost;
	document.getElementById("id").value = id;
}
