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
		rf.setFormStatus2("SUBMITTED");
		rf.setFormStatus3("SUBMITTED");
		
		try(Connection conn = Connections.getConnection()){
			String sql = "INSERT INTO ReimbursementForm (Form_Status, Form_Status2, Form_Status3, Event_Type, "
					+ "Event_Location, Event_Description, Event_Cost, Forms_Date, Start_Date,"
					+ "Grade_Format, Grade_Cut_Off, Work_Time_Missed) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, rf.getFormStatus());
			ps.setString(2, rf.getFormStatus2());
			ps.setString(3, rf.getFormStatus3());
			ps.setString(4, rf.getEventType());
			ps.setString(5, rf.getEventLocation());
			ps.setString(6, rf.getEventDescribtion());
			ps.setInt(7, rf.getEventCost());
			ps.setDate(8, rf.getFormsDate());
			ps.setDate(9, rf.getStartDate());
			ps.setString(10, rf.getGradeCutOff());
			ps.setString(11, rf.getGradeCutOff());
			ps.setString(12, rf.getWorkTimeMissed());
			
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
					rs.getString(7),
					rs.getString(8),
					rs.getInt(9),
					rs.getDate(10),
					rs.getDate(11),
					rs.getString(12),
					rs.getString(13),
					rs.getString(14)
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
		PreparedStatement ps = null;
		
		
		try(Connection conn = Connections.getConnection()){
			String sql = "UPDATE ReimbursementForm SET Form_Status3 = ? WHERE RF_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, urf.getRfId());
			ps.setString(2, urf.getFormStatus3());
			
			
			int up = ps.executeUpdate();
			
			
			log.info(up + "INSERT IS GOOD");
		} catch (SQLException e) {
			log.info("INSERT FAIL");
			e.printStackTrace();
		}finally {
			close(ps);
		}
		
		
	}

	@Override
	public void deleteReimbursementForm(ReimbursementForm drf) {
		
		
	}

	@Override
	public void attachment(ReimbursementForm rf, String file) {
		
		
	}


	


}
