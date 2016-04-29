<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
<html:html>
<head>
<title>
paperOperaSuccess
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
<table width="80%" border="0" align="center" cellpadding="5" cellspacing="0" class="InputFrameMain">
  <tr>
    <td align="center">
      <bean:message key="success.submitExamTestPaper"/>
    </td>
  </tr>
  <tr align="center">
    <td align="center">
      <html:link action="/admin/program/searchAction?jsp=e_distribute">
        <bean:message key="link.distribute"/>
      </html:link>
    </td>
  </tr>
</table>
</body>
</html:html>
