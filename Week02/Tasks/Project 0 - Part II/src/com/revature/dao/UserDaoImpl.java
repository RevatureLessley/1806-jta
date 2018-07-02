package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.User;
import com.revature.util.Connections;

public class UserDaoImpl implements UserDao{

	@Override
	public void insertUser(User user) {
		PreparedStatement pStatement = null;
		ResultSet rSet = null;
		String sql = "INSERT INTO Users VALUES (?, ?, ?, ?, ?, ?, ?)";
		
		try(Connection conn = Connections.getConnection()){
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1, user.getId());
			pStatement.setString(2, user.getFname());
			pStatement.setString(3, user.getLname());
			pStatement.setString(4, user.getPassword());
			pStatement.setString(5, user.getEmail());
			pStatement.setInt(6, user.getRole());
			pStatement.setInt(7, user.getState());
			rSet = pStatement.executeQuery();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rSet);
			close(pStatement);
		}
	}

	@Override
	public User selectUserById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Users WHERE UserID = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				return new User(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7)
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
	public List<User> selectAllUsers() {
		Statement stmt = null; // Simple SQL query to be executed
		ResultSet rs = null; //Object that holds query results
		List<User> users = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT * FROM Users";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
						
			

			while(rs.next()){
				User User = new User(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7)
						);
				users.add(User);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
			close(rs);
		}
		return users;
	}

	@Override
	public Integer deleteUserById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean insertUserViaSp(User user) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertIntoUsers(?,?,?,?,?,?)}");
			
			stmt.setString(1, user.getFname());
			stmt.setString(2, user.getLname());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getEmail());
			stmt.setInt(5, user.getRole());
			stmt.setInt(6, user.getState());
			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}

	@Override
	public List<User> selectUsersByState(int state) {
		PreparedStatement ps = null;
		ResultSet rs = null; //Object that holds query results
		List<User> users = new ArrayList<>();
		String sql = "SELECT * FROM Users WHERE State = ?";
		
		try(Connection conn = Connections.getConnection()){	
			ps = conn.prepareStatement(sql);
			ps.setInt(1, state);
			rs = ps.executeQuery();
						
			

			while(rs.next()){
				User User = new User(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7)
						);
				users.add(User);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(ps);
			close(rs);
		}
		return users;
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
	
	@Override
	public void updateUserStateById(Integer id, int state) {
		PreparedStatement ps = null; // Simple SQL query to be executed
		ResultSet rs = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "UPDATE Users SET State = ? WHERE UserID = ?";
			
			ps = conn.prepareStatement(sql);
			ps.setInt(1, state);
			ps.setInt(2, id);
			rs = ps.executeQuery();
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(ps);
			close(rs);
		}
		
	}

}
