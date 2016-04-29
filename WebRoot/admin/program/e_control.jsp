<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page contentType="text/html; charset=GBK"%>
<html:html locale="true">
<head>
  <html:base/>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
  <html:form action="/admin/program/absenceAction.do">
   <html:hidden name="exam" property="e_id"/>
    <table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
      <tr>
        <td class="tabletitletext">
          <div align="center" class="tabletitletext">
            <bean:message key="control.title"/>
          </div>
        </td>
      </tr>
    </table>

<table width="80%" border="0" align="center" cellpadding="7" cellspacing="0" class="InputFrameMain">
  <bean:message key="control.examInfo"/> :
  <tr>
    <td width="3%" nowrap>&nbsp;</td>
    <td width="11%" nowrap> <strong> <bean:message key="search.e_name"/> </strong> </td>
    <td width="26%" nowrap> <html:text name="exam" property="e_name" readonly="true" /> </td>
    <td width="9%" nowrap> <div align="center"> <strong> <bean:message key="control.e_passvalue"/> </strong> </div></td>
    <td width="22%" nowrap> <html:text name="exam" property="e_passvalue" size="10" readonly="true" /> <bean:message key="control.score"/> </td>
    <td width="11%" nowrap> <div align="center"> <strong> <bean:message key="control.e_timer"/> </strong> </div></td>
    <td width="13%" nowrap> <html:text name="exam" property="e_timer" size="6" readonly="true" /> <bean:message key="control.minutes"/> </td>
  </tr>
  <tr>
    <td nowrap>&nbsp;</td>
    <td nowrap> <strong> <bean:message key="search.e_begin"/> </strong> </td>
    <td nowrap> <html:text name="exam" property="e_begin" size="12" readonly="true" /> </td>
    <td nowrap> <div align="center"> <strong> <bean:message key="search.e_end"/> </strong> </div></td>
    <td nowrap> <html:text name="exam" property="e_end" size="12" readonly="true" /> </td>
    <td nowrap> <div align="center"> <strong> <bean:message key="control.e_total"/> </strong> </div></td>
    <td nowrap> <span class="InputAreaCell"> <html:text name="exam" property="e_total" size="6" readonly="true" /> </span> <bean:message key="control.score"/> </td>
  </tr>
</table>
    <table width="80%" border="1" align="center" cellpadding="7" cellspacing="0" class="InputFrameMain">
      <bean:message key="control.testInfo"/>
      <tr>
        <td width="20%" nowrap>
          <div align="center">
            <strong>
              <span class="InputAreaCell">
                <bean:message key="control.t_state"/>
                <br>
              </span>
            </strong>
          </div>
        </td>
        <td width="20%" nowrap>
          <div align="center">
            <strong>
              <span class="InputAreaCell">
                <bean:message key="control.examinee_id"/>
              </span>
            </strong>
          </div>
        </td>
        <td width="20%" nowrap>
          <div align="center">
            <strong>
              <span class="InputAreaCell">
                <bean:message key="control.name"/>
              </span>
            </strong>
          </div>
        </td>
        <td width="20%" nowrap>
          <div align="center">
            <strong>
              <span class="InputAreaCell">
                <bean:message key="control.t_begin"/>
                <br>
              </span>
            </strong>
          </div>
        </td>
        <td width="20%" nowrap>
          <div align="center">
            <strong>
              <span class="InputAreaCell">
                <bean:message key="control.t_end"/>
                <br>
              </span>
            </strong>
          </div>
        </td>
        <td width="20%" nowrap>
          <div align="center">
            <strong>
              <bean:message key="control.t_total"/>
            </strong>
          </div>
        </td>
        <td width="20%" nowrap>
          <div align="center">
            <strong>
              <bean:message key="control.ifPass"/>
            </strong>
          </div>
        </td>
        <td width="20%" nowrap>
          <div align="center">
            <strong>
              <bean:message key="search.e_operation"/>
            </strong>
          </div>
        </td>
      </tr>
      <logic:iterate id="testPaper" name="testPaperList" indexId="index">
        <tr>
          <td nowrap>
            <div align="center">
              <bean:write name="testPaper" property="t_stateName"/>
            </div>
          </td>
          <td nowrap>
            <div align="center">
              <bean:write name="testPaper" property="examinee_id"/>
            </div>
          </td>
          <td nowrap>
            <div align="center">
              <bean:write name="testPaper" property="name"/>
            </div>
          </td>
          <td nowrap>
            <div align="center">
              <bean:write name="testPaper" property="t_begin"/>
            </div>
          </td>
          <td nowrap>
            <div align="center">
              <bean:write name="testPaper" property="t_end"/>
            </div>
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
          <bean:define id="passvalue" name="testPaper" property="t_passvalue">          </bean:define>
        <%
          float ss = ((Float) passvalue).floatValue();
          String pa = String.valueOf(ss);
        %>
          <logic:greaterEqual name="testPaper" property="t_total" value="0">
            <logic:greaterEqual name="testPaper" property="t_total" value="<%=pa%>">
              <td nowrap>
                <div align="center">
                  <bean:message key="control.pass"/>
                </div>
              </td>
            </logic:greaterEqual>
            <logic:lessThan name="testPaper" property="t_total" value="<%=pa%>">
              <td nowrap>
                <div align="center">
                  <bean:message key="control.nopass"/>
                </div>
              </td>
            </logic:lessThan>
          </logic:greaterEqual>
          <logic:equal name="testPaper" property="t_total" value="-1">
            <td nowrap>
              <div align="center">
                <bean:message key="control.wait"/>
              </div>
            </td>
            <td>
              <bean:define id="t_idStr" name="testPaper" property="t_id"/>
              <%
              long t_id = ((Long) t_idStr).longValue();
              String str = String.valueOf(t_id);
              %>
              <bean:define id="e_idStr" name="testPaper" property="e_id"/>
              <%
              long e_id = ((Long) e_idStr).longValue();
              String e_idS = String.valueOf(e_id);
              %>
              <logic:notEqual value="040" name="testPaper" property="e_state">
                <logic:equal value="095" name="testPaper" property="t_state">
                <html:link action='<%="/admin/program/reAttandExamAction?t_id=" + str + "&e_id=" + e_idS%>'>
                <bean:message key="link.reAttandExam"/>
              </html:link>
              </logic:equal>
              </logic:notEqual>

            </td>
          </logic:equal>
        </tr>
      </logic:iterate>
    </table>
   
    <table align="center">
      <tr>
        <td height="30" colspan="8" nowrap>
          <div align="center">
            <html:submit styleClass="button">
              <bean:message key="button.absence"/>
            </html:submit>
          &nbsp;&nbsp;&nbsp;&nbsp;
          <html:link action="/admin/program/searchAction?jsp=e_manage">
      <bean:message key="button.back"/>
          </html:link>
          </div>
        </td>
      </tr>
    </table>
  </html:form>
</body>
</html:html>
