package com.crypt.Services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.crypt.beans.Account;
import com.crypt.beans.DataFile;
import com.crypt.dao.DataFileDao;
import com.crypt.dao.DataFileDaoImpl;

public class DataFileService {
	public static Boolean insertNewFile(DataFile df) {
		DataFileDao dfd = new DataFileDaoImpl();
		return dfd.insertNewDataFile(df);
	}
	public static List<DataFile> selectAllFiles(Account a) {
		DataFileDao dfd = new DataFileDaoImpl();
		try {
			return dfd.selectAll(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
