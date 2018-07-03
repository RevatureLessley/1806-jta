package com.revature.week1.tasks.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connections {
	
	private static Connection conn = null;
	static Properties prop = new Properties();
	
	
	
public static Connection getConnection() {
		
		
		try 
		{
			String props[] = System.getenv("dbprops").split(";");
			Class.forName(props[0]);
			conn = DriverManager.getConnection(props[1],props[2],props[3]);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		//System.out.println("Database connected!");
		return conn;
	}

}
