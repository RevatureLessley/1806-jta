package Project0_PartII.RevatureDatabase;

import java.sql.*;
import java.util.HashMap;
import Project0_PartII.*;
import Project0_PartII.RevatureAccounts.*;

/**
 * ToDisk encapsulates the logic of writing to persistent storage.
 */
public class ToDisk implements LogReference{
	/**
	 * connection contains a reference to persistent storage.
	 */
	private Connection connection;

	/**
	 * This constructor opens up a connection to persistent storage.
	 */
	public ToDisk() {
		logger.debug("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
	                 "Constructing ToDisk().");
		connection = DatabaseConnection.connect();
		logger.debug("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
		             "Constructed ToDisk().");
	}
	
	/**
	 * insert() inserts a new user account into the database.
	 * 
	 * @param ua the account to be inserted.
	 */
	public void insert(UserAccount ua) {
		logger.debug("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
                	 "Entered insert().");
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
			logger.error("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
						 "Inserting to the database failed!.");
			se.printStackTrace();
		}
		
		finally {
			DatabaseConnection.close(statement);
		}
		
		logger.debug("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
           	 	     "Exiting insert().");
	}

	/**
	 * write() updates the database.
	 * 
	 * @param accounts the potential accounts to be updated.
	 */
	public void write(HashMap<Integer, Account> accounts) {
		logger.debug("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
	                 "Entered write().");
		String sqlCommit = "COMMIT";
		Statement statement = null;
		
		try{
			for(Account a : accounts.values()) {
				a.write(connection);
			}
			statement = connection.createStatement();
			statement.execute(sqlCommit);
		}
		
		catch (SQLException se) {
			logger.error("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
					 	 "Updating the database failed!.");
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
	 */
	public void close() {
		logger.debug("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
					 "Entered close().");
		DatabaseConnection.close(connection);
		logger.debug("Project0_PartII/RevatureDatabase/ToDisk.java: " + 
					 "Exiting close().");
	}
}
