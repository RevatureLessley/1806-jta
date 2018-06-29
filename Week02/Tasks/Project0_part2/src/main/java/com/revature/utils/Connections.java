package com.revature.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connections {

	private final static String FILE_NAME = "dbprops.properties";
	private static Properties prop = null;
	private static Connection con = null;

	public static Connection getConnection() {

		try {

			prop = new Properties();
			prop.load(new FileInputStream(FILE_NAME));

			// String s = System.getenv("DBProps");
			// String[] props = System.getenv("DBProps").split(";");
			//
			// Class.forName(props[0]);
			// con = DriverManager.getConnection(props[1], props[2], props[3]);

			Class.forName(prop.getProperty("class"));

			con = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"),
					prop.getProperty("password"));

			System.out.println("DATABASE CONNECTION SUCCESS");

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DATABASE CONNECTION FAILED");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Could not find OracleDriver");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return con;
	}

}
