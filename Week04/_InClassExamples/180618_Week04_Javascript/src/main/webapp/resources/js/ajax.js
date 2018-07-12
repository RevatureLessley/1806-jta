function retrieve(){
	
	document.getElementById("pokeydiv").innerHTML = 
		"<img width=75 height=75 src='https://zippy.gfycat.com/SkinnySeveralAsianlion.gif'>";
	
	/*
	 * XMLHttpRequest (XHR for short) object is the object we use in AJAZ in order to communicate
	 * with the server API endpoints in order to send a reuqest,
	 * and get a response with the date we wanted.
	 * 
	 * XHR objects have FICE states they can be in.
	 * These states are numbered from 0-4
	 * 
	 * 0 - Request is not configured yet.
	 * 		-IE the object is created, just configured
	 * 1 - Request has been properly configured
	 * 		-we called the .open() method on our XHR
	 * 2 - The request has been sent to the server
	 * 		-We called the .send() method
	 * 3 - Request is received and being processed
	 * 		-Communication with the server has been established.
	 * 		-yet we have not received a response yet
	 * 4 - Request has been send, and proper response has been received.
	 * 		-request/response lifecycle is complete.
	 */
	
	let pkid = document.getElementById("pkmnid").value;
	let xhr = new XMLHttpRequest(); //STATE 0\
	let url = ("https://pokeapi.co/api/v2/pokemon/" + pkid + "/");
	
	xhr.onreadystatechange = function(){
		
		
		console.log(xhr.readystate + " " + xhr.status)
		
		if(xhr.readyState == 4 && xhr.status == 200){
			
			let response = JSON.parse(xhr.response);
			/*
			 * JSON stands for JavaScript Object Notation,
			 * like XML it serves to only store data for use.
			 * However JSON is a favorite among developers because it provides us with
			 * a means to navigate data as we would navigate a javascript object.
			 * So as opposed to grabbign specific data by using dom manipulation on XML,
			 * all we have to do now, is interact with the data using dot notation or
			 * bracket notation.
			 * JSON.parse, converts the data to the object format.
			 */
			
			let pkid = document.createTextNode(response.id);
			let pkname = document.createTextNode(response.name);
			let types = response.types;
			let type1,type2;
			
			if(types.length>1){
				type1 = types[0].type.name;
				type2 = types[1].type.name;
			}else{
				type1 = types[0].type.name;
			}
			
			let type = type1;
			if(type2){
				type += ", " + type2;
			}
			type = document.createTextNode(type);
			
			let pksprite = response.sprites.front_default;
			
			let td1 = document.createElement('td');
			let td2 = document.createElement('td');
			let td3 = document.createElement('td');
			let td4 = document.createElement('td');
			
			let th1 = document.createElement('th');
			let th2 = document.createElement('th');
			let th3 = document.createElement('th');
			let th4 = document.createElement('th');
			
			let th1t = document.createTextNode('ID');
			let th2t = document.createTextNode('NAME');
			let th3t = document.createTextNode('TYPE');
			let th4t = document.createTextNode('SPRITE');
			
			let img = document.createElement('img');
			img.setAttribute("src", pksprite);
			
			let tr1 = document.createElement('tr');
			let tr2 = document.createElement('tr');
			let tr3 = document.createElement('tr');
			let tr4 = document.createElement('tr');
			let table = document.createElement('table');
			
			td1.appendChild(pkid);
			td2.appendChild(pkname);
			td3.appendChild(type);
			td4.appendChild(img);
			
			th1.appendChild(th1t);
			th2.appendChild(th2t);
			th3.appendChild(th3t);
			th4.appendChild(th4t);
			
			tr1.appendChild(th1);
			tr1.appendChild(td1);
			tr2.appendChild(th2);
			tr2.appendChild(td2);
			tr3.appendChild(th3);
			tr3.appendChild(td3);
			tr4.appendChild(th4);
			tr4.appendChild(td4);
			
			table.appendChild(tr1);
			table.appendChild(tr2);
			table.appendChild(tr3);
			table.appendChild(tr4);
			
			document.getElementById("pokeydiv").innerHTML = "";
			document.getElementById("pokeydiv").appendChild(table);
			
			
		}else if(xhr.readyState==4 && xhr.status!=200){
			document.getElementById("pokeydiv").innerHTML=
				'<div class="alert alert-danger">' +
				'NO SUCH POKEYMAN' +
				'</div>';
		}
		
	}
	//This method configures the xhr.
	xhr.open("GET", url); //STATE 1
	xhr.send(); //STATE 2
	
	
	
}