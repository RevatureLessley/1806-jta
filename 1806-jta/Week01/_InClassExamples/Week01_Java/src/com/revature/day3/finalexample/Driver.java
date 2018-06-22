package com.revature.day3.finalexample;

public class Driver extends Something{

	public static void main(String[] args) {
		Driver d = new Driver();
		d.method1();
		d.method2();
		d.method2(3);
	}
	
	/*
	 * Note the '@Override'
	 * In Java these are called annotations. They simply serve as metadata.
	 * Some do enforce functionality.
	 * Override, for instance, helps us ensure that we are, indeed, overriding a method.
	 * The annotation will force an error on methods that aren't actually overriding 
	 * anything.
	 */
	
	@Override
	public void method1() {
		super.method1();
	}
	
//	//Cannot overide method2() since it is final.
//	public void method2(){
//		
//	}
	
	

}
