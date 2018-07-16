package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.Reimbursement;
import com.revature.util.Connections;
import com.revature.util.DateConversions;

public class ReimbursementDaoImpl {
	public boolean insertReimbursement(Reimbursement r) {
		CallableStatement stmt = null;
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall(
					"{call insertIntoReim(?,?,?,?,?,?,?,?,?,?)}"
					);
			
			java.sql.Date tsqlDate = DateConversions.javaToSql(r.getReimDate());
			java.sql.Date ssqlDate = DateConversions.javaToSql(r.getStartDate());
			
			stmt.setInt(1, r.getEmpId());
			stmt.setDate(2, tsqlDate);
			stmt.setDate(3, ssqlDate);
			stmt.setString(4, r.getLocation());
			stmt.setString(5, r.getDescription());
			stmt.setInt(6, r.getCost());
			stmt.setInt(7, r.getGradeId());
			stmt.setInt(8, r.getTrainId());
			stmt.setString(9, r.getJustification());
			stmt.setInt(10, r.getPassGrade());
			
			
			stmt.execute();
			return true;
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(stmt);
		}
		
		return false;
	}
	
	public List<Reimbursement> selectReimbursementByEmpId(int empId) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM reimbursement WHERE employee_id = ?";
		
		List<Reimbursement> res = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, empId);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Reimbursement r = new Reimbursement(
										rs.getInt(1),
										rs.getInt(2),
										rs.getDate(3),
										rs.getDate(4),
										rs.getString(5),
										rs.getString(6),
										rs.getInt(7),
										rs.getInt(8),
										rs.getInt(9),
										rs.getInt(11),
										rs.getString(10),
										rs.getBoolean(12),
										rs.getBoolean(13),
										rs.getBoolean(14));
				res.add(r);
			}
			 
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			close(rs);
			close(stmt);
		}
		
		return res;
	}
	
	
	

}
