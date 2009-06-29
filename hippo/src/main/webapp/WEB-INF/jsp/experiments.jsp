<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="${(empty experiment.uid) ? 'post' : 'put'}" commandName="experiment" cssClass="stdform">
	<fieldset>
		<legend>Experiment Details</legend>
		<form:hidden path="uid" />
		<ol>
			<li>
				<label for="name">Title:<em>*</em></label>
				<form:input id="title" path="title" />
				<form:errors path="title" cssClass="error" />
			</li>
			<li>
				<label for="purpose">Purpose:<em>*</em></label>
				<form:textarea id="purpose" path="purpose"/>
				<form:errors path="purpose" cssClass="error" />
			</li>
			<li>
				<label for="method">Method:</label>
				<form:textarea id="method" path="method" />
				<form:errors path="method" cssClass="error" />
			</li>
			<li>
				<label for="conclusion">Conclusion:</label>
				<form:textarea id="conclusion" path="conclusion" />
				<form:errors path="method" cssClass="error" />
			</li>
		</ol>
		<input type="submit" name="submit" value="${empty experiment.uid ? 'create' : 'update'}" />
	</fieldset>
</form:form>