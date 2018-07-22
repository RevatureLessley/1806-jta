function checkApprovalAttachments() {
	let div = document.getElementById("beforeApprovalType");
	div.innerHTML = "";
	let b = document.getElementById("directSupervisor").checked;
	let c = document.getElementById("departmentHead").checked;
	let d = document.getElementById("benefitsCoordinator").checked;
	
	if(!(b || c || d)) {
		div.innerHTML = "At least one of the checkboxes must be checked.";
	}
}

function checkDatetime() {
	let div = document.getElementById("beforeDatetime");
	div.innerHTML = "";
	let datestring = document.getElementById("datetime").value;
	let enteredDate = new Date(datestring);
	let cutoffDate = new Date();
	cutoffDate.setDate(cutoffDate.getDate() + 7);
	if(cutoffDate > enteredDate) {
		div.innerHTML = 
			"You must submit this form at least 1 week before the event.";
	}
}

function checkPassword() {
	let div = document.getElementById("beforePassword");
	div.innerHTML = "";
	let password1 = document.getElementById("rpassword").value;
	let password2 = document.getElementById("passwordConfirm").value;

	if(password1 != password2){
		div.innerHTML = "Passwords do not match.";
	}
}

function getCutoff() {
	console.log("Getting cutoff.");
	let div = document.getElementById("afterGrade");
	div.innerHTML = "";
	let format = document.getElementById("grading").value;
	
	if(format == "Passing Grade") {
		console.log("Displaying cutoff.");
		div.innerHTML = "<br><label for=\"cutoff\">Passing Grade:</label>" + 
						"<input " +  
							"class=\"form-control\" id=\"cutoff\" " +
							"name=\"cutoff\" type=\"number\" min=\"0\" " +
							"max=\"1\" step=\"0.01\" required" + 
						">";
	}
}

function handleFileSelect(evt) {
    var files = evt.target.files; // FileList object

    // Loop through the FileList and render image files as thumbnails.
    for (var i = 0, f; f = files[i]; i++) {
      var reader = new FileReader();

      // Closure to capture the file information.
      reader.onload = (function(theFile) {
        return function(e) {
          document.getElementById('fileDisplay').innerHTML = 
        	  ['<object data="' , e.target.result , '" title="' , 
        	   escape(theFile.name) , '"/>'].join('');
        };
      })(f);

      // Read in the image file as a data URL.
      reader.readAsDataURL(f);
    }
}