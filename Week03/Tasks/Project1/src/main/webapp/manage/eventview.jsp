<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="com.revature.service.EventService"%>
<%@page import="com.revature.service.NotificationService"%>
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
<title>Manage Event</title>
<script src="../resources/js/eventview.js"></script>
<script src="../resources/js/common.js"></script>
<script src="../resources/js/messagebuilder.js"></script>
<link rel="import" href="../template/eventview.html">
<link rel="import" href="../template/navbar.html">
<link rel="import" href="../template/manageevent.html">
<link rel="import" href="../template/message.html">
</head>
<body>

<div id="navDiv"></div>
	<br>	
	<div id="container" class="container-fluid"></div>

	<script>
	 	addFromTemplate("navbar", "#navDiv");
	    addFromTemplate("eventview", "#container");
	    addFromTemplate("manageevent", "#container");
	   
	    <%Integer userId = (Integer) session.getAttribute("userId");%>;
	    
	
		let json = <%
				Integer eventId = Integer.parseInt(request.getParameter("eventId"));
				out.print(EventService.selectUserEvent(userId, eventId));%>;
		fillEventData(json);
		fillEventManageData(json);		
		
		json = <%out.print(NotificationService.selectEventNotifications(eventId));%>;
		createMessages(json, "#container");
	</script>	

</body>
</html>