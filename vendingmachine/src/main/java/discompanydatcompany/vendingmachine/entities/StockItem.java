package discompanydatcompany.vendingmachine.entities;

import java.io.Serializable;

import discompanydatcompany.vendingmachine.entities.Item;

public class StockItem implements Serializable {

    private final Item item;
    private int stockCount;

    /**
     * Represents the quantity of an item in stock inventory.
     *
     */
    public StockItem (Item item, int initialInventoryCount) {

	this.item = item;
	this.stockCount = initialInventoryCount;
    }
    
    public StockItem() {
    	this.item = new NoItem();
    	this.stockCount = 1;
    }

    /**
     * Get the Item of the StockItem object.
     *
     * @return this.item the Item.
     */
    public Item getItem() {

	return this.item;
    }

    /**
     * Get the number of items left in stock.
     *
     * @return int the number of items left in stock.
     */
    public int getStockCount() {

	return this.stockCount;
    }

    /**
     * Takes item from stock. This function returns the number of remaining
     * items in the stock if @quantity is greater than the number
     * of remaining items. Returns 0 if zero items are remaining.
     *
     * @param quantity
     * @return int number of items taken from stock. 
     */
    public int takeFromStock(int quantity) {	
		if (this.stockCount == 0) {
	
		    return 0;
	
		} else if (quantity > this.stockCount) {
	
		    int allRemaining = this.stockCount;
	
		    this.stockCount = 0;
		    return allRemaining;
		} else {
	
		    this.stockCount -= quantity;
		    return this.stockCount;
		}
    }

    /**
     * Adds @quantity to the stock
     *
     * @param quantity number of items to add
     * @param int new
     */
    public int addToStock(int quantity) {
		if (quantity <= 0) {
		    return this.stockCount;
		} else {
		    this.stockCount += quantity;
		    return this.stockCount;
		}
    }

    /**
     * Returns the Item value
     *
     * @return int the new stock count.
     */
    public int getItemValue() {
    	return this.item.getValue();
    }

    /**
     * Returns a quote of purchasing @quantity of items
     * 
     * @param quantity quantity of items to purchase.
     * @return int price quote.
     */
    public int getQuote(int quantity) {
		if (quantity < 0) {
		    return 0;
		} else {
		    return quantity * this.getItemValue();
		}
    }
}
