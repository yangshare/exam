<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<html:html locale="true">
  <html:base/>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title><bean:message key="question.detials"/></title>
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
  <html:errors/>
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
    <tr>
      <td class="tabletitletext">
        <div align="center" class="tabletitletext"><bean:message key="question.detials"/></div>
      </td>
    </tr>
  </table>
  <!--DWLayoutTable-->
  <html:form action="/admin/program/gradeAction.do?method=saveGrade" method="post">
    <br>
    <table width="595" border="0" align="center" cellpadding="7" cellspacing="0" class="InputFrameMain"><bean:message key="question.content"/>
      <tr>
        <td width="10%" nowrap>
          <span class="InputAreaCell">
            <strong>
              <bean:message key="question.content"/>
            </strong>
          </span>
        </td>
        <td width="90%">
          <html:textarea cols="60" rows="5" name="gradeActionForm" property="q_content" readonly="true">          </html:textarea>
        </td>
      </tr>
      <tr>
        <td height="30" colspan="8" nowrap>
          <div align="center">          </div>
          <div align="center">          </div>
        </td>
      </tr>
    </table>
    <br>
    <table width="595" border="0" align="center" cellpadding="7" cellspacing="0" class="InputFrameMain"><bean:message key="question.standard"/>
      <tr>
        <td width="50%" nowrap>          &nbsp;
          <span class="InputAreaCell">
            <strong>
              <bean:message key="exam.q_standard"/>
              <br>
              <html:textarea cols="40" rows="5" name="gradeActionForm" property="q_standard" readonly="true">              </html:textarea>
              <br>
              <bean:message key="exam.t_answer"/>
              <br>
              <html:textarea cols="40" rows="5" name="gradeActionForm" property="testPaperDetail.t_answer" readonly="true">              </html:textarea>
              <br>
              <br>
              <bean:message key="exam.q_value"/>
              <html:text maxlength="10" name="gradeActionForm" property="q_value" size="10" readonly="true"/>
              <br>
             <bean:message key="exam.t_value"/>
              <logic:lessThan name="gradeActionForm" property="testPaperDetail.t_value" value="0">
                <html:text maxlength="10" name="gradeActionForm" property="testPaperDetail.t_value" size="10" value="">                </html:text>
              </logic:lessThan>
              <logic:greaterEqual name="gradeActionForm" property="testPaperDetail.t_value" value="0">
                <html:text maxlength="10" name="gradeActionForm" property="testPaperDetail.t_value" size="10">                </html:text>
              </logic:greaterEqual>
            </strong>
          </span>
        </td>

       </tr>
      <tr>
        <td nowrap>          &nbsp;
          <strong>&nbsp;</strong>
        </td>
        <td nowrap>&nbsp;</td>
      </tr>
      <tr>
        <td height="30" colspan="8" nowrap>
          <div align="center">          </div>
          <div align="center">
            <html:hidden name="gradeActionForm" property="testPaperDetail.t_ID"/>
            <html:hidden name="gradeActionForm" property="testPaperDetail.q_ID"/>
            <html:submit styleClass="button" >
         			<bean:message key="button.submit"/>
						</html:submit>
          	<html:button styleClass="button" property="" onclick="history.back(-1)">
              <bean:message key="button.back"/>
          	</html:button>
          </div>
        </td>
      </tr>
    </table>
  </html:form>
</body>
</html:html>
