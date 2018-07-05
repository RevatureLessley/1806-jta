package com.revature.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CloseStreams {
	public static void close(Statement resource){
		if(resource!=null){
			try{
				resource.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	public static void close(ResultSet resource){
		if(resource!=null){
			try{
				resource.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
	public static void close(FileInputStream resource){
		if(resource!=null){
			try{
				resource.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	public static void close(Connection resource){
		if(resource!=null){
			try{
				resource.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
