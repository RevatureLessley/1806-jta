package com.crypt.dao;

import static com.crypt.util.CloseStreams.close;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crypt.beans.Account;
import com.crypt.beans.DataFile;
import com.crypt.util.Connections;

public class DataFileDaoImpl implements DataFileDao {
	@Override
	public List<DataFile> selectAll(Account a) throws SQLException, IOException{
		List<DataFile> files = new ArrayList<>();

		Statement stmt = null;
		ResultSet rs = null;

		try(Connection conn = Connections.getConnection()){

			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT * FROM files WHERE file_owner_id="
							+ a.getId()
					);

			while(rs.next()) {
				File f = new File(rs.getString(4));
				InputStream bs = rs.getBlob(5).getBinaryStream();
				byte[] read = new byte[bs.available()];
				bs.read(read);
				
				DataFile file = new DataFile(
						rs.getInt(1), 
						rs.getInt(2),
						rs.getString(3), 
						f,
						read
						);
				files.add(file);
			}
		}

		return files;
	}
	
	@Override
	public Boolean insertNewDataFile(DataFile df) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){
			
			
			stmt = conn.prepareCall("{call insertIntoFiles(?,?,?,?)}");
			
			stmt.setInt(1, df.getOwnerId());
			stmt.setString(2, df.getFileName());
			stmt.setString(3, df.getFilepath().toString());
			stmt.setBinaryStream(4, new ByteArrayInputStream(df.getUploadedData()));

			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}
}
