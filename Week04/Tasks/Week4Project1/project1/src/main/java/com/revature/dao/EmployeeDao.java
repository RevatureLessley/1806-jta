package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Employee;
import com.revature.util.Connections;

/**
 * Employee Data Access Object
 * <br>Used to grab information regarding an Employee from
 * the database.
 * <br>
 * @author Logan Brewer
 *
 */
public class EmployeeDao 
{
	/**
	 * Grab all data to make an employee from the database
	 * using an employees ID.
	 * @param id
	 * @return
	 */
	public Employee getEmployeeViaEmpId(Integer id)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM employee WHERE emp_id = ?";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next())
			{
				return new Employee(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getInt(8)
						);
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
	 * Grab an employees ID from the database using that employees accountname.
	 * @param accountname
	 * @return
	 */
	public Integer getEmpIdByAccountnameDao(String accountname)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT emp_id FROM employee WHERE emp_accountname = ?";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			ps.setString(1, accountname);
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
		return 0;
	}
	
	/**
	 * Grab the amount left in an employees account using that employees
	 * ID.
	 * @param empId
	 * @return
	 */
	public Integer getEmployeeAmountLeftViaSp(Integer empId)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT amount_left FROM employee WHERE emp_id = ?";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
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
		return 0;
	}
	
	/**
	 * Grab the sum of the cost of an employees pending and accepted reimbursements
	 * for every reimbursement under an employees accountname.
	 * @param accountName
	 * @return
	 */
	public Integer selectAmountLeftByAccountName(String accountName) 
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT SUM(reimbursement.event_cost) FROM employee\r\n" + 
				"INNER JOIN reimbursement\r\n" + 
				"ON employee.emp_id = reimbursement.emp_id\r\n" + 
				"WHERE employee.emp_accountname = ? AND \r\n" + 
				"(reimbursement.approval_id = 1 OR \r\n" + 
				"reimbursement.approval_id = 2 OR \r\n" + 
				"reimbursement.approval_id = 3 OR \r\n" + 
				"reimbursement.approval_id = 4 OR \r\n" + 
				"reimbursement.approval_id = 5)";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			ps.setString(1, accountName);
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
	 * Grab the amount left in an employees account using that employees
	 * account name.
	 * @param accountName
	 * @return
	 */
	public Integer selectAmountInAccount(String accountName)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT amount_left FROM employee WHERE emp_accountname = ?";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			ps.setString(1, accountName);
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
	 * Call the update_amount_left stored procedure by entering an employee
	 * ID and the amount to update the employees amount_left calue to.
	 * @param employee
	 * @param amountLeft
	 * @return
	 */
	public Boolean updateEmployeeAmountLeftViaSp(Employee employee, Integer amountLeft) 
	{
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection())
		{
			stmt = conn.prepareCall("{call update_amount_left(?,?)}");
			
			stmt.setInt(1, employee.getEmployeeId());
			stmt.setInt(2, amountLeft);

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
	 * Grab everything to make an employee from the database
	 * by using an employees account name.
	 * @param accountName
	 * @return
	 */
	public Employee selectEmployeeByAccountName(String accountName) 
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM employee WHERE emp_accountname = ?";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			ps.setString(1, accountName);
			rs = ps.executeQuery();
			while(rs.next())
			{
				return new Employee(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getInt(8)
						);
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
	 * Grab the job type id from the database for a specific employee
	 * by using that employees account name.
	 * @param accountName
	 * @return
	 */
	public int selectJobTypeIdByAccountName(String accountName) 
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT job_type_id FROM employee WHERE emp_accountname = ?";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			ps.setString(1, accountName);
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
		return 0;
	}
	
	/**
	 * Grab the job type id from the database for a specific employee
	 * by using that employees ID.
	 * @param empId
	 * @return
	 */
	public int selectJobTypeIdByEmpId(Integer empId) 
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT job_type_id FROM employee WHERE emp_id = ?";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
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
		return 0;
	}
}
