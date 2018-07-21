<jsp:include page="header.jsp" />
<script src="js/uuidv4.min.js"></script>
<script src="js/modify.js"></script>
<%@page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>

<form enctype="multipart/form-data" method="post" action="ApplicationForm" id="form">
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
		Course/Event name: <input type="text" name="eventName" /><br/>
		Physical address the course takes place: <input type="text" name="courseLocation" /><br/>
		Event type: <br/>
		<label for="1">certification</label><input type="radio" name="eventType" id="1" value="certification"><br/>
		<label for="2">technical training</label><input type="radio" name="eventType" id="2" value="technicalTraining"><br/>
		<label for="3">university course</label><input type="radio" name="eventType" id="3" value="universityCourse"><br/>
		<label for="4">certification preparation class</label><input type="radio" name="eventType" id="4" value="certificationPreparationClass"><br/>
		<label for="5">seminars</label><input type="radio" name="eventType" id="5" value="seminars"><br/>
		<label for="6">other</label><input type="radio" name="eventType" id="6" value="other"><br/>
		Grading policy:<div>
		<label for="grade">grade</label>
		<input type="radio" id="grade" name="policy" value="grade" /><br/>	
		<label for="presentation">presentation</label>
		<input type="radio" id="presentation" name="policy" value="presentation" /><br/>
		Passing grade cutoff date: <input type="date" name="cutOffDate" /><label for="cutOffDateNotKnown">Not known</label><input type="checkbox" id="cutOffDateNotKnown" name="cutOffDateNotKnown" value="True"/>
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
		Electronic signature: <input type="text" name="electronicSignature" id="electronicSignature">
		<input type="date" id="currentDate" name="currentDate" value=<%=currentDate%> readonly>
	</fieldset>
	<br />
	<input type="submit" value="submitApplication"/>
</form>