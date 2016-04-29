<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %>

<%@ page contentType="text/html; charset=GBK" %>
<html:html locale="true">
<head>
<html:base/>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <br><br>
<%@ include file="menu.jsp"%>
<title><bean:message key="user.editPwd"/></title>
<link href="../css/main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />
<script type="text/javascript" src="../js/tabs.js"></script>
<html:javascript formName="editAdminPwdActionForm" dynamicJavascript="true" staticJavascript="true"/>
</head>
<html:errors/>
<body>
<html:form action="/exam/saveEditPwd" onsubmit="return validateEditAdminPwdActionForm(this);">
<table width="911" border="0" cellpadding="0" cellspacing="0">
  <!--DWLayoutTable-->
  <tr>
    <td width="184" height="123">&nbsp;</td>
    <td width="491">&nbsp;</td>
    <td width="236">&nbsp;</td>
  </tr>
  <tr>
    <td height="168">&nbsp;</td>
    <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0"  class="InputFrameMain">
        <!--DWLayoutTable-->
        <tr>
          <td width="197" height="14" nowrap><div><strong><bean:message key="user.editPwd"/>:</strong></div></td>
          <td width="287" >&nbsp;</td>
          <td width="5"></td>
        </tr>
        <tr>
          <td height="14"></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td height="14" nowrap><div align="right"><strong><bean:message key="user.oldPwd"/></strong></div></td>
          <td nowrap><html:password maxlength="15" property="passwordOld" size="15" redisplay="false" /></td>
          <td></td>
        </tr>
        <tr>
          <td height="14"></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td height="14" nowrap><div align="right"><strong><bean:message key="user.newPwd"/></strong></div></td>
          <td nowrap> <html:password maxlength="15" property="passwordNew" size="15" redisplay="false" /> </td>
          <td></td>
        </tr>
        <tr>
          <td height="14"></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td height="14" nowrap><div align="right"><strong><bean:message key="user.newPwdAgain"/></strong></div></td>
          <td nowrap> <html:password maxlength="15" property="passwordNew2" size="15" redisplay="false" /></td>
          <td></td>
        </tr>
        <tr>
          <td height="14"></td>
          <td></td>
          <td></td>
        </tr>
        <tr>
          <td>
            <html:hidden property="passwordPre"/>
          </td>
          <td height="14"><div align="right"><span class="InputAreaCell"><html:submit styleClass="button"><bean:message key="button.submit"/></html:submit></span></div></td>
          <td><div align="center"><html:reset styleClass="button"><bean:message key="button.reset"/></html:reset></div></td>
          <td></td>
        </tr>
        <tr>
          <td height="18"></td>
          <td></td>
          <td></td>
        </tr>
      </table></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="209">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</html:form>
</body>
</html:html>
