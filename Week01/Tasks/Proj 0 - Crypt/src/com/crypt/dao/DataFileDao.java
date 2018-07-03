package com.crypt.dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.crypt.beans.Account;
import com.crypt.beans.DataFile;

public interface DataFileDao {

	List<DataFile> selectAll(Account a) throws SQLException, IOException;

	Boolean insertNewDataFile(DataFile df);

}
