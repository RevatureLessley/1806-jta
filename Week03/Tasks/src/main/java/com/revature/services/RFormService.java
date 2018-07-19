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
			double propReim,
			String justification,
			int timeMissed,
			int gradeFormat,
			int cutoffGrade,
			int eventTypeId,
			int eventCost
		){
		
	RForm rform = new RForm(empid,rFormDate,place,info,propReim,justification,timeMissed,
			gradeFormat, cutoffGrade, eventTypeId, eventCost);
	RFormDaoImpl rformDao = new RFormDaoImpl();
	
	if(rformDao.insertRFormViaSp(rform)) return true;
	
	return false;
	}
}
