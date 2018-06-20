package com.revature.day3.shortcircuits;

public class Driver {

	public static void main(String[] args) {
		/*
		 * A comparator is any operator that aims to compare 2 entities.
		 * IE: >,<,<=,>=<,etc
		 * Two big comparators are the AND and OR operators.
		 * 
		 */
		
		if(returnTrue());
		if(returnFalse());
		System.out.println("==================");
		
		System.out.println(returnFalse() & returnTrue());
		System.out.println(returnTrue() | returnFalse());
		System.out.println("==================");
		/*
		 * Normal & and | will check all sides of the comparison regardless if they are
		 * true or false. This can be wasteful.
		 * So instead we have short-circuit operators, where if they notice that 
		 * and AND or OR statement will evaluate to true or false, regardless of the 
		 * rest of the comparison, then it will stop checking altogether and return the
		 * answer.
		 */
		System.out.println(returnFalse() && returnTrue());
		System.out.println(returnTrue() || returnFalse() && returnFalse() || returnFalse());
		System.out.println("==================");		
		
		
	}
	
	public static boolean returnTrue(){
		System.out.println("RETURNED TRUE");
		return true;
	}
	public static boolean returnFalse(){
		System.out.println("RETURNED FALSE");
		return false;
	}

}
