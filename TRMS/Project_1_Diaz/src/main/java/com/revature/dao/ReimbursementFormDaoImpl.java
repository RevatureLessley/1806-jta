package com.revature.dao;

import static com.revature.util.CloseStreams.close;
import static com.revature.util.LogFourJ.log;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

import com.revature.beans.ReimbursementForm;
import com.revature.util.Connections;

public class ReimbursementFormDaoImpl implements ReimbursementFormDao{

	@Override
	public void insertReimbursementForm(ReimbursementForm rf) {
		PreparedStatement ps = null;
		
		long temp = System.currentTimeMillis();
		Timestamp temp2 = new Timestamp(temp);
		rf.setFormsDate(temp2);
		rf.setFormStatus("SUBMITTED");
		
		try(Connection conn = Connections.getConnection()){
			String sql = "INSERT INTO ReimbursementForm (Employee_Id, Form_Status, Event_Type, "
					+ "Event_Location, Event_Description, Event_Cost, Forms_Date, Start_Date,"
					+ "Start_Time, Grade_Format, Grade_Cut_Off, Work_Time_Missed) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1,rf.getEmpId());
			ps.setString(2, rf.getFormStatus());
			ps.setString(3, rf.getEventType());
			ps.setString(4, rf.getEventLocation());
			ps.setString(5, rf.getEventDescribtion());
			ps.setInt(6, rf.getEventCost());
			ps.setTimestamp(7, rf.getFormsDate());
			ps.setTimestamp(8, rf.getStartDate());
			ps.setTimestamp(9, rf.getStartTime());
			ps.setString(10, rf.getGradeCutOff());
			ps.setString(11, rf.getGradeCutOff());
			ps.setString(12, rf.getWorkTimeMissed());
			
			
			
			log.info(sql + "INSERT IS GOOD");
		} catch (Exception e) {
			log.info("INSERT FAIL");
			e.printStackTrace();
		}finally {
			close(ps);
		}
		
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
