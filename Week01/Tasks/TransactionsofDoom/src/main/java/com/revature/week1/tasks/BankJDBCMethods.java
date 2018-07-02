package com.revature.week1.tasks;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.week1.tasks.util.Connections;
//import com.revature.week1.tasks.BankMain;

public class BankJDBCMethods {

	private float newBalance;
	private String newName;
	private int newId;
	private boolean newisAdmin;
	private boolean newisApproved;
	private String newPassword;
	static BankMain bkm = new BankMain();
	static ArrayList<User> bUsers = BankMain.users;
	

	
	
	
	
	public User adminCheckUsersJdbc()
	{
		
		User user = null;		
		try(Connection conn = Connections.getConnection())
				{
					Statement stmt = null;
					ResultSet rs = null;
					String sql = "SELECT * FROM USERS";
					 
					stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					rs = stmt.executeQuery(sql);
					
					
					int size = 0;  
		            if (rs != null)   
		            {  
		              
		            rs.last();  
		            size = rs.getRow();
		            rs.beforeFirst();  
		            }
		         
					while(rs.next())
					{
						if(rs.getInt(5) == 0)
						{
							newId = rs.getInt(1);
							newName = rs.getString(2);
							newPassword = rs.getString(3);
							newBalance = rs.getFloat(4);
							if(rs.getInt(5) == 1)
							{
								newisAdmin = true;
							}
							else
							{
								newisAdmin = false;
							}
							
							if(rs.getInt(6) == 1)
							{
								newisApproved = true;
							}
							else
							{
								newisApproved = false;
							}
							
							user = new User(newId, newName, newPassword, newBalance, newisAdmin, newisApproved);
							System.out.println("test admin check and approve");
							
						}
						else
						{
							System.out.println("test admin check its all good");
							continue;
						}
					}
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
		return user;
				}
						
					
	



	
}
	
