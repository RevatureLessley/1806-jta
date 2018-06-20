package com.rev.day2.garbageCollection;

public class Driver {

	public static void main(String[] args) {
		
		for (int i = 0; i < 200000; i++) {
			CrumpledNewsPaper c = new CrumpledNewsPaper(i);			
		}
	}

}
