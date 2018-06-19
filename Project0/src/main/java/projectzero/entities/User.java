package projectzero.entities;

import projectzero.entities.Inventory;
import projectzero.entities.NoItem;

public class User {

    private String name;
    private Inventory inventory;
    private String aboutMe;
    private String loginUUID;

    public User() {
	this.loginUUID = null;
	this.name = "No name set.";
	this.aboutMe = "No about me set.";
	this. inventory = buildInventory(); 
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
}

