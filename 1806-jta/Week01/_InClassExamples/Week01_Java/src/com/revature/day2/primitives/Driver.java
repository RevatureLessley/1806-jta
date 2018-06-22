package com.revature.day2.primitives;

public class Driver {
	
	//Primitive datatypes
	/*
	 * A primitive datatype is a non object datatype
	 * typically reserved in memory for the first such n such amounts of data.
	 * IE, the first 100 numbers of an int, are literally stored as such in memory for easy access.
	 * (You CAN alter these values so that 2 + 2 = 5)
	 * Since these are NOT objects themselves, one can say Java is NOT 100% object oriented.
	 */
	int i;			//number of max size: 32 bits;
	short s;		//number: 16 bits;
	long l;			//number: 64 bits;
	float f;		//decimal value support: 32 bits
	double d;		//decimal value support: 64 bits
	byte b;			//number: 8 bits;
	char c;			//number/letter 16 bits;
	boolean bool;	//A boolean only needs a bit, but arguments suggests it may actually be a byte reserved.
	
	
	public static void main(String[] args) {
		Driver d = new Driver();
		d.displayPrimitives();
		
	}
	
	public void displayPrimitives(){
		System.out.println( //DEFAULT VALUES (Only applicable in instance scope)
				i + "\n" +  //0
				s + "\n" +	//0
				l + "\n" +	//0
				f + "\n" +	//0.0
				d + "\n" +	//0.0
				b + "\n" +	//0
				c + "\n" +	//NUL
				bool + "\n" //false
				);
	}

}
