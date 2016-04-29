<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<html:html locale="true">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>
error
</title>
</head>
<body bgcolor="#ffffff">
<html:errors/>
<% String action = request.getParameter("action");
%>
<table>
  <tr>
    <td>
      <html:link action='<%="/errorReturnAction?action="+action%>' >
        <bean:message key="error.Return"/>
      </html:link>
    </td>
  </tr>
</table>
</body>
</html:html>
