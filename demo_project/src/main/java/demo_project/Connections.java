package demo_project;

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
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn =DriverManager.getConnection(
//				"jdbc:oracle:thin:@localhost:1521:xe",
//				"Salara",
//				"pass");
			
			//Connection via property file
			prop = new Properties();
			prop.load(new FileInputStream(FILE_NAME));
			
//			Class.forName(prop.getProperty("class"));
//			conn = DriverManager.getConnection(
//					prop.getProperty("url"),
//					prop.getProperty("username"),
//					prop.getProperty("password"));
			
			String props[] = System.getenv("DBProps").split(";");
			
			Class.forName(props[0]);
			conn = DriverManager.getConnection(props[1], props[2], props[3]);
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			System.out.println("DATABSE CONNECTION FAILED.");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return conn;
		
	}

}
