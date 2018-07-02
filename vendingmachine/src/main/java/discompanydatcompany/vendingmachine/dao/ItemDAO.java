package discompanydatcompany.vendingmachine.dao;

import java.util.List;

import discompanydatcompany.vendingmachine.beans.Item;

public interface ItemDAO {
	public boolean addItem(Item item);
	public List<Item> getAllItems();
	public boolean deleteItem(Item item);
	public boolean updateItem(Item item);
}
