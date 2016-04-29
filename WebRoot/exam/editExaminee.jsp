<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %>

<html:html locale="true">
<head>
<html:base/>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <br><br> 
<%@ include file="menu.jsp"%>
<title><bean:message key="editExaminee.title"/></title>
<link href="../css/main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />
<script type="text/javascript" src="../js/tabs.js"></script>
<html:javascript formName="examineeActionForm" dynamicJavascript="true" staticJavascript="true"/>
</head>

<body>
<html:errors/>
<html:form action="/exam/saveEditExaminee" onsubmit="return validateExamineeActionForm(this);">
<table width="80%" border="0" align="center" cellpadding="5" cellspacing="0" class="InputFrameMain">
  <!--DWLayoutTable-->
  <tr>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td rowspan="2" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <!--DWLayoutTable-->
        <tr>
          <td width="150" height="28">&nbsp;</td>
          <td width="100">&nbsp;</td>
        </tr>
        <tr>
          <td height="10" nowrap><div align="right"><bean:message key="examineeActionForm.examinee_id"/>&nbsp;&nbsp;</div></td>
          <td><html:text property="examinee_id" readonly="true" style="width=110"/></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10" nowrap><div align="right"><bean:message key="examineeActionForm.name"/>&nbsp;&nbsp;</div></td>
          <td><html:text property="name" readonly="true" style="width=110"/></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10" nowrap><div align="right"><bean:message key="examineeActionForm.age"/>&nbsp;&nbsp;</div></td>
          <td><html:text property="age" readonly="true" style="width=110"/></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10" nowrap><div align="right"><bean:message key="examineeActionForm.organization_id"/>&nbsp;&nbsp;</div></td>
          <td><html:select property="organization_id" disabled="true" style="width=110"> <html:optionsCollection name="organizationOpts"/> </html:select> <html:hidden property="organization_id"/> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10" nowrap><div align="right"><bean:message key="examineeActionForm.post_index"/>&nbsp;&nbsp;</div></td>
          <td><html:select property="post_index" disabled="true" style="width=110"> <html:optionsCollection name="post_indexOpts"/> </html:select> <html:hidden property="post_index"/> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10" nowrap><div align="right"><bean:message key="examineeActionForm.state"/>&nbsp;&nbsp;</div></td>
          <td><html:select property="state" disabled="true" style="width=110"> <html:optionsCollection name="stateOpts"/> </html:select> <html:hidden property="state"/> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10" nowrap><div align="right"><bean:message key="examineeActionForm.sex"/>&nbsp;&nbsp;</div></td>
          <td><html:select property="sex" disabled="true" style="width=110"> <html:optionsCollection name="sexOpts"/> </html:select> <html:hidden property="sex"/> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10" nowrap><div align="right"><bean:message key="examineeActionForm.education_index"/>&nbsp;&nbsp;</div></td>
          <td><html:select property="education_index" disabled="true" style="width=110"> <html:optionsCollection name="education_indexOpts"/> </html:select> <html:hidden property="education_index"/> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10" nowrap><div align="right"><bean:message key="examineeActionForm.operation"/>&nbsp;&nbsp;</div></td>
          <td><html:select property="operation" disabled="true" style="width=110"> <html:optionsCollection name="operationOpts"/> </html:select> <html:hidden property="operation"/> </td>
        </tr>
      </table></td>
    <td colspan="2" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <!--DWLayoutTable-->
        <tr>
          <td width="10" height="28">&nbsp;</td>
          <td width="293">&nbsp;</td>
        </tr>
        <tr>
          <td height="10" nowrap><div align="right"><bean:message key="examineeActionForm.address"/>&nbsp;&nbsp;</div></td>
          <td><html:text property="address" style="width=110"/></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10" nowrap><div align="right"><bean:message key="examineeActionForm.phone"/>&nbsp;&nbsp;</div></td>
          <td><html:text property="phone" style="width=110"/></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10" nowrap><div align="right"><bean:message key="examineeActionForm.email"/>&nbsp;&nbsp;</div></td>
          <td><html:text property="email" style="width=110"/></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10" nowrap><div align="right"><bean:message key="examineeActionForm.remark"/>&nbsp;&nbsp;</div></td>
          <td height="0" valign="top"><html:textarea cols="40" rows="6" property="remark"></html:textarea></td>
        </tr>
      </table></td>
  </tr>
  <tr>
    <td height="21">&nbsp;</td>
    <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <!--DWLayoutTable-->
        <tr>
          <td width="172" height="21" valign="top"> <div align="right"> <html:submit styleClass="button"><bean:message key="button.submit"/></html:submit> </div></td>
          <td width="372" valign="top"><div align="center"> <html:reset styleClass="button"><bean:message key="button.reset"/></html:reset> </div></td>
        </tr>
      </table></td>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td height="137">&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
</html:form>
</body>
</html:html>
