package com.crypt.Services;

import java.sql.SQLException;
import java.util.HashMap;

import com.crypt.beans.UserPass;
import com.crypt.dao.UserPassDao;
import com.crypt.dao.UserPassDaoImpl;

public class UserPassService {
	public static HashMap<String, String> selectAllUserPass(){
		HashMap<String, String> ups = new HashMap<>();
		try {
			UserPassDao upd = new UserPassDaoImpl();
			ups = upd.selectAll();
			return ups;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("UserPass SQL Failure");
			e.printStackTrace();
		}
		return null;
	}
	public static Boolean insertUserPass(UserPass up) {
		UserPassDao upd = new UserPassDaoImpl();
		return upd.insertUserNewUserPass(up);
	}
}
