/**
 * 
 */
var x = 0;


//Directors NOT CLIENTS
function getInfo(){
	let xhr = new XMLHttpRequest();
	let table = document.getElementById("infoTable");
	table.innerHTML = "";
	//Use the servlet url mapping for your url
	//when hitting it with AJAX.
	let url = "requestTable";
	

	
	let rowIndex = document.createElement('tr');
	let td1I     = document.createElement('td');
	let td2I     = document.createElement('td');
	let td3I     = document.createElement('td');
	let td4I     = document.createElement('td');
	let td5I     = document.createElement('td');
	let td6I     = document.createElement('td');
	let td7I     = document.createElement('td');
	let td8I     = document.createElement('td');
	let td9I     = document.createElement('td'); 
	
	let td1It = document.createTextNode("Username");
	let td2It = document.createTextNode("First Name");
	let td3It = document.createTextNode("Last Name");
	let td4It = document.createTextNode("Date Submitted");
	let td5It = document.createTextNode("Request Id");
	let td6It = document.createTextNode("Description");
	let td7It = document.createTextNode("Event");
	let td8It = document.createTextNode("Justification");
	let td9It = document.createTextNode("Cost");
	
	td1I.appendChild(td1It);
	td2I.appendChild(td2It);
	td3I.appendChild(td3It);
	td4I.appendChild(td4It);
	td5I.appendChild(td5It);
	td6I.appendChild(td6It);
	td7I.appendChild(td7It);
	td8I.appendChild(td8It);
	td9I.appendChild(td9It);
	
	rowIndex.appendChild(td1I);
	rowIndex.appendChild(td2I);
	rowIndex.appendChild(td3I);
	rowIndex.appendChild(td4I);
	rowIndex.appendChild(td5I);
	rowIndex.appendChild(td6I);
	rowIndex.appendChild(td7I);
	rowIndex.appendChild(td8I);
	rowIndex.appendChild(td9I);
	
	table.appendChild(rowIndex);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			//Converts a JSON string into a JSON object.
			let data = JSON.parse(xhr.response);
			
			//To convert to a JSON string, for java use, we use:
			//JSON.stringify()
			console.log(data);



			
			for(index in data){
				let row = document.createElement('tr');
				
				let td1  = document.createElement('td');
				let td2  = document.createElement('td');
				let td3  = document.createElement('td');
				let td4  = document.createElement('td');
				let td5  = document.createElement('td');
				let td6  = document.createElement('td');
				let td7  = document.createElement('td');
				let td8  = document.createElement('td');
				let td9  = document.createElement('td');
				let td10 = document.createElement('td');

				
				let td1t = document.createTextNode(data[index]["uname"]);
				let td2t = document.createTextNode(data[index]["fname"]);
				let td3t = document.createTextNode(data[index]["lname"]);
				let td4t = document.createTextNode(data[index]["date"]);
				let td5t = document.createTextNode(data[index]["reqId"]);
				let td6t = document.createTextNode(data[index]["descript"]);
				let td7t = document.createTextNode(data[index]["event"]);
				let td8t = document.createTextNode(data[index]["justify"]);
				let td9t = document.createTextNode(data[index]["cost"]);
				
				//Creating a delete button
				let del = document.createTextNode("Approve");
				
				
				let delBut = document.createElement('button'); //<button></button>
				
				//"approveRequest(" + data[index]["reqId"] + ")"
				delBut.setAttribute("onclick", "approveRequest(" + data[index]["reqId"] + ")");
				//<button onclick="removeRow(npcid)"></button>
				
				delBut.setAttribute("style","color:red");
				//<button onclick="removeRow(npcid)" style="color:red"></button>
				
				delBut.appendChild(del);
				//<button onclick="removeRow(npcid)" style="color:red">
				//	Delete
				//</button>
				
				
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				td5.appendChild(td5t);
				td6.appendChild(td6t);
				td7.appendChild(td7t);
				td8.appendChild(td8t);
				td9.appendChild(td9t);
				td10.appendChild(delBut);
				
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				row.appendChild(td6);
				row.appendChild(td7);
				row.appendChild(td8);
				row.appendChild(td9);
				row.appendChild(td10);
				
				table.appendChild(row);
			}
			
			
		}
	}	
	xhr.open("GET", url);
	xhr.send();
}

