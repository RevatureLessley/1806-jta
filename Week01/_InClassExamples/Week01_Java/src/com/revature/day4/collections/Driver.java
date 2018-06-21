package com.revature.day4.collections;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		/*
		 * collection: A term for data structures
		 * Collection: The API for java collections
		 * Collections: Utility for interacting with collections
		 */
		
		//Note: Collections only deal with objects! Therefore primitive values are not
		//allowed. But, they will still work because of autoboxing.
		
		System.out.println("=====LISTS=====");
		
		ArrayList l1 = new ArrayList();
		l1.add("Bobbert");
		l1.add(12);
		l1.add(false);
		System.out.println(l1);
		
		/*
		 * Generics
		 * -enforce a type for a collection
		 * 		-This type, could be used as a variable, enforcing dynamically
		 * 			typed lists.
		 * 		-In addition to making a strong collection, you can also use 
		 * 			generics to dynamically type a colleciton at runtime.
		 * 				(Parametric polymorphism)
		 */
		
		//At what line number will this app crash
		//Or will it crash at all?
		List<Integer> ints = new ArrayList<>();
		ints.add(15);
		ints.add(new Integer(20));
		ints.add(5);
		System.out.println(ints);
		ints.remove(new Integer(15));
		System.out.println(ints.remove("ryan"));
		System.out.println(ints);
	}

}
