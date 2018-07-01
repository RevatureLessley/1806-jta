package Project0_PartII.RevatureDatabase;

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
	private Connection connection;
	
	/**
	 * This constructor opens up a connection to persistent storage.
	 */
	public FromDisk() {
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
	                 "Constructing FromDisk().");
		connection = DatabaseConnection.connect();
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
		             "Constructed FromDisk().");
	}
	
	/**
	 * @param username used in hashCode.
	 * @param password used in hashCode.
	 * @return hashCode ID based of the username and password.
	 */
	private Integer getID(String username, String password) {
		Integer index = username.hashCode() + 
						password.hashCode();

		return index.hashCode();
	}


	/**
	 * read() queries the database.
	 * 
	 * @return all accounts in the database.
	 */
	public HashMap<Integer, Account> read() {
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
					 "Entered read().");
		HashMap<Integer, Account> as = new HashMap<>();
		String sqlSelect = "SELECT * FROM Account_Join";
		Statement statement = null;
		ResultSet result = null; 
		
		try{
			statement = connection.createStatement();
			result = statement.executeQuery(sqlSelect); 
			
			while(result.next()) {
				Integer i = getID(result.getString("acc_sta_username"), 
								  result.getString("acc_sta_password"));
				UserAccount ua = new UserAccount(result);
				as.put(i, ua);
			}
		}
		
		catch (SQLException se) {
			logger.error("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
					 	 "Querying database failed!.");
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
	 */
	
	public void close() {
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
					 "Entered close().");
		DatabaseConnection.close(connection);
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
		             "Exiting close().");
	}
}
