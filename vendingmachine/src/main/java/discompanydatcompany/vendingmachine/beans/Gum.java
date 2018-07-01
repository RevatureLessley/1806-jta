package discompanydatcompany.vendingmachine.beans;

import java.io.Serializable;

public class Gum extends Item implements Serializable{
	
	public Gum(String flavor, int value, String description) {
		super(flavor, value, description, true);
	}
	
	public Gum() {
		super("Basic Gum", 1, "This gum is basic.", true);
	}
	
	@Override
	public String use() {
		return "Your taste buds react as you are chewing " + this.getName() + ".";
	};
	
	@Override
	public String toString() {
		return "g";
	}
}
