package com.rev.day3.shortcircuits;

public class Driver {

	public static void main(String[] args) {
		System.out.println((((returnTrue() || returnFalse()) && returnFalse()) || returnTrue()));
	}

	public static boolean returnTrue() {
		System.out.println("returned TRUE");
		return true;
	}

	public static boolean returnFalse() {
		System.out.println("returned FALSE");
		return false;
	}
}
