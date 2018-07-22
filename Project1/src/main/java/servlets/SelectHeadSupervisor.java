package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.HeadSupervisorService;


public class SelectHeadSupervisor extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public SelectHeadSupervisor() {
        super();
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
		response.setContentType("text");
		PrintWriter out = response.getWriter();
		HeadSupervisorService hss = new HeadSupervisorService();
		
		int id = Integer.parseInt(request.getParameter("foundID"));
		out.println(hss.getHeadSupervisorJSON(id));
	}

}