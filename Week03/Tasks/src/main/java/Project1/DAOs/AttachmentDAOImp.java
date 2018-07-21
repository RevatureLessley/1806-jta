package Project1.DAOs;

import java.io.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import Project1.*;
import Project1.Beans.*;

public class AttachmentDAOImp implements LogReference {
	Connection connection;
	
	// Note: Remember to batch this.
	public boolean insertAttachment(String category, String filename, String filesize) {
		logger.debug("Project1/DAO/ApplicationDAOImp.java: " + 
           	 "Entered insertAttachment().");
		String sqlInsert = "{call insertAttachment(?, ?, ?, ?, ?, ?)}";
		CallableStatement statement = null;
	
		try(Connection connection = DatabaseConnection.connect()) {
			statement = connection.prepareCall(sqlInsert);
			statement.setString(1, category);
			statement.setInt(2, 1);
			statement.setString(3, filename);
			statement.setString(4, "." + filename.split("\\.")[1]);
			statement.setString(5, filesize);
			statement.setBinaryStream(6, new FileInputStream(filename));
			
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
		selectAttachment(BigInteger foreignKey, String category) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * " + 
						  "FROM Attachment_View " + 
						  "WHERE A_AttachmentFK = ? AND " +
						  "A_AttachmentCategory = ?";
		HashMap<BigInteger, Attachment> attachments = new HashMap<>();
		
		try {
			connection = DatabaseConnection.connect();
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, String.valueOf(foreignKey));
			ps.setString(2, category);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				// Get the primary key
				String s = rs.getString("A_ATTACHMENTID");
				
				BigInteger index = new BigInteger(s); 
				Attachment attachment = 
						new Attachment(
							category,
							rs.getString("A_AttachmentMIME"),
							rs.getString("A_AttachmentName"),
							new BigInteger(
									rs.getString("A_AttachmentSize")
							),
							rs.getBlob("A_AttachmentFile")
						);
				
				attachments.put(index, attachment);
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
