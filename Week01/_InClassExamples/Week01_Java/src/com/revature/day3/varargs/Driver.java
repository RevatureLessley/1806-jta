package com.revature.day3.varargs;

public class Driver {

	public static void main(String[] args) {
		Driver d = new Driver();
		int result = d.addition(10, 20);

		System.out.println(result);
		System.out.println(d.addition(5));
		System.out.println(d.addition(3,4,5));
		System.out.println(d.division(5.0, 10));
		System.out.println(d.addition(1,2,3,4,5,6,7,8,9,10));
	}
	
	//OVERLOADING
	/*
	 * By defining two or methods with the same name, but different parameters, you
	 * will overload them. This allows us to call the same method with different input
	 * yielding different results.
	 * For example, I can reuse "addition" for 1,2, or even 3 numbers via overloading.
	 * When one overloads.
	 * The only thing you need to ensure is that the method name matches, and the parameters
	 * are different.
	 */
	public int addition(int a, int b){
		return a + b;
	}
	
	public int addition(int a){
		return a;
	}
	
	private double addition(int a, int b, int c){
		return a + b + c;
	}
	
	public double division(double a, int b){
		return a/b;
	}
	public double division(int a, double b){
		return a/b;
	}

	/*
	 * Instead of overloading this function 100 times to allow for any number of 
	 * parameters to add, we can use varargs instead. varargs allow us to have a dynamic 
	 * amount of parameters so that we dont have to cater to each possible number.
	 * USE ... to make a vararg
	 */
	
	public int addition(int atLeastOnenumberRequired, int ...nums){
		int result = 0;
		//NOTE, nums is an array when using varargs.
		for(int i: nums){
			result += i;
		}
		
		return result;
	}
}
