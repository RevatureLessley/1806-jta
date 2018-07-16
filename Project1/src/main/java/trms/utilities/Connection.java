package trms.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connection {
	private static java.sql.Connection connection = null;
	//public static final String FILE_NAME = "WEB-INF/database.properties";
	public static Properties properties = null;
	
	public static java.sql.Connection getConnection() {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream("trms/utilities/database.properties");
			// System.out.println(Connection.class.getProtectionDomain().getCodeSource().getLocation().getPath());
			properties = new Properties();
			properties.load(input);
			Class.forName(properties.getProperty("class"));
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
			/*
			String properties[] = System.getenv("DatabaseProperties").split(";");
			Class.forName(properties[0]);
			connection = DriverManager.getConnection(properties[1],properties[2],properties[3]);
			*/
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
