package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.RForm;
import com.revature.util.Connections;

public class RFormDaoImpl {
	public Boolean insertRFormViaSp(RForm rform) {
CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertNewRForm(?,?,?,?,?,?,?,?,?,?)}");
			
			stmt.setInt(1, rform.getEmpid());
			stmt.setDate(2, rform.getrFormDate());
			stmt.setDate(3, rform.getDeadlineDate());
			stmt.setString(4, rform.getPlace());
			stmt.setString(5, rform.getInfo());
			stmt.setInt(6, rform.getEventId());
			stmt.setDouble(7,rform.getPropReim());
			stmt.setString(8,rform.getJustification());
			stmt.setString(9,rform.getFilekey());
			stmt.setInt(10,rform.getTimeMissed());
			

			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}
}
