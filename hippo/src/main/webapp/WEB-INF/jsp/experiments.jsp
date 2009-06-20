<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Current Experiments</h2>
<c:forEach var="experiment" items="${experiments}" >
	<h3>
		<a href="<c:url value="/console/experiment/${experiment.data.uid}" />" >
			<c:out value="${experiment.data.name}" />
		</a>
	</h3>
	<p>
		<c:out value="${experiment.data.purpose}" />
	</p>
</c:forEach>

