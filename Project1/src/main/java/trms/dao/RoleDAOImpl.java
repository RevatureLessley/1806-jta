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
		java.sql.Connection connection = this.getConnection();
		UserDAO userDAO = new UserDAOImpl();
		
		try {
			// If user does not exist, register the user
			
			if (userDAO.getUserByUUID(user.getUuid()) == null) {
				userDAO.registerUser(user);
				
				// Associate user's UUID with the Supervisor role
				CallableStatement callableStatement = connection.prepareCall("{call insertSupervisor(?)}");
				callableStatement.setString(1, user.getUuid());
				callableStatement.execute();
				return true;
			}
		
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean addUserAsBenefitsCoordinator(User user) {
		java.sql.Connection connection = this.getConnection();
		UserDAO userDAO = new UserDAOImpl();
		
		try {
			// If user does not exist, register the user
			
			if (userDAO.getUserByUUID(user.getUuid()) == null) {
				userDAO.registerUser(user);
				
				// Associate user's UUID with the Benefits Coordinator role
				CallableStatement callableStatement = connection.prepareCall("{call insertBenefitsCoordinator(?)}");
				callableStatement.setString(1, user.getUuid());
				callableStatement.execute();
				return true;
			}
		
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean addUserAsDepartmentHead(User user) {
		java.sql.Connection connection = this.getConnection();
		UserDAO userDAO = new UserDAOImpl();
		
		try {
			// If user does not exist, register the user
			
			if (userDAO.getUserByUUID(user.getUuid()) == null) {
				userDAO.registerUser(user);
				
				// Associate user's UUID with the Department Head role
				CallableStatement callableStatement = connection.prepareCall("{call insertDepartmentHead(?)}");
				callableStatement.setString(1, user.getUuid());
				callableStatement.execute();
				return true;
			}
		
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean addUserAsAdmin(User user) {
		java.sql.Connection connection = this.getConnection();
		UserDAO userDAO = new UserDAOImpl();
		
		try {
			// If user does not exist, register the user
			
			if (userDAO.getUserByUUID(user.getUuid()) == null) {
				userDAO.registerUser(user);
				
				// Associate user's UUID with the Admin role
				CallableStatement callableStatement = connection.prepareCall("{call insertAdmin(?)}");
				callableStatement.setString(1, user.getUuid());
				callableStatement.execute();
				return true;
			}
		
		} catch (SQLException e) {
			return false;
		}
		return false;
	}

	@Override
	public boolean removeUserAsSupervisor(User user) {
		java.sql.Connection connection = this.getConnection();
		UserDAO userDAO = new UserDAOImpl();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call removeSupervisor(?)}"); 
			callableStatement.setString(1, user.getUuid());
			callableStatement.execute();
			return true;
		} catch (SQLException sqle) {
			return false;
		}
	}

	@Override
	public boolean removeUserAsBenefitsCoordinator(User user) {
		java.sql.Connection connection = this.getConnection();
		UserDAO userDAO = new UserDAOImpl();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call removeBenefitsCoordinator(?)}");
			callableStatement.setString(1, user.getUuid());
			callableStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean removeUserAsDepartmentHead(User user) {
		java.sql.Connection connection = this.getConnection();
		UserDAO userDAO = new UserDAOImpl();
		
		try {
			CallableStatement callableStatement = connection.prepareCall("{call removeDeparmentHead(?)}");
			callableStatement.setString(1, user.getUuid());
			callableStatement.execute();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

}
