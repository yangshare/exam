<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page contentType="text/html; charset=GBK"%>
<html:html locale="true">
<head>
  <html:base/>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../js/calendar.js" type="text/JavaScript"></script></head>
<body>
  <div align="center" class="tabletitletext">
    <bean:message key="examinationPaper.distribut"/>
  </div>
  <table width="80%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
    <tr class="Listtitle">
      <td width="5%" nowrap class="listcellTitle">
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
      <td width="13%" nowrap class="listcellTitle">
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
            <logic:equal name="paper" property="e_state" value="039">
              <html:link action="/admin/program/chooseExaminee" paramId="e_id" paramName="paper" paramProperty="e_id">
                <bean:message key="link.distribute"/>
              </html:link>
              &nbsp;
              <bean:define id="e_id" name="paper" property="e_id">              </bean:define>
              <bean:define id="e_examineeList" name="paper" property="e_examineeList">              </bean:define>
              <bean:define id="e_passvalue" name="paper" property="e_passvalue">              </bean:define>
            <%
              java.util.HashMap newValues = new java.util.HashMap();
              newValues.put("E_ID", e_id);
              newValues.put("E_EXAMINEELIST", e_examineeList);
              newValues.put("E_PASSVALUE", e_passvalue);
              pageContext.setAttribute("newValues", newValues);
            %>
              <html:link action="/admin/program/examBeginAction" name="newValues">
                <bean:message key="link.examBegin"/>
              </html:link>
            </logic:equal>
            <logic:equal name="paper" property="e_state" value="038">
              <html:link action="/admin/program/chooseExaminee" paramId="e_id" paramName="paper" paramProperty="e_id">
                <bean:message key="link.distribute"/>
              </html:link>
            </logic:equal>
            <logic:equal name="paper" property="e_state" value="041">
              <html:link action="/admin/program/makeSureStopExamTestPaperAction" paramId="e_id" paramName="paper" paramProperty="e_id">
                <bean:message key="link.stopExam"/>
              </html:link>
            </logic:equal>
          </div>
        </td>
      </tr>
    </logic:iterate>
  </table>
  

</body>
</html:html>
