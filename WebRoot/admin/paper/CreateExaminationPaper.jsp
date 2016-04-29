<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html locale="true">
<head>
  <html:base/>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
	<script language="JavaScript" src="../../js/calendar.js" type="text/JavaScript"></script>
<html:javascript formName="addExaminationPaperForm" dynamicJavascript="true" staticJavascript="true"/>
<title>
  <bean:message key="examinationPaper.title"/>
</title>
</head>
<body>
  <table width="80%" border="0" align="center" cellpadding="7" cellspacing="0" >
    <tr>
      <td class="tabletitletext">
        <div align="center">
          <bean:message key="examinationPaper.title"/>
        </div>
      </td>
    </tr>
  </table>
  <html:errors/>
  <html:form action="/admin/paper/addExaminationPaperAction" method="post"  onsubmit="return validateAddExaminationPaperForm(this);">
    <strong><bean:message key="examinationPaper.baseinfo" /></strong>

<table width="80%" border="0" align="center" cellpadding="5" cellspacing="0" class="InputFrameMain">
  <!--DWLayoutTable-->
  <tr>
    <td>&nbsp;</td>
    <td height="8" nowrap><div align="right" nowrap><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examinationPaper.name" /></div></td>
    <td nowrap><html:text name="addExaminationPaperForm" property="examinationPaper.e_name" style="width=110"/> </td>
    <td nowrap><div align="right" nowrap><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examinationPaper.type" /></div></td>
    <td nowrap><html:select name="addExaminationPaperForm" property="examinationPaper.e_type" style="width=110"> <html:optionsCollection name="e_typeOpts"/> </html:select> </td>
    <td nowrap><div align="right" nowrap><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examinationPaper.time" /></div></td>
    <td nowrap><html:text name="addExaminationPaperForm" property="examinationPaper.e_timer" style="width=110"/><bean:message key="examinationPaper.mi" /></td>
  </tr>
  <tr>
    <td nowrap>&nbsp;</td>
    <td nowrap height="8"> <div align="right" nowrap><bean:message key="examinationPaper.begin_time" /></div></td>
    <td nowrap><html:text name="addExaminationPaperForm" property="examinationPaper.e_begin" onfocus="setday(this)" readonly="true" style="width=110"/> </td>
    <td nowrap><div align="right" nowrap><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examinationPaper.total" /></div></td>
    <td nowrap><html:text property="examinationPaper.e_total" readonly="true" style="width=110"/><bean:message key="examinationPaper.score" /></td>
    <td nowrap><div align="right" nowrap><b><font color="red">*</font></b>&nbsp;&nbsp;&nbsp;<bean:message key="examinationPaper.passvalue" /></div></td>
    <td nowrap><html:text property="examinationPaper.e_passvalue" style="width=110"/><bean:message key="examinationPaper.score" /></td>
  </tr>
  <tr>
    <td nowrap>&nbsp;</td>
    <td height="8"> <div align="right" nowrap><bean:message key="examinationPaper.end_time" /></div></td>
    <td nowrap> <html:text name="addExaminationPaperForm" property="examinationPaper.e_end" onfocus="setday(this)" readonly="true" style="width=110"/> </td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
    <td>&nbsp;</td>
  </tr>
