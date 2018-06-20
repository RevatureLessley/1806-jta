package com.revature.day3.wrapperclasses;

public class Driver {

	public static void main(String[] args) {
		/*
		 * A wrapper class is an object representation of a primitive
		 * value, thus giving us methods we are able to call for primitive values.
		 * 
		 */
		
		//Wrapper classes
		/*
		 * A wrapper class is simply the primitive datatype, with a capital first 
		 * letter.
		 * Exceptions being:
		 * Integer
		 * Character
		 * Where the full name is also used.
		 */
		
		System.out.println(Integer.MAX_VALUE);
		
		/*
		 * Autoboxing
		 * -Autoboxing is the implicit conversion of a primitive value into its
		 * wrapper class. This takes place any time you use a primitive as a parmeter
		 * that expects a wrapper class.
		 * 
		 * Likewise
		 * Auto-unboxing
		 * -The reverse, implicitly unwrapping an wrapper class back into its primitive
		 * form.
		 */
		
		Driver d = new Driver();
		System.out.println(d.addition(5, 15));	//Autobox
		
	}
	
	public int addition(Integer a, Integer b){

		return a + b; //Auto-unboxing occurs during the arithmetic.
	}

}
