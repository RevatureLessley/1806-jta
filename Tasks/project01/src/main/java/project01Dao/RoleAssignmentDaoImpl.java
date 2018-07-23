package project01Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import project01util.Connections;

public class RoleAssignmentDaoImpl implements RoleAssignmentDao{
	final static Logger logger = Logger.getLogger(RoleAssignmentDaoImpl.class);

	@Override
	public int getRole(String username) {
		logger.info("Getting user name and connecting to DB");
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
			logger.info("Can't connect to DB");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Unable to close the connection");
			}
		}
		return -1;
	}

}
