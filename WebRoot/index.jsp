<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html locale="true">
<head>
  <html:base/>
  <link href="css/main.css" rel="stylesheet" type="text/css">
<title><bean:message key="logon.title"/></title>
<html:base/>
<script LANGUAGE="JavaScript">
if(self!=top){top.location=self.location;}
</script>
</head>
<body bgcolor="">
  <table width="80%" border="0" align="center" cellpadding="5" cellspacing="0" class="InputFrameMain">

<html:form action="/logon"  method="post" focus="userName">
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
    <h1>
<bean:message key="exam.welcomeMessage"/>
</h1>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<p>&nbsp;</p>
<table border="0" width="100%">
  <tr>
    <th align="right" width="50%">
      <bean:message key="examineeActionForm.examinee_id"/>&nbsp;
    </th>
    <td align="left" width="25%">
      <html:text property="userName" size="16" maxlength="16" value="" />
    </td>
	<td width="25%">&nbsp;</td>
  </tr>
  <tr>
    <th align="right" width="50%">
      <bean:message key="examineeActionForm.examinee_password"/>&nbsp;
    </th>
    <td align="left" width="25%">
      <html:password property="password" size="16" maxlength="16" redisplay="false"/>
    </td>
	<td width="25%"></td>
  </tr>
  <tr>
    <td align="right">
      <html:submit styleClass="button" property="submit"><bean:message key="button.submit"/></html:submit>
    </td>
    <td align="left">
      <html:reset styleClass="button"><bean:message key="button.reset"/></html:reset>
    </td>
    
    <td align="left">
      <html:link page="/showAdminlogin.do" ><bean:message key="admin.showAdminlogon"/></html:link>
    
    </td>
    
  </tr>
</table>
</html:form>
</table>
<table border="0" width="100%">
  <tr>
    <td width="33%">&nbsp;
    </td>
    <td width="33%">&nbsp;
    </td>
    <td width="34%" align="center">
      &nbsp;<html:errors/>
    </td>
  </tr>
</table>
</body>
</html:html>
<!--last modified on Aug 30th 2005 -->
