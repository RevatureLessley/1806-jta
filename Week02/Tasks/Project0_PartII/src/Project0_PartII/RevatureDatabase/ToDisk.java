package Project0_PartII.RevatureDatabase;

import java.io.*;
import java.sql.*;
import java.util.HashMap;

import Project0_PartII.*;
import Project0_PartII.RevatureAccounts.*;
//import Project0_PartII.RevatureAccounts.AccountAttributes.*;

/**
 * ToDisk encapsulates the logic of writing to persistent storage.
 */
public class ToDisk implements LogReference{
	/**
	 * connection contains a reference to persistent storage.
	 */
//	private FileOutputStream connection;
	private Connection connection;
	/**
	 * record contains the data written to persistent storage.
	 */
//	private ObjectOutputStream record;

	/**
	 * This constructor opens up a connection to persistent storage.
	 * 
	 * @param filename name of the persistent storage file.
	 * @throws FileNotFoundException
	 */
//	public ToDisk(String filename) throws FileNotFoundException {
	public ToDisk() {
		logger.debug("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
	                 "Constructing ToDisk().");
//		connection = new FileOutputStream(filename);
		connection = DatabaseConnection.connect();
		logger.debug("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
		             "Constructed ToDisk().");
	}
	
	public void insert(UserAccount ua) {
		String sqlInsert = "{call insertAccount(?, ?, ?, ?, ?, ?)}";
		CallableStatement statement = null;
		
		try {
			statement = connection.prepareCall(sqlInsert);
			statement.setString(1, ua.getUsername());
			statement.setString(2, ua.getPassword());
			statement.setString(3, ua.getFirstName());
			statement.setString(4, ua.getLastName());
			statement.setBigDecimal(5, ua.getBalance());
			statement.setString(6, ua.getStatus().getString());
			statement.execute();
		}
		
		catch(SQLException se) {
			se.printStackTrace();
		}
		
		finally {
			DatabaseConnection.close(statement);
		}
	}

	public void write(HashMap<Integer, Account> accounts) {
		logger.debug("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
	                 "Entered write().");
		String sqlCommit = "COMMIT";
		Statement statement = null;
		
		try{
//			record = new ObjectOutputStream(connection);
//			record.writeObject(o);
//			record.close();
			for(Account a : accounts.values()) {
				a.write(connection);
			}
			statement = connection.createStatement();
			statement.execute(sqlCommit);
		}

//		catch(IOException ioe){
//			logger.error("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
//		                 "Writing to persistent storage failed!");
//			ioe.printStackTrace();
//		}
		
		catch (SQLException se) {
			se.printStackTrace();
		}
		
		finally {
			DatabaseConnection.close(statement);
		}
		
		logger.debug("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
		             "Exiting write().");
	}
	
	/**
	 * close() closes the connection to persistent storage.
	 * 
	 * @throws IOException
	 */
//	public void close() throws IOException {
	public void close() {
		logger.debug("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
					 "Entered close().");
//		connection.close();
		DatabaseConnection.close(connection);
		logger.debug("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
					 "Exiting close().");
	}
}
