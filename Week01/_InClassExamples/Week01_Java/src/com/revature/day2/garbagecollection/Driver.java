package com.revature.day2.garbagecollection;

public class Driver {
	/*
	 * You can NOT force garbage collection. You can only request it.
	 * You can request via the system.gc() method.
	 */
	public static void main(String[] args) {
		for(int i = 0; i < 1000000; i++){
			CrumpledNewspaper cn =  new CrumpledNewspaper(i);
			System.gc();
		}
	}

}
