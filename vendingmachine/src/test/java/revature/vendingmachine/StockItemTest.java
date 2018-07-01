package revature.vendingmachine;

import discompanydatcompany.vendingmachine.beans.BottledWater;
import discompanydatcompany.vendingmachine.beans.Item;
import discompanydatcompany.vendingmachine.beans.NoItem;
import discompanydatcompany.vendingmachine.beans.StockItem;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StockItemTest extends TestCase {

	/**
	 * Creates test case for Stock Items
	 * 
	 * @param testName name of the test case
	 */
	public StockItemTest (String testName) {
		super(testName);
	}
	
	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(StockItemTest.class);
	}
	
	/**
	 * StockItem default constructor returns
	 * a NoItem with a stock count of 1.
	 */
	public void testDefaultConstructor() {
		StockItem stockItem = new StockItem();
		
		if ((stockItem.getItem() instanceof NoItem) && 
				(stockItem.getStockCount() == 1)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
	
	/**
	 * Test StockItem(Item item, int initialInventoryCount)
	 * Test adds a NoItem item with a quantity of 20
	 * Test evaluates if stockItem is of type NoItem and
	 * has a stock count of 20
	 */
	public void testItemQuantityConstructor() {
		Item noItem = new NoItem();
		int stockCount = 20;
		
		StockItem stockItem = new StockItem(noItem, stockCount);
		
		if ((stockItem.getItem() instanceof NoItem) && 
				(stockItem.getStockCount() == stockCount)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
	
	/**
	 * Test StockItem(Item item, int initialInventoryCount)
	 * Test StockItem.takeFromStock(int quantity)
	 * Test adds a NoItem item with a quantity of 20
	 * Test evaluates if taking 1 item from a NoItem
	 * stock count of 20 decrements the stock count by 1
	 * to 19.
	 */
	public void testTakeFromStock() {
		Item noItem = new NoItem();
		int stockCount = 20;
		
		StockItem stockItem = new StockItem(noItem, stockCount);
		int remaining = stockItem.takeFromStock(1);
		
		if (stockItem.getStockCount() == 19 && remaining == 19) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
	
	/**
	 * Test StockItem(Item item, int initialInventoryCount)
	 * Test StockItem.getItemValue()
	 * Test StockItem.getQuote(int quantity)
	 * Test adds a BottledWater item with a quantity of 20
	 * Test evaluates if StockItem.getItemValue() is
	 * equivalent to the StockItem.getQuote() function
	 * with a quantity size of 1.
	 */
	public void testItemValueVersusQuotePrice() {
		Item bottledWater = new BottledWater();
		int stockCount = 20;
		
		StockItem stockItem = new StockItem(bottledWater, stockCount);
		
		if (stockItem.getItemValue() == stockItem.getQuote(1)) {
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}
}
