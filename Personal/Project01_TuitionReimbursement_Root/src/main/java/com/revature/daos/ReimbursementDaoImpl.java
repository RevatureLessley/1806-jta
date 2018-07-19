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
import com.revature.util.Connections;

public class ReimbursementDaoImpl implements ReimbursementDao {

	public Reimbursement selectReimbursementById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Reimbursement> selectAllReimbursementById(Integer id) {
		List<Reimbursement> reims = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT reimbursement_id, doc_id, emp_id, reimbursement_date, "
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
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getDouble(7),
						rs.getString(8),
						rs.getString(9),
						rs.getDouble(10),
						rs.getString(11)
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

	public Integer updateReimbursement(Reimbursement reim) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean insertReimbursementViaSp(Reimbursement reim) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertReimbursement(?,?,?,?,?,?,?,?,?)}");
						
			stmt.setInt(1, reim.getEmpId());
			stmt.setString(2, reim.getDate());
			stmt.setString(3, reim.getLocation());
			stmt.setString(4, reim.getDescription());
			stmt.setDouble(5, reim.getCost());
			stmt.setString(6, reim.getGradingFormat());
			stmt.setString(7, reim.getType());
			stmt.setDouble(8, reim.getCoveragePercent());
			stmt.setString(9, reim.getJustification());

			
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
