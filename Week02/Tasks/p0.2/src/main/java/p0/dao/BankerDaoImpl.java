package p0.dao;

import static p0.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import p0.BankerAccount;
import p0.Connections;

public class BankerDaoImpl implements BankerDao{

	@Override
	public Integer updateBanker(BankerAccount banker) {
	PreparedStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){
			String sql = "Update banker SET banker_interest = ?,\r\n" + 
					"WHERE loaner_id=?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setDouble(1, banker.getBankInfo().getInterest());
			stmt.setInt(2, 2);
			return stmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}
		return 0;
	}
}
