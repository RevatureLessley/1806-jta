<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.revature.service.EventService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" type="text/css" href="../resources/css/main.css">
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<!-- Bring Bootstrap 3 libraries -->
<script src="http://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link rel="import" href="../template/navbar.html">
<script src="../resources/js/events.js"></script>
<script src="../resources/js/common.js"></script>
<title>Welcome to Project1</title>
</head>
<body>



	<div id="navDiv"></div>
	<br>

	<div class="container-fluid justify-content-md-center">

		<h4 style="text-align: center;">My Requests</h4>

		<div class="row justify-content-md-center">

			<!-- areas -->
			<div class="col-11 rsection" style="padding: 0px">
				<table class="table table-hover">
					<thead>
						<tr>
							<th></th>
							<th>Event Id</th>
							<th>Name</th>
							<th>Type</th>
							<th>Amount</th>
							<th>Date</th>
							<th>Status</th>
						</tr>
					</thead>

					<tbody id="tableBody"></tbody>
				</table>

				<div align="right" style="margin: 10px;">
					<button type="reset" class="btn btn-primary" onclick='goBack()'>Back</button>
				</div>
			</div>
		</div>
	</div>

	<script>
		fetchEvents("all", "tableBody", createEventTable, false);
		createNavbar();
	</script>

</body>
</html>