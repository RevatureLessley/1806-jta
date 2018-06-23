package bank;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream;

public class BankDeserializer {
	
	/**
	 * Deserialization method for a Bank object
	 * @return a new Bank object, if one doesn't exist in file
	 */
	public static Bank getBank() {
		Bank bank = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("bank.ser"));
			bank = (Bank)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			bank = new Bank("Chase", "1 Cricle Way", "555-555-5555");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bank;
	}
}
