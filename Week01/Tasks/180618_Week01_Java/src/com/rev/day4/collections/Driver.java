package com.rev.day4.collections;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ArrayList l1 = new ArrayList();
		l1.add("Bobbert");
		l1.add(12);
		l1.add(false);
		
		List<Integer> ints = new ArrayList<>();
		ints.add(1);
		ints.add(4);
		ints.add(15);
		
		ints.remove((Integer)15);

		System.out.println(ints);
	}

}
