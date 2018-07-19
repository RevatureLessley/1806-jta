package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.Connection;
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
			String sql = "SELECT dep_name,dep_id FROM Department";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				dep.insertDep(rs.getString(1),rs.getInt(2));
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
