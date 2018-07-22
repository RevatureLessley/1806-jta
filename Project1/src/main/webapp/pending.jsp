<jsp:include page="header.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- If Benefits Coordinator, Department Head, Supervisor, or Admin -->
<!-- See All -->
<form action="Pending" method="post">
	<c:if test="${not empty users }" >
	<select name="user">
		<c:forEach items="${users}" var="user">
			<option value="${user.uuid}">${user.username}</option>
		</c:forEach>
	</select>
	</c:if>
	<c:if test="${not empty forms }" >
	<select name="form">
		<c:forEach items="${forms}" var="form">
			<option value="${form.formUUID}">${form.formCreationDate}</option>
		</c:forEach>
	</select>
	</c:if>
	<input type="submit" value="select" />
</form>
 <script>
            $(document).on("click", "#buttonLoad", function() {
                $.get("Pending", function(responseJson) {
                    var $select = $("#dropdownlist");
                    $select.find("option").remove();  
                    $.each(responseJson, function(index, category) {
                        $("<option>").val(user.uuid).text(user.username).appendTo($select);
                    });                   
                     
                });
            });
 </script>
 <button id="buttonLoad">Select</button>
 <select id="dropdownlist"></select>
<!-- See Assigned -->
	<!-- Select  -->

<!-- If Benefits Coordinator -->
	<!-- Change amount -->
	<!-- Modify form comments -->
	<!-- make a decision -->

<!-- If Department Head -->
	<!-- modify department head comments -->
	<!-- make a decision -->

<!-- If Supervisor -->
	<!-- Modify supervisor comments -->
	<!-- make a decision -->

<!-- If Admin -->
	<!-- view -->

<!-- If No Role Employee -->
	<!-- view -->
	<!-- append form comments -->
	
<!-- View current form -->
<form>
	<input type="text" name="formUUID" readonly/>
	<input type="text" name="employeeUUID" readonly/>
	<input type="text" name="formCreationDate" readonly/>
	<input type="text" name="firstName" readonly/>
	<input type="text" name="lastName" readonly/>
	<input type="text" name="departmentHeadUUID" readonly/>
	<input type="text" name="generalStatus" readonly/>
	<input type="text" name="formComments" readonly/>
	<input type="text" name="isCompletedWithSatisfaction" readonly/>
	<input type="text" name="isFormClosed" readonly/>
	<input type="text" name="isPresentationToManagementRequired" readonly/>
	
	<input type="text" name="directSupervisorUUID" readonly/>
	<input type="text" name="isSupervisorDecisionMade" readonly/>
	<input type="text" name="directSupervisorDecisionDate" readonly/>
	<input type="text" name="supervisorComments" readonly/>
	
	<input type="text" name="benefitsCoordinatorUUID" readonly/>
	<input type="text" name="isBenCoDecisionMade" readonly/>
	<input type="text" name="benefitsCoordinatorDecisionDate" readonly/>
	<input type="text" name="benCoComments" readonly/>
	
	<input type="text" name="departmentHeadUUID" readonly/>
	<input type="text" name="isDepartmentHeadDecisionMade" readonly/>
	<input type="text" name="departmentHeadDecisionDate" readonly/>
	<input type="text" name="departmentHeadComments" readonly/>
</form>