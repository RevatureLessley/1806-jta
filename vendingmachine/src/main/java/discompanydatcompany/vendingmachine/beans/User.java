package discompanydatcompany.vendingmachine.beans;

import java.io.Serializable;
import java.lang.StringBuffer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

import discompanydatcompany.vendingmachine.beans.Inventory;
import discompanydatcompany.vendingmachine.beans.NoItem;

public class User implements Serializable {

    private String name;
    private String password;
    private Inventory inventory;
    private String aboutMe;
    private String loginUUID;
    private HashSet<String> status;
    private int cash;
    private String locationUUID;
    private boolean enabled;

    public User() {
		this.loginUUID = String.valueOf(UUID.randomUUID());
		this.name = "No name set.";
		this.password = "password";
		this.aboutMe = "No about me set.";
		this.inventory = buildInventory(); 
		this.status = new HashSet<String>();
		this.cash = 100;
		this.locationUUID = "";
		this.enabled = false;
    }
    
    public User(String name, String password, String aboutMe) {
		this.loginUUID = String.valueOf(UUID.randomUUID());
		this.name = name;
		this.password = password;
		this.aboutMe = aboutMe;
		this.inventory = buildInventory(); 
		this.status = new HashSet<String>();
		this.cash = 100;
		this.locationUUID = "";
		this.enabled = false;
    }
    
    public User(String name, String password, String aboutMe, boolean enabledFlag) {
		this.loginUUID = String.valueOf(UUID.randomUUID());
		this.name = name;
		this.password = password;
		this.aboutMe = aboutMe;
		this.inventory = buildInventory(); 
		this.status = new HashSet<String>();
		this.cash = 100;
		this.locationUUID = "";
		this.enabled = enabledFlag;
    }

    /**
     * Builds starter Inventory. Where the inventory's max capacity is 5.
     *
     *
     */
    private Inventory buildInventory() {
		Inventory inventory = new Inventory();
		
		for (int i = 0; i < 5; i++) {
		    NoItem noItem = new NoItem();
		    StockItem stockItem = new StockItem(noItem, 1);
		    inventory.put(String.valueOf(i), stockItem);
		}

		return inventory;
    }

    /**
     * Return the name of the user
     *
     * @return String the name of the user
     */
    public String getName() {
    	return this.name;
    }

    /**
     * Modifies the name of the user
     *
     * @param name new name.
     */
    public void setName(String name) {
    	this.name = name;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
    public void setPassword(String password) {
    	this.password = password;
    }

    /**
     * Returns the About Me description of a user.
     * 
     * @return String the about me description of the user.
     */
    public String getAboutMe() {
    	return this.aboutMe;
    }

    /**
     * Modifies the AboutMe description of the user.
     *
     * @param aboutMe new about me
     */
    public void setAboutMe(String aboutMe) {
    	this.aboutMe = aboutMe;
    }

    /**
     * Returns the login UUID of the user.
     *
     * @return String the user's login UUID.
     */
    public String getLoginUUID() {
    	return this.loginUUID;
    }

    /**
     * Modify the login UUID of the user
     *
     * @param loginUUID new loginUUID String
     */
    public void setLoginUUID(String loginUUID) {
    	this.loginUUID = loginUUID;
    }
    
    public void useItem(int pocketNumber) {
    	if (pocketNumber <= 0 || pocketNumber > 6) {
    		return;
    	} else {
    		String key = String.valueOf(pocketNumber - 1);
    		StockItem stockItem = this.inventory.getStockItemAt(key);
    		int quantityRemainingAfterUse = stockItem.getStockCount() - 1;
    		Item item = stockItem.getItem();
    		
    		if (item instanceof NoItem) {
    			System.out.println(item.use());
    			return;
    		} else if (quantityRemainingAfterUse <= 0){
    			
    			// Use item
    			// Add item status result to status
    			this.status.add(item.use());
    			
    			// After use
    			item = new NoItem();
    			stockItem = new StockItem(item, 1);
    			this.inventory.put(key, stockItem);
    			return;
    		} else {
    			
    			// Use item
    			// Add item status result to status
    			this.status.add(item.use());
    			
    			// Decrement item in stock
    			stockItem.takeFromStock(1);
    			return;
    		}
    	}
    }
    
    public void addItem(StockItem stockItem) {
    	
    	for (int i = 0; i < 5; i++) {
    		String key = String.valueOf(i);
    		Item currentItem = this.inventory.getStockItemAt(key).getItem();
    		
    		if (currentItem instanceof NoItem) {
    			this.inventory.put(key, stockItem);
    			return;
    		}
    	}
    	
    	System.out.println("Inventory is full. User your items!");
    }
    
    public void getStatus() {
    	if (this.status.size() == 0) {
    		System.out.println("You are currently perusing a vending machine.");
    	} else {
    		for (String status : this.status) {
    			System.out.println(status);
    		}
    	}
    }
    
    public Inventory getInventory() {
    	return this.inventory;
    }
    
    public int getCash() {
    	return this.cash;
    }
    
    public void setCash(int ledger) {
    	this.cash += ledger;
    }
    
    public String getLocation() {
    	return this.locationUUID;
    }
    
    public void setLocation(String locationUUID) {
    	this.locationUUID = locationUUID;
    }
    
    public boolean getEnabled() {
    	return this.enabled;
    }
    
    public void setEnabled(boolean isEnabled) {
    	this.enabled = isEnabled;
    }

    public boolean isEnabled() {
    	return this.enabled;
    }
    
    public void enableAccount() {
    	this.enabled = true;
    }
    
    public void disableAccount() {
    	this.enabled = false;
    }
    public String isActive() {
    	String result = this.enabled == true ? "Y" : "N";
    	return result;
    }
    @Override 
    public String toString() {
    	StringBuffer stringBuffer = new StringBuffer("{ USER: " + this.name + " " + "ITEMS: ");
    	
    	for (int i = 0; i < 5; i++) {
    		String key = String.valueOf(i);
    		Item currentItem = this.inventory.getStockItemAt(key).getItem();
    		
    		String slotNumber = " [" + String.valueOf(i + 1) + "]";
    		String itemName = currentItem.getName();
    		stringBuffer.append(slotNumber + itemName);
    	}
    	
    	stringBuffer.append(" }");
    	
    	return stringBuffer.toString();
    }
}