package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Connections 
{
	private static Connection conn = null;
	private final static String FILE_NAME = "C:\\Users\\Kevin\\Desktop\\dbprops.properties";
	private static Properties prop = null;
	
	public static Connection getConnection()
	{
		try
		{			
			prop = new Properties();
			prop.load(new FileInputStream(FILE_NAME));
			
			Class.forName(prop.getProperty("class"));
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("username"),
					prop.getProperty("password")
					);
			System.out.println("CONNECTION STARTED");
			
		} 
		catch ( SQLException e1 ) 
		{
			System.out.println("SQLEXCEPTION CONNECTION FAILED");
			e1.printStackTrace();
		}
		catch ( ClassNotFoundException e )
		{
			System.out.println("CLASSNOTFOUNDEXCEPTION CONNECTION FAILED");
			e.printStackTrace();
		}
		catch ( IOException e2 )
		{
			System.out.println("IOEXCEPTION CONNECTION FAILED");
			e2.printStackTrace();
		}
		return conn;
	}
	
}
