package com.revature.servlets;

import static com.revature.util.LogFourJ.log;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.beans.ReimbursementForm;
import com.revature.dao.ReimbursementFormDaoImpl;

/**
 * Servlet implementation class ReimbursementForm
 */
public class ReimbursementFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReimbursementFormServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append(" Test : ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SimpleDateFormat date = new SimpleDateFormat("mm/dd/yy");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
		String startDate = request.getParameter("Start_Date");
		String startTime = request.getParameter("Start_Time");
		ReimbursementFormDaoImpl rfi = new ReimbursementFormDaoImpl();
		ReimbursementForm rf = new ReimbursementForm();
		Timestamp start = null;
		Timestamp start2 = null;
		
		try {
			start = new Timestamp(date.parse(startDate).getTime());
			start2 = new Timestamp(time.parse(startTime).getTime());
		} catch (ParseException e) {
			log.info(" Time Error ");
			e.printStackTrace();
		}
		
		try {
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
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}finally {
			
		}
		rfi.insertReimbursementForm(rf);
		RequestDispatcher rd = request.getRequestDispatcher("test.html");
		rd.forward(request, response);
	}

}
