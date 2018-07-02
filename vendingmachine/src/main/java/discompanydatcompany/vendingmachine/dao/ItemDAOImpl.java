package discompanydatcompany.vendingmachine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import discompanydatcompany.vendingmachine.beans.Inventory;
import discompanydatcompany.vendingmachine.beans.Item;
import discompanydatcompany.vendingmachine.utilities.Connection;


/*table vending_machine_item (
	    item_index number(10) primary key,
	    item_name varchar2(32),
	    item_value number(4),
	    item_cannot_be_sold char(1),
	    item_description varchar2(100),
	    serialized_object blob
	);*/

public class ItemDAOImpl extends Connection implements ItemDAO {

	@Override
	public boolean addItem(Item item) {
		
		try {
			java.sql.Connection connection = getConnection();
			String sql = "insert into vending_machine_item values (null, ?, ?, ?, ?, ?)"; // uuid, serialized object
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, item.getName());
			statement.setInt(2, item.getValue());
			statement.setString(3, item.isSellable());
			statement.setString(4, item.getDescription());
			statement.setObject(5, item);
			
			if (statement.executeUpdate() != 0) {
				return true;
			}
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return false;


	}

	@Override
	public List<Item> getAllItems() {
		ArrayList<Item> items = new ArrayList<Item>();
		
		try {
			java.sql.Connection connection = this.getConnection();
			String sql = "select * from vending_machine_item";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				Item item = (Item) rs.getObject("serialized_object");
				items.add(item);
			}
			
			return items;
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean deleteItem(Item item) {
		
		try {
			java.sql.Connection connection = getConnection();
			
			String sql = "delete vending_machine_item where item_name=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, item.getName());

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
	public boolean updateItem(Item item) {

		try {
			java.sql.Connection connection = getConnection();
			
			String sql = "update vending_machine_item set item_name=?,item_vale=?,item_cannot_be_sold=?,item_description=?,serialized_object=? where item_name=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, item.getName());
			statement.setInt(2, item.getValue());
			statement.setString(3, item.isSellable());
			statement.setString(4, item.getDescription());
			statement.setObject(5, item);
			statement.setString(6, item.getName());

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
