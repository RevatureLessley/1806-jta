function fizzBuzz()
	{
		var x = 0;
	
		while (x < 100) {
		  
		  x = x + 1;
		  
		  if ( x % 3 === 0 && x % 5 === 0 ){
			document.getElementById('para1').innerHTML += x +'FIZZBUZZ1<br>';
		    continue;
		  }
		  
		  if( x % 3 === 0 ){
			  document.getElementById('para1').innerHTML += x + 'FIZZ<br>';
		    continue;
		  }
		  
		  if( x % 5 === 0 ) {
			document.getElementById('para1').innerHTML += x + 'BUZZ!<br>';
		    continue;
		  }
		  
		  document.getElementById('para1').innerHTML += x+'<br>';
		  console.log(x.toString());
		  
		}
	}
