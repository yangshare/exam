<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html>
<head>
<title>
  <bean:message key="question.title.list"/>
</title>
<script type="text/JavaScript">
function showDetails(){
  var q_id = q_id1;
 var ReturnAry=window.showModalDialog('/admin/showQuestionDetailsAction.do','q_id','dialogHeight: 600px;dialogWidth: 800px; center: yes; status:no;help: no');
}
</script>  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
    <tr>
      <td class="tabletitletext">
        <div align="center" class="tabletitletext">
          <bean:message key="question.title.list"/>
        </div>
      </td>
    </tr>
  </table>
  
  <html:form action="/admin/question/AUD_QuestionAction.do?method=deleteQuestion&action=noQType">
    <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
      <tr>
        <td width="33%" class="tabletitletext">
          <div align="center" class="tabletitletext">
            <div align="left">
              <bean:message key="question.resultSet"/>
            </div>
          </div>
        </td>
        <td width="51%" class="tabletitletext">
          <div align="right">          </div>
        </td>
        <td width="16%" class="tabletitletext">
          <div align="center">
            <html:submit styleClass="button">
              <bean:message key="button.delete"/>
            </html:submit>
          </div>
        </td>
      </tr>
    </table>
    <table width="80%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
      <tr class="Listtitle">
        <td width="7%" nowrap="nowrap" align="center">
          <bean:message key="question.choise"/>
        </td>
        <td width="10%" nowrap="nowrap" align="center">
          <bean:message key="question.id"/>
        </td>
        <td width="7%" nowrap="nowrap" align="center">
          <bean:message key="question.type"/>
        </td>
        <td width="3%" nowrap="nowrap" align="center">
          <bean:message key="question.class"/>
        </td>
        <td width="5%" nowrap="nowrap" align="center">
          <bean:message key="question.content"/>
        </td>
        <td width="9%" nowrap="nowrap" align="center">
          <bean:message key="question.knowledge"/>
        </td>
        <td width="13%" nowrap="nowrap" align="center">
          <bean:message key="question.difficulty"/>
        </td>
        <td width="12%" nowrap="nowrap" align="center">
          <bean:message key="question.value"/>
        </td>
        <td width="10%" nowrap="nowrap" align="center">
          <bean:message key="question.picture"/>
        </td>
        <td width="9%" nowrap="nowrap" align="center">
          <bean:message key="question.operation"/>
        </td>
      </tr>

      <logic:iterate id="question" name="questionSet" indexId="index">
        <tr>
          <td align="center" nowrap="nowrap">
            <html:multibox name="questionActionForm" property="stringMultibox">
              <bean:write name="question" property="q_id"/>
            </html:multibox>
<%= index.intValue()+1 %>          </td>
          <td align="center" nowrap="nowrap">
            <bean:write name="question" property="q_id" filter="true"/>
          </td>
          <td align="center" nowrap="nowrap">
            <bean:write name="question" property="q_type"/>
          </td>
          <td align="center" nowrap="nowrap">
            <bean:write name="question" property="q_class"/>
          </td>
          <td align="center" nowrap="nowrap">
            <html:text name="question" property="q_content" size="25" maxlength="300"/>
          </td>
          <td align="center" nowrap="nowrap">
            <bean:write name="question" property="q_knowledge"/>
          </td>
          <td align="center" nowrap="nowrap">
            <bean:write name="question" property="q_difficulty"/>
          </td>
          <td align="center" nowrap="nowrap">
            <bean:write name="question" property="q_value"/>
          </td>
          <td align="center" nowrap="nowrap">
            <bean:write name="question" property="q_picture"/>
          </td>
          <td align="center" nowrap="nowrap">
            <html:link action="/admin/question/showQuestionDetailsAction" paramId="q_id" paramName="question" paramProperty="q_id">
              <bean:message key="question.details"/>
            </html:link>
          </td>
        </tr>
      </logic:iterate>
    </table>
   
  </html:form>
</body>
</html:html>
