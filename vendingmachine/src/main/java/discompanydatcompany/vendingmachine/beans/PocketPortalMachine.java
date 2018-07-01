package discompanydatcompany.vendingmachine.beans;

import java.util.ArrayList;

public class PocketPortalMachine extends Item {

    public int capacity;
    public ArrayList<String> vendingMachines;

    public PocketPortalMachine() {
	super("Pocket Portal Machine",
	      0,
	      "Use this item to portal to another vending machine",
	      false);
	this.vendingMachines = new ArrayList<String>();
	this.capacity = 0;
    }

    public PocketPortalMachine(String name, int value, String description, boolean canSell, int capacity){
	super(name, value, description, canSell);
	this.capacity = capacity;
	this.vendingMachines = new ArrayList<String>();
    }
    
    public static PocketPortalMachine newUserPocketPortalMachine(User user, int slots) {
	String itemName = "Pocket Portal Machine";
	int value = 0;
	int capacity = 5;
	String description = "This is " + user.getName() + "'s pocket portal.";
	boolean canSell = false;
	PocketPortalMachine portalMachine = new PocketPortalMachine(itemName, value, description, canSell, capacity);
	return portalMachine;
    }

    public void addCapacity(int newSlots) {
	newSlots = Math.abs(newSlots);
	this.capacity += newSlots; 
    }
    
    @Override
    public String toString() {
    	return "#";
    }
}
