package com.revature.project0.bankaccount;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Deserializer {
	public static void main(String[] args) {
		TransUnion myUnion = null;
		
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new FileInputStream("TransUnion.ser"));
			myUnion = (TransUnion)ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(myUnion);
		
	}
}
