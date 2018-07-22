package com.revature.services;

import static com.revature.util.LogFourJ.log;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.beans.ReimbursementForm;
import com.revature.dao.ReimbursementFormDao;
import com.revature.dao.ReimbursementFormDaoImpl;

public class ReimbursementFormService {
	

	public static boolean createFormEntry(HttpServletRequest request){
		HttpSession session = request.getSession();
		SimpleDateFormat date = new SimpleDateFormat("mm/dd/yy");
		String startDate = request.getParameter("Start_Date");
		String formsDate = request.getParameter("Forms_Date");
		ReimbursementFormDaoImpl rfi = new ReimbursementFormDaoImpl();
		ReimbursementForm rf = new ReimbursementForm();
		Date start = null;
		Date start2 = null;
		
		try {
			start = new Date(date.parse(startDate).getTime());
			start2 = new Date(date.parse(startDate).getTime());
		} catch (ParseException e) {
			log.info(" Time Error ");
			e.printStackTrace();
		}
		rf = new ReimbursementForm(
					(Integer)session.getAttribute("Employee_Id"),
					request.getParameter("Event_Type"),
					request.getParameter("Event_Location"),
					request.getParameter("Event_Description"),
					Integer.parseInt(request.getParameter("Event_Cost")),
					start,
					start2,
					request.getParameter("Grade_Format"),
					request.getParameter("Grade_Cut_Off"),
					request.getParameter("Work_Time_Missed")
					);
		rfi.insertReimbursementForm(rf);
		return true;
	}
	public List<ReimbursementForm> getAllClams(){
		ReimbursementFormDao rfd = new ReimbursementFormDaoImpl();
		List<ReimbursementForm> rfs = rfd.selectAllReimbursementForm();
		return rfs;
	}
	public String getAllJSON(){
		List<ReimbursementForm> rfs = getAllClams();
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(rfs);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
	public static boolean updateStatusBenCo(HttpServletRequest request){
		HttpSession session = request.getSession();
		ReimbursementFormDaoImpl rfi = new ReimbursementFormDaoImpl();
		ReimbursementForm rf = new ReimbursementForm();
		
		rf = new ReimbursementForm(
				Integer.parseInt(request.getParameter("Rf_Id")),
				request.getParameter("Form_Status3")
				);	
		
		rfi.updateReimbursementForm(rf);
		return true;
		
	}
	public static boolean updateStatusDepoHead(HttpServletRequest request){
		HttpSession session = request.getSession();
		ReimbursementFormDaoImpl rfi = new ReimbursementFormDaoImpl();
		ReimbursementForm rf = new ReimbursementForm();
		
		rf = new ReimbursementForm(
				Integer.parseInt(request.getParameter("Rf_Id")),
				request.getParameter("Form_Status2"),
				request.getParameter("Event_Type")
				);	
		
		rfi.updateReimbursementForm2(rf);
		return true;
		
	}
	public static boolean updateStatusAdmin(HttpServletRequest request){
		HttpSession session = request.getSession();
		ReimbursementFormDaoImpl rfi = new ReimbursementFormDaoImpl();
		ReimbursementForm rf = new ReimbursementForm();
		
		rf = new ReimbursementForm(
				Integer.parseInt(request.getParameter("Rf_Id")),
				request.getParameter("FORM_STATUS"),
				request.getParameter("Event_Type"),
				request.getParameter("Event_Location")
				);	
		
		rfi.updateReimbursementForm3(rf);
		return true;
		
	}
}
	
