<jsp:include page="header.jsp" />
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- If Benefits Coordinator, Department Head, Supervisor, or Admin -->
<!-- See All -->
<form action="Pending" method="post">
	<c:if test="${not empty users }" >
	<select name="user">
		<c:forEach items="${users}" var="user">
			<option value="${user.uuid}"
				<c:if test="${user.uuid eq selectedId}">selected="selected"</c:if>
				>
				${user.username}
			</option>
		</c:forEach>
	</select>
	</c:if>
	<c:if test="${not empty selectedId }" >
	<select name="form">
		<c:forEach items="${forms}" var="form">
			<option value="${form.formUUID}"
				<c:if test="${form.formUUID eq selectedForm}">selected="selected"</c:if>
				>
			${form.formUUID}
		</option>
		</c:forEach>
	</select>
	</c:if>
	<input type="submit" value="select" />
</form>
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