function getRequests(){
	let xhr = new XMLHttpRequest();
	let table = document.getElementById("requestTable");
	table.innerHTML = "";
	//Use the servlet url mapping for your url
	//when hitting it with AJAX.
	let url = "RequestTableUser";
	
	let rowIndex = document.createElement('tr');
	let td1I = document.createElement('td');
	let td2I = document.createElement('td');
	let td3I = document.createElement('td');
	let td4I = document.createElement('td');
	let td5I = document.createElement('td');
	let td6I = document.createElement('td');
	let td7I = document.createElement('td');
	let td8I = document.createElement('td');
	let td9I = document.createElement('td'); 
	let td10I = document.createElement('td');
	
	let td1It = document.createTextNode("Username");
	let td2It = document.createTextNode("First Name");
	let td3It = document.createTextNode("Last Name");
	let td4It = document.createTextNode("Date Submitted");
	let td5It = document.createTextNode("Request Id");
	let td6It = document.createTextNode("Description");
	let td7It = document.createTextNode("Event");
	let td8It = document.createTextNode("Justification");
	let td9It = document.createTextNode("Cost");
	let td10It = document.createTextNode("Approved")
	
	td1I.appendChild(td1It);
	td2I.appendChild(td2It);
	td3I.appendChild(td3It);
	td4I.appendChild(td4It);
	td5I.appendChild(td5It);
	td6I.appendChild(td6It);
	td7I.appendChild(td7It);
	td8I.appendChild(td8It);
	td9I.appendChild(td9It);
	td10I.appendChild(td10It);

	
	rowIndex.appendChild(td1I);
	rowIndex.appendChild(td2I);
	rowIndex.appendChild(td3I);
	rowIndex.appendChild(td4I);
	rowIndex.appendChild(td5I);
	rowIndex.appendChild(td6I);
	rowIndex.appendChild(td7I);
	rowIndex.appendChild(td8I);
	rowIndex.appendChild(td9I);
	rowIndex.appendChild(td10I);
	
	table.appendChild(rowIndex);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			//Converts a JSON string into a JSON object.
			let data = JSON.parse(xhr.response);
			
			//To convert to a JSON string, for java use, we use:
			//JSON.stringify()
			console.log(data);



			
			for(index in data){
				let row = document.createElement('tr');
				
				let td1  = document.createElement('td');
				let td2  = document.createElement('td');
				let td3  = document.createElement('td');
				let td4  = document.createElement('td');
				let td5  = document.createElement('td');
				let td6  = document.createElement('td');
				let td7  = document.createElement('td');
				let td8  = document.createElement('td');
				let td9  = document.createElement('td');
				let td10 = document.createElement('td');
				
				let td1t = document.createTextNode(data[index]["uname"]);
				let td2t = document.createTextNode(data[index]["fname"]);
				let td3t = document.createTextNode(data[index]["lname"]);
				let td4t = document.createTextNode(data[index]["date"]);
				let td5t = document.createTextNode(data[index]["reqId"]);
				let td6t = document.createTextNode(data[index]["descript"]);
				let td7t = document.createTextNode(data[index]["event"]);
				let td8t = document.createTextNode(data[index]["justify"]);
				let td9t = document.createTextNode(data[index]["cost"]);
				let td10t = document.createTextNode("Pending");
				
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				td5.appendChild(td5t);
				td6.appendChild(td6t);
				td7.appendChild(td7t);
				td8.appendChild(td8t);
				td9.appendChild(td9t);
				td10.appendChild(td10t);
				
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				row.appendChild(td6);
				row.appendChild(td7);
				row.appendChild(td8);
				row.appendChild(td9);
				row.appendChild(td10);
				
				table.appendChild(row);
			}
			
			
		}
	}	
	xhr.open("GET", url);
	xhr.send();
}

function approveRequest(id){
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
			//Converts a JSON string into a JSON object.
			let data = JSON.parse(xhttp.response);
			
			if(data){alert("Approved!");}
			else{alert("Not enought funding!");}
	    }
	    else{alert("Pending. . .");}
	  };
	  xhttp.open("POST", "DirectSupervisorApproval",false);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("reqId="+id);
}

