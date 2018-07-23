<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.revature.service.EventService"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" type="text/css" href="./resources/css/main.css">
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<!-- Bring Bootstrap 3 libraries -->
<script src="http://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<title>Welcome to Project1</title>
<script src="./resources/js/forms.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark"> <!-- Brand -->
	<a class="navbar-brand" href="#">Logo</a> </nav>
	<br>

	<div class="container-fluid">
		<div class="row justify-content-center">
			<div class="col-sm-4 rsection">
				<h3>Login</h3>
				<form action='Login.do' method="post">
					<div id="invalidDiv"></div>
					<div class="form-group">
						<label for="username">username</label> <input class="form-control"
							type="text" name="username" required="required">
					</div>
					<div class="form-group">
						<label for="password">password</label> <input class="form-control"
							type="password" name="password" required="required">
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
					<button type="reset" class="btn btn-secondary">Reset</button>

				</form>
			</div>
		</div>
	</div>

	<script>
		let invalid =
	<%String s = request.getParameter("status");
			if (s == null)
				out.print(0);
			else
				out.print(1);%>
				
		if (invalid) {
			let div = document.getElementById("invalidDiv");
			div.setAttribute("class", "alert alert-danger");
			div.innerHTML = "invalid username or password";
		}
	</script>


</body>
</html>