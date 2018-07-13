

function start(){
	//alert("wassap");
	var min = Number(document.getElementById('minInput').value);
	var max = Number(document.getElementById('maxInput').value);
	
	console.log("min = " + min);
	console.log("max = " + max);
	
	if(min > max){ console.log("Min is greater than max"); return; }
	if(min == undefined | max == undefined | min == NaN | max == NaN){
		//alert("No minimum or maximum entered or are not numbers");
		return;
	}
	var fizzyResults = [];
	var rows = [];
	
	for(index = min; index <= max; index++){ fizzyResults.push(calculate(index)); console.log(fizzyResults); }
	for(result in fizzyResults){ rows.push(row(rows.length + 1, fizzyResults[result]));  }
	for(row in rows){ console.log(row); document.getElementById("theTable").appendChild(rows[row]); }
	
	document.getElementById("minInput").value = "";
	document.getElementById("maxInput").value = "";
}


function calculate(input){
	if(input % 3 == 0){ 
		if(input % 5 == 0){ return 'FizzBuzz'; } else { return 'Fizz'; }
	}
	if(input % 5 == 0){ return 'Buzz'; } else { return 'nani?!'; }
}

function removeRow(x){ document.getElementById('theTable').childNodes[x].remove(); }
function clearTable(){
	document.getElementById('theTable').innerHTML = '<tr><th>Fizz/Buzzy Number</th><th>Did Fizz or Buzz?</th></tr>';
}

function row(index, fizzer){
	var col1Text = document.createTextNode(index);
	var col2Text = document.createTextNode(fizzer);
	var delText = document.createTextNode("X");
	
	var td1 = document.createElement('td');
	var td2= document.createElement('td');
	var td3 = document.createElement('td');
	
	var delButton = document.createElement('button');
	delButton.setAttribute("onclick", "removeRow(" +  col1Text + ")");
	delButton.setAttribute("style", "color:red");
	delButton.appendChild(delText);
	
	td1.appendChild(col1Text);
	td2.appendChild(col2Text);
	td3.appendChild(delButton);
	
	var row = document.createElement("tr");
	row.setAttribute("id", index);
	row.appendChild(td1);
	row.appendChild(td2);
	row.appendChild(td3);
	
	return row;
}