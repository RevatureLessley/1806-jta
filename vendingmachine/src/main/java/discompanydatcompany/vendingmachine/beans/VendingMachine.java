package discompanydatcompany.vendingmachine.entities;

import java.util.HashMap;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import discompanydatcompany.vendingmachine.entities.Inventory;
import discompanydatcompany.vendingmachine.entities.NoItem;
import discompanydatcompany.vendingmachine.entities.StockItem;
import discompanydatcompany.vendingmachine.utilities.Printing;

public class VendingMachine implements Serializable {

    private String vendingMachineName;
    private String adminName;
    private String adminUUID;
    private String message;
    private Inventory inventory;
    private String vendingMachineId;

    /**
     * Instantiates a vending machine object.
     *
     * @param vendingMachineName vending machine name
     * @param adminName vending machine owner's name
     */
    public VendingMachine(String vendingMachineName, String adminName, String adminUUID) {
	
		this.vendingMachineName = vendingMachineName;
		this.adminName = adminName;
		this.adminUUID = adminUUID;
		this.message = "An empty vending machine.";
		this.inventory = VendingMachine.buildEmptyInventory();
	    this.vendingMachineId = String.valueOf(UUID.randomUUID());
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
			  String adminUUID,
			  String message) {
		this.vendingMachineName = vendingMachineName;
		this.adminName = adminName;
		this.adminUUID = adminUUID;
		this.message = message;
		this.inventory = VendingMachine.buildEmptyInventory();
		this.vendingMachineId = String.valueOf(UUID.randomUUID());
    }

    /**
     *
     *
     */
    public VendingMachine(String vendingMachineName,
			  String adminName,
			  String adminUUID,
			  Inventory inventory) {
		this.vendingMachineName = vendingMachineName;
		this.adminName = adminName;
		this.adminUUID = adminUUID;
		this.message = "";
		this.inventory = inventory;
		this.vendingMachineId = String.valueOf(UUID.randomUUID());
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
			  String adminUUID,
			  String message,
			  Inventory inventory,
			  String vendingMachineId) {
		this.vendingMachineName = vendingMachineName;
		this.adminName = adminName;
		this.adminUUID = adminUUID;
		this.message = message;
		this.inventory = inventory;
		this.vendingMachineId = vendingMachineId;
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
    
    public void setVendingMachineName(String name) {
    	this.vendingMachineName = name;
    }

    /**
     * Returns the Admin's (Owner's) name of the vending machine.
     *
     * @return this.adminName the owner's name of the vending machine.
     */
    public String getAdminName() {
    	return this.adminName;
    }
    
    public void setAdminName(String adminName) {
    	this.adminName = adminName;
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
    
    public String getVendingMachineId() {
    	return this.vendingMachineId;
    }
    
    public void setVendingMachineId(String vendingMachineId) {
    	this.vendingMachineId= vendingMachineId;
    }
    
    public String modifyStock(String key, StockItem stockItem) {
    	String result = "";
    	if (!this.inventory.getInventory().containsKey(key)) {
    		result = "Enter a combination A-D, 1-5.\nExamples: A1, D5, C2. ";
    	} else {
    		this.inventory.getInventory().put(key, stockItem);
    		result = "A quantity of " + stockItem.getStockCount() + " " + stockItem.getItem().getName()
    				+ " have been added to the vending machine name " + this.getVendingMachineName();
    	}
    	return result;
    }
    
    public String modifyStock(String key, int toAddOrRemove) {
    	String result = "";
    	
    	if (!this.inventory.getInventory().containsKey(key)) {
    		result = "Enter a combination A-D, 1-5.\nExamples: A1, D5, C2. ";
    	} else if (this.inventory.getInventory().get(key).getItem() instanceof NoItem) {
    		result = "There is no item stocked at " + key;
    	} else {
    		StockItem stockItem = this.inventory.getInventory().get(key);
    		int stockDifference = stockItem.getStockCount() + toAddOrRemove;
    		
    		// If the stock Difference is negative
    		if (stockDifference < 0) {
    			result = "Removing that many " + stockItem.getItem().getName() + "'s yields a negative value.";
    		} else if (stockDifference == 0) {
    			result = "All " + stockItem.getItem().getName() + "'s were removed.";
    			Item item = new NoItem();
    			stockItem = new StockItem(item, 1);
    			this.inventory.put(key, stockItem);
    		} else {
    			if (toAddOrRemove >= 0) {
    				result = stockItem.addToStock(toAddOrRemove) + " " + stockItem.getItem().getName()
    						+ "(s) was added.";
    			} else {
    				result = stockItem.takeFromStock(toAddOrRemove) + " " + stockItem.getItem().getName()
    						+ "(s) was removed.";
    			}
    		result = "A quantity of " + stockItem.getStockCount() + " " + stockItem.getItem().getName()
    				+ " have been added to the vending machine name " + this.getVendingMachineName();
    		}
    	}
    	
    	return result;
    }

    /**
     * Modifies the vending machine's message value
     * 
     * @param message
     */
    public void setMessage(String message) {
	this.message = message;
    }
    
    /**
     * returns a toString() equivalent of the VendingMachine object as an ArrayList<String>
     * 
     * @return returns a toString() equivalent of the VendingMachine object as an ArrayList<String>
     */
    public ArrayList<String> toArrayList() {
	 String machineName = Printing.paddedString(this.vendingMachineName, 20);
       String adminName = Printing.paddedString(this.adminName, 20);
       ArrayList<String> itemIcons = new ArrayList<String>();

       for (StockItem stockItem : this.getInventory().getInventory().values()) {
	   String itemIcon = stockItem.getItem().toString();
	   itemIcons.add(Printing.paddedString(itemIcon, 4));
       }
       
       ArrayList<String> printMachine = new ArrayList<String>();
       printMachine.add("=========================\n");
       printMachine.add("=[" + machineName +  "]==\n");
       printMachine.add("=[" + adminName +    "]==\n");
       printMachine.add("=========================\n");
       printMachine.add("==    A   B   C   D   ===\n");
       
       for (int i = 1, j = 0; i < 6; i++, j += 4) {
	   StringBuilder builder = new StringBuilder();
	   builder.append("== " + i + " ");

	   for (int k = j; k < j + 4; k++) {
	       builder.append(itemIcons.get(k));
	   }
	   
	   builder.append(" ===\n");
	   printMachine.add(builder.toString());
       }

       printMachine.add("=========================\n");
       printMachine.add("=========================\n");
       printMachine.add("=======[        ]========\n");
       printMachine.add("=========================\n");
       printMachine.add("=========================\n");
       printMachine.add("=========================\n\n");
       
       printMachine.add(Printing.rowLengthLimitedString(this.getMessage(), 36));
       
       return printMachine;
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
