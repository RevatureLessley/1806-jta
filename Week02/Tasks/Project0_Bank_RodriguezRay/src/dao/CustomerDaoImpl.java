package dao;

import static util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.CustomerAccount;
import util.Connections;

public class CustomerDaoImpl implements CustomerDao{

	@Override
	public void insertCusomter(CustomerAccount cust) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean withdrawById(Integer accId, Double amount) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlCustomer = "UPDATE customer SET balance=? WHERE acc_id=?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sqlCustomer);
			ps.setInt(1, accId);
			ps.setDouble(2, amount);
			
			if(ps.executeUpdate() > 0)
				return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return false;
	}

	@Override
	public CustomerAccount selectCustomerById(Integer accId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlCustomer = "SELECT account.ACC_ID, account.ACC_TYPE, account.FIRST_NAME, account.LAST_NAME"
				+ "FROM customer JOIN account ON "
				+ "customer.acc_id = account.acc_id"
				+ "WHERE customer.acc_id=?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sqlCustomer);
			ps.setInt(1, accId);
			rs = ps.executeQuery();
			
			while(rs.next()){
				return new CustomerAccount(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getDouble(7), rs.getBoolean(8), rs.getBoolean(9));
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
	public CustomerAccount selectCustomerByUNandPw(String un, String pw) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT account.ACC_ID, account.ACC_TYPE, account.FIRST_NAME, account.LAST_NAME, account.USER_NAME, "
				+"account.USER_PASSWORD, customer.BALANCE, customer.APPROVED, customer.BANNED "
				+"FROM customer JOIN account ON customer.acc_id = account.acc_id "
				+"WHERE account.user_name=? AND account.user_password=?";
		
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, un);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			
			while(rs.next()){
				return new CustomerAccount(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getString(6), rs.getDouble(7), rs.getBoolean(8), rs.getBoolean(9));
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
	public List<CustomerAccount> selectAllCustomers() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT account.ACC_ID, account.ACC_TYPE, account.FIRST_NAME, account.LAST_NAME, account.USER_NAME, "
				+"account.USER_PASSWORD, customer.BALANCE, customer.APPROVED, customer.BANNED "
				+"FROM customer JOIN account ON customer.acc_id = account.acc_id";
		List<CustomerAccount> custs = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()){
				custs.add(new CustomerAccount(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), 
						rs.getString(6), rs.getDouble(7), rs.getBoolean(8), rs.getBoolean(9)));
			}
			return custs;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}

	@Override
	public Integer deleteCustomerById(Integer id) {
		return null;
	}

	@Override
	public Boolean updateCustomer(CustomerAccount cust) {
		PreparedStatement ps = null;
		String sql = "UPDATE customer SET balance=?, approved=?, banned=?"
				+ "WHERE acc_id=?";

		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, cust.getBalance());
			ps.setBoolean(2, cust.isApproved());
			ps.setBoolean(3, cust.isBanned());
			ps.setInt(4, cust.getAccNum());
			
			if(ps.executeUpdate()>0) {
				return true;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(ps);
		}
		return false;
	}

	@Override
	public Boolean insertCustomerViaSp(CustomerAccount cust) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){
			stmt = conn.prepareCall("{call insertCustomer(?,?,?,?,?,?,?,?)}");
			
			stmt.setString(1, cust.getAccType());
			stmt.setString(2, cust.getfName());
			stmt.setString(3, cust.getlName());
			stmt.setString(4, cust.getUserName());
			stmt.setString(5, cust.getPassword());
			stmt.setDouble(6, cust.getBalance());
			stmt.setString(7, cust.isApproved().toString());
			stmt.setString(8, cust.isBanned().toString());
			
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
