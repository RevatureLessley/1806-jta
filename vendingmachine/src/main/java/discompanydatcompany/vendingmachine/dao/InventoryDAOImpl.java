package discompanydatcompany.vendingmachine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import discompanydatcompany.vendingmachine.beans.Inventory;
import discompanydatcompany.vendingmachine.beans.User;
import discompanydatcompany.vendingmachine.beans.VendingMachine;
import discompanydatcompany.vendingmachine.utilities.Connection;
import discompanydatcompany.vendingmachine.utilities.SaveFile;

public class InventoryDAOImpl extends Connection implements InventoryDAO {

	@Override
	public boolean addInventory(User user) {
		
		try {
			java.sql.Connection connection = getConnection();
			String sql = "insert into inventory values (?, ?)"; // uuid, serialized object
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user.getLoginUUID());
			statement.setObject(2, user);
			
			if (statement.executeUpdate() != 0) {
				return true;
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean addInventory(VendingMachine vendingMachine) {
		
		try {
			java.sql.Connection connection = getConnection();
			String sql = "insert into inventory values (?, ?)"; // uuid, serialized object
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
	public List<Inventory> getAllInventories() {
		
		ArrayList<Inventory> saveFiles = new ArrayList<Inventory>();
		
		try {
			java.sql.Connection connection = this.getConnection();
			String sql = "select * from inventory";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				Inventory inventory = (Inventory) rs.getObject("serialized_object");
				saveFiles.add(inventory);
			}
			
			return saveFiles;
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean deleteInventory(String uuid) {
		
		try {
			java.sql.Connection connection = getConnection();
			
			String sql = "delete inventory where uuid=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, uuid);

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
	public boolean deleteInventory(User user) {
		
		try {
			java.sql.Connection connection = getConnection();
			
			String sql = "delete inventory where uuid=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, user.getLoginUUID());

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
	public boolean deleteInventory(VendingMachine vendingMachine) {

		try {
			java.sql.Connection connection = getConnection();
			
			String sql = "delete inventory where uuid=?";
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
	public boolean updateInventory(User user) {

		try {
			java.sql.Connection connection = getConnection();
			
			String sql = "update inventory set serialized_object=? where uuid=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setObject(1, user);
			statement.setString(2, user.getLoginUUID());

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
	public boolean updateInventory(VendingMachine vendingMachine) {
		
		try {
			java.sql.Connection connection = getConnection();
			
			String sql = "update inventory set serialized_object=? where uuid=?";
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
