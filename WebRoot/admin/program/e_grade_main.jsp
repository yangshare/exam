<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page contentType="text/html; charset=GBK"%>
<html:html locale="true">
<head>
  <html:base/>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../js/calendar.js" type="text/JavaScript"></script>
<title><bean:message key="search.title"/></title>
</head>
<body>
  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td>&nbsp;</td>
  </tr>
  <tr>
    <td class="tabletitletext"><div align="center" class="tabletitletext"><bean:message key="grade.paper"/></div></td>
  </tr>
</table>
 
  <table width="80%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
    <tr class="Listtitle">
      <td width="10%" nowrap class="listcellTitle">
        <bean:message key="search.selectIt"/>
      </td>
      <td width="8%" nowrap class="listcellTitle">
        <span class="nobr">
          <bean:message key="search.e_id"/>
        </span>
      </td>
      <td width="25%" nowrap class="listcellTitle">
        <span class="nobr">
          <bean:message key="search.e_name"/>
        </span>
      </td>
      <td width="13%" nowrap class="listcellTitle">
        <span class="nobr">
          <bean:message key="search.e_begin"/>
        </span>
      </td>
      <td width="9%" nowrap class="listcellTitle">
        <span class="nobr">
          <bean:message key="search.e_end"/>
        </span>
      </td>
      <td width="4%" nowrap class="listcellTitle">
        <span class="nobr">
          <bean:message key="search.e_state"/>
        </span>
      </td>
      <td width="15%" nowrap class="listcellTitle">
        <span class="nobr">
          <bean:message key="search.e_operation"/>
        </span>
      </td>
    </tr>
    <logic:iterate id="paper" name="paperList" indexId="index">
      <tr class='Listrow1' align="center">
        <td nowrap>
          <div align="center"><%= index.intValue()+1 %>          </div>
        </td>
        <td nowrap>
          <div align="center">
            <bean:write name="paper" property="e_id"/>
          </div>
        </td>
        <td nowrap>
          <div align="center">
            <bean:write name="paper" property="e_name"/>
          </div>
        </td>
        <td nowrap>
          <div align="center">
            <bean:write name="paper" property="e_begin"/>
          </div>
        </td>
        <td nowrap>
          <div align="center">
            <bean:write name="paper" property="e_end"/>
          </div>
        </td>
        <td nowrap>
          <div align="center">
            <bean:write name="paper" property="e_state"/>
          </div>
        </td>
        <td nowrap>
          <bean:define id="e_id" name="paper" property="e_id"/>
          <bean:define id="e_name" name="paper" property="e_name"/>
        <%
          java.util.HashMap newValues = new java.util.HashMap();
          newValues.put("e_name", e_name);
          newValues.put("e_id", e_id);
          pageContext.setAttribute("newValues", newValues);
        %>
          <div align="center">
            <html:link action="/admin/program/gradeAction.do?method=showExamination&amp;page=1" name="newValues"><bean:message key="link.grade"/></html:link>
          </div>
        </td>
      </tr>
    </logic:iterate>
  </table>
 
</body>
</html:html>
