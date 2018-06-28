package demo_project;

import java.sql.Connection;

public class Driver 
{
	public static void main(String[] args) throws Exception{
		Connection conn = Connections.getConnection();
		if(conn!=null) {
			conn.close();
		}
		System.out.println("Success!");
	}
}
