package Project0.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The Connections class is a utility class for establishing a connection with a database.
 * The connection properties are imported using a properties file.
 * @author Vladimir Bukhalo
 *
 */
public class Connections {
	private static Connection conn = null;
	private final static String FILE_NAME = "db_props.properties";
	private static Properties prop = null;
	
	/**
	 * The Connection() establishes a connection with a database using the provided 
	 * db_props.properites file.
	 * @return Returns a connection object.
	 */
	public static Connection getConnection() {
		try {	
			prop = new Properties();
			prop.load(new FileInputStream(FILE_NAME));
			
			Class.forName(prop.getProperty("class"));
			conn = DriverManager.getConnection(
												prop.getProperty("url"), 
												prop.getProperty("username"), 
												prop.getProperty("password"));
			
		}
		catch(SQLException e) {
			System.out.println("Database Connection NOT Successful");
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
