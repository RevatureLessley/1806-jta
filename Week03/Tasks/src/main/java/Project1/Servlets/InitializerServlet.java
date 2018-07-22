package Project1.Servlets;

import javax.servlet.*;
import javax.servlet.http.*;

import Project1.DAOs.*;

/**
 * Servlet implementation class InitializerServlet
 */
public class InitializerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		MIMEDAOImp mdi = new MIMEDAOImp();
		mdi.selectMIMETypes();
		EventTypeDAOImp edi = new EventTypeDAOImp();
		edi.selectEventTypes();
	}
}
