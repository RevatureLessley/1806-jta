package discompanydatcompany.vendingmachine.utilities;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import discompanydatcompany.vendingmachine.beans.BottledWater;
import discompanydatcompany.vendingmachine.beans.Gum;
import discompanydatcompany.vendingmachine.beans.Item;
import discompanydatcompany.vendingmachine.beans.MysteriousPocketPortalMachine;
import discompanydatcompany.vendingmachine.beans.Snack;
import discompanydatcompany.vendingmachine.beans.User;
import discompanydatcompany.vendingmachine.beans.UserList;
import discompanydatcompany.vendingmachine.beans.VendingMachine;
import discompanydatcompany.vendingmachine.beans.VendingMachineList;
import discompanydatcompany.vendingmachine.dao.InventoryDAO;
import discompanydatcompany.vendingmachine.dao.InventoryDAOImpl;
import discompanydatcompany.vendingmachine.dao.ItemDAO;
import discompanydatcompany.vendingmachine.dao.ItemDAOImpl;
import discompanydatcompany.vendingmachine.dao.SaveFileDAO;
import discompanydatcompany.vendingmachine.dao.SaveFileDAOImpl;
import discompanydatcompany.vendingmachine.dao.UserDAO;
import discompanydatcompany.vendingmachine.dao.UserDAOImpl;
import discompanydatcompany.vendingmachine.dao.VendingMachineDAO;
import discompanydatcompany.vendingmachine.dao.VendingMachineDAOImpl;

public class SaveFile implements Serializable {
	
	private UserList userList;
	private VendingMachineList vendingMachineList;
	
	public SaveFile() {
		this.userList = new UserList();
		this.vendingMachineList = new VendingMachineList();
	}
	
	public SaveFile(UserList userList, VendingMachineList vendingMachineList) {
		this.userList = userList;
		this.vendingMachineList = vendingMachineList;
	}
	
	public UserList getUserList() {
		return userList;
	}
	
	public void setUserList(UserList userList) {
		this.userList = userList;
	}
	
	public VendingMachineList getVendingMachineList() {
		return this.vendingMachineList;
	}
	
	public void setVendingMachineList(VendingMachineList vendingMachineList) {
		this.vendingMachineList = vendingMachineList;
	}
	
	public void writeToFile(String file) throws IOException {
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
		objectOutputStream.writeObject(this);
	}
	
	public SaveFile readFromFile(String file) throws IOException, ClassNotFoundException {
		ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
		SaveFile saveFile = (SaveFile) objectInputStream.readObject();
		return saveFile;
	}
	
	public void loadFromFile(String file) {
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
			SaveFile saveFromFile = (SaveFile) objectInputStream.readObject();
			VendingMachineList vendingMachineListFromFile = saveFromFile.getVendingMachineList();
			UserList userListFromFile = saveFromFile.getUserList();
			if (vendingMachineListFromFile != null) {
				this.setVendingMachineList(vendingMachineListFromFile);
			}
			if (userListFromFile != null) {
				this.setUserList(userListFromFile);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean writeToDataBase(SaveFile saveFile, VendingMachineList vendingMachineList, UserList userList, ArrayList<Item> itemsList) {
		
		InventoryDAO inventoryDAO = new InventoryDAOImpl();
		UserDAO userDAO = new UserDAOImpl();
		VendingMachineDAO vendingMachineDAO = new VendingMachineDAOImpl();
		ItemDAO itemDAO = new ItemDAOImpl();
		SaveFileDAO saveFileDAO = new SaveFileDAOImpl();
		
		System.out.println("Barcoding and packaging all items");
		ArrayList<Item> items = new ArrayList<Item>();
		BottledWater bottledWater = new BottledWater();
		Gum gum = new Gum();
		Snack snack = new Snack();
		MysteriousPocketPortalMachine teleporter = new MysteriousPocketPortalMachine();
		items.add(gum);
		items.add(snack);
		items.add(bottledWater);
		items.add(teleporter);
		
		for (Item item : items) {
			itemDAO.addItem(item);
		}
		
		System.out.println("Items scanned.");
		
		System.out.println("Splicing inventories.");
		
		for (User user : userList.getUsers()) {
			inventoryDAO.preparedUpdate(user);
		}
		
		for (VendingMachine vendingMachine : vendingMachineList.getVendingMachines()) {
			inventoryDAO.preparedUpdate(vendingMachine);
		}
		System.out.println("Inventories recorded.");
		
		System.out.println("Collecting vending machines.");
		
		for (VendingMachine vendingMachine : vendingMachineList.getVendingMachines()) {
			vendingMachineDAO.preparedUpdate(vendingMachine);
		}
		
		System.out.println("Vending machines stowed");
		
		System.out.println("Allocating space for users");
		
		for (User user : userList.getUsers()) {
			userDAO.preparedUpdate(user);
		}
		
		System.out.println("Users stowed.");
		
		System.out.println("Saving ...");
		
		saveFileDAO.preparedUpdate(saveFile);
		
		System.out.println("Content saved.");
				
		return true;
	}
}
