package com.rev.day3.serialization;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Driver {

	public static final String FILE_NAME = "company.ser";

	public static void main(String[] args) {

		Employee[] emps = { new Employee("a", "1", "desk", "777"), new Employee("b", "2", "desk", "777"),
				new Employee("c", "3", "boss guy", "777") };

		Company company = new Company("Revature", emps);
		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME));
			oos.writeObject(company);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (oos != null) {
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
