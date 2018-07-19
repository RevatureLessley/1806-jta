package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.beans.EventType;
import com.revature.util.Connections;

public class EventTypeDaoImpl {
	public EventType getEventTypes() {
		Statement stmt = null; 
		ResultSet rs = null;
		EventType eventtype = new EventType();
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT event_type, event_type_id, preimb FROM EventType";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				eventtype.insertType(rs.getString(1),rs.getInt(2),rs.getInt(3));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
			close(rs);
		}
		return eventtype;
	}
}
