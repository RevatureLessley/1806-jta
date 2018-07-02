package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.beans.Balance;
import com.revature.util.Connections;

public class BalanceDaoImpl implements BalanceDao {

	@Override
	public void insertBalance(Balance balance) {
		
		
	}

	@Override
	public Balance selectBalanceByUserId(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM UserBalance WHERE User_ID = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (!rs.isBeforeFirst() ) {    
			    return null; 
			} 
			while(rs.next()){
				return new Balance(
						rs.getInt(1),
						rs.getBigDecimal(2),
						rs.getInt(3),
						rs.getInt(4)
						);
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
	public BigDecimal selectBalanceViaFnc(Integer id) {
		Statement stmt = null; // Simple SQL query to be executed
		ResultSet rs = null;
		BigDecimal result = null;
		PreparedStatement ps = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT FindBalance(?) FROM UserBalance WHERE User_ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, id);
			rs = ps.executeQuery();
			
			/*stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);*/
			if (!rs.isBeforeFirst() ) {    
				return null;
			}
			while(rs.next()) {
				result = rs.getBigDecimal(1);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
			close(rs);
		}
		
		return result;
	}

	@Override
	public void updateBalanceAmountById(Integer id, BigDecimal amount) {
		PreparedStatement ps = null; // Simple SQL query to be executed
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "UPDATE UserBalance SET Balance = ? WHERE User_ID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setBigDecimal(1, amount);
			ps.setInt(2, id);
			rs = ps.executeQuery();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(ps);
			close(rs);
		}
		
	}

	@Override
	public void insertIntoBalanceViaSp(Balance balance) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertIntoBalance(?,?,?)}");
			
			stmt.setBigDecimal(1, balance.getBalance());
			stmt.setInt(2, balance.getCardNumber());
			stmt.setInt(3, balance.getUserId());
			
			stmt.execute(); //Returns amount rows effected;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
	}
	
	@Override
	public void commit() {
		Statement stmt = null; // Simple SQL query to be executed
		ResultSet rs = null; //Object that holds query results
		
		try(Connection conn = Connections.getConnection()){
			String sql = "COMMIT";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
			close(rs);
		}
	}

}
