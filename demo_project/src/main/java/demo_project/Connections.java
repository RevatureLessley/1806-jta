package demo_project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connections {
	
	private static Connection conn = null;
	
	public static Connection getConnection() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn =DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe",
				"Salara",
				"pass");
			System.out.println("DATABASE CONNECTION SUCCESS!");
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.out.println("DATABSE CONNECTION FAILED.");
		}
		catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}

}
