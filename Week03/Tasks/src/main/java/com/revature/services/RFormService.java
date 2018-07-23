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
			int propReim,
			String justification,
			int timeMissed,
			int gradeFormat,
			int cutoffGrade,
			int eventTypeId,
			double eventCost,
			int supid,
			String eventname,
			int depid,
			int finalperc
		){
		
	RForm rform = new RForm(empid,rFormDate,place,info,propReim,justification,timeMissed,
			gradeFormat, cutoffGrade, eventTypeId, eventCost,supid,eventname,depid,finalperc);
	RFormDaoImpl rformDao = new RFormDaoImpl();
	
	if(rformDao.insertRFormViaSp(rform)) return true;
	
	return false;
	}
	
	public static boolean approveRForm(int applvl, int rformid) {
		RFormDaoImpl rformDao = new RFormDaoImpl();
		if(rformDao.approveRForm(applvl, rformid)) return true;
		return false;
	}
	
	public static boolean submitGradeRForm(int grade, int rformid) {
		RFormDaoImpl rformDao = new RFormDaoImpl();
		if(rformDao.submitGrade(grade, rformid)) return true;
		return false;
	}
	
	public static boolean setFileKeyRForm(int rformid, String key) {
		RFormDaoImpl rformDao = new RFormDaoImpl();
		if(rformDao.setFileKey(rformid, key)) return true;
		return false;
	}
}
