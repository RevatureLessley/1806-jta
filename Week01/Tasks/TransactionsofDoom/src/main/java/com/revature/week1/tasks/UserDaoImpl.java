package com.revature.week1.tasks;
import static com.revature.week1.tasks.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.week1.tasks.util.Connections;

public class UserDaoImpl implements UserDao{
	
	
	
	private int userid;
	private String name;
	private String password;
	private float balance;
	private boolean isAdmin;
	private boolean isApproved;
	//private int adminSqlLnt;
	//private int approvedSqlInt;

	@Override
	public void insertUser(User user, String string)
	{
		// TODO Auto-generated method stub
		String tableSelection = string;
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertInto" + tableSelection +"(?,?,?,?,?,?)}");
			
			stmt.setInt(1, user.getUserid());
			stmt.setString(2, user.getName());
			stmt.setString(3,  user.getPassword());
			stmt.setFloat(4, user.getBalance());
			stmt.setInt(5, user.isAdmin() == true? 1:0);
			stmt.setInt(6, user.isApproved() == true? 1:0);

			
			stmt.execute(); //Returns amount rows effected;
			System.out.println("executed fine");
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		
	}
	public void insertUser(User user)
	{
		// TODO Auto-generated method stub
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertIntousers(?,?,?,?,?)}");
			
			stmt.setString(1, user.getName());
			stmt.setString(2,  user.getPassword());
			stmt.setFloat(3, user.getBalance());
			stmt.setInt(4, user.isAdmin() == true? 1:0);
			stmt.setInt(5, user.isApproved() == true? 1:0);

			
			stmt.execute(); //Returns amount rows effected;
			System.out.println("executed fine");
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		
	}

	@Override
	public User selectUserById(Integer id)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM users WHERE user_id = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next())
			{
				userid = rs.getInt(1);
				name = rs.getString(2);
				password = rs.getString(3);
				balance = rs.getFloat(4);
				if(rs.getInt(5) == 1)
				{
					isAdmin = true;
				}
				else
				{
					isAdmin = false;
				}
				
				if(rs.getInt(6) == 1)
				{
					isApproved = true;
				}
				else
				{
					isApproved = false;
				}
				
				
				return new User(userid, name, password, balance, isAdmin, isApproved);
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
		return null;
	}

	@Override
	public List<User> selectAllUser()
	{
		
		List<User> users = new ArrayList<>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM users";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			//ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next())
			{
				// Since I need booleans, I am adding an extra step here to convert an int to a boolean.
				// Commented out in case I need to reference later
				/*userid = rs.getInt(1);
				name = rs.getString(2);
				password = rs.getString(3);
				balance = rs.getFloat(4);
				if(rs.getInt(5) == 1)
				{
					isAdmin = true;
				}
				else
				{
					isAdmin = false;
				}
				
				if(rs.getInt(6) == 1)
				{
					isApproved = true;
				}
				else
				{
					isApproved = false;
				}*/
				
				
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4), rs.getInt(5) == 1 ? true:false,  rs.getInt(6) == 1 ? true:false);
				users.add(user);
				
			}
			
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
		return users;
	}

	@Override
	public Integer deleteUserById(User user)
	{
		Statement stmt = null;
		ResultSet rs = null;
		
		
		PreparedStatement stmt2 = null;
		ResultSet rs2 = null;
		
		
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT * FROM unapproved_users where user_id = " + user.getUserid();
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					rs = stmt.executeQuery(sql);
					
					if(rs != null)
					{
						rs.last();  
			            int size = rs.getRow();
			            System.out.println(size);
			            if(size == 1)
			            	{
			            	rs.beforeFirst();	
			            	String sql2 = "DELETE FROM unapproved_users WHERE user_id = ?";
			            		stmt2 = conn.prepareStatement(sql2);
								stmt2.setInt(1, user.getUserid());
								stmt2.executeQuery();
			            	}
					}
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					close(stmt);
					close(stmt2);
				}
		System.out.println("Update is a success!");
				return 0;
	}

	@Override
	public Integer updateUser(User user)
	{
		PreparedStatement stmt = null; 
				
		try(Connection conn = Connections.getConnection())
		{
			String sql = "Update users2 set user_name = ?,user_password = ?, user_balance = ?, user_Admin = ?, user_approved = ? WHERE user_id=?";
					stmt = conn.prepareStatement(sql);
					
					stmt.setString(1, user.getName());
					stmt.setString(2, user.getPassword());
					stmt.setFloat(3, user.getBalance());
					stmt.setInt(4, user.isAdmin()? 1:0);
					stmt.setInt(5, user.isApproved()? 1:0);
					stmt.setInt(6,  user.getUserid());
					
					return stmt.executeUpdate(); //Returns amount rows effected;
					
				}catch(SQLException e){
					e.printStackTrace();
				}finally{
					close(stmt);
				}
		System.out.println("Update is a success!");
				return 0;
	}

}
