<%@page import="com.revature.dao.EmployeeDao"%>
<%@page import="com.revature.bean.Employee"%>
<%@page import="com.revature.utils.HtmlTemplates"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../resources/css/main.css">
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<!-- Bring Bootstrap 3 libraries -->
<script src="http://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<title>Welcome to Project1</title>


</head>
<body>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<!-- Brand -->
		<a class="navbar-brand" href="#">Logo</a>

		<!-- Links -->
		<ul class="navbar-nav">

			<!-- Dropdown -->
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbardrop"
				data-toggle="dropdown"> Dropdown link </a>
				<div class="dropdown-menu">
					<a class="dropdown-item" href="#">User View</a> <a
						class="dropdown-item" href="#">Reimbursement Request</a> <a
						class="dropdown-item" href="#">Employee Requests</a><a
						class="dropdown-item" href="#">Supervisor View</a> <a
						class="dropdown-item" href="#">Reimbursement Confirm</a>
				</div></li>
		</ul>
	</nav>

	<br>

	<div class="container-fluid">
		<div class="row justify-content-md-center">
			<div class="col-5">
				<h5>Reimbursements</h5>
				<div class="rsection">
					<h6>amount remaining: $1000.00</h6> 
					<hr>
					<a class="btn btn-primary" href="./request.html">Make Request</a>
				</div>
				<br>
				<div class="rsection">
					<h6>Current Requests</h6>
					<hr>
					<div id="currentRequests">
						<p>no pending requests</p>
					</div>
					<a class="btn btn-primary" href="./events.jsp">View All</a>
				</div>
			</div>

			<div class="col-5">

				<h5>User Info</h5>
				<div id="userInfo" class="rsection">
					<%
					Integer id = (Integer)session.getAttribute("user_id"); 
					Employee employee = new EmployeeDao().selectById(id);
					System.out.println(employee);
					out.print(HtmlTemplates.getUserSummary(employee));
					%>
				</div>
			</div>
		</div>
	</div>



</body>
</html>