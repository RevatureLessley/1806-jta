package com.crypt;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
	
	static Scanner scan = new Scanner(System.in);
	
	private static String scan() {
		String output = scan.nextLine();
		return output;
	}
	
	public static byte getInputByte() {
		try {
			return Byte.parseByte(scan());
		}catch(InputMismatchException e){
			System.out.println("Input: Byte conversion failed");
			return (byte) -1;
		}
	}
	
	public static String getInputString() {		
		try {
			return scan();
		}catch(InputMismatchException e){
			System.out.println("Input Reader: String conversion failed");
			return "";
		}		
	}
	
	public static String[] giveStringArray(String ...things) { return things; }
	
	public static byte showMenu(String prescript,String[] responses) {//input, Response
		System.out.println(prescript);
		System.out.println("Please choose one of the following to continue:");
		Integer i = 0;
		for(String s : responses) { System.out.println("(" + i.toString() + ")" + s); i++; }
		return getInputByte();
	}
}
