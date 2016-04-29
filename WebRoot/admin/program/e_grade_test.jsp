<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page contentType="text/html; charset=GBK"%>
<html:html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>show test</title>
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
  <html:form action="/admin/program/gradeAction.do?method=countTotalValue">
    <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
      <tr align="center" >

  </tr>
      <tr>
        <td width="33%" class="tabletitletext">
          <div align="center" class="tabletitletext">
            <div align="left">
             <bean:message key="grade.questionlist"/>
              <span class="style1">              </span>
            </div>
          </div>
        </td>
        <td width="51%" class="tabletitletext">
          <div align="right">          </div>
        </td>
        <td width="16%" class="tabletitletext">
          <div align="center">          </div>
        </td>
      </tr>
    </table>
    <table width="80%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
      <tr class="Listtitle">
        <td width="10%" nowrap class="listcellTitle"><bean:message key="search.selectIt"/></td>
        <td width="10%" nowrap class="listcellTitle"><bean:message key="grade.xh"/></td>
        <td width="8%" nowrap class="listcellTitle"><bean:message key="grade.content"/>
</nobr>        </td>
        <td width="7%" nowrap class="listcellTitle"><bean:message key="grade.score"/></td>
      </tr>
      <logic:iterate id="testDetails" name="testDedailsSet" indexId="index">
        <tr class='Listrow1' align="center">
          <td nowrap >
            <div align="center"><%= index.intValue()+1 %>            </div>
          </td>
          <td nowrap>
            <bean:write name="testDetails" property="q_ID"/>
            <div align="center">            </div>
          </td>
          <td nowrap>
            <div align="center">
              <bean:define id="t_id" name="testDetails" property="t_ID"/>
              <bean:define id="q_id" name="testDetails" property="q_ID"/>
            <%
              java.util.HashMap newValues = new java.util.HashMap();
              newValues.put("t_id", t_id);
              newValues.put("q_id", q_id);
              pageContext.setAttribute("newValues", newValues);
            %>
              <html:hidden name="gradeActionForm" property="testPaperDetail.t_ID"/>
              t_id
              <bean:write name="gradeActionForm" property="testPaperDetail.t_ID"/>
              <html:link styleClass="LinkText style1" action="/admin/program/gradeAction.do?method=showQuestion" name="newValues"> <bean:message key="grade.detail"/></html:link>
            </div>
          </td>
          <td nowrap>
            <div align="center">
              <logic:lessThan name="testDetails" property="t_value" value="0">
                <html:text maxlength="5" name="testDetails" property="t_value" size="5" value="" disabled="true">                </html:text>
              </logic:lessThan>
              <logic:greaterEqual name="testDetails" property="t_value" value="0">
                <html:text maxlength="5" name="testDetails" property="t_value" size="5" disabled="true">                </html:text>
              </logic:greaterEqual>
            </div>
          </td>
        </tr>
      </logic:iterate>
    </table>
    <br>
    <table align="center">
      <tr>
        <td height="30" colspan="8" nowrap>
          <div align="center">
            <html:submit styleClass="button">
              <bean:message key="question.submit"/>
            </html:submit> </div>
        </td>
      </tr>
    </table>
  </html:form>
</body>
</html:html>
