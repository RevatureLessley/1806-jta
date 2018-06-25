package com.revature.day3.pillars;

public class C extends B{
	public char field = 'C';
	@Override
	//When overriding I can NOT change return type.
	//I can NOT change the parameter count
	//You CAN change access, long as you are making it
	//	MORE accessible.
	public void method1(){
		System.out.println("Method in C");
		
	}
	public void method2(){
		System.out.println("Method in C");
	}
	public void method3(){
		System.out.println("Method in C");
	}
}
