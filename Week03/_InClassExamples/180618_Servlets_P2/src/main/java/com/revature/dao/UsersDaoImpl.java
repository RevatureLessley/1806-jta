package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Npc;
import com.revature.beans.User;
import com.revature.util.Connections;

public class UsersDaoImpl {
	public Boolean insertUserViaSp(User user) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertIntoUser(?,?,?)}");
			
			stmt.setInt(1, user.getId());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());

			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}
	
	public User selectUserByUsername(String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM users WHERE username = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()){
				return new User(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3)
						);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}

}
