window.onload = function()
{
	getAccountCurrency();
}

function getAccountCurrency(){
	let xhr = new XMLHttpRequest();
	let paraTwo = document.getElementById("employeecurrencynow");
	paraTwo.innerHTML = "";
	let url = "../GetEmpCurrency";
	
	xhr.onreadystatechange = function(){
		//console.log("inside xhr.onreadystatechange readyState: " + xhr.readyState);
		if(xhr.readyState==4){
			//console.log("++++++++++INSIDE xhr.readyState == 4++++++++++");
			console.log(xhr.response);
			//console.log("JSON parse: " + JSON.parse(xhr.response));
			paraTwo.innerHTML = "You have $$$$$ of reimbursement left.";
			let data = JSON.parse(xhr.response);
			
			paraTwo.innerHTML = "You have $" + data + " of reimbursement left.";
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}