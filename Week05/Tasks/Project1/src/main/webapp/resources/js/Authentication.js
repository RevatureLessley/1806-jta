emailCheck = false;
passwordCheck = false;
function checkValidation(){
    return (emailCheck && passwordCheck);
}

function checkPasswords() {
    let e = null;
	if( e = document.getElementById("error-passwords")) e.remove();

	let pass1 = document.forms['registerForm']['password1'].value;
	let pass2 = document.forms['registerForm']['password2'].value;

	if(pass1 != pass2){
		let errorText = document.createTextNode("Passwords do not match!");
		let errorDiv = document.createElement('div');
		errorDiv.setAttribute("class", "alert alert-danger");
		errorDiv.setAttribute("id", "error-passwords");
		errorDiv.appendChild(errorText);
		document.getElementById("list-item-password1").appendChild(errorDiv);
		passwordCheck = false;

	}
	else passwordCheck = true;
}

function checkEmail(){
    let e = null;
	if( e = document.getElementById("error-email")) e.remove();

    let email = document.forms['registerForm']['email'].value;

	let xhr = new XMLHttpRequest();

	xhr.onreadystatechange = function(){
	    if(xhr.readyState==4){
	        let data = JSON.parse(xhr.response);
            let isUsed = false;
            for (index in data){
                if (data[index]['email'] == email) isUsed = true;
            }
            if (isUsed){
                let errorText = document.createTextNode("Email is already registered.");
                let errorDiv = document.createElement('div');
                errorDiv.setAttribute("class", "alert alert-danger");
                errorDiv.setAttribute("id", "error-email");
                errorDiv.appendChild(errorText);
                document.getElementById("list-item-email").appendChild(errorDiv);
                emailCheck = false;
            }
            else emailCheck = true;
	    }
	}
    xhr.open("GET", "GetAllEmployees.Servlet");
    xhr.send();
}