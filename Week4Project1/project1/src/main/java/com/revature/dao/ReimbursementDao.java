package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.Connections;

public class ReimbursementDao
{

	public Integer selectRIdByEmpId() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT r_id FROM reimbursement WHERE rownum = 1 ORDER BY r_id DESC";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				return rs.getInt(1);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}
	
	public Boolean insertReimbursementViaSp(String eventDate, String eventTime, String eventLocation,
											String eventDesc, Integer eventCost, String justification,
											Integer gradeCutoff, Integer empId, Integer eventId,
											Integer gradingFormatId) 
	{
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call insert_into_reimbursement(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			stmt.setInt(1, (selectRIdByEmpId()));
			stmt.setString(2, eventDate);
			stmt.setString(3, eventTime);
			stmt.setString(4, eventLocation);
			stmt.setString(5, eventDesc);
			stmt.setInt(6, eventCost);
			stmt.setString(7, justification);
			stmt.setInt(8, gradeCutoff);
			stmt.setInt(9, 0);
			stmt.setInt(10, empId);
			stmt.setInt(11, eventId);
			stmt.setInt(12, gradingFormatId);
			stmt.setInt(13, 0);
			stmt.setInt(14, 1);
			
			stmt.execute();
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}
		return false;
	}
	
}
