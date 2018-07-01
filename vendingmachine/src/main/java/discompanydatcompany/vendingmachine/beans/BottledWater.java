package discompanydatcompany.vendingmachine.beans;

import java.io.Serializable;

public class BottledWater extends Item implements Serializable {
	
	public BottledWater(String name, int value, String description) {
		super(name, value, description, true);
	}
	
	public BottledWater() {
		super("Bottled Water", 2, "A sealed bottle of water from a fancy-named fresh water lake.", true);
	}
	
	@Override
	public String toString() {
		return "b";
	}
	
	@Override
	public String use() {
		System.out.println("Mmm refreshing");
		return "You are sipping water from a bottle of " + this.name + ".";
	}
}
