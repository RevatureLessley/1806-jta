package dao;

import static util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.AdminAccount;
import beans.BankerAccount;
import util.Connections;

public class BankerDaoImpl implements BankerDao{

	@Override
	public void insertBanker(BankerAccount banker) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BankerAccount selectBankerById(Integer bankerId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlBanker = "SELECT account.ACC_TYPE, account.FIRST_NAME, account.LAST_NAME, account.USER_NAME, account.USER_PASSWORD," + 
				"FROM banker JOIN account ON banker.acc_id = account.acc_id;";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sqlBanker);
			rs = ps.executeQuery();
			
			while(rs.next()){
				return new BankerAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}

	@Override
	public List<BankerAccount> selectAllBankers() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlBanker = "SELECT account.ACC_TYPE, account.FIRST_NAME, account.LAST_NAME, account.USER_NAME, account.USER_PASSWORD, banker.BALANCE " + 
				"FROM banker JOIN account ON banker.acc_id = account.acc_id;";
		List<BankerAccount> bankers = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sqlBanker);
			rs = ps.executeQuery();
			
			while(rs.next()){
				bankers.add(new BankerAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
			}
			return bankers;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}

	@Override
	public Integer deleteBankerById(Integer id) {
		return null;
	}

	@Override
	public Integer updateBanker(BankerAccount banker) {
		return null;
	}

	@Override
	public Boolean insertBankerViaSp(BankerAccount banker) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call insertBanker(?,?,?,?,?)}");
			
			stmt.setString(1, banker.getAccType());
			stmt.setString(2, banker.getfName());
			stmt.setString(3, banker.getlName());
			stmt.setString(4, banker.getUserName());
			stmt.setString(5, banker.getPassword());
			
			stmt.execute();	//returns amount of rows effected;
		
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		return false;
	}

	@Override
	public BankerAccount selectBankerByUNandPw(String un, String pw) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlCustomer = "SELECT ACC_TYPE, FIRST_NAME, LAST_NAME, USER_NAME, USER_PASSWORD, BALANCE"+ 
				" FROM account a, banker c "+ 
				"WHERE a.user_name = ? AND a.user_password = ? AND a.acc_id = c.acc_id";
		
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sqlCustomer);
			ps.setString(1, un);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			
			while(rs.next()){
				return new BankerAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
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
