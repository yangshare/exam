<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page contentType="text/html; charset=GBk" %>
<html:html locale="true">
<head>
<html:base/>
<meta http-equiv="Content-Type" content="text/html; charset=GBk">
<link href="../../css/main.css" rel="stylesheet" type="text/css">
<title><bean:message key="examinee.index.title"/></title>
</head>

<body>
<table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
  <tr>
    <td class="tabletitletext"><div align="center" class="tabletitletext"><bean:message key="examinee.userlist"/></div></td>
  </tr>
</table>

<html:form action="/admin/examinee/deleteExaminee">
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
  <tr>
    <td width="33%" class="tabletitletext"><div align="center" class="tabletitletext">
      <div align="left"></div>
	      </div></td>
    <td width="51%" class="tabletitletext"><div align="right">
    </div></td>
    <td width="16%" class="tabletitletext"><div align="center">
      <html:submit styleClass="button"><bean:message key="button.delete"/></html:submit>
    </div></td>
  </tr>
</table>
<table width="74%"  border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
  <tr class="Listtitle">
    <td width="7%" nowrap align="center"><nobr><bean:message key="gird.index"/></td>
    <td width="10%" nowrap align="center"><nobr><bean:message key="examineeActionForm.examinee_id"/></nobr></td>
    <td width="8%" nowrap align="center"><nobr><bean:message key="examineeActionForm.name"/></nobr></td>
    <td width="4%" nowrap align="center"><nobr><bean:message key="examineeActionForm.sex"/></nobr></td>
    <td width="5%" nowrap align="center"><nobr><bean:message key="examineeActionForm.age"/></nobr></td>
    <td width="9%" nowrap align="center"><nobr><bean:message key="examineeActionForm.state"/></nobr></td>
    <td width="13%"  nowrap align="center"><nobr><bean:message key="examineeActionForm.organization_id"/></nobr></td>
    <td width="10%" nowrap align="center"><nobr><bean:message key="examineeActionForm.post_index"/></nobr></td>
    <td width="13%" nowrap align="center"><nobr><bean:message key="examineeActionForm.phone"/></nobr></td>
    <td width="9%" nowrap align="center"><nobr><bean:message key="examinee.details"/></nobr></td>
    <td width="9%" nowrap align="center"><nobr><bean:message key="examinee.revertPwd"/></nobr></td>
  </tr>
  <logic:iterate id="examinee" name="examinees" indexId="index">
  <tr>
    <td align="center" nowrap="nowrap">
      <html:multibox property="examineeList" ><bean:write name="examinee" property="examinee_id" /></html:multibox>
      <%= index.intValue()+1 %>
    </td>
    <td align="center" nowrap="nowrap">
      <bean:write name="examinee" property="examinee_id" filter="true"/>
    </td>
    <td align="center" nowrap="nowrap">
      <html:link action="/admin/examinee/editExaminee" paramId="examinee_id" paramName="examinee" paramProperty="examinee_id">
      <bean:write name="examinee" property="name" />
      </html:link>
    </td>
    <td align="center" nowrap="nowrap">
      <bean:write name="examinee" property="sexName" />
    </td>
    <td align="center" nowrap="nowrap">
      <bean:write name="examinee" property="age"/>
    </td>
    <td align="center" nowrap="nowrap">
      <bean:write name="examinee" property="stateName" />
    </td>
    <td align="center" nowrap="nowrap">
      <bean:write name="examinee" property="organizationName" />
    </td>
    <td align="center" nowrap="nowrap">
      <bean:write name="examinee" property="postName" />
    </td>
    <td align="center" nowrap="nowrap">
      <bean:write name="examinee" property="phone" />
    </td>
    <td align="center" nowrap="nowrap">
      <html:link action="/admin/examinee/examineeTestDetails" paramId="examinee_id" paramName="examinee" paramProperty="examinee_id">
        <bean:message key="examinee.details"/>
      </html:link>
    </td>
    <td align="center" nowrap="nowrap">
      <html:link action="/admin/examinee/saveEditExaminee" paramId="reInitPwd" paramName="examinee" paramProperty="examinee_id" target="_blank">
        <bean:message key="examinee.revertPwd"/>
      </html:link>
    </td>
  </tr>
</logic:iterate>
</table>

</html:form>
</body>
</html:html>
