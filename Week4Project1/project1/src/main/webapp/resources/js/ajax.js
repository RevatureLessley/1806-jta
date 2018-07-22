window.onload = function()
{
	getAccountCurrency();
}

function getAccountCurrency()
{
	let xhr = new XMLHttpRequest();
	let paraTwo = document.getElementById("employeecurrencynow");
	paraTwo.innerHTML = "";
	let url = "../GetEmpCurrency";
	
	xhr.onreadystatechange = function()
	{
		if(xhr.readyState==4)
		{
			paraTwo.innerHTML = "You have $$$$$ of reimbursement left.";
			let data = JSON.parse(xhr.response);
			
			paraTwo.innerHTML = "You have $" + data + " of reimbursement left.";
		}
	}
	
	xhr.open("GET", url);
	xhr.send();
}