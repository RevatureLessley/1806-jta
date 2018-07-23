window.onload = function()
{
	getReqs();
}
function getReqs()
{
	let xhr = new XMLHttpRequest();
	let table = document.getElementById("reqTable");
	//Use the servlet url mapping for your url
	//when hitting it with AJAX.
	let url = "SelectReq";
	
	xhr.onreadystatechange = function()
	{
		if(xhr.readyState==4)
		{
			//Converts a JSON string into a JSON object.
			let data = JSON.parse(xhr.response);
			
			//To convert to a JSON string, for java use, we use:
			//JSON.stringify()
			console.log(data);
			
			for(index in data)
			{
				let row = document.createElement('tr');
				let td1 = document.createElement('td');
				let td2 = document.createElement('td');
				let td3 = document.createElement('td');
				let td4 = document.createElement('td');
				let td5 = document.createElement('td');
				
				let td1t = document.createTextNode(data[index]["reimbursementId"]);
				let td2t = document.createTextNode(data[index]["eventId"]);
				let td3t = document.createTextNode(data[index]["amountReq"]);
				let td4t = document.createTextNode(data[index]["isBcApprove"]);
				
				td1.appendChild(td1t);
				td2.appendChild(td2t);
				td3.appendChild(td3t);
				td4.appendChild(td4t);
				
				row.appendChild(td1);
				row.appendChild(td2);
				row.appendChild(td3);
				row.appendChild(td4);
				
				let But = document.createElement('button');
				let btn = document.createTextNode("Approve");
				But.addEventListener("click", function(){
					approve(data[index]["reimbursementId"]);
				});
				But.appendChild(btn);
				td5.appendChild(But);
				row.appendChild(td5);
				table.appendChild(row);
			}
			
			
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}

function approve(id)
{
	let xhr = new XMLHttpRequest();
	//Use the servlet url mapping for your url
	//when hitting it with AJAX.
	let url = "ApproveServlet";
	xhr.open("POST", url) //Or GET
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xhr.send("param1=" + id);
	}