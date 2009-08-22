<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	    
    <link rel="stylesheet" type="text/css" href="<c:url value="/ext/resources/css/ext-all.css" />" />
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/hippo.css" />"  />
   	<script type="text/javascript" src="<c:url value="/ext/adapter/ext/ext-base.js" />" ></script>
 	<script type="text/javascript" src="<c:url value="/ext/ext-all-debug.js" />" ></script>
 	
 	<script type="text/javascript" src="<c:url value="/js/HippoViewport.js" />" ></script>
 	<script type="text/javascript" src="<c:url value="/js/dialog/NewExperimentDlg.js" />" ></script>
 	<script type="text/javascript" src="<c:url value="/js/form/NewExperimentForm.js" />" ></script>
 	<script type="text/javascript" src="<c:url value="/js/panel/ExperimentTabPanel.js" />" ></script>
 	<script type="text/javascript" src="<c:url value="/js/panel/ExperimentListPanel.js" />" ></script>
 	<script type="text/javascript" src="<c:url value="/js/panel/ExperimentSettingsPanel.js" />" ></script>
 	<script type="text/javascript" src="<c:url value="/js/panel/ExperimentToolPanel.js" />" ></script>
    <title>Hippo</title>
 
  	<script type="text/javascript">
  		Ext.onReady(function() {
	  		Ext.BLANK_IMAGE_URL = '<c:url value="/ext/resources/images/default/s.gif" />';
	  		Ext.QuickTips.init();

	  		view = new Hippo.HippoViewport();
  		});
  	</script>
  
</head>
 
<body></body>
 
</html>