<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="trms.dao.ApplicationFormDAO" %>
<%@page import="trms.dao.ApplicationFormDAOImpl" %>
<%@page import="trms.beans.ApplicationForm" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	  <% 
	   Cookie[] cookies = request.getCookies();
	   String name = "";
	   boolean isAdmin = false;
	   boolean isBenefitsCoordinator = false;
	   boolean isDirectSupervisor = false;
	   boolean isDepartmentHead = false;
	   if (cookies != null) {
		   for (Cookie cookie : cookies) {
			   	String cookieName = cookie.getName();
			   	switch (cookieName) {
			   		case "name":
			   			name = cookie.getValue();
			   		break;
			   		case "Admin":
			   			isAdmin = true;
			   		break;
			   		case "BenefitsCoordinator":
			   			isBenefitsCoordinator = true;
			   		break;
			   		case "Supervisor":
			   			isDirectSupervisor = true;
			   		break;
			   		case "DepartmentHead":
			   			isDepartmentHead = true;
			   		break;
			   		case "DeparmentHead":
			   			isDepartmentHead = true;
			   		break;
			   		default:
			   		break;
			   	}
		   }
	   }%>
	<div class="container">
	<div class="col-md-6">
	<form method="post" action="Review">
    <c:if test="${not empty selectedForm}">
	<%  String formUUID = request.getParameter("form");
	ApplicationFormDAO applicationFormDAO = new ApplicationFormDAOImpl();
	ApplicationForm formData = applicationFormDAO.getApplicationForm(formUUID);
	String formCreationDate = formData.getFormCreationDate().toString();
	String formComments = formData.getFormComments();
	String benCoComments = formData.getBenCoComments();
	String supervisorComments = formData.getSupervisorComments();
	String departmentHeadComments = formData.getDepartmentHeadComments();
			%>
	<input size="36" type="text" name="formUUID" id="formUUID" value="<%=formUUID %>" /><br/>
	<input size="36" type="text" name="formCreationDate" id="formCreationDate" value="form created on <%=formCreationDate %>" readonly/><br/>
	<textarea cols="90" rows="25" name="formComments" id="formComments"><%=formComments %></textarea><br/>
	<% if (request.isUserInRole("Admin")) { %>
	<% } %>
	<% if (isBenefitsCoordinator) { %>
	<div>
	<label for="benCoComments">Modify Benefits Coordinator Comments</label>
	<textarea cols="90" rows="25" name="benCoComments" id="benCoComments"><%=benCoComments %></textarea><br/>
	<label for="approve">approve</label>
		<input type="radio" id="approve" name="benCoApproval" value="YES" /><br/>	
		<label for="deny">deny</label>
		<input type="radio" id="deny" name="benCoApproval" value="NO" required/><br/>
	</div>
	<% } %>
	<% if (isDepartmentHead) { %>
		<div>
		<label for=departmentHeadComments>Modify Department Head Comments</label>
		<textarea cols="90" rows="25" name="departmentHeadComments" id="departmenHeadComments"><%=departmentHeadComments %></textarea><br/>
		<label for="approve">approve</label>
		<input type="radio" id="approve" name="deptHeadApproval" value="YES" /><br/>	
		<label for="deny">deny</label>
		<input type="radio" id="deny" name="deptHeadApproval" value="NO" required/><br/>
		</div>
	<% } %>
	<% if (isDirectSupervisor) { %>
	<div>
	<label for="supervisorComments">Modify Supervisor Comments</label>
	<textarea cols="90" rows="25" name="supervisorComments" id="departmentHeadComments"><%=departmentHeadComments %></textarea><br/>
		<label for="approve">approve</label>
		<input type="radio" id="approve" name="supervisorApproval" value="YES" /><br/>	
		<label for="deny">deny</label>
		<input type="radio" id="deny" name="supervisorApproval" value="NO" required/><br/>
	</div>
	<% } %>
	<input type="submit" value="update">
	</form>
	</div>
	<div class="col-md-6">
	<br/><br/>
	<label for="formComments">read-only overview</label>
	<textarea cols="90" rows="25" name="formComments" id="formComments" readonly><%=formComments %></textarea><br/>
	<textarea cols="90" rows="25" name="benCoComments" id="benCoComments" readonly><%=benCoComments %></textarea><br/>
	<textarea cols="90" rows="25" name="supervisorComments" id="supervisoComments" readonly><%=supervisorComments %></textarea><br/>
	<textarea cols="90" rows="25" name="departmenHeadComments" id="departmentHeadComments" readonly><%=departmentHeadComments %></textarea><br/>
	</div>
	</div>
</c:if>