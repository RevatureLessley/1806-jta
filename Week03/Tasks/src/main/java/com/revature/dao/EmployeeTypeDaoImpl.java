package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.beans.EmployeeType;
import com.revature.util.Connections;

public class EmployeeTypeDaoImpl implements EmployeeTypeDao{
	public EmployeeType getEmployeeTypes() {
		Statement stmt = null; 
		ResultSet rs = null;
		EmployeeType emptype = new EmployeeType();
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT * FROM EmployeeType";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				emptype.insertType(rs.getInt(1),rs.getString(2));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
			close(rs);
		}
		return emptype;
	}
}
