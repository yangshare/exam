<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page contentType="text/html; charset=GBK" %>
<html:html locale="true">
<head>
  <SCRIPT language=JavaScript>
  function SelectAll(chk,id)
{
  var oEvent = document.all(id);
 var chks = oEvent.getElementsByTagName("INPUT");
 for(var i=0; i<chks.length; i++)
    {
      if(chks[i].type=="checkbox")
  chks[i].checked=true;
    }
}

function UnSelectAll(chk,id)
{
  var oEvent = document.all(id);
 var chks = oEvent.getElementsByTagName("INPUT");
 for(var i=0; i<chks.length; i++)
    {
      if(chks[i].type=="checkbox")
  chks[i].checked=false;
    }
}

  </SCRIPT>
<html:base/>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<link href="../../css/main.css" rel="stylesheet" type="text/css">
<title><bean:message key="examineeChooser.title"/></title>
</head>

<body>
<table width="600" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
  <tr>
    <td class="tabletitletext"><div align="center" class="tabletitletext"><bean:message key="examinee.userlist"/></div></td>
  </tr>
</table>
<html:errors/>
<html:form action="/admin/program/chooseExaminee.do?jsp=e_distribute">
<table width="80%"  border="0" align="center" cellpadding="7" cellspacing="0" class="InputFrameMain">
  <tr>
    <td width="3%" nowrap>&nbsp;</td>
    <td height="20"><div align="right"><bean:message key="examineeActionForm.organization_id"/></div></td>
    <td> <html:select property="organization_id"> <html:optionsCollection name="organizationOpts"/> </html:select> </td>
    <td height="20"><div align="right"><bean:message key="examineeActionForm.post_index"/></div></td>
    <td> <html:select property="post_index"> <html:optionsCollection name="post_indexOpts"/> </html:select></td>
    <td><html:submit styleClass="button"><bean:message key="button.check"/></html:submit>
    </td>
  </tr>
</table>
</html:form>
<html:form action="/admin/program/saveChooseExamineeAction">

<table id="DataGrid1" width="74%"  border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
  <tr>
    <html:button styleClass="button" property="button" onclick="SelectAll(this,'DataGrid1')">
      <bean:message key="selectAll"/>
      </html:button>
      <html:button styleClass="button" property="button" onclick="UnSelectAll(this,'DataGrid1')">
      <bean:message key="UnselectAll"/>
      </html:button>
  </tr>
  <tr>
    <td>
      <table width="100%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
        <tr class="Listtitle">
          <td width="29%" align="center"><bean:message key="gird.index"/></td>
          <td width="71%" align="center"><bean:message key="examineeActionForm.examinee_id"/></td>
        </tr>
      </table>
    </td>
    <td>
      <table width="100%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
        <tr class="Listtitle">
          <td width="29%" align="center"><bean:message key="gird.index"/></td>
          <td width="71%" align="center"><bean:message key="examineeActionForm.examinee_id"/></td>
        </tr>
      </table>
    </td>
    <td>
      <table width="100%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
        <tr class="Listtitle">
          <td width="29%" align="center"><bean:message key="gird.index"/></td>
          <td width="71%" align="center"><bean:message key="examineeActionForm.examinee_id"/></td>
        </tr>
      </table>
    </td>
    <td>
      <table width="100%" border="1px" align="center" cellpadding="0" cellspacing="0" class="tableframe">
        <tr class="Listtitle">
          <td width="29%" align="center"><bean:message key="gird.index"/></td>
          <td width="71%" align="center"><bean:message key="examineeActionForm.examinee_id"/></td>
        </tr>
      </table>
    </td>
  </tr>
<bean:define id="length" name="chooseExamineeActionForm" property="length" type="java.lang.Integer" />
<%
  int size = length.intValue();
  for (int i = 0; i<= size;i++) {
%>
  <tr>
  <logic:iterate id="examinee" name="examinees" indexId="index" offset="<%=String.valueOf(4*i)%>" length="4">
    <td>
      <table width="100%" align="center" cellpadding="0" cellspacing="0">
        <tr>
          <td width="29%" align="center" nowrap="nowrap">
            <%= index.intValue()+1 %>
            <html:multibox property="examineeList" ><bean:write name="examinee" property="examinee_id" /></html:multibox>
          </td>
          <td width="71%" align="center" nowrap="nowrap">
            <bean:write name="examinee" property="examinee_id" filter="true"/>
          </td>
        </tr>
      </table>
    </td>
  </logic:iterate>
  </tr>
<%
  }
%>
</table>

<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0" class="tabletoolbar">
  <tr>
    <td width="33%" class="tabletitletext"><div align="center" class="tabletitletext">
      <div align="left"></div>
	      </div></td>
    <td width="51%" class="tabletitletext"><div align="right">
    </div></td>
    <td width="16%" class="tabletitletext"><div align="center">
      <html:submit styleClass="button"><bean:message key="button.submit"/></html:submit>
    </div>
    </td>
    <td width="16%" class="tabletitletext"><div align="center">
      <html:button property="backButton" onclick="history.back()" styleClass="button">
      <bean:message key="button.back"/>
    </html:button>
    </div>
    </td>
  </tr>

</table>
<html:hidden property="tempList"/>
</html:form>
</body>
</html:html>
