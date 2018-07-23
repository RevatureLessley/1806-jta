package trms.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import trms.dao.ApplicationFormDAO;
import trms.dao.ApplicationFormDAOImpl;
import trms.dao.UserDAO;
import trms.dao.UserDAOImpl;

public class ApplicationForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ApplicationForm() {
		super();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cookie[] cookies = request.getCookies();
		String username = "";
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username")) {
				username = cookie.getValue();
			}
		}
		UserDAO userDAO = new UserDAOImpl();
		ApplicationFormDAO applicationFormDAO = new ApplicationFormDAOImpl();
		String formUUID = String.valueOf(UUID.randomUUID());
		String employeeUUID = userDAO.getUserUUIDByUsername(username);
		
		// provide employee UUID
		// generate form UUID
		// for each attachment, 
		Map params = request.getParameterMap();
		Enumeration paramNames = request.getParameterNames();
		List<FileItem> fileItems = null;
		
		String title = "Application Submitted";
		StringBuilder stringBuilder = new StringBuilder();
		StringBuilder formComments = new StringBuilder();
		
	    stringBuilder.append("<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n");

	    stringBuilder.append("<html>\n" +
		            "<head><title>" + title + "</title></head>\n" +
		            "<body bgcolor = \"#f0f0f0\">\n" +
		               "<h1 align = \"center\">" + title + "</h1>\n" +
		               "<ul>\n");
/*	    while(paramNames.hasMoreElements()) {
	    	String name = (String) paramNames.nextElement();
	    	stringBuilder.append("<li><b>" + name + " value: </b>");
	    	stringBuilder.append(request.getParameter(name) + "</li>");
	    }*/
		stringBuilder.append("<li><b>formUUID: </b>" + formUUID + "</li>");
		stringBuilder.append("<li><b>userUUID: </b>" + employeeUUID + "</li>");
		try {
			fileItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : fileItems) {
				if (!item.isFormField()) {
					String fieldName = item.getFieldName();
					String fileName = FilenameUtils.getName(item.getName());
					InputStream fileContent = item.getInputStream();
			    	stringBuilder.append("<li><b>" + fieldName + " value: </b>");
			    	stringBuilder.append(fileName + "</li>");
				} else {
					String fieldName = item.getFieldName();
					String fieldValue = item.getString();
					
					switch (fieldName) {
					case "firstName":
						if (fieldValue != null) {
							formComments.append(fieldValue + " ");
						} else {
							formComments.append("No first name was supplied. ");
						}
						break;
					case "lastName":
						if (fieldValue != null) {
							formComments.append( fieldValue + " ");
						} else {
							formComments.append("No last name was supplied. ");
						}
						break;
					case "relation":
						if (fieldValue != null) {
							formComments.append("The relation to the applicant is " + fieldValue + ". ");
						} else {
							formComments.append("No relation was supplied. ");
						}
						break;
					case "email":
						if (fieldValue != null) {
							formComments.append(" created an application form for reimbursement. (The applicant supplied the following email for contact: " + fieldValue + "). ");
						} else {
							formComments.append("No email was supplied. ");
						}
						break;
					case "fee":
						if (fieldValue != null) {
							formComments.append("the event fee is " + fieldValue + " USD. ");
						} else {
							formComments.append("The fee was not supplied. ");
						}
						break;
					case "eventName":
						if (fieldValue != null) {
							formComments.append("The name of the event is " + fieldValue + " ");
						} else {
							formComments.append("The event name was not supplied. ");
						}
						break;
					case "startDate":
						if (fieldValue != null) {
							formComments.append("The event starts on " + fieldValue + " ");
						} else {
							formComments.append("The start date was not supplied. ");
						}
						break;
					case "endDate":
						if (fieldValue != null) {
							formComments.append("and is expected to end on " + fieldValue + ".");
						} else {
							formComments.append("The end date was not supplied. ");
						}
						break;
					case "eventType":
						if (fieldValue != null) {
							formComments.append(" This is a request to reimburse a " + fieldValue + ". ");
						} else {
							formComments.append("The event type was not supplied. ");
						}
						break;
					case "courseLocation":
						if (fieldValue != null) {
							formComments.append("and located at " + fieldValue + ". ");
						} else {
							formComments.append("The course location was not supplied. ");
						}
						break;
					case "cutOffDate":
						if (fieldValue != null) {
							formComments.append("The anticipated grade cut-off date is " + fieldValue + ". ");
						} else {
							formComments.append("No cut-off date was supplied. ");
						}
						break;
					case "policy":
						if (fieldValue != null) {
							formComments.append("A " + fieldValue + " is expected to complete the course.");
						} else {
							formComments.append("The applicant did not inform whether the event is to be graded or presented. ");
						}
						break;
					case "cutOffDateNotKnown":
						if (fieldValue != null) {
							formComments.append("The cut-off date is not known. ");
						} else {
							formComments.append("The cut-off is known. ");
						}
						break;
					case "electronicSignature":
						if (fieldValue != null) {
							formComments.append("The request was signed with the following name, " + fieldValue + ", on ");
						} else {
							formComments.append("No signature was supplied. ");
						}
						break;
					case "currentDate":
						if (fieldValue != null) {
							formComments.append(fieldValue + ".");
						} else {
							formComments.append(fieldName + "no value supplied.<br/>");
						}
						break;
					default:
						stringBuilder.append("<li><b>" + fieldName + "</b>");
						stringBuilder.append(fieldValue.toString() + "</li>");
						break;
					}

				}
			}
		} catch (FileUploadException e) {
			throw new ServletException("Cannot parse the multiparse request.", e);
		} catch (IOException io) {
			io.printStackTrace();
		}
		applicationFormDAO.submitNewApplicationForm(formUUID, employeeUUID, formComments.toString());
	    stringBuilder.append("</ul>\n" + "<form action=\"index.jsp\" method=\"get\"><input type=\"submit\" value=\"return\"></input></form>"  +  "</body>" + "</html>");
	    out.println(stringBuilder);
	    out.println(formComments.toString());
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
