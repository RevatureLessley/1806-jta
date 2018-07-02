package com.crypt.Services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.crypt.dao.UserPassDaoImpl;

public class UserPassService {
	public static HashMap<String, String> selectAllUserPass(){
		HashMap<String, String> ups = new HashMap<>();
		try {
			ups = UserPassDaoImpl.selectAll();
			return ups;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("UserPass SQL Failure");
			e.printStackTrace();
		}
		return null;
	}
}
