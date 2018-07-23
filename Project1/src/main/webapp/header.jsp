<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<title>Tuition Reimbursement Management System</title>
</head>
<body id="page-top" class="index">
	  <% 
	   Cookie[] cookies = request.getCookies();
	   String name = "";
	   boolean isAdmin = false;
	   if (cookies != null) {
		   for (Cookie cookie : cookies) {
			   	String cookieName = cookie.getName();
			   	switch (cookieName) {
			   		case "name":
			   			name = cookie.getValue();
			   		break;
			   		case "Admin":
			   			isAdmin = true;
			   		default:
			   		break;
			   	}
		   }
	   }%>
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    MENU
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath }">Tuition Reimbursement Management System</a>            
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
					<li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Information<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          <li><a href="benefits.jsp">View tuition reimbursement benefits</a></li>
                          <li><a href="approvalProcess.jsp">View approval process</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Tuition Reimbursement Forms<b class="caret"></b></a>
                        <ul class="dropdown-menu">
                          <li><a href="application.jsp">Start a new form</a></li>
					      <li><a href="Pending">View pending forms</a></li>
                        </ul>
                    </li>
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Profile<b class="caret"></b></a>
                        <ul class="dropdown-menu">

                              <% if (name.equals("")) { %>
                              	<li ><a href="login.jsp">Login</a></li>
                              	<li><a href="register.jsp">Register a new account</a></li>
                              <% } else {  %>	
	                     		  <li><%=name%></li>
		                          <li><a href="#">Manage Profile</a></li>
		                          <li><a href="Logout">Logout</a></li>
		                          <% if (isAdmin) { %>
		                          <li><a href="#">Modify user accounts</a></li>
		                          <% } %>
	                          <% } %>
                        </ul>
                    </li>           
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
