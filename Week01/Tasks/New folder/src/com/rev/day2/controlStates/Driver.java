package com.rev.day2.controlStates;

public class Driver {
	public static void main(String[] args) {

		for (int i = 5; i > 0; i--) {
			System.out.println(i);
		}
		
		
		int[] nums = new int[5];
		
		for(int i : nums){
			System.out.println(i);
		}
		
		int[] nums2 = {1, 2, 3, 4, 5};
		
		for(int i : nums2) {
			System.out.println(i);
		}
		
		int k = 0;
		
		while(k < 10) {
			System.out.print("lo");
			k++;
		}
		System.out.println("l");
		
		int rando = (int)(Math.random()*4);
		
		switch(rando) {
		case 0:
			System.out.println("a");
			break;
		case 1: 
			System.out.println("b");
			break;
		case 2: 
			System.out.println("c");
			break;
		default: 
			System.out.println("d");
		}
	}
}
