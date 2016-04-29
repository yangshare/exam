<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-menu.tld" prefix="menu" %>
<%@ taglib uri="/WEB-INF/app.tld" prefix="app" %>
<%@ include file="menu.jsp"%>
<%@page contentType="text/html; charset=GBK"%>
<html:html> 
<head>
  <html:base/>
  <meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title><bean:message key="exam.title"/></title>
  <link href="../css/main.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" media="screen" href="../css/global.css" />
<link rel="stylesheet" type="text/css" media="screen" href="../css/tabs.css" />
<script type="text/javascript" src="../js/tabs.js"></script>
</head>
<body bgcolor="#ffffff">
  <html:form action="/exam/saveTestPaper">
    <html:hidden name="examTestActionForm" property="t_id"/>
    <bean:write name="examTestActionForm" property="t_id"/>
    <!--DWLayoutTable-->
    <td height="2">    </td>

<td><html:submit><bean:message key="exam.submit" /></html:submit></td>
    <% int i=-1; %>
    <logic:iterate id="singleQ" name="examTestActionForm" property="singleQ" indexId="index">
      <tr>
        <td width="800" height="30"><%= index.intValue()+1 %>          .
          <bean:write name="singleQ" property="q_content"/>
          (
          <bean:write name="singleQ" property="q_value"/>
          <bean:message key="examinationPaper.score" />)
        </td>
      </tr>
      <tr>
        <% i=i+1;%>
        <td width="50%" nowrap>
          <html:radio value="A" name="examTestActionForm" property='<%= "t_answer[" +i+ "]" %>'/>
          <bean:write name="singleQ" property="text1"/>
          <br>
          <html:radio value="B" name="examTestActionForm" property='<%= "t_answer[" + i + "]" %>'/>
          <bean:write name="singleQ" property="text2"/>
          <br>
          <html:radio value="C" name="examTestActionForm" property='<%= "t_answer[" + i + "]" %>'/>
          <bean:write name="singleQ" property="text3"/>
          <br>
          <html:radio value="D" name="examTestActionForm" property='<%= "t_answer[" + i + "]" %>'/>
          <bean:write name="singleQ" property="text4"/>
          <br>
        </td>

    </logic:iterate>
    <br />
    ********************************************************
     <logic:iterate id="multiQ" name="examTestActionForm" property="multiQ" indexId="index">
              <tr>
                <td width="23" height="30"><%= index.intValue()+1 %>                  .
</td>
                <td width="603">
                  <bean:write name="multiQ" property="q_content"/>
                  (
                  <bean:write name="multiQ" property="q_value"/>
                  <bean:message key="examinationPaper.score" />)
                </td>
              </tr>
              <tr>
                <td height="48">&nbsp;</td>
                <td>
                  <html:multibox value="A"  property='<%= "multi_answer[" + index.intValue() + "]" %>'/>
                  <bean:write name="multiQ" property="text1"/>
                  <br>
                  <html:multibox value="B" property='<%= "multi_answer[" + index.intValue() + "]" %>'/>
                  <bean:write name="multiQ" property="text2"/>
                  <br>
                  <html:multibox value="C" property='<%= "multi_answer[" + index.intValue() + "]" %>'/>
                  <bean:write name="multiQ" property="text3"/>
                  <br>
                  <html:multibox value="D" property='<%= "multi_answer[" + index.intValue() + "]" %>'/>
                  <bean:write name="multiQ" property="text4"/>
                  <br>
                  <html:multibox value="E" property='<%= "multi_answer[" + index.intValue() + "]" %>'/>
                  <bean:write name="multiQ" property="text5"/>
                </td>
              </tr>
            </logic:iterate>
            <br />
            <% int j=0; %>
 <logic:iterate id="fitinQ" name="examTestActionForm" property="fitinQ" indexId="index">
              <tr>
                <td width="23" height="30"><%= index.intValue()+1 %>                  .
</td>
                <td width="603">
                  <bean:write name="fitinQ" property="q_content"/>
                  (
                  <bean:write name="fitinQ" property="q_value"/>
                  <bean:message key="examinationPaper.score" />)
                </td>
              </tr>
              <tr>
                <td height="48">&nbsp;</td>
                <td>
                  <% i=i+1; %>
                  <html:textarea name="examTestActionForm" property='<%= "t_answer[" + i + "]" %>' cols="60" rows="6">                  </html:textarea>
                </td>
              </tr>
            </logic:iterate>
             <logic:iterate id="answerQ" name="examTestActionForm" property="answerQ" indexId="index">
              <tr>
                <td width="23" height="30"><%= index.intValue()+1 %>                  .
</td>
                <td width="603">
                  <bean:write name="answerQ" property="q_content"/>
                  (
                  <bean:write name="answerQ" property="q_value"/>
                  <bean:message key="examinationPaper.score" />)
                </td>
              </tr>
              <tr>
                <td height="48">&nbsp;</td>
                <td>
                    <% i=i+1; %>
                  <html:textarea name="examTestActionForm" property='<%= "t_answer[" + i + "]" %>' cols="60" rows="6">                  </html:textarea>
                </td>
              </tr>
              <tr>
                <td height="48">&nbsp;</td>
                <td>
                  <bean:define id="picture" name="answerQ" property="q_picture">                  </bean:define>
                  <bean:write name="answerQ" property="q_picture"/>
                  <bean:write name="picture"/>
<%=picture%>                <%
                  //String impSrc = picture;
                  //out.println(impSrc);
                %>
                 <html:img src='<bean:write name="picture"/>'/>
                </td>
              </tr>
            </logic:iterate>
  </html:form>
</body>
</html:html>
