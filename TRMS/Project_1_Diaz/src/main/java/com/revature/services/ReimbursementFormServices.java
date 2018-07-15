package com.revature.services;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import com.revature.beans.ReimbursementForm;
import com.revature.dao.ReimbursementFormDaoImpl;

public class ReimbursementFormServices {
	
	public static boolean createClam(HttpServletRequest request){

		ReimbursementFormDaoImpl rfdi = new ReimbursementFormDaoImpl();

	
		
		ReimbursementForm rf = new ReimbursementForm(
				request.getParameter("startDate"),
				request.getParameter("formsDate"),
				request.getParameter("gradeCutOff"),
				request.getParameter("eventId"),
				request.getParameter("eventAttachId")
				
				);
		rfdi.insertReimbursementForm(rf);
		
		return true;
}
	
}
