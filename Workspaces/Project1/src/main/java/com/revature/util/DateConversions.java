package com.revature.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateConversions {
	
	public static java.sql.Date javaToSql(java.util.Date j) {
		java.sql.Date sqlDate = new java.sql.Date(j.getTime());
		return sqlDate;
	}
	
	public static java.util.Date stringToDate(String s){
		try {
			java.util.Date date = new SimpleDateFormat("yyyy-mm-dd").parse(s);
			return date;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
