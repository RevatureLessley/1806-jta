/*
	Javascript block comment
*/
//Javascript line comment
function writeToPage(){
	document.write("<h1>Document.write!</h1>");
	
}

function changeSection(){
	let el = document.getElementById("magicSection");
	let random = Math.floor((Math.random()*4));
	
	switch(random){
	case 0:
		el.setAttribute("style", "background-color:red");
		break;
	case 1:
		el.setAttribute("style", "background-color:blue");
		break;
	case 2:
		el.setAttribute("style", "background-color:green");
		break;
	case 3:
		el.setAttribute("style", "background-color:purple");
		break;
	}
}

npcId = 0;

function addNPC(){
	//Store input values first!
	let npcName = document.getElementById("NPCName").value;
	let npcClass = document.getElementById("NPCClass").value;
	//.value grabs any written text for an input element.
	
	//Create nodes for the TEXT to go in
	let idText = document.createTextNode(++npcId);
	let nameText = document.createTextNode(npcName);
	let classText = document.createTextNode(npcClass);
	let del = document.createTextNode("X");
	
	//Create the elements to store our information
	let td1 = document.createElement('td');	//<td></td>
	let td2 = document.createElement('td'); //<td></td>
	let td3 = document.createElement('td'); //<td></td>
	let td4 = document.createElement('td'); //<td></td>
	
	let delBut = document.createElement('button'); //<button></button>
	delBut.setAttribute("onclick", "removeRow(" + npcId + ")");
	//<button onclick="removeRow(npcid)"></button>
	delBut.setAttribute("style","color:red");
	//<button onclick="removeRow(npcid)" style="color:red"></button>
	delBut.appendChild(del);
	//<button onclick="removeRow(npcid)" style="color:red">
	//	X
	//</button>
	
	td1.appendChild(idText);
	//<td>npcID</td>
	td2.appendChild(nameText);
	//<td>nameText</td>
	td3.appendChild(classText);
	//<td>class Text</td>
	td4.appendChild(delBut);
	//<td>
	//	<button onclick="removeRow(npcid)" style="color:red">
	//		X
	//	</button>
	//</td>
	
	//finally, we build our row
	let row = document.createElement("tr");
	row.appendChild(td1);
	row.appendChild(td2);
	row.appendChild(td3);
	row.appendChild(td4);
	/*
	<tr>
		<td>npcID</td>
		<td>nameText</td>
		<td>class Text</td>
		<td>
			<button onclick="removeRow(npcid)" style="color:red">
				X
			</button>
		</td>
	</tr>
	 * 
	 */
	row.setAttribute("id", npcId);
	
	document.getElementById("theTable").appendChild(row);
	document.getElementById("NPCName").value = ""; //resets input field
	document.getElementById("NPCClass").value = "";//resets input field
	
}

function removeRow(x){
	document.getElementById(x).remove();
	let row = document.getElementById("theTable").childNodes;
	for(thing in row){
		console.log(row[thing]);
	}
}
//Any functions executed here will be gauranteed that all elements are fully loaded and
//ready for manipulation.
window.onload = function(){
	var d1 = document.getElementById("1");
	var d2 = document.getElementById("2");
	var d3 = document.getElementById("3");
	
	d1.addEventListener("click", d1click, true);
	d2.addEventListener("click", d2click, true);
	d3.addEventListener("click", d3click, true);
	//AddEventListener passes 3 arguments
	//the event, the callback function, useCapture <- This is set to false by default
	
	function d1click(){
		window.alert("D1 CLICKED");
		event.stopPropagation();
		//Use this line to prevent full capturing
		//or bubbling.
		
	}
	function d2click(){
		
		window.alert("D2 CLICKED");
	}
	function d3click(){
		window.alert("D3 CLICKED");
	}
	
};