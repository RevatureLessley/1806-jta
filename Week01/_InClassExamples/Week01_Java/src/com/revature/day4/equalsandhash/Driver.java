package com.revature.day4.equalsandhash;

import java.util.HashSet;
import java.util.Set;

public class Driver {

	public static void main(String[] args) {
		Person p1 = new Person("Bobbert", 78);
		Person p2 = new Person("Bobbert", 78);
		System.out.println(p1 == p2);		//false
		System.out.println(p1.equals(p2));	//true
		
		Set<Person> persons = new HashSet<>();
		persons.add(p1);
		persons.add(p2);
		persons.add(new Person("Bobbert", 78));
		System.out.println(persons.size()); //
		System.out.println(persons);
		
		
	}

}
