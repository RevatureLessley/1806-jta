function setCookie(cname, cvalue, exdays){
	var d = new Date();
	d.setTime(d.getTime() + (exdays*24*60*60*10000));
	var expires = "expires="+d.toUTCString();
	document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
	console.log("created cookie: "+cname);
}

function checkCookie(cname){
	var user = getCookie("username");
	if(user != ""){
		// logged in
	} else {
		// is not logged in. kill jsession and return to login page
	}
}

function httpGet(){
	var url_string = window.location.href;
	var url = new URL(url_string);
	var c = url.searchParams.get("username");
	console.log(c);
	document.getElementById("navbardrop").innerHTML = c;
	document.getElementById("username").innerHTML = "Welcome! " + c;
	setCookie("username", c, 1);
}