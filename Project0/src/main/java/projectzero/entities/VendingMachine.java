package projectzero.entities;

import java.util.HashMap;
import java.util.ArrayList;

import projectzero.entities.Inventory;
import projectzero.entities.StockItem;
import projectzero.entities.NoItem;

import projectzero.utilities.Printing;

public class VendingMachine {

    private final String vendingMachineName;
    private final String adminName;
    private String message;
    private Inventory inventory;

    /**
     * Instantiates a vending machine object.
     *
     * @param vendingMachineName vending machine name
     * @param adminName vending machine owner's name
     */
    public VendingMachine(String vendingMachineName, String adminName) {
	
	this.vendingMachineName = vendingMachineName;
	this.adminName = adminName;
	this.message = "An empty vending machine.";
	this.inventory = VendingMachine.buildEmptyInventory();
    }

    /**
     * Instantiates a vending machine object.
     *
     * @param vendingMachineName vending machine name
     * @param adminName vending machine owner's name
     * @param message vending machine's welcome message
     */
    public VendingMachine(String vendingMachineName,
			  String adminName,
			  String message) {
	this.vendingMachineName = vendingMachineName;
	this.adminName = adminName;
	this.message = message;
	this.inventory = VendingMachine.buildEmptyInventory();
    }

    /**
     *
     *
     */
    public VendingMachine(String vendingMachineName,
			  String adminName,
			  Inventory inventory) {
	this.vendingMachineName = vendingMachineName;
	this.adminName = adminName;
	this.inventory = inventory;
    }
    
    /**
     * Creates an empty inventory with selections A1-D5; A-D and 1-5.
     *
     * @return inventory an empty vending machine inventory
     */
    public static Inventory buildEmptyInventory() {

	Inventory inventory = new Inventory();
	String[] columns = {"A", "B", "C", "D"};
	String[] rows = {"1", "2", "3", "4", "5"};

	for (String column : columns) {
	    for (String row: rows) {
		String key = column + row;
		Item noItem = new NoItem();
		StockItem stockItem = new StockItem(noItem, 0);
		inventory.put(key, stockItem);
	    }
	} 

	return inventory;
    }

    /** 
     * Return's the name of the vending machine.
     *
     * @return this.vendingMachineName the Vending Machine's name
     */
    public String getVendingMachineName() {

	return this.vendingMachineName;
    }

    /**
     * Returns the Admin's (Owner's) name of the vending machine.
     *
     * @return this.adminName the owner's name of the vending machine.
     */
    public String getAdminName() {

	return this.adminName;
    }

    public Inventory getInventory() {
	return this.inventory;
    }

    public void setInventory(Inventory inventory) {
	this.inventory = inventory;
    }

    public String getMessage() {
	return this.message;
    }

    public void setMessage(String message) {
	this.message = message;
    }
    
   @Override
   public String toString() {
       String machineName = Printing.paddedString(this.vendingMachineName, 20);
       String adminName = Printing.paddedString(this.adminName, 20);
       ArrayList<String> itemIcons = new ArrayList<String>();

       for (StockItem stockItem : this.getInventory().getInventory().values()) {
	   String itemIcon = stockItem.getItem().toString();
	   itemIcons.add(Printing.paddedString(itemIcon, 4));
       }
       
       StringBuffer printMachine = new StringBuffer(new String(
	                     "=========================\n"
	                   + "=[" + machineName +  "]==\n"
	                   + "=[" + adminName +    "]==\n"
	                   + "=========================\n"
			     + "==    A   B   C   D   ===\n"));
       
       for (int i = 1, j = 0; i < 6; i++, j += 4) {
	   
	   printMachine.append("== " + i + " ");

	   for (int k = j; k < j + 4; k++) {
	       printMachine.append(itemIcons.get(k));
	   }
	   
	   printMachine.append(" ===\n");
	   }

       printMachine.append("=========================\n"
			 + "=========================\n"
	                 + "=======[        ]========\n"
			 + "=========================\n"
			 + "=========================\n"
			 + "=========================\n\n");
       
       printMachine.append(Printing.rowLengthLimitedString(this.getMessage(), 36));
       
       return printMachine.toString();
   }
}
