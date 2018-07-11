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


	<nav class="navbar navbar-expand-sm bg-dark navbar-dark"> <!-- Brand -->
	<a class="navbar-brand" href="#">Logo</a> <!-- Links -->
	<ul class="navbar-nav">

		<!-- Dropdown -->
		<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
			href="#" id="navbardrop" data-toggle="dropdown"> Dropdown link </a>
			<div class="dropdown-menu"></div></li>
	</ul>
	</nav>

	<br>

	<div class="container-fluid justify-content-md-center">

		<h4 style="text-align: center;">All Requests</h4>

		<div class="row justify-content-md-center">

			<!-- areas -->
			<div class="col-11 rsection" style="padding: 0px">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>Event Id</th>
							<th>Name</th>
							<th>Type</th>
							<th>Amount</th>
							<th>Date</th>
						</tr>
					</thead>
					<tbody>
						<%
							out.write("<tr><td>a</td><td>b</td><td>v</td><td>d</td><td>e</td></tr>");
							out.write("<tr><td>a</td><td>b</td><td>v</td><td>d</td><td>e</td></tr>");
							out.write("<tr><td>a</td><td>b</td><td>v</td><td>d</td><td>e</td></tr>");
						%>
					</tbody>
				</table>

				<div align="right" style="margin: 10px;">
					<button type="reset" class="btn btn-primary" onclick='goBack()'>Back</button>
				</div>
			</div>
		</div>
	</div>

	<script>
		function goBack() {
			window.history.back();
		}
	</script>

</body>
</html>