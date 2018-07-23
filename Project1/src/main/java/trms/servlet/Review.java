package trms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;

import trms.dao.ApplicationFormDAO;
import trms.dao.ApplicationFormDAOImpl;

/**
 * Servlet implementation class Review
 */
public class Review extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Review() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		Map params = request.getParameterMap();
		Enumeration paramNames = request.getParameterNames();
		List<FileItem> fileItems = null;
		String formUUID = String.valueOf(request.getParameter("formUUID"));
		ApplicationFormDAO applicationFormDAO = new ApplicationFormDAOImpl();
		String title = "Reading Data";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n");

	    stringBuilder.append("<html>\n" +
		            "<head><title>" + title + "</title></head>\n" +
		            "<body bgcolor = \"#f0f0f0\">\n" +
		               "<h1 align = \"center\">" + title + "</h1>\n" +
		               "<ul>\n");
	    while (paramNames.hasMoreElements()) {
	    	String name = (String) paramNames.nextElement();
	    	String value = String.valueOf(request.getParameter(name));
	    	
	    	switch (name) {
	    	case "benCoComments":
	    		applicationFormDAO.updateBenefitsCoordinatorComments(formUUID, value);
	    		break;
	    	case "benCoApproval":
	    		applicationFormDAO.updateBenefitsCoordinatorDecision(formUUID, value);
	    		break;
	    	case "departmentHeadComments":
	    		applicationFormDAO.updateBenefitsCoordinatorComments(formUUID, value);
	    		break;
	    	case "deptHeadApproval":
	    		applicationFormDAO.updateDepartmentHeadDecision(formUUID, value);
	    		break;
	    	case "supervisorComments":
	    		applicationFormDAO.updateSupervisorComments(formUUID, value);
	    		break;
	    	case "supervisorApproval":
	    		applicationFormDAO.updateSupervisorDecision(formUUID, value);
	    		break;
	    	}
	    }
	    
	    while (paramNames.hasMoreElements()) {
	    	String name = (String) paramNames.nextElement();
	    	String value = String.valueOf(request.getParameter(name));
	    	stringBuilder.append("<li>" + name + " " + value + "</li>");
	    }
	    stringBuilder.append("</ul>\n" +    "</body>" + "</html>");
	    try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    out.println(stringBuilder.toString());
	}

}
