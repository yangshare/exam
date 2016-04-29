<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %>

<html:html locale="true">
  <html:base/>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <br><br> 
<%@ include file="menu.jsp"%>
<title><bean:message key="exam.title"/></title>
  <link href="../css/main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />
<script type="text/javascript" src="../js/tabs.js"></script>
</head>
<body>
  <p class="toolbar">&nbsp;</p>
  <html:form action="/exam/inquryExamAction">
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
    <tr>
      <td class="tabletitletext">
        <div align="center" class="tabletitletext"><bean:message key="examinee.inquiry" /></div>
      </td>
    </tr>
  </table>
  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
    <tr>
      <td width="33%" class="tabletitletext">
        <div align="center" class="tabletitletext">
          <div align="left">          </div>
        </div>
      </td>
      <td width="51%" class="tabletitletext">
        <div align="right">        </div>
      </td>
      <td width="16%" class="tabletitletext">
        <div align="center">        </div>
      </td>
    </tr>
  </table>
  <table width="80%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
    <tr class="Listtitle">
      <td width="10%" nowrap class="listcellTitle"><bean:message key="index" /></td>
      <td width="25%" nowrap class="listcellTitle"><bean:message key="examinationPaper.name" /></td>
      <td width="7%" nowrap class="listcellTitle"><bean:message key="examinationPaper.type" /></td>
      <td width="13%" nowrap class="listcellTitle"><bean:message key="examinationPaper.time" /></td>
      <td width="9%" nowrap class="listcellTitle"><bean:message key="control.t_total" /></td>
      <td width="4%" nowrap class="listcellTitle"><bean:message key="exam.state" /></td>
      <td width="4%" nowrap class="listcellTitle"><bean:message key="control.e_passvalue" /></td>
      <td width="4%" nowrap class="listcellTitle"><bean:message key="examinationPaper.state" /></td>
    </tr>
    <logic:iterate id="testPaper" name="testerPaperSet" indexId="index">
      <tr>
        <td align="left"><%= index.intValue() + 1 %>        </td>
        <td align="left">
        	<html:link action="/exam/inquryExamAction" paramId="e_id"  paramName="testPaper" paramProperty="e_id">
          <bean:write name="testPaper" property="e_name"/>
          </html:link>
        </td>
        <td align="center" nowrap>
          <bean:write name="testPaper" property="e_typeName"/>
        </td>
        <td align="center" nowrap>
          <bean:write name="testPaper" property="e_timer"/>
        </td>
        <logic:notEqual value="-1" name="testPaper" property="t_total">
          <td align="center" nowrap>
          <bean:write name="testPaper" property="t_total"/>
        </td>
        </logic:notEqual>
        <logic:equal value="-1" name="testPaper" property="t_total">
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

        <td align="center" nowrap>
          <bean:write name="testPaper" property="t_stateName"/>
        </td>
        <td align="center" nowrap>
          <bean:write name="testPaper" property="t_passvalue"/>
        </td>
        <td align="center" nowrap>
          <bean:message key="control.examTestPaperHasBeenEnd"/>
        </td>
      </tr>
    </logic:iterate>
  </table>
  </html:form>
 
</body>
</html:html>
