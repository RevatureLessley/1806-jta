package com.rev.day2.exceptions;

import java.io.IOError;
import java.io.IOException;

public class Driver {
	public static void main(String[] args) {
		Driver driver = new Driver();

		driver.divZeroHandled();
		driver.goodHandling();
		driver.checkedException();

		try {
			driver.ducking();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			driver.method1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(driver.weird());
	}

	public void divZero() {

		// this is risky code. Should be put in a try block
		System.out.println(1 / 0);
		System.out.println("end of div0");
	}

	public void divZeroHandled() {

		try {
			System.out.println(1 / 0);
		} catch (ArithmeticException e) {
			System.out.println("cannot divide by zero");
		} finally {
			// executes no matter what unless application crashes via error, or user invokes
			// System.exit(0)
		}

		System.out.println("end of div0");
	}

	public void goodHandling() {

		try {
			System.out.println(1 / 0);
			Object o = null;
			o.toString();
			int[] array = new int[1];
			array[1] = 99;
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ArithmeticException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	public void checkedException() {
		try {
			throw new IOException();
		} catch (Exception e) {
			System.out.println("gotem");
		}
	}

	public void ducking() throws Exception {
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
	
	public String weird() {
		try {
			throw new MyException();
		}catch (Exception e) {
			e.printStackTrace();
			return "catch";
		}finally {
			return "finally";
		}

	}
	
	private class MyException extends Exception{
		@Override
		public String getMessage() {
			return "What the @#$@# happened?";
		}
	}

}
