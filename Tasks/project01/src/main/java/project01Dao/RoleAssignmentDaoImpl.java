package project01Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import project01util.Connections;

public class RoleAssignmentDaoImpl implements RoleAssignmentDao{

	@Override
	public int getRole(String username) {
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT ROLE FROM PROJECT1_ROLES WHERE USERNAME = ?";
			ResultSet rs = null;
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, username);
			rs = stmt.executeQuery();
			rs.next();
			return rs.getInt("ROLE");
		}catch(SQLException e){
			e.printStackTrace();
			System.out.println("Something went wrong with connecting");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Couldn't Close the Connection");
			}
		}
		return -1;
	}

}
