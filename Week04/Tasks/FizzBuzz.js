function fizzBuzz()
	{
		x = Number(document.getElementById('min').value);
		y = Number(document.getElementById('max').value);
			
		while (x <= y) 
		{ 
				
				if( x % 3 == 0 && x % 5 == 0 )
				{
					document.getElementById('para1').innerHTML += x + ' <b>FIZZBUZZ!</b><br>';
				}
				else if( x % 3 == 0 )
				{
				  document.getElementById('para1').innerHTML += x + ' <b>FIZZ</b><br>';
				}
				else if( x % 5 == 0 ) 
				{
					document.getElementById('para1').innerHTML += x + ' <b>BUZZ!</b><br>';
				}
				document.getElementById('para1').innerHTML += x + '<br>';
				console.log(x.toString());
				x++;
		}
	}

function clear()
{
	// Not entirely sure why this won't work.
	document.getElementById('para1').innerHTML = ' ';
}
