package com.revature.day3.finalexample;

public class Something {
	//Final on a field enforces a constant.
	//You cannot change this anywhere else in application.
	public final int SOME_NUMBER = 200;
	//NOTE: Convention suggests you use all caps and underscores when naming constants.
	
	public void method1(){
		//SOME_NUMBER += 10; You cannot alter final fields
		System.out.println("Method1");
	}
	
	public final void method2(){
		System.out.println("Method2");
	}
	
	public void method2(int a){
		
	}
}
