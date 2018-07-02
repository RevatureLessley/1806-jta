package p0.dao;

import static p0.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import p0.Connections;
import p0.PlayerAccount;

public class PlayerDaoImpl implements PlayerDao {

	@Override
	public Integer updatePlayer(PlayerAccount player) {
		PreparedStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){
			String sql = "Update player SET player_acc_balance = ?,player_bank_balance = ?,\r\n" + 
					"player_loan_balance = ?, player_has_loan = ?, player_loan_waiting = ?,\r\n" + 
					"player_account_approved = ? WHERE player_id=?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, player.getPlayerInfo().getAccountBalance());
			stmt.setInt(2, player.getPlayerInfo().getBankBalance());
			stmt.setInt(3, player.getPlayerInfo().getLoanBalance());
			if(player.getPlayerInfo().isHasLoan())
				stmt.setInt(4, 1);
			else
				stmt.setInt(4, 0);
			if(player.getPlayerInfo().isLoanWaiting())
				stmt.setInt(5, 1);
			else
				stmt.setInt(5, 0);
			if(player.getPlayerInfo().isAccountActive())
				stmt.setInt(6, 1);
			else
				stmt.setInt(6, 0);
			stmt.setInt(7, player.getPlayerInfo().getPlayerID());
		
			return stmt.executeUpdate();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}
		return 0;
	}

	@Override
	public void createPlayer(String name, String Uname, String pword, Integer count) {
		CallableStatement stmt = null;
		CallableStatement stmt2= null;
		try(Connection conn = Connections.getConnection()){
			String sql = "{CALL create_acc(?,?,?)}";
			stmt = conn.prepareCall(sql);
			
			stmt.setString(1, name);
			stmt.setString(2, Uname);
			stmt.setString(3, pword);
			stmt.execute();
			
			String sql2 = "{CALL create_player(?,?,?,?,?,?,?)}";
			stmt2 = conn.prepareCall(sql2);
			
			stmt2.setInt(1, 100);
			stmt2.setInt(2, 100);
			stmt2.setInt(3, 0);
			stmt2.setInt(4, 0);
			stmt2.setInt(5, 0);
			stmt2.setInt(6, 0);
			stmt2.setInt(7, count);
			stmt2.execute();
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}
	}
}
