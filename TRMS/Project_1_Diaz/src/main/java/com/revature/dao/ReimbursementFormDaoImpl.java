package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import com.revature.beans.Employee;
import com.revature.beans.ReimbursementForm;
import com.revature.util.Connections;

public class ReimbursementFormDaoImpl implements ReimbursementFormDao{

	@Override
	public Boolean insertReimbursementForm(ReimbursementForm rf) {
		
		CallableStatement stmt = null;
		
		try (Connection conn = Connections.getConnection()){
			
			stmt = conn.prepareCall("CALL insertReimbursementForm(?,?,?,?,?)");
			
			stmt.setString(1, rf.getStartDate());
			stmt.setString(2, rf.getFormsDate());
			stmt.setString(3, rf.getGradeCutOff());
			stmt.setString(4, rf.getEventId());
			stmt.setString(5, rf.getEventAttachId());
			
			stmt.executeUpdate(); 
			
		} catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}

	@Override
	public Employee selectReimbursementForm(String rfId) {

		return null;
	}


}
