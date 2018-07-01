package p0.dao;

import static p0.CloseStreams.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import p0.AdministratorAccount;
import p0.BankerAccount;
import p0.Connections;
import p0.LoanerAccount;
import p0.PlayerAccount;

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

	@Override
	public ArrayList<PlayerAccount> getPlayerArray() {
		Statement stmt = null; // Simple SQL query to be executed
		ResultSet rs = null; // Object that holds query results
		ArrayList<PlayerAccount> players = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT * FROM player FULL OUTER JOIN acc ON player.acc_id = acc.acc_id";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				boolean hasL;
				boolean lWait;
				if(rs.getInt(6) == 0)
				{
					hasL = false;
				}
				else
				{
					hasL = true;
				}
				if(rs.getInt(7) == 0)
				{
					lWait = false;
				}
				else
				{
					lWait = true;
				}
				PlayerAccount p = new PlayerAccount(rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(2), rs.getInt(3),rs.getInt(4), hasL, lWait);
				players.add(p);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			close(stmt);
			close(rs);
		}
		return players;
	}

	@Override
	public AdministratorAccount getAdmin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankerAccount getBanker() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoanerAccount getLoaner() {
		// TODO Auto-generated method stub
		return null;
	}

}
