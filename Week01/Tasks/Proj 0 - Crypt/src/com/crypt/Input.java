package com.crypt;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {
	private static Scanner scan = new Scanner(System.in);
	
	/**
	 * receives user input
	 * @return
	 */
	private static String scan() {
		String output = scan.nextLine();
		return output;
	}
	

	/**
	 * gets user input in the form of a byte
	 * @return
	 */
	public static byte getInputByte() {
		try {
			return Byte.parseByte(scan());
		}catch(InputMismatchException e){
			Crypt.logger.error("Input: Byte conversion failed: " + e.getMessage());
			return (byte) -1;
		}
	}
	
	public static String getInputString() {		
		try {
			return scan();
		}catch(InputMismatchException e){
			Crypt.logger.error("Input Reader: String conversion failed: " + e.getMessage());
			return "";
		}		
	}
	
	/**
	 * Accepts strings and returns an array of received strings
	 * @param ...things
	 * @return things
	 */
	public static String[] giveStringArray(String ...things) { return things; }
	
	/**
	 * Accepts an optional prescript as well as an array of strings to enumerate as
	 * menu options it also returns user input as a byte
	 * @param prescript
	 * @param responses
	 * @return
	 */
	public static byte showMenu(String prescript,String[] responses) {//input, Response
		Crypt.logger.trace("show message entered");
		System.out.println(prescript);
		System.out.println("Please choose one of the following to continue:");
		Integer i = 1;
		for(String s : responses) { System.out.println("(" + i.toString() + ")" + s); i++; }
		return getInputByte();
	}
}
