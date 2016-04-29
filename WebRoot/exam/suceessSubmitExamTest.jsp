<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html>
<head>
<title>
submitExamTest
</title>
</head>
<body bgcolor="#ffffff">
  <center>
    <h1>
  <font color="green">
    <bean:message key="success.submitExamTestPaper"/>
  </font>
</h1>
<html:link action="/exam/examinationPaperListAction">
  <bean:message key="button.back"/>
</html:link>
  </center>
</body>
</html>
