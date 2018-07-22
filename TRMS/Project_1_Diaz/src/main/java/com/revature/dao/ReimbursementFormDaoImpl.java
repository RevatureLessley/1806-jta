package com.revature.dao;

import static com.revature.util.CloseStreams.close;
import static com.revature.util.LogFourJ.log;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.ReimbursementForm;
import com.revature.util.Connections;

public class ReimbursementFormDaoImpl implements ReimbursementFormDao{

	@Override
	public void insertReimbursementForm(ReimbursementForm rf) {
		PreparedStatement ps = null;
		
		long temp = System.currentTimeMillis();
		Date temp2 = new Date(temp);
		rf.setFormsDate(temp2);
		rf.setFormStatus("SUBMITTED");
		
		try(Connection conn = Connections.getConnection()){
			String sql = "INSERT INTO ReimbursementForm (Form_Status, Event_Type, "
					+ "Event_Location, Event_Description, Event_Cost, Forms_Date, Start_Date,"
					+ "Grade_Format, Grade_Cut_Off, Work_Time_Missed) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, rf.getFormStatus());
			ps.setString(2, rf.getEventType());
			ps.setString(3, rf.getEventLocation());
			ps.setString(4, rf.getEventDescribtion());
			ps.setInt(5, rf.getEventCost());
			ps.setDate(6, rf.getFormsDate());
			ps.setDate(7, rf.getStartDate());
			ps.setString(8, rf.getGradeCutOff());
			ps.setString(9, rf.getGradeCutOff());
			ps.setString(10, rf.getWorkTimeMissed());
			
			int up = ps.executeUpdate();
			
			
			log.info(up + "INSERT IS GOOD");
		} catch (SQLException e) {
			log.info("INSERT FAIL");
			e.printStackTrace();
		}finally {
			close(ps);
		}
		
	}
public List<ReimbursementForm> selectAllReimbursementForm() {
		Statement stmt = null; // Simple SQL query to be executed
		ResultSet rs = null; //Object that holds query results
		List<ReimbursementForm> rfs = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()){
			String sql = "Select * FROM V_Test";


			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);			
			
			while(rs.next()){
				ReimbursementForm rf = new ReimbursementForm(
						rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getInt(7),
						rs.getDate(8),
						rs.getDate(9),
						rs.getString(10),
						rs.getString(11),
						rs.getString(12)
						);
				rfs.add(rf);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
			close(rs);
		}
		
		return rfs;
	}

	@Override
	public void updateReimbursementForm(ReimbursementForm urf) {
		
		
	}

	@Override
	public void deleteReimbursementForm(ReimbursementForm drf) {
		
		
	}

	@Override
	public void attachment(ReimbursementForm rf, String file) {
		
		
	}

	


}
