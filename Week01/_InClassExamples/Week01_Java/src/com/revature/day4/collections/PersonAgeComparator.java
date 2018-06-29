package com.revature.day4.collections;

import java.util.Comparator;

public class PersonAgeComparator implements Comparator<Person>{

	@Override
	public int compare(Person p1, Person p2) {
		/*
		 * if(p1.getAge() < p2.getAge()) return -1;
		 * if(p1.getAge() > p2.getAge()) return 1;
		 * return 0;
		 */
		
		return p1.getAge() - p2.getAge();
	}

}
