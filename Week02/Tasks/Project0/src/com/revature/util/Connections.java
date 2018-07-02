package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.revature.bank.Bank;


public class Connections 
{
	private static Connection conn = null;
	private final static String FILE_NAME = "dbprops.properties";
	private static Properties prop = null;
	final static Logger logger = Logger.getLogger(Bank.class);
	
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
			
			logger.info("Connection successfully made.");
		} 
		catch ( SQLException e1 ) 
		{
			e1.printStackTrace();
			logger.info("Connected failed to be made.");
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
