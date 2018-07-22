function setCookie(cname, cvalue, exdays){
	var d = new Date();
	d.setTime(d.getTime() + (exdays*24*60*60*10000));
	var expires = "expires="+d.toUTCString();
	document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
	console.log();
}

function delete_cookie( name ) {
	console.log("deleting cookie");
	document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;path=/';
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function checkCookie(cname){
	var user = getCookie(cname);
	if(user == ""){
		return true;
	} else {
		return false;
	}
}	

function setUser(){
	var user = getCookie("username");
		
	var el;
	if(el = document.getElementById("navbardrop"))
		el.innerHTML = " "+user;
	if(el = document.getElementById("username"))
		el.innerHTML = "Welcome! " + user;
}

function httpGet(){
	var url_string = window.location.href;
	var url = new URL(url_string);
	var c = url.searchParams.get("username");

	if (c != null && getCookie("username") != c)
		setCookie("username", c, 1);
	
}