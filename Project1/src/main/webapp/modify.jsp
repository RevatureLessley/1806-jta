<jsp:include page="header.jsp" />
<script src="js/uuidv4.min.js"></script>
<script src="js/modify.js"></script>

<form method="post">
	<fieldset>
		<legend>Employee information</legend>
		First name:<input type="text" name="firstName" /><br/>
		Last name:<input type="text" name="lastName" /><br/>
		Employee relation<input type="text" name="relation"><br/>
	</fieldset>
	<fieldset>
		<legend>Course information</legend>
		Course Fee:<input type="number" name="fee" /><br/>
		Start date:<input type="date" name="startDate" /><br/>
		End date:<input type="date" name="endDate" /><br/>
		Physical address the course takes place:<input type="text" name="courseLocation" /><br/>
		Grading policy:<div>
			<input type="checkbox" id="grade" name="grading" />
			<label for="grade">Grade</label>
			<input type="checkbox" id="presentation" name="presentation" />
			<label for="presentation">Presentation</label>
		</div>
		
	</fieldset>
	<fieldset>
		<legend>Attachments</legend>
		<div id="attachments">
			<table id="attachmentTable">
			</table>
			<button type="button" onclick="addAttachment()">add attachments (e.g. pdf, png, jpeg, txt, doc, msg)</button>
		</div>
	</fieldset>
	<fieldset>
		<legend>Signature</legend>
	</fieldset>
</form>

<input type="submit" value="newForm" action="NewForm" />
</form>