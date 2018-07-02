package p0.dao;

import static p0.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import p0.Connections;
import p0.LoanerAccount;

public class LoanerDaoImpl implements LoanerDao{

	@Override
	public Integer updateLoaner(LoanerAccount loaner) {
	PreparedStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){
			String sql = "Update loaner SET loaner_acc_balance = ?,loaner_interest = ?,\r\n" + 
					"WHERE loaner_id=?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, loaner.getLoanerInfo().getBalance());
			stmt.setDouble(2, loaner.getLoanerInfo().getInterest());
			stmt.setInt(3, 3);
			return stmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}
		return 0;
	}
}