function getInfoDS(){
	let xhr = new XMLHttpRequest();
	let table = document.getElementById("infoTable");
	table.innerHTML = "";
	//Use the servlet url mapping for your url
	//when hitting it with AJAX.
	let url = "requestTableDS";
	

	
	let rowIndex = document.createElement('tr');
	let td1I     = document.createElement('td');
	let td2I     = document.createElement('td');
	let td3I     = document.createElement('td');
	let td4I     = document.createElement('td');
	let td5I     = document.createElement('td');
	let td6I     = document.createElement('td');
	let td7I     = document.createElement('td');
	let td8I     = document.createElement('td');
	let td9I     = document.createElement('td'); 
	
	let td1It = document.createTextNode("Username");
	let td2It = document.createTextNode("First Name");
	let td3It = document.createTextNode("Last Name");
	let td4It = document.createTextNode("Date Submitted");
	let td5It = document.createTextNode("Request Id");
	let td6It = document.createTextNode("Description");
	let td7It = document.createTextNode("Event");
	let td8It = document.createTextNode("Justification");
	let td9It = document.createTextNode("Cost");
	
	td1I.appendChild(td1It);
	td2I.appendChild(td2It);
	td3I.appendChild(td3It);
	td4I.appendChild(td4It);
	td5I.appendChild(td5It);
	td6I.appendChild(td6It);
	td7I.appendChild(td7It);
	td8I.appendChild(td8It);
	td9I.appendChild(td9It);
	
	rowIndex.appendChild(td1I);
	rowIndex.appendChild(td2I);
	rowIndex.appendChild(td3I);
	rowIndex.appendChild(td4I);
	rowIndex.appendChild(td5I);
	rowIndex.appendChild(td6I);
	rowIndex.appendChild(td7I);
	rowIndex.appendChild(td8I);
	rowIndex.appendChild(td9I);
	
	table.appendChild(rowIndex);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			//Converts a JSON string into a JSON object.
			let data = JSON.parse(xhr.response);
			
			//To convert to a JSON string, for java use, we use:
			//JSON.stringify()
			console.log(data);



			
			for(index in data){
				let row = document.createElement('tr');
				
				let td1  = document.createElement('td');
				let td2  = document.createElement('td');
				let td3  = document.createElement('td');
				let td4  = document.createElement('td');
				let td5  = document.createElement('td');
				let td6  = document.createElement('td');
				let td7  = document.createElement('td');
				let td8  = document.createElement('td');
				let td9  = document.createElement('td');
				let td10 = document.createElement('td');

				
				let td1t = document.createTextNode(data[index]["uname"]);
				let td2t = document.createTextNode(data[index]["fname"]);
				let td3t = document.createTextNode(data[index]["lname"]);
				let td4t = document.createTextNode(data[index]["date"]);
				let td5t = document.createTextNode(data[index]["reqId"]);
				let td6t = document.createTextNode(data[index]["descript"]);
				let td7t = document.createTextNode(data[index]["event"]);
				let td8t = document.createTextNode(data[index]["justify"]);
				let td9t = document.createTextNode(data[index]["cost"]);
				
				//Creating a delete button
				let del = document.createTextNode("Approve");
				
				
				let delBut = document.createElement('button'); //<button></button>
				
				//"approveRequest(" + data[index]["reqId"] + ")"
				delBut.setAttribute("onclick", "approveRequestDS(" + data[index]["reqId"] + ")");
				//<button onclick="removeRow(npcid)"></button>
				
				delBut.setAttribute("style","color:red");
				//<button onclick="removeRow(npcid)" style="color:red"></button>
				
				delBut.appendChild(del);
				//<button onclick="removeRow(npcid)" style="color:red">
				//	Delete
				//</button>
				
				
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				td5.appendChild(td5t);
				td6.appendChild(td6t);
				td7.appendChild(td7t);
				td8.appendChild(td8t);
				td9.appendChild(td9t);
				td10.appendChild(delBut);
				
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				row.appendChild(td6);
				row.appendChild(td7);
				row.appendChild(td8);
				row.appendChild(td9);
				row.appendChild(td10);
				
				table.appendChild(row);
			}
			
			
		}
	}	
	xhr.open("GET", url);
	xhr.send();
}

function approveRequestDS(id){
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      alert("Approved!");
	    }
	    else{alert("Pending. . .");}
	  };
	  xhttp.open("POST", "DepartmentHeadApproval",false);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("reqId="+id);
}

