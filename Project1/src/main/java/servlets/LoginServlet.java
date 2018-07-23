package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.person;
import services.CooperateService;
import services.EmployeeService;
import services.HeadService;
import services.HeadSupervisorService;
import services.PersonService;
import services.SupervisorService;
import util.HtmlTemplates;

/**
 * Servlet implementation class loginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public LoginServlet() {
        
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		int uId = PersonService.userLogin(username, password);
		if(uId != -1){
			PersonService ps = new PersonService();
			person p = ps.buildPersonByID(uId);
			getServletContext().setAttribute("personID", uId);
			
			int sId = p.getSecLvl();
			String test;
			switch (sId) {
			case 1: EmployeeService es = new EmployeeService();
				test = es.getEmployeeJSON(Integer.parseInt(getServletContext().getAttribute("personID").toString()));
				getServletContext().setAttribute("curUser", test);
				response.sendRedirect("employee.html");
				break;
			case 2: SupervisorService ss = new SupervisorService();
				test = ss.getSupervisorJSON(Integer.parseInt(getServletContext().getAttribute("personID").toString()));
				getServletContext().setAttribute("curUser", test);
				response.sendRedirect("supervisor.html");
				break;
			case 3: HeadService hs = new HeadService();
				test = hs.getHeadJSON(Integer.parseInt(getServletContext().getAttribute("personID").toString()));
				getServletContext().setAttribute("curUser", test);
				response.sendRedirect("head.html");
				break;
			case 4: HeadSupervisorService hss = new HeadSupervisorService();
				test = hss.getHeadSupervisorJSON(Integer.parseInt(getServletContext().getAttribute("personID").toString()));
				getServletContext().setAttribute("curUser", test);
				response.sendRedirect("head_supervisor.html");
				break;
			case 5: CooperateService cs = new CooperateService();
				test = cs.getCooperateJSON(Integer.parseInt(getServletContext().getAttribute("personID").toString()));
				getServletContext().setAttribute("curUser", test);
				response.sendRedirect("cooperate.html");
				break;
			}
		}else{
			out.println("<h3 style='color:red'>Login Failed, go back and try again</h3>");
			getServletContext().setAttribute("curUser", null);
			HtmlTemplates.goBackButton(out);
		}
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
}
