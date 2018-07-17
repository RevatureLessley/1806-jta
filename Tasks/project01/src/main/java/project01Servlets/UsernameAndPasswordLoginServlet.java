package project01Servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import project01Services.UsernameAndPasswordService;

/**
 * Servlet implementation class UsernameAndPasswordLoginServlet
 */
public class UsernameAndPasswordLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UsernameAndPasswordLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UsernameAndPasswordService servicer = new UsernameAndPasswordService();
		String username = null;
		String password = null;

		if(session.isNew()) {
			session.setAttribute("username", request.getParameter("username"));
			session.setAttribute("password", request.getParameter("password"));
			username = request.getParameter("username");
			password = request.getParameter("password");}
		else {
			username = (String)session.getAttribute("username");
			password = (String)session.getAttribute("password");
		}
		

		response.setContentType("text/html");
		RequestDispatcher rd;

		if(username == null | password == null){
			 rd = request.getRequestDispatcher("LogoutUtil");
			 rd.forward(request, response); 
		}
		

		
		if(servicer.loginToAccount(username, password)){
			switch(servicer.getRole(username)) {
			case 0: session.setAttribute("username", request.getParameter("username"));
					rd = request.getRequestDispatcher("user/request.html");  
	                rd.forward(request, response); 
	                break;
	                
			case 1:	session.setAttribute("username", request.getParameter("username"));
					rd = request.getRequestDispatcher("user/DirectSupervisor.html");  
                    rd.forward(request, response); 
                    break;
                    
			case 2:	session.setAttribute("username", request.getParameter("username"));
					rd = request.getRequestDispatcher("user/DepartmentHead.html");  
                    rd.forward(request, response); 
                    break;
                    
			case 3: session.setAttribute("username", request.getParameter("username"));
					rd = request.getRequestDispatcher("user/BenefitsCoordinator.html");  
            		rd.forward(request, response); 
            		break;
            		
			case -1: rd = request.getRequestDispatcher("../LogoutUtil");  
					 rd.forward(request, response); 
					 break;
			}
		}else{
			 rd = request.getRequestDispatcher("LogoutUtil");
			 rd.forward(request, response); 
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
