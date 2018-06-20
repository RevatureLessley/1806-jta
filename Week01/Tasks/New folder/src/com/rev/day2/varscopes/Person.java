package com.rev.day2.varscopes;

/*
 * design patterns: common approach to achieve a common functionality
 * POJO: Plain old java object - container of data
 * 
 */
public class Person {
	
	public static int personCount = 0;
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
		personCount++;
	}
	
	// Instance variables b/c this is instance scope
	private String name;
	private int age;

	public void setName(String name) {
		// method scope - local variables
		// when multiple variables have the same name, defaults to more restrictive
		// scope

		this.name = name;
	}

	public String getName() {
		return name;
	}

	// can generate getters and setters
	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void SomeMethod() {

		for (int i = 0; i < 5; i++) {
			//loop scope
			//i only exists w/in forloop
			System.out.println(i);
		}

	}
}
