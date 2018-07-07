package com.revature.daos;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.beans.BenefitsCoordinator;
import com.revature.util.Connections;

public class BenefitsCoordinatorDaoImpl implements BenefitsCoordinatorDao {

	public BenefitsCoordinator selectBenefitsCoordinatorById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<BenefitsCoordinator> selectAllBenefitsCoordinator() {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer deleteBenefitsCoordinatorById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer updateBenefitsCoordinator(BenefitsCoordinator benco) {
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean insertBenefitsCoordinatorViaSp(BenefitsCoordinator benco) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertBenCo(?,?,?,?,?,?,?)}");
						
			stmt.setInt(1, benco.getSupVId());
			stmt.setString(2, benco.getFirstName());
			stmt.setString(3, benco.getLastName());
			stmt.setInt(4, benco.getPhone());
			stmt.setString(5, benco.getEmail());
			stmt.setString(6, benco.getAddress());
			stmt.setString(7, benco.getLocation());

			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}

	public BenefitsCoordinator selectBenefitsCoordinatorByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
