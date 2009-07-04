<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html 
	PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
	<head>
		<style type="text/css" media="screen" >
		 	@import url("<c:url value="/css/hippo.css" />");
		</style>
		<title>
			<tiles:getAsString name="title" />
		</title>
	</head>
	<body>
		<div id="page">
			<div id="header" >
				<tiles:insertAttribute name="header" />
			</div>
			
			<div id="nav">
				<tiles:insertAttribute name="navigation" />
			</div>
			<div id="main">
				<tiles:insertAttribute name="body" />
			</div>
		
			<div id="footer" >
				<tiles:insertAttribute name="footer" />
			</div>
		</div>
	</body>
</html>
