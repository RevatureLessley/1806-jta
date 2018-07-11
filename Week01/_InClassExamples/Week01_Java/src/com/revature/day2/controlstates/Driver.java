package com.revature.day2.controlstates;

public class Driver {

	public static void main(String[] args) {
		/*
		 * Control statements are any blocks of code that can control
		 * the flow of applications. IE. Different branches, loop, etc.
		 */
		
		//FOR LOOP
		//for(incrementor: condition: increment block)
		
		System.out.println("===FOR LOOP==="); 
		for(int i = 5; i > 0; i--){ //ugh
			System.out.println(i);
		}
		
		//POST FIX
		//i-- / i++ is an example of a POST FIX, what this does is use the variable
		//i then, once done, adds 1 to it.
		//PRE FIX
		//--i / ++i will be scenarios where when I is used, it will add 1/ subtract 1 first,
		//THEN use it.
		
		int j = 10;
		System.out.println(j++); //10
		System.out.println(++j); //12
		
		//NOTE: you can make an infinite loop via for(;;)
		
		//Enhanced for loop (foreach loop)
		
		//You make arrays by using a datatype with empty brackets.
		//Then instantiate with 'new' and indicate the size of the array. Array sizes
		//can NOT be changed.
		//Arrays in java are 0 index based. IE, the first number is at index 0;
		//And in the below example, the LAST number is in index 4.
		System.out.println("====foreach loop====");
		int[] nums = new int[5];
		for(int num : nums){ //noice
			System.out.println(num);
		}
		
		int[] nums2 = {1,2,3,4,5};
		for(int num : nums2){
			System.out.println(num);
		}
		
		//WHILE LOOP
		System.out.println("=====While loop=====");
		
		int k = 5;
		while(k > 0){
			k--;
			System.out.println("Mhm");
		}
		System.out.println("Broke out of the loop!");
		
		//DO-While LOOP
		//Execute once gauranteed, then check condition
		System.out.println("=====DO-WHILe LOOP====");
		k = 5;
		do{
			System.out.println("MHM");
			k--;
		}while(k > 0 && k < 5 );
		
		System.out.println("====if statements====");
		int grade = 95;
		if(grade>90){
			System.out.println("GREAT SCORE!");
		}else if(grade>59){ //else if is its own entity.
			System.out.println("Cutting it close dont'cha think?");
		}else{
			System.out.println("get out of here...");
		}
		
		
		System.out.println("=====SWITCH=====");
		int rando = (int)(Math.random()*0);
		System.out.println(rando);
		switch(rando){
		default: //Default gets used when no other case gets matched.
			System.out.println("Monday's quiz will be excruciating");
			break; //break statements take control out of the current loop
				   //please NOTE: the loop in this case is the SWITCH statement, not the case.
				   //Look for curly braces.
		case 0:
			System.out.println("Monday's quiz will be sort of hard.");
			break;
		case 1:
			System.out.println("Monday's quiz will be okay.");
			break;
		case 2:
			System.out.println("Monday's quiz will easy.");
			break;
		}
		
		for(int m = 0; m < 50; m++){
			if(m==30) break; //If statement uses only one line, you dont need curly braces
			if(m%2==1) continue; //Skips directly to the next loop iteration.
			System.out.println(m);
		}



	}
}
