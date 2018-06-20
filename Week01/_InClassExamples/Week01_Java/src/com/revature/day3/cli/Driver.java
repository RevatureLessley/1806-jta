package com.revature.day3.cli;

public class Driver {

	public static void main(String[] args) {
		/*
		 * args serves to hold runnign the application using
		 * command line arguments.
		 * There are 2 ways to do this.
		 * The cheating, boring IDE way,
		 * OR
		 * using the actual command line.
		 */
		
		System.out.println(args.length);
		for(String s: args){
			System.out.println(s);
		}
		
		/*
		 * Using command line args:
		 * (The easy way)
		 * 1. navigate to :
		 * 		run > run configurations > (click the arguments tab) > write arguments
		 * 			separated by spaces.
		 * 2. Run the app
		 * 
		 * (The brave way)
		 * 1. Navigate to your src folder
		 * 2. Invoke the java compiler for your project
		 * 2.5. e.g. javac package\package\file.java
		 * 3. next execute the application using 'java'
		 * 3.5 e.g. java package.package.file arg1 arg2 arg3 argetc
		 */
	}

}
