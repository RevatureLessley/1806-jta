function fizzbuzz() {
	let min = document.getElementById("min").value;
	let max = document.getElementById("max").value;
	let p = document.createElement('p');
	
	for(let i = min; i <= max; i++) {
		if((i % 3 == 0) & (i % 5 == 0)) {
			console.log("FizzBuzz");
		}
		
		else if(i % 3 == 0) {
			console.log("Fizz");
		}
		
		else if(i % 5 == 0) {
			console.log("Buzz");
		}
		
		else {
			console.log(i);
		}
	}
}

function changeSection() {
	let e1 = document.getElementById("magicSection");
	let random = Math.floor((Math.random() * 4));
	
	switch(random) {
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

npcID = 0;

function addNPC() {
	let npcName = document.getElementById("NPCName").value;
	let npcClass = document.getElementById("NPCClass").value;
	let idText= document.createTextNode(npcId);
	let nameText= document.createTextNode(npcName);
	let classText= document.createTextNode(npcClass);
	let del = document.createTextNode("X");
	
	let td1 = document.createElement('td');
	let td2 = document.createElement('td');
	let td3 = document.createElement('td');
	let td4 = document.createElement('td');
	
	let delBut = docment.createElement('button');
	delBut.setAttribute("onclick", "removeRow(" + npcIDC + ")");
	delBut.setAttribute("style", "color:red");
	delBut.appendChild(del);
	td1.appendChild(idText);
	td2.appendChild(nameText);
	td3.appendChild(classText);
	td4.appendChild(delBut);
	
	let row = document.createElement("tr");
	row.appendChild(td1);
	row.appendChild(td2);
	row.appendChild(td3);
	row.appendChild(td4);
	row.setAttribute("id", npcId);
	
	document.getElementById("theTable").appendChild(row);
	document.getElementById("NPCName").value = "";
	document.getElementById("NPCClass").value = "";
}

function removeRow(x) {
	document.getElementById(x).remove();
	let row = document.getElementById("theTable").childNodes;
	
	for(thing in row) {
		console.log(thing);
	}
}

window.onload = function () {
	var d1 = document.getElementById("1");
	var d2 = document.getElementById("2");
	var d3 = document.getElementById("3");
	
	d1.addEventListener("click", d1click, true);
	d2.addEventListener("click", d2click, true);
	d3.addEventListener("click", d3click, true);
	
	function d1click() {
		window.alert("D1 Clicked");
	}
	
	function d2click(event) {
		event.stopPropogation();
		window.alert("D2 Clicked");
	}
	
	function d3click() {
		window.alert("D3 Clicked");
	}
}