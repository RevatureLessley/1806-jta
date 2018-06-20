package com.revature.day3.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Driver {

	/*
	 * Serialization is where we take an object and convert it to a byte code format
	 * that represents the object and all the data within.
	 */
	public static void main(String[] args) {
		Employee[] emps = {
				new Employee("Ryan", "lessley", "bob@bob.bob", "trainer", "555-555-5552"),
				new Employee("Rysdsan", "less", "ds", "trainer", "555-555-5552"),
				new Employee("Ran", "is", "bob@bob.bob", "sd", "555-555-5552"),
				new Employee("Ryn", "more", "bob@bob.bob", "trainer", "555-555-5552"),
				new Employee("Ry", "lesley", "bob@bob.bob", "sd", "555-555-5552")
		};
		
		Company comp = new Company("Ryanture", emps);
		
		try{
			ObjectOutputStream oos = new ObjectOutputStream(
										new FileOutputStream("company.ser"));
			oos.writeObject(comp); //Serialize
		}catch(IOException e){
			e.printStackTrace();
		}
		
		System.out.println("END OF EXECUTION");

	}

}
