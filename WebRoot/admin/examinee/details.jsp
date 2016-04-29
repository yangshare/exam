<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page contentType="text/html; charset=GBK"%>
<html:html>
  <html:base/>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title><bean:message key="examinee.details" /></title>
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">

    <tr>
      <td width="33%" class="tabletitletext">
        <div align="center" class="tabletitletext">
          <div align="left">
            <span class="style1"><bean:message key="examinee.details" /></span>
          </div>
        </div>
      </td>
      <td width="51%" class="tabletitletext">
        <div align="right">        </div>
      </td>
      <td width="16%" class="tabletitletext">
        <div align="center">        </div>
      </td>
      <td width=""33%"">
      </td>
      <td>
        <html:button property="backButton" onclick="history.back()" styleClass="button">
      <bean:message key="button.back"/>
    </html:button>
      </td>
    </tr>
  </table>
  <html:form action="/admin/examinee/examineeTestDetails">

  <table width="80%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
    <tr>
      <bean:message key="examineeActionForm.name"/>:
	<bean:write name="examineeTestDetailsActionForm" property="examinee_name"/>
    </tr>
    <tr class="Listtitle">
      <td width="10%" nowrap class="listcellTitle"><bean:message key="index" /></td>
      <td width="8%" nowrap class="listcellTitle"><bean:message key="state" /></td>
      <td width="25%" nowrap class="listcellTitle"><bean:message key="examinationPaer.name" /></td>
      <td width="13%" nowrap class="listcellTitle"><bean:message key="date.begin" /></td>
      <td width="13%" nowrap class="listcellTitle"><bean:message key="date.end" /></td>
      <td width="4%" nowrap class="listcellTitle"><bean:message key="testpaper.t_total" /></td>
      <td width="4%" nowrap class="listcellTitle"><bean:message key="testpaper.e_total" /></td>
      <td width="5%" nowrap class="listcellTitle"><bean:message key="exam.e_passvalue" /></td>
    </tr>

    <logic:iterate id="testPaper" name="testerPaperSet" indexId="index">
      <tr>
        <td align="center">
          <%= index.intValue() + 1 %>
        </td>
        <td align="center">
          <bean:write name="testPaper" property="t_state"/>
        </td>
        <td align="center">
          <bean:write name="testPaper" property="e_name"/>
        </td>
        <td align="center">
          <bean:write name="testPaper" property="t_begin"/>
        </td>
        <td align="center">
          <bean:write name="testPaper" property="t_end"/>
        </td>
        <logic:notEqual name="testPaper" property="t_total" value="-1">
            <td nowrap>
              <div align="center">
                <bean:write name="testPaper" property="t_total"/>
              </div>
            </td>
          </logic:notEqual>
          <logic:equal name="testPaper" property="t_total" value="-1">
            <logic:equal name="testPaper" property="e_state" value="040">
              <td nowrap>
              <div align="center">
                <bean:message key="button.absence"/>
              </div>
            </td>
            </logic:equal>
            <logic:notEqual name="testPaper" property="e_state" value="040">
              <td nowrap>
            	<div align="center">
              <bean:write name="testPaper" property="t_stateName"/>
            	</div>
          </td>
            </logic:notEqual>
          </logic:equal>
        <td align="center">
          <bean:write name="testPaper" property="e_total"/>
        </td>
        <td align="center">
          <bean:write name="testPaper" property="t_passvalue"/>
        </td>
      </tr>
    </logic:iterate>
  </table>


   
    </html:form>
</body>
</html:html>
