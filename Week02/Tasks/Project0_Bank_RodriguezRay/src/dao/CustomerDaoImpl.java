package dao;

import static util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import beans.CustomerAccount;
import beans.DBAccObj;
import beans.DBCustObj;
import util.Connections;

public class CustomerDaoImpl implements CustomerDao{

	@Override
	public void insertCusomter(CustomerAccount cust) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CustomerAccount selectCustomerById(Integer custId) {
		PreparedStatement psCustomer = null;
		PreparedStatement psAccount = null;
		ResultSet rsCustomer = null;
		ResultSet rsAccount = null;
		String sqlCustomer = "SELECT * FROM customer WHERE customer_id = ?";
		String sqlAccount = "SELECT * FROM account WHERE acc_id = ?";
		
		try(Connection conn = Connections.getConnection()){
			psCustomer = conn.prepareStatement(sqlCustomer);
			psAccount.setInt(1, custId);
			rsCustomer = psCustomer.executeQuery();
			
			DBCustObj dbCustObj=null;
			
			while(rsCustomer.next()){
				dbCustObj = new DBCustObj(rsCustomer.getInt(1), rsCustomer.getDouble(2), rsCustomer.getInt(3), rsCustomer.getInt(4));
			}
			
			psAccount = conn.prepareStatement(sqlAccount);
			psAccount.setInt(1, dbCustObj.getAccId());
			rsAccount = psAccount.executeQuery();
			
			DBAccObj dbAccObj=null;
			
			while(rsAccount.next()) {
				dbAccObj = new DBAccObj(rsAccount.getInt(1), rsAccount.getString(2), rsAccount.getString(3), rsAccount.getString(4),
						rsAccount.getString(5), rsAccount.getString(6));
			}
			
			return new CustomerAccount(dbAccObj.getAccType(), dbAccObj.getFirstName(), dbAccObj.getLastName(),
					dbAccObj.getUserName(), dbAccObj.getUserName(), dbCustObj.getBalance());
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rsCustomer);
			close(psCustomer);
			close(rsAccount);
			close(psAccount);
		}
		return null;
	}

	@Override
	public List<CustomerAccount> selectAllCustomers() {
		PreparedStatement psCustomer = null;
		PreparedStatement psAccount = null;
		ResultSet rsCustomer = null;
		ResultSet rsAccount = null;
		String sqlCustomer = "SELECT * FROM customer";
		String sqlAccount = "SELECT * FROM account WHERE acc_id = ?";
		
		try(Connection conn = Connections.getConnection()){
			psCustomer = conn.prepareStatement(sqlCustomer);
			rsCustomer = psCustomer.executeQuery();
			
			DBCustObj dbCustObj;
			
			while(rsCustomer.next()){
				dbCustObj = new DBCustObj(rsCustomer.getInt(1), rsCustomer.getDouble(2), rsCustomer.getInt(3), rsCustomer.getInt(4));
			}
			
			psAccount = conn.prepareStatement(sqlAccount);
			psAccount.setInt(1, dbCustObj.getAccId());
			rsAccount = psAccount.executeQuery();
			
			DBAccObj dbAccObj;
			
			while(rsAccount.next()) {
				dbAccObj = new DBAccObj(rsAccount.getInt(1), rsAccount.getString(2), rsAccount.getString(3), rsAccount.getString(4),
						rsAccount.getString(5), rsAccount.getString(6));
			}
			
			return new CustomerAccount(dbAccObj.getAccType(), dbAccObj.getFirstName(), dbAccObj.getLastName(),
					dbAccObj.getUserName(), dbAccObj.getUserName(), dbCustObj.getBalance());
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rsCustomer);
			close(psCustomer);
			close(rsAccount);
			close(psAccount);
		}
		return null;
	}

	@Override
	public Integer deleteCustomerById(Integer id) {
		
	}

	@Override
	public Integer updateCustomer(CustomerAccount cust) {
		
	}

	@Override
	public Boolean insertCustomerViaSp(CustomerAccount cust) {
		
	}

}
