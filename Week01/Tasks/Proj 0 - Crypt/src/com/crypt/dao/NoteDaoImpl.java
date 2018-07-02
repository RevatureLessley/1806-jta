package com.crypt.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crypt.beans.Account;
import com.crypt.beans.Note;
import com.crypt.util.Connections;

public class NoteDaoImpl extends DAO {


	public static List<Note> selectAll(Account a) throws SQLException {
		List<Note> notes = new ArrayList<>();
		Statement stmt = null;
		ResultSet rs = null;

		try(Connection conn = Connections.getConnection()){

			stmt = conn.createStatement();
			rs = stmt.executeQuery(
					"SELECT * FROM note WHERE hst_owner_id="
							+ a.getId().toString()
					);			
			while(rs.next()) {
				Note n = new Note(
						rs.getInt(1),
						rs.getInt(2),
						rs.getString(3)
						);
				notes.add(n);
			}
		}

		return notes;

	}


	public void insert(Note t) {
		// TODO Auto-generated method stub

	}


	public Note selectById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer deleteById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	public Integer updateById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	public boolean insertViaSp(Note t) {
		// TODO Auto-generated method stub
		return false;
	}

}
