<jsp:include page="header.jsp" />
<script src="js/validation.js"></script>

<form method="post" action="Register" id="registerForm" onsubmit="return validateRegistration()">
	First name:<input type="text" name="firstName" /><br/>
	Last name:<input type="text" name="lastName" /><br />
	Username:<input type="text" name="username" /><br/>
	E-mail:<input type="email" name="email" /><br/>
	
	<div id="passwordMessage">
	Login password:<input type="password" name="password" /><br/>
	verify password: <input type="password" name="password2" /><br/>
	</div>

	Phone number:<input type="text" name="phoneNumber" /><br />
	<input type="submit" value="register" />
</form>
