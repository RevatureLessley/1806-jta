package trms.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import oracle.jdbc.OracleTypes;
import trms.beans.User;
import trms.utilities.Connection;

public class FinanceDAOImpl extends Connection implements FinanceDAO {

	@Override
	public Integer getUserAvailableReimbursementsAmount(String uuid) {
		Integer amountAvailable = -1;
		
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectAmountAvailable(?,?)}");
			callableStatement.setString(1, uuid);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			callableStatement.executeQuery();
			ResultSet rs = (ResultSet) callableStatement.getObject(2);
			
			while (rs.next()) {
				amountAvailable = rs.getInt(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return amountAvailable;
	}

	@Override
	public Integer getUserPendingReimbursementsAmount(String uuid) {
		Integer amountPending = -1;
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectPendingAmount(?,?)}");
			callableStatement.setString(1, uuid);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			callableStatement.executeQuery();
			ResultSet rs = (ResultSet) callableStatement.getObject(2);
			
			while (rs.next()) {
				amountPending = rs.getInt(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return amountPending;
	}

	@Override
	public Integer getUserAwardedReimbursementAmount(String uuid) {
		Integer amountAwarded = -1;
		
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectAmountAwarded(?,?)}");
			callableStatement.setString(1, uuid);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			callableStatement.executeQuery();
			ResultSet rs = (ResultSet) callableStatement.getObject(2);
			
			while (rs.next()) {
				amountAwarded = rs.getInt(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return amountAwarded;
	}
	

	@Override
	public Integer getUserExceededReimbursementAmount(String uuid) {
		Integer amountExceeded = -1;
		
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call selectAmountExceeded(?,?)}");
			callableStatement.setString(1, uuid);
			callableStatement.registerOutParameter(2, OracleTypes.CURSOR);
			callableStatement.executeQuery();
			ResultSet rs = (ResultSet) callableStatement.getObject(2);
			
			while (rs.next()) {
				amountExceeded = rs.getInt(1);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return amountExceeded;
	}

	@Override
	public boolean updateAvailableReimbursementsAmount(String uuid, Integer amount) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call updateAmountAvailable(?,?)}");
			callableStatement.setString(1, uuid);
			callableStatement.setInt(2, amount);
			callableStatement.execute();
			return true;
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePendingReimbursementsAmount(String uuid, Integer amount) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call updateAmountPending(?,?)}");
			callableStatement.setString(1, uuid);
			callableStatement.setInt(2, amount);
			callableStatement.execute();
			return true;
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateAwardedReimbursementsAmount(String uuid, Integer amount) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call updateAmountAwarded(?,?)}");
			callableStatement.setString(1, uuid);
			callableStatement.setInt(2, amount);
			callableStatement.execute();
			return true;
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean updateExceededReimbursementAmount(String uuid, Integer amount) {
		try {
			java.sql.Connection connection = this.getConnection();
			CallableStatement callableStatement = connection.prepareCall("{call updateExceededAmount(?,?)}");
			callableStatement.setString(1, uuid);
			callableStatement.setInt(2, amount);
			callableStatement.execute();
			return true;
		} catch (Exception sqle) {
			sqle.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		UserDAO userDAO = new UserDAOImpl();
		FinanceDAO financeDAO = new FinanceDAOImpl();
		List<User> users = userDAO.getAllUsers();
		System.out.println(users.size());
		for (User user : users) {
			int amountAvailable = financeDAO.getUserAvailableReimbursementsAmount(user.getUuid());
			int amountPending = financeDAO.getUserPendingReimbursementsAmount(user.getUuid());
			int amountAwarded = financeDAO.getUserAwardedReimbursementAmount(user.getUuid());
			int amountExceeded = financeDAO.getUserAvailableReimbursementsAmount(user.getUuid());
			
			System.out.println("User: " + user.getUuid() + "amount: " + amountAvailable + '\n'
					           + "awarded: " + amountAwarded + "exceeded: " + amountExceeded);
		}
	}
}
