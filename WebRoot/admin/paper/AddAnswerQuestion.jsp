<%@page contentType="text/html; charset=GBK" session="true"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<html:html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
<!--
function setReadOnly() {
  var l = document.forms[1].stringMultibox.length;
  for (var i=0;i<l;i++) {
    if (document.forms[1].stringMultibox[i].checked) {
      document.forms[1].stringMultibox[i].disabled = "true";
    }
  }
}
//-->
</script>
</head>
<body onload="setReadOnly()">
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
    <tr>
      <td class="tabletitletext">
        <div align="center" class="tabletitletext"><bean:message key="examinationPaper.choosebyhand" /></div>
      </td>
    </tr>
  </table>
 
  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
    <tr>
      <td width="100%" class="tabletitletext">
        <div align="center" class="tabletitletext">
          <div align="left"> <bean:message key="question.resultSet" />
          </div>
        </div>
      </td>
    </tr>
  </table>
  <html:form action="/admin/paper/addQuestionToExaminationPaperAction?action=answer">
    <table width="80%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
      <tr class="Listtitle">
        <td width="10%" nowrap class="listcellTitle"><bean:message key="question.choise" /></td>
        <td width="8%" nowrap class="listcellTitle"><bean:message key="question.id" /></td>
        <td width="8%" nowrap class="listcellTitle"><bean:message key="question.knowledge" /></td>
        <td width="4%" nowrap class="listcellTitle"><bean:message key="question.difficulty" /></td>
        <td width="4%" nowrap class="listcellTitle"><bean:message key="question.value" /></td>
        <td width="4%" nowrap class="listcellTitle"><bean:message key="question.picture" /></td>
        <td width="16%" nowrap class="listcellTitle"><bean:message key="question.operation" /></td>
      </tr>
      <logic:iterate id="question" name="questionSet" indexId="index">
        <tr>
          <td align="center">
            <html:multibox name="questionActionForm" property="stringMultibox">
              <bean:write name="question" property="q_id"/>
            </html:multibox>
<%= index.intValue() + 1 %>          </td>
          <td align="left">
            <bean:write name="question" property="q_id" filter="true"/>
          </td>
          <td align="left">
            <bean:write name="question" property="q_knowledge"/>
          </td>
          <td align="left">
            <bean:write name="question" property="q_difficulty"/>
          </td>
          <td align="center">
            <bean:write name="question" property="q_value"/>
          </td>
          <td align="center">
            <bean:write name="question" property="q_picture"/>
          </td>
          <td align="center">
            <html:link action="/admin/question/showQuestionDetailsAction?submit_reset=ok" paramId="q_id" paramName="question" paramProperty="q_id" target="_blank">
              <bean:message key="question.view" />
              </html:link>
          </td>
        </tr>
      </logic:iterate>
    </table>
   
    <center>
      <table>
        <tr>
          <td height="30" colspan="8" nowrap class="button">
            <div align="center">            </div>
            <div align="center">
              <html:hidden  name="questionActionForm" property="e_id"/>
              <html:submit property="submit" styleClass="button"><bean:message key="button.submit" /></html:submit>
              <html:reset property="reset" styleClass="button"><bean:message key="button.reset" /></html:reset>
            </div>
          </td>
        </tr>
      </table>
    </center>
  </html:form>
</body>
</html:html>
