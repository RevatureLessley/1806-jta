package com.crypt.dao;

import java.sql.SQLException;
import java.util.List;

import com.crypt.beans.Account;
import com.crypt.beans.Note;

public interface NoteDao{

	List<Note> selectAll(Account a) throws SQLException;

	Boolean insertNewNote(Note t);

}
