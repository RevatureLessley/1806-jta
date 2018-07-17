package Project1.DAOs;

import java.sql.*;
import Project1.*;
import Project1.Beans.*;

public class EmployeeDAOImp implements LogReference {
	
	public boolean checkEmployee(String username) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * " + 
						  "FROM Employee_View " + 
						  "WHERE E_Username = ?";

		try(Connection connection = DatabaseConnection.connect()) {
			ps = connection.prepareStatement(sqlQuery);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if(rs.next()) return true;
			
			else return false;
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			DatabaseConnection.close(ps);
			DatabaseConnection.close(rs);
		}
		
		return false;
	}
	
	public Employee insertEmployee(String username, String password,
								  String firstname, String lastname, 
								  String department, String supervisor, 
								  String isBenco) {
		logger.debug("Project1/DAO/EmployeeDAOImp.java: " + 
           	 "Entered insertEmployee().");
		String sqlInsert = "{call insertEmployee(?, ?, ?, ?, ?, ?, ?)}";
		CallableStatement statement = null;

		try(Connection connection = DatabaseConnection.connect()) {
			statement = connection.prepareCall(sqlInsert);
			statement.setString(1, username);
			statement.setString(2, password);
			statement.setString(3, firstname);
			statement.setString(4, lastname);
			statement.setString(5, department);
			statement.setString(6, supervisor);
			statement.setString(7, isBenco);
			statement.execute();
			
			return new Employee(supervisor, 1000, username, password,
								firstname, lastname, 
								isBenco.compareTo("Y") == 0);
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
		
		return null;
	}
	
	public Employee selectEmployee(String username, String password) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * " + 
						  "FROM Employee_View " + 
						  "WHERE E_Username = ? AND E_Password = ?";

		try(Connection connection = DatabaseConnection.connect()) {
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
