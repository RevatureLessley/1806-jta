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
			String sql = "SELECT * FROM player FULL OUTER JOIN acc ON player.acc_id = acc.acc_id WHERE player.acc_id >3";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				if(rs.getInt(8) == 1)
				{
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
					PlayerAccount p = new PlayerAccount(rs.getInt(1), rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(2), rs.getInt(3),rs.getInt(4), hasL, lWait);
					players.add(p);
				}
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
		Statement stmt = null; // Simple SQL query to be executed
		ResultSet rs = null; // Object that holds query results
		AdministratorAccount admin = null;
		
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT * FROM admin_acc FULL OUTER JOIN acc ON admin_acc.acc_id = acc.acc_id WHERE admin_acc.acc_id =1";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {

				admin = new AdministratorAccount(rs.getString(3), rs.getString(4), rs.getString(5));
				
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			close(stmt);
			close(rs);
		}
		return admin;
	}

	@Override
	public BankerAccount getBanker() {
		Statement stmt = null; // Simple SQL query to be executed
		ResultSet rs = null; // Object that holds query results
		BankerAccount banker = null;
		
		try(Connection conn = Connections.getConnection())
		{
			String sql ="SELECT * FROM banker FULL OUTER JOIN acc ON banker.acc_id = acc.acc_id WHERE banker.acc_id = 2";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				banker = new BankerAccount(rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(2));
				System.out.println("successfully made banker");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			close(stmt);
			close(rs);
		}
		return banker;
	}

	@Override
	public LoanerAccount getLoaner() {
		// TODO Auto-generated method stub
		return null;
	}

}
