<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html>
<head>
<html:base/>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>success</title>
<link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>

<body>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>

<table width="350" height="60" border="0" align="center" cellpadding="0" cellspacing="0" class="InputFrameMain">
  <tr>
    <td>
      <div align="center">
        <span class="tabletitletext">
          <bean:message key="success.operationOK"/>
    
        <html:button property="close" value='<bean:message key="button.close"/>' onclick="window.close()" ></html:button>
        </span>
      </div>
    </td>
  </tr>
</table>
</body>
</html:html>
