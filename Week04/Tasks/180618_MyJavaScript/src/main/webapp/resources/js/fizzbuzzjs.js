function resetTable()
{
	var elmtTable = document.getElementById("theTable");
	var tableRows = elmtTable.getElementsByTagName("tr");
	var rowCount = tableRows.length;

	for (var x=rowCount-1; x>0; x--) {
	   elmtTable.removeChild(tableRows[x]);
	}
}

function errorValues()
{
	first
}

function doFizzBuzz()
{
	firstMinValue = parseInt(document.getElementById("MinValue").value);
	firstMaxValue = parseInt(document.getElementById("MaxValue").value);

	if (firstMinValue >= firstMaxValue)
	{
		document.getElementById("para1").setAttribute("style", "color:red");
		document.getElementById("para1").innerHTML = "<b>Make sure min is less than max</b>";
	}
	else
	{
		document.getElementById("para1").innerHTML = "";
		for (i = firstMinValue; i< ((parseInt(firstMaxValue))+1); i++)
		{
			
			valueText = document.createTextNode(i);
			if (i%3 == 0 && i%5 == 0)
			{
				
				wordText = document.createTextNode("fizzbuzz");
			}
			else if (i%5 == 0)
			{
				wordText = document.createTextNode("buzz");
			}
			else if (i%3 == 0)
			{
				wordText = document.createTextNode("fizz");
			}
			else
			{
				wordText = document.createTextNode("");
			}
			
			td1 = document.createElement("td");
			td2 = document.createElement("td");
			
			td1.appendChild(valueText);
			td2.appendChild(wordText);
			
			row = document.createElement("tr");
			row.appendChild(td1);
			row.appendChild(td2);
			
			document.getElementById("theTable").appendChild(row);
			document.getElementById("MinValue").value = "";
			document.getElementById("MaxValue").value = "";
		}
	}
}