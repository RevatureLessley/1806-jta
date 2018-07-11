<jsp:include page="header.jsp" />
<script src="validation.js"></script>

<form method="post" action="Register" id="registerForm" onsubmit="return validateRegistration()">
Username:<input type="text" name="username" /><br/>
E-mail:<input type="email" name="email" /><br/>
Login password:<input type="password1" name="password" /><br/>
<input type="password" name="password2" /><br/>

Verify password:<input type="password" name="verifyPassword" /><br/>
First name:<input type="text" name="firstName" /><br/>
Last name:<input type="text" name="lastName" /><br />
Phone number:<input type="text" name="phoneNumber" /><br />
<input type="submit" value="register" />
</form>
