package p0.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static p0.CloseStreams.close;
import p0.Connections;


import javax.sql.ConnectionPoolDataSource;

public class AccountListDaoImpl implements AccountListDao {

	@Override
	public Integer getAccSize() {
		Statement stmt = null;
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT count(*) FROM acc";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getInt(1);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			close(stmt);
			close(rs);
		}
		
		return 0;
	}

}
