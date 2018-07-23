package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {

	private static Connection con = null;

	public static Connection getConnection() {

		try {
			String props[] = System.getenv("DBPropsProj1").split(";");

			Class.forName(props[0]);
			con = DriverManager.getConnection(props[1], props[2], props[3]);

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("DATABASE CONNECTION FAILED");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Could not find OracleDriver");
		}

		return con;
	}

}
