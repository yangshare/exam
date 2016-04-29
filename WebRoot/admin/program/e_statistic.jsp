<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<html:html locale="true">
<head>
  <html:base/>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
  <link href="../../css/main.css" rel="stylesheet" type="text/css">
<title>e_statistic</title>
</head>
<body>
  <div align="center">
    <span class="tabletitletext">
      <bean:message key="statis.scoreList"/>
    </span>
  </div>
  <table width="80%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
    <tr class="Listtitle">
      <td width="10%" nowrap class="listcellTitle">
        <bean:message key="statis.t_class"/>
      </td>
      <td width="8%" nowrap class="listcellTitle">
        <span class="nobr">
          <bean:message key="control.examinee_id"/>
        </span>
      </td>
      <td width="8%" nowrap class="listcellTitle">
        <span class="nobr">
          <bean:message key="examineeActionForm.name"/>
        </span>
      </td>
      <td width="25%" nowrap class="listcellTitle">
        <bean:message key="search.e_name"/>
      </td>
      <td width="4%" nowrap class="listcellTitle">
        <bean:message key="control.t_total"/>
      </td>
    </tr>
    <logic:iterate id="paper" name="testPaperList" indexId="index">
      <tr class='Listrow1' align="center">
        <td nowrap>
          <div align="center"><%= index.intValue()+1 %>          </div>
        </td>
        <td nowrap>
          <div align="center">
            <bean:write name="paper" property="examinee_id"/>
          </div>
        </td>
        <td nowrap>
          <div align="center">
            <bean:write name="paper" property="name"/>
          </div>
        </td>
        <td nowrap>
          <div align="center">
            <bean:write name="paper" property="e_name"/>
          </div>
        </td>
        <td nowrap>
          <div align="center">
            <bean:write name="paper" property="t_total"/>
          </div>
        </td>
      </tr>
    </logic:iterate>
  </table>
  
  <div align="center">
    <br>
    <span class="tabletitletext">
      <bean:message key="statis.areaList"/>
    </span>
    <br>
  </div>
  <table width="80%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
    <tr class="Listtitle">
      <td width="10%" nowrap class="listcellTitle">
        <bean:message key="search.selectIt"/>
      </td>
      <td width="8%" nowrap class="listcellTitle">
        <span class="nobr">
          <bean:message key="statis.score_area"/>
        </span>
      </td>
      <td width="25%" nowrap class="listcellTitle">
        <bean:message key="search.e_name"/>
      </td>
      <td width="25%" nowrap class="listcellTitle">
        <bean:message key="statis.e_percent"/>
      </td>
      <td width="4%" nowrap class="listcellTitle">
        <bean:message key="statis.subSum"/>
      </td>
    </tr>
    <logic:iterate id="score" name="scoreList" indexId="index">
      <tr class='Listrow1' align="center">
        <td nowrap>
          <div align="center"><%= index.intValue()+1 %>          </div>
        </td>
        <td nowrap>
          <div align="center">
            <bean:write name="score" property="score_area"/>
          </div>
        </td>
        <td nowrap>
          <div align="center">
            <bean:write name="score" property="e_name"/>
          </div>
        </td>
        <td nowrap>
          <bean:write name="score" property="e_percent"/>
        </td>
        <td nowrap>
          <div align="center">
            <bean:write name="score" property="subSum"/>
          </div>
        </td>
      </tr>
    </logic:iterate>
  </table>
  <table width="80%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
  <html:button property="backButton" onclick="history.back()" styleClass="button">
      <bean:message key="button.back"/>
    </html:button>
    </table>
</body>
</html:html>
