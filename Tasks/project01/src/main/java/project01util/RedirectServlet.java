package project01util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project01Services.UsernameAndPasswordService;

/**
 * Servlet implementation class RedirectServlet
 */
public class RedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		HttpSession session = req.getSession(false);
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		RequestDispatcher rd;
		UsernameAndPasswordService servicer = new UsernameAndPasswordService();

		if(session==null) {res.sendRedirect("../index.html");}

		else{
			switch(servicer.getRole(((String)session.getAttribute("username")))) {
			case 0: rd = request.getRequestDispatcher("user/request.html");  
	                rd.forward(request, response); 
	                break;
	                
			case 1:	rd = request.getRequestDispatcher("user/DirectSupervisor.html");  
                    rd.forward(request, response); 
                    break;
                    
			case 2:	rd = request.getRequestDispatcher("user/DepartmentHead.html");  
                    rd.forward(request, response); 
                    break;
                    
			case 3: rd = request.getRequestDispatcher("user/BenefitsCoordinator.html");  
            		rd.forward(request, response); 
            		break;
            		
			case -1: out.println("<h3 style='color:red'> ERROR IN ROLE ASSIGNMENT</h3>");
					 break;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
