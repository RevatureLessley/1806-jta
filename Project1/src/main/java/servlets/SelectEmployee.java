package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.EmployeeService;


public class SelectEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public SelectEmployee() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("text");
//		PrintWriter out = response.getWriter();
//		EmployeeService es = new EmployeeService();
//		
//		int id = Integer.parseInt(request.getParameter("foundID"));
//		out.println(es.getEmployeeJSON(id));
		System.out.println(getServletContext().getAttribute("personID"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("why are you here");
	}

}