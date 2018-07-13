<%@page import="com.revature.dao.EmployeeDao"%>
<%@page import="com.revature.bean.Employee"%>
<%@page import="com.revature.utils.HtmlTemplates"%>
<%@page import="com.revature.service.UserService"%>
<%@page import="com.revature.service.EventService"%>
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
<script src="../resources/js/eventParse.js"></script>
</head>
<body>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark"> <!-- Brand -->
	<a class="navbar-brand" href="#">Logo</a> <!-- Links -->
	<ul class="navbar-nav">

		<!-- Dropdown -->
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown"> Dropdown link </a>
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

					<table class="table table-hover" id="tableBody">
						<tr><td>no pending requests</td></tr>
					</table>
					
					<a class="btn btn-primary" href="./events.jsp">View All</a>
				</div>
			</div>

			<div class="col-5">

				<h5>User Info</h5>
				<div id="userInfo" class="rsection">
					<%
						Integer id = (Integer) session.getAttribute("userId");
						Employee employee = new EmployeeDao().selectById(id);

						out.print(HtmlTemplates.getUserSummary(employee));
					%>
				</div>
			</div>
		</div>
	</div>

	<script>
		let jsonStr =
	<%Integer userId = (Integer) session.getAttribute("userId");
			out.print(EventService.selectUserEvents(userId));%>

		createTableSmall(jsonStr);
	</script>

</body>
</html>