package Project1.DAO;

import java.io.*;
import java.sql.*;
import Project1.*;

public class AttachmentDAOImp implements LogReference {
	
	// Note: Remember to batch this.
	public boolean insertAttachment(String table) {
		logger.debug("Project1/DAO/ApplicationDAOImp.java: " + 
           	 "Entered insertAttachment().");
		String sqlInsert = "{call insert" + table + "(?, ?, ?)}";
		CallableStatement statement = null;
		String filename = "C:\\Users\\Swilery\\Documents\\Walter\\Revature\\FromFS\\Poems.pdf";

		try(Connection connection = DatabaseConnection.connect()) {
			statement = connection.prepareCall(sqlInsert);
			statement.setInt(1, 1);
			statement.setString(2, filename);
			statement.setBinaryStream(3, new FileInputStream(filename));
			
			return statement.execute();
		}

		catch(SQLException se) {
			logger.error("Project1/DAO/ApplicationDAOImp.java: " + 
		           	 "Inserting into Application failed!.");
			se.printStackTrace();
		} 
		
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		finally {
			logger.debug("Project1/DAO/ApplicationDAOImp.java: " + 
		           	 "Exiting insertAttachment().");
			DatabaseConnection.close(statement);
		}
		
		return false;
	}
}
