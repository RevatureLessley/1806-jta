function checkPassword(){
	let div = document.getElementById("beforePassword");
	div.innerHTML = "";
	let password1 = document.getElementById("password").value;
	let password2 = document.getElementById("passwordConfirm").value;

	if(password1 != password2){
		div.innerHTML = "Passwords do not match.";
	}
}