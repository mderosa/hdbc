<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="${(empty experiment.uid) ? 'post' : 'put'}" commandName="experiment" >
	<form:hidden path="uid" />
	<label for="name">Name:
	</label>
	<form:input id="name" path="name" />
	
	<label for="purpose">Purpose:
	</label>
	<form:textarea id="purpose" path="purpose"/>
	
	<label for="method">Method:
	</label>
	<form:textarea id="method" path="method" />
	
	<label for="conclusion">Conclusion:
	</label>
	<form:textarea id="conclusion" path="conclusion" />
	
</form:form>