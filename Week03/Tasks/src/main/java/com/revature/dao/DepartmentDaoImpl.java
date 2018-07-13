package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.beans.Department;
import com.revature.util.Connections;

public class DepartmentDaoImpl implements DepartmentDao{
	public Department getDepartments() {
		Statement stmt = null; 
		ResultSet rs = null;
		Department dep = new Department();
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT * FROM Department";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				dep.insertDep(rs.getInt(1),rs.getString(2));
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
			close(rs);
		}
		return dep;
	}
}
