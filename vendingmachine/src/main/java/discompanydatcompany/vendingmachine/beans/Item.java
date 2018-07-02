package discompanydatcompany.vendingmachine.beans;

import java.io.Serializable;

public class Item implements Serializable {

    protected String name;
    protected int value;
    protected boolean cannotSell;
    protected String description;

    public Item(String name, int value, String description) {
		this.name = name;
		this.value = value;
		this.description = description;
		this.cannotSell = false;
    }

    public Item(String name, int value, String description, boolean canSell) {
		this.name = name;
		this.value = value;
		this.description = description;
		this.cannotSell = canSell;
    }

    public String getName() {
    	return this.name;
    }

    public void setName(String name) {
    	this.name = name;
    }

    public int getValue() {
    	return this.value;
    }

    public void setValue(int value) {
    	this.value = value;
    }

    public String getDescription() {
    	return this.description;
    }

    public void setDescription(String description) {
    	this.description = description;
    }
    
    public String use() {
    	return "Nothing happened.\n";
    }
    
    public boolean canSell() {
    	return this.cannotSell;
    }
    
    public String isSellable() {
    	String sellable = this.cannotSell == true ? "No" : "Yes";
    	return sellable;
    }

    @Override
    public String toString() {
	return "_";
    }
}
