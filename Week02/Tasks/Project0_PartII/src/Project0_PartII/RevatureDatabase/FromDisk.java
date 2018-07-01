package Project0_PartII.RevatureDatabase;

import java.io.*;
import java.sql.*;
import java.util.HashMap;
import Project0_PartII.LogReference;
import Project0_PartII.RevatureAccounts.*;

/**
 * FromDisk encapsulates the logic of reading from persistent storage.
 */
public class FromDisk implements LogReference {
	/**
	 * connection contains a reference to persistent storage.
	 */
//	private FileInputStream connection;
	private Connection connection;
	/**
	 * record contains the data read from persistent storage.
	 */
//	private ObjectInputStream record;

	/**
	 * This constructor opens up a connection to persistent storage.
	 * 
	 * @param filename name of the persistent storage file.
	 * @throws FileNotFoundException
	 */
//	public FromDisk(String filename) throws FileNotFoundException {
	public FromDisk() {
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
	                 "Constructing FromDisk().");
//		connection = new FileInputStream(filename);
		connection = DatabaseConnection.connect();
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
		             "Constructed FromDisk().");
	}
	
	private Integer getID(String username, String password) {
		Integer index = username.hashCode() + 
						password.hashCode();

		return index.hashCode();
	}

//	public Object read() {
	public HashMap<Integer, Account> read() {
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
					 "Entered read().");
		HashMap<Integer, Account> as = new HashMap<>();
		String sqlSelect = "SELECT * FROM Account_Join";
		Statement statement = null;
		ResultSet result = null; 
		
		try{
//			record = new ObjectInputStream(connection);
//			Object a = record.readObject();
//			record.close();
//			return a;
			statement = connection.createStatement();
			result = statement.executeQuery(sqlSelect); 
			
			while(result.next()) {
				Integer i = getID(result.getString("acc_sta_username"), 
								  result.getString("acc_sta_password"));
				UserAccount ua = new UserAccount(result);
				as.put(i, ua);
			}
		}

//		catch(IOException ioe){
//			logger.error("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
//		                 "Reading from persistent storage failed!");
//			ioe.printStackTrace();
//		}
//
//		catch(ClassNotFoundException cnfe){
//			logger.error("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
//		                 "Data in persistent storage corrupted!");
//			cnfe.printStackTrace();
//		}
		
		catch (SQLException se) {
			se.printStackTrace();
		}
		
		finally {
			DatabaseConnection.close(statement);
			DatabaseConnection.close(result);
		}
		
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
		             "Exiting read().");
		return as;
	}

	/**
	 * close() closes the connection to persistent storage.
	 * 
	 * @throws IOException
	 */
//	public void close() throws IOException {
	public void close() {
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
					 "Entered close().");
//		connection.close();
		DatabaseConnection.close(connection);
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
		             "Exiting close().");
	}
}
