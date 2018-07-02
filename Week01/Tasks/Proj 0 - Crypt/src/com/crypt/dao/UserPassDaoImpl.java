package com.crypt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.crypt.beans.UserPass;
import com.crypt.util.CloseStreams;
import com.crypt.util.Connections;

public class UserPassDaoImpl extends DAO {

	public static HashMap<String,String> selectAll() throws SQLException {
		HashMap<String, String> ups = new HashMap<>();

		Statement stmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM user_pass");
			
			while(rs.next()) { ups.put(rs.getString(1), rs.getString(2)); }

			return ups;
		}
	}

	public static void insert(UserPass t) {
			PreparedStatement stmt = null; 
			
			try(Connection conn = Connections.getConnection()){
			
				stmt = conn.prepareCall("INSERT INTO user_pass VALUES(?, ?)");
				
				stmt.setString(1, t.getUsername());
				stmt.setString(2, t.getPassword());
				
				stmt.execute();				
			
			}catch(SQLException e){
				e.printStackTrace();
			}finally{
				CloseStreams.close(stmt);
			}		
	}
	

	public UserPass selectById(String id) {

		return null;
	}

	public Integer deleteById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer updateById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean insertViaSp(UserPass t) {
		// TODO Auto-generated method stub
		return false;
	}

}
