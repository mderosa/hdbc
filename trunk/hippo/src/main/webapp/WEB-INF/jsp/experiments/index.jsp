<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p>
	The name is: ${experiment.name }
	<br/>
	The purpose is: ${experiment.purpose}
	<br/>
	The level is: ${experiment["purpose"] }
	<br/>
	addition: ${1 + 2 }
	<br/>
	Here is something that <c:out value="${car}" default="no go bro" />
	<c:url var="homeUrl" value="/home" />
	<a href="<c:url value='/reports' />" >reports</a>
	
	<a href="${homeUrl}">Home</a>
	
</p>
