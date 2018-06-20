package com.revature.day3.serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class CompanyDeserializer {

	public static void main(String[] args) {
		Company myComp = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("company.ser"));
			myComp = (Company)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(myComp);
		
		System.err.println(myComp.getEmployees()[3]);
	}

}
