package com.revature.day2.accessmods2;

import com.revature.day2.accessmods.Silence;

//In order to access classes from other packages, you need to IMPORT them.
//NOTE: ctrl + shift + o auto imports missing packages.

public class Driver extends Silence{

	public static void main(String[] args) {
		Silence silence = new Silence();
		silence.output();
		
		System.out.println("====ACESS VIA METHOD OUTSIDE CLASS. OUTSIDE PACKAGE====");
		
		//System.out.println(silence.priv);
		//System.out.println(silence.def);
		//System.out.println(silence.prot);
		System.out.println(silence.pub); //ONLY public is allowed outside of package wihtout
										 //Inheritance.
		Driver d = new Driver();
		
		//System.out.println(d.priv);
		//System.out.println(d.def);
		System.out.println(d.prot); //Since driver as a class inherits from Silence, we have
									//Access to protected elements as well.
		System.out.println(d.pub);
	}

}
