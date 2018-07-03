package com.crypt.dao;

import java.sql.SQLException;
import java.util.HashMap;

import com.crypt.beans.UserPass;

public interface UserPassDao{

	HashMap<String, String> selectAll() throws SQLException;

	Boolean insertUserNewUserPass(UserPass up);

}
