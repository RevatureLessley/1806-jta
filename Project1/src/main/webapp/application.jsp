<jsp:include page="header.jsp" />
<script src="js/uuidv4.min.js"></script>
<script src="js/modify.js"></script>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>

<form method="post" action="ApplicationForm">
	<fieldset>
		<legend>[ Employee information ]</legend>
		First name: <input type="text" name="firstName" /><br/>
		Last name: <input type="text" name="lastName" /><br/>
		Employee relation to applicant: <input type="text" name="relation"><br/>
	</fieldset>
	<fieldset>
		<legend>[ Course information ]</legend>
		Course Fee: <input type="number" name="fee" /><br/>
		Start date: <input type="date" name="startDate" /><br/>
		End date: <input type="date" name="endDate" /><br/>
		Physical address the course takes place: <input type="text" name="courseLocation" /><br/>
		Grading policy:<div>
			<input type="radio" id="grade" name="policy" />
			<label for="grade">Grade</label><br/>
			<input type="radio" id="presentation" name="policy" />
			<label for="presentation">Presentation</label>
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
		Electronic signature: <input type="text" id="electronicSignature">
		<input type="date" id="currentDate" value=<%=currentDate%> readonly>
	</fieldset>
	<br />
	<input type="submit" value="submitApplication"/>
</form>