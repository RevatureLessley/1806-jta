/**
 * 
 */

function fizzbuzz(){
	let max   =   Number(document.getElementById("max").value);
	let min   =   Number(document.getElementById("min").value);
	
	if(Number.isInteger(Number(max))&&Number.isInteger(Number(min))){
		for(let i = min; i <= max; i++){
			if(i%3 == 0 & i%5==0){document.getElementById("textBox").innerHTML+="FizzBuzz<br>"}
			else if(i%3 == 0){document.getElementById("textBox").innerHTML+="Fizz<br>"}
			else if(i%5 == 0){document.getElementById("textBox").innerHTML+="Buzz<br>"}
			else{window.alert(i + " Not divisible by 3 or 5");}
		}
		
	}
	
	
	else{window.alert("Please enter valid numbers!!!");}
}