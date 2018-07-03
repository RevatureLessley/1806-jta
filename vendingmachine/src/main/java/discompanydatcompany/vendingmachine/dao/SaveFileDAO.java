package discompanydatcompany.vendingmachine.dao;

import java.util.List;

import discompanydatcompany.vendingmachine.utilities.SaveFile;

public interface SaveFileDAO {
	public void addSaveFile(SaveFile saveFile);
	public List<SaveFile> getAllSaveFiles();
	public boolean deleteSaveFile(int saveFileIndex);
	boolean updateSaveFile(int fileIndex, SaveFile saveFile);
	boolean saveContent(SaveFile saveFile);
	boolean preparedUpdate(SaveFile saveFile);
}
