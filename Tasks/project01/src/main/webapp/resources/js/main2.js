/**
 * 
 */

function getFiles(){
	let x = document.getElementById("form2");
	let x2 = x.elements[0].value;
	
	
	let table = document.getElementById("selectTable");
    document.getElementById("selectTable").innerHTML = "List of Files";
	let form1 = document.createElement("form");
	form1.setAttribute('action',"/project01/BenefitDownload");
	form1.setAttribute('target',"_blank");
	let select1 = document.createElement("select");
	select1.setAttribute('name','file');
	let sub1 = document.createElement('input');
	sub1.setAttribute('type','submit');
	

	
	var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function(){
			if(xhttp.readyState==4){
				//Converts a JSON string into a JSON object.
				let data = JSON.parse(xhttp.response);
				
				//To convert to a JSON string, for java use, we use:
				//JSON.stringify()

				for(index in data){
					if(data[index]=="No file given"){alert("Processing. . .");}
					else{
						alert(data[index]);
						let td1  = document.createElement('option');
					    td1.setAttribute("value", data[index]);
					    var t = document.createTextNode(data[index]);
					    td1.appendChild(t);
					    select1.appendChild(td1);
					
					}
					
					form1.appendChild(select1);
					form1.appendChild(sub1);
					table.appendChild(form1);
				}
			}
	    else{alert("Pending . . .");}
	  };
	  xhttp.open("POST", "fileNamesServlet",false);
	  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	  xhttp.send("id="+ x2);
}

/*
 *     <option value="volvo">Volvo</option>
    <option value="saab">Saab</option>
    <option value="fiat">Fiat</option>
    <option value="audi">Audi</option>
    
    
    	 <form action="/action_page.php">
	  <select id="selectTable">
	  </select>
	  <input type="submit">
	</form>
 * */


/*
 *     var x = document.getElementById("frm1");
    var text = "";
    var i;
    for (i = 0; i < x.length ;i++) {
        text += x.elements[i].value + "<br>";
    }
    document.getElementById("demo").innerHTML = text;
 * 
 */