package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Reimbursement;
import com.revature.services.EmployeeService;
import com.revature.util.Connections;

/**
 * Reimbursement Data Access Object
 * <br>Used to grab information regarding a reimbursement
 * from the database.
 * <br>
 * @author Logan Brewer
 *
 */
public class ReimbursementDao
{
	
	/**
	 * Grab the reimbursement id for the first result in the 
	 * reimbursement table.
	 * @return
	 */
	public Integer selectRIdByEmpId() 
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT r_id FROM reimbursement WHERE rownum = 1 ORDER BY r_id DESC";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				return rs.getInt(1);
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
		return null;
	}
	
	/**
	 * Call the insert_into_reimbursement stored procedure by passing 
	 * in all the information required to create a reimbursement.
	 * @param eventDate
	 * @param eventTime
	 * @param eventLocation
	 * @param eventDesc
	 * @param eventCost
	 * @param justification
	 * @param gradeCutoff
	 * @param empId
	 * @param eventId
	 * @param gradingFormatId
	 * @return
	 */
	public Boolean insertReimbursementViaSp(String eventDate, String eventTime, String eventLocation,
											String eventDesc, Integer eventCost, String justification,
											Integer gradeCutoff, Integer empId, Integer eventId,
											Integer gradingFormatId) 
	{
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection())
		{
			stmt = conn.prepareCall("{call insert_into_reimbursement(null,?,?,?,?,?,?,?,null,?,?,?,null,?)}");
			
			stmt.setString(1, eventDate);
			stmt.setString(2, eventTime);
			stmt.setString(3, eventLocation);
			stmt.setString(4, eventDesc);
			stmt.setInt(5, eventCost);
			stmt.setString(6, justification);
			stmt.setInt(7, gradeCutoff);
			stmt.setInt(8, empId);
			stmt.setInt(9, eventId);
			stmt.setInt(10, gradingFormatId);
			stmt.setInt(11, empId);
			
			stmt.execute();
			return true;	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(stmt);
		}
		return false;
	}
	
	/**
	 * Grab all the information needed to generate a row on a table showing
	 * reimbursements by using an employees account name. Shows all
	 * reimbursements that are not declined or approved.
	 * @param accountname
	 * @return
	 */
	public List<Reimbursement> selectReimbursementInfo(String accountname)
	{
		EmployeeService es = new EmployeeService();
		Integer empId = es.getEmpIdByAccountname(accountname);
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reimbursement> reims = new ArrayList<>();
		
		String sql = "SELECT reimbursement.event_desc, reimbursement.event_date, reimbursement.event_time, reimbursement.event_location, reimbursement.event_cost FROM reimbursement WHERE reimbursement.emp_id = ? AND (reimbursement.approval_id = 1 OR reimbursement.approval_id = 2 OR reimbursement.approval_id = 3 OR reimbursement.approval_id = 4)";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Reimbursement reim = new Reimbursement(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5)
						);
				reims.add(reim);
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
		return reims;
	}
	
	/**
	 * Grab all information needed for a reimbursement table by using an
	 * employees account name. Grabs all reimbursements that are on approval
	 * level 1, meaning they need approved by a supervisor or a higher up.
	 * @param accountname
	 * @return
	 */
	public List<Reimbursement> selectLevelOneReimbursementInfo(String accountname)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reimbursement> reims = new ArrayList<>();
		
		String sql = "SELECT reimbursement.event_desc, reimbursement.event_date, reimbursement.event_time, reimbursement.event_location, reimbursement.event_cost FROM reimbursement WHERE reimbursement.approval_id = 1";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Reimbursement reim = new Reimbursement(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5)
						);
				reims.add(reim);
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
		return reims;
	}
	
	/**
	 * Grab all information needed for a reimbursement table by using an
	 * employees account name. Grabs all reimbursements that are on approval
	 * level 2, meaning they need approved by a department head or a higher up.
	 * @param accountname
	 * @return
	 */
	public List<Reimbursement> selectLevelTwoReimbursementInfo(String accountname)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reimbursement> reims = new ArrayList<>();
		
		String sql = "SELECT reimbursement.event_desc, reimbursement.event_date, reimbursement.event_time, reimbursement.event_location, reimbursement.event_cost FROM reimbursement WHERE reimbursement.approval_id = 2";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Reimbursement reim = new Reimbursement(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5)
						);
				reims.add(reim);
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
		return reims;
	}
	
	/**
	 * Grab all information needed for a reimbursement table by using an
	 * employees account name. Grabs all reimbursements that are on approval
	 * level 2, meaning they need approved by an employee who is
	 * a department head & supervisor at the same time or a higher up.
	 * @param accountname
	 * @return
	 */
	public List<Reimbursement> selectLevelOneOrTwoReimbursementInfo(String accountname)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reimbursement> reims = new ArrayList<>();
		
		String sql = "SELECT reimbursement.event_desc, reimbursement.event_date, reimbursement.event_time, reimbursement.event_location, reimbursement.event_cost FROM reimbursement WHERE (reimbursement.approval_id = 1 OR reimbursement.approval_id = 2)";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Reimbursement reim = new Reimbursement(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5)
						);
				reims.add(reim);
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
		return reims;
	}
	
	/**
	 * Grab all information needed for a reimbursement table by using an
	 * employees account name. Grabs all reimbursements that are on an approval
	 * any level other than 0, meaning they need approved by an employee who is
	 * a Benefits Coordinator.
	 * @param accountname
	 * @return
	 */
	public List<Reimbursement> selectAllLevelsReimbursementInfo(String accountname)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reimbursement> reims = new ArrayList<>();
		
		String sql = "SELECT reimbursement.event_desc, reimbursement.event_date, reimbursement.event_time, reimbursement.event_location, reimbursement.event_cost FROM reimbursement WHERE (reimbursement.approval_id = 1 OR reimbursement.approval_id = 2 OR reimbursement.approval_id = 3 OR reimbursement.approval_id = 4)";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Reimbursement reim = new Reimbursement(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5)
						);
				reims.add(reim);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
		return reims;
	}
	
	/**
	 * Grab all information needed for a reimbursement table using
	 * an employees account name. Grabs all reimbursement requests
	 * that have been fully approved.
	 * @param accountname
	 * @return
	 */
	public List<Reimbursement> selectApprovedReimbursementInfo(String accountname)
	{
		EmployeeService es = new EmployeeService();
		Integer empId = es.getEmpIdByAccountname(accountname);
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reimbursement> reims = new ArrayList<>();
		
		String sql = "SELECT reimbursement.event_desc, reimbursement.event_date, reimbursement.event_time, reimbursement.event_location, reimbursement.event_cost FROM reimbursement WHERE reimbursement.emp_id = ? AND reimbursement.approval_id = 5";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Reimbursement reim = new Reimbursement(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5)
						);
				reims.add(reim);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
		return reims;
	}
	
	/**
	 * Grab all information needed for a reimbursement table using
	 * an employees account name. Grabs all reimbursement requests
	 * that have been denied.
	 * @param accountname
	 * @return
	 */
	public List<Reimbursement> selectDeclinedReimbursementInfo(String accountname)
	{
		EmployeeService es = new EmployeeService();
		Integer empId = es.getEmpIdByAccountname(accountname);
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Reimbursement> reims = new ArrayList<>();
		
		String sql = "SELECT reimbursement.event_desc, reimbursement.event_date, reimbursement.event_time, reimbursement.event_location, reimbursement.event_cost FROM reimbursement WHERE reimbursement.emp_id = ? AND reimbursement.approval_id = 0";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			while(rs.next())
			{
				Reimbursement reim = new Reimbursement(
							rs.getString(1),
							rs.getString(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5)
						);
				reims.add(reim);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
		return reims;
	}
	
	/**
	 * Used by a supervisor in order to approve a reimbursement
	 * from a regular employee and promote it to approval
	 * level 2.
	 */
	public void updateApprovalToLevelTwo()
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "UPDATE reimbursement SET approval_id = 2 WHERE reimbursement.approval_id = 1 AND rownum = 1";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
	}
	
	/**
	 * Used by a supervisor, department head, or benefits coordinator
	 * in order to demote a reimbursement request to level 0, meaning it
	 * is denied.
	 */
	public void updateApprovalToLevelZero()
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "UPDATE reimbursement SET approval_id = 0 WHERE (reimbursement.approval_id = 1 OR reimbursement.approval_id = 2 OR reimbursement.approval_id = 3 OR reimbursement.approval_id = 4) AND rownum = 1";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
	}
	
	/**
	 * Used by a department head in order to approve a reimbursement
	 * from a regular employee or supervisor and promote it to approval
	 * level 3.
	 */
	public void updateApprovalToLevelThree()
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "UPDATE reimbursement SET approval_id = 3 WHERE reimbursement.approval_id = 2 AND rownum = 1";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
	}
	
	/**
	 * Used by an employee who is a department head &
	 * supervisor in order to approve a reimbursement
	 * from a regular employee or supervisor and promote it to approval
	 * level 4.
	 */
	public void updateApprovalToLevelFour()
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "UPDATE reimbursement SET approval_id = 4 WHERE (reimbursement.approval_id = 1 OR reimbursement.approval_id = 2) AND rownum = 1";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
	}
	
	/**
	 * Used by a Benefits Coordinator in order to promote
	 * a reimbursement request of any level to approved.
	 * Can be used before a supervisor or department head
	 * approve a request.
	 */
	public void updateApprovalToLevelFive()
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "UPDATE reimbursement SET approval_id = 5 WHERE (reimbursement.approval_id = 1 OR reimbursement.approval_id = 2 OR reimbursement.approval_id = 3 OR reimbursement.approval_id = 4) AND rownum = 1";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
	}
}
