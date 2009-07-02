<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h2>
	<fmt:message key="experimentsLists.experiments" />
</h2>
<ul class="stdlist">
	<c:forEach var="experiment" varStatus="status" items="${experiments}" >
	<li class="${(status.index % 2) eq 0 ? 'white' : 'gray'}" >
		<a href="<c:url value="/console/experiments/${experiment.data.uid}" />" >
			<c:out value="${experiment.data.title}" />
		</a>
		<p>
			<c:out value="${experiment.data.purpose}" />
		</p>
	</li>
	</c:forEach>
</ul>
