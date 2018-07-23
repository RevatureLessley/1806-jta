package com.revature.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.main.Driver;
import com.revature.services.UserService;

/**
 * Servlet implementation class ProcessDocServlet
 */
public class ProcessDocServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProcessDocServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("===="+this.getServletName()+"====");
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();

		InputStream input = request.getInputStream();
		byte[] buffer = new byte[390000];
		
		for(int length = 0; (length = input.read(buffer))> -1;) {
			output.write(buffer, 0, length);
		}
		
		byte[] rawBytes = output.toByteArray();
		
		UserService.updateReimDocById(Driver.loggedIn.getSelectedReimbursement().getId(), rawBytes);
		
		response.setContentType("text");
		PrintWriter out = response.getWriter();
	}

}
