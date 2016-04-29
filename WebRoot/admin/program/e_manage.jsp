<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html locale="true">
<head>
  <html:base/>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../js/calendar.js" type="text/JavaScript"></script></head>
<body>
  <div align="center" class="tabletitletext"><bean:message key="examinationPaper.manage"/></div>

  

  <table width="80%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
    <tr class="Listtitle">
      <td width="5%" nowrap class="listcellTitle">
        <bean:message key="gird.index"/>
      </td>
      <td width="8%" nowrap class="listcellTitle">
        <span class="nobr">
          <bean:message key="search.e_id"/>
        </span>
      </td>
      <td width="15%" nowrap class="listcellTitle">
        <span class="nobr">
          <bean:message key="search.e_type"/>
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
      <td width="13%" nowrap class="listcellTitle">
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
          <div align="center"><%= index.intValue()+1 %></div>
        </td>
        <td nowrap>
          <div align="center">
            <bean:write name="paper" property="e_id"/>
          </div>
        </td>
        <td nowrap>
          <div align="center">
            <bean:write name="paper" property="e_type"/>
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
            <bean:write name="paper" property="e_stateName"/>
          </div>
        </td>
        <td nowrap>
          <div align="center">
            <html:link action="/admin/program/controlAction.do?page=1" paramId="E_ID" paramName="paper" paramProperty="e_id">
              <bean:message key="link.control"/>
            </html:link>
            &nbsp;
            <bean:define id="e_id" name="paper" property="e_id"></bean:define>
            <bean:define id="e_passvalue" name="paper" property="e_passvalue"></bean:define>
            <bean:define id="e_total" name="paper" property="e_total"></bean:define>
          <%
            java.util.HashMap newValues = new java.util.HashMap();
            newValues.put("E_ID", e_id);
            newValues.put("E_PASSVALUE", e_passvalue);
            newValues.put("E_TOTAL", e_total);
            pageContext.setAttribute("newValues", newValues);
          %>
            <html:link action="/admin/program/statisticAction.do?page=1" name="newValues">
              <bean:message key="link.statistic"/>
            </html:link>
          </div>
        </td>
      </tr>
    </logic:iterate>
  </table>
  

</body>
</html:html>
