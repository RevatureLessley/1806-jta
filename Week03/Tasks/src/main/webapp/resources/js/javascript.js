function checkPassword(){
	let div = document.getElementById("beforePassword");
	div.innerHTML = "";
	let password1 = document.getElementById("rpassword").value;
	let password2 = document.getElementById("passwordConfirm").value;

	if(password1 != password2){
		div.innerHTML = "Passwords do not match.";
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