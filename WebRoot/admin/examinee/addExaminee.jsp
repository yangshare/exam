<%@ page contentType="text/html; charset=GBK" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Progma","no-cache");
	response.setDateHeader("Expires",0);
%>
<html:html locale="true">
<head>
  <html:base/>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
  <script language="JavaScript" src="../../js/util.js" type="text/JavaScript"></script>
<title><bean:message key="addExmainee.title"/></title>
<html:javascript formName="examineeActionForm" dynamicJavascript="true" staticJavascript="true"/>
</head>
<body>
<html:errors/>
<html:form action="/admin/examinee/saveAddExaminee" onsubmit="return validateExamineeActionForm(this);">
<table width="80%" border="0" align="center" cellpadding="5" cellspacing="0" class="InputFrameMain">
  <!--DWLayoutTable-->
  <tr>
    <td width="27" height="468"></td>
    <td width="600" valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <!--DWLayoutTable-->
        <tr>
          <td width="100" height="14"></td>
          <td colspan="2"></td>
        </tr>
        <tr>

          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.examinee_id"/>&nbsp;&nbsp;</div></td>
          <td width="20%"><html:text property="examinee_id" value="" onblur="checkOverlap()" /></td>
          <td width="60%" id="checkoverlap" colspan="2" nowrap="nowrap"></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td colspan="2"></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.name"/>&nbsp;&nbsp;</div></td>
          <td colspan="2"><html:text property="name" /></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td colspan="2"></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.age"/>&nbsp;&nbsp;</div></td>
          <td colspan="2"><html:text property="age" /></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td colspan="2"></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.organization_id"/>&nbsp;&nbsp;</div></td>
          <td colspan="2"> <html:select property="organization_id" style="width=110"> <html:optionsCollection name="organizationOpts"/> </html:select> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td colspan="2"></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.post_index"/>&nbsp;&nbsp;</div></td>
          <td colspan="2"> <html:select property="post_index" style="width=110"> <html:optionsCollection name="post_indexOpts"/> </html:select> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td colspan="2"></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.state"/>&nbsp;&nbsp;</div></td>
          <td colspan="2"> <html:select property="state" style="width=110"> <html:optionsCollection name="stateOpts"/> </html:select> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td colspan="2"></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.sex"/>&nbsp;&nbsp;</div></td>
          <td colspan="2"> <html:select property="sex" style="width=110"> <html:optionsCollection name="sexOpts"/> </html:select> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td colspan="2"></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.education_index"/>&nbsp;&nbsp;</div></td>
          <td colspan="2"> <html:select property="education_index" style="width=110"> <html:optionsCollection name="education_indexOpts"/> </html:select> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td colspan="2"></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examineeActionForm.operation"/>&nbsp;&nbsp;</div></td>
          <td colspan="2"> <html:select property="operation" style="width=110"> <html:optionsCollection name="operationOpts"/> </html:select> </td>
        </tr>
        <tr>
          <td height="10"></td>
          <td colspan="2"></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><bean:message key="examineeActionForm.address"/>&nbsp;&nbsp;</div></td>
          <td colspan="2"><html:text property="address" /></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td colspan="2"></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><bean:message key="examineeActionForm.phone"/>&nbsp;&nbsp;</div></td>
          <td colspan="2"><html:text property="phone" /></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td colspan="2"></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><bean:message key="examineeActionForm.email"/>&nbsp;&nbsp;</div></td>
          <td colspan="2"><html:text property="email"/></td>
        </tr>
        <tr>
          <td height="10"></td>
          <td colspan="2"></td>
        </tr>
        <tr>
          <td height="10"><div align="right"><bean:message key="examineeActionForm.remark"/>&nbsp;&nbsp;</div></td>
          <td colspan="2" rowspan="2"><html:textarea cols="40" rows="6" property="remark"></html:textarea></td>
        </tr>
      </table></td>
    <td width="107">&nbsp;</td>
  </tr>
  <tr>
    <td height="10"></td>
    <td></td>
    <td></td>
  </tr>
  <tr>
    <td height="21"></td>
    <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
        <!--DWLayoutTable-->
        <tr>
          <td width="231" height="21"> <div align="right"> <html:submit styleClass="button" property="submitIt"><bean:message key="button.submit"/></html:submit> </div></td>
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
