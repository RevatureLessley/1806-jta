package Project0_PartII.RevatureDatabase;

import java.sql.*;
import Project0_PartII.*;

/**
 * DatabaseConnection encapsulates the logic of the database connection.
 */
public class DatabaseConnection implements LogReference {
	/**
	 * connection contains a reference to persistent storage.
	 */
	public static Connection connection = null;
	
	/**
	 * connect() opens up a connection to persistent storage.
	 * 
	 * @return the database connection.
	 */
	public static Connection connect() {
		logger.debug("Project0_PartII/RevatureDatabase/" + 
					 "DatabaseConnection.java: Entered connect().");
		try {
			String args[] = System.getenv("DBARGS").split(";");
			Class.forName(args[0]);
			connection = 
					DriverManager.getConnection(args[1], args[2], args[3]);
			logger.debug("Project0_PartII/RevatureDatabase/" + 
					 	 "DatabaseConnection.java: Exiting connect().");
			
			return connection;
		}
		
		catch (SQLException e) {
			logger.error("Project0_PartII/RevatureDatabase/" + 
				 	 	 "DatabaseConnection.java: " + 
						 "Failed to connect to the database!");
			e.printStackTrace();
		} 
		
		catch (ClassNotFoundException e) {
			logger.error("Project0_PartII/RevatureDatabase/" + 
			 	 	 "DatabaseConnection.java: " + 
					 "Failed load the database driver!");
			e.printStackTrace();
		} 
		
		return null;
	}
	
	/**
	 * close() closes a resource.
	 * 
	 * @param resource for a Statement.
	 */
	public static void close(Statement resource) {
		logger.debug("Project0_PartII/RevatureDatabase/" + 
				 	 "DatabaseConnection.java: Entered close(Statement).");
		
		if(resource!=null) {
			try {
				resource.close();
			}
			
			catch(SQLException e) {
				logger.warn("Project0_PartII/RevatureDatabase/" + 
				 	 	 	"DatabaseConnection.java: " + 
						 	"Failed close a Statement.");
				e.printStackTrace();
			}
		}
		
		logger.debug("Project0_PartII/RevatureDatabase/" + 
			 	 	 "DatabaseConnection.java: Exiting close(Statement).");
	}
	
	/**
	 * close() closes a resource.
	 * 
	 * @param resource for a ResultSet.
	 */
	public static void close(ResultSet resource) {
		logger.debug("Project0_PartII/RevatureDatabase/" + 
			 	 	 "DatabaseConnection.java: Entered close(ResultSet).");
		
		if(resource!=null) {
			try {
				resource.close();
			}
			
			catch(SQLException e) {
				logger.warn("Project0_PartII/RevatureDatabase/" + 
			 	 	 		"DatabaseConnection.java: " + 
					 		"Failed close a ResultSet.");
				e.printStackTrace();
			}
		}
		
		logger.debug("Project0_PartII/RevatureDatabase/" + 
					 "DatabaseConnection.java: Exiting close(ResultSet).");
	}
	
	/**
	 * close() closes a resource.
	 * 
	 * @param resource for a Connection.
	 */
	public static void close(Connection resource) {
		logger.debug("Project0_PartII/RevatureDatabase/" + 
		 	 	 	 "DatabaseConnection.java: Entered close(Connection).");
		if(resource!=null){
			try {
				logger.warn("Project0_PartII/RevatureDatabase/" + 
		 	 	 			"DatabaseConnection.java: " + 
				 			"Failed close a Connection.");
				resource.close();
			}
			
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		logger.debug("Project0_PartII/RevatureDatabase/" + 
	 	 	 	 	 "DatabaseConnection.java: Exiting close(Connection).");
	}
}