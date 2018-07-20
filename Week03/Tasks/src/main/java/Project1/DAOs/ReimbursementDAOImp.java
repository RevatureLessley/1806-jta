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
			statement.setString(1, "walterx");
			statement.setString(2, "UNIVERSITY_COURSE");
			statement.setBigDecimal(3, new BigDecimal(5000));
			statement.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			statement.setString(5, "Austin, TX");
			statement.setString(6, "240 0:0:0.0");
			statement.setDouble(7, 0.7);
			statement.setString(8, "High education.");
			statement.setString(9, "It is to be expected.");
			
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
