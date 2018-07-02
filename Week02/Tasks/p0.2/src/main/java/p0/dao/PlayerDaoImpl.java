package p0.dao;

import static p0.CloseStreams.close;

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
					"player_loan_balance = ?, player_has_loan = ?, player_loan_waiting = ?\r\n" + 
					"WHERE player_id=?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setInt(1, player.getPlayerInfo().getAccountBalance());
			stmt.setInt(2, player.getPlayerInfo().getBankBalance());
			stmt.setInt(3, player.getPlayerInfo().getLoanBalance());
			if(player.getPlayerInfo().isHasLoan())
				stmt.setInt(4, 0);
			else
				stmt.setInt(4, 1);
			if(player.getPlayerInfo().isLoanWaiting())
				stmt.setInt(5, 0);
			else
				stmt.setInt(5, 1);
			stmt.setInt(6, player.getPlayerInfo().getPlayerID());
			
			int temp = stmt.executeUpdate();
			sendCommit();
			return temp;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}
		return 0;
	}

	public void sendCommit() {
		PreparedStatement stmt = null;
		try(Connection conn = Connections.getConnection()){
			String sql = "Commit";
			stmt = conn.prepareStatement(sql);
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}
	}
}
