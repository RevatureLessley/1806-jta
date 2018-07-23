window.onload = function(){
	getSup();
}
function getSup(){
	let xhr = new XMLHttpRequest();
	let list = document.getElementById("dirsup");
	list.innerHTML = "";
	let depid = document.getElementById("dep").value;
	let url = "GetSupervisors.do";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			let data = JSON.parse(xhr.response);
			for(index in data){
				list.innerHTML += "<option value=" 
					+ data[index]["empid"] + ">"
					+ data[index]["firstN"] + " " + data[index]["lastN"]
					+ "</option>";
			}
		}
	}
	xhr.open("POST", url);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("currdepid=" + depid);
}