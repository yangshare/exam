<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<html:html locale="true">
  <html:base/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
<title><bean:message key="addExmainee.title"/></title>
<html:javascript formName="examineeActionForm" dynamicJavascript="true" staticJavascript="true"/>
</head>
<body>
<html:errors/>
<html:form action="/admin/examinee/saveEditExaminee" onsubmit="return validateExamineeActionForm(this);">
<table width="80%" border="0" align="center" cellpadding="5" cellspacing="0" class="InputFrameMain">
  <!--DWLayoutTable-->
  <tr>
    <td width="75" height="278"></td>
    <td width="550" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <!--DWLayoutTable-->
        <tr>
          <td width="111" height="14"></td>
          <td width="285"></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><bean:message key="examineeActionForm.examinee_id"/>&nbsp;&nbsp;</div></td>
          <td><bean:write name="examineeActionForm" property="examinee_id"/> <html:hidden property="examinee_id"/></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.name"/>&nbsp;&nbsp;</div></td>
          <td><html:text property="name" /></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.age"/>&nbsp;&nbsp;</div></td>
          <td><html:text property="age" /></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.organization_id"/>&nbsp;&nbsp;</div></td>
          <td> <html:select property="organization_id"> <html:optionsCollection name="organizationOpts"/> </html:select> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.post_index"/>&nbsp;&nbsp;</div></td>
          <td> <html:select property="post_index"> <html:optionsCollection name="post_indexOpts"/> </html:select> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.state"/>&nbsp;&nbsp;</div></td>
          <td> <html:select property="state"> <html:optionsCollection name="stateOpts"/> </html:select> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.sex"/>&nbsp;&nbsp;</div></td>
          <td> <html:select property="sex"> <html:optionsCollection name="sexOpts"/> </html:select> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.education_index"/>&nbsp;&nbsp;</div></td>
          <td> <html:select property="education_index"> <html:optionsCollection name="education_indexOpts"/> </html:select> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.operation"/>&nbsp;&nbsp;</div></td>
          <td> <html:select property="operation"> <html:optionsCollection name="operationOpts"/> </html:select> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><bean:message key="examineeActionForm.address"/>&nbsp;&nbsp;</div></td>
          <td><html:text property="address" /></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><bean:message key="examineeActionForm.phone"/>&nbsp;&nbsp;</div></td>
          <td><html:text property="phone" /></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><bean:message key="examineeActionForm.email"/>&nbsp;&nbsp;</div></td>
          <td><html:text property="email"/></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><bean:message key="examineeActionForm.remark"/>&nbsp;&nbsp;</div></td>
          <td rowspan="2"><html:textarea cols="40" rows="6" property="remark"></html:textarea></td>
        </tr>

      </table></td>
    <td width="109"></td>
  </tr>
  <tr>
    <td height="10"></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td height="10"></td>
    <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <!--DWLayoutTable-->
        <tr>
          <td width="231" height="21"> <div align="right"> <html:submit styleClass="button"><bean:message key="button.submit"/></html:submit> </div></td>
          <td width="236"><div align="center"> <html:reset styleClass="button"><bean:message key="button.reset"/></html:reset> </div></td>
        </tr>
      </table></td>
    <td></td>
  </tr>
  <tr>
    <td height="10"></td>
    <td></td>
    <td></td>
  </tr>
</table>
</html:form>
</body>
</html:html>
