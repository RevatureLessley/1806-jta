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
import util.Connections;

public class AdminDaoImpl implements AdminDao{

	@Override
	public void insertAdmin(AdminAccount admin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AdminAccount selectAdminById(Integer adminId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlAdmin = "SELECT account.ACC_ID, account.ACC_TYPE, account.FIRST_NAME, account.LAST_NAME, account.USER_NAME, account.USER_PASSWORD," + 
				"FROM admin JOIN account ON admin.acc_id = account.acc_id;";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sqlAdmin);
			rs = ps.executeQuery();
			
			while(rs.next()){
				return new AdminAccount(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(3), rs.getString(4), rs.getString(5));
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
	public AdminAccount selectAdminByUNandPw(String un, String pw) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT a.ACC_ID, a.ACC_TYPE, a.FIRST_NAME, a.LAST_NAME, a.USER_NAME, a.USER_PASSWORD"+ 
				" FROM account a, admin c "+ 
				"WHERE a.user_name = ? AND a.user_password = ? AND a.acc_id = c.acc_id";
		
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, un);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			
			while(rs.next()){
				return new AdminAccount(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
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
	public List<AdminAccount> selectAllAdmins() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlAdmin = "SELECT account.ACC_ID, account.ACC_TYPE, account.FIRST_NAME, account.LAST_NAME, account.USER_NAME, account.USER_PASSWORD, admin.BALANCE " + 
				"FROM admin JOIN account ON admin.acc_id = account.acc_id;";
		List<AdminAccount> admins = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sqlAdmin);
			rs = ps.executeQuery();
			
			while(rs.next()){
				admins.add(new AdminAccount(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
			}
			return admins;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}

	@Override
	public Integer deleteAdminById(Integer id) {
		return null;
	}

	@Override
	public Integer updateAdmin(AdminAccount admin) {
		return null;
	}

	@Override
	public Boolean insertAdminViaSp(AdminAccount admin) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call insertAdmin(?,?,?,?,?)}");
			
			stmt.setString(1, admin.getAccType());
			stmt.setString(2, admin.getfName());
			stmt.setString(3, admin.getlName());
			stmt.setString(4, admin.getUserName());
			stmt.setString(5, admin.getPassword());
			
			stmt.execute();	//returns amount of rows effected;
		
			return true;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
		}
		return false;
	}

}
