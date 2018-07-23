
function setMinDate(){
	var date = new Date();
	date.setDate(date.getDate()+7);

	let dd = date.getDate();
	let mm = date.getMonth() + 1;
	let yyyy = date.getFullYear();

	if (dd < 10)
		dd = "0" + dd;
	if (mm < 10)
		mm = "0" + mm;

	document.getElementById("datefield").setAttribute("min",
			yyyy + '-' + mm + '-' + dd);
}

function costChange(){
	
	let costInput = document.getElementById("costInput");
	let eventType = document.getElementById("eventType");
	
	let t = eventType.value;
	let amt = costInput.value;
	
	console.log(t);
	console.log(amt);
	
	let xhr = new XMLHttpRequest();
	let url = `/Project1/CalcReimbServlet?eventType=${t}&amount=${amt}`;

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4 && xhr.status == 200) {
			console.log(xhr.response);			
			updateReimbAmount(xhr.response);
		}

	}

	xhr.open("GET", url);
	xhr.send();
	
}

function updateReimbAmount(amount){
	costLabel = document.getElementById("costLabel");
	costLabel.innerHTML = `Cost - (reimbursement ${amount})`;
}