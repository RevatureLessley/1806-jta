package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connections {
	private static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			//Environment variable setup using .bash_profile
			//may have to launch IDE through terminal
			String props[] = System.getenv("AWSVARS").split(";");
			Class.forName(props[0]);
			conn = DriverManager.getConnection(props[1],props[2],props[3]);
			
			//System.out.println("DATABASE CONNECTION SUCCESS!");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("DATABASE CONNECTION FAILED.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ORACLE DRIVER CLASS NOT FOUND.");
		} 
		return conn;
	}
}
