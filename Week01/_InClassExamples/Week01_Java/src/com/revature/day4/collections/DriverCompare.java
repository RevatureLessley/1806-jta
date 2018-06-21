package com.revature.day4.collections;

import java.util.Arrays;
import java.util.List;

public class DriverCompare {

	public static void main(String[] args) {
		List<Person> p = Arrays.asList(
					new Person("Bobbert", 12),
					new Person("Adam", 98),
					new Person("Dorothy", 66)
				);
		
		for(Person q: p){
			System.out.println(q);
		}
		p.sort(null); //sort naturally
		System.out.println("====Sorted====");
		for(Person q: p){
			System.out.println(q);
		}
		System.out.println("====Comparator=====");
		p.sort(new PersonAgeComparator());
		for(Person q: p){
			System.out.println(q);
		}
	}

}
