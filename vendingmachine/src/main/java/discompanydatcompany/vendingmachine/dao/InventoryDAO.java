package discompanydatcompany.vendingmachine.dao;

import java.util.List;

import discompanydatcompany.vendingmachine.beans.Inventory;
import discompanydatcompany.vendingmachine.beans.User;
import discompanydatcompany.vendingmachine.beans.VendingMachine;

public interface InventoryDAO {
	public boolean addInventory(User user);
	public boolean addInventory(VendingMachine vendingMachine);
	public List<Inventory> getAllInventories();
	public boolean deleteInventory(String uuid);
	public boolean deleteInventory(User user);
	public boolean deleteInventory(VendingMachine vendingMachine);
	public boolean updateInventory(User user);
	public boolean updateInventory(VendingMachine vendingMachine);
	public boolean preparedUpdate(User user);
	public boolean preparedUpdate(VendingMachine vendingMachine);
}
