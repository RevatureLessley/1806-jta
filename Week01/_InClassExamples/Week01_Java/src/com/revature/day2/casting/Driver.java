package com.revature.day2.casting;

public class Driver {

	public static void main(String[] args) {
		int i = 32768;			
		short s = 32767;		
		long l = 210_273_840_234_090L;			
		float f = 5.5f;			//NOTE: floats require the letter 'f' in the number
		double d = 10.10;		
		byte b = 80;			//NOTE: -128 - 127
		char c = 'r';			//NOTE: chars are written using apostrophes, not quotes;
								//NOTE: 0 - 65535
		boolean bool;	
		
		/*
		 * Two kinds of casting in Java:
		 * -Explicit casting
		 * --Where you specifically write out what datatype you want to cast to, IE convert to.
		 * -Implicit casting
		 * --Java will convert the datatype into the needed one for you, without you having to type it out.
		 */
		
		//EXPLICIT CASTING
		s = (short)i;
		System.out.println(s);
		
		//IMPLICIT CASTING
		i = s;
		
		i = (int)l;
		l = i;
		
		s = (short)l;
		l = s;
		
		c = (char)s;
		c = (char)i;
		c = (char)b;
		
		f = (float)d;
		d = f;
		
	}

}
