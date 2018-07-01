package com.revature.util;

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
			String props[] = System.getenv("DbProps").split(";");
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