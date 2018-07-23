package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.ReimbursementReq;
import com.revature.util.Connections;

public class ReimbursementReqDaoImpl {
	
	
	public void insertReq(ReimbursementReq req, String table)
	{	
			CallableStatement stmt = null; 
			
			try(Connection conn = Connections.getConnection()){

				stmt = conn.prepareCall("{call insertIntoreimbursement" + table + " (?,?,?,?,?,?,?,?,?,?,?,?,?)}");
				
				stmt.setInt(1, req.getReimbursementId());
				stmt.setInt(2, req.getEmpId());
				stmt.setInt(3,  req.getEventId());
				stmt.setInt(4,  req.getEventType());
				stmt.setDate(5, req.getEventDate());
				stmt.setFloat(6, req.getAmountReq());
				stmt.setString(7, req.isDsApprove() == true? "yes":"no");
				stmt.setDate(8, req.getDsApproveDate());
				stmt.setString(9, req.isDhApprove() == true? "yes":"no");
				stmt.setDate(10, req.getDhApproveDate());
				stmt.setString(11, req.isBcApprove() == true? "yes":"no");
				stmt.setDate(12, req.getBcApproveDate());
				stmt.setString(13, req.isExceedAmountApproved() == true? "yes":"no");

				
				stmt.execute(); //Returns amount rows effected;
				System.out.println("executed fine");
				
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				close(stmt);
			}		
		}
	public void insertNewReq(ReimbursementReq req)
	{	
			CallableStatement stmt = null; 
			CallableStatement stmt2 = null; 
			try(Connection conn = Connections.getConnection()){

				stmt = conn.prepareCall("{call insertIntoreimbursementshort (?,?,?,?,?,?,?,?,?)}");
				
				stmt.setInt(1, req.getEmpId());
				stmt.setInt(2,  req.getEventId());
				stmt.setInt(3,  req.getEventType());
				stmt.setDate(4, req.getEventDate());
				stmt.setFloat(5, req.getAmountReq());
				stmt.setString(6, req.isDsApprove() == true? "yes":"no");
				stmt.setString(7, req.isDhApprove() == true? "yes":"no");
				stmt.setString(8, req.isBcApprove() == true? "yes":"no");
				stmt.setString(9, req.isExceedAmountApproved() == true? "yes":"no");

				stmt.execute(); //Returns amount rows effected;
				System.out.println("executed fine");
				
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				close(stmt);
			}		
			
			try(Connection conn = Connections.getConnection()){

				stmt = conn.prepareCall("{call insertIntoreimbursementds (?,?,?,?,?,?,?,?,?)}");
				
				stmt.setInt(1, req.getEmpId());
				stmt.setInt(2,  req.getEventId());
				stmt.setInt(3,  req.getEventType());
				stmt.setDate(4, req.getEventDate());
				stmt.setFloat(5, req.getAmountReq());
				stmt.setString(6, req.isDsApprove() == true? "yes":"no");
				stmt.setString(7, req.isDhApprove() == true? "yes":"no");
				stmt.setString(8, req.isBcApprove() == true? "yes":"no");
				stmt.setString(9, req.isExceedAmountApproved() == true? "yes":"no");

				stmt.execute(); //Returns amount rows effected;
				System.out.println("executed fine");
				
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
			finally
			{
				close(stmt);
			}		
		}
	// TODO Consider passing just the reqId instead of the entire req since we are just using the id anyway.
	public Integer deleteReq(ReimbursementReq req, String table)
	{
		// Setup my statements and result sets
		Statement stmt = null;
		ResultSet rs = null;
		
		PreparedStatement stmt2 = null;
		
		// First we will make sure that the req is actually in the table
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT * FROM reimbursement" + table + " where reimbursement_id = " + req.getReimbursementId();
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					rs = stmt.executeQuery(sql);
		// If the result set is not null, go to the last record in the result set and return that row number
		// If it is 1, remove it
					if(rs != null)
					{
						rs.last();  
			            int size = rs.getRow();
			            System.out.println(size);
			            if(size == 1)
			            	{
			            		rs.beforeFirst();	
			            		String sql2 = "DELETE FROM " + table + " WHERE reimbursement_id = ?";
			            		stmt2 = conn.prepareStatement(sql2);
								stmt2.setInt(1, req.getReimbursementId());
								stmt2.executeQuery();
			            	}
					}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(stmt);
			close(stmt2);
		}
		
		System.out.println("Update is a success!");
				return 0;
	}
	
	
	public List<ReimbursementReq> selectReqs(String table)
	{
		Statement stmt = null;
		ResultSet rs = null;
		String sql = ("Select * from reimbursement" + table );
		System.out.println(sql);
		List<ReimbursementReq> reqs = new ArrayList<>();
		ReimbursementReq req = null;
		
		try (Connection conn = Connections.getConnection())
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);

