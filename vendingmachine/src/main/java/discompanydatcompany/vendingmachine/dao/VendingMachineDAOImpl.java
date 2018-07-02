package discompanydatcompany.vendingmachine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import discompanydatcompany.vendingmachine.dao.VendingMachineDAO;
import discompanydatcompany.vendingmachine.utilities.Connection;
import discompanydatcompany.vendingmachine.beans.Inventory;
import discompanydatcompany.vendingmachine.beans.VendingMachine;

public class VendingMachineDAOImpl extends Connection implements VendingMachineDAO{

	@Override
	public boolean addVendingMachine(VendingMachine vendingMachine) {
		
		try {
			java.sql.Connection connection = getConnection();
			String sql = "insert into vending_machine values (?, ?)"; // uuid, serialized object
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vendingMachine.getVendingMachineId());
			statement.setObject(2, vendingMachine);
			
			if (statement.executeUpdate() != 0) {
				return true;
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;

	}

	@Override
	public VendingMachine selectVendingMachineById(VendingMachine vendingMachine) {
		
		try {
			VendingMachine result = null;
			java.sql.Connection connection = getConnection();
			String sql = "select * from vending_machine where uuid=?"; // uuid, serialized object
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, vendingMachine.getVendingMachineId());
			ResultSet rs = statement.executeQuery();
			
			while (rs.next()) {
				result = (VendingMachine) rs.getObject("serialized_object");
			}
			
			return result;
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return null;
	}

	@Override
	public List<VendingMachine> selectAllVendingMachines() {

		ArrayList<VendingMachine> vendingMachines = new ArrayList<VendingMachine>();
		
		try {
			java.sql.Connection connection = this.getConnection();
			String sql = "select * from vending_machine";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				VendingMachine vendingMachine = (VendingMachine) rs.getObject("serialized_object");
				vendingMachines.add(vendingMachine);
			}
			
			return vendingMachines;
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean deleteVendingMachine(String vendingMachineUUID) {

		try {
			java.sql.Connection connection = getConnection();
			
			String sql = "delete vending_machine where uuid=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, vendingMachineUUID);

			if (statement.executeUpdate() != 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}

	@Override
	public boolean deleteVendingMachine(VendingMachine vendingMachine) {

		try {
			java.sql.Connection connection = getConnection();
			
			String sql = "delete vending_machine where uuid=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, vendingMachine.getVendingMachineId());

			if (statement.executeUpdate() != 0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}

	@Override
	public boolean updateVendingMachine(VendingMachine vendingMachine) {

		try {
			java.sql.Connection connection = getConnection();
			
			String sql = "update vending_machine set serialized_object=? where uuid=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setObject(1, vendingMachine);
			statement.setString(2, vendingMachine.getVendingMachineId());

			if (statement.executeUpdate() != 0) {
				
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
}
