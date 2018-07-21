package Project1.DAOs;

import java.io.*;
import java.sql.*;
import Project1.*;

public class GradingFormatDAOImp implements LogReference {
	
	// Note: Remember to batch this.
//	public boolean updateGradingFormatProof() {
//		logger.debug("Project1/DAO/GradingFormatDAOImp.java: " + 
//           	 "Entered updateGradingFormatProof().");
//		String sqlInsert = "{call updateGradingFormatProof(?, ?, ?, ?)}";
//		CallableStatement statement = null;
//		String filename = "C:\\Users\\Swilery\\Documents\\Walter\\Revature\\FromFS\\Poems.pdf";
//		String filesize = "970411";
//
//		try(Connection connection = DatabaseConnection.connect()) {
//			statement = connection.prepareCall(sqlInsert);
//			statement.setInt(1, 1);
//			statement.setString(2, filename);
//			statement.setString(3, filesize);
//			statement.setBinaryStream(4, new FileInputStream(filename));
//			
//			return statement.execute();
//		}
//
//		catch(SQLException se) {
//			logger.error("Project1/DAO/ApplicationDAOImp.java: " + 
//		           	 "Updating Grading_Format failed!.");
//			se.printStackTrace();
//		} 
//		
//		catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		finally {
//			logger.debug("Project1/DAO/GradingFormatDAOImp.java: " + 
//		           	 "Exiting updateGradingFormatProof().");
//			DatabaseConnection.close(statement);
//		}
//		
//		return false;
//	}
}
