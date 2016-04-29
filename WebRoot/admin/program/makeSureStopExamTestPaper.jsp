<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page contentType="text/html; charset=GBK" %>
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
<html:html>
<head>
<title>
makeSureStopExamTestPaper
</title>
</head>
<body bgcolor="#ffffff">
  <p>&nbsp;
  </p>
  <p>&nbsp;
  </p>
  <p>&nbsp;
  </p>
  <p>&nbsp;
  </p>
  <center>
    <h1>
      <font color="red">
        	<bean:message key="link.makeSureStopExamTestPaper"/>
      </font>
</h1>
    <table width="80%" border="0" align="center" cellpadding="7" cellspacing="0" class="InputFrameMain">

<html:link action="/admin/program/stopExamAction" paramId="e_id" paramName="makeSureStopExamTestPaperActionForm" paramProperty="e_id">
  <bean:message key="button.confirm"/>
</html:link>
<html:link action="/admin/program/searchAction?jsp=e_distribute">
  <bean:message key="button.giveup"/>
</html:link>
</table>
  </center>

</body>
</html:html>
