package Project1.DAOs;

import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import Project1.*;
import Project1.Beans.*;

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
	
	public HashMap<BigInteger, Attachment> 
		selectAttachment(String table, String column, BigInteger foreignKey) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * " + 
						  "FROM " + table + "_Attachment " + 
						  "WHERE " + column + " = ?";
		HashMap<BigInteger, Attachment> attachments = new HashMap<>();
		
		try(Connection connection = DatabaseConnection.connect()) {
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, String.valueOf(foreignKey));
			rs = ps.executeQuery();
			
			while(rs.next()) {
				// Get the primary key
				String s = rs.getString(1);
				
				if(s != null) {
					BigInteger index = new BigInteger(s); 
					Attachment attachment = 
							new Attachment(
								// Get the file name
								rs.getString(3),
								// Get the file
								rs.getBinaryStream(4)
							);
					
					attachments.put(index, attachment);
				}
			}
			
			return attachments;
		}
		
		catch(SQLException e) {
			DatabaseConnection.close(rs);
			e.printStackTrace();
		}

		return attachments;
	}
}
