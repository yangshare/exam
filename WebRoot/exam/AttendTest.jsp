<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %>

<html:html>
  <html:base/>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title><bean:message key="exam.title"/></title>
<br><br> 
<%@ include file="menu.jsp"%>
<script language="javascript" type="text/javascript">
<!--
function showall(e_id) {
  var actstr;
  {
    actstr="showExamTestAction.do?e_id="+e_id;
    window.showModalDialog(actstr,"","dialogHeight: 600;dialogWidth: 760; center: yes; status:yes;help:no");
  }
}
//-->
</script>
<link href="../css/main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />
<script type="text/javascript" src="../js/tabs.js"></script>
</head>
<body>
  <p class="toolbar">&nbsp;</p>
  <table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
    <tr>
      <td class="tabletitletext">
        <div align="center" class="tabletitletext"><bean:message key="exam.attendexam"/></div>
      </td>
    </tr>
  </table>
  <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
    <tr>
      <td width="33%" class="tabletitletext">
        <div align="center" class="tabletitletext">
          <div align="left">          </div>
        </div>
      </td>
      <td width="51%" class="tabletitletext">
        <div align="right">        </div>
      </td>
      <td width="16%" class="tabletitletext">
        <div align="center">        </div>
      </td>
    </tr>
  </table>
  <table width="80%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
    <tr class="Listtitle">
      <td width="3%" nowrap class="listcellTitle"><bean:message key="index"/></td>
      <td width="25%" nowrap class="listcellTitle"><bean:message key="examinationPaper.name"/></td>
      <td width="10%" nowrap class="listcellTitle"><bean:message key="examinationPaper.type"/></td>
      <td width="13%" nowrap class="listcellTitle"><bean:message key="examinationPaper.begin_time"/></td>
      <td width="13%" nowrap class="listcellTitle"><bean:message key="examinationPaper.end_time"/></td>
      <td width="9%" nowrap class="listcellTitle"><bean:message key="examinationPaper.total"/></td>
      <td width="9%" nowrap class="listcellTitle"><bean:message key="examinationPaper.passvalue"/></td>
      <td width="9%" nowrap class="listcellTitle"><bean:message key="examinationPaper.time"/></td>
      <td width="17%" nowrap class="listcellTitle"><bean:message key="examinationPaper.operation"/></td>
    </tr>
      <logic:iterate id="examinationPaper" name="examinationPaperSet" indexId="index">
        <html:form action="/exam/showExamTestAction">
          <bean:define id="e_id" name="examinationPaper" property="e_id" />
          <html:hidden property="e_id" name="examinationPaper"/>
        <tr class="Listrow1">
          <td width="3%" nowrap class="listcellTitle" align="center">
            <%= index.intValue() + 1 %>
          </td>
          <td nowrap class="listcellTitle">

              <bean:write name="examinationPaper" property="e_name"/>

          </td>
          <td nowrap class="listcellTitle" align="center">

              <bean:write name="examinationPaper" property="e_typeName"/>

          </td>
          <td nowrap class="listcellTitle" align="center">

              <bean:write name="examinationPaper" property="t_begin"/>

          </td>
          <td nowrap class="listcellTitle" align="center">

              <bean:write name="examinationPaper" property="t_end"/>

          </td>
          <td nowrap class="listcellTitle" align="center">
            <bean:write name="examinationPaper" property="e_total"/>
          </td>
          <td nowrap class="listcellTitle" align="center">
            <bean:write name="examinationPaper" property="e_passvalue"/>
          </td>
          <td nowrap class="listcellTitle" align="center">
              <bean:write name="examinationPaper" property="e_timer"/><bean:message key="examinationPaper.mi"/>
          </td>
          <td nowrap class="listcellTitle" align="center">
					<html:submit styleClass="button" property="ATTEND TEST">
					<bean:message key="exam.attendexam"/>
 					</html:submit>
          </td>
        </tr>
  			</html:form>
      </logic:iterate>
    </table>
   
</body>
</html:html>
