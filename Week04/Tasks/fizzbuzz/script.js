function fizzBuzz(){
  reset();
  let min = document.getElementById("min").value;
  let max = document.getElementById("max").value;
  let minNum = Number(min);
  let maxNum = Number(max);
  if(minNum >= maxNum){
    document.getElementById("textHere").innerHTML = "Max number must be greater than the min number.";
  } else if(minNum < 0){
    document.getElementById("textHere").innerHTML = "Only positive numbers are allowed.";
  }
  else{
    for( ; minNum<=maxNum; minNum++){
      if(minNum % 3 == 0 && minNum % 5 == 0){
        document.getElementById("textHere").innerHTML += "fizzbuzz ";
      } else if (minNum % 3 == 0) {
        document.getElementById("textHere").innerHTML += "fizz ";
      } else if (minNum % 5 == 0) {
        document.getElementById("textHere").innerHTML += "buzz ";
      } else {
        document.getElementById("textHere").innerHTML += minNum + " ";
      }
    }
  }
}

function reset() {
  document.getElementById("textHere").innerHTML = "";
}