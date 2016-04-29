<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %>

<html:html>
  <html:base/>
<head>
<title><bean:message key="exam.title"/></title>
<link href="../css/main.css" rel="stylesheet" type="text/css"> <link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />
<br><br>
<%@ include file="menu.jsp"%>
<link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />
<script type="text/javascript" src="../js/tabs.js"></script>

<Script LANGUAGE="JavaScript">
if(self!=top){top.location=self.location;}
</script>
</head>
<body bgcolor="#ffffff">
  <br />
  <br />
  <br />
  <br />
<h1>
<bean:message key="exam.index_welcome"/>
</h1>
</body>
</html:html>
