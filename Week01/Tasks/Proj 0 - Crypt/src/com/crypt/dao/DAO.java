package com.crypt.dao;

import static com.crypt.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.crypt.util.Connections;

public class DAO {
	
	public static ResultSet getData(String sql) {
		Statement stmt = null; // Simple SQL query to be executed
		ResultSet rs = null; //Object that holds query results
		
		try(Connection conn = Connections.getConnection()){
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			return rs;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
			close(rs);
		}
		return null;
	}
	
}