</table>
    <strong><bean:message key="examinationPaper.qlist" /></strong>
    <table width="80%" border="1" align="center" cellpadding="7" cellspacing="0" class="InputFrameMain">
      <tr>
        <td nowrap>
          <strong><bean:message key="index" /></strong>
        </td>
        <td nowrap>
          <strong><bean:message key="question.type" /></strong>
        </td>
        <td nowrap>
          <strong><bean:message key="examinationPaper.idlist" /></strong>
        </td>
        <td nowrap>
          <strong><bean:message key="examinationPaper.count" /></strong>
        </td>
        <td nowrap>
          <strong><bean:message key="examinationPaper.easy" /></strong>
        </td>
        <td nowrap>
          <strong><bean:message key="examinationPaper.mid" /></strong>
        </td>
        <td nowrap>
          <strong><bean:message key="examinationPaper.hard" /></strong>
        </td>
        <td nowrap>
          <strong><bean:message key="examinationPaper.value" /></strong>
        </td>
        <td nowrap>
          <strong><bean:message key="examinationPaper.operation" /></strong>
        </td>
      </tr>
      <tr>
        <td>1</td>
        <td nowrap><bean:message key="question.single" /></td>
        <td>
          <html:text property="singleQ_idList" readonly="true"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="singleTotalCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="singleSimpleCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="singleMidCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="singleDifficultyCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="singleTotalValue"/>
        </td>
        <td nowrap="nowrap">
          <html:link action="/admin/paper/toListQuestionAction?question.q_type=047&action=single" paramId="e_id" paramName="addExaminationPaperForm" paramProperty="examinationPaper.e_id"><bean:message key="examinationPaper.add" /></html:link>
          <html:link action="/admin/paper/clearIdList?action=single" paramId="e_id" paramName="addExaminationPaperForm" paramProperty="examinationPaper.e_id"><bean:message key="examinationPaper.clear" /></html:link>
        </td>
      </tr>
      <tr>
        <td>2</td>
        <td nowrap><bean:message key="question.multi" /></td>
        <td>
          <html:text property="multiQ_idList" readonly="true"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="multiTotalCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="multiSimpleCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="multiMidCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="multiDifficultyCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="multiTotalValue"/>
        </td>
        <td nowrap="nowrap">
          <html:link action="/admin/paper/toListQuestionAction?question.q_type=048&action=multi" paramId="e_id" paramName="addExaminationPaperForm" paramProperty="examinationPaper.e_id"><bean:message key="examinationPaper.add" /></html:link>
          <html:link action="/admin/paper/clearIdList?action=multi" paramId="e_id" paramName="addExaminationPaperForm" paramProperty="examinationPaper.e_id"><bean:message key="examinationPaper.clear" /></html:link>
        </td>
      </tr>
      <tr>
        <td>3</td>
        <td nowrap><bean:message key="question.fitin" /></td>
        <td>
          <html:text property="fitinQ_idList" readonly="true"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="fitinTotalCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="fitinSimpleCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="fitinMidCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="fitinDifficultyCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="fitinTotalValue"/>
        </td>
        <td nowrap="nowrap">
          <html:link action="/admin/paper/toListQuestionAction?question.q_type=049&action=fitin" paramId="e_id" paramName="addExaminationPaperForm" paramProperty="examinationPaper.e_id"><bean:message key="examinationPaper.add" /></html:link>
          <html:link action="/admin/paper/clearIdList?action=fitin" paramId="e_id" paramName="addExaminationPaperForm" paramProperty="examinationPaper.e_id"><bean:message key="examinationPaper.clear" /></html:link>
        </td>
      </tr>
      <tr>
        <td>4</td>
        <td nowrap><bean:message key="question.answer" /></td>
        <td>
          <html:text property="answerQ_idList" readonly="true"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="answerTotalCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="answerSimpleCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="answerMidCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="answerDifficultyCount"/>
        </td>
        <td>
          <bean:write name="addExaminationPaperForm" property="answerTotalValue"/>
        </td>
        <td nowrap="nowrap">
          <html:link action="/admin/paper/toListQuestionAction?question.q_type=050&action=answer" paramId="e_id" paramName="addExaminationPaperForm" paramProperty="examinationPaper.e_id"><bean:message key="examinationPaper.add" /></html:link>
          <html:link action="/admin/paper/clearIdList?action=answer" paramId="e_id" paramName="addExaminationPaperForm" paramProperty="examinationPaper.e_id"><bean:message key="examinationPaper.clear" /></html:link>
        </td>
      </tr>
    </table>
    <table width="80%" border="0" align="center" cellpadding="7" cellspacing="0" class="tabletoolbar">
      <tr>
        <td>
          <html:hidden property="examinationPaper.e_id"/>
        </td>
        <td>
          <html:hidden property="examinationPaper.e_idlist"/>
        </td>
      </tr>
      <tr>
        <td width="3%" nowrap>&nbsp;</td>
        <td height="20">
          <html:submit styleClass="button"><bean:message key="button.submit" /></html:submit>
        </td>
        <td width="20">
          <html:reset styleClass="button"><bean:message key="button.reset" /></html:reset>
        </td>
      </tr>
    </table>
  </html:form>
</body>
</html:html>
