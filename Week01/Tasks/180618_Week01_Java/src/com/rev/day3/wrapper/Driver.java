package com.rev.day3.wrapper;

public class Driver {

	public static void main(String[] args) {
		
		System.out.println(addition(10, 5));// values are being auto-boxed
	}

	public static int addition(Integer a, Integer b) {
		return a + b; //values being auto-unboxed by '+'
	}

}