function getInfoBC(){
	let xhr = new XMLHttpRequest();
	let table = document.getElementById("infoTable");
	table.innerHTML = "";
	//Use the servlet url mapping for your url
	//when hitting it with AJAX.
	let url = "requestTableBC";
	

	
	let rowIndex = document.createElement('tr');
	let td1I     = document.createElement('td');
	let td2I     = document.createElement('td');
	let td3I     = document.createElement('td');
	let td4I     = document.createElement('td');
	let td5I     = document.createElement('td');
	let td6I     = document.createElement('td');
	let td7I     = document.createElement('td');
	let td8I     = document.createElement('td');
	let td9I     = document.createElement('td'); 
	
	let td1It = document.createTextNode("Username");
	let td2It = document.createTextNode("First Name");
	let td3It = document.createTextNode("Last Name");
	let td4It = document.createTextNode("Date Submitted");
	let td5It = document.createTextNode("Request Id");
	let td6It = document.createTextNode("Description");
	let td7It = document.createTextNode("Event");
	let td8It = document.createTextNode("Justification");
	let td9It = document.createTextNode("Cost");
	
	td1I.appendChild(td1It);
	td2I.appendChild(td2It);
	td3I.appendChild(td3It);
	td4I.appendChild(td4It);
	td5I.appendChild(td5It);
	td6I.appendChild(td6It);
	td7I.appendChild(td7It);
	td8I.appendChild(td8It);
	td9I.appendChild(td9It);
	
	rowIndex.appendChild(td1I);
	rowIndex.appendChild(td2I);
	rowIndex.appendChild(td3I);
	rowIndex.appendChild(td4I);
	rowIndex.appendChild(td5I);
	rowIndex.appendChild(td6I);
	rowIndex.appendChild(td7I);
	rowIndex.appendChild(td8I);
	rowIndex.appendChild(td9I);
	
	table.appendChild(rowIndex);
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			//Converts a JSON string into a JSON object.
			let data = JSON.parse(xhr.response);
			
			//To convert to a JSON string, for java use, we use:
			//JSON.stringify()
			console.log(data);



			
			for(index in data){
				let row = document.createElement('tr');
				
				let td1  = document.createElement('td');
				let td2  = document.createElement('td');
				let td3  = document.createElement('td');
				let td4  = document.createElement('td');
				let td5  = document.createElement('td');
				let td6  = document.createElement('td');
				let td7  = document.createElement('td');
				let td8  = document.createElement('td');
				let td9  = document.createElement('td');
				let td10 = document.createElement('td');

				
				let td1t = document.createTextNode(data[index]["uname"]);
				let td2t = document.createTextNode(data[index]["fname"]);
				let td3t = document.createTextNode(data[index]["lname"]);
				let td4t = document.createTextNode(data[index]["date"]);
				let td5t = document.createTextNode(data[index]["reqId"]);
				let td6t = document.createTextNode(data[index]["descript"]);
				let td7t = document.createTextNode(data[index]["event"]);
				let td8t = document.createTextNode(data[index]["justify"]);
				let td9t = document.createTextNode(data[index]["cost"]);
				
				//Creating a delete button
				let del = document.createTextNode("Approve");
				
				
				let delBut = document.createElement('button'); //<button></button>
				
				//"approveRequest(" + data[index]["reqId"] + ")"
				delBut.setAttribute("onclick", "approveRequestBC(" + data[index]["reqId"] + ")");
				//<button onclick="removeRow(npcid)"></button>
				
				delBut.setAttribute("style","color:red");
				//<button onclick="removeRow(npcid)" style="color:red"></button>
				
				delBut.appendChild(del);
				//<button onclick="removeRow(npcid)" style="color:red">
				//	Delete
				//</button>
				
				
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				td5.appendChild(td5t);
				td6.appendChild(td6t);
				td7.appendChild(td7t);
				td8.appendChild(td8t);
				td9.appendChild(td9t);
				td10.appendChild(delBut);
				
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				row.appendChild(td5);
				row.appendChild(td6);
				row.appendChild(td7);
				row.appendChild(td8);
				row.appendChild(td9);
				row.appendChild(td10);
				
				table.appendChild(row);
			}
			
			
		}
	}	
	xhr.open("GET", url);
	xhr.send();
}

function approveRequestBC(id){
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	      alert("Approved!");
	    }
	    else{alert("Pending. . .");}
	  };
	  xhttp.open("POST", "BCApproval",false);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("reqId="+id);
}