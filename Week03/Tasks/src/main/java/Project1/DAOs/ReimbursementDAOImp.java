package Project1.DAOs;

import java.math.*;
import java.sql.*;
import java.util.*;
import Project1.*;
import Project1.Beans.*;

public class ReimbursementDAOImp implements LogReference {
	
	public boolean insertReimbursement() {
		logger.debug("Project1/DAO/ReimbursementDAOImp.java: " + 
           	 "Entered insertEmployee().");
		String sqlInsert = 
				"{call insertReimbursement(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		CallableStatement statement = null;

		try(Connection connection = DatabaseConnection.connect()) {
			statement = connection.prepareCall(sqlInsert);
			statement.setString(1, "kwang");
			statement.setString(2, "TECHNICAL_TRAINING");
			statement.setBigDecimal(3, new BigDecimal(20000));
			statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			statement.setString(5, "Arlington, TX");
			statement.setString(6, "7 0:0:0.0");
			statement.setDouble(7, 0.9);
			statement.setString(8, "Revature");
			statement.setString(9, "My lungs!");
			
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
	
	public HashMap<BigInteger, Reimbursement> selectReimbursement(ResultSet rs)
	{
		HashMap<BigInteger, Reimbursement> reimbursements = new HashMap<>();
		
		try {
			
			while(rs.next()) {
				BigInteger index = 
						new BigInteger(rs.getString("E_ReimbursementID")); 
				Reimbursement reimbursement = 
						new Reimbursement(index,
								rs.getBigDecimal("E_AwardedReimbursement"),
								rs.getString("E_IsCancelled")
								  .compareTo("Y") == 0, 
								rs.getString("E_IsPending")
								  .compareTo("Y") == 0, 
								rs.getString("E_ReimbursementJustification"),
								rs.getString("E_ReasonExceededMax")
						);
				
				reimbursements.put(index, reimbursement);
			}
			
			return reimbursements;
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			DatabaseConnection.close(rs);
		}

		return reimbursements;
	}
	
	public void temp() {
		String sqlInsert = "SELECT eve_work_missed FROM EVENT";
		Statement statement = null;
		ResultSet rs = null;
		
		try(Connection connection = DatabaseConnection.connect()) {
			statement = connection.createStatement();
			rs = statement.executeQuery(sqlInsert);
			
			while(rs.next()) {
				System.out.println(rs.getString(1));
			}
		}

		catch(SQLException se) {
			se.printStackTrace();
		}

		finally {
			DatabaseConnection.close(statement);
		}
	}
}
