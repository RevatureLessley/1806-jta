package com.revature.servlets;

import static com.revature.util.CloseStreams.close;
import static com.revature.util.LogFourJ.log;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Request;

import com.revature.beans.ReimbursementForm;
import com.revature.services.ReimbursementFormService;
import com.revature.util.Connections;
import com.revature.util.HtmlTemplates;

/**
 * Servlet implementation class updateReimbursementFormServlet
 */
public class updateReimbursementFormServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateReimbursementFormServlet3() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if(ReimbursementFormService.updateStatusAdmin(request)) {
			out.println("<h3 style='color:green'>Clam Update</h3>");
		}else{
			out.println("EEEEERRRRR ");
		}
		HtmlTemplates.goBackButton(out);
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
