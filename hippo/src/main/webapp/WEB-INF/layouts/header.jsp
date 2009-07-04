<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="branding">
	<img src="<c:url value="/img/hippo.png" />" />
	<span>Hippo</span>
</div>

<form id="searchform" method="GET" action="<c:url value="/experiments/list/search" />" >
	<label for="search" >search</label>
	<input type="text" id="search" name="search" />
	<input type="submit" value="go" />
</form>



