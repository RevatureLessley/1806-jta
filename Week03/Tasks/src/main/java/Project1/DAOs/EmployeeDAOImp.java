package Project1.DAOs;

import java.sql.*;
import Project1.*;
import Project1.Beans.*;

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
	
	public Employee selectEmployee(String username, String password) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * " + 
						  "FROM Employee_View " + 
						  "WHERE E_Username = ? AND E_Password = ?";

		try(Connection connection = DatabaseConnection.connect()){
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();

			if(rs.next()) 
				return new Employee(rs.getString("E_Supervisor"), 
								    rs.getDouble("E_AvailableReimbursement"),
								    rs.getString("E_Username"), 
								    rs.getString("E_Password"), 
								    rs.getString("E_FirstName"),
								    rs.getString("E_LastName"), 
								    rs.getString("E_IsBenCo")
								      .compareTo("Y") == 0
								    );
			else return null;
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			DatabaseConnection.close(ps);
			DatabaseConnection.close(rs);
		}

		return null;
	}
}
