package com.revature.daos;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.BranchManager;
import com.revature.util.Connections;

public class BranchManagerDaoImpl implements BranchManagerDao {

	public BranchManager selectBranchManagerById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BranchManager> selectAllBranchManager() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer deleteBranchManagerById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer updateBranchManager(BranchManager bm) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean insertBranchManagerViaSp(BranchManager bm) {
CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertEmployee(?,?,?,?,?,?,?,?,?,?)}");
			
			stmt.setString(1, "branchmanager");
			stmt.setInt(2, bm.getSupVId());
			stmt.setString(3, bm.getFirstName());
			stmt.setString(4, bm.getLastName());
			stmt.setInt(5, bm.getPhone());
			stmt.setString(6, bm.getEmail());
			stmt.setString(7, bm.getAddress());
			stmt.setString(8, bm.getLocation());
			stmt.setString(9, bm.getUsername());
			stmt.setString(10, bm.getPassword());

			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}

	public BranchManager selectBranchManagerByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
