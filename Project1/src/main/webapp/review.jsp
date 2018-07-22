<jsp:include page="header.jsp" />
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="trms.dao.ApplicationFormDAO" %>
<%@page import="trms.dao.ApplicationFormDAOImpl" %>
<%@page import="trms.beans.ApplicationForm" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
	<% if (request.isUserInRole("Benefits Coordinator")) { %>
	<textarea cols="90" rows="25" name="benCoComments" id="benCoComments"><%=benCoComments %></textarea><br/>
	<label for="approve">approve</label>
		<input type="radio" id="approve" name="benCoApproval" value="YES" /><br/>	
		<label for="deny">deny</label>
		<input type="radio" id="deny" name="benCoApproval" value="NO" required/><br/>
		<button>update decision</button>
	<% } %>
	<% if (request.isUserInRole("Department Head")) { %>
	<textarea cols="90" rows="25" name="supervisorComments" id="supervisoComments"><%=supervisorComments %></textarea><br/>
		<label for="approve">approve</label>
		<input type="radio" id="approve" name="deptHeadApproval" value="YES" /><br/>	
		<label for="deny">deny</label>
		<input type="radio" id="deny" name="deptHeadApproval" value="NO" required/><br/>
		<button>update decision</button>
	<% } %>
	<% if (request.isUserInRole("Direct Supervisor")) { %>
	<textareacols="90" rows="25" name="departmenHeadComments" id="departmentHeadComments"><%=departmentHeadComments %></textarea><br/>
		<label for="approve">approve</label>
		<input type="radio" id="approve" name="supervisorApproval" value="YES" /><br/>	
		<label for="deny">deny</label>
		<input type="radio" id="deny" name="supervisorApproval" value="NO" required/><br/>
		<button>update decision</button>
	<% } %>
	General comments: <textarea cols="90" rows="25" name="formComments" id="formComments" readonly><%=formComments %></textarea><br/>
	<hr>
	Benefits Coordinator: <textarea cols="90" rows="25" name="benCoComments" id="benCoComments" readonly><%=benCoComments %></textarea><br/>
	<hr>
	Direct Supervisor: <textarea cols="90" rows="25" name="supervisorComments" id="supervisoComments" readonly><%=supervisorComments %></textarea><br/>
	<hr>
	Department Head: <textarea cols="90" rows="25" name="departmenHeadComments" id="departmentHeadComments" readonly><%=departmentHeadComments %></textarea><br/>

</c:if>