<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html locale="true">
<head>
  <html:base/>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
</head>
<body bgcolor="#ffffff">
  <center>
    <p>&nbsp;</p>
    <h1>
      <bean:message key="control.absenceList"/>
    </h1>
  </center>

  <p>&nbsp;</p>
  <hr />
  <html:form action="/admin/program/controlAction.do?page=1">
    <table width="50%" border="1" align="center" cellpadding="2" cellspacing="0" class="InputFrameMain">
      <tr>
        <td width="5%" nowrap class="listcellTitle">
          <bean:message key="search.selectIt"/>
        </td>
        <td width="50%" nowrap>
          <div align="center">
            <strong>
              <span class="InputAreaCell">
                <bean:message key="control.examinee_id"/>
              </span>
            </strong>
          </div>
        </td>
        <td width="50%" nowrap>
          <div align="center">
            <strong>
              <span class="InputAreaCell">
                <bean:message key="control.name"/>
              </span>
            </strong>
          </div>
        </td>
      </tr>
      <logic:iterate id="testPaper" name="testPaperList" indexId="index">
        <tr>
          <td nowrap>
            <div align="center"><%= index.intValue()+1 %>            </div>
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
        </tr>
      </logic:iterate>
    </table>
    <table align="center">
      <tr>
        <td height="30" colspan="8" nowrap>
          <div align="center">
            <html:button  property="back" styleClass="button" onclick="javascript:history.back()">
              <bean:message key="button.confirm"/>
            </html:button>
          </div>
        </td>
      </tr>
    </table>
  </html:form>
</body>
</html:html>
