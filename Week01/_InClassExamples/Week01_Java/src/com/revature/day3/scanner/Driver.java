package com.revature.day3.scanner;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Driver {
	static Scanner in = null;
	
	public static void main(String[] args) {
		/*
		 * A scanner is a class used to parse string.
		 * e.g. parse numbers/letters/etc from strings easily.
		 * 
		 * More commonly, used for user input in console applicaitons.
		 * By default, scanner will delimit input by spaces ' '.
		 * (Delimit meaning how it separates the values within the input.
		 * 
		 * NOTE: System.in is used to take user input;
		 */
		
		in = new Scanner(System.in);
		System.out.println("Input anything, seriously, anything.\n");
//		System.out.println(in.nextLine());
		
		int i;
		while(true){
			String input = in.nextLine();
			if(input.equals("exit")){
				System.out.println("Later");
				break;
			}else{
				System.out.println("You said: " + input);
			}
		}
		
		Driver d = new Driver();
		d.scannerExample2();
	}
	
	public void scannerExample2(){
		StringTokenizer st;
		int i;
		breakhere:
		while(true){
				
			String input = in.nextLine();
			st = new StringTokenizer(input);
			while(st.hasMoreTokens()){
				String token = st.nextToken();
				try{
					i = Integer.parseInt(token);
					if(i == 5){
						break breakhere;
					}
					else{
						System.out.println("That's not the secret number!");
					}
				}catch(NumberFormatException e){
					System.out.println("YO, '" + token + "' isn't a number!");
				}
			}
			
		}
		
		
		if(in != null){
			in.close();
		}
	}

}
