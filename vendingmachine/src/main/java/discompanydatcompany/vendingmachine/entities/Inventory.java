package discompanydatcompany.vendingmachine.entities;

import java.io.Serializable;
import java.util.TreeMap;

import discompanydatcompany.vendingmachine.entities.Item;
import discompanydatcompany.vendingmachine.entities.StockItem;

public class Inventory implements Serializable {

    private TreeMap<String, StockItem> inventory;
    
    public Inventory() {
    	this.inventory = new TreeMap<String, StockItem>();
    }

    public Inventory(TreeMap<String, StockItem> inventory) {
    	this.inventory = inventory;
    }

    public boolean addStockItem(String key, StockItem stockItem) {
	
		boolean isSuccess = false;
	
		if (this.inventory.containsKey(key) == true ) {
		    return isSuccess;
		}
	
		isSuccess = true;
		this.inventory.put(key, stockItem);
	
		return isSuccess;
    }

    public TreeMap<String, StockItem> getInventory() {
    	return this.inventory;
    }

    public void setInventory(TreeMap<String, StockItem> inventory) {
    	this.inventory = inventory;
    }
    
    public StockItem getStockItemAt(String key) {
    	
    	if(!this.inventory.containsKey(key)) {
    		NoItem noItem = new NoItem();
    		StockItem stockItem = new StockItem(noItem, 0);
    		return stockItem;
    	}
    	
    	return this.inventory.get(key);
    }

    public void put(String key, StockItem stockItem) {
		if (stockItem != null) {
		    this.inventory.put(key, stockItem);
		}
    }
}
