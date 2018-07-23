function getRemainingBalance(){
	let xhr = new XMLHttpRequest();
	let url = "SelectEmployee";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState== 4){

			let data = JSON.parse(xhr.response);
			let count = 1;
			let amountRemaining = 0;
			for(index in data){
				if(count ==2){
					amountRemaining = data[index];
					console.log(amountRemaining);
				}
				else if(count == 3){
					console.log("got in count 3");
					for(index2 in data[index]){
						if(data[index][index2]['status'] != 0){
							amountRemaining = amountRemaining - (data[index][index2]["fullAmount"] * data[index][index2]["typeValue"])
						}
					}
				}
				else if(count == 4){
					console.log(data[index]["name"]);
					console.log(amountRemaining);
					let header = document.getElementById("reimburesment_amount");
					//header.innerHTML = "";
					let sentance = document.createTextNode("Reimbursement Remaining: ");
					let amount = document.createTextNode(amountRemaining);
					header.appendChild(sentance);
					header.appendChild(amount);
				}
				count++;
			}		
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}


function viewReimbersementTable(){
	let xhr = new XMLHttpRequest();
	let url = "../SelectEmployee";
	let table = document.getElementById("reimbursementTable");
	table.innerHTML = "";
	
	xhr.onreadystatechange = function(){
		if(xhr.readyState== 4){
			let data = JSON.parse(xhr.response);
			let count = 1
			
			let row = document.createElement('tr');
			let td1 = document.createElement('td');
			let td2 = document.createElement('td');
			let td3 = document.createElement('td');
			let td4 = document.createElement('td');
			let td5 = document.createElement('td');
			let td6 = document.createElement('td');
			
			let td1t = document.createTextNode("Reimbursement ID");
			let td2t = document.createTextNode("Full Amount");
			let td3t = document.createTextNode("Adjusted Amount");
			let td4t = document.createTextNode("CooperateAdjusted");
			let td5t = document.createTextNode("Status");
			let td6t = document.createTextNode("Event");
			
			td1.appendChild(td1t);
			td2.appendChild(td2t);
			td3.appendChild(td3t);
			td4.appendChild(td4t);
			td5.appendChild(td5t);
			td6.appendChild(td6t);
			
			row.appendChild(td1);
			row.appendChild(td2);
			row.appendChild(td3);
			row.appendChild(td4);
			row.appendChild(td5);
			row.appendChild(td6);
			
			table.appendChild(row);
			
			for(index in data){
				if(count == 3){
					for(index2 in data[index]){
						let cooperateAdjusted = "false";
						if(data[index][index2]["cooperateAmount"] != 0.0){
							cooperateAdjusted = "true";
						}
						let adjustedAmmount =data[index][index2]["fullAmount"] * data[index][index2]["typeValue"];
						let row = document.createElement('tr');
						let td1 = document.createElement('td');
						let td2 = document.createElement('td');
						let td3 = document.createElement('td');
						let td4 = document.createElement('td');
						let td5 = document.createElement('td');
						let td6 = document.createElement('td');
						
						let td1t = document.createTextNode(data[index][index2]["id"]);
						let td2t = document.createTextNode(data[index][index2]["fullAmount"]);
						let td3t = document.createTextNode(adjustedAmmount);
						let td4t = document.createTextNode(cooperateAdjusted);
						let td5t = document.createTextNode(data[index][index2]["status"]);
						let td6t = document.createTextNode(data[index][index2]["eventDescription"]);
						
						td1.appendChild(td1t);
						td2.appendChild(td2t);
						td3.appendChild(td3t);
						td4.appendChild(td4t);
						td5.appendChild(td5t);
						td6.appendChild(td6t);
						
						row.appendChild(td1);
						row.appendChild(td2);
						row.appendChild(td3);
						row.appendChild(td4);
						row.appendChild(td5);
						row.appendChild(td6);
						
						table.appendChild(row);
						}
				}
				count++;
			}		
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}

function viewManageTable(){
	let xhr = new XMLHttpRequest();
	let url = "../SelectEmployee";
	let table = document.getElementById("manageTable");
	table.innerHTML = "";
		
		
	xhr.onreadystatechange = function(){
		if(xhr.readyState== 4){
			let data = JSON.parse(xhr.response);
			let count = 1;

			let row = document.createElement('tr');
			let td1 = document.createElement('td');
			let td2 = document.createElement('td');
			let td3 = document.createElement('td');
			let td4 = document.createElement('td');
			let td5 = document.createElement('td');
			let td6 = document.createElement('td');
			let td7 = document.createElement('td');

			let td1t = document.createTextNode("Reimbursement ID");
			let td2t = document.createTextNode("Full Amount");
			let td3t = document.createTextNode("Adjusted Amount");
			let td4t = document.createTextNode("CooperateAdjusted");
			let td5t = document.createTextNode("Status");
			let td6t = document.createTextNode("Event");
			let td7t = document.createTextNode("");

			td1.appendChild(td1t);
			td2.appendChild(td2t);
			td3.appendChild(td3t);
			td4.appendChild(td4t);
			td5.appendChild(td5t);
			td6.appendChild(td6t);
			td7.appendChild(td7t);

			row.appendChild(td1);
			row.appendChild(td2);
			row.appendChild(td3);
			row.appendChild(td4);
			row.appendChild(td5);
			row.appendChild(td6);
			row.appendChild(td7);

			table.appendChild(row);

			for(index in data){
				if(count == 3){
					for(index2 in data[index]){
						if(data[index][index2]["status"] == 1){
							let cooperateAdjusted = "false";
							if(data[index][index2]["cooperateAmount"] != 0.0){
								cooperateAdjusted = "true";
							}
							
							let adjustedAmmount =data[index][index2]["fullAmount"] * data[index][index2]["typeValue"];

							let string = JSON.stringify(data[index][index2])
							
							let manBut = document.createElement('button');
							let man = document.createTextNode("Manage");
							manBut.setAttribute("onclick", "loadManager(" + string + ")");
							manBut.appendChild(man);

							let row = document.createElement('tr');
							let td1 = document.createElement('td');
							let td2 = document.createElement('td');
							let td3 = document.createElement('td');
							let td4 = document.createElement('td');
							let td5 = document.createElement('td');
							let td6 = document.createElement('td');
							let td7 = document.createElement('td');

							let td1t = document.createTextNode(data[index][index2]["id"]);
							let td2t = document.createTextNode(data[index][index2]["fullAmount"]);
							let td3t = document.createTextNode(adjustedAmmount);
							let td4t = document.createTextNode(cooperateAdjusted);
							let td5t = document.createTextNode(data[index][index2]["status"]);
							let td6t = document.createTextNode(data[index][index2]["eventDescription"]);

							td1.appendChild(td1t);
							td2.appendChild(td2t);
							td3.appendChild(td3t);
							td4.appendChild(td4t);
							td5.appendChild(td5t);
							td6.appendChild(td6t);
							td7.appendChild(manBut);

							row.appendChild(td1);
							row.appendChild(td2);
							row.appendChild(td3);
							row.appendChild(td4);
							row.appendChild(td5);
							row.appendChild(td6);
							row.appendChild(td7);

							table.appendChild(row);
						}
					}
				}		
				count++;
			}
		}
	}	

	xhr.open("GET", url);
	xhr.send();
}

function loadManager(x){
	console.log(x);

	let table = document.getElementById("activeTable");
	table.innerHTML = "";
	
	let cooperateAdjusted = "false";
	if(x["cooperateAmount"] != 0.0){
		cooperateAdjusted = x["cooperateAmount"];
	}
	
	let adjustedAmount =x["fullAmount"] * x["typeValue"];
	
	let string = JSON.stringify(x);
	
	let upBut = document.createElement('button');
	let up = document.createTextNode("Approve");
	upBut.setAttribute("onclick", "updateRequest(" + string + ")");
	upBut.appendChild(up);

	let row1 = document.createElement('tr');
	let row2= document.createElement('tr');
	let row3 = document.createElement('tr');
	let row4 = document.createElement('tr');
	let row5 = document.createElement('tr');
	let row6 = document.createElement('tr');
	let row7 = document.createElement('tr');
	let row8 = document.createElement('tr');
	let row9 = document.createElement('tr');
	let row10 = document.createElement('tr');
	let row11 = document.createElement('tr');
	
	
	let td11 = document.createElement('td');
	let td21 = document.createElement('td');
	let td31 = document.createElement('td');
	let td41 = document.createElement('td');
	let td51 = document.createElement('td');
	let td61 = document.createElement('td');
	let td71 = document.createElement('td');
	let td81 = document.createElement('td');
	let td91 = document.createElement('td');
	let td101 = document.createElement('td');
	let td111 = document.createElement('td');
	
	let td12 = document.createElement('td');
	let td22 = document.createElement('td');
	let td32 = document.createElement('td');
	let td42 = document.createElement('td');
	let td52 = document.createElement('td');
	let td62 = document.createElement('td');
	let td72 = document.createElement('td');
	let td82 = document.createElement('td');
	let td92 = document.createElement('td');
	let td102 = document.createElement('td');
	let td112 = document.createElement('td');
	
	
	let td11t= document.createTextNode('Reimburesment ID');
	let td21t= document.createTextNode('Full Amount');
	let td31t= document.createTextNode('Reimburesment Amount');
	let td41t= document.createTextNode('Cooperate Adjustement');
	let td51t= document.createTextNode('Current Status');
	let td61t= document.createTextNode('Request Created Date');
	let td71t= document.createTextNode('Event Date');
	let td81t= document.createTextNode('Event Location');
	let td91t= document.createTextNode('Justification');
	let td101t= document.createTextNode('Grading Format');
	let td111t= document.createTextNode('');
	
	
	let td12t= document.createTextNode(x["id"]);
	let td22t= document.createTextNode(x["fullAmount"]);
	let td32t= document.createTextNode(adjustedAmount);
	let td42t= document.createTextNode(cooperateAdjusted);
	let td52t= document.createTextNode(x["status"]);
	let td62t= document.createTextNode(x["creationDate"]);
	let td72t= document.createTextNode(x["eventDate"]);
	let td82t= document.createTextNode(x["eventLocation"]);
	let td92t= document.createTextNode(x["eventJustification"]);
	let td102t= document.createTextNode(x["gradingFormat"]);
	
	
	td11.appendChild(td11t);
	td21.appendChild(td21t);
	td31.appendChild(td31t);
	td41.appendChild(td41t);
	td51.appendChild(td51t);
	td61.appendChild(td61t);
	td71.appendChild(td71t);
	td81.appendChild(td81t);
	td91.appendChild(td91t);
	td101.appendChild(td101t);
	td111.appendChild(td111t);
	
	td12.appendChild(td12t);
	td22.appendChild(td22t);
	td32.appendChild(td32t);
	td42.appendChild(td42t);
	td52.appendChild(td52t);
	td62.appendChild(td62t);
	td72.appendChild(td72t);
	td82.appendChild(td82t);
	td92.appendChild(td92t);
	td102.appendChild(td102t);
	td112.appendChild(upBut);

	row1.appendChild(td11);
	row2.appendChild(td21);
	row3.appendChild(td31);
	row4.appendChild(td41);
	row5.appendChild(td51);
	row6.appendChild(td61);
	row7.appendChild(td71);
	row8.appendChild(td81);
	row9.appendChild(td91);
	row10.appendChild(td101);
	row11.appendChild(td111);
	
	row1.appendChild(td12);
	row2.appendChild(td22);
	row3.appendChild(td32);
	row4.appendChild(td42);
	row5.appendChild(td52);
	row6.appendChild(td62);
	row7.appendChild(td72);
	row8.appendChild(td82);
	row9.appendChild(td92);
	row10.appendChild(td102);
	row11.appendChild(td112);
	
	table.appendChild(row1);	
	table.appendChild(row2);	
	table.appendChild(row3);	
	table.appendChild(row4);	
	table.appendChild(row5);	
	table.appendChild(row6);	
	table.appendChild(row7);	
	table.appendChild(row8);	
	table.appendChild(row9);	
	table.appendChild(row10);
	table.appendChild(row11);
}

function updateRequest(x){
	x["status"] = 2;
	let xhr = new XMLHttpRequest();
	let url = "../UpdateRequest";
	let stuff = JSON.stringify(x);
	console.log(x);
	xhr.onreadystatechange = function(){
		if(xhr.readyState== 4){

			
		}
	}
	xhr.open("POST", url) //Or GET
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("x="+stuff);
}