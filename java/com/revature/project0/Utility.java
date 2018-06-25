package com.revature.project0;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

public class Utility {
	private static Logger logger = Logger.getLogger(Utility.class);
	public static User retrieveUser(String username) {
		User u1 = null;
		try {
			FileInputStream file = new FileInputStream(username + ".ser");
			ObjectInputStream in = new ObjectInputStream(file);

			u1 = (User) in.readObject();

			in.close();
			file.close();

		}

		catch (IOException ex) {
			logger.info("IOException is caught");
			System.out.println("Incorrect username or password");
		}

		catch (ClassNotFoundException ex) {
			logger.info("ClassNotFoundException is caught");
			System.out.println("Incorrect username or password");
		}
		return u1;
	}


	public static void persistObj(User u) {
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(new FileOutputStream(u.getUsername()
					+ ".ser"));
			oos.writeObject(u); // Serialize
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {

			}
		}
	}
}
