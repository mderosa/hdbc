<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<form:form method="${(empty experiment.uid) ? 'post' : 'put'}" commandName="experiment" cssClass="stdform">
	<fieldset>
		<legend>
			<fmt:message key="experiments.experiment.details" />
		</legend>
		<form:hidden path="uid" />
		<ol>
			<li>
				<label for="title">
					<fmt:message key="experiments.title" />
					<em>*</em>
				</label>
				<form:input id="title" path="title" maxlength="64" />
				<form:errors path="title" cssClass="error" />
			</li>
			<li>
				<label for="purpose">
					<fmt:message key="experiments.purpose" />
					<em>*</em>
				</label>
				<form:textarea id="purpose" path="purpose"/>
				<form:errors path="purpose" cssClass="error" />
			</li>
			<li>
				<label for="method">
					<fmt:message key="experiments.method" />
				</label>
				<form:textarea id="method" path="method" />
				<form:errors path="method" cssClass="error" />
			</li>
			<li>
				<label for="conclusion">
					<fmt:message key="experiments.conclusion" />
				</label>
				<form:textarea id="conclusion" path="conclusion" />
				<form:errors path="method" cssClass="error" />
			</li>
		</ol>		
	</fieldset>
	<c:choose>
		<c:when test="${empty experiment.uid}" >
			<input type="submit" name="submit" value="
				<fmt:message key="experiments.create" />" />
		</c:when>
		<c:otherwise>
			<input type="submit" name="submit" value="
				<fmt:message key="experiments.update" />" />
		</c:otherwise>
	</c:choose>
</form:form>