function updateCoverage(){
	let cost = document.getElementById("cost").value;
	let train = document.getElementById("train").value;
	let cov = document.getElementById("coverage");
	let coverage = 0;
	
	if(train == 1){
		coverage = cost*0.8;
	}
	else if(train == 2){
		coverage = cost*0.6;
	}
	else if(train == 3){
		coverage = cost*0.75;
	}
	else if(train == 4){
		coverage = cost;
	}
	else if(train == 5){
		coverage = cost*0.9;
	}
	else if(train == 6){
		coverage = cost*0.3;
	}
	else{
		coverage = 0;
	}
	
	cov.setAttribute("placeholder", coverage);
}

function disableGrade(){
	let gradeFormat = document.getElementById('grade').value;
	let grade = document.getElementById('gradepass');
	
	if(gradeFormat == 2){
		grade.setAttribute("disabled", "disabled");
	}
	else{
		grade.removeAttribute("disabled");
	}
}