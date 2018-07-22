package Project1.DAOs;

import java.math.*;
import java.sql.*;
import java.util.*;
import Project1.*;
import Project1.Beans.*;

public class ReimbursementDAOImp implements LogReference {
	
	public Reimbursement insertReimbursement(String username, String type, 
									   		 BigDecimal cost, 
									   		 Timestamp datetime, 
									   		 String location,
									   		 String workMissed, 
									   		 Double cutoff, String description, 
									   		 String justification) {
		logger.debug("Project1/DAO/ReimbursementDAOImp.java: " + 
           	 "Entered insertEmployee().");
		String sqlInsert = 
				"{call insertReimbursement(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
		CallableStatement statement = null;

		try(Connection connection = DatabaseConnection.connect()) {
			statement = connection.prepareCall(sqlInsert);
			statement.registerOutParameter(1, Types.INTEGER);
			statement.setString(2, username);
			statement.setString(3, type);
			statement.setBigDecimal(4, cost);
			statement.setTimestamp(5, datetime);
			statement.setString(6, location);
			statement.setString(7, workMissed);
			
			if(cutoff == null) statement.setNull(8, Types.DOUBLE);
				
			else statement.setDouble(8, cutoff);
			
			statement.setString(9, description);
			statement.setString(10, justification);
			statement.execute();
			
			Event event = new Event(type, EventType.getCoverage(type), cost,
									datetime, description, location, workMissed);
			BigInteger bi = new BigInteger(statement.getString(1));
			Reimbursement reimbursement = new Reimbursement(bi, justification);
			reimbursement.setEvent(event);
			
			return reimbursement;
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
		
		return null;
	}
	
	public HashMap<BigInteger, Reimbursement> selectReimbursement(ResultSet rs)
	{
		HashMap<BigInteger, Reimbursement> reimbursements = new HashMap<>();
		
		try {
			
			while(rs.next()) {
				String s = rs.getString("E_ReimbursementID");
				
				if(s != null) {
					BigInteger index = new BigInteger(s); 
					Reimbursement reimbursement = 
							new Reimbursement(
								index,
								rs.getBigDecimal("E_AwardedReimbursement"),
								rs.getString("E_IsCancelled")
								  .compareTo("Y") == 0, 
								rs.getString("E_IsPending")
								  .compareTo("Y") == 0, 
								rs.getString("E_ReimbursementJustification"),
								rs.getString("E_ReasonExceededMax")
							);
					
					EventDAOImp edi = new EventDAOImp();
					reimbursement.setEvent(edi.selectEvent(rs));
					reimbursements.put(index, reimbursement);
				}
			}
			
			return reimbursements;
		}
		
		catch(SQLException e) {
			DatabaseConnection.close(rs);
			e.printStackTrace();
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
