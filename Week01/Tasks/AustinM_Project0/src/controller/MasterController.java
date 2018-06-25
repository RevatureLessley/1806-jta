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

import org.apache.log4j.Logger;

public final class MasterController implements Serializable {

	private static final long serialVersionUID = -8925159279195878612L;
	private static final String USER_DATA_FILE = "userdata.dat";
	private static MasterController masterController;

	public UserController userController;
	public AccountController accountController;

	private static Logger logger = Logger.getLogger(MasterController.class);

	private static boolean freeData = false;

	/**
	 * MasterController has an instance of UserController and AccountController. The
	 * MasterController follows the Singleton design pattern.
	 */
	private MasterController() {
		logger.info("New MasterController created");
		userController = new UserController();
		accountController = new AccountController();		
	}

	/**
	 * Gets the singleton instance of the master controller. This method is only
	 * used internally.
	 * <p>
	 * Attempts to read a MasterController instance the was previously written
	 * before creating a new one.
	 */
	private static MasterController getMaster() {
		if (masterController == null) {
			if (!readData())
				masterController = new MasterController();
		}

		return masterController;
	}
	
	/**
	 * Gets the static instance of UserController
	 */
	public static UserController getUserController() {
		return getMaster().userController;
	}

	/**
	 * Gets the static instance of AccountController
	 */
	public static AccountController getAccountController() {
		return getMaster().accountController;
	}

	/**
	 * Tells the MasterController not to write data on shutdown.
	 */
	public static void setFreeData() {
		freeData = true;
	}

	/**
	 * Handles end of session actions. Will write all user data, unless previously
	 * set to free data. If set to free, any existing data files are deleted.
	 */
	public static void shutdown() {
		if (freeData) {
			try {
				Files.delete(new File(USER_DATA_FILE).toPath());
				logger.info("Freeing previously written data");
				
			} catch (IOException e) {
				logger.info("No previously written data exists");
			}finally {
				masterController = null;
			}
		} else {
			writeData();
		}
	}

	/**
	 * Write data to be used in a future session
	 */
	public static void writeData() {

		ObjectOutputStream oos = null;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE));
			oos.writeObject(masterController);
			logger.info("MasterController written to file");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			logger.error("Trying to write data; file not found");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Trying to write data; IO exception");
		}

		try {
			if (oos != null)
				oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Reads data from previous session
	 * 
	 * @return true if successful, else false
	 */
	private static boolean readData() {

		ObjectInputStream ois = null;
		boolean result = false;

		try {
			ois = new ObjectInputStream(new FileInputStream(USER_DATA_FILE));
			masterController = (MasterController) ois.readObject();
			result = true;
		} catch (FileNotFoundException e) {
			logger.info("No previous data found");
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("Trying to read data; IO exception");
		} catch (ClassNotFoundException e) {
			logger.error("Trying to read data; Class not found");
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
