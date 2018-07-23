


function authenticate(){
    let e = null;
	if( e = document.getElementById("error-passwords")) e.remove();

    let xhr = new XMLHttpRequest();
    let username = document.getElementById('username').value;
    let password = document.getElementById('password').value;

    xhr.onreadystatechange = function(){
        if (xhr.readyState == 4){
            let jsonObject = JSON.parse(xhr.response);
            var validCredentials = false;
            for (record in jsonObject){
                if (jsonObject[record].email == username){
                    if (jsonObject[record].password == password){
                        xhr = new XMLHttpRequest();
                        xhr.onreadystatechange = function(){}   //NOTE: it is important that .onreadystatechange gets reassigned.
                        xhr.open("POST", "../Authentication.Servlet");
                        xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
                        xhr.send('username='+username+'&password='+password);
                        validCredentials = true;
                    }
                    break;
                }

            }
            if (!validCredentials){
                let errorText = document.createTextNode("Username or password is incorrect.");
                let errorDiv = document.createElement('div');
                errorDiv.setAttribute("class", "alert alert-danger");
                errorDiv.setAttribute("id", "error-passwords");
                errorDiv.appendChild(errorText);
                document.getElementById("password").appendChild(errorDiv);
            }
        }
    }
    xhr.open("GET", "../GetAllEmployees.Servlet");
    xhr.send();
}