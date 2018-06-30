package discompanydatcompany.vendingmachine.entities;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

public class MysteriousPocketPortalMachine extends PocketPortalMachine implements Serializable{

	public MysteriousPocketPortalMachine(String name, int value, String description) {
		super(name, 3, description, true, 1);
	}
	
	public MysteriousPocketPortalMachine() {
		super("Teleported", 3, "A mysterious pocket portal teleporter machine.", true, 1);
	}
	
	public String use() {
		for (int i = 0; i < 100; i++) {
			System.out.println(new String(new char[1000]).replace("\0", String.valueOf((char) (Math.random() * 500))));
		}
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Welcome back.");
		return "You are dazed and confused from having recently used a " + this.name + ".";
	}
	
	@Override
	public String toString() {
		return "?";
	}
}
