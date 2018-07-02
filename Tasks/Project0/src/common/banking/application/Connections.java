package common.banking.application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
	private static Connection conn = null;

	public static Connection getConnection() {
		
		try {
			
			String props[] = System.getenv("dbprop").split(";");
			Class.forName(props[0]);
			conn = DriverManager.getConnection(props[1],props[2],props[3]);
		    }		    
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Connection failed");
				}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				System.out.println("Class not found. . .");
			}
		
		return conn;
	}
}
