package com.revature.day5.designpatterns;

import static com.revature.day5.designpatterns.Singleton.getSingleton;
import static com.revature.day5.designpatterns.ShapeFactory.getShape;

public class Driver {

	
	public static void main(String[] args) {
		Singleton s1 = getSingleton();
		Singleton s2 = getSingleton();
		
		System.out.println(s1.equals(s2));
		System.out.println(s1 == s2);
		
		System.out.println(getShape("square").draw());
		//System.out.println(getShape("circle").draw());
		System.out.println(getShape("TrIaNgLe").draw());
		
		System.out.println(Thread.currentThread().getName());
	}

}
