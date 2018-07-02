package com.revature.week1.tasks;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import static com.revature.week1.tasks.util.CloseStreams.close;
import com.revature.week1.tasks.util.Connections;




public class BankMain {
	
	
	
	
	static ArrayList<User> users = new ArrayList<User>();
	static ArrayList<User> users2 = new ArrayList<User>();
	transient final static Logger logger = Logger.getRootLogger();
	transient static Connection conn = Connections.getConnection();
	
	
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, IOException 
	{		
		
		intialSetup();
		Bank bank = new Bank(users);
/*
		try 
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("BankUsers.ser"));
			bank = (Bank)ois.readObject();
			ois.close();
		} 
		catch (FileNotFoundException e) 
		{
			intialSetup();
		}*/
		bank.initialSetup();
	}
	
	
	static void intialSetup()
	{
		
		// Note to self: User( "name", "password", balance, isAdmin, isApproved)
		// These users are here largely for testing, but also so that I have an administrator to approve people
		// They also serve as an initial setup for the first time the "app" is run
		users2.add(new User("chris", "password", 9999, true, true));
		users2.add(new User("carol", "kitty", 356, false, true));
		users2.add(new User("jack", "secretpassword", 1500, false, false));
		users2.add(new User("admin", "password", 0, true, true));
		
		try(Connection conn = Connections.getConnection())
		{
			Statement stmt = null;
			ResultSet rs = null;
			String sql = "SELECT * FROM USERS2";
			 
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
			
			
			int size;  
            if (rs != null)   
            {   
	            rs.last();  
	            size = rs.getRow();
	            System.out.println(size);
	            if(size <= 0)
	            {
	            	UserDaoImpl ud = new UserDaoImpl();
	        		String table = null;
	        		
	        		
	        		
	        		for(User user : users2)
	        		{
	        			ud.insertUser(user);
	        			
	        			if(user.isAdmin())
	        			{
	        				table = "adminusers";
	        			}
	        			else if(user.isApproved())
	        			{
	        				table = "approvedusers";
	        			}
	        			else if (!user.isApproved())
	        			{
	        				table = "unapprovedusers";
	        			}
	        			
	        			ud.insertUser(user, table);
	        		}
	            }
            }
		}
	    catch(Exception e)
    	{
    		e.printStackTrace();
    	}
		Step2();
	}
	
	
	public static ArrayList<User> Step2()
	{
		Statement stmt = null; // Simple SQL query to be executed
		ResultSet rs = null; //Object that holds query results
		boolean isIdMatch = false;
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT * FROM users2";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
						
			while(rs.next())
			{
							User user = new User(
									rs.getInt(1),
									rs.getString("user_name"),
									rs.getString("user_password"),
									rs.getFloat(4),
									rs.getInt(5) == 1? true:false,
									rs.getInt(6) == 1? true:false
									);
							users.add(user);
							System.out.println(user);
			}
		
	
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(stmt);
			close(rs);
		}
		
		
		
		return users;
	}
	
	
	// Removed OOS and OIS since the database can serve the same purpose.
	/*static void intialSetup()
	{
		
		Bank bank = new Bank(users);
		
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("BankUsers.ser"));
			oos.writeObject(bank); //Serialize
			oos.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}	
		System.out.println("END OF EXECUTION");
		
		bank.initialSetup();
	}*/
	
	
}
	

