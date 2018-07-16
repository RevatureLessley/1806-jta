package com.trms.util;

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
			prop = new Properties();
			prop.load(new FileInputStream(FILE_NAME));

			Class.forName(prop.getProperty("class"));
			conn = DriverManager.getConnection(
					prop.getProperty("url"),
					prop.getProperty("username"),
					prop.getProperty("password"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ORACLE DRIVER CLASS NOT FOUND.");
		} catch (IOException e){
			e.printStackTrace();
		}
		return conn;
	}
}
