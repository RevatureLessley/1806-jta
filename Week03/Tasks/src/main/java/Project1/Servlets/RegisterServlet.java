package Project1.Servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import Project1.Service.EmployeeService;

/**
 * Servlet implementation class RegisterServlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, 
						 HttpServletResponse response) throws ServletException,
															  IOException {
		RequestDispatcher rd  = request.getRequestDispatcher("index.html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String password = null;
		String passwordConfirm = null;
		String supervisor = null;
		
		if(EmployeeService.checkUsername(username)) {
			rd.include(request, response);
			out.println("<script>" + 
							"document.getElementById(\"beforeUsername\")" + 
									".innerHTML=" + 
										"\"Username already exists.\"" + 
						"</script>");
		}
		
		else {
			password = request.getParameter("password");
			passwordConfirm = request.getParameter("passwordConfirm");
			
			if(password.compareTo(passwordConfirm) != 0) {
				rd.include(request, response);
				out.println("<script>" + 
								"document.getElementById(\"beforePassword\")" + 
										".innerHTML=" + 
											"\"Passwords do not match.\"" + 
							"</script>");
			}
			
			else {
				supervisor = request.getParameter("supervisor");
				
				if(!EmployeeService.checkUsername(supervisor)) {
					rd.include(request, response);
					out.println("<script>" + 
									"document.getElementById(\"beforeSupervisor\")" + 
											".innerHTML=" + 
												"\"Supervisor does not exist.\"" + 
								"</script>");
				}
				
				else {
					System.out.println("About to insert.");
					String firstname = request.getParameter("firstname");
					String lastname = request.getParameter("lastname");
					String department = request.getParameter("department");
					String isBenco = 
							request.getParameter("benCo") == null ? "N" : "Y";
			
					EmployeeService.employeeRegister(username, password,
													 firstname, lastname,
													 department, supervisor,
													 isBenco);
					HttpSession session = request.getSession();
					session.setAttribute("username", username);
					rd = request.getRequestDispatcher("user/index.html");
					rd.forward(request, response);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
						  HttpServletResponse response) 
								  throws ServletException, IOException {
		doGet(request, response);
	}
}
