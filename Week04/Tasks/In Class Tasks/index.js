//Eric Sundberg

function printStuff(){


}

function captureInput(){
    let el = document.getElementById("section1");
    let min = document.getElementById("min").value;
    let max = document.getElementById("max").value;

    //retrieve text space element
    let element = document.getElementById("output");
    element.innerHTML = ""; //reset
    let list = document.createElement('ul');
    list.setAttribute("style", "list-style-type:none");

    min = parseInt(min);
    max = parseInt(max);
    for(i = min; i <= max; i++){
        let row = document.createElement("li");
        let a;
        if (i % 3 == 0 && i % 5 == 0){
            a = "Fizz Buzz";
        }
        else if (i % 3 == 0){
            a = "Fizz";
        }
        else if(i % 5 == 0){
            a = "Buzz"
        }
        else {
            a = i;
        }
        let out = document.createTextNode(a);
        row.appendChild(out);
        list.appendChild(row);
    }


    element.appendChild(list);


}
