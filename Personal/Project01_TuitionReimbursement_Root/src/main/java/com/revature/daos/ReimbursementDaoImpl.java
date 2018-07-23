package com.revature.daos;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Reimbursement;
import com.revature.main.Driver;
import com.revature.util.Connections;

public class ReimbursementDaoImpl implements ReimbursementDao {

	public Reimbursement selectReimbursementById(Integer id) {
		Reimbursement reim = null;
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT reimbursement_id, doc_id, emp_id, approver_id, reimbursement_date, "
					+ "reimbursement_location, description, cost, grading_format, event_type,"
					+ "coverage, justification "
					+ "FROM reimbursement "
					+ "WHERE reimbursement_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();

			while(rs.next()){
				reim = new Reimbursement(
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getDouble(8),
						rs.getString(9),
						rs.getString(10),
						rs.getDouble(11),
						rs.getString(12)
						);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(pstmt);
			close(rs);
		}
		
		return reim;
	}

	public List<Reimbursement> selectAllReimbursementByEmpId(Integer id) {
		List<Reimbursement> reims = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT reimbursement_id, doc_id, emp_id, approver_id, reimbursement_date, "
					+ "reimbursement_location, description, cost, grading_format, event_type,"
					+ "coverage, justification "
					+ "FROM reimbursement "
					+ "WHERE emp_id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();

			while(rs.next()){
				Reimbursement reim = new Reimbursement(
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getDouble(8),
						rs.getString(9),
						rs.getString(10),
						rs.getDouble(11),
						rs.getString(12)
						);
				reims.add(reim);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(pstmt);
			close(rs);
		}
		
		return reims;
	}
	
	public List<Reimbursement> selectAllReimbursementByApproverId(Integer id) {
		List<Reimbursement> reims = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT reimbursement_id, doc_id, emp_id, approver_id, reimbursement_date, "
					+ "reimbursement_location, description, cost, grading_format, event_type,"
					+ "coverage, justification "
					+ "FROM reimbursement "
					+ "WHERE approver_id=? AND supervisor_appr = 0";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			
			rs = pstmt.executeQuery();

			while(rs.next()){
				Reimbursement reim = new Reimbursement(
						rs.getInt(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getDouble(8),
						rs.getString(9),
						rs.getString(10),
						rs.getDouble(11),
						rs.getString(12)
						);
				reims.add(reim);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(pstmt);
			close(rs);
		}
		
		return reims;
	}

	public Integer deleteReimbursementById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean insertReimbursementViaSp(Reimbursement reim) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertReimbursement(?,?,?,?,?,?,?,?,?,?)}");
						
			stmt.setInt(1, reim.getEmpId());
			stmt.setInt(2, reim.getApproverId());
			stmt.setString(3, reim.getDate());
			stmt.setString(4, reim.getLocation());
			stmt.setString(5, reim.getDescription());
			stmt.setDouble(6, reim.getCost());
			stmt.setString(7, reim.getGradingFormat());
			stmt.setString(8, reim.getType());
			stmt.setDouble(9, reim.getCoveragePercent());
			stmt.setString(10, reim.getJustification());

			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}
	
	public Boolean updateReimbursementViaSp(Reimbursement reim) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call updateReimbursement(?,?,?,?,?,?,?,?,?,?,?)}");
						
			stmt.setInt(1, reim.getEmpId());
			stmt.setInt(2, reim.getApproverId());
			stmt.setString(3, reim.getDate());
			stmt.setString(4, reim.getLocation());
			stmt.setString(5, reim.getDescription());
			stmt.setDouble(6, reim.getCost());
			stmt.setString(7, reim.getGradingFormat());
			stmt.setString(8, reim.getType());
			stmt.setDouble(9, reim.getCoveragePercent());
			stmt.setString(10, reim.getJustification());
			stmt.setInt(11, reim.getId());

			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}
	
	public Boolean updateReimbursementSupervisorApprovalByIdViaSP(Integer id) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call updateReimSupvApprov(?,?)}");
						
			stmt.setInt(1, id);
			stmt.setInt(2, Driver.loggedIn.getSupVId());
			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}
	
	public Boolean updateReimbursementHeadApprovalByIdViaSP(Integer id) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call updateReimHeadApprov(?,?)}");
						
			stmt.setInt(1, id);
			stmt.setInt(2, Driver.loggedIn.getSupVId());
			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}
	
	public Boolean updateReimbursementBencoApprovalByIdViaSP(Integer id) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call updateReimBencoApprov(?)}");
						
			stmt.setInt(1, id);
			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}

	public Reimbursement selectReimbursementByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
