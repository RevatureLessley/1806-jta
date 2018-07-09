package trms.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;
import trms.beans.Role;
import trms.beans.User;
import trms.utilities.Connection;

public class RoleDAOImpl extends Connection implements RoleDAO {

	@Override
	public List<Role> getUserRoleList() {
		ArrayList<Role> roles = new ArrayList<Role>();
		
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectAllRoles(?)}");
			callableStatement.registerOutParameter(1, OracleTypes.CURSOR);
			callableStatement.executeQuery();
			ResultSet rs = (ResultSet) callableStatement.getObject(2);
			
			while (rs.next()) {
				Role role = new Role();
				UserDAO userDAO = new UserDAOImpl();
				role.setRoleName(rs.getString("employee_role"));
				role.setUser(userDAO.getUserByUUID(rs.getString("employee_uuid")));
				roles.add(role);
			}
			return roles;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean addUserAsSupervisor(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUserAsBenefitsCoordinator(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUserAsDepartmentHead(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addUserAsAdmin(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeUserAsSupervisor(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeUserAsBenefitsCoordinator(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeUserAsDepartmentHead(User user) {
		// TODO Auto-generated method stub
		return false;
	}

}
