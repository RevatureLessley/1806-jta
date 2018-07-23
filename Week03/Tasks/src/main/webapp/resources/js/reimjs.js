function toggleC(){
	var c = document.getElementById("cutoff");
	var ch = document.getElementById("cutoffh");
	if (c.style.visibility === 'hidden') {
        c.style.visibility = 'visible';
        ch.style.visibility = 'visible';
    } else {
        c.style.visibility = 'hidden';
        ch.style.visibility = 'hidden';
    }
}