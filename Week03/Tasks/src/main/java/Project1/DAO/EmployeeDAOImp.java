package Project1.DAO;

import java.sql.*;
import Project1.*;

public class EmployeeDAOImp implements LogReference {
	
	public boolean insertEmployee() {
		logger.debug("Project1/DAO/EmployeeDAOImp.java: " + 
           	 "Entered insertEmployee().");
		String sqlInsert = "{call insertEmployee(?, ?, ?, ?, ?, ?, ?)}";
		CallableStatement statement = null;

		try(Connection connection = DatabaseConnection.connect()) {
			statement = connection.prepareCall(sqlInsert);
			statement.setString(1, "kwang");
			statement.setString(2, "kwang");
			statement.setString(3, "Kevin");
			statement.setString(4, "Wang");
			statement.setString(5, "Dictator");
			statement.setString(6, "swilery");
			statement.setString(7, "N");
			
			return statement.execute();
		}

		catch(SQLException se) {
			logger.error("Project1/DAO/EmployeeDAOImp.java: " + 
		           	 "Inserting into Employee failed!.");
			se.printStackTrace();
		}

		finally {
			logger.debug("Project1/DAO/EmployeeDAOImp.java: " + 
		           	 "Exiting insertEmployee().");
			DatabaseConnection.close(statement);
		}
		
		return false;
	}
}
