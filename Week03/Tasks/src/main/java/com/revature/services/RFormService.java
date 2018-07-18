package com.revature.services;

import java.sql.Date;

import com.revature.beans.RForm;
import com.revature.dao.RFormDaoImpl;

public class RFormService {
	public static boolean registerRForm(
			int empid,
			Date rFormDate,
			String place,
			String info,
			int eventId,
			double propReim,
			String justification,
			String filekey,
			int timeMissed
		){
		
	RForm rform = new RForm(empid,rFormDate,place,info,eventId,propReim,justification,filekey,timeMissed);
	RFormDaoImpl rformDao = new RFormDaoImpl();
	
	if(rformDao.insertRFormViaSp(rform)) return true;
	
	return false;
	}
}
