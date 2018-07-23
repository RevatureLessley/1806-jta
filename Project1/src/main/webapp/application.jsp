<jsp:include page="header.jsp" />
<script src="js/uuidv4.min.js"></script>
<script src="js/modify.js"></script>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>

<% String firstName = "";
   String lastName = "";
   String email = "";
   Cookie[] cookies = request.getCookies();
   for (Cookie cookie : cookies) {
   	String name = cookie.getName();
   	switch (name) {
   		case "name":
   			firstName = cookie.getValue();
   			break;
   		case "last":
   			lastName = cookie.getValue();
   			break;
   		case "email":
   			email = cookie.getValue();
   			break;
   		default:
   		 break;
   	}
   }%>
<div class="container">
<form enctype="multipart/form-data" method="post" action="ApplicationForm" id="form">
	<fieldset>
		<legend>[ Employee information ]</legend>
		First name: <input type="text" name="firstName" value=<%=firstName%> readonly /><br/>
		Last name: <input type="text" name="lastName" value=<%=lastName%> readonly /><br/>
		Email: <input type="email" name="email" value=<%=email%> readonly />
		Employee relation to applicant: <input type="text" name="relation" value="self" readonly><br/>
	</fieldset>
	<fieldset>
		<legend>[ Course information ]</legend>
		Course Fee: <input type="number" name="fee" required/><br/>
		Start date: <input type="date" name="startDate" required/><br/>
		End date: <input type="date" name="endDate" required/><br/>
		Course/Event name: <input type="text" name="eventName" required/><br/>
		Physical address the course takes place: <input type="text" name="courseLocation" required/><br/>
		Event type: <br/>
		<label for="1">certification</label><input type="radio" name="eventType" id="1" value="Certification"><br/>
		<label for="2">technical training</label><input type="radio" name="eventType" id="2" value="Technical Training"><br/>
		<label for="3">university course</label><input type="radio" name="eventType" id="3" value="University Course"><br/>
		<label for="4">certification preparation class</label><input type="radio" name="eventType" id="4" value="Certification Preparation Class"><br/>
		<label for="5">seminars</label><input type="radio" name="eventType" id="5" value="Seminar"><br/>
		<label for="6">other</label><input type="radio" name="eventType" id="6" value="Other" required><br/>
		Grading policy:<div>
		<label for="grade">grade</label>
		<input type="radio" id="grade" name="policy" value="grade" /><br/>	
		<label for="presentation">presentation</label>
		<input type="radio" id="presentation" name="policy" value="presentation" required/><br/>
		Passing grade cutoff date: <input type="date" name="cutOffDate" required/><label for="cutOffDateNotKnown">Not known</label><input type="checkbox" id="cutOffDateNotKnown" name="cutOffDateNotKnown" value="True"/>
		</div>
		
	</fieldset>
	<fieldset>
		<legend>[ Attachments ]</legend>
		<div id="attachments">
			<table id="attachmentTable">
			</table>
			<button type="button" onclick="addAttachment()">add attachments (e.g. pdf, png, jpeg, txt, doc, msg)</button>
		</div>
	</fieldset>
	<fieldset id="signature">
		<legend>[ Signature ]</legend>
		<% SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd");
					Date now = new Date();
					String currentDate = "\"" + simpleDate.format(now) + "\"";%>
		Electronic signature: <input type="text" name="electronicSignature" id="electronicSignature" required>
		<input type="date" id="currentDate" name="currentDate" value=<%=currentDate%> readonly>
	</fieldset>
	<br />
	<input type="submit" value="submit application"/>
</form>
</div>