package com.crypt.dao;

import static com.crypt.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crypt.beans.Account;
import com.crypt.beans.Note;
import com.crypt.util.Connections;

public class NoteDaoImpl implements NoteDao {

	@Override
	public List<Note> selectAll(Account a) throws SQLException {
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

	@Override
	public Boolean insertNewNote(Note note) {
		CallableStatement stmt = null; 

		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertIntoNote(?,?)}");

			stmt.setInt(1, note.getAcctId());
			stmt.setString(2, note.getContent());

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