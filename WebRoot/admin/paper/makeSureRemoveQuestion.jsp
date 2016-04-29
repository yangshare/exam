<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
<html:html>
<head>
<title>
makeSureRemoveQuestion
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
    <table width="80%" border="0" align="center" cellpadding="5" cellspacing="0" class="InputFrameMain">
    <br>
    <br>
  <font color="red">
    <h1>
<bean:message key="examinationPaper.makeSureRemoveQuestion"/>
</h1>
  </font>
  <hr>
    <html:link action="/admin/paper/removeQuestionAction" paramId="e_id" paramName="makeSureRemoveQuestionForm" paramProperty="e_id">
      <bean:message key="button.confirm"/>
    </html:link>
    &nbsp;&nbsp;
    <html:link action="/admin/program/searchAction?jsp=e_list">
      <bean:message key="button.giveup"/>
    </html:link>
    </table>
  </center>
</body>
</html:html>
