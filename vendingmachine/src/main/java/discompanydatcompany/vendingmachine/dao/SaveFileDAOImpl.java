package discompanydatcompany.vendingmachine.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import discompanydatcompany.vendingmachine.utilities.Connection;
import discompanydatcompany.vendingmachine.utilities.SaveFile;

public class SaveFileDAOImpl extends Connection implements SaveFileDAO {

	@Override
	public void addSaveFile(SaveFile saveFile) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<SaveFile> getAllSaveFiles() {
		ArrayList<SaveFile> saveFiles = new ArrayList<SaveFile>();
		
		try {
			java.sql.Connection connection = this.getConnection();
			String sql = "select * from save_file";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			ResultSet rs = statement.executeQuery(sql);
			
			while(rs.next()) {
				SaveFile save = (SaveFile) rs.getObject("serialized_object");
				saveFiles.add(save);
			}
			
			return saveFiles;
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean deleteSaveFile(int fileIndex) {
		
		try {
			java.sql.Connection connection = getConnection();
			
			String sql = "delete save_file where save_file_index=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, fileIndex);

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
	public boolean updateSaveFile(int fileIndex, SaveFile saveFile) {
		
		try {
			java.sql.Connection connection = getConnection();
			
			String sql = "update save_file set serialized_object=? where save_file_index=?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setObject(1, saveFile);
			statement.setInt(2, fileIndex);

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
	public boolean saveContent(SaveFile saveFile) {
		
		
		return false;
	}
}
