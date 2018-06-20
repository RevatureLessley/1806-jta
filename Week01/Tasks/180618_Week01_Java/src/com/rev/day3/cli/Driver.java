package com.rev.day3.cli;

public class Driver {
	public static void main(String[] args) {
		
		System.out.println(args.length);
		
		for(String s : args) {
			System.out.println(s);
		}
		
	}
}
