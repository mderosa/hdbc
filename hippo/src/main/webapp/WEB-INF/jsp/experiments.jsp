<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="${(empty experiment.uid) ? 'post' : 'put'}" commandName="experiment" cssClass="stdform">
	<fieldset>
		<legend>Experiment Details</legend>
		<form:hidden path="uid" />
		<ol>
			<li>
				<label for="name">Name:<em>*</em></label>
				<form:input id="name" path="name" />
			</li>
			<li>
				<label for="purpose">Purpose:<em>*</em></label>
				<form:textarea id="purpose" path="purpose"/>
			</li>
			<li>
				<label for="method">Method:</label>
				<form:textarea id="method" path="method" />
			</li>
			<li>
				<label for="conclusion">Conclusion:</label>
				<form:textarea id="conclusion" path="conclusion" />
			</li>
		</ol>
		<input type="submit" name="submit" value="submit" />
	</fieldset>
</form:form>