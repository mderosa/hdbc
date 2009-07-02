<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<ul>
	<li>
		<a href="<c:url value="/console/experiments/lists/active" />">
			<fmt:message key="navigation.home" />
		</a>
	</li>
	<li>
		<a href="<c:url value="/console/experiments" />">
			<fmt:message key="navigation.new.experiment" />
		</a>
	</li>
</ul>
