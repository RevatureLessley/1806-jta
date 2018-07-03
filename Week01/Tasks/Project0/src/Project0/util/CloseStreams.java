package Project0.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The CloseStreams class is a utility class used for closing open streams.
 * @author Vladimir Bukhalo
 *
 */
public class CloseStreams {
	/**
	 * Used for closing a Statement.
	 * @param resourse The Statement to be closed.
	 */
	public static void close(Statement resourse) {
		if(resourse != null) {
			try {
				resourse.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
	/**
	 * Used for closing a ResultSet.
	 * @param resourse The ResultSet to be closed.
	 */
	public static void close(ResultSet resourse) {
		if(resourse != null) {
			try {
				resourse.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}			
		}
	}
	
	/**
	 * Used to close a FileInputStream.
	 * @param resourse The FileInputStream to be closed.
	 */
	public static void close(FileInputStream resourse) {
		if(resourse != null) {
			try {
				resourse.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}			
		}
	}
	
	/**
	 * Used for closing a connection.
	 * @param resourse The connection to be closed.
	 */
	public static void close(Connection resourse) {
		if(resourse != null) {
			try {
				resourse.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}			
		}
	}

}