			while (rs.next())
			{
				req = new ReimbursementReq(rs.getInt(1), 
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getDate(5),
						rs.getFloat(6),
						rs.getString(7) == "yes" ? true : false,
						rs.getDate(8),
						rs.getString(9) == "yes" ? true : false,
						rs.getDate(10),
						rs.getString(11) == "yes" ? true : false,
						rs.getDate(12),
						rs.getString(13) == "yes" ? true : false);
				
				reqs.add(req);
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(stmt);
		}
		
		for(ReimbursementReq rreq : reqs)
		{
			System.out.println(rreq);
		}
		
		return reqs;
	}
	
	// TODO this method marked for deletion if I end up not needing it
	public List<ReimbursementReq> selectReqByReqNotApproved(int id) {
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM reimbursement WHERE ds_approve = 'no'";
		List<ReimbursementReq> reqs = new ArrayList<>();
		ReimbursementReq req = null;
		
		try(Connection conn = Connections.getConnection())
		{
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			
			
			while(rs.next()){
				req = new ReimbursementReq (
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getDate(5),
						rs.getFloat(6),
						rs.getString(7) == "yes"? true:false,
						rs.getDate(8),
						rs.getString(9) == "yes"? true:false,
						rs.getDate(10),
						rs.getString(11) == "yes"?true:false,
						rs.getDate(12),
						rs.getString(13) == "yes"?true:false
						);
				
				reqs.add(req);
			}	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(stmt);
		}
		
		return reqs;
	}
	
	public ReimbursementReq selectReqByReqId(int id) 
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM reimbursement WHERE reimbursement_id = ?";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();	
			
			while(rs.next()){
				return new ReimbursementReq (
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getDate(5),
						rs.getFloat(6),
						rs.getString(7) == "yes"? true:false,
						rs.getDate(8),
						rs.getString(9) == "yes"? true:false,
						rs.getDate(10),
						rs.getString(11) == "yes"?true:false,
						rs.getDate(12),
						rs.getString(13) == "yes"?true:false
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
	 * This function should be called immediately after any change is made to
	 * the reimbursement request to ensure that the database remains current
	 * @param req
	 * @param table
	 * @return
	 */
	public Integer updateReq(ReimbursementReq req, String table)
	{
		PreparedStatement stmt = null; 
				
		try(Connection conn = Connections.getConnection())
		{
			String sql = "Update reimbursement" + table + " set emp_id = ?, event_id = ?, amnt_req = ?, "
						+ "ds_approve = ?, ds_approve_date = ?, dh_approve =?, dh_approve_date = ?, bc_approve = ?, "
						+ "bc_approve_date = ?, exceed_amount_exception = ?, WHERE reimbursement_id=?";
					stmt = conn.prepareStatement(sql);
					
					stmt.setInt(1, req.getReimbursementId());
					stmt.setInt(2, req.getEmpId());
					stmt.setInt(3,  req.getEventId());
					stmt.setFloat(4, req.getAmountReq());
					stmt.setString(5, req.isDsApprove() == true? "yes":"no");
					stmt.setDate(6, req.getDsApproveDate());
					stmt.setString(7, req.isDhApprove() == true? "yes":"no");
					stmt.setDate(8, req.getDhApproveDate());
					stmt.setString(9, req.isBcApprove() == true? "yes":"no");
					stmt.setDate(10, req.getBcApproveDate());
					stmt.setString(11, req.isExceedAmountApproved() == true? "yes":"no");
					
					return stmt.executeUpdate(); //Returns amount rows effected;
					
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					close(stmt);
				}
		System.out.println("Update is a success!");
				return 0;
	}
}
