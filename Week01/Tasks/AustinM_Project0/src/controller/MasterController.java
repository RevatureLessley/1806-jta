package controller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;

public class MasterController implements Serializable{
	
	private static final long serialVersionUID = -8925159279195878612L;
	private static final String USER_DATA_FILE = "userdata.dat";
	private static MasterController masterController;
	
	public UserController userController;
	public AccountController accountController;
	
	private static boolean freeData = false;
	
	private MasterController() {
		userController = new UserController();
		accountController = new AccountController();
	}
	
	private static MasterController getMaster(){
		if(masterController == null) {
			if(!readData())
				masterController = new MasterController();			
		}
		
		return masterController;
	}
	
	public static UserController getUserController() {
		return getMaster().userController;
	}

	public static AccountController getAccountController() {
		return getMaster().accountController;
	}
	
	public static void setFreeData() {
		freeData = true;
	}
	
	public static void shutdown() {
		if(freeData) {
			try {
				Files.delete(new File(USER_DATA_FILE).toPath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else {
			writeData();
		}
	}

	
	public static void writeData() {

		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE));
			oos.writeObject(masterController);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			if (oos != null)
				oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static boolean readData() {

		ObjectInputStream ois = null;
		boolean result = false;
		
		try {
			ois = new ObjectInputStream(new FileInputStream(USER_DATA_FILE));
			masterController = (MasterController) ois.readObject();
			result = true;
		} catch (FileNotFoundException e) {
			System.out.println("no previous user data");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try {
			if (ois != null)
				ois.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;

	}
	
}
