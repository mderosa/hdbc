<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Experiments</h2>
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
