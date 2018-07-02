package discompanydatcompany.vendingmachine.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connection {
	private static java.sql.Connection connection = null;
	public static final String FILE_NAME = "src/resources/database.properties";
	private static Properties properties = null;
	
	public static java.sql.Connection getConnection() {
		try {
			properties = new Properties();
			properties.load(new FileInputStream(FILE_NAME));
			Class.forName(properties.getProperty("class"));
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
			
			return connection;
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
		return null;
	}
}
