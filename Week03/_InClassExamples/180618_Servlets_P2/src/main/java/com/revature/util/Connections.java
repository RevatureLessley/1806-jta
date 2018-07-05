package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connections {
	private static Connection conn = null;
	private final static String FILE_NAME = "dbprops.properties";
	private static Properties prop = null;
	
	public static Connection getConnection() {
		try {
			
			//HARD CORDED CONNECTION
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn = DriverManager.getConnection(
//					"jdbc:oracle:thin:@localhost:1521:xe", //url
//					//MAC USERS
//					//"jdbc:oracle:thin:@{awsEndpoint}:1521:orcl"
//					"local_180616", //username
//					"admin" //password
//					);
			
			//Connection via property file
			/*
			 * A property file aids in making for a more dynamic application. As
			 * opposed to having to change the actual code for connection details,
			 * re-compile, then redeploy, a process that could take a day in most 
			 * enterprises. Is now simply having to change an on-computer text file.
			 */
			
//			prop = new Properties();
//			prop.load(new FileInputStream(FILE_NAME));
//			
//			Class.forName(prop.getProperty("class"));
//			conn = DriverManager.getConnection(
//					prop.getProperty("url"),
//					prop.getProperty("username"),
//					prop.getProperty("password"));
			
			String props[] = System.getenv("DBProps").split(";");
			Class.forName(props[0]);
			conn = DriverManager.getConnection(props[1],props[2],props[3]);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ORACLE DRIVER CLASS NOT FOUND.");
		} 
		return conn;
	}
}
