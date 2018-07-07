package com.revature.daos;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.DepartmentHead;
import com.revature.util.Connections;

public class DepartmentHeadDaoImpl implements DepartmentHeadDao {

	public DepartmentHead selectDepartmentHeadById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DepartmentHead> selectAllDepartmentHead() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer deleteDepartmentHeadById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer updateDepartmentHead(DepartmentHead head) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean insertDepartmentHeadViaSp(DepartmentHead head) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertHead(?,?,?,?,?,?,?)}");
						
			stmt.setInt(1, head.getSupVId());
			stmt.setString(2, head.getFirstName());
			stmt.setString(3, head.getLastName());
			stmt.setInt(4, head.getPhone());
			stmt.setString(5, head.getEmail());
			stmt.setString(6, head.getAddress());
			stmt.setString(7, head.getLocation());

			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}

	public DepartmentHead selectDepartmentHeadByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
