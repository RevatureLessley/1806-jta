function validateRegistration() {
	
	let password1 = document.forms['registerForm']['password1'].value;
	let password2 = document.forms['registerForm']['password2'].value;
	
	if (password1 != password2) {
		let errorText = document.createTextNode("Password does not match");
		let errorDiv = document.createElement('div');
		errorDiv.setAttribute("class", "alert alert-danger");
		errorDiv.setAttribute("id", "error");
		errorDive.appendChild(errorText);
		document.findElementById("passwordMessage").appendChild(errorDiv);
		
		return false;
	}
}