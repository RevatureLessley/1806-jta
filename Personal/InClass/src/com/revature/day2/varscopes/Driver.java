package com.revature.day2.varscopes;

public class Driver {

	public static void main(String[] args) {
		
		System.out.println(Person.personCount); //0
		//You can access static fields via the name of the Class directly
		
		//Reference = Assignment/Object
		Person p1 = new Person("bobbert", 92);
		//when objects:
		//ClassName variableName = new ClassName();
		//Class:
		//A blueprint for an Object
		//Object:
		//An instantiation of a class.
		//A runtime entity that can be altered and maintain state changes.
		System.out.println(p1.getAge());	//92
		System.out.println(p1.personCount);	//1
		
		System.out.println("======PERSON2======");
		Person p2 = new Person("Ryan", 118);
		System.out.println(Person.personCount);	//2
		System.out.println(p2.personCount); 	//2
		
	}

}
