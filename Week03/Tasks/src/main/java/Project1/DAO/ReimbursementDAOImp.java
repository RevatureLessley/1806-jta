package Project1.DAO;

import java.math.*;
import java.sql.*;
import Project1.*;

public class ReimbursementDAOImp implements LogReference {
	
	public boolean insertReimbursement() {
		logger.debug("Project1/DAO/ReimbursementDAOImp.java: " + 
           	 "Entered insertEmployee().");
		String sqlInsert = 
				"{call insertReimbursement(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		CallableStatement statement = null;

		try(Connection connection = DatabaseConnection.connect()) {
			statement = connection.prepareCall(sqlInsert);
			statement.setString(1, "ahwang");
			statement.setString(2, "TECHNICAl_TRAINING");
			statement.setBigDecimal(3, new BigDecimal(20000));
			statement.setString(4, "Wang");
			statement.setString(5, "Dictator");
			statement.setString(6, "swilery");
			statement.setString(7, "N");
			statement.setString(8, "swilery");
			statement.setString(9, "N");
			
			return statement.execute();
		}

		catch(SQLException se) {
			logger.error("Project1/DAO/ReimbursementDAOImp.java: " + 
		           	 "Inserting into Employee failed!.");
			se.printStackTrace();
		}

		finally {
			logger.debug("Project1/DAO/ReimbursementDAOImp.java: " + 
		           	 "Exiting insertReimbursement().");
			DatabaseConnection.close(statement);
		}
		
		return false;
	}
}
