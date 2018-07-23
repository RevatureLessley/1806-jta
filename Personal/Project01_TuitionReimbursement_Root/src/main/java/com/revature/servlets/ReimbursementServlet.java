package com.revature.servlets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.main.Driver;
import com.revature.services.UserService;

/**
 * Servlet implementation class ReimbursementServlet
 */
public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("===="+this.getServletName()+"====");
		int empId = Driver.loggedIn.getId();
		String evDate = request.getParameter("eventDate");
		String evLoc = request.getParameter("locations");
		String evDesc = request.getParameter("eventDescription");
		double evCost = Double.parseDouble(request.getParameter("eventCost"));
		String evGForm = request.getParameter("formats");;
		String evType = request.getParameter("eventTypes");
		double evCover = 0.0; // poll database for info
		String evJust = request.getParameter("eventJustification");
		
		response.setContentType("text");
		PrintWriter out = response.getWriter();
		
		Employee loggedIn = Driver.loggedIn;
		out.println("<!DOCTYPE html>" + 
				"<html>" + 
				"<head>" + 
				"" + 
				"<meta charset='utf-8'>" + 
				"<meta name='viewport' content='width=device-width, initial-scale=1'>" + 
				"<link rel='stylesheet'" + 
				"	href='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css'>" + 
				"<script" + 
				"	src='https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>" + 
				"<script" + 
				"	src='https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js'></script>" + 
				"<title>Tuition Reimbursement</title>" + 
				"<link rel='stylesheet' type='text/css'" + 
				"	href='/Project01_TuitionReimbursement/resources/css/main.css'>" + 
				"<script src='/Project01_TuitionReimbursement/resources/js/cookie.js'></script>" + 
				"<script src='/Project01_TuitionReimbursement/resources/js/requests.js'></script>" + 
				"<script src='http://code.jquery.com/jquery-latest.min.js'></script>" + 
				"</head>" + 
				"<script>" + 
				"	" + 
				"</script>" + 
				"<body>");
		out.println("<nav class='navbar navbar-inverse'>" + 
				"		<div class='container-fluid'>" + 
				"			<div class='navbar-header'>" + 
				"				<a class='navbar-brand'>Tuition Reimbursement</a>" + 
				"			</div>" + 
				"			<ul class='nav navbar-nav'>" + 
				"				<li class='active'><a" + 
				"					href='/Project01_TuitionReimbursement/user/index.html'>Home</a></li>" + 
				"			</ul>" + 
				"			<ul class='nav navbar-nav navbar-right'>" + 
				"				<li><a" + 
				"					href='/Project01_TuitionReimbursement/user/reimbursement.html'>Create" + 
				"						Reimbursement</a></li>" + 
				"				<li class='dropdown'><a" + 
				"					href='/Project01_TuitionReimbursement/register.html'" + 
				"					class='dropdown-toggle' data-toggle='dropdown'><span" + 
				"						id='navbardrop'></span></a>" + 
				"					<ul class='dropdown-menu'>" + 
				"						<li><a class='glyphicon glyphicon-log-out'" + 
				"							onclick='inValidate()'> Logout</a></li>" + 
				"					</ul></li>" + 
				"			</ul>" + 
				"		</div>" + 
				"	</nav>");
		if(UserService.createReimbursement(new Reimbursement(empId, loggedIn.getSupVId(), evDate, evLoc, evDesc,
				evCost, evGForm, evType, evCover, evJust))){
			out.println("<h3 style='color:green'>Reimbursement " + evType + " CREATED!</h3>");
		}else{
			out.println("<h3 style='color:green'>Reimbursement " + evType + " CREATED!</h3>");
		}
		out.println("</body>" + 
				"<script>" + 
				"	setUser();" + 
				"</script>" + 
				"</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
