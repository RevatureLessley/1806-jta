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
	// Hard coded to where the database properties file is on local machine
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
		} 
		catch ( SQLException e1 ) 
		{
			e1.printStackTrace();
		}
		catch ( ClassNotFoundException e )
		{
			e.printStackTrace();
		}
		catch ( IOException e2 )
		{
			e2.printStackTrace();
		}
		return conn;
	}
	
}
