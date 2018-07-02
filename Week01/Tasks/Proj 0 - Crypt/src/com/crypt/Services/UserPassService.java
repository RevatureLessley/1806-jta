package com.crypt.Services;

import java.sql.SQLException;
import java.util.HashMap;

import com.crypt.dao.UserPassDaoImpl;

public class UserPassService {
	public static HashMap<String, String> selectAllUserPass(){
		try {
			return UserPassDaoImpl.selectAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
