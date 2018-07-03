package util;

import static util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.Account;

public class DatabaseUtil {
	public static Account findUserOnDB(String un) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlBanker = "SELECT ACC_TYPE, FIRST_NAME, LAST_NAME, USER_NAME, USER_PASSWORD " + 
				"FROM account WHERE user_name = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sqlBanker);
			ps.setString(1, un);
			rs = ps.executeQuery();
			
			while(rs.next()){
				return new Account(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
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
