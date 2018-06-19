package com.revature.day2.accessmods;

public class Driver {

	public static void main(String[] args) {
		Silence silence = new Silence();
		System.out.println("=====Printing via the class itself=====");
		silence.output();
		
		System.out.println(silence.pub);
		System.out.println(silence.prot);
		System.out.println(silence.def);
		//System.out.println(silence.priv); ONLY ACCISSIBLE WITHIN THE CLASS
	}

}
