package com.revature.daos;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.Supervisor;
import com.revature.util.Connections;

public class SupervisorDaoImpl implements SupervisorDao {

	public Supervisor selectSupervisorById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Supervisor> selectAllSupervisor() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer deleteSupervisorById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer updateSupervisor(Supervisor supv) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean insertSupervisorViaSp(Supervisor supv) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertSupervisor(?,?,?,?,?,?,?)}");
						
			stmt.setInt(1, supv.getSupVId());
			stmt.setString(2, supv.getFirstName());
			stmt.setString(3, supv.getLastName());
			stmt.setInt(4, supv.getPhone());
			stmt.setString(5, supv.getEmail());
			stmt.setString(6, supv.getAddress());
			stmt.setString(7, supv.getLocation());

			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}

	public Supervisor selectSupervisorByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
