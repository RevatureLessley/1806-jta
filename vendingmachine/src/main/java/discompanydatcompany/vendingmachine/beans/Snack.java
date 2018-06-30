package discompanydatcompany.vendingmachine.entities;

import java.io.Serializable;

public class Snack extends Item implements Serializable {
	
	public Snack(String name, int value, String description) {
		super(name, value, description, true);
	}
	
	public Snack() {
		super("Tasty Snack", 1, "A tasty edible food item.");
	}
	
	@Override
	public String use() {
		return "satisfied by munching on snacks";
	}
	
	@Override
	public String toString() {
		return "s";
	}

}
