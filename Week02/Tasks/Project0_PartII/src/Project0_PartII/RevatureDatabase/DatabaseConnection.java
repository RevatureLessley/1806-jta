package Project0_PartII.RevatureDatabase;

import java.io.*;
import java.sql.*;

public class DatabaseConnection {
	public static Connection connection = connect();
	
	private static Connection connect() {
		try {
			String args[] = System.getenv("DBARGS").split(";");
			Class.forName(args[0]);
			connection = 
					DriverManager.getConnection(args[1], args[2], args[3]);
	
			return connection;
		}
		
		catch (SQLException e) {
			e.printStackTrace();
		} 
		
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	public static void close(Statement resource){
		if(resource!=null) {
			try{
				resource.close();
			}
			
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(ResultSet resource){
		if(resource!=null) {
			try{
				resource.close();
			}
			
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(FileInputStream resource){
		if(resource!=null) {
			try{
				resource.close();
			}
			
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(Connection resource){
		if(resource!=null){
			try{
				resource.close();
			}
			
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}