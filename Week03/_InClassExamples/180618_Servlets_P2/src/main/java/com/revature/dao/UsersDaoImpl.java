package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

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
}
