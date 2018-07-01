package discompanydatcompany.vendingmachine.utilities;

import java.io.Serializable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import discompanydatcompany.vendingmachine.beans.UserList;
import discompanydatcompany.vendingmachine.beans.VendingMachineList;

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
}
