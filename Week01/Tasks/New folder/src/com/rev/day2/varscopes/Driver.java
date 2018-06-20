package com.rev.day2.varscopes;

public class Driver {
	
	public static void main(String[] args) {
		
		//reference = assignment
		Person p1 = new Person("p1", 0);
		Person p2 = new Person("p2", 0);
		Person p3 = new Person("p3", 0);
		
		System.out.println(p1.getAge());
		System.out.println(p1.getName());
		System.out.println(Person.personCount);
		
	}
	
}
