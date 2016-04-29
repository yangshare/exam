<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page contentType="text/html; charset=GBK"%>
<html:html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>
  <bean:message key="testpater.title.gradelist"/>
</title>
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
    <tr align="center" >
   <div align="center" class="tabletitletext"><bean:message key="grade.testlist"/></div>
  </tr>
    <tr>
      <td width="33%" class="tabletitletext">
        <div align="center" class="tabletitletext">
          <div align="left">
            <bean:write name="gradeActionForm" property="title"/>
            <html:hidden name="gradeActionForm" property="title"/>
            <span class="style1">            </span>
          </div>
        </div>
      </td>
      <td width="51%" class="tabletitletext">
        <div align="right">        </div>
      </td>
      <td width="16%" class="tabletitletext">
        <div align="center">
          <html:link action="/admin/program/searchAction?jsp=e_grademain">
            <bean:message key="grade.back"/>
          </html:link>
        </div>
      </td>
    </tr>
  </table>
  <table width="80%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
    <tr class="Listtitle">
      <td width="10%" nowrap="nowrap" class="listcellTitle">
       <bean:message key="search.selectIt"/>
      </td>
      <td width="8%" nowrap="nowrap" class="listcellTitle">
        <bean:message key="control.examinee_id"/>
</nobr>      </td>
      <td width="7%" nowrap="nowrap" class="listcellTitle">
        <bean:message key="control.name"/>
      </td>
      <td width="25%" nowrap="nowrap" class="listcellTitle">
        <bean:message key="search.e_name"/>
      </td>
      <td width="13%" nowrap="nowrap" class="listcellTitle">
        <bean:message key="control.t_begin"/>
      </td>
      <td width="12%" nowrap="nowrap" class="listcellTitle">
        <bean:message key="control.t_end"/>
      </td>
      <td width="10%" nowrap="nowrap" class="listcellTitle">
        <bean:message key="control.t_state"/>
      </td>
      <td width="10%" nowrap="nowrap" class="listcellTitle">
        <bean:message key="grade.e_autovalue"/>
      </td>
      <td width="10%" nowrap="nowrap" class="listcellTitle">
        <bean:message key="grade.e_manualvalue"/>
      </td>
      <td width="15%" nowrap="nowrap" class="listcellTitle">
        <bean:message key="search.e_operation"/>
</nobr>      </td>
    </tr>
    <logic:iterate id="testPaper" name="testPaperSet" indexId="index">
      <tr class='Listrow1' align="center">
        <td nowrap="nowrap">
          <div align="center"><%= index.intValue()+1 %>          </div>
        </td>
        <td nowrap="nowrap">
          <div align="center">
            <bean:write name="testPaper" property="examinee_id"/>
          </div>
        </td>
        <td nowrap="nowrap">
          <div align="center">
            <bean:write name="testPaper" property="name"/>
          </div>
        </td>
        <td nowrap="nowrap">
          <div align="center">
            <bean:write name="testPaper" property="e_name"/>
          </div>
        </td>
        <td nowrap="nowrap">
          <div align="center">
            <bean:write name="testPaper" property="t_begin"/>
          </div>
        </td>
        <td nowrap="nowrap">
          <div align="center">
            <bean:write name="testPaper" property="t_end"/>
          </div>
        </td>
        <td nowrap="nowrap">
          <div align="center">
            <bean:write name="testPaper" property="t_state"/>
          </div>
        </td>
        <td nowrap="nowrap">
          <div align="center">
            <logic:equal  name="testPaper" property="t_state" value="093">
            <bean:write name="testPaper" property="e_autovalue"/>
            </logic:equal>
            <logic:equal  name="testPaper" property="t_state" value="097">
            <bean:write name="testPaper" property="e_autovalue"/>
            </logic:equal>
          </div>
        </td>
        <td nowrap="nowrap">
          <logic:equal name="testPaper" property="t_state" value="097">
            <bean:write name="testPaper" property="e_manualvalue"/>
          </logic:equal>
        </td>
        <td nowrap="nowrap">
          <div align="center">
            <bean:define id="name" name="testPaper" property="name"/>
            <bean:define id="e_name" name="testPaper" property="e_name"/>
            <bean:define id="t_id" name="testPaper" property="t_id"/>
            <bean:define id="e_id" name="testPaper" property="e_id"/>
          <%
            java.util.HashMap newValues = new java.util.HashMap();
            newValues.put("e_name", e_name);
            newValues.put("name", name);
            newValues.put("t_id", t_id);
            pageContext.setAttribute("newValues", newValues);
          %>
            <logic:equal name="testPaper" property="t_state" value="093">
              <html:link action="/admin/program/gradeAction.do?method=showTest" name="newValues"><bean:message key="link.grade"/></html:link>
            </logic:equal>
            <logic:equal name="testPaper" property="t_state" value="097">
              <html:link action="/admin/program/gradeAction.do?method=showTest" name="newValues"><bean:message key="link.modify"/></html:link>
            </logic:equal>
            &nbsp;
          </div>
        </td>
      </tr>
    </logic:iterate>
  </table>
  
</body>
</html:html>
