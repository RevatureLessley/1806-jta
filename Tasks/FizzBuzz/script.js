var min, max;
var output = "";
let form = document.getElementsByTagName("form")[0];
let table = document.getElementsByTagName("table")[0];

function inputCheck()
{
    min = document.getElementById("min").value;
    max = document.getElementById("max").value;
    table.innerHTML = "";

    if (min >= max)
        alert("Min is not smaller than Max");
    else
    {
        addRow("Starting at " + min);
        calcFizzBuzz(min, max);
    }
}

function calcFizzBuzz(min, max)
{
    for(let i=min; i <= max; i++)
    {
        let message = "";
        
        if(i%3 == 0)
            message += "Fizz";
        if(i%5 == 0)
            message += "Buzz";
        
        output = (message || i);
        addRow(output);
    }
}

function addRow(message)
{
    let row = table.insertRow();
    let cell = row.insertCell();
    cell.innerHTML = message;
}
