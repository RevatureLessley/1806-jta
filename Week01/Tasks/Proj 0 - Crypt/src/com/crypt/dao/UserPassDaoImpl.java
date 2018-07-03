package com.crypt.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com.crypt.beans.UserPass;
import com.crypt.util.Connections;
import static com.crypt.util.CloseStreams.*;

public class UserPassDaoImpl implements UserPassDao {
	
	@Override
	public HashMap<String,String> selectAll() throws SQLException {
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

	@Override
	public Boolean insertUserNewUserPass(UserPass up) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertIntoUserPass(?,?)}");
			
			stmt.setString(1, up.getUsername());
			stmt.setString(2, up.getPassword());
			
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
