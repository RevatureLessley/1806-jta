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
	public CustomerAccount selectCustomerById(Integer custId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sqlCustomer = "SELECT account.ACC_TYPE, account.FIRST_NAME, account.LAST_NAME, account.USER_NAME, account.USER_PASSWORD, customer.BALANCE " + 
				"FROM customer JOIN account ON customer.acc_id = account.acc_id;";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sqlCustomer);
			rs = ps.executeQuery();
			
			while(rs.next()){
				return new CustomerAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6));
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
		String sqlCustomer = "SELECT ACC_TYPE, FIRST_NAME, LAST_NAME, USER_NAME, USER_PASSWORD, BALANCE"+ 
				" FROM account a, customer c "+ 
				"WHERE a.user_name = ? AND a.user_password = ? AND a.acc_id = c.acc_id";
		
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sqlCustomer);
			ps.setString(1, un);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			
			while(rs.next()){
				return new CustomerAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6));
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
		String sqlCustomer = "SELECT account.ACC_TYPE, account.FIRST_NAME, account.LAST_NAME, account.USER_NAME, account.USER_PASSWORD, customer.BALANCE " + 
				"FROM customer JOIN account ON customer.acc_id = account.acc_id;";
		List<CustomerAccount> custs = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sqlCustomer);
			rs = ps.executeQuery();
			
			while(rs.next()){
				custs.add(new CustomerAccount(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getDouble(6)));
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
	public Integer updateCustomer(CustomerAccount cust) {
		return null;
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
