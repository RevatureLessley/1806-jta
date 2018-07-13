function validateRegistration(){
	let e = null;
	if( e = document.getElementById("error")){
		e.remove();
	};
	
	
	let pass1 = document.forms['registerForm']['password1'].value;
	let pass2 = document.forms['registerForm']['password2'].value;
	
	if(pass1 != pass2){
		let errorText = document.createTextNode("Passwords do not match!");
		let errorDiv = document.createElement('div');
		errorDiv.setAttribute("class", "alert alert-danger");
		errorDiv.setAttribute("id", "error");
		errorDiv.appendChild(errorText);
		document.getElementById("passwordMessage").appendChild(errorDiv);
		
		return false;
		
	}
	return true;
}
