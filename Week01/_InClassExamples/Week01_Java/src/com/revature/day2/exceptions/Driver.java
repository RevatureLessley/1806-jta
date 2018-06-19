package com.revature.day2.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.crypto.ExemptionMechanismException;

public class Driver {

	public static void main(String[] args){
		Driver d = new Driver();
		//d.divideByZero();
		d.divideByZeroHandled();
		d.goodHandling();
		try{
			d.ducking();
		}catch(Exception e){
			System.out.println("Caught the duck");
		}
		try{
			d.method1();
		}catch(Exception e){
			
		}
		
		
		
		System.out.println(d.weird());
		
		d.oops();
		
		System.out.println("===END OF EXECUTION===");
		
		
	}
	
	public void divideByZero(){
		System.out.println(1/0);
		System.out.println("===END OF DIVIDEBYZERO");
	}
	/*
	 * An exception can be safely described as an incident where something unexpected occurs.
	 * -An applicaiton behaves in a manner it should not.
	 * 
	 * An exception is NOT an error.
	 * Both are two separate classes.
	 * An error can be described as:
	 * 	-An incident that cannot be rasonably recovered from.
	 * -Think: OutOfMemoryError and StackOverflowError
	 */
	public void divideByZeroHandled(){
		//Below is risky code. AS such, any code you consider risky, should go into a 'try'
		//block. The first requirement for exception handling.
		try{
			System.out.println(1/0);
			
		}
		//Try by itself is not enough, you need at least one more thing.
		//In this case we will use a catch block.
		catch(ArithmeticException e){
			/*
			 * A catch block will describe which exception it is looking for. If the 
			 * detailed exception occurs, it will be caught, and the flow of execution will be
			 * re-routed into the catch block for any cleanup that should occur.
			 */
			System.out.println("====EXCEPTION HANDELED!=====");
			e.printStackTrace();
		}finally{
			/*
			 * A finally block is a block that you can use which will execute no matter what!
			 * If an exception occurs, it will be handled, and the the finally block will
			 * execute next. If no exceptions occurs, the finally block execute next. If 
			 * an exception crashes the application, the finally block will execute next.
			 * There are only TWO ways where this block will NOT execute. 
			 * Either the application crashes via error, or a user invokes System.exit(0).
			 */
			System.out.println("===Finally block executed!===");
		}
		
		System.out.println("===END OF DIVIDEBYZERO");
	}
	
	public void goodHandling(){
		//You can use the 'throw' keyword to manually invoke any exception of your choice.
		try{
			
			System.out.println(1/0); //ArithmeticException
			Object o = null;
			o.toString(); //NullPointerException
			int array[] = {1};
			array[99] = 22; //ArrayOutOfboundsException
		}catch(NullPointerException e){
		
			e.printStackTrace();
		}catch(ArithmeticException e){
			e.printStackTrace();
		}catch(ArrayIndexOutOfBoundsException e){
			e.printStackTrace();
		}
		/*
		 * When using multiple catch blocks, be sure to order form child exceptions first,
		 * to parent ones. Otherwise you get unreachable code.
		 * A try block can have 0-many catch blocks assuming a finally block attached for the
		 * 0 catch block case.
		 */
	}
	
	public void checkedException(){
		try{	
			throw new Exception(); //If you do not handle this Checked Exception, your code will
		}catch(Exception e){		//not compile.
			System.out.println("Got em...");
		}
	}
	
	/*
	 * When it comes exception handling, one does not need to worry about a try catch block
	 * right away. If the exception is something you would have handled elsewhere, you can
	 * choose to propogate the exception. Also called ducking the exception.
	 * Where, instead of handling it, you just throw it up to the calling method/class to
	 * handle instead.
	 */
	public void ducking() throws Exception{
		throw new Exception();
	}
	
	public void method1() throws IOException{
		method2();
	}
	
	public void method2() throws IOException{
		method3();
	}
	
	public void method3() throws IOException{
		throw new IOException();
	}
	
	public String weird(){
		
		try{
			throw new Exception();
		}catch(Exception e){
			return "catch";
		}finally{
			return "finally";
		}
	}
	
	public void oops(){
		try{
			throw new RyanWasWrong();
		}
		catch(RyanWasWrong e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
